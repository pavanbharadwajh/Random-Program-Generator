package Data;

import Generator.Node;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

/* All the static data required for the specification of the code generation*/
public class Data {
    public static HashMap<String, Integer[]> f_symbols;
    public static Vector<String> f_rhs;
    public static Set<String> f_keys;
    public static Vector<String> f_InterfaceNames;
    public static Vector<String> f_Interfaceabstarctmethods;
    public static Vector<String> f_Interfacedefaultmethods;
    public static Vector<String> f_methodvarNames;
    public static Vector<String> f_classNames;
    public static int f_MinClass;
    public static int f_MaxClass;
    public static int f_MaxInterface;
    public static int f_MinInterface;
    public static int f_MaxMethodsInClass;
    public static int f_MinMethodsInClass;
    public static int f_MaxMethodsInInterface;
    public static int f_MinMethodsInInterface;
    public static int f_MaxDefaultMethodsInInterface;
    public static int f_MinDefaultMethodsInInterface;
    public static int f_MaxFields;
    public static int f_MinFields;
    public static int f_MaxStatements;
    public static int f_MinStatements;
    public static int f_MaxParameters;
    public static int f_MinParameters;

    public static int f_ClassFlag = 0;
    public static String test_key = "class_name", test_rand_value;
    public static Node<String> f_test_rootNode;

    static {
        f_symbols = new HashMap<String, Integer[]>();
        f_rhs = new Vector<String>();
        f_InterfaceNames= new Vector<String>();
        f_Interfaceabstarctmethods= new Vector<String>();
        f_Interfacedefaultmethods= new Vector<String>();
        f_methodvarNames=new Vector<String>();
        f_classNames=new Vector<String>();
        }
}
