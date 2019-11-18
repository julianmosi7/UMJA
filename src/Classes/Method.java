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


}
