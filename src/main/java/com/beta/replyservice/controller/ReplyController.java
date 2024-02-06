package com.beta.replyservice.controller;

import com.beta.replyservice.models.ReplyMessage;
import com.beta.replyservice.models.ReplyMessageError;
import com.beta.replyservice.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/reply")
    public ReplyMessage replying() {
        return new ReplyMessage(ReplyMessageError.messageIsEmpty.getMessage());
    }

    @GetMapping("/reply/{message}")
    public ReplyMessage replying(@PathVariable String message) {
        return new ReplyMessage(message);
    }
}