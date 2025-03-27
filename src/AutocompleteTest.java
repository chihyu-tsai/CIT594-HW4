import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AutocompleteTest {


//    @Test
//    public void testBuildTrie() {
//        Autocomplete ac = new Autocomplete();
//        Node actual = ac.buildTrie("pokemon.txt", 2);
//        assertEquals(728, actual.getPrefixes());
//    }

    @Test
    public void testGetSubTrie() {
        Autocomplete ac = new Autocomplete();
        Node actual = ac.buildTrie("test.txt", 2);
        assertEquals(4, ac.getSubTrie("a").getPrefixes());
        assertEquals(3, ac.getSubTrie("pol").getPrefixes());
        assertNull(null, ac.getSubTrie("l"));
    }

    @Test
    public void testCountPrefixes() {
        Autocomplete ac = new Autocomplete();
        Node actual = ac.buildTrie("test.txt", 2);
        assertEquals(3, ac.countPrefixes("pol"));
    }

    @Test
    public void testGetSuggestions() {
        Autocomplete ac = new Autocomplete();
        Node actual = ac.buildTrie("test.txt", 2);
        List<ITerm> resultList = new ArrayList<>();
        resultList.add(new Term("politics", 43));
        resultList.add(new Term("poll", 22));
        resultList.add(new Term("pollution", 21));
        assertEquals(resultList.size(), ac.getSuggestions("p").size());
        assertEquals(resultList.get(0).getTerm(), ac.getSuggestions("po").get(0).getTerm());
        assertEquals(resultList.get(1).getWeight(), ac.getSuggestions("pol").get(1).getWeight());

    }



}

