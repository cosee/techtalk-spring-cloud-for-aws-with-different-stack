package biz.cosee.techtalks.springcloud.springbootforaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //Only used for SQS polling
public class SpringBootForAwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootForAwsApplication.class, args);
    }
}
