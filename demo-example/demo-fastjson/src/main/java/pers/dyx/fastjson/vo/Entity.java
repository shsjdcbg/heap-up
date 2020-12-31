package pers.dyx.fastjson.vo;

import lombok.Data;

/**
 * @author dyx
 * @date 2020/12/31 16:55
 */
@Data
public class Entity {
    private Integer id;
    private String name;
    private Object value;

    public Entity() {
    }

    public Entity(Integer id, Object value) {
        this.id = id;
        this.value = value;
    }

    public Entity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Entity(String name) {
        this.name = name;
    }
}
