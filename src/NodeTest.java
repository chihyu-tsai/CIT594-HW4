import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class NodeTest {

    @Test
    public void testConstructor() {
        Node n1 = new Node();
        assertEquals("", n1.getTerm().getTerm());
        assertEquals(0, n1.getTerm().getWeight());
        assertEquals(0, n1.getWords());
        assertEquals(0, n1.getPrefixes());
        Node[] expected = new Node[26];
        assertEquals(expected, n1.getReferences());


        Node n2 = new Node("berkeley", 48);
        assertEquals("berkeley", n2.getTerm().getTerm());
        assertEquals(48, n2.getTerm().getWeight());
        assertEquals(0, n2.getWords());
        assertEquals(0, n2.getPrefixes());
        Node[] expected2 = new Node[26];
        assertEquals(expected2, n2.getReferences());
    }

}
