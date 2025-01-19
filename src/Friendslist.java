import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Friendslist {

    //declare as private variables at the start to begin the constructor
    private String friend1;
    private List<String> friendtop4;


    //finish the constructor with the name and topmovie list
    public Friendslist(String name, List<String> topMovies) {
        this.friend1 = name;
        this.friendtop4 = new ArrayList<>(topMovies);
    }

    //create a method to get the name and top movies from the friend
    public String getName() {
        return friend1;
    }

    //create a method to output the friends top four movies
    public List<String> getTopMovies() {
        return friendtop4;
    }


    //create a void method to get the friends info from the text file
    public void friendslist() {


        //access the file and give it a name in this class
        File friendfile = new File("src/Friendslistfile");

        //use the absolute path to access the text in the file
        String filePath = friendfile.getAbsolutePath();

        //declare as blank and 0 for future use
        String line;
        int lineNumber = 0;


        //use the buffered reader to read each line of the file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            //use the while leap to read everyline of the file until there is nothing there
            while ((line = br.readLine()) != null) {
                lineNumber++;

                //use the if loop to make the first line of the file the friends name
                if (lineNumber == 1) {
                    friend1 = line;

                    //then use the if else loop to make lines 2-5 the friends top movie
                } else if (lineNumber >= 2 && lineNumber <= 5) {

                    if (lineNumber - 2 < friendtop4.size()) {
                        friendtop4.set(lineNumber - 2, line);
                    } else {
                        friendtop4.add(line);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}