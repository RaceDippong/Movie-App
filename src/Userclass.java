import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Declare the start of the constructor as private strings with the users unique info
public class Userclass {
    private final String username;
    private final String password;
    private final List<String> topMovies;
    private final List<String> watchlist;


    //Continue that with the rest of the constructor
    public Userclass(String username, String password, List<String> topMovies, List<String> watchlist) {
        this.username = username;
        this.password = password;
        this.topMovies = new ArrayList<>(topMovies);
        this.watchlist = new ArrayList<>(watchlist);
    }


    //create a public method to call the private main method of this class
    public void userclass() {
        parseuserclass();
    }

    //make this private so others cannot access this part of the class
    private void parseuserclass() {

        //Gather the file info from the User1 file
        File userinfo = new File("src/User1");


        //Declare the filepath as a string and use login.getabsolutepath to access the file
        String filePath = userinfo.getAbsolutePath();


        //Use the try loop with the buffered reader with the filepath inside it then declaring the string line as nothing and
        //line number as 0
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {


            //declare as strings and ints here for future use
            String line;
            int lineNumber = 0;


            //using the while loop with the line being equal to the buffered reader until it is null reading the whole file
            while ((line = br.readLine()) != null) {
                lineNumber++;


                //use the if loop equal to the first line number of the user1 file getting the username
                if (lineNumber == 1) {
                    line = username;


                    //use the if loop equal to the second line number of the user1 file getting the password
                } else if (lineNumber == 2) {
                    line = password;


                    //get the users top movies as listed in the file using if loops to gather that info for the specific set line numbers
                } else if (lineNumber >= 3 && lineNumber <= 6) {


                    if (lineNumber - 3 < topMovies.size()) {
                        topMovies.set(lineNumber - 3, line);

                    } else {
                        topMovies.add(line);
                    }


                    //get the users watchlist as listed in the file using if loops to gather that info for the specific set line numbers
                } else if (lineNumber >= 7 && lineNumber <= 10) {

                    if (lineNumber - 7 < watchlist.size()) {
                        watchlist.set(lineNumber - 7, line);
                    } else {
                        watchlist.add(line);
                    }
                }
            }
            //Catch part of the try loop for errors
        } catch (Exception error) {
            error.printStackTrace();
        }
    }


    //create a private method to save the users changes to the file so the changes are saved across logins
    private void saveToFile() {


        //access the file with a try loop and then use the buffered reader to access it
        File login = new File("src/User1");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(login))) {


            //use the buffered reader to write the user name and then use enhanced for loops to save the users updated movies to the file
            bw.write(username);
            bw.newLine();
            bw.write(password);
            bw.newLine();


            //create two enhanced for loops to write into top movies and watchlist
            for (String movie : topMovies) {
                bw.write(movie);
                bw.newLine();
            }
            for (String movie : watchlist) {
                bw.write(movie);
                bw.newLine();
            }

        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    //Declare as public lists so the values inside can be returned in the main class
    public List<String> getTopMovies() {
        return topMovies;
    }

    public List<String> getWatchlist() {
        return watchlist;
    }


    //Create a method to add a movie to the users watchlist, declaring it as a void to update and String movie on the inside and then save to file
    public void addToWatchlist(String movie) {

        //use an if loop if the watchlist doesn't already contain the movie, then add it to the watchlist if it isnt, if it is the else loop will conduct and shoot out a message
        if (!watchlist.contains(movie)) {

            if (watchlist.size() < 4) {
                watchlist.add(movie);
                System.out.println(movie + " has been added to your watchlist.");
                saveToFile();
            } else {
                System.out.println("Watchlist is already full please remove one. ");
            }
        } else {
            System.out.println(movie + " has already been added to your watchlist.");
        }
    }


    //Same annotation as the previous method instead it will be removed if the movie exists and then save to file
    public void removeFromWatchlist(String movie) {

        if (watchlist.contains(movie)) {
            watchlist.remove(movie);
            System.out.println(movie + " has been removed from your watchlist.");
            saveToFile();
        } else {
            System.out.println(movie + " is not in your watchlist.");
        }
    }


    //Create a method to add to top moives
    public void addToTopMovies(String movie) {

        //create an if loop if the topmovie array does not contain the entered movie
        if (!topMovies.contains(movie)) {

            //then create a if loop with the size being less than four and if it is the body of the loop will be conducted and then save to file
            if (topMovies.size() < 4) {

                topMovies.add(movie);
                System.out.println(movie + " has been added to your top movies.");
                saveToFile();

                //if the loop is larger than 4 the body of the else loop will be conducted prompting you to remove one
            } else {
                System.out.println("Your top four movies is already full please remove one.");
            }
            //if the movie is already in your top four the first if's loop else will conduct and tell you it has already been added
        } else {
            System.out.println(movie + " has already been added to your top movies.");
        }
    }

    //create a method to remove a movie from the users top four movies
    public void removeFromTopMovies(String movie) {

        //create a if loop if the array of the users top four movies contains it, the movie will be removed and then save to file
        if (topMovies.contains(movie)) {

            //then remove the movie from the topMovies array
            topMovies.remove(movie);
            System.out.println(movie + " has been removed from your top movies.");
            saveToFile();

            //if the movie is not in the topfour movies the body of the else loop will be conducted telling you it isnt
        } else {
            System.out.println(movie + " is not in your top movies.");
        }
    }
}
