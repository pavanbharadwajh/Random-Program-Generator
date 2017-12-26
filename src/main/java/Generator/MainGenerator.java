package Generator;

import Data.Data;
import FileWriter.FileWriterClass;
import com.mifmif.common.regex.Generex;

import static FileWriter.FileWriterClass.filewriter;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/*
Generation of the Class as per the grammar specification provided in the configuration file
Class -> "class_modifier" class "identifier" {"field_declaration" "method_declaration"} or
         "class_modifier" class "identifier"{ } or "field_declaration" "method_declaration" or
         "class_modifier" class "identifier" {}

*/


public class MainGenerator {
    Node<String> t_rootNode;
    Node<String> t_currentNode;
    Vector<Node<String >> leafNodes = new Vector<Node<String>>();

    public MainGenerator() {
    }
    public MainGenerator(String p_root) {
        t_rootNode = new Node<String>(p_root);
        t_currentNode=t_rootNode;
    }
    boolean isRegex(String p_input){
        if(p_input.contains("regex"))
            return true;
        else
            return false;
    }

    /*for each of the Interface generated the randomly child nodes are created.
      The Child Nodes can be randomly picked.
      It can either be an identifier, an another Interface, abstract method
    */

    public void addChildNodes(Node<String>p_Node,int p_recDepth){
        String key = p_Node.getData();
        if(key.equals("class_name")){
         //   RandomGenerator r=new RandomGenerator();
           // int ran=r.random(0,(Data.f_classNames.size()-1));
            String str=Data.f_classNames.elementAt(0);
            p_Node.addChild(str);
            return;
        }
        int production_rule;
        String production=null;
        RandomGenerator rand;
        Integer[] value;
        Boolean flag=false;
        if (Data.f_symbols.get(key) != null) {
            value = Data.f_symbols.get(key);
            rand = new RandomGenerator();
            while (!flag){
                flag=true;
                production_rule = rand.random(value[0], value[1]);
                production = Data.f_rhs.elementAt(production_rule);
                if (p_recDepth >= 3) {
                    if (production.contains(key)) {
                        flag = false;
                    }
                }
            }
            String nodes[] = production.split("\"");
            for (String str : nodes) {
                p_Node.addChild(str);
            }
        }
        else if(isRegex(key)){
            key=key.substring(5,key.length());
            Generex g = new Generex(key); //give the regex as parameter here
            String str = g.random();
            p_Node.addChild(str);
            if(((((p_Node.getParent()).getParent()).getData()).equals("class_declaration"))||((((p_Node.getParent()).getParent()).getData()).equals("class_declaration1"))){
                Data.f_classNames.add(str);
            }

        }
        else
            return;

    }
    public void fn1(Node<String>p_node,int p_recdepth)
    {
        for (Node<String> stringNode : p_node.getChildren()) {
            // System.out.println(stringNode.getData());
            if(stringNode.getData().equals("method_declaration"))
            {
                Method_Generator method_generator=new Method_Generator(stringNode);
                stringNode=method_generator.doGenerate();
                continue;

            }
            else if(stringNode.getData().equals("method_declaration1"))
            {
                Method_Generator method_generator=new Method_Generator(stringNode);
                stringNode=method_generator.doGenerate();
                continue;

            }
            addChildNodes(stringNode,p_recdepth);
            if(stringNode.getData().equals(p_node.getData()))
                fn1(stringNode,++p_recdepth);
            else
                fn1(stringNode,p_recdepth);
        }
    }

    public void getAllLeafNodes(Node<String>p_node) {

        if (p_node.getChildren().isEmpty()) {
            leafNodes.add(p_node);
        } else {
            for (Node<String> child : p_node.getChildren()) {
                getAllLeafNodes(child);
            }
        }
    }
    public void codeGenerator() throws IOException {

        String key = t_currentNode.getData();
        addChildNodes(t_currentNode,0);
        RandomGenerator randomGenerator=new RandomGenerator();
        for (Node<String>str:t_currentNode.getChildren()) {
            if(str.getData().equals("f_d")){
                int upBound=randomGenerator.random(Data.f_MinFields,Data.f_MaxFields);
                for(int i=0;i<upBound;i++){
                    str.addChild("field_declaration");
                }}
            else if(str.getData().equals("m_d")){
                int upBound=randomGenerator.random(Data.f_MinClass,Data.f_MaxClass);
                for(int i=0;i<upBound;i++){
                    str.addChild("method_declaration");
                }}
            else if(str.getData().equals("gen_f_d")){
                int upBound=randomGenerator.random(Data.f_MinFields,Data.f_MaxFields);
                for(int i=0;i<upBound;i++){
                    str.addChild("field_declaration1");
                }}
            else if(str.getData().equals("gen_m_d")){
                int upBound=randomGenerator.random(Data.f_MinClass,Data.f_MaxClass);
                for(int i=0;i<upBound;i++){
                    str.addChild("method_declaration1");
                }}
        }

        fn1(t_currentNode,0);
        getAllLeafNodes(t_rootNode);
        if(Data.f_ClassFlag == 0) {

        }
        for (Node<String>str:leafNodes) {
            filewriter.write(str.getData());
            //System.out.print(str.getData());
        }
        //filewriter.write("\n");
        //System.out.println();
    }


}

