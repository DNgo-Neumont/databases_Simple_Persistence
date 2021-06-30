package neumont;

public class Main {

    public static void main(String[] args) {
	// write your code here
        FileReadWrite fileRead = new FileReadWrite();

        fileRead.printPeopleDetails("C:\\Users\\dngo\\OneDrive - Neumont College of Computer Science\\Neumont\\Quarter 4\\DBT230\\people\\simple\\");

        fileRead.deserializeFromFile("C:\\Users\\dngo\\OneDrive - Neumont College of Computer Science\\Neumont\\Quarter 4\\DBT230\\people\\simple\\");

    }
}
