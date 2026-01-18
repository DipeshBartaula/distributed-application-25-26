package com.hsfulda.demo.products.services;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EMailService {
    private static final Logger logger = Logger.getLogger(EMailService.class.getName());

    public void sendEMail(Long userId) {
        logger.info("Email sent to user with ID: " + userId);
    }
}
