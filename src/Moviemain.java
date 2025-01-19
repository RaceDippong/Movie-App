import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Moviemain {

    //Create a static method for the ease of repetition later
    public static String error() {
        return "Invalid input please try again.";
    }

    public static void main(String[] args) {


        //Declare as strings at the start for ease of use later
        String login = "login";
        String ratings = "ratings";
        String exit = "exit";
        String logout = "logout";
        String register = "register";


        //Declare the new name to the other class outside the loop to prevent repetition inside
        Moviedisplay moviedisplaycall = new Moviedisplay();
        Movieratings movieratingscall = new Movieratings();


        //declare the scanner and session outside the while loop because for the re-logging in to work it has to be outside it
        Scanner scanmain = new Scanner(System.in);
        LoginSession session = new LoginSession();

        System.out.println("Welcome to the movie app!");

        //Display the list of top movies to the user by calling the displaymovie method
        moviedisplaycall.displaymovies();
        System.out.println("\n");


        //input a while loop here to allow the user to login while in guest mode
        while (true) {


            //create an if loop if the session hasn't been previously logged in with the guest mode inside it, have the condition be equal to not login, if you are logged in it will ignore this loop
            if (!session.loggedIn()) {


                //create a while loop that will repeat the following code until the user selects otherwise
                while (true) {


                    //output the choice and then declare the seconduserchoice string outside the loop because if it was in the if loop the while loop will not recognize it
                    System.out.println("Would you like to view ratings, login, register or exit?");
                    String userchoice = scanmain.nextLine();


                    //create an if loop for if the user enters incorrectly
                    while (!userchoice.equals(register) && !userchoice.equals(exit) && !userchoice.equals(login) && !userchoice.equals(ratings)) {
                        System.out.println(error());
                        userchoice = scanmain.nextLine();
                    }


                    //create an if/else if loop with each choice from the user with the corresponding code
                    if (userchoice.equals(exit)) {

                        //exit the program by setting guestmode to false and then breaking the program
                        System.out.println("Goodbye, thank you for using the movie app.");
                        System.exit(0);


                    } else if (userchoice.equals(ratings)) {

                        //call the movieratings and then break the loop
                        movieratingscall.movieratingsout();
                        break;


                    } else if (userchoice.equals(login)) {
                        //call the login1 method
                        Login login1 = new Login();


                        //create another if loop using the previously called class using the credentials method and then use an else loop
                        //if the login1.credentials are not met which will continue
                        if (login1.credentials()) {
                            session.login("username", "password");
                            System.out.println("Logged in successfully.");

                        } else {
                            System.out.println("Login failed. Please try again.");
                        }
                        break;


                    } else if (userchoice.equals(register)) {
                        Login register1 = new Login();
                        register1.register();
                        session.login("username", "password");
                        System.out.println("Logged in successfully.");
                        break;
                    }
                }


                //create an else loop is the session is logged in
            } else if (session.loggedIn()) {


                //Declare as strings for ease of user later
                String profile = "profile";
                String watchliststring = "watchlist";
                String friends = "friends";
                String add = "add";
                String remove = "remove";
                String top4 = "top four";
                String movieToAdd;
                String movieToRemove;
                String username = "";
                String password = "";


                //Declare these as a new array so they can be used further down
                List<String> topMovies = new ArrayList<>();
                List<String> watchlist = new ArrayList<>();


                //Access the user1 file containing all of that users info and then use the filepath to gather the info inside it
                File loginmain = new File("src/User1");
                String filePath = loginmain.getAbsolutePath();


                //Use the buffered reader so each line of the file can be read
                try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {


                    //Declare a temp variable and declare the linenumber as 0
                    String temp;
                    int lineNumber = 0;


                    //Create a while loop with the temp variable that reads everything inside until it ends at a null variable
                    while ((temp = br.readLine()) != null) {
                        //Add the line number each time
                        lineNumber++;


                        //Create a if loop for where the username is in the file and equal it to the temp variable
                        if (lineNumber == 1) {
                            username = temp;


                            //Create an else if loop for where the password is in the file and equal it to the temp variable
                        } else if (lineNumber == 2) {
                            password = temp;
                        }
                    }

                    //Call the user class with a new name and new Userclass and then use the new name. name of the Userclass
                    Userclass userclasscall = new Userclass(session.username(), session.password(), topMovies, watchlist);
                    userclasscall.userclass();


                    //Create a welcome message with the username then class call the .display movie method
                    System.out.println("\n");
                    System.out.println("Welcome " + username + "!");


                    //create a boolean string with the value being true
                    boolean loggedIn = true;

                    Scanner usefulscan = new Scanner(System.in);
                    //Create a while loop so the following code is repeated until exited by the user with the loggin in status

                    while (loggedIn) {


                        //Output a message to the user to allow for their input on what they want to do next
                        System.out.println("Would you like to view the ratings on the top movies, look at your user profile," +
                                " add movies to your watchlist, look at your friends top movies or exit?");
                        System.out.println("Enter: 'ratings' to look at the top ratings, 'profile' to look at your user profile, 'friends' to look at your friends top movies, 'top four' to view or edit your top four movies" +
                                " or 'watchlist' to look at your watchlist, 'logout' to logout and 'exit' to exit.");

                        String seconduserchoice = usefulscan.nextLine();


                        //Create a while loop is second user choice is mis-entered prompting re-entry
                        while (!seconduserchoice.equalsIgnoreCase(profile) && !seconduserchoice.equalsIgnoreCase(friends) && !seconduserchoice.equalsIgnoreCase(top4) &&
                                !seconduserchoice.equalsIgnoreCase(watchliststring) && !seconduserchoice.equalsIgnoreCase(exit) && !seconduserchoice.equalsIgnoreCase(ratings)
                                && !seconduserchoice.equalsIgnoreCase(logout)) {
                            System.out.println(error());
                            seconduserchoice = usefulscan.nextLine();

                        }


                        //use a switch and cases instead of if loop because it is more efficient and cleaner
                        switch (seconduserchoice) {
                            case "exit":

                                //Create a system exit if the user selects it
                                System.out.println("You have selected exit thank you for using the movie app, goodbye.");
                                System.exit(0);
                                break;


                            case "logout":

                                //create an option to logout and set the logged in value to false and logout the session which will return the user to main menu
                                System.out.println("You have logged out, returning to main menu.");
                                loggedIn = false;
                                session.logout();
                                break;


                            case "ratings":
                                System.out.println("\n");

                                //Using the movieratingscall method output that value to the user
                                movieratingscall.movieratingsout();
                                break;


                            case "profile":
                                System.out.println("\n");


                                //Output the users username password to them
                                System.out.println("Your username is: " + username);
                                System.out.println("Your password is: " + password);


                                //Call the userclasscall with the return method for the top movies and watchlist
                                System.out.println("Your top movies are: ");
                                System.out.println(userclasscall.getTopMovies());


                                System.out.println("Your watchlist is: ");
                                System.out.println(userclasscall.getWatchlist());


                                System.out.println("\n");
                                break;

                            case "friends":
                                System.out.println("\n");

                                //call the Friendslist constructor and declare them as blank variables
                                Friendslist friendcall = new Friendslist("", Collections.singletonList(""));


                                //call the friendslist to get the friends info from the file into the main class
                                friendcall.friendslist();


                                System.out.println("Current friends are: " + friendcall.getName());
                                System.out.println("Enter your friends name to view their top movies or exit to exit.");


                                //create an if loop with the first scanner to get the friends name
                                if (scanmain.hasNextLine()) {


                                    //input the second part of the scanner and then call the method for the friends lists top movies from the user class
                                    String friendname = scanmain.nextLine();


                                    //create an if and else loop to output the friends top movies and also an error message if the friend doesn't have any/doesn't exist
                                    if (!friendcall.getTopMovies().isEmpty()) {
                                        System.out.println("Top movies for " + friendname + " are: " + friendcall.getTopMovies());

                                    } else {
                                        System.out.println("Friend not found or no top movies available.");
                                    }
                                }

                                System.out.println("\n");
                                break;

                            case "top four":
                                System.out.println("\n");


                                //create a while loop here with the true value so you can keep editing your top four multiple times in a row without having to re-enter the top4 loop
                                while (true) {


                                    //Output the current top four of the user and a scanner for the option to add and remove
                                    System.out.println("Your current top four is: " + userclasscall.getTopMovies());
                                    System.out.println("Would you like to add or remove from your top four or exit?");
                                    System.out.println("Enter: 'add' or 'remove' and 'exit' to exit.");
                                    String top4choice = scanmain.nextLine();


                                    //Create a while loop for incorrect spelling which allows re-entering
                                    while (!top4choice.equalsIgnoreCase(add) && !top4choice.equalsIgnoreCase(remove) && !top4choice.equalsIgnoreCase(exit)) {
                                        System.out.println(error());
                                        top4choice = scanmain.nextLine();
                                    }


                                    //Create an if loop for add and an else loop for if the user selects remove
                                    if (top4choice.equalsIgnoreCase(add)) {
                                        System.out.println("\n");


                                        //Create another scanner for the specific movie and then use the class call with the correct method to edit the movie
                                        System.out.println("Enter the name of the movie you want to add: ");
                                        movieToAdd = scanmain.nextLine();
                                        userclasscall.addToTopMovies(movieToAdd);


                                    } else if (top4choice.equalsIgnoreCase(remove)) {
                                        System.out.println("\n");


                                        //Create another scanner for the specific movie and then use the class call with the correct method to edit the movie
                                        System.out.println("Enter the name of the movie you want to remove: ");
                                        movieToRemove = scanmain.nextLine();
                                        userclasscall.removeFromTopMovies(movieToRemove);


                                        //create an else if loop for if the user wants to exit
                                    } else if (top4choice.equalsIgnoreCase(exit)) {
                                        break;
                                    }
                                }

                                System.out.println("\n");
                                break;

                            case "watchlist":
                                System.out.println("\n");

                                //create a while loop to repeat the add and remove without having to re enter watchlist
                                while (true) {

                                    //Call the watchlist with the .get method
                                    System.out.println("Your watchlist is: ");
                                    System.out.println(userclasscall.getWatchlist());


                                    //give the user the choice to add remove or exit then input a scanner for the watchlist choice
                                    System.out.println("Would you like to add or remove from your watchlist, or exit the watchlist? ");
                                    System.out.println("Enter: 'add' to add, 'remove' to remove or 'exit' to exit");
                                    String watchlistchoiceinput = scanmain.nextLine();


                                    //Create a while loop for incorrect spelling which allows re-entering
                                    while (!watchlistchoiceinput.equalsIgnoreCase(add) && !watchlistchoiceinput.equalsIgnoreCase(remove) && !watchlistchoiceinput.equalsIgnoreCase(exit)) {
                                        System.out.println(error());
                                        watchlistchoiceinput = scanmain.nextLine();
                                    }


                                    //Create a if loop for each choice that will conduct what the user selects
                                    if (watchlistchoiceinput.equalsIgnoreCase(add)) {
                                        System.out.println("\n");

                                        //use the previously initiated string and input a new scanner to allow the input and then call the addToWatchlist method
                                        System.out.println("Enter the name of the movie you want to add: ");
                                        movieToAdd = scanmain.nextLine();
                                        userclasscall.addToWatchlist(movieToAdd);


                                    } else if (watchlistchoiceinput.equalsIgnoreCase(remove)) {
                                        System.out.println("\n");

                                        //use the previously initiated string and scanner to allow the input and then call the removeFromWatchList method
                                        System.out.println("Enter the name of the movie you want to remove: ");
                                        movieToRemove = scanmain.nextLine();
                                        userclasscall.removeFromWatchlist(movieToRemove);


                                    } else if (watchlistchoiceinput.equalsIgnoreCase("exit")) {
                                        //a break is used here instead of system exit so the user can reselect if needed
                                        break;
                                    }
                                }
                                System.out.println("\n");
                                break;
                        }
                    }
                } catch (IOException error) {
                    error.getMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //create a else loop for if the login fails it will exit the program
            } else {
                System.out.println("Login failed. Exiting the program.");
                System.exit(0);
            }
        }
    }
}
