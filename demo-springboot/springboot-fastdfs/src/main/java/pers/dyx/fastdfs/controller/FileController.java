package pers.dyx.fastdfs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.dyx.fastdfs.service.IFastDFSService;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    IFastDFSService iFastDFSService;

    /**
     * 文件上传
     */
    @RequestMapping(value = "/uploadFile", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return iFastDFSService.uploadFile(file);
    }

}
