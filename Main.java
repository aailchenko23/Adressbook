package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        stage.setTitle("Адресна книга");
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/demo1/Style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        testData();
    }
    private void testData(){
        CollectionAdressBook adressBook = new CollectionAdressBook();
        adressBook.testData();
        adressBook.print();
    }


    public static void main(String[] args) {
        launch();
    }
}