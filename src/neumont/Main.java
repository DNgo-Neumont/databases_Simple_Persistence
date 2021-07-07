package neumont;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String[] options = {"long","simple"};

        String path = ConsoleIO.promptForString("input Path to folder: ");
	    int choice = ConsoleIO.promptForMenuSelection(options,true);

        FileReadWrite fileRead = new FileReadWrite();
        String pathWithChoice = path + "\\" + options[choice-1]+ "\\";
        employeeAddition employeeAdder = new employeeAddition();
        fileRead.createHashMap(path);


         if (choice > 0){

             System.out.println("Hash Map readThrough");
             fileRead.printAllEmployees(path);

           // fileRead.printPeopleDetails(pathWithChoice);
           // fileRead.deserializeFromFile(pathWithChoice);
            fileRead.deleteEmployeeFrom(11, pathWithChoice);

            employeeAdder.addEmployee("Wanda", "Simmons", 1994, pathWithChoice);
            employeeAdder.updateEmployee(10, "RANDALL", "MONTGOMERY", 2001, pathWithChoice);

            fileRead.serializeAllEmployees(pathWithChoice);

            fileRead.GetSerializedEmployee(pathWithChoice);


            if((fileRead.GetSerializedEmployee(21, pathWithChoice) != null)){
                System.out.println(fileRead.GetSerializedEmployee(21, pathWithChoice));
            }else{
                System.out.println("Employee not found");
            }

            if(fileRead.findEmployee(21) != null){
                System.out.println(fileRead.findEmployee(21));
            }else{
                System.out.println("Employee not found");
            }

            if(fileRead.findEmployee("SIMMONS") != null){
                System.out.println(fileRead.findEmployee("SIMMONS"));
            }else{
                System.out.println("Employee not found");
            }

            System.out.println("--------------------------------------------------------------");
            System.out.println("Searching for multiple employees");


            if(!fileRead.findAllEmployeesByLastName("SIMMONS").isEmpty()){
                List<Employee> results = fileRead.findAllEmployeesByLastName("SIMMONS");

                for(int i = 0; i < results.size(); i ++){
                    System.out.println(results.get(i));
                }

            }

         }

    }

}
