package com.beta.replyservice.controller;

import com.beta.replyservice.models.ReplyMessage;
import com.beta.replyservice.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2")
public class ReplyControllerV2 {

    private final ReplyService replyService;

    @GetMapping("/reply/{message}")
    public ResponseEntity<ReplyMessage> replyingV2(@PathVariable String message) {
        return replyService.encodeMessage(message);
    }

    @GetMapping("/reply")
    public ResponseEntity<ReplyMessage> replyingV2() {
        return replyService.encodeMessage("");
    }
}
