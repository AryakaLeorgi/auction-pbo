import java.util.ArrayList;
import java.util.HashSet;

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

    /**
     * Place a bid on a specific lot.
     * Prints a message based on whether the bid was successful.
     * @param lotNumber The lot number being bid for.
     * @param bidder The person placing the bid.
     * @param amount The value of the bid.
     */
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

    /**
     * Lists all persons and their bids, if they have placed one.
     */
    public void listBiddersAndBids() {
        // Use a HashSet to avoid printing the same bidder multiple times for different lots
        HashSet<Person> bidders = new HashSet<>();
        
        System.out.println("Listing all bidders and their bids:");

        // Loop through all the lots
        for (Lot lot : itemList) {
            // Get the highest bid for the current lot
            Bid highestBid = lot.getHighestBid();
            if (highestBid != null) {
                Person bidder = highestBid.getBidder();
                // Add to the HashSet to avoid duplicates
                if (!bidders.contains(bidder)) {
                    System.out.println("Bidder: " + bidder.getName() + ", Bid: " + highestBid.getValue());
                    bidders.add(bidder);
                }
            }
        }

        // If no bidders found, print a message
        if (bidders.isEmpty()) {
            System.out.println("No bidders have placed bids yet.");
        }
    }
}
