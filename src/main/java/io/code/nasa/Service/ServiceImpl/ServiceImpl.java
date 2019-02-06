package io.code.nasa.Service.ServiceImpl;

import io.code.nasa.Dao.ApiDetails;
import io.code.nasa.Entity.Info;
import io.code.nasa.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@org.springframework.stereotype.Service
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

    public String getApod(){
        String url = "https://api.nasa.gov/planetary/apod?api_key=" + getKey();

        //we shall make our first api call :
        HttpHeaders httpHeaders = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String>request = new HttpEntity<>("",httpHeaders);
        ResponseEntity<String>response = restTemplate.exchange(url,HttpMethod.GET,request,String.class);

        return response.getBody();
    }

}
