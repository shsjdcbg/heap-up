package pers.dyx.security.scrf;

import org.springframework.context.annotation.PropertySource;

import java.util.ResourceBundle;

@PropertySource("classpath:config.properties")
public class ConfigUtil {
    private static final String CONFIG_FILE = "config";
    private static final ResourceBundle RESOURCE = ResourceBundle.getBundle(CONFIG_FILE);
    public final static String BLANK_LIST = RESOURCE.getString("BlankList");
}
