import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {

    public boolean credentials() {
        // Use boolean for checking if the user is authenticated and set it to false and then call the loginsession class with the session method
        boolean isAuthenticated = false;
        LoginSession session = new LoginSession();


        // Declare the initial attempts as zero and the max amount as 3 but also as a final int so it cannot be changed
        int attempts = 0;
        final int maxattempts = 3;


        // Input try-catch block to allow the file to be accessed
        try {
            // Create a new file with the login info file with the users info inside and a scanner to be able to access that info
            File login = new File("src/User1");
            Scanner sc = new Scanner(login);

            // Input scanner for the user input for the login info
            Scanner userlogin = new Scanner(System.in);

            // Two scanners for each unique credential equal to a string with the corresponding name
            System.out.println("Enter Username");
            String user = userlogin.nextLine();

            System.out.println("Enter Password");
            String pass = userlogin.nextLine();

            //Assign two unique strings  to the correct login info
            String inpUser = sc.nextLine();
            String inpPass = sc.nextLine();


            // Create an if loop with the correct login info from the file equaling it to the info entered by the user with an else loop attached if
            // the info entered is incorrect and set boolean to true if info is correct
            if (inpUser.equals(user) && inpPass.equals(pass)) {
                System.out.print("You have successfully logged in.");
                System.out.println("\n");
                isAuthenticated = true;


            } else {

                // Create a while loop if the attempts are less than maxattempts, it will continue
                while (attempts < maxattempts) {

                    // Output error message and re-input the scanners for re-entering
                    System.out.println("Please try again.");

                    System.out.println("Enter Username");
                    user = userlogin.nextLine();

                    System.out.println("Enter Password");
                    pass = userlogin.nextLine();


                    // Create an if loop if the login credentials are correct it will continue and break; if not,
                    // it will add 1 to the amount of attempts and subtract that amount from the max to show the amount remaining
                    if (inpUser.equals(user) && inpPass.equals(pass)) {

                        System.out.println("Login successful!");
                        System.out.println("\n");
                        isAuthenticated = true;
                        session.loggedIn();
                        break;

                    } else {
                        attempts++;
                        System.out.println("Incorrect credentials. Attempts left: " + (maxattempts - attempts));
                    }
                }

                // If the attempts are equal to the max attempts, the loop will exit the program
                if (attempts == maxattempts) {

                    System.out.println("Too many attempts. Account locked.");
                    System.exit(0);
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        // Return the value of the isAuthenticated variable
        return isAuthenticated;
    }


    //Create a method to allow the user to register their username and password
    public void register() {

        //declare file path for the user file
        String filePath = "src/User1";

        try {
            //read all existing lines from the file
            List<String> lines = new ArrayList<>();


            //use a buffered reader to read through the file
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            //Input a scanner for user input
            Scanner scregister = new Scanner(System.in);
            boolean validinput = false;

            String newUser = "";
            String newPass = "";

            while (!validinput) {

                //use the second half of the scanner to access the users entered info
                System.out.println("Enter Username:");
                newUser = scregister.nextLine();

                System.out.println("Enter Password:");
                newPass = scregister.nextLine();


                //create an if loop with the condition if the newUser or pass is empty it will continue
                if (newUser.isEmpty() || newPass.isEmpty()) {

                    System.out.println("Invalid input. Username and Password cannot be empty. Please try again.");

                } else {
                    // Break out of the loop if the input is valid with the else side of the loop
                    validinput = true;
                }
            }


            //Update the first two lines with the new username and password
            if (lines.size() >= 2) {


                //Replace the first and second line with the new username and password
                lines.set(0, newUser);
                lines.set(1, newPass);


            } else {
                //if the file has nothing in it the if loop will add that info to the file, same with the else loop if there is only one line
                if (lines.isEmpty()) {

                    lines.add(newUser);
                    lines.add(newPass);
                } else if (lines.size() == 1) {

                    lines.set(0, newUser);
                    lines.add(newPass);
                }
            }

            //Use the buffered writer to write the entered info back into the file using the for loop on the inside to conduct that operation
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("Registration successful.");

        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
