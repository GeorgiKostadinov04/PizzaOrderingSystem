package pizzaorder;

import customexceptions.InvalidLoginException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class User {
    protected String username;
    private String salt;
    private String hashedPassword;

    public User(String username, String password){
        this.username = username;
        this.salt = generateSalt();
        this.hashedPassword = hashPassword(password, this.salt);
    }

    public String getUsername(){
        return username;
    }



    public boolean login(String username, String password) throws InvalidLoginException{
        if(getUsername().equals(username) && checkPassword(password)){
            System.out.println("Login successful! Hello " + getUsername());
            return true;
        }
        else{
            throw new InvalidLoginException("Invalid username or password");
        }
    }

    public void logout(){
        System.out.println(getUsername() + " has logged out.");
    }

    public void setPassword(String password) {
        this.salt = generateSalt();
        this.hashedPassword = hashPassword(password, this.salt);
    }

    public boolean checkPassword(String password) {
        return hashPassword(password, this.salt).equals(this.hashedPassword);
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : saltBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: Hashing algorithm not found.");
        }
    }
}
