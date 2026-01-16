package com.company.usermanagement.controller;

import com.company.usermanagement.model.User;
import com.company.usermanagement.service.UserService;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private final UserService service = new UserService();
    private final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new Controller().run();
    }

    public void run() {
        while (true) {
            System.out.println("\nEnter your choice:");
            System.out.println("1. Add new user");
            System.out.println("2. List all users");
            System.out.println("3. Delete user by ID");
            System.out.println("4. Show user by ID");
            System.out.println("5. Exit");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input, try again.");
                continue;
            }

            switch (choice) {
                case 1 -> addUser();
                case 2 -> listAllUsers();
                case 3 -> deleteUserById();
                case 4 -> showUserById();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    // ✅ Add user
    private void addUser() {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Enter name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter email: ");
            String email = sc.nextLine().trim();

            System.out.print("Enter age: ");
            int age = Integer.parseInt(sc.nextLine().trim());

            User user = new User(id, name, email, age);

            boolean added = service.addUser(user);
            System.out.println(added ? "User added successfully." : "Failed to add user.");

        } catch (Exception e) {
            System.out.println("Invalid input. User not added.");
        }
    }

    // ✅ List all users
    private void listAllUsers() {
        List<User> users = service.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.println("\n--- Users List ---");
        for (User user : users) {
            System.out.printf(
                "ID: %d | Name: %s | Email: %s | Age: %d%n",
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge()
            );
        }
    }

    // ✅ Delete user by ID
    private void deleteUserById() {
        try {
            System.out.print("Enter user ID to delete: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            boolean deleted = service.deleteById(id);
            System.out.println(deleted ? "User deleted." : "User not found.");

        } catch (Exception e) {
            System.out.println("Invalid ID.");
        }
    }

    // ✅ Show user by ID
    private void showUserById() {
        try {
            System.out.print("Enter user ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            User user = service.getUserById(id);
            if (user == null) {
                System.out.println("User not found.");
            } else {
                System.out.printf(
                    "ID: %d | Name: %s | Email: %s | Age: %d%n",
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getAge()
                );
            }

        } catch (Exception e) {
            System.out.println("Invalid ID.");
        }
    }
}
