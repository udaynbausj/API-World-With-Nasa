package io.code.nasa.Service.ServiceImpl;

import io.code.nasa.Dao.ApiDetails;
import io.code.nasa.Entity.Info;
import io.code.nasa.Service.Service;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@org.springframework.stereotype.Service
@Slf4j
public class ServiceImpl implements Service {


    @Autowired
    ApiDetails apiDetails;

    public String getKey(){
       List<Info>list = apiDetails.findAll();
       String key = "";
       for(Info info : list){
           key = info.getKet();
           break;
       }
       return key;
    }

    public void openBrowser(String  url) throws IOException {
        Runtime rt = Runtime.getRuntime();
        String url1 = url;
        rt.exec("open " + url1);
    }

    public JSONObject getApod() {
        String url = "https://api.nasa.gov/planetary/apod?api_key=" + getKey();

        //we shall make our first api call :
        HttpHeaders httpHeaders = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String>request = new HttpEntity<>("",httpHeaders);
        ResponseEntity<JSONObject>response = restTemplate.exchange(url,HttpMethod.GET,request,JSONObject.class);
        try {
            openBrowser(response.getBody().get("url").toString());
        }catch(Exception e){
            log.error("Error opening Browser to load the nasa image : ( ");
            System.out.println("Exception handled  : "+ e);
        }
        return response.getBody();
    }





}
