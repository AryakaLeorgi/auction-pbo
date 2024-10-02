public class Lot {
    // A unique identifier for this lot.
    private final int lotNumber;
    // A description of the item.
    private String description;
    // The highest bid received for this lot.
    private Bid highestBid;

    /**
     * Constructs a new Lot instance with the given number and description.
     * @param lotNumber The unique number of the lot.
     * @param description Description of the lot.
     */
    public Lot(int lotNumber, String description) {
        this.lotNumber = lotNumber;
        this.description = description;
    }

    /**
     * Attempts to place a bid on this lot.
     * The bid must be higher than the current highest bid to be successful.
     * @param bid The bid being placed.
     * @return true if the bid is successful, false otherwise.
     */
    public boolean receiveBid(Bid bid) {
        if (highestBid == null || bid.getValue() > highestBid.getValue()) {
            highestBid = bid;
            return true;
        }
        return false;
    }

    /**
     * Returns a string describing the lot.
     * @return A description of the lot and its highest bid (if any).
     */
    public String toString() {
        String details = lotNumber + ": " + description;
        if (highestBid != null) {
            details += "    Current bid: " + highestBid.getValue();
        } else {
            details += "    (No bids)";
        }
        return details;
    }

    public int getNumber() {
        return lotNumber;
    }

    public String getDescription() {
        return description;
    }

    public Bid getHighestBid() {
        return highestBid;
    }
}
