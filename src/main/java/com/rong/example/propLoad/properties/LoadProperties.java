package com.rong.example.propLoad.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
@Slf4j
@Data
public class LoadProperties {

    public static Map<String,String> paramMap;

    @PostConstruct
    private void init(){
        try {
            log.info("加载默认properties");
            Properties properties = new Properties();
            InputStream inputStream = this.getClass().getResourceAsStream("/example-map.properties");
            properties.load(inputStream);

            paramMap=new HashMap<>((Map)properties);

        } catch (IOException e) {
            log.error("加载默认properties异常");
        }

    }
}
