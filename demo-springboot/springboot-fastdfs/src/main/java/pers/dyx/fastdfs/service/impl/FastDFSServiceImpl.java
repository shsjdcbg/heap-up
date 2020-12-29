package pers.dyx.fastdfs.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.dyx.fastdfs.FastDFSClient;
import pers.dyx.fastdfs.service.IFastDFSService;

import javax.annotation.Resource;

@Service
public class FastDFSServiceImpl implements IFastDFSService {

    @Resource
    private FastDFSClient fastDFSClient;

    @Override
    public String uploadFile(MultipartFile file) {
        String result;
        //首先判断是不是空的文件
        if (!file.isEmpty()) {
            //获得文件后缀名
            String a = file.getOriginalFilename().toLowerCase();
            if (a.endsWith(".jpg") || a.endsWith(".jpeg") || a.endsWith(".png")) {
                try {
                    String path = fastDFSClient.uploadFile(file);
                    if (!StringUtils.isEmpty(path)) {
                        result = path;
                    } else {
                        result = "上传失败";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result = "服务异常";
                }
            } else {
                result = "上传失败";
            }
        } else {
            result = "上传失败";
        }
        return result;
    }
}
