package spell;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import spell.ISpellCorrector.NoSimilarWordFoundException;

/**
 * A simple main class for running the spelling corrector. This class is not
 * used by the passoff program.
 */
public class Main {
	
	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 */
	public static void main(String[] args) throws NoSimilarWordFoundException, IOException {
		
		String dictionaryFileName = args[0];
		String inputWord = args[1];
		
		/**
		 * Create an instance of your corrector here
		 */
		ITrie first = new Trie();
		ITrie second = new Trie();
		first.add("cat");
		first.add("dog");
		second.add("cat");
		second.add("dog");
		second.add("dog");
		if (first.equals(second)){
			System.out.print("equals");
		} else {
			System.out.print("doesn't equal");
		}
		
		//ISpellCorrector corrector = new SpellCorrector();
		
		//corrector.useDictionary(dictionaryFileName);
		
		//String suggestion = corrector.suggestSimilarWord(inputWord);
		
		
		//System.out.println("Suggestion is: \n" + suggestion);
	}

}
