package sample;

import Classes.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Compliler {
    public static void compile(List<File> files, String path){

        for (File f:files) {
            java.io.File outFile = new java.io.File(path + "\\" + f.name + ".java");
            try {
                FileWriter fr=new FileWriter(outFile);
                fr.write(outFile.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
