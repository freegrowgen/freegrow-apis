package com.freegrownextgen.freegrow.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

public class AuthUtils {

    public String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }

    public int generateOtp() {
        SecureRandom random = new SecureRandom();
        return 100000 + random.nextInt(900000);
    }

    public Date getCurrentDateTime() {
        return Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant());
    }

    public String generateRandomToken(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder token = new StringBuilder();
        SecureRandom rnd = new SecureRandom();
        for (int i = 0; i < length; i++) {
            token.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return token.toString();
    }

    public boolean isDifferenceGreaterThanMinutes(Date startDate, Date endDate, long minutes) {
        Instant start = startDate.toInstant();
        Instant end = endDate.toInstant();
        long diffMinutes = Duration.between(start, end).toMinutes();
        return diffMinutes > minutes;
    }

    public String generateRandoUsername(String firstName) {
        firstName = firstName.trim().toLowerCase().replaceAll("[^a-z]", "");
        int randomNum = 10000 + new Random().nextInt(900000000); 
        return firstName + "-" + randomNum;
    }
}
