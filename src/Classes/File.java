package Classes;

import java.util.ArrayList;
import java.util.List;

public class File {
    public FileType fileType;
    public String name;
    public File parent;
    public String pack;
    public List<File> implement;
    public List<Variable> attributes;
    public List<Method> methodes;

    public File(FileType fileType, String name, File parent, List<File> implement, List<Variable> attributes, List<Method> methodes, String pack) {
        this.fileType = fileType;
        this.name = name;
        this.parent = parent;
        this.implement = implement;
        this.attributes = attributes;
        this.methodes = methodes;
        this.pack=pack;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        if(fileType.equals(FileType.Class)){
            str.append("public class" + name);
            if(parent != null){
                str.append(" extends " + parent.name);
            }
            if(implement != null){
                str.append("implements ");
                for (File files:
                     implement) {
                    str.append(files.name + " ");
                }
            }
            str.append("{");
            str.append("\n");
            for (Variable variable:
                 attributes) {
                str.append(variable.toString());
                str.append("\n");
            }
            for (Method method:
                 methodes) {
                str.append(method.toString());
                str.append("\n");
            }
            str.append("}");


        }else if(fileType.equals((FileType.Interface))){
            str.append("interface " + name + "{");
            str.append("\n");
            for (Variable variable:
                 attributes) {
                str.append(variable.toString());
                str.append("\n");
            }
            for (Method method:
                 methodes) {
                str.append(method.toStringInterface());
            }
            str.append("}");

        }else if(fileType.equals((FileType.Enum))){
            str.append("enum " + name + "{");
            str.append("\n");
            for (Variable variable:
                 attributes) {
                str.append(variable.toStringEnum() + ",");
            }
            str.append(";");
        }else {
            return "Fehler";
        }
        return "";
    }
}


