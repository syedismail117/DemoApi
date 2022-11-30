package com.example.demoapi.ServerResponseModels;

public class InfoModel {
    private String _id;
    private String Name;
    private String userID;
    private String PhoneNumber;
    private String AccountNumber;
    private String IFSCCode;
    private String AccountName;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }
/*  "_id": "63760685be0e7b6b43297b59",
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
             "__v": 0*/
}
