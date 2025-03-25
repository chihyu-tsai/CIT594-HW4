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
        return this.term.compareTo(that.getTerm());
    }

    @Override
    public String toString() {
        return weight + "\t" + term;
    }

    @Override
    public long getWeight() {
        return weight;
    }

    @Override
    public String getTerm() {
        return term;
    }

    @Override
    public void setWeight(long weight) {
        this.weight = weight;
    }

    @Override
    public String setTerm(String term) {
        this.term = term;
        return term;
    }


}
