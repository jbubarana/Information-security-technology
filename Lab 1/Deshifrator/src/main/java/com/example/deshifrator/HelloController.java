package com.example.deshifrator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HelloController {
    @FXML
    private TextField textField;
    @FXML
    private TextArea textArea;
    @FXML
    private Label label;

    @FXML
    protected void onHelloButtonClick() throws IOException {

        try {
            String F = textField.getText();
            File file = new File(F);

            Scanner scanner = new Scanner(new FileReader(file));
            String shifr = "";

            while (scanner.hasNextLine()) {
                shifr = scanner.nextLine();
            }

            StringBuilder word = new StringBuilder();

            for (int i = 0; i < shifr.length(); i++) {
                char l = shifr.charAt(i);
                l += -15;
                word.append(l);
            }
            textArea.setText(String.valueOf(word));
            textField.setStyle(" -fx-border-color: grey;");
            label.setText("Файл дешифровано");
        }catch (IOException e){
            textField.setStyle(" -fx-border-color: red;");
            label.setText("Не вірний формат файлу або його відсутність !");
        }
    }
}