package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.exceptions.InvalidSeedException;
import java.util.ArrayList;

/**
 * This is a unique number String.
 */
public class Seed {

  private ArrayList<Integer> seed;


  /**
   * The seed should be an array list with integer values of the length 2.
   * @param seedInteger
   * @throws InvalidSeedException
   */
  public Seed(ArrayList<Integer> seedInteger) throws InvalidSeedException {
    if (seedInteger.size() < 1) {
      throw new InvalidSeedException("Array is too small.");
    }
    if (seedInteger.size() > 2) {
      throw new InvalidSeedException("Array is longer than permitted.");
    }

    seed = seedInteger;

    if (seed.size() < 2) {
      while (seed.size() < 2) {
        seed.add(seed.size() - 1, 0);
      }
    }

  }


  public ArrayList<Integer> getSeed() {
    return seed;
  }
}




/**
 * stringParser = seed.split("(?!^)");
 *     ArrayList<Integer> intParser = new ArrayList<Integer>();
 *
 *     for (String s : stringParser) {
 *       intParser.add(Integer.parseInt(s));
 *     }
 *
 *     while(intParser.size() < minDigitNumber){
 *       intParser.add(0);
 *     }
 *
 *     this.roomParser = intParser;
 *   }
 *
 *   /**
 *    * Constructor for Seed without any input.
 *    *
 * public Seed(){
        *Random rand=new Random();
        *while(roomParser.size()<minDigitNumber){
        *roomParser.add(rand.nextInt(9));
        *}
        *}
        *
        *public int getTotalRoomCount(){
        *return roomParser.get(0)+roomParser.get(1)+roomParser.get(2);
        *}
        *
        *public ArrayList<Integer> getRoomParser(){
        *return roomParser;
        *}
 */
