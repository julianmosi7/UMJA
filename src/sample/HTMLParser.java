package sample;

public class HTMLParser {

    public static String[] htmlToString(String stringtext) {

      stringtext=stringtext.replace("\n","");
      stringtext=stringtext.replace("<html>","");
      stringtext=stringtext.replace("</html>","");
      stringtext=stringtext.replace("<br>","");
      stringtext=stringtext.replace("<u>","");
      String[] ret=new String[(stringtext.split("\\+").length-1)+(stringtext.split("-").length-1)];
      if(ret.length>0) {
          int index = 0;
          String toAdd = "";
          for (int i = 0; i < stringtext.length(); i++) {
              char current = stringtext.charAt(i);
              if ((current == '+' || current == '-') && i != 0) {
                  ret[index] = toAdd;
                  toAdd = "";
                  index++;
              }
              toAdd += current;
          }
          ret[index] = toAdd;

          for (int i = 0; i < ret.length; i++) {
              String line = ret[i];
              String lineWithoutU = line.replace("</u>", "");
              if (!lineWithoutU.equals(line)) {
                  ret[i] = "static " + lineWithoutU;
              }
          }
          return ret;
      }else if(stringtext.contains(",")){
          ret=stringtext.split(", ");
      }
      return ret;
    }
}