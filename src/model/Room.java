package model;

import java.util.Objects;

public class Room implements IRoom{
     String roomNumber;
     Double price;
     RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    public String getRoomNumber(){
       return roomNumber;
   }

   public Double getRoomPrice(){
       return price;
   }

   public RoomType getRoomType(){
       return roomType;
   }

   public boolean isFree(){
       return false;
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }


    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                '}';
    }
}
