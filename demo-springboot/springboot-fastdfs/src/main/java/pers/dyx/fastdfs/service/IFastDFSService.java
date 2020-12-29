package pers.dyx.fastdfs.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFastDFSService {
    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);
}
