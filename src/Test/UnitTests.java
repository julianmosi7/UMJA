package Test;

import org.junit.BeforeClass;
import org.junit.Test;
import sample.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.Assert.*;

public class UnitTests {
    @BeforeClass
    public static void runBeforeTest(){
        System.out.println("Execute ->");
        Main.execute("..\\src\\Test\\UmlTest\\AudUml.graphml","..\\src\\Test\\JavaTest\\Output.java ");
    }

    @Test
    public void testClass() {
        System.out.println("testClass ->");
        String java = getJava("..\\Package_1\\ChildClass2");
        assertTrue(java.matches("/(\\s*package\\s*(Test\\.JavaTest\\.Package_1));(\\s+|\\n*)((\\s*public)(\\s*class)(\\s*ChildClass2)((\\s*extends)(\\s*PlayerClass))*)(\\s+|\\n+)*(\\{)(\\n*|.*)*(?=\\})(\\s+|\\n+)*\\}/"));
    }

    @Test
    public void testMethod() {
        System.out.println("testMethod ->");
        String java = getJava("..\\Package_1\\ChildClass2");
        assertTrue(java.matches("/(public)(\\s)+(int\\[\\])(\\s)+(Method_1)+(\\s)*(\\(()*\\))(\\s)*(\\{(\\s*|\\n)\\})/"));
    }

    @Test
    public void testVar() {
        System.out.println("testVar ->");
        String java = getJava("..\\Package_1\\ChildClass2");
        assertTrue(java.matches("/(int)(\\s)+var(\\s)*;/"));
    }

    @Test
    public void testEnum(){
        System.out.println("testEnum ->");
        String java = getJava("..\\Package_1\\Enum");
        assertTrue(java.matches("/(package\\s+)(Test\\.JavaTest\\.Package_1)( *;)\\s*public\\s*enum\\s*Enum\\s*{\\s*PROP_1\\s*,\\s*PROP_2\\s*,\\s*PROP_3\\s*,\\s*PROP_4\\s*}\\s*/"));
    }

    @Test
    public void testInterface(){
        System.out.println("testEnum ->");
        String java = getJava("..\\Package_1\\Interface_1");
        assertTrue(java.matches("/(\\s*package)(\\s*Test\\.JavaTest\\.Package_1)(\\s*;)(\\s*public)(\\s*interface)(\\s*Interface_1)(\\s*{(\\s*int\\s*constVar\\s*=\\s*2\\s*;)(\\s*public\\s*Double\\s*Method\\(\\)\\s*;)(\\s*}))/"));
    }

    public String getJava(String file){
        try {
            String content = new Scanner(new File(file)).useDelimiter("\\Z").next();
            return content;
        } catch (FileNotFoundException e) {
            return "";
        }
    }
}
