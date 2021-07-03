package neumont;

import java.io.*;
import java.util.Arrays;

public class employeeAddition {

    private File matchingFile;

    public void addEmployee(String firstName, String lastName, int hireDate, String path){
        File dirPath = new File(path);
        String[] fileList = dirPath.list();

        int[] splitString = new int[fileList.length];

        for(int i = 0; i < fileList.length; i ++){
            splitString[i] =Integer.parseInt(fileList[i].split(".txt")[0]);
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
            idString[0] = "1";
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

    public void updateEmployee(int id, String firstName, String lastName, int hireDate, String path) {
        File dirPath = new File(path);
        String[] fileList = dirPath.list();

        int[] splitString = new int[fileList.length];

        for(int i = 0; i < fileList.length; i ++){
            splitString[i] =Integer.valueOf(fileList[i].split(".txt")[0]);
        }

        Arrays.sort(splitString);

        String[] idString;

        int matchingID = 0;

        boolean noMatch = true;

        for(int i = 0; i < fileList.length; i ++){
            try{
                File currentFile = new File(path + splitString[i] + ".txt");
                BufferedReader iterativeReader = new BufferedReader(new FileReader(currentFile));

                idString = iterativeReader.readLine().split(", ");

                if(Integer.parseInt(idString[0]) == id){
                    matchingID = Integer.parseInt(idString[0]);
                    matchingFile = currentFile;
                    iterativeReader.close();
                    noMatch = false;
                    break;
                }

                iterativeReader.close();

            }catch(IOException ioe){
                    System.err.println(ioe.getMessage());
            }
        }

        if(!noMatch){
            try{
                FileWriter matchingWriter = new FileWriter(matchingFile.toString());
                matchingWriter.write(matchingID + ", " + firstName + ", " + lastName + ", " + hireDate);
                matchingWriter.close();

            }catch(IOException ioe){
                System.err.println(ioe.getMessage());
            }

        }

        matchingFile = null;

    }



}
