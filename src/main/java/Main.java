import Data.Data;
import Generator.InterfaceGenerator;
import Generator.MainGenerator;
import Generator.RandomGenerator;
import Generator.RandomGenerator.*;
import Reader1.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static FileWriter.FileWriterClass.filewriter;

public class Main {



    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        reader.readInput();
        RandomGenerator r = new RandomGenerator();
        int rand = r.random(Data.f_MinInterface, Data.f_MaxInterface);
        int iterator;
        InterfaceGenerator interfaceGenerator;
        /*Creating interfaces based on the min and max number specified in config file
        */
        for(iterator = 0; iterator < rand; iterator++) {
            interfaceGenerator = new InterfaceGenerator("interface_declaration");
            interfaceGenerator.codeGenerator();
        }
        /*Creating public class so that the generated code has the same file name as the public class
         */
        MainGenerator mainGenerator=new MainGenerator("class_declaration");
        mainGenerator.codeGenerator();
        rand = r.random(Data.f_MinClass, Data.f_MaxClass-1);
        /*Creating generic class
         */
        if(Data.f_MaxClass>1)
        {
            mainGenerator=new MainGenerator("Generic");
            mainGenerator.codeGenerator();
        }
        /*Creating classes based on the min and max number specified in config file
         */
        for(iterator = 0; iterator < rand-1; iterator++) {
            mainGenerator = new MainGenerator("class_declaration1");
            mainGenerator.codeGenerator();
        }

        filewriter.close();

        FileReader fileReader = new FileReader("Trace.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null, temp[], main_class_name[];
        line = bufferedReader.readLine();
        temp = line.split("public class ");
        main_class_name = temp[1].split("\\{");
        System.out.println("Random Java program generated successfully.\nProgram location: out\\" + main_class_name[0] + ".java");

        filewriter = new FileWriter("out\\" + main_class_name[0] + ".java");
        line = Reader.replaceStr(line);
        filewriter.write(line);
        filewriter.close();

        bufferedReader.close();
    }

}
