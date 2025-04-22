package com.freegrownextgen.freegrow.services;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class EmailServices {

    private static final String url = "https://freeemailapi.vercel.app/";
    private static final String server = "https://freeemailapi.vercel.app/sendEmail/";
    private static final String emailValidationRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private static final String[] serverErrors = {
        "wrongCredentials",
        "sendEmailfailed",
        "wrongInput",
        "wrongUrl",
        "serverError",
        "passkeyrequired",
        "wrongEmail"
    };

    private static final HashMap<String, String> response = new HashMap<>();
    private final JSONObject jsonObject;
    private final String[] params = new String[6];

    public EmailServices(String toEmail) {
        this(toEmail, "", "", "", "defaultemailapi@gmail.com", "");
    }

    public EmailServices(String toEmail, String title) {
        this(toEmail, title, "", "", "defaultemailapi@gmail.com", "");
    }

    public EmailServices(String toEmail, String title, String subject) {
        this(toEmail, title, subject, "", "defaultemailapi@gmail.com", "");
    }

    public EmailServices(String toEmail, String title, String subject, String body) {
        this(toEmail, title, subject, body, "defaultemailapi@gmail.com", "");
    }

    public EmailServices(String toEmail, String title, String subject, String body, String fromEmail, String passkey) {
        this.params[0] = toEmail;
        this.params[1] = title;
        this.params[2] = subject;
        this.params[3] = body;
        this.params[4] = fromEmail;
        this.params[5] = passkey;

        jsonObject = new JSONObject();
        jsonObject.put("toEmail", toEmail);
        if (!title.isEmpty()) jsonObject.put("title", title);
        if (!subject.isEmpty()) jsonObject.put("subject", subject);
        if (!body.isEmpty()) jsonObject.put("body", body);
        if (!fromEmail.equals("defaultemailapi@gmail.com")) jsonObject.put("fromEmail", fromEmail);
        if (!passkey.isEmpty()) jsonObject.put("passkey", passkey);
    }

    public HashMap<String, String> sendEmail() {
        if (!checkEmail(params[0])) {
            response.put("message", "wrongEmail");
            return response;
        }

        if (!checkServer()) {
            response.put("message", "serverIsNotRunning");
            return response;
        }

        String result = makeRequestToServer(jsonObject.toString());
        if (result.equals("emailSendSuccess")) {
            response.put("message", "emailSendSuccess");
            response.put("title", params[1]);
            response.put("subject", params[2]);
            response.put("body", params[3]);
            response.put("fromEmail", params[4]);
        } else {
            response.put("message", getServerError(result));
        }

        return response;
    }

    private static boolean checkServer() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> serverResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            return serverResponse.body().contains("running");
        } catch (IOException | InterruptedException | URISyntaxException e) {
            return false;
        }
    }

    private static boolean checkEmail(String email) {
        return email.matches(emailValidationRegex);
    }

    private static String makeRequestToServer(String jsonBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(server))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .header("Content-Type", "application/json")
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> serverResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = serverResponse.body();
            if (responseBody.contains("emailSendSuccess")) {
                return "emailSendSuccess";
            }

            for (String err : serverErrors) {
                if (responseBody.contains(err)) {
                    return err;
                }
            }

            return "someThingWrong";

        } catch (IOException | InterruptedException | URISyntaxException e) {
            return e.getClass().getSimpleName();
        }
    }

    private static String getServerError(String message) {
        for (String err : serverErrors) {
            if (err.equals(message)) {
                return err;
            }
        }
        return "someThingWrong";
    }
}
