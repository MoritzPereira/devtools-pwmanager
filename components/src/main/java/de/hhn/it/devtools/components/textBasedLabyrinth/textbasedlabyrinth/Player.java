package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;


import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.NoSuchItemFoundException;
import java.util.HashMap;

/**
 * Player Class for the Game, the Player can move to other Rooms and interact with environment.
 */
public class Player {

  private String name;
  private HashMap<Integer, Item> inventory;
  public Room currentRoomOfPlayer;

  /**
   * Constructor of Player.
   *
   * @param name name of the Player
   */
  public Player(String name) {
    this.name = name;
    inventory = new HashMap<>();
  }


  public void addItem(Item item) {

    inventory.put(item.getItemId(), item);
  }

  /**
   * Javadoc.
   *
   * @param itemId new Item
   * @throws NoSuchItemFoundException Exception
   */
  public void removeItem(int itemId) throws NoSuchItemFoundException {
    if (!inventory.containsKey(itemId)) {
      throw new NoSuchItemFoundException();
    }
    inventory.remove(itemId);
  }

  /**
   * Javadoc.
   *
   * @param itemId Item
   * @return return
   * @throws NoSuchItemFoundException exception
   */
  public Item getItem(int itemId) throws NoSuchItemFoundException {
    if (!inventory.containsKey(itemId)) {
      throw new NoSuchItemFoundException();
    }
    return inventory.get(itemId);
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter for currentRoomOfPlayer.
   *
   * @return Room where the Player is inside right now
   */
  public Room getCurrentRoomOfPlayer() {
    return currentRoomOfPlayer;
  }

  /**
   * Setter for currentRoomOfPlayer.
   * Sets the selected Room as the Room the Player is in
   *
   * @param currentRoomOfPlayer Room with the Player inside
   */
  public void setCurrentRoomOfPlayer(Room currentRoomOfPlayer) {
    this.currentRoomOfPlayer = currentRoomOfPlayer;
  }

  public void reset() {
    inventory.clear();
  }
}
