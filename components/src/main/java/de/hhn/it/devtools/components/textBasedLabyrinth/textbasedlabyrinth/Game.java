package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;


import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.NoSuchItemFoundException;
import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.RoomFailedException;
import java.util.ArrayList;
import java.util.List;

/**
 * Game class.
 */
public class Game implements GameService {

  public Room currentRoom;
  public Player player;
  public Layout currentLayout;
  public OutputListener outputListener;
  public ArrayList<Layout> layouts;
  public Map map;

  /**
   * Constructor for game.
   */
  public Game(OutputListener listener) {
    this.player = new Player("Placeholder");
    outputListener = listener;
  }


  public void move(Direction direction) throws RoomFailedException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }
    boolean succeeded = false;
    String message = "";

    if (direction.equals(Direction.SOUTH)) {
      if (currentRoom.isSouthAssigned.equals(true)) {
        Door checkDoor = currentRoom.getSouthDoor();
        message = checkDoor.open();
        if (!checkDoor.checkIfLocked()) {
            currentRoom = currentRoom.toTheSouth;
            player.setCurrentRoomOfPlayer(currentRoom);
          }
        } else {
        throw new RoomFailedException("No room found in the southern direction.");
      }
      succeeded = true;
    }
    if (direction.equals(Direction.WEST)) {
      if (currentRoom.isWestAssigned.equals(true)) {
        Door checkDoor = currentRoom.getWestDoor();
        message = checkDoor.open();
        if (!checkDoor.checkIfLocked()) {
          currentRoom = currentRoom.toTheWest;
          player.setCurrentRoomOfPlayer(currentRoom);
        }
      } else {
        throw new RoomFailedException("No room found in the western direction.");
      }
    }
    if (direction.equals(Direction.EAST)) {
      if (currentRoom.isEastAssigned.equals(true)) {
        Door checkDoor = currentRoom.getEastDoor();
        message = checkDoor.open();
        if (checkDoor.checkIfLocked()) {
          currentRoom = currentRoom.toTheEast;
          player.setCurrentRoomOfPlayer(currentRoom);
        }
      } else {
        throw new RoomFailedException("No room found in the eastern direction.");
      }
    }
    if (direction.equals(Direction.NORTH)) {
      if (currentRoom.isNorthAssigned.equals(true)) {
        Door checkDoor = currentRoom.getNorthDoor();
        message = checkDoor.open();
        if (!checkDoor.checkIfLocked()) {
          currentRoom = currentRoom.toTheNorth;
          player.setCurrentRoomOfPlayer(currentRoom);
        }
      } else {
        throw new RoomFailedException("No room found in the northern direction.");
      }
    }
    outputListener.sendOutputPlayer(message);

    if (!succeeded) {
      throw new IllegalArgumentException("Direction was not valid.");
    }
  }

  /**
   * Inspects the room for doors.
   *
   * @param direction gets doors in all directions.
   * @throws IllegalArgumentException direction should not be null.
   */
  public String inspect(Direction direction) throws IllegalArgumentException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }
    boolean succeeded = false;
    String message = "";

    if (direction.equals(Direction.NORTH)) {
      message = currentRoom.getNorthDoor().getInspectMessage();
      succeeded = true;
    }
    if (direction.equals(Direction.WEST)) {
      message = currentRoom.getWestDoor().getInspectMessage();
      succeeded = true;
    }
    if (direction.equals(Direction.EAST)) {
      message = currentRoom.getEastDoor().getInspectMessage();
      succeeded = true;
    }
    if (direction.equals(Direction.SOUTH)) {
      message = currentRoom.getSouthDoor().getInspectMessage();
      succeeded = true;
    }
    outputListener.sendOutputNavigation(message);

    if (!succeeded) {
      throw new IllegalArgumentException("Direction was not valid.");
    }
    return message;
  }

  /**
   * Player tries to interact with a door/direction.
   *
   * @param direction gets the direction to the next door.
   * @param item item player uses.
   * @throws IllegalArgumentException direction not null.
   */
  public String interaction(Direction direction, Item item) throws IllegalArgumentException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction should not be null.");
    }
    if (item == null) {
      throw new IllegalArgumentException("Item should not be null.");
    }
    boolean succeeded = false;
    String successMessage = "";

    if (direction.equals(Direction.NORTH)) {
      boolean unlocked = currentRoom.getNorthDoor().unlock(item);
      succeeded = true;
      Door door = currentRoom.getNorthDoor();
      if (unlocked) {
        successMessage = door.getPuzzle().getUnlockMessage();
      } else {
        successMessage = door.getPuzzle().getLockedMessage();

      }
    }
    if (direction.equals(Direction.WEST)) {
      boolean unlocked = currentRoom.getWestDoor().unlock(item);
      succeeded = true;
      Door door = currentRoom.getWestDoor();
      if (unlocked) {
        successMessage = door.getPuzzle().getUnlockMessage();
      } else {
        successMessage = door.getPuzzle().getLockedMessage();
      }
    }
    if (direction.equals(Direction.EAST)) {
      boolean unlocked = currentRoom.getEastDoor().unlock(item);
      succeeded = true;
      Door door = currentRoom.getEastDoor();
      if (unlocked) {
        successMessage = door.getPuzzle().getUnlockMessage();
      } else {
        successMessage = door.getPuzzle().getLockedMessage();
      }
    }
    if (direction.equals(Direction.SOUTH)) {
      boolean unlocked = currentRoom.getSouthDoor().unlock(item);
      succeeded = true;
      Door door = currentRoom.getSouthDoor();
      if (unlocked) {
        successMessage = door.getPuzzle().getUnlockMessage();
      } else {
        successMessage = door.getPuzzle().getLockedMessage();
      }
    }
    outputListener.sendOutputPlayerInteract(successMessage);

    if (!succeeded) {
      throw new IllegalArgumentException("Direction was not valid.");
    }

    return successMessage;
  }

  /**
   * Gets the next room.
   * It gives a list. Whether this list will be given to the main field in the ui or appear as a popup
   * is not decided yet.
   *
   * @throws RoomFailedException description to room
   *
   */
  public List<Item> searchRoom() throws RoomFailedException {
    List<Item> items = new ArrayList<>();

    try {
      items = itemSearcher();
    } catch (NullPointerException n) {
      throw new RoomFailedException(n.getMessage());
    }

    if (items.size() == 1) {
      if (items.get(0).getItemId() == 10077001) {
        items = null;
      }
    }
    return items;

  }

  /**
   * Gets Item for player.
   *
   * @param itemId gets an item
   * @return gives item to player
   * @throws NoSuchItemFoundException if item not found.
   * @throws NullPointerException if item cant be null.
   */
  public Item pickUpItem(int itemId) throws NoSuchItemFoundException {

    List<Item> items = new ArrayList<>();


    try {
      items = itemSearcher();
    } catch (NullPointerException n) {
      throw new NoSuchItemFoundException(n.getMessage());
    }
   Item searchedItem = null;
    for (Item item : items) {
      if (item.getItemId() == itemId) {
        searchedItem = item;
      }
    }

    if (searchedItem == null) {
      throw new NoSuchItemFoundException("The item was not found.");
    } else {
      player.addItem(searchedItem);
      outputListener.sendOutputPlayer(searchedItem.getName());
      return searchedItem;
    }
  }


  /**
   * Method to remove an item from the player inventory.
   *
   * @param itemId the id of the item to be removed.
   * @return the message, which is about the success or failure of the operation.
   */
  public String dropItem(int itemId) {
    String message = "";
    try {
      player.removeItem(itemId);
      message = "You lay the item carefully on the ground.";
    } catch (NoSuchItemFoundException n) {
      message = n.getMessage();
    }
    outputListener.sendOutputPlayer(message);
    return message;
  }


  @Override
  public void inspectItemInInventoryOfPlayer(int itemId) throws NoSuchItemFoundException {
    String message = null;

    try {
      message = player.getItem(itemId).getInfo();
    } catch (NoSuchItemFoundException i) {
      throw new NoSuchItemFoundException(i.getMessage());
    }

    outputListener.sendOutputInventory(message);
  }

  @Override
  public boolean setPlayerName(String name) {
    boolean success = true;
    if (name.isEmpty() || name.isBlank()) {
      success = false;
    }

    if (success) {
      player.setName(name);
    }

    return success;
  }

  /**
   * Message to be given to the player after (probably) every action
   * and every time the player moves between rooms.
   */
  public String check() {
    String message = "You find yourself in " + currentRoom.getDescription();
    message = message + ("You can search the room or move on.");
    outputListener.sendOutputRoom(message);
    return message;
  }

  /**
   * Text to be given to the player at the start of the game.
   */
  public String startText() {
    String message = "You are " + player.getName()
            + " and you are in the depths of a labyrinth.";
    outputListener.sendOutputRoom(message);
    return message;
  }


  private List<Item> itemSearcher() throws NullPointerException {
    List<Item> items = new ArrayList<>();

    try {
      items = currentRoom.search();
    } catch (NullPointerException n) {
      throw new NullPointerException(n.getMessage());
    }

    return items;
  }

  /**
   * Setter for current Layout
   * @param newMap Map to be selected
   * @param newSeed Seed for the Map
   */
  public void setCurrentLayout(Map newMap, Seed newSeed){
    this.currentLayout = new Layout(player, newMap, newSeed);
    this.currentRoom = currentLayout.startRoom;
  }
}
