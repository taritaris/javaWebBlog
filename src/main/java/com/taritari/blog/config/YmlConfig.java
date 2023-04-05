package com.taritari.blog.config;

import org.yaml.snakeyaml.Yaml;


import java.io.InputStream;
import java.util.Map;

public class YmlConfig {
    public Map<String, Object> getApplication(String applicationName){
        Yaml yaml = new Yaml();
        InputStream inputStream = YmlConfig.class.getClassLoader().getResourceAsStream("application.yml");
        Map<String, Object> obj = yaml.load(inputStream);
        Map<String, Object> datasource = (Map<String, Object>) obj.get(applicationName);
        return datasource;
    }
}