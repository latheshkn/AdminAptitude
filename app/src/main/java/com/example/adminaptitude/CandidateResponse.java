package com.example.adminaptitude;

public class CandidateResponse {

    String Email;
    String Mobile;
    String Name;
    String Password;
    String User_Id;

    public String getUser_id() {
        return User_Id;
    }

    public void setUser_id(String user_id) {
        User_Id = user_id;
    }

    public CandidateResponse(String email, String mobile, String name, String password, String user_id) {
        Email = email;
        Mobile = mobile;
        Name = name;
        Password = password;

    }
    public CandidateResponse() {

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


}
