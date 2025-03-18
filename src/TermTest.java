import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TermTest {


    @Test
    public void testByReverseWeightOrder() {
        List<ITerm> iTermList = new ArrayList<>();
        iTermList.add(new Term("berkeley", 12));
        iTermList.add(new Term("chicago", 43));
        iTermList.add(new Term("stanford", 27));
        iTermList.add(new Term("brown", 56));
        iTermList.add(new Term("mit", 77));
        iTermList.add(new Term("northwestern", 67));
        Collections.sort(iTermList, ITerm.byReverseWeightOrder());
        assertEquals("mit", iTermList.get(0).getTerm());
        assertEquals("northwestern", iTermList.get(1).getTerm());
    }

    @Test
    public void testByPrefixOrder() {
        List<ITerm> iTermList = new ArrayList<>();
        iTermList.add(new Term("berkeley", 12));
        iTermList.add(new Term("chicago", 43));
        iTermList.add(new Term("stanford", 27));
        iTermList.add(new Term("brown", 56));
        iTermList.add(new Term("mit", 77));
        iTermList.add(new Term("northwestern", 67));
        Collections.sort(iTermList, ITerm.byPrefixOrder(3));
        assertEquals("berkeley", iTermList.get(0).getTerm());
        assertEquals("brown", iTermList.get(1).getTerm());
    }
}
