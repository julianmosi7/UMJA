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
    private Node parent,child;
    private String tag;
    public ParserHandler(){
        nodes = new Stack<>();
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("node")){
            Node n = new Node();
            n.id = attributes.getValue(0);
            nodes.push(n);
        }else if(qName.equals("edge")){
            parent = getNodeById(attributes.getValue(1));
            child = getNodeById(attributes.getValue(2));
        }else if(qName.equals("y:Arrows")){
            if(attributes.getValue(1).equals("white_delta")){
                child.parent = parent;
            }
        }else if(qName.equals("y:UML")){
            nodes.peek().fileType = attributes.getValue(3).equals("")? "class":attributes.getValue(3);
        }
        //TODO Add implements
        tag = qName;

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(tag.equals("y:AttributeLabel")){
            nodes.peek().appendVariables(new String(ch, start, length));
        }else if(tag.equals("y:MethodLabel")){
            String s = new String(ch, start, length);
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
        private Node getNodeById(String id){
            for (Node n:nodes) {
                if(n.id.equals(id)){
                    return n;
                }
            }
            return null;
        }

    }
