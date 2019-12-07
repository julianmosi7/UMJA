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
            str.append("    public ");
        }else{
            str.append("    private ");
        }
        if(isStatic){
            str.append(" static ");
        }
        if (value == null) {
            return str.toString() + returntype + " " + name + ";";
        } else
            if(returntype.equals("String")){
                return str.toString() + returntype + " " + name + " = " + "\"" +value + "\"" + ";";
            }
            return str.toString() + returntype + " " + name + " = " + value + ";";
        }

    public String toStringEnum(){
        return name;
    }

    public String toParamerterString(){
        return returntype + " " + name;
    }

}

