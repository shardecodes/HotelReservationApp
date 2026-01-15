package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

public class AdminResource {
    private static final AdminResource adminResource = new AdminResource();
    public static AdminResource getInstance(){
        return adminResource;
    }

    private final CustomerService customerService =  CustomerService.getInstance();
    private final ReservationService reservationService = ReservationService.getInstance();

    public AdminResource() {
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    public void addRoom(List<IRoom> rooms){
        for(IRoom room : rooms){
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
       Collection<Reservation> allReservations = reservationService.getAllReservations();

       if(allReservations.isEmpty()){
           System.out.println("No reservations found");
           return;
       }
        for (Reservation reservation : allReservations){
            System.out.println(reservation);
        }
    }
}
