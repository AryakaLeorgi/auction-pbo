public class Bid {
    // The person making the bid.
    private final Person bidder;
    // The bid amount.
    private final long bidValue;

    /**
     * Constructs a new Bid with the given bidder and amount.
     * @param bidder The person placing the bid.
     * @param value The amount of the bid.
     */
    public Bid(Person bidder, long value) {
        this.bidder = bidder;
        this.bidValue = value;
    }

    public Person getBidder() {
        return bidder;
    }

    public long getValue() {
        return bidValue;
    }
}
