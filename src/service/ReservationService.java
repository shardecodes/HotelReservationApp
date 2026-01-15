package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static final ReservationService INSTANCE = new ReservationService();
    public static ReservationService getInstance() {
        return INSTANCE;
    }
    private  static final Map<String, IRoom> rooms = new HashMap<>();
    private static final Set<Reservation> reservations = new HashSet<>();

    public void addRoom(IRoom room){
        if (room == null){
            throw new IllegalArgumentException("Room can't be null");
        }
    rooms.put(room.getRoomNumber(), room);
    }
public IRoom getARoom(String roomId){

        return rooms.get(roomId);
}

    public Collection<IRoom> getAllRooms(){
        return rooms.values();
    }

    public Collection<IRoom> findRooms(Date checkIn, Date checkOut) {
        Collection<IRoom> availableRooms = new HashSet<>();
        for (IRoom room : rooms.values()) {
            if (isRoomAvailable(room, checkIn, checkOut)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    private boolean isRoomAvailable(IRoom room, Date checkIn, Date checkOut) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().equals(room)) {
                // Check for overlap
                if (!(checkOut.before(reservation.getCheckInDate()) || checkIn.after(reservation.getCheckOutDate()))) {
                    return false;
                }
            }
        }
        return true;
    }

public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
       Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
       reservations.add(reservation);
       return reservation;
}
public Collection<Reservation> getCustomersReservation(Customer customer){
        Collection<Reservation> customerReservations = new HashSet<>();

        for(Reservation reservation : reservations) {
            if(reservation.getCustomer().equals(customer)){
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
}
    public void printAllReservation(){
        for(Reservation reservation: reservations){
            System.out.println(reservation);
        }
}

public Collection<Reservation> getAllReservations(){
        return reservations;
}

}
