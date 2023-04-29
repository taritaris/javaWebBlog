package com.taritari.blog.emum;

/**
 * @author taritari
 * @date 2023-04-20 16:29
 * @description
 */
public enum  UrlEnum {
    ADMIN("http://localhost:8081/Gradle___org_example___masterBlog_1_0_SNAPSHOT_war/");
    private String url;

    public String getUrl() {
        return url;
    }

    UrlEnum(String url) {
        this.url = url;
    }
    @Override
    public String toString() {
        return url;
    }

}
