package Classes;

public class Variable extends Attributes {
    String name;
    String value;

    public Variable(Boolean isStatic, Boolean isPublic, String returntype, String name, String value) {
        super(isStatic, isPublic, returntype);
        this.name = name;
        this.value = value;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        if(isPublic){
            str.append("public");
        }else{
            str.append("private");
        }
        if(isStatic){
            str.append(" static");
        }
        if (value.isEmpty()) {
            return str.toString() + returntype + " " + name + ";";
        } else
            return str.toString() + returntype + " " + name + " = " + value + ";";
    }

    public String toStringEnum(){
        return name;
    }

}

