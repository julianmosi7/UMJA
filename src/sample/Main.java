package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.html.HTML;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        HTMLParser parser = new HTMLParser();
        parser.htmlToString("&lt;html&gt;\n" +
                "- &lt;u&gt;SCANNER : Scanner&lt;/u&gt;&lt;br&gt;\n" +
                "- &lt;u&gt;STORE : Store\n" +
                "&lt;/u&gt;&lt;br&gt;\n" +
                "- &lt;u&gt;user : User\n" +
                "&lt;/u&gt;\n" +
                "&lt;/html&gt;");
    }
}
