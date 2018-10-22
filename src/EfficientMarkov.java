import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
/**
 * 
 * @author Collins Abanda
 *Created EfficientMarkov as extension of BaseMarkov
 *Initialized new map called myMap
 */
public class EfficientMarkov extends BaseMarkov{
	private Map<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
	
//created EJfficientMarkov constructor
//myMap assigned to map of string and arraylist
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}
//set this to 3 as in BaseMarkov	
	public EfficientMarkov() {
		this(3);
	}
	//created setTraining constructor and called override
	@Override
	public void setTraining(String text) {
		//Initialize text to variable myText
		//initialized the values as myVal
		//cleared map 
		myText = text;
		String myVal = "";   
		myMap.clear();
		//for loop loops through the text to extract map keys from text
		for(int i=0; i <= myText.length() - myOrder + 1; i++){
			//myText.substring gets characters from index i up to but not
			//including index i+ myOrder
			String myKey = myText.substring(i, i + myOrder);
			//if key in map, set key value
			ArrayList<String> next = new ArrayList<String>();
			if (myMap.containsKey(myKey)) {
				next = myMap.get(myKey);
			}
			//if key not in map, put key and assign an array list for values
			if(! myMap.containsKey(myKey)) {
				myMap.put(myKey, new ArrayList<String>());
			}
			//when the code gets to the last set of keys, return PSEUDO_EOS
			if(i == myText.length() - myOrder) {
				next.add(PSEUDO_EOS);
				myMap.put(myKey, next);
				break;
			//break out of for loop in order to not have an out of bounds exception
			}
			else {
				//initialize vals to this case otherwise
				myVal = myText.substring(i + myOrder, i + myOrder + 1);
				next.add(myVal);
			}
			//builds map with key and arraylist of values 
			myMap.put(myKey, next);
			
		}

	}
	/**
	 * created the getFollows constructor
	 * check to see if map contains key or not 
	 * return null at the end 
	 */
	@Override
	public ArrayList<String> getFollows(String key){
		if (myMap.containsKey(key)) {
			return myMap.get(key);
		}
		if(!myMap.containsKey(key)) {
			throw new NoSuchElementException(key+" not in map");
		}
	return null;	
	
	}
	
}