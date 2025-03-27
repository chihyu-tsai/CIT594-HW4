import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Autocomplete implements IAutocomplete {

    private Node root = new Node();


    @Override
    public void addWord(String word, long weight) {
        // test if root is null and sanity check on word and weight
        if (root == null) {
            return;
        }
        if (word == null || weight < 0) {
            return;
        }

        // convert word to all lowercase
        word = word.toLowerCase();

        // if contains invalid character just do nothing
        for (int i = 0 ; i < word.length(); i++) {
            if (!Character.isAlphabetic(word.charAt(i))) {
                return;
            }
        }
        // assign curr - initially start at root
        Node curr = root;
        root.setPrefixes(root.getPrefixes() + 1);

        for (int i = 0 ; i < word.length(); i++) {
            Node[] currReference = curr.getReferences();
            int index = word.charAt(i) - 'a';
            Node target = currReference[index];
            if (target == null) {
                target = new Node();
                target.setPrefixes(1);
                currReference[index] = target;
                curr = target;
            } else {
                target.setPrefixes(target.getPrefixes() + 1);
                curr = target;
            }
        }
        curr.setTerm(new Term(word, weight));
        curr.setWords(1);
    }

    @Override
    public Node buildTrie(String filename, int k) {
//        Node root = new Node();
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            // to get rid of the first line in the .txt file where there's just a number
//            root.setPrefixes(Integer.parseInt(br.readLine()));
            // start reading the content in the file
            String read = br.readLine();
            while (read != null) {
                String message = read;
                message = message.trim();
                String[] msgList = message.split("\t");
                while (msgList.length > 1) {
                    addWord(msgList[1], Long.parseLong(msgList[0]));
                    break;
                }
                read = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    @Override
    public Node getSubTrie(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            // prefix[i] is the actual character we get
            int index = prefix.charAt(i) - 'a';
            if (index < 0) {
                return null;
            }
            // check if the current character is in the trie
            if (curr.getReferences()[index] != null) {
                curr = curr.getReferences()[index];
            } else {
                return null;
            }
        }
        return curr;
    }

    @Override
    public int countPrefixes(String prefix) {
        // calling getSubTrie to move the target node
        Node result = getSubTrie(prefix);
        if (result == null) {
            return 0;
        }
        int num = result.getPrefixes();
        return num;
    }

    @Override
    public List<ITerm> getSuggestions(String prefix) {
        /* calling getSubTrie to move the target node
        we want to now treat the subtrie's node as the root and return all potential words */
        List<ITerm> suggestions = new ArrayList<>();
        Node start = getSubTrie(prefix);
        if (start == null) {
            return suggestions;
        }
        // call the recursive function on this starting node
        traverse(start, suggestions);
        return suggestions;
    }

    public void traverse(Node node, List list) {
        // this means we hit the leaf node
        if (node.getWords() == 1) {
//            Node copy = new Node(node.getTerm().getTerm(),node.getTerm().getWeight());
            ITerm copyTerm = new Term(node.getTerm().getTerm(), node.getTerm().getWeight());
            list.add(copyTerm);
        }
        for (int i = 0; i < node.getReferences().length; i++) {
            if (node.getReferences()[i] == null  || node.getReferences()[i].getPrefixes() == 0) {
                continue;
            }
            traverse(node.getReferences()[i], list);
        }
    }

}
