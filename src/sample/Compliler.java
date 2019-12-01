package sample;

import Classes.File;

import java.util.ArrayList;

public class Compliler {
    public static void compile(ArrayList<File> files, String path){
        for (File f:files) {
            java.io.File outFile = new java.io.File(path + "\\" + f.name + ".java");
        }
    }
}
