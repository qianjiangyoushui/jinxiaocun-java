package com.ecfund.base.rabbitMQ.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * jiaxd-create
 */

@Service
public class PrintOrderProducer {
    private Logger logger = LoggerFactory.getLogger(PrintOrderProducer.class);

    @Resource(name="amqpTemplatePrint")
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Object message) throws IOException {
        logger.info("to send message:{}", message);
        amqpTemplate.convertAndSend(message);
    }
}
