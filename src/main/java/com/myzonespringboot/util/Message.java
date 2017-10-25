package com.myzonespringboot.util;


import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cgq on 2017/10/25.
 */
public class Message {
    public static Map<String, Object> info(Integer code, Object content) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("msg", code == 0 ? "操作成功!" : "操作失败");
        map.put("data", content);
        return map;
    }

    public static Map<String, Object> failure() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 1);
        map.put("msg", "操作失败!");
        map.put("data", null);
        return map;
    }

    public static Map<String, Object> failure(Object content) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 1);
        map.put("msg", "操作失败!");
        map.put("data", content);
        return map;
    }

    public static Map<String, Object> success() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "操作成功!");
        map.put("data", null);
        return map;
    }

    public static Map<String, Object> success(Object content) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "操作成功!");
        map.put("data", content);
        return map;
    }
}
