package spell;

import java.io.IOException;

public class Trie implements ITrie {
	public NodeClass root;

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
	public ITrie.INode find(String word) {
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
	
	public int hashCode(){
		int mynum = getWordCount() * getNodeCount();
		return mynum * 31;
	}
	
	public boolean equals(Object o){
		if (o == null){
			return false;
		}
		StringBuilder mypathstring = new StringBuilder("");
		StringBuilder inpathstring = new StringBuilder("");
		this.root.printPathString(mypathstring);
		if (!Trie.class.isAssignableFrom(o.getClass())){
			return false;
		}
		final Trie other = (Trie) o;
		
		other.root.printPathString(inpathstring);
		String my = mypathstring.toString();
		String in = inpathstring.toString();
		if (my.equals(in)){
			return true;
		}
		return false;
		
	}

}


