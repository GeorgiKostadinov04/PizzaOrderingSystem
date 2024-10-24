public class User {
    protected String username;
    protected String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public boolean login(String username, String password) throws InvalidLoginException{
        if(getUsername().equals(username) && getPassword().equals(password)){
            System.out.println("Login successful! User - " + getUsername());
            return true;
        }
        else{
            throw new InvalidLoginException("Invalid username or password");
        }
    }

    public void logout(){
        System.out.println(getUsername() + " has logged out.");
    }
}
