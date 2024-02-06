package com.beta.replyservice.service;

import com.beta.replyservice.models.ReplyMessage;
import org.springframework.http.ResponseEntity;

public interface ReplyService {
    ResponseEntity<ReplyMessage> encodeMessage(String message);
}
