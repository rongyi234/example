package com.rong.example.task;

import com.rong.example.bean.bo.UserMessage;
import com.rong.example.cache.RedisClient;
import com.rong.example.service.UserMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ExampleTask {
    @Autowired
    private UserMessageService userMessageService;

    @Autowired
    private RedisClient redisClient;

    /**
     * 样例
     */
    @Async("executor1")
    @Scheduled(cron = "${task.ExampleTask}")
    public void auditTask() {
        if (redisClient.getLock("task_exampleTask", 500, TimeUnit.MILLISECONDS)) {
            log.info("定时任务启动：ExampleTask");
            try {

                List<UserMessage> msgList = userMessageService.selectMsgInfo(null);
                int count= msgList==null?0:msgList.size();

                log.info("执行结果，消息数量为："+count);

            } catch (Exception e) {
                log.error("定时任务异常：ExampleTask",e);
            }finally {
                redisClient.releaseLock("task_exampleTask");
            }
        }
    }
}
