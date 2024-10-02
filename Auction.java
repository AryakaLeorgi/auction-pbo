import java.util.ArrayList;

public class Auction {
    // List of items (lots) in the auction.
    private ArrayList<Lot> itemList;
    // The lot number for the next item.
    private int upcomingLotNumber;

    /**
     * Initializes a new Auction instance.
     */
    public Auction() {
        itemList = new ArrayList<>();
        upcomingLotNumber = 1;
    }

    /**
     * Adds a new lot to the auction.
     * @param description Description of the lot.
     */
    public void addLot(String description) {
        itemList.add(new Lot(upcomingLotNumber, description));
        upcomingLotNumber++;
    }

    /**
     * Displays all lots in the auction.
     */
    public void listAllLots() {
        for (Lot lot : itemList) {
            System.out.println(lot);
        }
    }

    public void placeBid(int lotNumber, String personName, long amount) {
    // Retrieve or create the Person object using getOrCreatePerson
    Person bidder = Person.getOrCreatePerson(personName);
    
    // Now place the bid using the Person object
    Lot chosenLot = findLot(lotNumber);
    if (chosenLot != null) {
        if (chosenLot.receiveBid(new Bid(bidder, amount))) {
            System.out.println("Bid placed successfully for lot " + lotNumber);
        } else {
            System.out.println("Lot " + lotNumber + " already has a higher bid: " +
                               chosenLot.getHighestBid().getValue());
        }
    }
}



    /**
     * Finds and returns a lot by its number. 
     * Returns null if no such lot exists.
     * @param lotNumber The number of the lot.
     */
    public Lot findLot(int lotNumber) {
        if (lotNumber >= 1 && lotNumber < upcomingLotNumber) {
            Lot chosenLot = itemList.get(lotNumber - 1);
            if (chosenLot.getNumber() != lotNumber) {
                System.out.println("Internal error: wrong lot retrieved for number: " + lotNumber);
            }
            return chosenLot;
        } else {
            System.out.println("Lot " + lotNumber + " does not exist.");
            return null;
        }
    }
}
