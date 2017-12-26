package Generator;

import Data.Data;
import com.mifmif.common.regex.Generex;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static FileWriter.FileWriterClass.filewriter;

/* Generation of the Interface as per the grammar specification provided in the configuration file
   Interface -> interface "identifier" {"method_declaration"} or
                interface "identifier" {"default_method_declaration"} or
                interface "identifier" {"method_declaration" "default_method_declaration"}
*/

public class InterfaceGenerator {
    Node<String> t_rootNode;
    Node<String> t_currentNode;
    Vector<Node<String >> leafNodes = new Vector<Node<String>>();


    public InterfaceGenerator(String p_root) {
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
                //System.out.println();
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
             if(((p_Node.getParent()).getParent()).getData()=="interface_declaration"){
                Data.f_InterfaceNames.add(str);
                //System.out.println(Data.f_InterfaceNames.elementAt(0));
            }
            else if(((p_Node.getParent()).getParent()).getData()=="i_method_declaration"){
                Data.f_Interfaceabstarctmethods.add(str);
            }
            else if(((p_Node.getParent()).getParent()).getData()=="default_method_declaration"){
                Data.f_Interfacedefaultmethods.add(str);
            }
        }
        else
            return;

    }
    public void fn1(Node<String>p_node,int p_recdepth)
    {
        for (Node<String> stringNode : p_node.getChildren()) {
            if(stringNode.getData().equals("method_declaration"))
            {
                Method_Generator method_generator=new Method_Generator(stringNode);
                stringNode=method_generator.doGenerate();
                break;

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
        fn1(t_currentNode,0);
        getAllLeafNodes(t_rootNode);
        for (Node<String>str:leafNodes) {
            filewriter.write(str.getData());
        }
        //filewriter.write("\n");
        //System.out.println();

    }
}
