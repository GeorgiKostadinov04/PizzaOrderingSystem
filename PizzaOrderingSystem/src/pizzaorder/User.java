package pizzaorder;

import customexceptions.InvalidLoginException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class User {
    protected String username;
    private String salt;
    private String hashedPassword;

    public User(String username, char[] password){
        this.username = username;
        this.salt = generateSalt();
        this.hashedPassword = hashPassword(password, this.salt);
    }

    public String getUsername(){
        return username;
    }



    public boolean login(String username, char[] password) throws InvalidLoginException{
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

    public void setPassword(char[] password) {
        this.salt = generateSalt();
        this.hashedPassword = hashPassword(password, this.salt);
    }

    public boolean checkPassword(char[] password) {
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

    private String hashPassword(char[] password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");


            byte[] saltBytes = salt.getBytes();
            byte[] passwordBytes = new String(password).getBytes();
            byte[] combined = new byte[saltBytes.length + passwordBytes.length];

            System.arraycopy(saltBytes, 0, combined, 0, saltBytes.length);
            System.arraycopy(passwordBytes, 0, combined, saltBytes.length, passwordBytes.length);

            // Hash the combined salt and password
            byte[] hashedBytes = md.digest(combined);

            // Convert to hexadecimal string
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
