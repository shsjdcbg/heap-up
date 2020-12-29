package pers.dyx;

import org.springframework.stereotype.Service;

/**
 * @author dyx
 * @date 2020/10/30 11:08
 */
@Service
public class DemoService {
    public String sayHello() {
        return "hello from service layer";
    }
}
