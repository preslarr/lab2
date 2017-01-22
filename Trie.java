package spell;

import java.io.IOException;

public class Trie implements ITrie {
	private NodeClass root;

	public Trie() {
		// TODO Auto-generated constructor stub
		root = new NodeClass();
	}

	@Override
	public void add(String word) {
		// TODO Auto-generated method stub
		root.addToNode(word.toLowerCase());
	}

	@Override
	public INode find(String word) {
		// TODO Auto-generated method stub
		return root.findNode(word);
	}

	@Override
	public int getWordCount() {
		// TODO Auto-generated method stub
		return root.countWords();
	}

	@Override
	public int getNodeCount() {
		// TODO Auto-generated method stub
		return root.countNodes();
	}
	
	public String toString(){
		StringBuilder allwords = new StringBuilder();
		try {
			root.toString(allwords, "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allwords.toString();
	}

}
