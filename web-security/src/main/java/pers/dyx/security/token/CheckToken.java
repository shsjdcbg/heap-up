package pers.dyx.security.token;

import pers.dyx.tool.localcache.ExpireCache;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * token 请求合法性检验+防重放
 * js代码
 * var curUrl;
 * var sig = "";
 * var chars = "0123456789abcdef";
 * var curTime = parseInt(Math.random() * (9999 - 1000 + 1) + 1000) + "" + Date.parse(new Date());
 * sig = chars.charAt(parseInt(Math.random() * (15 - 15 + 1) + 10)) + chars.charAt(curTime.length) + "" + curTime;
 * <p>
 * var key = "";
 * var keyIndex = -1;
 * for (var i = 0; i < 6; i++) {
 * var c = sig.charAt(keyIndex + 1);
 * key += c;
 * keyIndex = chars.indexOf(c);
 * if (keyIndex < 0 || keyIndex >= sig.length) {
 * keyIndex = i;
 * }
 * }
 * <p>
 * var timestamp = parseInt(Math.random() * (99999999 - 10000000 + 1) + 10000000) + "_" + key + "_" + Date.parse(new Date());
 * var t = timestamp;//LEx.azdg.encrypt(timestamp,key);
 * t = t.replace(/\+/g, "_");
 * curUrl += "?s=" + sig;
 * curUrl += "&t=" + t;
 *
 * @author dyx
 * @version 1.0
 * @date 2020/7/31 14:55
 */
public class CheckToken {

    /**
     * 本地缓存，一天清理一次
     */
    private ExpireCache expireCache = new ExpireCache(24 * 60 * 60);

    public boolean checkToken(HttpServletRequest request) {
        String timestamp = request.getParameter("t");
        String sigature = request.getParameter("s");
        if (StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(sigature)) {
            return false;
        }
        //检查t是否由三部分组成
        String[] arrToken = timestamp.split("_");
        if (arrToken.length != 3) {
            return false;
        }
        //检查第一个元素是否为8位随机数
        int randomInt = Integer.parseInt(arrToken[0]);
        if (!(randomInt >= 10000000 && randomInt <= 99999999)) {
            return false;
        }
        //从签名字符串中提取公钥
        StringBuilder azdgKey = new StringBuilder();
        int keyIndex = -1;
        final String chars = "0123456789abcdef";
        for (int i = 0; i < 6; i++) {
            char c = sigature.charAt(keyIndex + 1);
            azdgKey.append(c);
            keyIndex = chars.indexOf(c);
            if (keyIndex < 0 || keyIndex >= sigature.length()) {
                keyIndex = i;
            }
        }
        //检查第二个元素是否按照规则生成
        if (!azdgKey.toString().equals(arrToken[1])) {
            return false;
        }
        //检查第三个元素是否是时间戳
        Date requestTime = new Date(Long.parseLong(arrToken[2]));
        Date now = new Date();
        //为了应对客户端和服务端时间的准确性，这里容错30分钟
        long interval = Math.abs(now.getTime() - requestTime.getTime());
        long minutes = interval / (60 * 1000);
        if (minutes > 30) {
            return false;
        }
        //检查请求是否重复访问，防重放攻击
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        String key = session.getId() + request.getRequestURI() + arrToken[0] + arrToken[2];
        String cacheData = (String) expireCache.get(key);
        if (cacheData != null && cacheData.length() == 1) {
            return false;
        } else {
            // 保存10天
            expireCache.set(key, null, 864000000);
        }
        return false;
    }
}
