package neumont;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileReadWrite {

    public void printPeopleDetails(String path) {
        File file = new File(path);
        String[] list = file.list();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                File readFile = new File(path + list[i]);

                try {
                    BufferedReader fileReader = new BufferedReader(new FileReader(readFile));
                    System.out.println(fileReader.readLine());
                    fileReader.close();
                } catch (IOException io) {
                    System.out.println("Something went wrong");
                }
            }

        } else {
            System.out.println("No employees found");
        }

    }

    public void deserializeFromFile(String path) {
        File file = new File(path);
        String[] list = file.list();

        String[] seperatedTerms;

        if (list != null) {

            for (int i = 0; i < list.length; i++) {
                File readFile = new File(path + list[i]);


                try {
                    BufferedReader fileReader = new BufferedReader(new FileReader(readFile));

                    String fileResult = fileReader.readLine();

                    seperatedTerms = fileResult.split(", ");

                    Employee newEmployee = new Employee(Integer.parseInt(seperatedTerms[0]), seperatedTerms[1], seperatedTerms[2], Integer.parseInt(seperatedTerms[3]));

                    System.out.println(newEmployee.toString());

                    fileReader.close();
                } catch (IOException io) {
                    System.out.println("Something went wrong");
                }


            }
        } else {
            System.out.println("No files found");
        }

    }

    public void deleteEmployeeFrom(int id, String path) {
        File dirPath = new File(path);
        String[] fileList = dirPath.list();

        String[] fileContent = new String[4];

        for (int i = 0; i < fileList.length; i++) {
            File currentFile = new File(path + fileList[i]);
            try {
                BufferedReader fileReader = new BufferedReader(new FileReader(currentFile));

                fileContent = fileReader.readLine().split(", ");

                fileReader.close();
            } catch (FileNotFoundException fnf) {
                System.out.println(fnf.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }

            if (Integer.parseInt(fileContent[0]) == id) {
                if (currentFile.delete()) {
                    System.out.println("File deleted");
                } else {
                    System.out.println("Failed to delete file");
                }
            }


        }

    }

    public void serializeAllEmployees(String path) {
        File dirPath = new File(path);
        String[] fileList = dirPath.list();

        int[] splitString = new int[fileList.length];

        for (int i = 0; i < fileList.length; i++) {
            splitString[i] = Integer.valueOf(fileList[i].split(".txt")[0]);
        }

        Arrays.sort(splitString);

        ArrayList<Employee> readEmployees = new ArrayList<Employee>();

        for (int i = 0; i < fileList.length; i++) {
            try {
                File currentFile = new File(path + splitString[i] + ".txt");

                BufferedReader fileReader = new BufferedReader(new FileReader(currentFile));

                String[] splitInput = fileReader.readLine().split(", ");

                Employee newEmployee = new Employee(Integer.parseInt(splitInput[0]), splitInput[1], splitInput[2], Integer.parseInt(splitInput[3]));

                readEmployees.add(newEmployee);

                fileReader.close();
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        }

        File upperDirectory = new File(dirPath.getParent());
        File longSerialized = new File(upperDirectory + "\\long serialized\\");

        if (!longSerialized.exists()) {
            longSerialized.mkdir();
        }

        for (int i = 0; i < readEmployees.size(); i++) {
            File currentFile = new File(longSerialized.getPath() + "\\" + readEmployees.get(i).getId() + ".ser");

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(currentFile.toString());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(readEmployees.get(i));

                objectOutputStream.close();
                fileOutputStream.close();
            } catch (FileNotFoundException fnf) {
                System.err.println(fnf.getMessage());
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }

        }

    }

    public Employee GetSerializedEmployee(int id, String path) {
        File dirPath = new File(path);
        File serializedPath = new File(dirPath.getParent() + "\\long serialized\\");
        String[] fileList = serializedPath.list();

        int[] splitString = new int[fileList.length];

        for (int i = 0; i < fileList.length; i++) {
            splitString[i] = Integer.valueOf(fileList[i].split(".ser")[0]);
        }

        Arrays.sort(splitString);

        ArrayList<Employee> employeeList = new ArrayList<>();


        for (int i = 0; i < fileList.length; i++) {

            File currentFile = new File(serializedPath.getPath() + "\\" + splitString[i] + ".ser");
            try {
                FileInputStream fileInputStream = new FileInputStream(currentFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                Employee newEmployee = (Employee) objectInputStream.readObject();

                employeeList.add(newEmployee);
                objectInputStream.close();
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Employee employeeToReturn = null;

        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                employeeToReturn = employeeList.get(i);
            }
        }

        return employeeToReturn;
    }

    public void search(String path) {
        ArrayList<String> pathways = new ArrayList<>();
        {
            pathways.add("large");
            pathways.add("long");
            pathways.add("simple");
        }

        for (int i = 0; i < pathways.size(); i++) {
            File Path = new File(path + pathways.get(i));
            String[] fileList = Path.list();

            for (int j = 0; j < fileList.length; j++) {

            }
        }

    }

    public void creatHashMap(String path) {
        HashMap<Integer, Employee> objectAndID = new HashMap<>();
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Integer> ID = new ArrayList<>();
        ArrayList<String> pathways = new ArrayList<>();
        {
            pathways.add("long");
            pathways.add("simple");
        }
        for (int t = 0; t < pathways.size(); t++){

        {
            File file = new File(path +"\\" + pathways.get(t));
            String[] list = file.list();

            String[] seperatedTerms;

            if (list != null) {

                for (int i = 0; i < list.length; i++) {
                    File readFile = new File(path +"\\" + pathways.get(t) + "\\" + list[i]);


                    try {
                        BufferedReader fileReader = new BufferedReader(new FileReader(readFile));

                        String fileResult = fileReader.readLine();

                        seperatedTerms = fileResult.split(", ");

                        Employee newEmployee = new Employee(Integer.parseInt(seperatedTerms[0]), seperatedTerms[1], seperatedTerms[2], Integer.parseInt(seperatedTerms[3]));
                        employees.add(newEmployee);
                        ID.add(Integer.parseInt(seperatedTerms[0]));

                        fileReader.close();
                    } catch (IOException io) {
                        System.out.println("Something went wrong");
                    }


                }
            } else {
                System.out.println("No files found");
            }
        }
        }

        for (int i = 0; i < employees.size(); i++) {
            objectAndID.put(ID.get(i), employees.get(i));
        }

    }
}