package sample;


import javafx.event.ActionEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.FileChooser;

import java.beans.XMLDecoder;
import java.io.*;
import java.util.List;

public class Controller {

    public AnchorPane draganddrop;
    List<File> files;
    final FileChooser fileChooser = new FileChooser();
    private File selectedFile;


    public void onDragOver(DragEvent dragEvent) {
        draganddrop.setStyle("-fx-background-color: grey;");
        if(dragEvent.getDragboard().hasFiles()){
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    public void onDragDropped(DragEvent dragEvent) {
        files = dragEvent.getDragboard().getFiles();
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
                //System.out.println(line);
                String[] teile = line.split(";");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
