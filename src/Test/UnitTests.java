package Test;

import org.junit.BeforeClass;
import org.junit.Test;
import sample.Main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class UnitTests {
    @BeforeClass
    public static void runBeforeTest(){
        System.out.println("Execute ->");
        Main.execute("src/Test/UmlTest/AudUml.graphml","src/Test/JavaTest");
    }

    @Test
    public void testClass() {
        System.out.println("testClass ->");
        String java = getJava("src/Test/JavaTest/Package_1/ChildClass2.java");
        assertTrue(Pattern.matches("(\\s*package\\s*(Test\\.JavaTest\\.Package_1));(\\s+|\\n*)((\\s*public)(\\s*class)(\\s*ChildClass2)((\\s*extends)(\\s*ParentClass))*)(\\s+|\\n+)*(\\{)(\\n*|.*)*(?=\\})(\\s+|\\n+)*}", java));
    }

    @Test
    public void testMethod() {
        System.out.println("testMethod ->");
        String java = getJava("src/Test/JavaTest/Package_1/ChildClass2.java");
        assertTrue(Pattern.compile("(public)(\\s)+(int\\[\\])(\\s)+(Method_1)+(\\s)*(\\(()*\\))(\\s)*(\\{(\\s*|\\n)\\})").matcher(java).find());
    }

    @Test
    public void testVar() {
        System.out.println("testVar ->");
        String java = getJava("src/Test/JavaTest/Package_1/ChildClass2.java");
        assertTrue(Pattern.compile("(int)(\\s)+var(\\s)*;").matcher(java).find());
    }

    @Test
    public void testEnum(){
        System.out.println("testEnum ->");
        String java = getJava("src/Test/JavaTest/Package_1/Enum.java");
        assertTrue(Pattern.matches("(package\\s+)(Test\\.JavaTest\\.Package_1)( *;)\\s*public\\s*enum\\s*Enum\\s*\\{\\s*PROP_1\\s*,\\s*PROP_2\\s*,\\s*PROP_3\\s*,\\s*PROP_4\\s*\\}\\s*", java));
    }

    @Test
    public void testInterface(){
        System.out.println("testEnum ->");
        String java = getJava("src/Test/JavaTest/Package_1/Interface_1.java");
        assertTrue(Pattern.matches("(\\s*package)(\\s*Test\\.JavaTest\\.Package_1)(\\s*;)(\\s*public)(\\s*interface)(\\s*Interface_1)(\\s*\\{(\\s*int\\s*constVar\\s*=\\s*2\\s*;)(\\s*public\\s*Double\\s*Method\\(\\)\\s*;)(\\s*\\}))", java));
    }

    public String getJava(String file){
        try {
            StringBuilder content = new StringBuilder();
            Files.lines(Paths.get(file), StandardCharsets.UTF_8).forEach(x -> content.append(x));
            System.out.println(" entered");
            return content.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
