package com.example.javafxconferenceorganizationcompany.controllers.PersonalAssistant;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.models.Conference;
import com.example.javafxconferenceorganizationcompany.models.User;
import com.example.javafxconferenceorganizationcompany.repository.ConferenceRepository;
import com.example.javafxconferenceorganizationcompany.repository.UserRepository;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class PersonalAssistantMainController implements Initializable {
    UserRepository userRepository;
    ConferenceRepository conferenceRepository;
    Stage stage;
    Integer id;

    @FXML
    private TableView<Conference> conferences;

    @FXML
    private TableColumn<User,String> startColumn;

    @FXML
    private TableColumn<User,String> finishColumn;

    @FXML
    private TableColumn<User,String> locationColumn;

    @FXML
    private TableColumn<User, String> companyColumn;

    @FXML
    private TableColumn<User, Button> moreColumn;

    @FXML
    private Label email;

    @FXML
    private Label birthDay;

    @FXML
    private Label login;

    @FXML
    private Label phoneNumber;

    @FXML
    private Label FIO;

    public void setUserRepository(UserRepository userRep) {
        userRepository=userRep;
    }

    public void setConferenceRepository (ConferenceRepository conferenceRep){
        conferenceRepository=conferenceRep;
    };

    public void setStage(Stage st) {
        stage = st;
    }

    public void setId(Integer i) {
        id = i;
    }

    public void setInfo(){
        User user=UserRepository.getUserPersonalInfo(id);

        login.setText(user.getUserLogin());
        FIO.setText(user.getFIO());
        phoneNumber.setText(user.getPhoneNumber());
        birthDay.setText(user.getBirthDate());
        email.setText(user.getEmail());

        ObservableList<Conference> confs= conferenceRepository.getPersonalAssistantConferencesByID(id);

        // устанавливаем тип и значение которое должно хранится в колонке

        startColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
//        startColumn.setCellValueFactory(cell -> cell.setWarpText(true));

        finishColumn.setCellValueFactory(new PropertyValueFactory<>("finishTime"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("conferenceLocationAddress"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        moreColumn.setCellValueFactory(new PropertyValueFactory<>("more"));

        // заполняем таблицу данными
        conferences.setItems(confs);



    }
    public void onClickChangePassword(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-change-password.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangePasswordController controller = fxmlLoader.getController();
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }

    public void onClickChangeLogin(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-change-login.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangeLoginController controller = fxmlLoader.getController();
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }

    public void onClickChangeEmail(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-change-email.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangeEmailController controller = fxmlLoader.getController();
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }
    public void onClickChangePhoneNumber(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-change-phone.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangePhoneNumberController controller = fxmlLoader.getController();
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

