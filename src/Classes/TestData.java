package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {
    ArrayList<File> files = new ArrayList<>();
    public static List<File> testData(){
        File aInterface = new File(FileType.Interface, "anInterface", null, Arrays.asList(), Arrays.asList(
                new Variable(true,true,"double", "someConst", "3.14")
        ), Arrays.asList(
                new Method(true, true, "int", new Variable[]{new Variable(true, null, "int", "someParam", null)}, "method1")
        ), "testPack");

        File aClass1 = new File(FileType.Class, "aClass1", null, Arrays.asList(), Arrays.asList(
                new Variable(false, false, "String", "Var1", "hello world"),
                new Variable(false, false, "int", "twoVar", "2")
        ), Arrays.asList(
                new Method(false, false, "boolean", null, "Class1Method")
        ), "testPack");

        File aClass2 = new File(FileType.Class, "aClass2", aClass1, Arrays.asList(aInterface), Arrays.asList(
                new Variable(false, true, "String", "stringVar", "...g"),
                new Variable(false, false, "float", "floatVar", "5")
        ), Arrays.asList(
                new Method(false, true, "String", new Variable[]{
                        new Variable(true, null, "String", "stringPar",null),
                        new Variable(true, null, "int", "intPar",null)
                }, "Class1Method")
        ), "testPack2");

        File aEnum = new File(FileType.Enum, "anEnum", null, Arrays.asList(), Arrays.asList(
                new Variable(null, null, "Enum", "FIRST", null),
                new Variable(null, null, "Enum", "SECOND", null)
        ), Arrays.asList(), "testPack2");

        return Arrays.asList(aInterface, aClass1, aClass2, aEnum);
    }

}
