
package com.freegrownextgen.freegrow.services;

/*
 *
 * importing useful modules
 *
 * */
import java.io.IOException;
import java.net.*;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
/*
 *
 * modules section end
 *
 * */
public class EmailServices {
    /*
     * to make a request we need a json object
     * this methods return json object in the form of String
     *
     * */
    //one params
    EmailServices(String toEmail) {
        this.jsonObject= "{"+quote+"toEmail"+quote+":"+quote+toEmail+quote+"}";
        this.params[0] = toEmail;

    }
    //two params
    EmailServices(String toEmail,String title) {
        this.jsonObject= "{"+quote+"toEmail"+quote+":"+quote+toEmail+quote+","
                +quote+"title"+quote+":"+quote+title+quote+ "}";
        this.params[0] = toEmail;
        this.params[1] = title;

    }
    //three params
    EmailServices(String toEmail,String title,String subject) {
        this.jsonObject= "{"+quote+"toEmail"+quote+":"+quote+toEmail+quote+","
                +quote+"title"+quote+":"+quote+title+quote+","+quote+"subject"+quote+":"+quote+subject+quote+"}";
        this.params[0] = toEmail;
        this.params[1] = title;
        this.params[2] = subject;

    }
    //four params
    EmailServices(String toEmail,String title,String subject,String body) {
        this.jsonObject= "{"+quote+"toEmail"+quote+":"+quote+toEmail+quote+","
                +quote+"title"+quote+":"+quote+title+quote+","+quote+"subject"+quote+
                ":"+quote+subject+quote+","+quote+"body"+quote+":"+quote+body+quote+"}";
        this.params[0] = toEmail;
        this.params[1] = title;
        this.params[2] = subject;
        this.params[3] = body;

    }
    //six params
    EmailServices(String toEmail,String title,
                String subject,String body,String fromEmail,String passkey) {
        this.jsonObject= "{"+quote+"toEmail"+quote+":"+quote+toEmail+quote+","
                +"subject"+":"+quote+title+quote+","+"body"+":"+quote+body+quote
                +","+"fromEmail"+":"+quote+fromEmail+quote+","+"passkey"+":"+quote+passkey+quote+"}";
        this.params[0] = toEmail;
        this.params[1] = title;
        this.params[2] = subject;
        this.params[3] = body;
        this.params[4] = toEmail;
        this.params[5] = passkey;

    }

    /*
     *
     * main send method to send email to specific email
     *
     *
     * */


    HashMap<String, String> sendEmail(){
        if(checkEmail(params[0])){
            if(checkServer()) {
                if(params[4] != "defaultemailapi@gmail.com"){
                    if (makeRequestToServer(jsonObject) == "emailSendSuccess") {
                        response.put("message","emailSendSuccess");
                        response.put("title",params[1]);
                        response.put("subject",params[2]);
                        response.put("body",params[3]);
                        response.put("fromEmail",params[4]);
                    }
                    else{
                        for(int j = 0;j < serverErrors.length;j++){
                            if(makeRequestToServer(jsonObject) == serverErrors[j]){
                                response.put("message",serverErrors[j]);
                                break;
                            }
                            else{
                                response.put("message",makeRequestToServer(jsonObject));
                            }
                        }
                    }
                }
                else{
                    if (makeRequestToServer(jsonObject) == "emailSendSuccess") {
                        response.put("message","emailSendSuccess");
                        response.put("title",params[1]);
                        response.put("subject",params[2]);
                        response.put("body",params[3]);
                    }
                    else{
                        for(int j = 0;j < serverErrors.length;j++){
                            if(makeRequestToServer(jsonObject) == serverErrors[j]){
                                response.put("message",serverErrors[j]);
                                break;
                            }
                            else{
                                response.put("message",makeRequestToServer(jsonObject));
                            }
                        }
                    }
                }
            }
            else{
                response.put("message","serverIsNotRunning");
            }
        }
        else{
            response.put("message","wrongEmail");
        }
        return  response;

    }


    /*
     * checkServer method to check whether server is running or not
     *
     * */
    static boolean checkServer(){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
            HttpClient makeRequest = HttpClient.newHttpClient();
            try {
                HttpResponse<String> responseFromServer = makeRequest.send(request,BodyHandlers.ofString());

                if(responseFromServer.body().contains("running")) return true;

                else return false;
            }
            catch(IOException e) {
                return false;

            }
            catch(InterruptedException e) {
                return false;
            }
        }
        catch(URISyntaxException e) {
            return false;
        }
    }
    /*
     * checking email is valid or not by REGEX
     *
     * */
    static boolean checkEmail(String email) {
        if(email.matches(emailValidationRegex)) return true;
        else return false;
    }

    /*
     * method to make a request with given parameters
     *
     * */

    static String makeRequestToServer(String jsonObject){

        HashMap<String, String> response = new HashMap<>();
        try {

            HttpRequest postRequest = HttpRequest.newBuilder()

                    .uri(new URI(server))
                    .POST(BodyPublishers.ofString(jsonObject))
                    .build();

            HttpClient makeRequest = HttpClient.newHttpClient();

            try {
                HttpResponse<String> responseFromServer = makeRequest.send(postRequest,
                        BodyHandlers.ofString());
                if(responseFromServer.body().contains("emailSendSuccess")) {
                    message= "emailSendSuccess";
                }
                else{
                    for(int i = 0;i < serverErrors.length;i++){
                        if(responseFromServer.body().contains(serverErrors[i])){
                            message = serverErrors[i];
                            break;
                        }
                        else{
                            message = "someThingWrong";
                            //message = responseFromServer.body();


                        }
                    }


                }

            } catch (IOException e) {
                message=  "IOException";
            } catch (InterruptedException e) {
                message = "InterruptedException";
            }
        }
        catch(URISyntaxException e) {
            message =  "URISyntaxException";
        }

        return message;
    }
    static  String url = "https://sendemail.cyclic.app/"; //homepage emailsenderapi url
    static String server = "https://sendemail.cyclic.app/sendEmail/"; // you need to post data to this url
    static  String emailValidationRegex = "^[a-zA-Z0-9.a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+\\.[a-zA-Z]+"; //regex pattern for email validation
    static  char quote = '"'; // "

    static String jsonObject = ""; //to store jsonobject in the form of string
    static String message = "empty"; //to store message
    /*
     *
     * must to pass toEmail at the time initializing
     *
     *
     * */
    static String[] params = {
            "afridfridayan01@gmail.com",//override toEmail
            "EmailAPI",//title
            "Test Message",//subject
            "This Is A Test Message",//body
            "defaultemailapi@gmail.com",// default from email
            "securePassword"//wrong password
    };
    static String[] serverErrors = {
            "wrongCredentials", //if any one from fromEmail and passkey is wrong
            "sendEmailfailed", //this error from server
            "wrongInput", //wrong json input
            "wrongUrl", //wrong url or url is broken
            "serverError", //server is under maintenance
            "passkeyrequired", //you need to give passkey which is app password
            "wrongEmail" //email is wrong
    };
    static HashMap<String,String> response = new HashMap<>(); //store response


}