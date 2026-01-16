package api;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public void start() {
        boolean running = true;

        while (running) {
            printMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createAccount();
                    break;
                case "2":
                    findAndReserveRoom();
                    break;
                case "3":
                    seeMyReservations();
                    break;
                case "4":
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.start();
                    break;
                case "5":
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input. Please select a number from 1 to 5. ");


            }
        }
    }

    private void printMainMenu() {
        System.out.println("\nWelcome to the Hotel Reservation App!");
        System.out.println("1.Create an account ");
        System.out.println("2.Find and reserve a room");
        System.out.println("3.See my reservations ");
        System.out.println("4.Admin");
        System.out.println("5.Exit");
        System.out.print("Please select an option: ");
    }

    private void findAndReserveRoom() {
        try {
            System.out.print("Enter check-in date (MM/dd/yyyy): ");
            Date checkIn = dateFormat.parse(scanner.nextLine());

            System.out.print("Enter check-out date (MM/dd/yyyy): ");
            Date checkOut = dateFormat.parse(scanner.nextLine());

            if (!checkOut.after(checkIn)) {
                System.out.println("Check-out date must be after check-in date.");
                return;
            }

            Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);

            if (availableRooms.isEmpty()) {
                System.out.println("No rooms available for these dates.");
                return;
            }

            System.out.println("Available rooms:");
            for (IRoom room : availableRooms) {
                System.out.println(room);
            }

            System.out.print("Enter your email to book a room: ");
            String email = scanner.nextLine();
            Customer customer = hotelResource.getCustomer(email);

            if (customer == null) {
                System.out.println("No account found with that email. Please create an account first.");
                return;
            }

            System.out.print("Enter room number to book: ");
            String roomNumber = scanner.nextLine();
            IRoom roomToBook = hotelResource.getRoom(roomNumber);

            if (roomToBook == null) {
                System.out.println("Invalid room number.");
                return;
            }

            Reservation reservation = hotelResource.bookARoom(email, roomToBook, checkIn, checkOut);
            System.out.println("Room successfully booked! Details:");
            System.out.println(reservation);

        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use MM/dd/yyyy.");
        }
    }


    private void seeMyReservations() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        Customer customer = hotelResource.getCustomer(email);
        if (customer == null) {
            System.out.println("No account found with that email.");
            return;
        }

        Collection<Reservation> reservations = hotelResource.getCustomerReservations(email);
        if (reservations.isEmpty()) {
            System.out.println("You have no reservations.");
        } else {
            System.out.println("Your reservations:");
            for (Reservation res : reservations) {
                System.out.println(res);
            }
        }
    }


    private void createAccount() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        hotelResource.createACustomer(email,firstName, lastName);
        System.out.println("Account successfully created for " + firstName + " " + lastName);
    }
}



