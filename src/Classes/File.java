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
        str.append("package " +pack+";\n");
        if(fileType.equals(FileType.Class)){
            str.append("public class " + name);
            if(parent != null){
                str.append(" extends " + parent.name);
            }
            if(implement.size()!=0){
                str.append(" implements ");
                for (int i=0;i<implement.size();i++) {
                    File files=implement.get(i);
                    str.append(files.name);
                    if(i<implement.size()-1){
                        str.append(", ");
                    }

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
            str.append("public interface " + name + "{");
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
            str.append("\n");
            str.append("}");

        }else if(fileType.equals((FileType.Enum))){
            str.append("public enum " + name + "{");
            str.append("\n");
            for(int i = 0;i < attributes.size();i++){
                str.append(attributes.get(i).toStringEnum());
                if(i != attributes.size()-1){
                    str.append(", ");
                }
            }
            str.append("\n");
            str.append("}");
        }else {
            return "Fehler";
        }
        return str.toString();
    }
}


