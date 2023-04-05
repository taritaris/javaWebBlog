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
    public static void main(String[] args) {
        YmlConfig ymlConfig = new YmlConfig();
        Map<String, Object> datasource = ymlConfig.getApplication("datasource");
        String url = datasource.get("url").toString();
        String username = datasource.get("username").toString();
        System.out.println("url: " + url);
        System.out.println("username: " + username);
    }
}