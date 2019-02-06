package io.code.nasa.Service.ServiceImpl;

import io.code.nasa.Dao.ApiDetails;
import io.code.nasa.Entity.Info;
import io.code.nasa.Service.Service;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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


    public Object getNeos(){

        RestTemplate restTemplate = new RestTemplate();

        Date dt = new Date();
        DateTime dtOrg = new DateTime(dt);
        DateTime dtPlusOne = dtOrg.plusDays(1);
        String today = dtPlusOne.toString().substring(0,10);
        String date_7daysAfter = dtOrg.plusDays(5).toString().substring(0,10);
        String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date="+today+"&end_date="+date_7daysAfter+"&api_key="+getKey();

        ResponseEntity<JSONObject>response = restTemplate.exchange(url,HttpMethod.GET,null,JSONObject.class);

        return response.getBody();
    }

    public Object getMarsPics(){
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&api_key="+getKey();
        ResponseEntity<JSONObject>response = restTemplate.exchange(url,HttpMethod.GET,null,JSONObject.class);

        return response.getBody();
    }


}
