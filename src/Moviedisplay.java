import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class Moviedisplay {

    //create a void method display the top movies from the text file
    public void displaymovies() {

        //use file path to access the movies file
        File movieFile = new File("src/Movies");


        //use the set to store them as unique movies so none are repeated using the hashset
        Set<String> uniqueMovies = new HashSet<>();


        //use the buffered reader to read the file
        try (BufferedReader br = new BufferedReader(new FileReader(movieFile))) {
            String line;


            //use a while loop to access each line of the file until it is empty
            while ((line = br.readLine()) != null) {
                uniqueMovies.add(line.trim());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //use a enhanced for loop to display the top movies to the user
        System.out.println("Top movies from our users:");
        for (String movie : uniqueMovies) {
            System.out.println(movie);
        }
    }
}
