package neumont;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileReadWrite {

    public void printPeopleDetails(String path) {
        File file = new File(path);
        String[] list = file.list();
        if(list != null){
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

        }else{
            System.out.println("No employees found");
        }

    }

    public void deserializeFromFile(String path){
        File file = new File(path);
        String[] list = file.list();

        String[] seperatedTerms;

        if(list != null){

            for (int i = 0; i < list.length; i++) {
                File readFile = new File(path + list[i]);


                try {
                    BufferedReader fileReader = new BufferedReader(new FileReader(readFile));

                    String fileResult = fileReader.readLine();

                    seperatedTerms = fileResult.split(", ");

                    Employee newEmployee = new Employee(Integer.parseInt(seperatedTerms[0]), seperatedTerms[1], seperatedTerms[2], Integer.parseInt(seperatedTerms[3]));

                    System.out.println(newEmployee.toString());

                    fileReader.close();
                }catch (IOException io){
                    System.out.println("Something went wrong");
                }


            }
        }else{
            System.out.println("No files found");
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
            
            if(Integer.parseInt(fileContent[0]) == id){
                if(currentFile.delete()){
                    System.out.println("File deleted");
                }else{
                    System.out.println("Failed to delete file");
                }
            }


        }

    }

    public void serializeAllEmployees(String path){
        File dirPath = new File(path);
        String[] fileList = dirPath.list();

        int[] splitString = new int[fileList.length];

        for(int i = 0; i < fileList.length; i ++){
            splitString[i] =Integer.valueOf(fileList[i].split(".txt")[0]);
        }

        Arrays.sort(splitString);

        ArrayList<Employee> readEmployees = new ArrayList<Employee>();

        for(int i = 0; i < fileList.length; i ++){
            try{
                File currentFile = new File(path + splitString[i] + ".txt");

                BufferedReader fileReader = new BufferedReader(new FileReader(currentFile));

                String[] splitInput = fileReader.readLine().split(", ");

                Employee newEmployee = new Employee(Integer.parseInt(splitInput[0]), splitInput[1], splitInput[2], Integer.parseInt(splitInput[3]));

                readEmployees.add(newEmployee);

                fileReader.close();
            }catch(IOException ioe){
                System.err.println(ioe.getMessage());
            }
        }

        File upperDirectory = new File(dirPath.getParent());
        File longSerialized = new File(upperDirectory + "\\long serialized\\");

        if(!longSerialized.exists()){
            longSerialized.mkdir();
        }

        for(int i = 0; i < readEmployees.size(); i ++){
            File currentFile = new File(longSerialized.getPath() + "\\" + readEmployees.get(i).getId() + ".ser");

            try{
                FileOutputStream fileOutputStream = new FileOutputStream(currentFile.toString());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(readEmployees.get(i));

                objectOutputStream.close();
                fileOutputStream.close();
            }catch(FileNotFoundException fnf){
                System.err.println(fnf.getMessage());
            }catch(IOException ioe){
                System.err.println(ioe.getMessage());
            }

        }

    }


}