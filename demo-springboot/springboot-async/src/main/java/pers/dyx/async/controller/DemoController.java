package pers.dyx.async.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dyx.async.service.DemoService;

/**
 * @author dyx
 * @date 2021/2/25 15:10
 */
@RestController
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;

    @RequestMapping("/async")
    public String async() {
        log.info("####DemoController####   1");
        demoService.saveLog();
        log.info("####DemoController####   4");
        return "success";
    }

}
