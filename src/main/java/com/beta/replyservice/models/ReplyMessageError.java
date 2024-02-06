package com.beta.replyservice.models;

import lombok.Getter;

@Getter
public enum ReplyMessageError {
    messageIsEmpty("Message is empty"), messageIsInvalid("Message is invalid"), invalidRule("Invalid Rule");

    private final String message;

    ReplyMessageError(String message) {
        this.message = message;
    }
}
