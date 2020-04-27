//Lopatin, task12, Дополняем калькулятор - Exceptions.
package org.brit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.nio.file.Files;

public class task12 {
    static String filename = "src/main/files/data8.txt";

    public enum OpEnum {
        PLUS("+"),
        MINUS("-"),
        MULT("*"),
        DIV("/"),
        REM("%");

        String value;

        OpEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }//enum OpEnum

    public static void main(String[] args) {
        System.out.println("Lopatin, task12, Дополняем калькулятор - Exceptions.");

        List<String> values = readFromFile();
        ListIterator<String> iterator = values.listIterator();

        while(iterator.hasNext()) {
            Integer var1 = Integer.parseInt(iterator.next());
            OpEnum operation = OpEnum.valueOf(iterator.next());
            Integer var2 = Integer.parseInt(iterator.next());

            Integer result = Calculate(var1,var2,operation);
            System.out.println(String.format("%d %s %d = %d",var1,operation.getValue(),var2,result));
        }//while
    }//main()

    public static List<String> readFromFile(){
        File file = new File(filename);
        List<String> strings = null;
        List<String> values = new ArrayList<>();
        try {
            strings = Files.readAllLines(file.toPath(), Charset.defaultCharset());
        }
        catch (NoSuchFileException fileNotFound){
            System.out.println("File \""+filename+"\" not found!");
        }//catch NoSuchFileException
        catch (IOException e) {
            e.printStackTrace();
        }//catch
        finally{
            System.exit(-1);
        }
        for (String s: strings) {
            String[] split = s.split(",");
            values.add(split[0].trim());  //1st number
            values.add(split[1].trim().toUpperCase()); //operation
            values.add(split[2].trim()); //2nd number
        }//for
        return values;
    }//readFromFile()


    public static Integer Calculate(Integer var1,Integer var2,OpEnum operation){
        switch(operation) {
            case PLUS:  return (var1+var2);
            case MINUS: return (var1-var2);
            case MULT:  return (var1*var2);
            case DIV: {
                    try {
                        return (var1/var2);
                    }//try
                    catch(ArithmeticException dz){
                        System.out.println("Divide by zero!");
                        return -1;
                    }//catch
            }//case DIV
            case REM: {
                    try {
                        return (var1%var2);
                    }//try
                    catch(ArithmeticException dz){
                        System.out.println("Divide by zero!");
                        return -1;
                    }//catch
            }//case REM
            default:{
                System.out.println("Unknown operation. Aborted.");
                return -1;
            }//case default
        }//switch
    }//Calculate()

}//class task12
