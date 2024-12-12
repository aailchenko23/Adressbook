package com.example.demo1;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {
    @FXML
    private TextField txtPip;
    @FXML
    private TextField txtPhone;

    private Person person;
    private ObservableList<Person> persons; // Список осіб для оновлення

    // Метод для передачі вибраної особи в редагування
    public void setPerson(Person person) {
        this.person = person;
        if (person != null) {
            txtPip.setText(person.getPip());
            txtPhone.setText(person.getPhone());
        }
    }

    // Метод для передачі списку осіб
    public void setPersonsList(ObservableList<Person> persons) {
        this.persons = persons;
    }

    @FXML
    public void handleOkButton() {
        if (person != null) {
            // Зчитуємо значення з полів
            String newPip = txtPip.getText();
            String newPhone = txtPhone.getText();

            // Оновлюємо ПІП і телефон
            person.setPip(newPip);
            person.setPhone(newPhone);

            // Оновлюємо список, якщо це необхідно
//            if (persons != null) {
//                int index = persons.indexOf(person);
//                if (index >= 0) {
//                    persons.set(index, person); // Оновлюємо елемент списку
//                }
//            }

            // Закриваємо вікно редагування
            Stage stage = (Stage) txtPip.getScene().getWindow();
            stage.close();
        } else {
            showAlert("Помилка", "Не вдалося оновити дані.");
        }
    }

    @FXML
    public void handleCancelButton() {
        Stage stage = (Stage) txtPip.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

