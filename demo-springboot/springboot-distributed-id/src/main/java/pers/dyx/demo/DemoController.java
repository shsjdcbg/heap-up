package pers.dyx.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dyx.leafid.MdIdsGen;


/**
 * @author Minbo
 */
@RestController
public class DemoController {

    @GetMapping("/getNewId")
    public Long getNewId() {
        return MdIdsGen.getId();
    }
}
