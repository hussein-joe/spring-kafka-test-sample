package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@EmbeddedKafka(topics = {"topic"})
public class DemoApplicationTests {

    @Autowired
    private TotalMessageLengthListener totalMessageLengthListener;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void shouldReturnLengthOfAllMessagesInTheTopic() throws InterruptedException {
        kafkaTemplate.send("topic", "key", "1");
        kafkaTemplate.send("topic", "key2", "22");
        kafkaTemplate.send("topic", "key3", "333");
        Thread.sleep(3000);

        assertThat(totalMessageLengthListener.getTotalMessageLength()).isEqualTo(6);
    }

}
