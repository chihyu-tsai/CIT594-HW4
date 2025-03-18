public class Term implements ITerm {

    // TODO: instance variables
    // add in field
    private String term;
    private long weight;

    /**
     * Initialize a Term with a given query String and weight
     */
    public Term(String term, long weight) {
        // finish the constructor
        if (term == null || weight < 0) {
            throw new IllegalArgumentException();
        }
        this.term = term;
        this.weight = weight;

    }

    // =================
    // TODO: Overrides!
    // =================
    @Override
    public int compareTo(ITerm that) {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public long getWeight() {
        return 0;
    }

    @Override
    public String getTerm() {
        return null;
    }

    @Override
    public void setWeight(long weight) {

    }

    @Override
    public String setTerm(String term) {
        return null;
    }


}
