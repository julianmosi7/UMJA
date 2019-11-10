package sample;

import java.util.ArrayList;

public class Node {
    Node parent;
    private ArrayList<String> variables;
    private String strVariables = "";
    private ArrayList<String> methods;
    private String strMethods = "";
    public String id;
    public String fileType="";
    public Node(){
        variables = new ArrayList<>();
        methods = new ArrayList<>();
    }
    public Node prepare(){
        //TODO Replace with HTML-Parser
        variables.add(strVariables);
        variables.add(strMethods);
        return this;

    }

    public void appendVariables(String append){
        strVariables+=append;
    }

    public void appendMetod(String append){
        strMethods+=append;
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
