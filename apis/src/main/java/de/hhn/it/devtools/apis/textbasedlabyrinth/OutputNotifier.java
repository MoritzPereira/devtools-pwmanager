package de.hhn.it.devtools.apis.textbasedlabyrinth;

/**
 * Callback handler, implementing Callback interface
 */
public class OutputNotifier implements OutputListener{

    public OutputNotifier(){
        
    }


    @Override
    public void sendOutputRoom(String output) {
        System.out.println(output);

    }

    @Override
    public void sendOutputPlayer(String output) {
        System.out.println(output);

    }

    @Override
    public void sendOutputInventory(String output) {
        System.out.println(output);

    }

    @Override
    public void sendOutputNavigation(String output) {
        sendOutputPlayer(output);
    }

    @Override
    public void sendOutputPlayerInteract(String output) {
        sendOutputPlayer(output);
    }
}
