package neumont;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleIO {
    public ConsoleIO() {
    }

    public static String promptForString(String prompt, boolean allowBlank) {
        if (prompt != null && !prompt.trim().isEmpty()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = null;
            boolean inputIsInvalid = true;

            do {
                System.out.print(prompt);

                try {
                    input = br.readLine();
                    inputIsInvalid = input == null || !allowBlank && input.trim().isEmpty();
                    if (inputIsInvalid) {
                        System.out.println("Your Input was invalid. Please, try again.");
                    }
                } catch (IOException var6) {
                    System.out.println("there was a problem and your input was not received. Please, try again");
                }
            } while(inputIsInvalid);
            
            
           //chicken nugget
            
            System.out.println("hi");

            return input;
        } else {
            throw new IllegalArgumentException("The prompt cannot be null, empty, or white space. prompt= " + prompt);
        }
    }

    public static String promptForString(String prompt) {
        return promptForString(prompt, false);
    }

    public static int promptForInt(String prompt, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("The Min cannot be greater than the Max. min= " + min + "; max= " + max);
        } else {
            int num = -1;
            boolean numIsInvalid = true;

            do {
                String input = promptForString(prompt);

                try {
                    num = Integer.parseInt(input);
                    numIsInvalid = num < min || num > max;
                } catch (NumberFormatException var7) {
                }

                if (numIsInvalid) {
                    System.out.println("You must enter a whole Number between " + min + " and " + max + " Please, try again.");
                }
            } while(numIsInvalid);

            return num;
        }
    }

    public static boolean promptForBoolean(String prompt, String trueString, String falseString) {
        if (trueString != null && falseString != null && !trueString.equalsIgnoreCase(falseString)) {
            boolean isTrue = false;
            boolean isInvalid = true;

            do {
                String input = promptForString(prompt, true);
                isInvalid = !input.equalsIgnoreCase(trueString) && !input.equalsIgnoreCase(falseString);
                if (isInvalid) {
                    System.out.println("you must enter either '" + trueString + "' or '" + falseString + ". Please try again.");
                } else {
                    isTrue = input.equalsIgnoreCase(trueString);
                }
            } while(isInvalid);

            return isTrue;
        } else {
            throw new IllegalArgumentException("the values of trueString and falseString cannot be null or equal: trueString=" + trueString + "falseString=" + falseString);
        }
    }

    public static int promptForMenuSelection(String[] options, boolean withQuit) {
        if ((options == null || options.length == 0) && !withQuit) {
            throw new IllegalArgumentException("the menu must have at least one option.");
        } else {
            if (options == null) {
                options = new String[0];
            }

            int min = withQuit ? 0 : 1;
            int max = options.length;
            StringBuilder sb = new StringBuilder("please, Choose one of the following: \n\n");

            for(int i = 0; i < max; ++i) {
                sb.append(i + 1).append(") ").append(options[i]).append('\n');
            }

            if (withQuit) {
                if (max > 0) {
                    sb.append('\n');
                }

                sb.append("0) Exit\n");
            }

            sb.append("\n Enter the number of your choice: ");
            String menu = sb.toString();
            return promptForInt(menu, min, max);
        }
    }

    public static void writeTextToFile(String filePath, String text) {
        writeTextToFile(filePath, text, true);
    }

    public static void writeTextToFile(String filePath, String text, boolean append) {
        try {
            FileWriter writer = new FileWriter(filePath, append);
            writer.write(text);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException var4) {
            System.out.println("An error occurred.");
            var4.printStackTrace();
        }

    }

    public static void createDirectory(String directory) {
        File file = new File(".", directory);
        file.mkdir();
    }

    public static void createDirectories(String directories) {
        File file = new File(".", directories);
        file.mkdirs();
    }

    public static String readTextFromFile(String filePath) {
        String returnString = "";

        try {
            try {
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String temp = br.readLine();
                br.close();
                returnString = temp;
            } catch (FileNotFoundException var4) {
                System.out.println("file was not found on this device: " + filePath);
            }
        } catch (IOException var5) {
            System.out.println("something Went wrong on the Devices end.");
        }

        return returnString;
    }

    public static void display(String message) {
        System.out.println(message);
    }
}