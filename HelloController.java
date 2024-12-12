package com.example.demo1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private CollectionAdressBook addressBookImpl = new CollectionAdressBook();

    @FXML
    private Button btnAdd;
    @FXML
    public Button btnEdit;

    @FXML
    public Button btnDelete;
    @FXML
    private TableColumn<Person, String> columnpip;
    @FXML
    private TableColumn<Person, String> columnphone;
    @FXML
    private TableView<Person> tableAddressBook;
    @FXML
    private Button btnSeatch;  // Кнопка пошуку

    @FXML
    private TextField txtSearch;  // Текстове поле для пошуку


    @FXML
    public void showDialog(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/add.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 400, 200));
            stage.setTitle("Добавити");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnAdd.getScene().getWindow());

            // Отримуємо контролер AddController
            AddController addController = fxmlLoader.getController();

            // Передаємо HelloController в AddController
            addController.setHelloController(this);

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPersonToTable(Person person) {
        // Додаємо нову особу в таблицю
        ObservableList<Person> currentData = tableAddressBook.getItems();
        currentData.add(person);
        //addressBookImpl.add(new Person());


    }

    @FXML
    public void showEditDialog(ActionEvent event) {
        System.out.println("Edit clicked"); // Перевірка

        // Отримуємо вибрану особу з таблиці
        Person selectedPerson = tableAddressBook.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/edit.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                // Отримуємо контролер EditController
                EditController editController = fxmlLoader.getController();
                // Передаємо вибрану особу для редагування
                editController.setPerson(selectedPerson);


                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Вікно редагування");
                stage.setMinHeight(200);
                stage.setMinWidth(400);
                stage.setResizable(false);

                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(btnEdit.getScene().getWindow());
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Повідомляємо, що особа не вибрана
            showAlert("Помилка", "Будь ласка, виберіть особу для редагування.");
        }
    }
    // Метод для показу повідомлень
    public void showAlert(String title, String message) {
        // Створюємо вікно повідомлення
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);  // Заголовок вікна
        alert.setHeaderText(null);  // Без заголовка
        alert.setContentText(message);  // Повідомлення

        // Показуємо діалогове вікно
        alert.showAndWait();
    }


    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Попередження");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    protected void handleDeleteAction(ActionEvent event) {
        // Confirmation Alert
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Видалення");
        confirmAlert.setHeaderText("Підтвердження дії");
        confirmAlert.setContentText("Ви впевнені, що хочете видалити запис?");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                showInformationAlert(); // Інформаційне повідомлення після успішного видалення
            } else if (response == ButtonType.CANCEL) {
                showWarningAlert(); // Попередження, якщо дію скасовано
            }
        });
    }

    private void showInformationAlert() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Видалення");
        infoAlert.setHeaderText("Результат:");
        infoAlert.setContentText("Ви успішно видалили запис!");
        infoAlert.showAndWait();
    }

    private void showWarningAlert() {
        Alert warningAlert = new Alert(Alert.AlertType.WARNING);
        warningAlert.setTitle("Видалення");
        warningAlert.setHeaderText("Попередження:");
        warningAlert.setContentText("Видалення може призвести до зміни порядку осіб у Адресній книзі!");
        warningAlert.showAndWait();
    }

    @FXML
    private Label labelCount;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnpip.setCellValueFactory(new PropertyValueFactory<>("pip"));
        columnphone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        addressBookImpl.fillTestData();

        tableAddressBook.setItems(addressBookImpl.getPersonList());

        labelCount.setText("Кількість записів: " + addressBookImpl.getPersonList().size());

    }
    @FXML
    public void handleSearchAction(ActionEvent event) {
        String searchText = txtSearch.getText();  // Отримуємо текст пошуку з поля

        // Якщо текст не порожній, виконуємо пошук
        if (searchText != null && !searchText.isEmpty()) {
            ObservableList<Person> filteredList = addressBookImpl.searchPerson(searchText);  // Виконати пошук за введеним текстом
            tableAddressBook.setItems(filteredList);  // Оновлюємо таблицю з результатами пошуку
        } else {
            // Якщо текст порожній, показуємо всі записи
            tableAddressBook.setItems(addressBookImpl.getPersonList());
        }
    }

}














