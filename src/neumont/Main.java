package neumont;

public class Main {

    public static void main(String[] args) {

        String[] options = {"large","long","simple"};

        String path = ConsoleIO.promptForString("input Path to folder: ");
	int choice = ConsoleIO.promptForMenuSelection(options,true);

	    if (choice > 0){

        FileReadWrite fileRead = new FileReadWrite();

        fileRead.printPeopleDetails(path + "\\" + options[choice-1] + "\\");

        fileRead.deserializeFromFile(path + "\\" + options[choice-1]+ "\\");
        }

    }
}
