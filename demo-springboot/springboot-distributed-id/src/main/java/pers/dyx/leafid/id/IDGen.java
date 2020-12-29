package pers.dyx.leafid.id;

import pers.dyx.leafid.Result;

public interface IDGen {
    Result get(String key);

    boolean init();
}
