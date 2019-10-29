package sample;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ParserHandler extends DefaultHandler {

    private Stack<Node> nodes;
    private String tag;
    public ParserHandler(){
        nodes = new Stack<>();
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("y:NodeLabel")){
            nodes.push(new Node());
        }
        tag = qName;

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(tag.equals("y:AttributeLabel")){
            nodes.peek().appendVariables(new String(ch, start, length));
        }else if(tag.equals("y:MethodLabel")){
            nodes.peek().appendMetod(new String(ch, start, length));
        }

        }

        public ArrayList<Node> getNodes(){
            ArrayList<Node> ret = new ArrayList<>();
            for (Node node:nodes) {
                if(!node.isEmpty()){ ret.add(node.prepare().clean());}
            }
            return ret;
        }

    }
