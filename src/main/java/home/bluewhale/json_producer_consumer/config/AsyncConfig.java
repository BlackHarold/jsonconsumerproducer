package home.bluewhale.json_producer_consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        Runtime runtime = Runtime.getRuntime();
        int availableProcessors = runtime.availableProcessors() - 1;
        System.out.println("available processors for JVM: " + availableProcessors);
        return new ThreadPoolExecutor(
                availableProcessors,
                availableProcessors,
                100,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}
