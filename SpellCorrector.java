package spell;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SpellCorrector implements ISpellCorrector {
	public Trie words;
	private Set<String> oneedit;
	private Set<String> twoedit;
	private Set<String> notindict;
	
	public SpellCorrector() {
		// TODO Auto-generated constructor stub
		words = new Trie();
	}

	@Override
	public void useDictionary(String dictionaryFileName) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new File(dictionaryFileName));
		while (sc.hasNext()){
			words.add(sc.next());
		}
		sc.close();
	}

	@Override
	public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {
		// TODO Auto-generated method stub
		ITrie.INode inputnode = words.find(inputWord.toLowerCase());
		if (inputnode != null){
			return inputWord.toLowerCase();
		} else {
			return mostSimilar(inputWord.toLowerCase());
		}

	}

	public String toString(){
		return words.toString();
	}
	
	private String mostSimilar(String input){
		Set<String> startingword = new HashSet<>();
		startingword.add(input);
		oneedit = makeEdit(startingword);
		StringBuilder testWords = new StringBuilder("");
		for (String s :oneedit){
			testWords.append(s + "\n");
		}
		return testWords.toString();
	}
	
	private Set<String> makeEdit(Set<String> in){
		Set<String> output = new HashSet<String>();
		Set<String> temp;
		temp = deletion(in);
		output.addAll(temp);
		temp.clear();
		temp = transposition(in);
		output.addAll(temp);
		temp.clear();
		temp = alteration(in);
		output.addAll(temp);
		temp.clear();
		temp = insertion(in);
		output.addAll(temp);
		temp.clear();
		return output;
	}
	
	private Set<String> deletion(Set<String> in){
		Set<String> output = new HashSet<String>();
		for (String s : in){
			output.addAll(deleteFromWord(s));
		}
		return output;
	}
	
	private Set<String> deleteFromWord(String s){
		Set<String> output = new HashSet<String>();
		if (s.length() == 1) {return output;}

		for (int x = 0; x < s.length(); x +=1){
			StringBuilder tempB = new StringBuilder(s);
			tempB.deleteCharAt(x);
			output.add(tempB.toString());
		}
		return output;
	}
	
	private Set<String> transposition(Set<String> in){
		Set<String> output = new HashSet<String>();
		for (String s : in){
			output.addAll(transposeFromWord(s));
		}
		return output;
	}
	
	private Set<String> transposeFromWord(String s){
		Set<String> output = new HashSet<String>();
		if (s.length() == 1) {return output;}
		for (int x = 0; x < s.length() - 1; x += 1){
			StringBuilder tempB = new StringBuilder(s);
			char tempchar = tempB.charAt(x);
			tempB.setCharAt(x, tempB.charAt(x+1));
			tempB.setCharAt(x+1, tempchar);
			output.add(tempB.toString());
		}
		return output;
		
	}
	
	private Set<String> alteration(Set<String> in){
		Set<String> output = new HashSet<String>();
		for (String s : in){
			output.addAll(alterFromWords(s));
		}
		return output;
	}
	
	private Set<String> alterFromWords(String s){
		Set<String> output = new HashSet<String>();
		for (int x = 0; x < s.length(); x += 1){
			for (int y = 0; y < 26; y += 1){
				StringBuilder tempB = new StringBuilder(s);	
				tempB.setCharAt(x, makeIntChar(y));
				output.add(tempB.toString());
			}
					
		}
		return output;
	}
	
	private char makeIntChar(int in){
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
			default: return 'a';
						
		}
	}
	
	private Set<String> insertion(Set<String> in){
		Set<String> output = new HashSet<String>();
		for (String s : in){
			output.addAll(insertFromWords(s));
		}
		return output;
	}
	
	private Set<String> insertFromWords(String s) {
		Set<String> output = new HashSet<String>();
		for (int x = 0; x < s.length() + 1; x += 1){
			for (int y = 0; y < 26; y += 1){
				StringBuilder tempB = new StringBuilder(s);	
				tempB.insert(x, makeIntChar(y));
				output.add(tempB.toString());
			}
					
		}
		return output;
	}
	
}
