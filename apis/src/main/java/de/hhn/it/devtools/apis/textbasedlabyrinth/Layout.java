package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.ArrayList;
import java.util.List;

/**
 * Layout defines the dungeon layout of the game and is determined by the selected map, which changes with the different
 * possible maps available
 */
public class Layout {

  public List<Room> allRooms;
  public int maxRoomCount = 13;
  public List<Layout> allLayouts;
  public Room startRoom;
  public Player player;
  public Map map;
  public int roomCount;

  /**
   * Constructor of Layout Class
   *
   * @param player the current player of the game
   * @param map the current map design of the game
   */
  public Layout(Player player, Map map, Seed seed) {

    this.map = map;
    roomCount = 1;
    String exampleDescription = "A dark, cold room.";
    this.player = player;


    // create a List for all the Rooms generated
    this.allRooms = new ArrayList<>();

    // create as many rooms as maxRoomCount allows
    int j = 0;
    while (allRooms.size() <= maxRoomCount) {
      Room newRoom = new Room(j, exampleDescription);
      this.allRooms.add(newRoom);
      j++;
    }

    // set the startroom up and place player inside
    this.startRoom = allRooms.get(0);
    player.setCurrentRoomOfPlayer(startRoom);

    if (map.equals(Map.Grave_of_the_Mad_King)) {
      startRoom.setNextDoorRoom(allRooms.get(1), false, true, false);
      allRooms.get(1).setNextDoorRoom(allRooms.get(2), false, true, false);
      allRooms.get(1).setNextDoorRoom(allRooms.get(3), false, false, true);
      startRoom.setNextDoorRoom(allRooms.get(4), false, false, true);
      allRooms.get(4).setNextDoorRoom(allRooms.get(5), false, false, true);
      allRooms.get(5).setNextDoorRoom(allRooms.get(6), false, false, true);
      allRooms.get(6).setNextDoorRoom(allRooms.get(7), true, false, false);
      allRooms.get(7).setNextDoorRoom(allRooms.get(8), false, false, false);
      startRoom.setNextDoorRoom(allRooms.get(9), true, false, false);
      allRooms.get(9).setNextDoorRoom(allRooms.get(10), true, false, false);
      allRooms.get(10).setNextDoorRoom(allRooms.get(11), true, false, false);
      allRooms.get(11).setNextDoorRoom(allRooms.get(12), false, false, true);
    }
    else if (map.equals(Map.Ancient_Dungeon)) {
      startRoom.setNextDoorRoom(allRooms.get(1), false, true, false);
      allRooms.get(1).setNextDoorRoom(allRooms.get(4),false, true, false);
      allRooms.get(4).setNextDoorRoom(allRooms.get(5),false, false, true);
      allRooms.get(5).setNextDoorRoom(allRooms.get(7),false, false, true);
      allRooms.get(7).setNextDoorRoom(allRooms.get(8),true, false, false);
      allRooms.get(8).setNextDoorRoom(allRooms.get(6),true, false, false);
      startRoom.setNextDoorRoom(allRooms.get(2), false, false, true);
      allRooms.get(2).setNextDoorRoom(allRooms.get(6),false, false, true);
      allRooms.get(6).setNextDoorRoom(allRooms.get(9),true, false, false);
      startRoom.setNextDoorRoom(allRooms.get(3),true, false, false);
      allRooms.get(3).setNextDoorRoom(allRooms.get(10),true, false, false);
      allRooms.get(10).setNextDoorRoom(allRooms.get(11),false, false, true);
      allRooms.get(11).setNextDoorRoom(allRooms.get(12),true, false, false);

    }
    else if (map.equals(Map.Unknown_Sewers)) {
      startRoom.setNextDoorRoom(allRooms.get(1), false, false, true);
      allRooms.get(1).setNextDoorRoom(allRooms.get(2), false, false, true);
      allRooms.get(2).setNextDoorRoom(allRooms.get(3), true, false, false);
      allRooms.get(2).setNextDoorRoom(allRooms.get(4), false, true, false);
      allRooms.get(2).setNextDoorRoom(allRooms.get(5), false, false, true);
      allRooms.get(5).setNextDoorRoom(allRooms.get(6), false, false, true);
      allRooms.get(6).setNextDoorRoom(allRooms.get(7), false, false, true);
      allRooms.get(7).setNextDoorRoom(allRooms.get(8), false, true, false);
      allRooms.get(7).setNextDoorRoom(allRooms.get(9), true, false, false);
      allRooms.get(7).setNextDoorRoom(allRooms.get(10), false, false, true);
      allRooms.get(10).setNextDoorRoom(allRooms.get(11), false, false, true);
      allRooms.get(11).setNextDoorRoom(allRooms.get(12), false, false, true);
    }


    for (Room room : allRooms) {
      room.setDoors();
    }

    if (map.equals(Map.Grave_of_the_Mad_King)) {
      int amountOfPuzzles = 1;
      if (seed.getSeed().get(0) < 5) {
        amountOfPuzzles = 2;
      }

      Item key1 = new Item(1, "ExitKey", "A big, old metal key.");
      allRooms.get(7).getSouthDoor().setPuzzle(key1);
      if (seed.getSeed().get(1) < 5) {
        allRooms.get(3).addItem(key1);
      } else {
        allRooms.get(2).addItem(key1);
      }

      if (amountOfPuzzles >= 2) {
        Item key2 = new Item(2, "Small rusty key", "A small, rusty key.");
        allRooms.get(4).getSouthDoor().setPuzzle(key2);
        if (seed.getSeed().get(1) < 3) {
          allRooms.get(9).addItem(key2);
        } else if (seed.getSeed().get(1) < 7 && seed.getSeed().get(1) > 3) {
          allRooms.get(2).addItem(key2);
        } else {
          allRooms.get(12).addItem(key2);
        }
      }



    } else if (map.equals(Map.Ancient_Dungeon)) {
      int amountOfPuzzles = 1;
      if (seed.getSeed().get(0) > 4) {
        amountOfPuzzles = 2;
      }
      if (seed.getSeed().get(0) > 8) {
        amountOfPuzzles = 3;
      }

      Item key1 = new Item(1, "ExitKey", "A metal key. It fits into your hand well.");
      allRooms.get(11).getWestDoor().setPuzzle(key1);
      if (seed.getSeed().get(1) < 5) {
        allRooms.get(4).addItem(key1);
      } else {
        allRooms.get(9).addItem(key1);
      }

      if (amountOfPuzzles >= 2) {
        Item key2 = new Item(2, "Small blue key", "Though clearly metal, this key has a blue tint");
        allRooms.get(1).getWestDoor().setPuzzle(key2);
        if (seed.getSeed().get(1) == 0) {
          allRooms.get(0).addItem(key2);
        } else if (seed.getSeed().get(1) < 5) {
          allRooms.get(10).addItem(key2);
        } else {
          allRooms.get(9).addItem(key2);
        }
      }

      if (amountOfPuzzles >= 3) {
        Item key3 = new Item(3, "Metal key",
                "On closer inspection, this key has a 10 itched into its metal.");
        allRooms.get(10).getEastDoor().setPuzzle(key3);
        if (seed.getSeed().get(1) < 5) {
          allRooms.get(7).addItem(key3);
        } else {
          allRooms.get(9).addItem(key3);
        }

      }



    } else if (map.equals(Map.Unknown_Sewers)) {
      int amountOfPuzzles = 1;
      if (seed.getSeed().get(0) < 5) {
        amountOfPuzzles = 2;
      }

      Item key1 = new Item(1, "ExitKey", "A big, old metal key. It feels wet.");
      allRooms.get(7).getNorthDoor().setPuzzle(key1);
      if (seed.getSeed().get(1) < 5) {
        allRooms.get(3).addItem(key1);
      } else {
        allRooms.get(5).addItem(key1);
      }

      if (amountOfPuzzles >= 2) {
        Item key2 = new Item(2, "Small rusty key", "A small, rusty key.");
        allRooms.get(7).getSouthDoor().setPuzzle(key2);
        if (seed.getSeed().get(1) < 5) {
          allRooms.get(4).addItem(key2);
        } else {
          allRooms.get(3).addItem(key2);
        }
      }

    }
  }


  public List<Room> getAllRooms () {
    return allRooms;
  }
}

/**
 *
 *
 *
 *
 * // West, starts when the first digit in the seed bigger than zero is
 *     if (sParser.get(0) > 0) {
 *       startRoom.setNextDoorRoom(allRooms.get(roomCount), false, true, false);
 *       roomCount++;
 *       // we count how long the straight corridor is(branches are excluded from this count)
 *       int westCount = 1;
 *       // when the first digit is higher than One we start a loop to give as many rooms as the digit dictates
 *        if (sParser.get(0) > 1) {
 *          while (westCount < sParser.get(0)) {
 *            allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount), false, true, false);
 *            roomCount++;
 *            westCount++;
 *          }
 *        }
 *     }
 *
 *     // North, starts when the second digit in the seed bigger than zero is
 *     if (sParser.get(1) > 0) {
 *       startRoom.setNextDoorRoom(allRooms.get(roomCount), false, false, true);
 *       roomCount++;
 *       int northCount = 1;
 *       // when the second digit is higher than One we start a loop to give as many rooms as the digit dictates
 *       if (sParser.get(1) > 1) {
 *         while (northCount < sParser.get(1)) {
 *           allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount), false, false, true);
 *           roomCount++;
 *           northCount++;
 *         }
 *       }
 *     }
 *
 *     // East, starts when the third digit in the seed bigger than zero is
 *     if (sParser.get(2) > 0) {
 *       startRoom.setNextDoorRoom(allRooms.get(roomCount), true, false, false);
 *       roomCount++;
 *       int eastCount = 1;
 *       // when the third digit is higher than One we start a loop to give as many rooms as the digit dictates
 *       if (sParser.get(2) > 1) {
 *         while (eastCount < sParser.get(2)) {
 *           allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount), true, false, false);
 *           roomCount++;
 *           eastCount++;
 *         }
 *       }
 *     }
 *
 *     // add branches that go up north, starts when the fourth digit is higher than Zero
 *     if (sParser.get(3) > 0) {
 *       // we will add a branch first on the xth room in the allRooms list, with x being the fourth digit of the Seed
 *       int nBranchCount = sParser.get(3);
 *       // we will repeat now this process x times, x still being fourth digit
 *       for (int i = 1; i < sParser.get(3); i++) {
 *         // we have to make sure though that we even have enough Rooms in the list to add branches to
 *         if (allRooms.size() > nBranchCount) {
 *           // and make sure the new Rooms are being added to the list
 *           Room newRoom = new Room(j, exampleDescription);
 *           this.allRooms.add(newRoom);
 *           j++;
 *
 *           allRooms.get(nBranchCount).setNextDoorRoom(newRoom, false, false, true);
 *           roomCount++;
 *           // and after every loop we multiple the count by 2, so if it was 2 before, it will be the 4th Room
 *           // in the allRooms list
 *           nBranchCount *= 2;
 *         }
 *       }
 *     }
 *
 *     // add branches that go up east, starts when the fifth digit is higher than Zero
 *     if (sParser.get(4) > 0) {
 *       // we will add a branch first on the xth room in the allRooms list, with x being the fifth digit of the Seed
 *       int eBranchCount = sParser.get(4);
 *       for (int i = 1; i < sParser.get(4); i++) {
 *         // we have to make sure though that we even have enough Rooms in the list to add branches to
 *         if (allRooms.size() > eBranchCount) {
 *           // and make sure the new Rooms are being added to the list
 *           Room newRoom = new Room(j, exampleDescription);
 *           this.allRooms.add(newRoom);
 *           j++;
 *
 *           allRooms.get(eBranchCount).setNextDoorRoom(newRoom, true, false, false);
 *           roomCount++;
 *           // and after every loop we multiple the count by 2, so if it was 2 before, it will be the 4th Room
 *           // in the allRooms list
 *           eBranchCount *= 2;
 *         }
 *       }
 *     }
 *
 *     // add branches that go up south, starts when the sixth digit is higher than Zero
 *     if (sParser.get(5) > 0) {
 *       // we will add a branch first on the xth room in the allRooms list, with x being the sixth digit of the Seed
 *       int sBranchCount = sParser.get(5);
 *       for (int i = 1; i < sParser.get(5); i++) {
 *         // we have to make sure though that we even have enough Rooms in the list to add branches to
 *         if (allRooms.size() > sBranchCount) {
 *           // and make sure the new Rooms are being added to the list
 *           Room newRoom = new Room(j, exampleDescription);
 *           this.allRooms.add(newRoom);
 *           j++;
 *
 *           allRooms.get(sBranchCount).setNextDoorRoom(newRoom, false, false, false);
 *           roomCount++;
 *           // and after every loop we multiple the count by 2, so if it was 2 before, it will be the 4th Room
 *           // in the allRooms list
 *           sBranchCount *= 2;
 *         }
 *       }
 *     }
 *
 *     // add branches that go up west, starts when the seventh digit is higher than Zero
 *     if (sParser.get(6) > 0) {
 *       // we will add a branch first on the xth room in the allRooms list, with x being the seventh digit of the Seed
 *       int wBranchCount = sParser.get(6);
 *       for (int i = 1; i < sParser.get(6); i++) {
 *         // we have to make sure though that we even have enough Rooms in the list to add branches to
 *         if (allRooms.size() > wBranchCount) {
 *           // and make sure the new Rooms are being added to the list
 *           Room newRoom = new Room(j, exampleDescription);
 *           this.allRooms.add(newRoom);
 *           j++;
 *
 *           allRooms.get(wBranchCount).setNextDoorRoom(newRoom, false, true, false);
 *           roomCount++;
 *           // and after every loop we multiple the count by 2, so if it was 2 before, it will be the 4th Room
 *           // in the allRooms list
 *           wBranchCount *= 2;
 *         }
 *       }
 *     }
   * Assigns a room on a random direction to a room depending on what is not already assigned.
   *
   * @param room Room that gets a new Room assigned.
   * @param roomToBeAssigned room to be assigned
   *
  public void assignRandomNextDoorRoom(Room room, Room roomToBeAssigned) {

    ArrayList<Boolean> randomBoolean = new ArrayList<>();
    randomBoolean.add(true);
    randomBoolean.add(false);
    randomBoolean.add(false);
    Collections.shuffle(randomBoolean);

    if (room.isNorthAssigned.equals(true)) {
      room.setNextDoorRoom(roomToBeAssigned, randomBoolean.get(0), randomBoolean.get(1), false);
    } else if (room.isEastAssigned.equals(true)) {
      room.setNextDoorRoom(roomToBeAssigned, false, randomBoolean.get(0), randomBoolean.get(1));
    } else if (room.isSouthAssigned.equals(true)) {
      room.setNextDoorRoom(roomToBeAssigned, randomBoolean.get(0), randomBoolean.get(1),
              randomBoolean.get(2));
    } else {
      room.setNextDoorRoom(roomToBeAssigned, randomBoolean.get(0), false, randomBoolean.get(1));
    }
  }

  public List<Room> getAllRooms() {
    return allRooms;
  }
}

/**
 * int roomCount = 1;
 *     String exampleDescription = "A dark, cold room.";
 *     this.player = player;
 *
 *     // create a List for all the Rooms generated
 *     this.allRooms = new ArrayList<>();
 *
 *     // create as many rooms as maxRoomCount allows
 *     int j = 0;
 *     while (allRooms.size() <= maxRoomCount) {
 *       Room newRoom = new Room(j, exampleDescription);
 *       this.allRooms.add(newRoom);
 *       j++;
 *     }
 *
 *     // take the first room generated as the starting room
 *     this.startRoom = allRooms.get(0);
 *     // place player inside
 *     player.setCurrentRoomOfPlayer(startRoom);
 *
 *     // West
 *     startRoom.setNextDoorRoom(allRooms.get(roomCount), false, true, false);
 *     roomCount++;
 *     if (seed.getFloorLength().get(0) > 1) {
 *       allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount),
 *                 false, seed.floorDir.get(0), seed.floorDir.get(1));
 *       roomCount++;
 *       if (seed.getFloorLength().get(0) > 2) {
 *         allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount),
 *                 seed.floorDir.get(1), seed.floorDir.get(2), false);
 *         roomCount++;
 *       }
 *     }
 *     // North
 *     startRoom.setNextDoorRoom(allRooms.get(roomCount), false, false, true);
 *     roomCount++;
 *     if (seed.getFloorLength().get(1) > 1) {
 *       allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount),
 *               seed.floorDir.get(0), false, seed.floorDir.get(1));
 *       roomCount++;
 *       if (seed.getFloorLength().get(1) > 2) {
 *         allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount),
 *                         seed.floorDir.get(0), false, seed.floorDir.get(1));
 *         roomCount++;
 *       }
 *     }
 *     // East
 *     startRoom.setNextDoorRoom(allRooms.get(roomCount), true, false, false);
 *     roomCount++;
 *     if (seed.getFloorLength().get(2) > 1) {
 *       allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount),
 *            seed.floorDir.get(0), false, seed.floorDir.get(1));
 *       roomCount++;
 *       if (seed.getFloorLength().get(2) > 2) {
 *         allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount),
 *                         seed.floorDir.get(1), seed.floorDir.get(2), false);
 *         roomCount++;
 *
 *       }
 *     }
   *
   *                         // West
   *     if (sParser[0] >= 1) {
   *       startRoom.setNextDoorRoom(allRooms.get(roomCount), false, true, false);
   *       roomCount++;
   *       if (sParser[0] > 1) {
   *         for (int i = 1; sParser[0] > i; i++) {
   *           allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount), false, seed.floorBool.get(0), seed.floorBool.get(1));
   *           roomCount++;
   *         }
   *       }
   *     }
   *
   *     // North
   *     if (sParser[1] >= 1) {
   *       startRoom.setNextDoorRoom(allRooms.get(roomCount), false, false, true);
   *       roomCount++;
   *       if (sParser[1] > 1) {
   *         for (int i = 1; sParser[1] > i; i++) {
   *           allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount), seed.floorBool.get(0), seed.floorBool.get(1), seed.floorBool.get(2));
   *           roomCount++;
   *         }
   *       }
   *     }
   *
   *     // East
   *     if (sParser[2] >= 1) {
   *       startRoom.setNextDoorRoom(allRooms.get(roomCount), true, false, false);
   *       roomCount++;
   *       if (sParser[2] > 1) {
   *         for (int i = 1; sParser[2] > i; i++) {
   *           allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount), seed.floorBool.get(0), false, seed.floorBool.get(2));
   *           roomCount++;
   *         }
   *       }
   *
   *       // add some branches
   *       int branchCounter = sParser[4];
   *       if (allRooms.size() >= branchCounter) {
   *         while (branchCounter <= allRooms.size()) {
   *           Room newRoom = new Room(j, exampleDescription);
   *           this.allRooms.add(newRoom);
   *           j++;
   *           allRooms.get(branchCounter).setNextDoorRoom(newRoom, seed.floorBool.get(0), seed.floorBool.get(1), seed.floorBool.get(2));
   *           branchCounter *= 2;
   *         }
   *       }
 *
 *
 *
 *                           //Now we create the puzzles.
 *     int numberOfPuzzles = 1;
 *     int a = maxRoomCount / 5;
 *     if (a > 1) {
 *       numberOfPuzzles = a;
 *     }
 *
 *     a = 0;
 *     int b = 5;
 *     String standardKeyDescription = "A metal key. Looks old.";
 *
 *     while (a < numberOfPuzzles) {
 *       Random random = new Random();
 *       Item key = new Item(a, "Key " + a, standardKeyDescription);
 *
 *       //We need this to make sure that the key is generated in a room before the puzzle.
 *       int puzzleInteger = random.nextInt(0, b);
 *       int keyInteger = 0;
 *       if (puzzleInteger == 0) {
 *         keyInteger = 0;
 *       } else {
 *         keyInteger = random.nextInt(0, puzzleInteger - 1);
 *       }
 *
 *       int c = 0;
 *       int d = 0;
 *
 *       Room puzzleRoom = allRooms.get(puzzleInteger);
 *       c = puzzleRoom.getNumberOfDoors();
 *       d = random.nextInt(1, c);
 *
 *       if (d == 1) {
 *         if (puzzleRoom.hasSouthDoor()) {
 *         puzzleRoom.getSouthDoor().setPuzzle(key);
 *         } else if (puzzleRoom.hasDoorWest()) {
 *           puzzleRoom.getWestDoor().setPuzzle(key);
 *         } else if (puzzleRoom.hasDoorNorth()) {
 *           puzzleRoom.getNorthDoor().setPuzzle(key);
 *         } else {
 *           puzzleRoom.getEastDoor().setPuzzle(key);
 *         }
 *       }
 *       if (d == 2) {
 *         if (puzzleRoom.hasDoorEast()) {
 *           puzzleRoom.getEastDoor().setPuzzle(key);
 *         } else if (puzzleRoom.hasSouthDoor()) {
 *           puzzleRoom.getSouthDoor().setPuzzle(key);
 *         } else if (puzzleRoom.hasDoorWest()) {
 *           puzzleRoom.getWestDoor().setPuzzle(key);
 *         } else {
 *           puzzleRoom.getNorthDoor().setPuzzle(key);
 *         }
 *       }
 *       if (d == 3) {
 *         if (puzzleRoom.hasDoorNorth()) {
 *           puzzleRoom.getNorthDoor().setPuzzle(key);
 *         } else if (puzzleRoom.hasDoorEast()) {
 *           puzzleRoom.getEastDoor().setPuzzle(key);
 *         } else if (puzzleRoom.hasSouthDoor()) {
 *           puzzleRoom.getSouthDoor().setPuzzle(key);
 *         } else {
 *           puzzleRoom.getWestDoor().setPuzzle(key);
 *         }
 *       }
 *       if (d == 4) {
 *         if (puzzleRoom.hasDoorWest()) {
 *           puzzleRoom.getWestDoor().setPuzzle(key);
 *         } else if (puzzleRoom.hasDoorNorth()) {
 *           puzzleRoom.getNorthDoor().setPuzzle(key);
 *         } else if (puzzleRoom.hasDoorEast()) {
 *           puzzleRoom.getEastDoor().setPuzzle(key);
 *         } else {
 *           puzzleRoom.getSouthDoor().setPuzzle(key);
 *         }
 *       }
 *
 *       allRooms.get(keyInteger).addItem(key);
 *
 *       a++;
 *       b = b + 5;
 *     }
 */
