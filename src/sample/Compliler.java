package sample;

import Classes.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Compliler {
    public static void compile(List<File> files, String path){

        for (File f:files) {
            java.io.File directorys=new java.io.File(path + "\\" +f.pack.replace('.','\\'));
            if(!directorys.exists()){
                directorys.mkdirs();
            }
            java.io.File outFile = new java.io.File(path + "\\" +f.pack.replace('.','\\')+"\\"+ f.name + ".java");

            try {
                FileWriter fr=new FileWriter(outFile);
                fr.write(f.toString());
                fr.flush();
                fr.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
