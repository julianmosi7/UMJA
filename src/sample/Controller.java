package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;

import javax.swing.*;
import java.beans.XMLDecoder;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public AnchorPane draganddrop;
    public TextField txt_projectfolder;
    public Button btn_projectfolder;
    List<File> files;
    final FileChooser fileChooser = new FileChooser();
    private File selectedFile;
    String path = System.getProperty("user.home");
    String documents = path + "/Documents";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txt_projectfolder.setText(documents);
        //Image image = new Image(getClass().getResourceAsStream("arrow.png"));
        //btn_projectfolder.setGraphic(new ImageView(image));
    }

    public void onDragOver(DragEvent dragEvent) {
        draganddrop.setStyle("-fx-background-color: grey;");
        if(dragEvent.getDragboard().hasFiles()){
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    public void onDragDropped(DragEvent dragEvent) {
        files = dragEvent.getDragboard().getFiles();
        for (File file :
                files) {
            selectedFile = file;
        }
        open(selectedFile);
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("GRAPHML", "*.graphml"));
        selectedFile = fileChooser.showOpenDialog(draganddrop.getScene().getWindow());
        if(selectedFile != null){
            open(selectedFile);
        }
    }

    private void open(File file){
                try(BufferedReader br = new BufferedReader(new FileReader(file))){
                    while(br.ready()){
                        String line = br.readLine();
                        System.out.println(line);
                        String[] teile = line.split(";");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
        }
    }


    public void onActionProjecrtFolder(ActionEvent actionEvent) {
        //speichern unter -> auswählbar durch filechooser, falls standar-folder nicht erwünscht
    }
}
