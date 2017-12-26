package Reader1;

import Data.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/*
    Reader class is used to read the production rules and other limiting parameters from the config file
*/

public class Reader {
    private static int num_of_productions;
    File configFile = new File("config.properties");

    public void readInput(){

        int count = 0, lower_bound, upper_bound;


        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            String buffer;

            num_of_productions = Integer.parseInt(props.getProperty("num_of_productions"));
            Data.f_MaxClass = Integer.parseInt(props.getProperty("f_MaxClass"));
            Data.f_MinClass = Integer.parseInt(props.getProperty("f_MinClass"));
            Data.f_MaxInterface = Integer.parseInt(props.getProperty("f_MaxInterface"));
            Data.f_MinInterface = Integer.parseInt(props.getProperty("f_MinInterface"));
            Data.f_MaxMethodsInClass = Integer.parseInt(props.getProperty("f_MaxMethodsInClass"));
            Data.f_MinMethodsInClass = Integer.parseInt(props.getProperty("f_MinMethodsInClass"));
            Data.f_MaxMethodsInInterface = Integer.parseInt(props.getProperty("f_MaxMethodsInInterface"));
            Data.f_MinMethodsInInterface = Integer.parseInt(props.getProperty("f_MinMethodsInInterface"));
            Data.f_MaxDefaultMethodsInInterface = Integer.parseInt(props.getProperty("f_MaxDefaultMethodsInInterface"));
            Data.f_MinDefaultMethodsInInterface = Integer.parseInt(props.getProperty("f_MinDefaultMethodsInInterface"));
            Data.f_MaxFields = Integer.parseInt(props.getProperty("f_MaxFields"));
            Data.f_MinFields = Integer.parseInt(props.getProperty("f_MinFields"));
            Data.f_MaxStatements = Integer.parseInt(props.getProperty("f_MaxStatements"));
            Data.f_MinStatements = Integer.parseInt(props.getProperty("f_MinStatements"));
            Data.f_MaxParameters = Integer.parseInt(props.getProperty("f_MaxParameters"));
            Data.f_MinParameters = Integer.parseInt(props.getProperty("f_MinParameters"));


            int i;
            for(i = 1; i <= num_of_productions; i++) {

                buffer = props.getProperty(i + "");

                if (buffer.contains("\"")) {
                    buffer = buffer.replace("\"", "\\\"");
                }

            }
            for ( i = 1; i <= num_of_productions; i++) {

                buffer = props.getProperty(i + "");
                //buffer=buffer.trim();
                //buffer=buffer.replaceAll("\\s", "");
                String[] production = buffer.split("::=");
                String[] expansion = production[1].split("\\|");

                lower_bound = count;

                for (String s : expansion) {
                    count++;
                    Data.f_rhs.add(s);

                }

                upper_bound = count - 1;
                production[0]=production[0].trim();
                Data.f_symbols.put(production[0], new Integer[]{lower_bound, upper_bound});
            }
            Data.f_keys = Data.f_symbols.keySet();
        }
        catch(FileNotFoundException ex){
            ex.printStackTrace(); // file does not exist
        } catch(IOException ex){
            ex.printStackTrace(); // I/O error
        }
    }

    /*replaceStr method: Used to format the generated code in a proper way by adding new line characters at appropriate locations*/
    public static String replaceStr(String line) {
        line = line.replace(";", ";\n");
        line = line.replace("{", "{\n");
        line = line.replace("}", "}\n");
        line = line.replace("gen_f_d", "");
        line = line.replace("f_d", "");
        return line;
    }
}
