package Classes;

public class Variable extends Attributes {
    String name;
    String value;

    public Variable(Boolean isStatic, Boolean isPublic, String returntype, String name, String value) {
        super(isStatic, isPublic, returntype);
        this.name = name;
        this.value = value;
    }

}
