
package sample;

import Classes.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PrepareCompile {
    public static ArrayList<File> prepareCompile(ArrayList<Node> nodes){
        FileType fileType = null;
        String fileName = "";
        List<Variable> attributes = new ArrayList<>();
        List<Method> methodes = new ArrayList<>();
        String pack = "";

        ArrayList<File> returnList=new ArrayList();

        for (Node node:nodes) {
            if(node.fileType.equals("enumeration")){
                fileType = FileType.Enum;
            }  else if(node.fileType.equals("interface")){
                fileType = FileType.Interface;
            } else {
                fileType = FileType.Class;
            }
            if(fileType!=FileType.Enum) {
                for (String var : node.getVariables()) {
                    Variable vari = parseVariable(var);
                    attributes.add(vari);

                }
                for (String method : node.getMethods()) {
                    Method metho = parseMethod(method);
                    methodes.add(metho);

                }
            } else {
                for(String enu : node.getVariables()){ //Action
                    Variable enumerat = parseEnum(enu);
                    attributes.add(enumerat);
                }
            }
            fileName = node.fileName;
            pack = node.pack;
            File f = new File(fileType, fileName, null, new ArrayList<File>(), attributes, methodes, pack);
            attributes=new ArrayList<>();
            methodes=new ArrayList<>();
            returnList.add(f);
        }
        for (Node n:nodes) {
            File current=getFileByName(getKeyByNode(n),returnList);
            for (Node implement:n.implemented) {
                File impFile=getFileByName(getKeyByNode(implement),returnList);
                if(impFile.fileType==FileType.Class){
                    current.parent=impFile;
                }else if(impFile.fileType==FileType.Interface){
                    current.implement.add(impFile);
                }
            }
        }

        return returnList;
    }

    private static Variable parseEnum(String enu) {

        String varName = enu;
        Variable variable;
        varName = varName.replace("\n", "");

        variable = new Variable(null, null, null, varName, null);
        return variable;
    }

    private static File getFileByName(String name, ArrayList<File> files){
        for (File file: files) {
            if(getKeyByFile(file).equals(name)) return file;
        }
        return null;
    }

    private static String getKeyByFile(File file){
        return file.pack + file.name;
    }
    private static String getKeyByNode(Node node){
        return node.pack + node.fileName;
    }

    private static Method parseMethod(String var) {
        String bracket = var.substring(var.indexOf("(")+1 , var.indexOf(")"));
        String[] parameters = bracket.split(", ");
        Variable[] paras;
        if(!parameters[0].equals("")) {
            paras = new Variable[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                paras[i] = parameterParser(parameters[i]);
            }
        } else{
            paras = null;
        }


        String[] parts = var.split(" ");
        boolean isStatic = var.contains("static");
        boolean isPublic = var.contains("+");
        String methodDataType = null;
        for (int i = 0; i < parts.length; i++) {
            if(var.replace(bracket,"").contains(":")){
                methodDataType = parts[parts.length - 1];
                methodDataType = methodDataType.replace("\n", "");
            }
        }

        String methodName = "";
        String methodNameAndPara = "";
        Method method;

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("+") || parts[i].equals("-")) {
                methodNameAndPara = parts[i + 1];
                methodName = methodNameAndPara.substring(0, methodNameAndPara.indexOf("("));
                break;
            }
        }

        method = new Method(isStatic, isPublic, methodDataType, paras, methodName);
        return method;
    }

    private static Variable parameterParser(String var) {
        String[] parts = var.split(" ");
        String paraDataType = parts[parts.length-1];
        String paraName = parts[0];
        Variable parameter;

        parameter = new Variable(false, false, paraDataType, paraName, null);
        return parameter;
    }

    private static Variable parseVariable(String var){
        String[] parts = var.split(" ");
        boolean isStatic = var.contains("static");
        boolean isPublic = var.contains("+");
        String varDataType = parts[parts.length-1];
        String varName = "";
        String varValue = null;
        Variable variable;

        for(int i = 0; i < parts.length; i++) {
            if (parts[i].equals("+") || parts[i].equals("-")) {
                varName = parts[i + 1];
                if(var.contains("=")) varValue = parts[i + 3];
                break;
            }
        }
        varDataType = varDataType.replace("\n", "");

        variable = new Variable(isStatic, isPublic, varDataType, varName, varValue);
        return variable;
    }
}

