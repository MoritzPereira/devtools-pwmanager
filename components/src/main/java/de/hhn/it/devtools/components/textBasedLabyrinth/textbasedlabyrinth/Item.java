package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;


/**
 * Creates Items which can be found in Rooms and can be used to open doors.
 */
public class Item {

  private final int itemId;
  private final String name;
  private final String info;

  /**
   * Every Item has an Item id, a name and a information.
   *
   */
  public Item(int id, String name, String info) {

    this.itemId = id;
    this.name = name;
    this.info = info;
  }

  public String getName() {

    return name;
  }


  public int getItemId() {

    return itemId;
  }

  public String getInfo() {

    return info;
  }




}
