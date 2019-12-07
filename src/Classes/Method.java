package Classes;

import java.util.Arrays;

public class Method extends Attributes{
    Variable[] parameters;
    String name;

    public Method(Boolean isStatic, Boolean isPublic, String returntype, Variable[] parameters, String name) {
        super(isStatic, isPublic, returntype);
        this.parameters = parameters;
        this.name = name;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\n");
        if (isPublic) {
            str.append(("    public"));
        }else{
            str.append(("    private"));
        }
        if (isStatic) {
            str.append(" static");
        }
        str.append(" static " + returntype + " " + name + "(");
        if(parameters != null) {

            for(int i = 0;i < parameters.length;i++){
                str.append(parameters[i].toParamerterString());
                if(i != parameters.length-1){
                    str.append(", ");
                }
            }
            str.append(")");
        }else{
            str.append(")");
        }
        str.append("{ }");
        return str.toString();
    }

    public String toStringInterface(){
        StringBuilder str = new StringBuilder();
        if (isPublic) {
            str.append(("    public"));
        }else{
            str.append(("    private"));
        }
        if (isStatic) {
            str.append(" static");
        }
        str.append(" static " + returntype + " " + name + "(");
        if(parameters != null){
            for(int i = 0;i < parameters.length;i++){
                str.append(parameters[i].toParamerterString());
                if(i != parameters.length-1){
                    str.append(", ");
                }
            }
            str.append(")");
            str.append(";");
        }else{
            str.append(")");
            str.append(";");
        }
        return str.toString();
    }
}
