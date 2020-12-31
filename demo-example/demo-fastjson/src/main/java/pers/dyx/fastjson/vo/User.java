package pers.dyx.fastjson.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author dyx
 * @date 2020/12/31 14:35
 */
@Data
@AllArgsConstructor
public class User {

    private int id;

    private Date date;

    private int money;
}
