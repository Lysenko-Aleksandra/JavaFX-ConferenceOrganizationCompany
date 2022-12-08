package com.example.javafxconferenceorganizationcompany.controllers.MainAdmin;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.controllers.Conference.AddConferenceController;
import com.example.javafxconferenceorganizationcompany.controllers.LocationController;
import com.example.javafxconferenceorganizationcompany.controllers.Staff.AddStaffController;
import com.example.javafxconferenceorganizationcompany.controllers.Staff.StaffController;
import com.example.javafxconferenceorganizationcompany.models.Location;
import com.example.javafxconferenceorganizationcompany.models.User;
import com.example.javafxconferenceorganizationcompany.repository.LocationRepository;
import com.example.javafxconferenceorganizationcompany.repository.UserRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GetFreeLocations {
    @FXML
    private Label FIO;

    @FXML
    private Label birthDay;
    @FXML
    private Label email;
    @FXML
    private Label login;
    @FXML
    private Label phoneNumber;
    @FXML
    private Button getStaffInfo;
    @FXML
    private Button getResourcesInfo;
    @FXML
    private Button newConference;
    @FXML
    private Button hireEmployee;
    @FXML
    private Label invalidDate;
    @FXML
    private TableView freeLocationsTable;
    @FXML
    private TableColumn locationNameColumn;
    @FXML
    private TableColumn locationAddressColumn;
    @FXML
    private TableColumn costColumn;
    @FXML
    private TextField start;
    @FXML
    private TextField finish;
    @FXML
    private RadioButton personalAssistants;
    @FXML
    private RadioButton videographers;
    private Connection connection;
    private UserRepository userRepository;
    private Stage stage;
    private Integer id;

    private Integer roleId;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setUserRepository(UserRepository userRep) {
        userRepository=userRep;
    }


    public void setStage(Stage st) {
        stage = st;
    }

    public void setId(Integer i) {
        id = i;
    }

    public void setRoleId(Integer role){
        roleId=role;
    }

    public void setStartAndFinish(String st, String fin){
        start.setText(st);
        finish.setText(fin);
    }

    public void setInfo(){
        User user=UserRepository.getUserPersonalInfo(id);

        login.setText(user.getUserLogin());
        FIO.setText(user.getFIO());
        phoneNumber.setText(user.getPhoneNumber());
        birthDay.setText(user.getBirthDate());
        email.setText(user.getEmail());

        LocationRepository locationRepository=new LocationRepository(connection);
        ObservableList<Location> locations = locationRepository.getAllLocations();

        locationNameColumn.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        locationAddressColumn.setCellValueFactory(new PropertyValueFactory<>("locationAddress"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("costPerHour"));

        freeLocationsTable.setItems(locations);

        EventHandler<ActionEvent> eventAss = e -> {
            ///парсим время и если всё ок то идем на другую страничку
            try {
                System.out.println(start.getText());
                System.out.println(finish.getText());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime startD = LocalDateTime.parse(start.getText(), formatter);
                LocalDateTime finishD = LocalDateTime.parse(finish.getText(), formatter);


                FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main-get-personal-assistants.fxml"));
                Scene newScene = null;
                try {
                    newScene = new Scene(fxmlLoader.load(), 700, 700);
                } catch (IOException r) {
                    throw new RuntimeException(r);
                }
                GetFreePersonalAssistants controller = fxmlLoader.getController();
                controller.setConnection(connection);
                controller.setConnection(connection);
                controller.setUserRepository(userRepository);
                controller.setStage(stage);
                System.out.println(roleId);
                controller.setRoleId(roleId);
                controller.setId(id);
                controller.setStartAndFinish(start.getText(), finish.getText());
                controller.setInfo();
                stage.setScene(newScene);
            } catch (DateTimeParseException n) {
                invalidDate.setText("Формат даты и времени yyyy-MM-dd HH:mm:ss.S");
            }
        };

        personalAssistants.setOnAction(eventAss);

        EventHandler<ActionEvent> eventAVid = e -> {
            ///парсим время и если всё ок то идем на другую страничку
            try {
                System.out.println(start.getText());
                System.out.println(finish.getText());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime startD = LocalDateTime.parse(start.getText(), formatter);
                LocalDateTime finishD = LocalDateTime.parse(finish.getText(), formatter);


                FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main-get-videographers.fxml"));
                Scene newScene = null;
                try {
                    newScene = new Scene(fxmlLoader.load(), 700, 700);
                } catch (IOException r) {
                    throw new RuntimeException(r);
                }
                GetFreeVideographers controller = fxmlLoader.getController();
                controller.setConnection(connection);
                controller.setConnection(connection);
                controller.setUserRepository(userRepository);
                controller.setStage(stage);
                System.out.println(roleId);
                controller.setRoleId(roleId);
                controller.setId(id);
                controller.setStartAndFinish(start.getText(), finish.getText());
                controller.setInfo();
                stage.setScene(newScene);
            } catch (DateTimeParseException n) {
                invalidDate.setText("Формат даты и времени yyyy-MM-dd HH:mm:ss.S");
            }
        };

        videographers.setOnAction(eventAVid);


    }

    public void onClickChangePassword(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main-change-password.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangePasswordController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }

    public void onClickChangeLogin(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main-change-login.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangeLoginController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }

    public void onClickChangeEmail(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main-change-email.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangeEmailController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }
    public void onClickChangePhoneNumber(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main-change-phone.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangePhoneNumberController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);
    }

    public void toStaff(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("staff.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StaffController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        System.out.println(roleId);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);
    }

    public void addStaff(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("add-employee.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddStaffController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        System.out.println(roleId);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);
    }

    public void goToBase(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("base-locations.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LocationController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        System.out.println(roleId);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }

    public void addConference(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("add-conference.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddConferenceController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        System.out.println(roleId);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);
    }

}
