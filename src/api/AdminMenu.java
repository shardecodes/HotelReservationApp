package api;

import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AdminResource adminResource = AdminResource.getInstance();

    public void start(){
        boolean running = true;

        while(running){
            printAdminMenu();
            String choice= scanner.nextLine();

        switch(choice){
            case"1":
                seeAllCustomers();
                break;
            case"2":
                seeAllRooms();
                break;
            case"3":
                seeAllReservations();
                break;
            case"4":
                addARoom();
                break;
            case"5":
                running = false;
                break;
            default:
                System.out.println("Invalid input. Please select a number from 1 to 5");
        }
        }
    }

    private void printAdminMenu() {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Back to Main Menu");
        System.out.print("Please select an option: ");
    }

    // --- Admin actions ---

    private void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("All customers:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    private void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            System.out.println("All rooms:");
            for (IRoom room : rooms) {
                System.out.println(room);
            }
        }
    }

    private void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    private void addARoom() {
        List<IRoom> roomsToAdd = new ArrayList<>();
        boolean adding = true;

        while (adding) {
            System.out.print("Enter room number: ");
            String roomNumber = scanner.nextLine();

            System.out.print("Enter price per night: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter room type (SINGLE/DOUBLE): ");
            String typeInput = scanner.nextLine().toUpperCase();
            RoomType roomType = RoomType.valueOf(typeInput);

            roomsToAdd.add(new Room(roomNumber, price, roomType));

            System.out.print("Add another room? (y/n): ");
            String again = scanner.nextLine();
            adding = again.equalsIgnoreCase("y");
        }

        adminResource.addRoom(roomsToAdd);
        System.out.println("Rooms added successfully!");
    }

}


