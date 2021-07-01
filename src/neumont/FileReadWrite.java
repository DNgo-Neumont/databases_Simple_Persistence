package neumont;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class FileReadWrite {

    public void printPeopleDetails(String path) {
        File file = new File(path);
        String[] list = file.list();

        for (int i = 0; i < list.length; i++) {
            File readFile = new File(path + list[i]);

            try {
                BufferedReader fileReader = new BufferedReader(new FileReader(readFile));
                System.out.println(fileReader.readLine());
                fileReader.close();
            }catch (IOException io){
                System.out.println("Something went wrong");
            }


        }

    }

    public void deserializeFromFile(String path){
        File file = new File(path);
        String[] list = file.list();

        String[] seperatedTerms;

        for (int i = 0; i < list.length; i++) {
            File readFile = new File(path + list[i]);


            try {
                BufferedReader fileReader = new BufferedReader(new FileReader(readFile));

                String fileResult = fileReader.readLine();

                seperatedTerms = fileResult.split(", ");

                Employee newEmployee = new Employee(Integer.valueOf(seperatedTerms[0]), seperatedTerms[1], seperatedTerms[2], Integer.valueOf(seperatedTerms[3]));

                System.out.println(newEmployee.toString());

                fileReader.close();
            }catch (IOException io){
                System.out.println("Something went wrong");
            }


        }
    }

    public void deleteEmployeeFrom(int id, String path){
        File dirPath = new File(path);
        String[] fileList = dirPath.list();

        String[] fileContent = new String[4];
        
        for(int i = 0; i < fileList.length; i ++ ){
            File currentFile = new File(path + fileList[i]);
            try{
                BufferedReader fileReader = new BufferedReader(new FileReader(currentFile));

                fileContent = fileReader.readLine().split(", ");
                
                fileReader.close();
            }catch(FileNotFoundException fnf){
                System.out.println(fnf.getMessage());
            }catch(IOException ioe){
                System.out.println(ioe.getMessage());
            }
            
            if(Integer.valueOf(fileContent[0]) == id){
                if(currentFile.delete()){
                    System.out.println("File deleted");
                }else{
                    System.out.println("Failed to delete file");
                }
            }


        }

    }


}