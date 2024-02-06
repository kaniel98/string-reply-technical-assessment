package com.beta.replyservice.controller;

import com.beta.replyservice.models.ReplyMessage;
import com.beta.replyservice.models.ReplyMessageError;
import com.beta.replyservice.service.ReplyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ReplyControllerV2Test {

    @Mock
    private ReplyService replyService;

    @InjectMocks
    private ReplyControllerV2 replyControllerV2;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Success: Return message is returned successfully")
    void replyingV2Success() {
        // * Given
        String expected = "5a8973b3b1fafaeaadf10e195c6e1dd4";
        given(replyService.encodeMessage("12-kbzw9ru")).willReturn(new ReplyMessage(expected));

        // * When
        ReplyMessage actual = replyControllerV2.replyingV2("12-kbzw9ru");

        // * Then
        assertNotNull(actual);
        assertEquals(expected, actual.getMessage());
    }

    @Test
    @DisplayName("Success: Return message is empty error")
    void replyingV2Empty() {
        // * Given
        String expected = ReplyMessageError.messageIsEmpty.getMessage();

        // * When
        ReplyMessage actual = replyControllerV2.replyingV2();

        // * Then
        assertNotNull(actual);
        assertEquals(expected, actual.getMessage());
    }
}