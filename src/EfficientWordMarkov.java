import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov {
	private Map<WordGram, ArrayList<String>> myMap;

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
	 * @param source
	 * @param start
	 * @param size
	 */
	@Override
	public void setTraining(String text) {
		myWords = text.split("\\s+");
		myMap.clear();
		ArrayList<String> myList;

		for (int i = 0; i < myWords.length - myOrder + 1; i++) {
			myList = new ArrayList<String>();

			WordGram Owen = new WordGram(myWords, i, myOrder);

			String nextStr = " ";
			if (i < myWords.length - myOrder) {
				nextStr = myWords[i + myOrder];

			}
			if (i == myWords.length - myOrder) {
				nextStr = PSEUDO_EOS;

			}

			if (!myMap.containsKey(Owen)) {
				myList.add(nextStr);
				myMap.put(Owen, myList);

			}

			else {
				myList = myMap.get(Owen);
				myList.add(nextStr);
				myMap.put(Owen, myList);
			}
		}
	}

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
	


