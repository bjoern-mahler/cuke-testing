package de.spinwork.testapp.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private SomeEntityRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Map get() {
        Map<String, Object> response = new HashMap<>();
        response.put("test", "testValue");
        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> post(@RequestBody Map<String, Object> submitValues) {
        submitValues.put("success", Boolean.TRUE);
        return submitValues;
    }


}
