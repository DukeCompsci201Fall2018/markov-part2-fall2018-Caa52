import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 
 * @author Collins Abanda
 * 
 *created EfficientWordMarkov class extension
 *initialized map as WordGram and ArrayList just as in BaseWordMarkov
 *created the EfficientWordMarkov constructor to initialize new map of WordGram, ArrayList called myMap
 *Set EfficentWordMarkov to 2
 */
public class EfficientWordMarkov extends BaseWordMarkov {
	private Map<WordGram, ArrayList<String>> myMap;

/**
 * 
 * @param order
 * @return EfficientWordMarkov 
 * created initialized myMap
 */
	public EfficientWordMarkov(int order) {
		super(order);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}

	public EfficientWordMarkov() {
		this(2);
	}

	/**
	 * Create WordGram
	 * 
	 * @param text
	 * @category void
	 * @return null because its void constructor 
	 * 
	 * called override on setTraining
	 * setTraining parameter is set as text
	 */
	
	@Override
	public void setTraining(String text) {
		//split white spaces between words of text 
		myWords = text.split("\\s+");
		//clear map
		myMap.clear();
		//loop through the words in text
		for(int i=0; i <= myWords.length - myOrder + 1; i++){
			//Create new WordGram called Owen
			WordGram Owen = new WordGram(myWords, i, myOrder);
			//create new string arraylist 
			ArrayList<String> myList = new ArrayList<String>();
			
			//if key Owen in in map, assign Owen a value in map
			if (myMap.containsKey(Owen)) {
				myList = myMap.get(Owen);
			}
			//if key Owen not in map, put Owen in map and assign Owen a 
			//new string arrayList to hold its values
			if(! myMap.containsKey(Owen)) {
				myMap.put(Owen, new ArrayList<String>());
			}
			//if you get to the end of the text, return PSEUDO_EOS and break
			if(i == myWords.length - myOrder) {
				myList.add(PSEUDO_EOS);
				myMap.put(Owen, myList);
				break;
			}
			//else add these to values called myList
			else {
				myList.add(myWords[i + myOrder]);
			}
			//build map with key and values
			myMap.put(Owen, myList);
		}
	}
	
	/**
	 * 
	 * @param WordGram Key
	 * @return keys matched to values if key is contained in map
	 * @throws NoSuchElementException if no key contained
	 * Overide getFollows constructor
	 * check weather or not Key is in new map
	 */

	@Override
	public ArrayList<String> getFollows(WordGram key) {
		ArrayList<String> StringsReturned = new ArrayList<String>();
		if (!myMap.containsKey(key)) {
			throw new NoSuchElementException(key + " not in map");
		} else {
			StringsReturned = myMap.get(key);
			return StringsReturned;

		}
	}
}
	


