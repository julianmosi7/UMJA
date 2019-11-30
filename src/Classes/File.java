package Classes;

import java.util.ArrayList;
import java.util.List;

public class File {
    public FileType fileType;
    public String name;
    public File parent;
    public String pack;
    public List<File> implement;
    public List<Variable> attributes;
    public List<Method> methodes;

    public File(FileType fileType, String name, File parent, List<File> implement, List<Variable> attributes, List<Method> methodes, String pack) {
        this.fileType = fileType;
        this.name = name;
        this.parent = parent;
        this.implement = implement;
        this.attributes = attributes;
        this.methodes = methodes;
        this.pack=pack;
    }}


