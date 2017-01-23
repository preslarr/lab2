package spell;

import java.io.IOException;

import spell.ITrie.INode;

public class NodeClass implements INode {
	private int count;
	private NodeClass[] nodes;

	public NodeClass() {
		// TODO Auto-generated constructor stub
		nodes = new NodeClass[26];
		count = 0;
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return count;
	}
	
	public void addToNode(String word){
		
		if (word.length() == 0){
			count += 1;
			return;
		}
		char letter = word.charAt(0);
		int noderef = letter - 'a';
		if (word.length() == 1){
			if (nodes[noderef] != null){
				nodes[noderef].addToNode("");
			} else {
				nodes[noderef] = new NodeClass();
				nodes[noderef].addToNode("");
			}
			
		} else {
			if (nodes[noderef] != null){
				nodes[noderef].addToNode(word.substring(1));
			} else {
				nodes[noderef] = new NodeClass();
				nodes[noderef].addToNode(word.substring(1));
			}
			
		}
	}
	
	public void toString(StringBuilder sb, String in) throws IOException{
		if (count > 0){
			sb.append(in + "\n");
		}
		for (int x = 0; x < 26; x += 1){
			if (nodes[x] != null){
				nodes[x].toString(sb, in + makeIntChar(x));
			}
		}
		return;
	}
	
	private char makeIntChar(int in) throws IOException{
		switch (in) {
			case 0: return 'a';
			case 1: return 'b';
			case 2: return 'c';
			case 3: return 'd';
			case 4: return 'e';
			case 5: return 'f';
			case 6: return 'g';
			case 7: return 'h';
			case 8: return 'i';
			case 9: return 'j';
			case 10: return 'k';
			case 11: return 'l';
			case 12: return 'm';
			case 13: return 'n';
			case 14: return 'o';
			case 15: return 'p';
			case 16: return 'q';
			case 17: return 'r';
			case 18: return 's';
			case 19: return 't';
			case 20: return 'u';
			case 21: return 'v';
			case 22: return 'w';
			case 23: return 'x';
			case 24: return 'y';
			case 25: return 'z';
			default: throw new IOException();
						
		}
	}
	
	public NodeClass findNode(String word) {
		if (word.length() == 0){
			if (count > 0) {
				return this;
			} else {
				return null;
			}
			
		}
		char letter = word.charAt(0);
		int noderef = letter - 'a';
		if (word.length() == 1){
			if (nodes[noderef] != null){
				return nodes[noderef].findNode("");
			} else {
				return null;
			}
			
		} else {
			if (nodes[noderef] != null){
				return nodes[noderef].findNode(word.substring(1));			
			} else {
				return null;
			}
		}
	}
	
	public int countWords() {
		int nodewordcount = 0;
		if (count > 0) {nodewordcount += 1;}
		for (int x = 0; x < 26; x += 1){
			if (nodes[x] != null){
				nodewordcount = nodewordcount + nodes[x].countWords();
			}
		}
		return nodewordcount;
	}
	
	public int countNodes() {
		int nodecount = 1;		
		for (int x = 0; x < 26; x += 1){
			if (nodes[x] != null){
				nodecount = nodecount+ nodes[x].countNodes();
			}
		}
		return nodecount;
	}
	
	public void printPathString(StringBuilder in){
		in.append(count);
		for (int x = 0; x < 26; x += 1){
			if (nodes[x] != null){
				nodes[x].printPathString(in);
			}
		}
		return;
	}
	
	public boolean compareNodes(NodeClass other){
		boolean output = true;
		for (int x = 0; x < 26; x += 1){
			if (this.nodes[x] != null && other.nodes[x] != null){
				output = this.nodes[x].compareNodes(other.nodes[x]);
			} else if (this.nodes[x] == null && other.nodes[x] == null){
				
			} else {
				return false;
			}
		}
		return output;
		
	}

}
