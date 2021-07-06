package neumont;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        String[] options = {"large","long","simple"};

        String path = ConsoleIO.promptForString("input Path to folder: ");
	    int choice = ConsoleIO.promptForMenuSelection(options,true);

        FileReadWrite fileRead = new FileReadWrite();
        String pathWithChoice = path + "\\" + options[choice-1]+ "\\";
        employeeAddition employeeAdder = new employeeAddition();
       fileRead.creatHashMap(path);


         if (choice > 0){

          fileRead.printPeopleDetails(pathWithChoice);
         fileRead.deserializeFromFile(pathWithChoice);
            fileRead.deleteEmployeeFrom(11, pathWithChoice);

          employeeAdder.addEmployee("Wanda", "Simmons", 1994, pathWithChoice);
        employeeAdder.updateEmployee(10, "RANDALL", "MONTGOMERY", 2001, pathWithChoice);

        fileRead.serializeAllEmployees(pathWithChoice);

        if((fileRead.GetSerializedEmployee(15, pathWithChoice) != null)){
            System.out.println(fileRead.GetSerializedEmployee(15, pathWithChoice));
         }else{
            System.out.println("Employee not found");
   }

         }

    }

}
