package de.hhn.it.devtools.apis.textbasedlabyrinth;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Room Class for the Game, defines the Rooms of the Game
 * with assigned next-door Rooms and Items inside of it.
 */
public class Room {


  private HashMap<Integer, Item> items;
  private int roomId;

  private String description;

  public Room toTheNorth;
  public Room toTheSouth;
  public Room toTheEast;
  public Room toTheWest;

  public Boolean isNorthAssigned;
  public Boolean isSouthAssigned;
  public Boolean isEastAssigned;
  public Boolean isWestAssigned;

  private Door westDoor;
  private Door eastDoor;
  private Door northDoor;
  private Door southDoor;
  private boolean hasDoorN;
  private boolean hasDoorW;
  private boolean hasDoorE;
  private boolean hasDoorS;

  public boolean isExit;

  /**
   * Constructor of Room.
   *
   * @param id ID of the room
   */
  public Room(int id, String description) {
    this.roomId = id;
    this.isNorthAssigned = false;
    this.isSouthAssigned = false;
    this.isEastAssigned = false;
    this.isWestAssigned = false;
    this.hasDoorS = false;
    this.hasDoorE = false;
    this.hasDoorN = false;
    this.hasDoorW = false;

    items = new HashMap<>();
    this.description = description;
  }

  public void addItem(Item item) {
    items.put(item.getItemId(), item);

  }

  /**
   * Gets the doors for the new Room.
   */
  public void setDoors() {
    Random random = new Random();
    int a = 0;

    if (isSouthAssigned) {
      if (toTheSouth.hasSouthDoor()) {
        southDoor = toTheSouth.getSouthDoor();
      } else {
        southDoor = new Door();
      }
      hasDoorS = true;
    } else {
      //This is a demo for a false door,
      //a door that opens and reveals a wall.
      a = random.nextInt(0, 4);
      if (a == 1) {
        southDoor = new Door();
        southDoor.isFake();
        isSouthAssigned = true;
      }
    }
    if (isWestAssigned) {
      if (toTheWest.hasDoorWest()) {
        westDoor = toTheWest.getWestDoor();
      } else {
        westDoor = new Door();
      }
      hasDoorW = true;
    }
    if (isNorthAssigned) {
      if (toTheNorth.hasDoorNorth()) {
        northDoor = toTheNorth.getNorthDoor();
      } else {
        northDoor = new Door();
      }
      hasDoorN = true;
    }
    if (isEastAssigned) {
      if (toTheEast.hasDoorEast()) {
        eastDoor = toTheEast.getEastDoor();
      } else {
        eastDoor = new Door();
      }
      hasDoorE = true;
    }

  }

  public void removeItem(int itemId) {
    items.remove(itemId);
  }

  public int getRoomId() {
    return roomId;
  }

  public String getDescription() {
    return description;
  }

  public Door getWestDoor() {
    return westDoor;
  }

  public Door getEastDoor() {
    return eastDoor;
  }

  public Door getNorthDoor() {
    return northDoor;
  }

  public Door getSouthDoor() {
    return southDoor;
  }

  public boolean hasSouthDoor() {
    return hasDoorS;
  }

  public boolean hasDoorNorth() {
    return hasDoorN;
  }

  public boolean hasDoorWest() {
    return hasDoorW;
  }

  public boolean hasDoorEast() {
    return hasDoorE;
  }

  /**
   * Get Number od foos.
   *
   * @return Number od Doors.
   */
  public int getNumberOfDoors() {
    int a = 0;
    if (hasDoorS) {
      a++;
    }
    if (hasDoorW) {
      a++;
    }
    if (hasDoorE) {
      a++;
    }
    if (hasDoorN) {
      a++;
    }
    return a;
  }



  /**
   * Searching for an item in the room.
   *
   * @return item
   * @throws NullPointerException room does not have items
   */
  public List<Item> search() throws NullPointerException {
    if (items == null) {
      throw new NullPointerException("Room could not hold items.");
    }
    boolean fail = false; // May need later.

    List<Item> items1 = items.values().stream().toList();

    if (items1.isEmpty()) {
      Item item = new Item(10077001, "NoItem", "opens door");
      ArrayList<Item> items2 = new ArrayList<>();
      items2.add(item);
      return items2;
    } else {
      return items1;
    }
  }

  /**
   * Set a Room as the Room next door for the current Room and vis versa.
   *
   * @param room Room to be assigned as the next door Room
   * @param isEast Boolean check to see if the new Room will be to the east of current room
   * @param isWest Boolean check to see if the new Room will be to the west of current room
   * @param isNorth Boolean check to see if the new Room will be to the north of current room
   */
  public void setNextDoorRoom(Room room, Boolean isEast, Boolean isWest, Boolean isNorth) {
    if (isEast && !isWest && !isNorth && !isEastAssigned) {
      this.toTheEast = room;
      this.isEastAssigned = true;
      room.toTheWest = this;
      room.isWestAssigned = true;
    } else if (isWest && !isEast && !isNorth && !isWestAssigned) {
      this.toTheWest = room;
      this.isWestAssigned = true;
      room.toTheWest = this;
      room.isEastAssigned = true;
    } else if (isNorth && !isEast && !isWest && !isNorthAssigned) {
      this.toTheNorth = room;
      this.isNorthAssigned = true;
      room.toTheSouth = this;
      room.isSouthAssigned = true;
    } else if (!isNorth && !isEast && !isWest && !isSouthAssigned) {
      this.toTheSouth = room;
      this.isSouthAssigned = true;
      room.toTheNorth = this;
      room.isNorthAssigned = true;
    }
  }

  public void setExit(){

  }

}
