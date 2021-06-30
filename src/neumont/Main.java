package neumont;

public class Main {

    public static void main(String[] args) {

        String[] options = {"large","large serialized","long","long serialized","simple","simple serialized"};

	int choice = ConsoleIO.promptForMenuSelection(options,true);

	    if (choice > 0){

        FileReadWrite fileRead = new FileReadWrite();

        fileRead.printPeopleDetails("C:\\Users\\kwashington\\Desktop\\classroomAssignments\\First Year\\quarter 4\\Sprint 1\\databases II\\git\\Assignment 1 - data\\people\\simple\\" + options[choice-1]);

        fileRead.deserializeFromFile("C:\\Users\\kwashington\\Desktop\\classroomAssignments\\First Year\\quarter 4\\Sprint 1\\databases II\\git\\Assignment 1 - data\\people\\simple\\" + options[choice-1]);
        }

    }
}
