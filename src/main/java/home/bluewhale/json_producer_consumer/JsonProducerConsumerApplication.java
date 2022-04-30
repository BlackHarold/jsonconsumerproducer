package home.bluewhale.json_producer_consumer;

import io.katharsis.spring.boot.v3.KatharsisConfigV3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(KatharsisConfigV3.class)
public class JsonProducerConsumerApplication {

    public static void main(String... args) {
        SpringApplication.run(JsonProducerConsumerApplication.class, args);
    }
}

