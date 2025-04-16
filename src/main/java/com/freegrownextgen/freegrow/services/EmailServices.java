package com.freegrownextgen.freegrow.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class EmailServices {

    public EmailServices(String toEmail) {
        this.jsonObject = "{\"toEmail\":\"" + toEmail + "\"}";
        this.params[0] = toEmail;
    }

    public EmailServices(String toEmail, String title) {
        this.jsonObject = "{\"toEmail\":\"" + toEmail + "\",\"title\":\"" + title + "\"}";
        this.params[0] = toEmail;
        this.params[1] = title;
    }

    public EmailServices(String toEmail, String title, String subject) {
        this.jsonObject = "{\"toEmail\":\"" + toEmail + "\",\"title\":\"" + title + "\",\"subject\":\"" + subject + "\"}";
        this.params[0] = toEmail;
        this.params[1] = title;
        this.params[2] = subject;
    }

    public EmailServices(String toEmail, String title, String subject, String body) {
        this.jsonObject = "{\"toEmail\":\"" + toEmail + "\",\"title\":\"" + title + "\",\"subject\":\"" + subject + "\",\"body\":\"" + body + "\"}";
        this.params[0] = toEmail;
        this.params[1] = title;
        this.params[2] = subject;
        this.params[3] = body;
    }

    public EmailServices(String toEmail, String title, String subject, String body, String fromEmail, String passkey) {
        this.jsonObject = "{\"toEmail\":\"" + toEmail + "\",\"subject\":\"" + title + "\",\"body\":\"" + body + "\",\"fromEmail\":\"" + fromEmail + "\",\"passkey\":\"" + passkey + "\"}";
        this.params[0] = toEmail;
        this.params[1] = title;
        this.params[2] = subject;
        this.params[3] = body;
        this.params[4] = fromEmail;
        this.params[5] = passkey;
    }

    public HashMap<String, String> sendEmail() {
        if (checkEmail(params[0])) {
            if (checkServer()) {
                if (!params[4].equals("defaultemailapi@gmail.com")) {
                    String result = makeRequestToServer(jsonObject);
                    if (result.equals("emailSendSuccess")) {
                        response.put("message", "emailSendSuccess");
                        response.put("title", params[1]);
                        response.put("subject", params[2]);
                        response.put("body", params[3]);
                        response.put("fromEmail", params[4]);
                    } else {
                        response.put("message", getServerError(result));
                    }
                } else {
                    String result = makeRequestToServer(jsonObject);
                    if (result.equals("emailSendSuccess")) {
                        response.put("message", "emailSendSuccess");
                        response.put("title", params[1]);
                        response.put("subject", params[2]);
                        response.put("body", params[3]);
                    } else {
                        response.put("message", getServerError(result));
                    }
                }
            } else {
                response.put("message", "serverIsNotRunning");
            }
        } else {
            response.put("message", "wrongEmail");
        }
        return response;
    }

    static boolean checkServer() {
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

    static boolean checkEmail(String email) {
        return email.matches(emailValidationRegex);
    }

    static String makeRequestToServer(String jsonObject) {
        try {
            System.out.println(jsonObject);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(server))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonObject))
                    .header("Content-Type", "application/json")
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> serverResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (serverResponse.body().contains("emailSendSuccess")) {
                return "emailSendSuccess";
            }

            for (String err : serverErrors) {
                if (serverResponse.body().contains(err)) {
                    return err;
                }
            }

            return "someThingWrong";

        } catch (IOException e) {
            return "IOException";
        } catch (InterruptedException e) {
            return "InterruptedException";
        } catch (URISyntaxException e) {
            return "URISyntaxException";
        }
    }

    static String getServerError(String message) {
        for (String err : serverErrors) {
            if (err.equals(message)) {
                return err;
            }
        }
        return "someThingWrong";
    }

    static String url = "https://freeemailapi.vercel.app/";
    static String server = "https://freeemailapi.vercel.app/sendEmail/";
    static String emailValidationRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    static String jsonObject = "";
    static String[] params = {
        "afridfridayan01@gmail.com",//override toEmail
        "EmailAPI",//title
        "Test Message",//subject
        "This Is A Test Message",//body
        "defaultemailapi@gmail.com",// default from email
        "securePassword"//wrong password
};
    static String[] serverErrors = {
            "wrongCredentials",
            "sendEmailfailed",
            "wrongInput",
            "wrongUrl",
            "serverError",
            "passkeyrequired",
            "wrongEmail"
    };
    static HashMap<String, String> response = new HashMap<>();
}
