package pers.dyx.tool.redis.redisson.example.collections;

import java.io.Serializable;

/**
 * Description：
 *
 * @author dyx
 * @date 2019/9/5 18:04
 */
public class Signup implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
