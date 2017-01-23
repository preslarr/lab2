package spell;

import java.util.Arrays;

import java.util.HashSet;


import java.util.Set;

import spell.ISpellCorrector.NoSimilarWordFoundException;

public class SuggestWord {
	private Trie words;
	private Set<String> oneedit;
	private Set<String> twoedit;
 	private Set<String> holdingtank;
	private Set<String> notindict;
	
	public SuggestWord() {
		// TODO Auto-generated constructor stub
		
	}
	
	public SuggestWord(Trie wordsin) {
		// TODO Auto-generated constructor stub
		words = wordsin;
		oneedit = new HashSet<String>();
		twoedit = new HashSet<String>();
		notindict = new HashSet<String>();
	}
	
	public String suggest(String inputWord) throws NoSimilarWordFoundException{
		return makeEditsToWord(inputWord);
	}
	
	private String makeEditsToWord(String input) throws NoSimilarWordFoundException{
		Set<String> startingword = new HashSet<>();
		startingword.add(input);
		holdingtank = makeEdit(startingword);			//make the first edit to word
		for (String s : holdingtank){
			if (words.find(s) != null){					//sorting wordedits into two sets
				oneedit.add(s);
			} else {
				notindict.add(s);
			}
		}
		if (oneedit.isEmpty()){							//if oneedit is empty then no edits were in dict
			oneedit = makeEdit(notindict);				//makes second edits to already edited words
			notindict.clear();							
			for (String t : oneedit){
				if (words.find(t) != null){
					twoedit.add(t);				//add twoedit words found in dict.
				} else {
					
					
				}
			}
			if (twoedit.isEmpty()) {					//if there are no words then throw exception
				throw new NoSimilarWordFoundException();
			} else {									//creating two edits made a word in dict.
				if (twoedit.size() == 1){				//if thats the only one then return that word
					String outpoot = "";
					for (String i : twoedit){
						outpoot = i;
					}
					return outpoot;
				} else {								//if there are many then look at freq and alpha
					return getMostSimilar(twoedit);
				}
				
			}
		} else {										//there were words in dict after one edit
			if (oneedit.size() == 1){					//if there is only one then return that one
				String outpoot = "";
				for (String i : oneedit){
					outpoot = i;
				}
				return outpoot;
			} else {									//if there are many then check freq and alpha
				return getMostSimilar(oneedit);
			}
		}
		 
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
	
	private String getMostSimilar(Set<String> in) {				//this takes set of strings and looks at freq
		int frequency = 0;
		Set<String> mostfreqstrings = new HashSet<String>();   
		for (String s : in){
			ITrie.INode ptr = words.find(s);				//for each string in set get it's node
			int ptrcount = ptr.getValue();					//
			if (ptrcount > frequency){						//if that nodes count is greater than our var
				frequency = ptrcount;						//  set our var to that count frequency
				mostfreqstrings.clear();					//  clear all previous strings from set of most freq
				mostfreqstrings.add(s);						//  add new string with high freq
			} else if (ptrcount == frequency){				//else if the nodes count is equal to our freq
				mostfreqstrings.add(s);						//  add that word to our most freq sets
			} else {										//else move on
				
			}
		}
		if (mostfreqstrings.size() == 1){					// if there is only one most freq string
			String firststr = ""; 
			for (String out : mostfreqstrings){
				firststr = out;
												//  return that string
			}
			return firststr;
		} else {
			return getAlphaFirstString(mostfreqstrings);	// if there are multiple freq strings then alphabetize 
		}
	}
	
	private String getAlphaFirstString(Set<String> in){
		String[] myarray = new String[in.size()];
		int iterate = 0;
		for (String s : in){
			myarray[iterate] = s;
			iterate += 1;
		}
		Arrays.sort(myarray);
		return myarray[0];
	}
}
