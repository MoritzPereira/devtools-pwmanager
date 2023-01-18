package de.hhn.it.devtools.apis.textbasedlabyrinth;

/**
 * Interface for callback method
 */
public interface OutputListener {


    /**
     * Output to what is happening in the current room and what state it is right now.
     * @param output String to be output.
     */
    void sendOutputRoom(String output);

    /**
     * Output about the state of the player and the actions done by him.
     * Picking items up and dropping them is currently considered a player action, not an inventory action.
     * @param output String to be output.
     */
    void sendOutputPlayer(String output);


    /**
     * Output about the state of the inventory and items inspected inside it.
     * Mainly for inspecting the items.
     * @param output String to be output.
     */
    void sendOutputInventory(String output);


    /**
     * Output about where the player can go.
     * @param output string to be output.
     */
    void sendOutputNavigation(String output);


    /**
     * Output collected when player interacts with a door or obstacle.
     * @param output string to be output.
     */
    void sendOutputPlayerInteract(String output);
}
