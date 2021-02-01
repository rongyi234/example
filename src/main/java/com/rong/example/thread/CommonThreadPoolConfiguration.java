package com.rong.example.thread;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 通用异步线程池
 */
@Configuration
public class CommonThreadPoolConfiguration {
    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 20;

    /**
     * 最大线程数，防止OOM
     */
    private static final int MAXIMUM_POOL_SIZE = 200;

    /**
     * 默认线程存活时间
     */
    private static final long DEFAULT_KEEP_ALIVE = 60L;

    /**
     * 线程池名称格式
     */
    private static final String THREAD_POOL_NAME = "commonThread-%d";

    /**
     * 线程工厂名称
     */
    private static final ThreadFactory FACTORY = new BasicThreadFactory.Builder().namingPattern(THREAD_POOL_NAME)
            .daemon(true).build();

    /**
     * 队列大小
     */
    private static final int DEFAULT_SIZE = 500;

    /**
     * 有界阻塞队列，防止OOM
     */
    private static BlockingQueue<Runnable> executeQueue = new ArrayBlockingQueue<>(DEFAULT_SIZE);



    @Bean(name= {"commonThreadPool"})
    public ExecutorService acquireThreadPoolExecutor() {

        return new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, DEFAULT_KEEP_ALIVE,
                TimeUnit.SECONDS, executeQueue, FACTORY);
    }
}
