package spell;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;


public class SpellCorrector implements ISpellCorrector {
	public Trie words;
	
	
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
			SuggestWord wordsuggester = new SuggestWord(words);
			return wordsuggester.suggest(inputWord.toLowerCase());
		}

	}

	public String toString(){
		return words.toString();
	}
	
	
	
}
