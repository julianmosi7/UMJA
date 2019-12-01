package sample;

public class HTMLParser {
    private static boolean pub = false;
    private static boolean isMethod;
    private static boolean isStatic = false;
    private static String finishedString = "";
    private static String clipString = "";
    private static String codeGt="&gt",codeLt="&lt";
    private static String[] arrayStrings;


    public static String[] htmlToString(String stringtext) {
        isMethod = false;
        int arrayPos = -1;
        int arrayLength = 0;
        String[] strings = stringtext.split("\\n");
        for (String string : strings) {
            String[] parts = string.split(";");
            for (int j = 0; j < parts.length; j++) {
                if (parts[j].contains("+") || parts[j].contains("-") || parts[j].contains("#")) arrayLength++;

            }
        }

        arrayStrings = new String[arrayLength];

        for (String string : strings) {

            if(!string.contains(":") && !string.isEmpty() && string.contains("(")){
                arrayPos++;
                arrayStrings[arrayPos] = string;
            } else if(string.contains(":") && string.contains("(")){
                arrayPos++;
                arrayStrings[arrayPos] = string;
            }
            else{

                String[] parts = string.split(";");

                for (int i = 0; i < parts.length; i++) {

                    if (parts[i].equals("u" + codeGt)) {
                        isStatic = true;
                        finishedString += "static ";
                    } else {
                        isStatic = false;
                    }

                    if (parts[i].contains("- " + codeLt)) {
                        finishedString += "- ";
                        pub = false;
                    }
                    if (parts[i].contains("+ " + codeLt)) {
                        finishedString += "+ ";
                        pub = true;
                    }
                    if (parts[i].contains("# " + codeLt)) {
                        finishedString += "# ";
                        pub = true;
                    }

                    if (parts[i].contains("-")) {
                        arrayPos++;
                        pub = false;
                    }
                    if (parts[i].contains("+")) {
                        arrayPos++;
                        pub = true;
                    }
                    if (parts[i].contains("#")) {
                        arrayPos++;
                        pub = true;
                    }


                    if (parts[i].contains("(")) {
                        String clip = parts[i].substring(parts[i].indexOf("(") + 1, parts[i].indexOf(")"));
                        if (clip.length() > 3) {
                            if (clip.contains(",")) {
                                String[] clipValues = clip.split(",");
                                for (int j = 0; j < clipValues.length; j++) {
                                    String[] clipParameter = clipValues[j].split(":");
                                    for (int k = 0; k < clipParameter.length; k++) {
                                        if (k == clipParameter.length - 1) {
                                            if (j != clipValues.length - 1) {
                                                clipString += clipParameter[k] + ", ";
                                            } else {
                                                clipString += clipParameter[k];
                                            }
                                        } else {
                                            clipString += clipParameter[k] + " : ";
                                        }
                                    }
                                }
                            } else {
                                clipString += clip;
                            }
                        }
                    }

                    if (parts[i].contains("(")) {
                        parts[i] = parts[i].replace(parts[i].substring(parts[i].indexOf("("), parts[i].indexOf(")") + 1), "");
                        isMethod = true;
                    }

                    if (parts[i].contains(" : ")) {
                        String[] infoForClassOrVariable = parts[i].split(" : ");
                        for (int j = 0; j < infoForClassOrVariable.length; j++) {
                            if (infoForClassOrVariable[j].contains(codeLt)) {
                                infoForClassOrVariable[j] = infoForClassOrVariable[j].replace(codeLt, "");
                                infoForClassOrVariable[j] = infoForClassOrVariable[j].replace(codeGt, "");
                            }
                        }
                        if (isMethod) {
                            finishedString += infoForClassOrVariable[0] + "(" + clipString + ") : " + infoForClassOrVariable[1] + " ";
                        } else {
                            finishedString += infoForClassOrVariable[0] + clipString + " : " + infoForClassOrVariable[1] + " ";
                        }
                        clipString = "";
                        if (finishedString.contains("static")) {
                            String[] staticFirst = finishedString.split(" ");
                            finishedString = staticFirst[1] + " " + staticFirst[0];
                            for (int k = 2; k < staticFirst.length; k++) {
                                finishedString += " " + staticFirst[k];
                            }
                            finishedString += " ";
                        }
                        finishedString = finishedString.substring(0, finishedString.length() - 1);
                        arrayStrings[arrayPos] = finishedString;
                        finishedString = "";
                    }
                }
            }
        }
        return arrayStrings;
    }
}