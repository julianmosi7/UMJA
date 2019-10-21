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
    private String text = "&lt;html&gt;\n" +
            "- &lt;u&gt;SCANNER : Scanner&lt;/u&gt;&lt;br&gt;\n" + // static - SCANNER : Scanner
            "- &lt;u&gt;STORE : Store\n" +
            "&lt;/u&gt;&lt;br&gt;\n" +
            "- &lt;u&gt;user : User\n" +
            "&lt;/u&gt;\n" +
            "&lt;/html&gt;";

    private boolean pub;
    private boolean isStatic = false;
    private String finishedString;

    public String htmlToString(String string2) {
        String[] strings = text.split("\\n");
        for (String string : strings) {
            String[] parts = string.split(";");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("u&gt")) {
                    isStatic = true;
                } else {
                    isStatic = false;
                }

                if (parts[i].equals("- &lt")) {
                    pub = false;
                } else {
                    pub = true;
                }

                if (parts[i].contains(" : ")) {
                    String[] infoForClassOrVariable = parts[i].split(" : ");
                    for (String inf : infoForClassOrVariable) {
                        if (inf.contains("&lt")) {
                            inf.replace("&lt", "");
                            inf.replace("&gt", "");
                        }
                        if (inf.contains("(")) {
                            String clip = inf.substring(inf.indexOf("("), inf.indexOf(")"));
                            if (clip.length() > 3) {
                                if (clip.contains(":")) {
                                    String[] clipParameter = clip.split(" : ");
                                    for (int j = 0; j < clipParameter.length; j++) { //String parameter : clipParameter){
                                        //string fertigstellen
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        return "";
    }
}
