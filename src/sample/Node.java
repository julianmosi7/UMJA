package sample;

import java.util.ArrayList;

public class Node {
    public ArrayList<Node> implemented;
    private ArrayList<String> variables;
    private String strVariables = "";
    private ArrayList<String> methods;
    private String strMethods = "";
    public String id;
    public String fileType="";
    public String fileName="";
    public String pack="";
    public Node(){
        variables = new ArrayList<>();
        methods = new ArrayList<>();
        implemented = new ArrayList<>();
    }
    public Node prepare(){
        for (String toAdd:HTMLParser.htmlToString(strVariables)) {
            variables.add(toAdd);
        }
        for (String toAdd:HTMLParser.htmlToString(strMethods)) {
            methods.add(toAdd);
        }
        return this;

    }

    public void appendVariables(String append){
        if(!append.startsWith("\n ")){
            strVariables+=append;
        }

    }

    public void appendMetod(String append){
        if(!append.startsWith("\n ")){
            strMethods+=append;
        }

    }

    public ArrayList<String> getVariables(){
        return variables;
    }

    public ArrayList<String> getMethods(){
        return methods;
    }
    public boolean isEmpty(){
        if(strVariables.isEmpty()&&strMethods.isEmpty()){
            return true;
        }

        if(!strVariables.trim().equals("")){
            return false;
        }
        if(!strMethods.trim().equals("")){
            return false;
        }
        return true;
    }
    public Node clean(){
        for (int i=0; i < variables.size(); i++){
            if(variables.get(i).trim().equals("")){
                variables.remove(i);
            }
        }

        for (int i=0; i < methods.size(); i++){
            if(methods.get(i).trim().equals("")){
                methods.remove(i);
            }
        }
        return this;
    }
}
