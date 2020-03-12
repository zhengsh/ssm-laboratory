package cn.edu.cqvie.rabbitmq.controller;

import cn.edu.cqvie.rabbitmq.server.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private IMessageProvider messageProvider;

    @RequestMapping("/rabbit/send")
    public String sendMessage() {
        return messageProvider.send();
    }
}
