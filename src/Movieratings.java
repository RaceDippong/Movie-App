import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Movieratings {

    //create a void method to output the movie ratings from the text file
    public void movieratingsout() {

        //Input try loop to allow the file to be accessed
        try {
            //Create a new file with the movies file with the list of movies to be displayed
            File movies = new File("src/Movie Ratings");

            System.out.println("The ratings of our top movies are: ");

            //Create a buffered reader to read each line in the file and then use a while loop to output that information
            BufferedReader br = new BufferedReader(new FileReader("src/Movie Ratings"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            //Input the catch part of the try loop for potential errors
        } catch (Exception error) {
            error.printStackTrace();

        }
    }
}
