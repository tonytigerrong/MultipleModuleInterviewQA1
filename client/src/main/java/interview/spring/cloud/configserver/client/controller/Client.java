package interview.spring.cloud.configserver.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RefreshScope
@RestController
public class Client {

    @Autowired
    @Lazy
    RestTemplate restTemplate;

    @Value("${websecurity.web.hellocontroller.cloudConfigTest}")
    String url;
    @GetMapping("/client")
    public List clientTest(){
        return restTemplate.getForObject(url,List.class);
    }
}
