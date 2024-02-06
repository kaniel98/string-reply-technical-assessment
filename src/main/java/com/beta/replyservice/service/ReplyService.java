package com.beta.replyservice.service;

import com.beta.replyservice.models.ReplyMessage;

public interface ReplyService {
    ReplyMessage encodeMessage(String message);
}
