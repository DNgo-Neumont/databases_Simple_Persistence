package neumont;

import java.io.*;
import java.util.Arrays;

public class employeeAddition {
    public void addEmployee(String firstName, String lastName, int hireDate, String path){
        File dirPath = new File(path);
        String[] fileList = dirPath.list();
        
        int[] splitString = new int[fileList.length];

        for(int i = 0; i < fileList.length; i ++){
            splitString[i] =Integer.valueOf(fileList[i].split(".txt")[0]);
        }

        Arrays.sort(splitString);

        String[] idString = new String[4];

        if (fileList != null) {
            String endFileName = splitString[fileList.length - 1] + ".txt";
            try{
                File endFile = new File(path + endFileName);
                BufferedReader fileReader = new BufferedReader(new FileReader(endFile));

                idString = fileReader.readLine().toString().split(", ");
                fileReader.close();
            }catch(IOException ioe){
                System.err.println(ioe.getMessage());
            }
        }else{
            idString[0] = "1.txt";
        }

        int finalID = Integer.valueOf(idString[0]) + 1;


        File newEmployeeFile = new File(path + "\\" + finalID + ".txt");

        try{
            FileWriter employeeWriter = new FileWriter(newEmployeeFile.toString());
            employeeWriter.write(finalID + ", " + firstName + ", " + lastName + ", " + hireDate);
            employeeWriter.close();

        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }


    }




}
