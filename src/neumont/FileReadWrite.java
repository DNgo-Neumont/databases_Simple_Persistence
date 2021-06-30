package neumont;

import java.awt.*;
import java.io.*;

public class FileReadWrite {

    public void printPeopleDetails(String path) {
        File file = new File(path);
        String[] list = file.list();


        for (int i = 0; i < list.length; i++) {
            File readFile = new File(path + list[i]);

            try {
                BufferedReader fileReader = new BufferedReader(new FileReader(readFile));
                System.out.println(fileReader.readLine());
            }catch (IOException io){
                System.out.println("Something went wrong");
            }

        }

    }

    public void deserializeFromFile(String path){
        File file = new File(path);
        String[] list = file.list();

        StringBuilder itemList = new StringBuilder();
        String[] seperatedTerms;

        for (int i = 0; i < list.length; i++) {
            File readFile = new File(path + list[i]);

            try {
                BufferedReader fileReader = new BufferedReader(new FileReader(readFile));

                String fileResult = fileReader.readLine();

                seperatedTerms = fileResult.split(", ");

                Employee newEmployee = new Employee(Integer.valueOf(seperatedTerms[0]), seperatedTerms[1], seperatedTerms[2], Integer.valueOf(seperatedTerms[3]));

                System.out.println(newEmployee.toString());


            }catch (IOException io){
                System.out.println("Something went wrong");
            }

        }

    }

}