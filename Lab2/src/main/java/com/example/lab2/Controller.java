package com.example.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Controller {
    //First tab
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;

    @FXML
    private TextArea textArea;

    @FXML
    protected void Button() {

        String text = textField1.getText();
        String key = textField2.getText();

        int error = 0;

        if (text.isEmpty()) {
            textField1.setText("");
            textField1.setPromptText("Please enter text");
            error++;
        }
        if (key.isEmpty()) {
            textField2.setText("");
            textField2.setPromptText("Please enter key");
            error++;
        }

        if (error == 0) {

            char leters [] = "abcdefghijklmnopqrstuvwxyz".toCharArray();

            String cipherText = "";

            String t = textField1.getText();
            String k = textField2.getText();

            t = t.toLowerCase();
            System.out.println(t);


            int tl = t.length();
            k += k.repeat(tl / k.length());

            System.out.println(k);

            for (int i = 0; i < t.length(); i++) {
                int r = 0;
                int x = 0;

                char c = t.charAt(i);
                char c1 = k.charAt(i);

                for (int j = 0; j < 26; j++) {
                    if (c == leters[j]){
                        r = j + 1;
                    }
                }

                for (int j = 0; j < 26; j++) {
                    if (c1 == leters[j]) {
                        x = j + 1;
                    }
                }

                int sum = r + x;
                sum = sum % 26;
                if (sum % 26 == 0){
                    sum = 26;
                }

                for (int j = 0; j < 26; j++) {
                    if (sum == j + 1){
                        cipherText += leters[j];
                    }
                }
            }
            textArea.setText(cipherText);
        }
    }

    //Second tab
    @FXML
    private TextField StextField1;
    @FXML
    private Label label;
    @FXML
    private TextArea StextArea;

    @FXML
    protected void SButton1(){
        FileChooser FC = new FileChooser();
        FC.setTitle("Виберіть файл");
        File file = FC.showOpenDialog(null);

        String path = file.getAbsolutePath();
        label.setText(path);
    }
    @FXML
    protected void SButton2() throws IOException {
        int error = 0;

        if (label.getText().isEmpty()){
            label.setText("Виберіть файл");
            error++;
        }
        if (StextField1.getText().isEmpty()){
            StextField1.setText("");
            StextField1.setPromptText("Введіть ключ");
            error++;
        }
        if (error == 0){
            String FileFolder = label.getText();

            try (FileReader fr = new FileReader(FileFolder)) {
                Scanner scan = new Scanner(fr);

                String text1 = "";
                while (scan.hasNextLine()) {

                    String text = "";
                    String cipherText = "";

                    text = scan.nextLine();

                    String key = StextField1.getText();
                    key += key.repeat(text.length() / key.length());
                    char leters[] = "abcdefghijklmnopqrstuvwxyz".toCharArray();

                    for (int i = 0; i < text.length(); i++) {
                        int r = 0;
                        int x = 0;

                        char c = text.charAt(i);
                        char c1 = key.charAt(i);

                        if (c == c1) {
                            cipherText += ' ';
                        }

                        for (int j = 0; j < 26; j++) {
                            if (c == leters[j]) {
                                r = j + 1;
                            }
                        }

                        for (int j = 0; j < 26; j++) {
                            if (c1 == leters[j]) {
                                x = j + 1;
                            }
                        }

                        int sum = r - x;
                        if (sum < 0) {
                            sum += 26;
                        }

                        for (int j = 0; j < 26; j++) {
                            if (sum % 26 == j + 1) {
                                cipherText += leters[j];
                            }
                        }
                    }
                    text1 += cipherText + "\n";
                }
                StextArea.setText(text1);


            }catch (FileNotFoundException e){
                label.setText("Файл не знайдено");
            }
        }
    }
}
