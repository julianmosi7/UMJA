package sample;

public class HTMLParser {

    //INFORMATIONEN
    //WENN IN &lt u STEHT DANN RAUSHOLEN
    //BEI html IGNORIEREN

    //- BEDEUTET PRIVATE
    //+ BEDEUTET PUBLIC
    //# BEDEUTET PROTECTED
    //u BEDEUTET static
    //: BEDEUTET TYP
    //NAME IN UPPERCASE BEDEUTET final

    // static - SCANNER : Scanner
    private String text = "&lt;html&gt;\n" +
            "- &lt;u&gt;SCANNER : Scanner&lt;/u&gt;&lt;br&gt;\n" +
            "- &lt;u&gt;STORE : Store\n" +
            "&lt;/u&gt;&lt;br&gt;\n" +
            "- &lt;u&gt;user : User\n" +
            "&lt;/u&gt;\n" +
            "&lt;/html&gt;";
    String text2 = "- name : String\n" +
            "- price : String\n" +
            "- published : String";

    String text3 = "+ getGames() : Game[]\n" +
            "+ addGameToUserWishlist(user2 : User2, gameName2 : String2) : void\n" +
            "+ addGameToUserLibrary(user : User, gameName : String) : void";

    private boolean pub;
    private boolean isMethod;
    private boolean isStatic = false;
    private String finishedString = "";
    private String clipString = "";

    public String htmlToString(String stringtext) {
        String[] strings = stringtext.split("\\n");
        for (String string : strings) {
            String[] parts = string.split(";");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("u&gt")) {
                    isStatic = true;
                    finishedString += "static ";
                } else {
                    isStatic = false;
                }

                if (parts[i].equals("- &lt")) {
                    finishedString += " - ";
                    pub = false;
                } if(parts[i].equals("+ &lt")) {
                    finishedString += " + ";
                    pub = true;
                } if(parts[i].equals("# &lt")) {
                    finishedString += " # ";
                    pub = true;
                }

                if (parts[i].contains("(")) {
                    String clip = parts[i].substring(parts[i].indexOf("(")+1, parts[i].indexOf(")"));
                    if (clip.length() > 3) {
                        if (clip.contains(",")) {
                            String[] clipValues = clip.split(",");
                            for(int j = 0; j < clipValues.length; j++){
                                    String[] clipParameter = clipValues[j].split(":");
                                    for(int k = 0; k < clipParameter.length; k++){
                                        if(k == clipParameter.length-1){
                                            if(j != clipValues.length-1){
                                                clipString += clipParameter[k] + ", ";
                                            } else {
                                                clipString += clipParameter[k];
                                            }
                                        } else {
                                            clipString += clipParameter[k] + " : ";
                                        }
                                    }


                            }
               /*             for (String clipinfo : clipValues) {
                                if (clipinfo.contains(":")) {
                                    String[] clipParameter = clipinfo.split(":");
                                    clipString += clipParameter[0] + " : " + clipParameter[1];
                                }
                            } */
                        }
                    }
                }

                if(parts[i].contains("(")){
                    parts[i] = parts[i].replace(parts[i].substring(parts[i].indexOf("("), parts[i].indexOf(")")+1), "");
                    isMethod = true;
                }
                        if (parts[i].contains(" : ")) {
                            String[] infoForClassOrVariable = parts[i].split(" : ");
                    /*for (String inf : infoForClassOrVariable) {
                        if (inf.contains("&lt")) {
                            inf.replace("&lt", "");
                            inf.replace("&gt", "");
                        }
                    } */
                            for (int j = 0; j < infoForClassOrVariable.length; j++) {
                                if (infoForClassOrVariable[j].contains("&lt")) {
                                    infoForClassOrVariable[j] = infoForClassOrVariable[j].replace("&lt", "");
                                    infoForClassOrVariable[j] = infoForClassOrVariable[j].replace("&gt", "");
                                }
                            }
                            if(isMethod){
                                finishedString += infoForClassOrVariable[0] + "(" + clipString + ") : " + infoForClassOrVariable[1] + " ";
                            }
                            else{
                                finishedString += infoForClassOrVariable[0] + clipString + " : " + infoForClassOrVariable[1] + " ";
                            }
                            clipString = "";

                  /*          for (String inf : infoForClassOrVariable) {
                                if (inf.contains("(")) {
                                    clip = inf.substring(inf.indexOf("("), inf.indexOf(")") + 1); //Funktioniert wegen dem split(":"); noch nicht. Der Fehler besteht weil er keine ")" findet.
                                    if (clip.length() > 3) {
                                        if (clip.contains(":")) {
                                            String[] clipParameter = clip.split(" : ");
                                            //for (int j = 0; j < clipParameter.length; j++) { //String parameter : clipParameter){
                                            finishedString += clipParameter[0] + " : " + clipParameter[1];
                                            //}
                                        }*/
                        }
                    }
                }
                finishedString += "\n";
        System.out.println(finishedString);
        return finishedString;
    }
}