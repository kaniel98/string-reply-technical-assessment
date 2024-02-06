package com.beta.replyservice.service.impl;

import com.beta.replyservice.models.ReplyMessage;
import com.beta.replyservice.models.ReplyMessageError;
import com.beta.replyservice.service.ReplyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ReplyServiceImplTest {

    @Autowired
    private ReplyService replyService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Success: Encode message returns correct encoded message")
    @Test
    void encodeMessageSuccess() {
        // * Given
        String messageToBeEncoded = "12-kbzw9ru";
        String expected = "5a8973b3b1fafaeaadf10e195c6e1dd4";

        // * When
        ReplyMessage actual = replyService.encodeMessage(messageToBeEncoded);

        // * Then
        assertNotNull(actual);
        assertEquals(expected, actual.getMessage());
    }

    @DisplayName("Failed: Encode message returns incorrect encoded message - message is empty")
    @Test
    void encodeMessageFailed() {
        // * Given
        String messageToBeEncoded = "";
        String expected = ReplyMessageError.messageIsEmpty.getMessage();

        // * When
        ReplyMessage actual = replyService.encodeMessage(messageToBeEncoded);

        // * Then
        assertNotNull(actual);
        assertEquals(expected, actual.getMessage());
    }

    @DisplayName("Failed: Encode message returns incorrect encoded message - message is null")
    @Test
    void encodeMessageFailedNull() {
        // * Given
        String messageToBeEncoded = "12-";
        String expected = ReplyMessageError.messageIsInvalid.getMessage();

        // * When
        ReplyMessage actual = replyService.encodeMessage(messageToBeEncoded);

        // * Then
        assertNotNull(actual);
        assertEquals(expected, actual.getMessage());
    }

    @DisplayName("Failed: Encode message returns incorrect encoded message - invalid rule")
    @Test
    void encodeMessageFailedInvalidRule() {
        // * Given
        String messageToBeEncoded = "13-kbzw9ru";
        String expected = ReplyMessageError.invalidRule.getMessage();

        // * When
        ReplyMessage actual = replyService.encodeMessage(messageToBeEncoded);

        // * Then
        assertNotNull(actual);
        assertEquals(expected, actual.getMessage());
    }
}