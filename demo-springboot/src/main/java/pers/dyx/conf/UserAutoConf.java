package pers.dyx.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户配置
 *
 * @author dyx
 * @date 2020/10/29 16:48
 */
@Component
@ConfigurationProperties(prefix = "conf.auto")
public class UserAutoConf {

    private String string;

    private Integer integer;

    private Boolean aboolean;

    private List<String> hosts = new ArrayList<String>();

    private String[] ports;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Boolean getAboolean() {
        return aboolean;
    }

    public void setAboolean(Boolean aboolean) {
        this.aboolean = aboolean;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public String[] getPorts() {
        return ports;
    }

    public void setPorts(String[] ports) {
        this.ports = ports;
    }
}
