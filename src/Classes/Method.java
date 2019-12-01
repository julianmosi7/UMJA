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
        if (isPublic) {
            str.append(("public"));
        }else{
            str.append(("private"));
        }
        if (isStatic) {
            str.append(" static");
        }
        return str.toString()+ " static " + returntype + " " + name + "(" + parameters + "){ }";
    }

}
