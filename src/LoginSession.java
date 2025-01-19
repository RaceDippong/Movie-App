public class LoginSession {
    //create another class to allow the user to log back in without having to re-enter username and password
    //declare as private booleans/strings for start of constructor
    private boolean loggedIn;
    private String username;
    private String password;


    //create a separate method to return the loggedin value
    public boolean loggedIn() {
        return loggedIn;
    }

    //create the this. part of the constructor with each of the previously privated variables
    public void login(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = true;
    }

    //create a method to logout and return the false value
    public void logout(){
        this.loggedIn = false;
    }

    //create a method to return username and password
    public String username() {
        return username;
    }
    public String password() {
        return password;
    }
}
