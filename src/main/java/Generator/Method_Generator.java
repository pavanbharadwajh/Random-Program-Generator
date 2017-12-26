package Generator;

import Data.Data;
import com.mifmif.common.regex.Generex;

import javax.print.DocFlavor;
import java.util.Random;
import java.util.Vector;

/*
   The Method_Generator class generates the method body based on the grammar specified in the configuration file.
   The method defination can contain "identifier" (parameter_list) {statement}  or "identifier"( ){statement}
*/

public class Method_Generator{
    Node<String> f_methodNode;

    public Method_Generator(Node<String>p_node) {
        f_methodNode=p_node;
    }
    boolean isRegex(String p_input){
        if(p_input.contains("regex"))
            return true;
        else
            return false;
    }
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
            if(((p_Node.getParent()).getData()).equals("identifier")){
                if(!((((p_Node.getParent()).getParent()).getData()).equals("parameter")))
                Data.f_methodvarNames.add(str);
            }

        }
        else
            return;

    }
    public  void fn1(int p_recdepth,Node<String>p_node)
    {
        for (Node<String> stringNode : p_node.getChildren()) {
            addChildNodes(stringNode,p_recdepth);
            if(stringNode.getData().equals(f_methodNode.getData()))
                fn1(++p_recdepth,stringNode);
            else
                fn1(0,stringNode);
        }
    }
    public Node<String> doGenerate(){
        addChildNodes(f_methodNode,0);
        fn1(0,f_methodNode);
        for (Node<String> child : f_methodNode.getChildren()) {
            if(child.getData().equals("{")){
                String str="{";
                for (String variables:Data.f_methodvarNames) {
                    str=str+"int "+variables+"=0;";
                }
                child.setData(str);
                break;
            }
        }
        Data.f_methodvarNames.clear();
        return f_methodNode;
    }
}
