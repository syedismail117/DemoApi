package com.example.demoapi.ServerResponseModels;

import java.util.ArrayList;

public class LoginResponseModel {
    private String response;
    private String message;
    private String jsontoken;
    private InfoModel Info;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJsontoken() {
        return jsontoken;
    }

    public void setJsontoken(String jsontoken) {
        this.jsontoken = jsontoken;
    }

    public InfoModel getInfo() {
        return Info;
    }

    public void setInfo(InfoModel info) {
        Info = info;
    }
/*{
        "response": 3,
            "message": "login successfully",
            "jsontoken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjgxMDYwMjI0MjMiLCJpYXQiOjE2Njk3ODU4MjF9.LYnOYyhZP_72XbHfSdbzEQqfQXatoITVOkWaELdz3a4",
            "Info": [
            {
        "_id": "63760685be0e7b6b43297b59",
                "Name": "mohan",
                "userID": "mohan419ecealfa@gmail.com",
                "PhoneNumber": "8106022423",
                "BankName": "SBI",
                "AccountNumber": "1234567890",
                "IFSCCode": "98765443321",
                "AccountName": "Chittepu VenkataMohanReddy",
                "RegisterDate": "1668679301404",
                "merchantID": "MID@1668679301404RkZ27PTu",
                "Password": "$2a$10$g85uaPDWRh7IBE7T6RXrL.Aw5onUkOA3hV2LvX49bfZMCqp..1VHi",
                "__v": 0
    }
    }*/
}
