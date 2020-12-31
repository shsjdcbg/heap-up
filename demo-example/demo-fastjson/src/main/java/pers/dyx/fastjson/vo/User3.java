package pers.dyx.fastjson.vo;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dyx
 * @date 2020/12/31 16:06
 */
@Data
public class User3 {
    private int id;

    private Date date;

    private int money;

    private Map<String, Object> attributes = new HashMap<>();
}
