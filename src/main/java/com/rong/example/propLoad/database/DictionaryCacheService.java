package com.rong.example.propLoad.database;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class DictionaryCacheService {

    /**
     * 配置
     */
    public static Map<String, String> dictionaryInfoMap;

    /**
     * spring自动注入（自动发现手动注入的数据源dataSource）
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void load() {
        log.info("[内存数据]--开始加载字典表数据");

        Map<String, String> _dictionaryInfoMap = new ConcurrentHashMap();
        List<Dictionary> dictionaryList =
                jdbcTemplate.query("select *  from t_dictionary", new BeanPropertyRowMapper<Dictionary>(Dictionary.class));

        for (Dictionary dictionary : dictionaryList) {
            // name为key value为对象
            _dictionaryInfoMap.put(dictionary.getName(), dictionary.getValue());
        }

        dictionaryInfoMap = _dictionaryInfoMap;
    }
}
