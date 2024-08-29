package com.test.sensitive.model;


import com.test.sensitive.SensitiveType;
import com.test.sensitive.annotation.JsonMapField;
import com.test.sensitive.annotation.JsonSensitive;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 集合类型
 *
 * @author Emily
 * @since :  Created in 2023/5/28 2:45 PM
 */
@JsonSensitive
public class PeopleMap {
    private String username;
    private String password;
    private LocalDateTime localDateTime;
    private Map<String, SubMap> subMapMap = new HashMap<>();
    @JsonMapField(value = {"password", "username"}, types = {SensitiveType.DEFAULT, SensitiveType.USERNAME})
    private Map<String, String> params = new HashMap<>();
    @JsonMapField(value = {"age", "username"}, types = { SensitiveType.DEFAULT, SensitiveType.USERNAME})
    private Map<Integer, String> ages = new HashMap<>();

    public Map<Integer, String> getAges() {
        return ages;
    }

    public void setAges(Map<Integer, String> ages) {
        this.ages = ages;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, SubMap> getSubMapMap() {
        return subMapMap;
    }

    public void setSubMapMap(Map<String, SubMap> subMapMap) {
        this.subMapMap = subMapMap;
    }

    public static class SubMap {
        private String sub;

        public String getSub() {
            return sub;
        }

        public void setSub(String sub) {
            this.sub = sub;
        }
    }
}
