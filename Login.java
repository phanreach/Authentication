import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();

        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();

        boolean loggedIn = Authentication.authenticateUser(username, password);

        if (loggedIn) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password. Login failed.");
        }

        scanner.close();
    }
}
