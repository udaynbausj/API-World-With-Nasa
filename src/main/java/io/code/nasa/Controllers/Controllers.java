package io.code.nasa.Controllers;


import io.code.nasa.Dao.ApiDetails;
import io.code.nasa.Entity.Info;
import io.code.nasa.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controllers {

    @Autowired
    Service service;
    @Autowired
    ApiDetails apiDetails;

//    @GetMapping("/add")
//    public Map<String,String> add(){
//        Info info = new Info();
//        info.setId(1);
//        info.setKet("");
//        apiDetails.save(info);
//
//        Map<String,String> mp = new HashMap<>();
//
//        mp.put("Status","Successfully added the info");
//        return mp;
//    }

    @GetMapping("/apod")
    public Object getApod(){
        return service.getApod();
    }
}
