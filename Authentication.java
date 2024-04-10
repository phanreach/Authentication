import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Authentication {
    public static boolean authenticateUser(String username, String password) {
        try (Scanner scanner = new Scanner(new File("Data.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("/");
                String storedUsername = parts[0];
                String storedPassword = parts[1];

                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: User database file not found.");
            e.printStackTrace();
        }

        return false;
    }

    public static void registerUser(String username, String password) {
        if (isUserRegistered(username)) {
            System.out.println("Username already exists. Please choose a different one.");
            return;
        }

        User user = new User(username, password);
        saveUserToDatabase(user);
    }

    public static boolean isUserRegistered(String username) {
        try (Scanner scanner = new Scanner(new File("Data.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("/");
                String storedUsername = parts[0];
                if (username.equals(storedUsername)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: User database file not found.");
            e.printStackTrace();
        }
        return false;
    }
    
    private static void saveUserToDatabase(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Data.txt", true))) {
            writer.println(user.getUsername() + "/" + user.getPassword());
            System.out.println("User registered successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while registering the user.");
            e.printStackTrace();
        }
    }
}
