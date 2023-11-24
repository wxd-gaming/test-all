package org.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-16 19:05
 **/
@RestController()
public class HelloController {


    @RequestMapping(path = "/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(path = "/show")
    public HashMap<Object, Object> show() {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("1", "1");
        return objectObjectHashMap;
    }

}
