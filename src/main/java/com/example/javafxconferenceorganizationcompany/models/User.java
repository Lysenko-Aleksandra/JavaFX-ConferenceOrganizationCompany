package com.example.javafxconferenceorganizationcompany.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class User {
    private IntegerProperty userId;
    private StringProperty userLogin;
    private StringProperty FIO;
    private StringProperty phoneNumber;
    private StringProperty birthDate;
    private StringProperty email;
    private StringProperty roleName;

    private Button delete;

    public User()
    {
        userId=new SimpleIntegerProperty();
        userLogin=new SimpleStringProperty();
        FIO=new SimpleStringProperty();
        phoneNumber=new SimpleStringProperty();
        birthDate=new SimpleStringProperty();
        email=new SimpleStringProperty();
        roleName=new SimpleStringProperty();
        delete=new Button("Удалить");
        delete.setStyle("-fx-background-color: #FF7F50;-fx-text-fill:#f4f2f2");
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public void setUserId(int id){
        userId.set(id);
    }
    public void setUserLogin(String login){
        userLogin.set(login);
    }
    public void setFIO(String fio){
        FIO.set(fio);
    }
    public void setPhoneNumber(String phoneNumb){
        phoneNumber.set(phoneNumb);
    }
    public void setBirthDate(String birth){
        birthDate.set(birth);
    }
    public void setEmail(String em){
        email.set(em);
    }
    public void setRoleName(String role){
        roleName.set(role);
    }

    public int getUserId(){
        return userId.get();
    }
    public String getUserLogin(){
        return userLogin.get();
    }
    public String getFIO(){
        return FIO.get();
    }
    public String getPhoneNumber(){
        return phoneNumber.get();
    }
    public String getBirthDate(){
        return birthDate.get();
    }
    public String getEmail(){
        return email.get();
    }
    public String getRoleName(){
        return roleName.get();
    }
}

