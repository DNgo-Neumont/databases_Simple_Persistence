package neumont;

public class Main {

    public static void main(String[] args) {

        String[] options = {"large","long","simple"};

        String path = ConsoleIO.promptForString("input Path to folder: ");
	    int choice = ConsoleIO.promptForMenuSelection(options,true);

	    if (choice > 0){

        String pathWithChoice =path + "\\" + options[choice-1]+ "\\";
        FileReadWrite fileRead = new FileReadWrite();
        employeeAddition employeeAdder = new employeeAddition();

        fileRead.deserializeFromFile(pathWithChoice);
        fileRead.printPeopleDetails(pathWithChoice);
        //fileRead.deleteEmployeeFrom(11, pathWithChoice); - Example
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
