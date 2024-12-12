package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {
    @FXML
    private TextField txtPip;
    @FXML
    private TextField txtPhone;
    @FXML
    private Button btnAdd;
    private HelloController helloController;

    // Метод для отримання HelloController
    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    @FXML
    public void handleAddButton(ActionEvent event) {
        String pip = txtPip.getText();
        String phone = txtPhone.getText();

        // Перевірка на порожні поля
        if (pip.isEmpty() || phone.isEmpty()) {
            showAlert("Помилка", "Будь ласка, заповніть всі поля.");
        } else {
            // Створюємо нову особу
            Person newPerson = new Person(pip, phone);

            // Додаємо нову особу в таблицю
            helloController.addPersonToTable(newPerson);

            // Закриваємо вікно після додавання
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
        }
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


