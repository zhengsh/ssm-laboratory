package cn.edu.cqvie.rabbitmq.server.impl;

import cn.edu.cqvie.rabbitmq.server.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@Slf4j
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString().replace("-", "");
        output.send(MessageBuilder.withPayload(serial).build());
        log.info(" =======> serial: {}", serial);
        return null;
    }
}
