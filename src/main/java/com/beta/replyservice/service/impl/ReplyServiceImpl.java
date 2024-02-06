package com.beta.replyservice.service.impl;

import com.beta.replyservice.models.ReplyMessage;
import com.beta.replyservice.models.ReplyMessageError;
import com.beta.replyservice.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    @Override
    public ResponseEntity<ReplyMessage> encodeMessage(String messageToBeEncoded) {
        // Check if string is empty / null
        if (StringUtils.isEmpty(messageToBeEncoded)) {
            // Throw Bad Request if message is empty
            return ResponseEntity.badRequest().body(new ReplyMessage(ReplyMessageError.messageIsEmpty.getMessage()));
        }
        // Get the rules and message from the message
        String[] messageParts = messageToBeEncoded.split("-");
        // Make sure the array has 2 parts
        if (messageParts.length != 2) {
            return ResponseEntity.badRequest().body(new ReplyMessage(ReplyMessageError.messageIsInvalid.getMessage()));
        }
        String message = messageParts[1];
        char[] rules = getRules(messageParts[0]);
        // Iterate through the char array - If 1, execute reverseString if 2 execute encodeString
        for (char rule : rules) {
            if (rule == '1') {
                message = reverseString(message);
            } else if (rule == '2') {
                message = encodeString(message);
            } else { // Rule does not exist
                return ResponseEntity.badRequest().body(new ReplyMessage(ReplyMessageError.invalidRule.getMessage()));
            }
        }
        return ResponseEntity.ok().body(new ReplyMessage(message));
    }

    // * Helper function to retrieve the rules from the message
    private char[] getRules(String operations) {
        return operations.toCharArray();
    }

    // * Helper function to reverse the string
    private String reverseString(String message) {
        return new StringBuilder(message).reverse().toString();
    }

    // * Helper function to encode the string with MD5 Hash Algorithm and return as hex
    private String encodeString(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(message.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
