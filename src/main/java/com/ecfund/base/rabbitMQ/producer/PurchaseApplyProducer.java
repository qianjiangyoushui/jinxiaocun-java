package com.ecfund.base.rabbitMQ.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class PurchaseApplyProducer {
    private Logger logger = LoggerFactory.getLogger(PurchaseApplyProducer.class);

        @Resource(name="amqpTemplatePurchaseApply")
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Object message) throws IOException {
        logger.info("采购开单====to send message:{}", message);
        amqpTemplate.convertAndSend(message);
    }
}
