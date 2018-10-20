import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientMarkov extends BaseMarkov{
	Map<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	
	public EfficientMarkov() {
		this(3);
	}

	public void setTraining(String text) {
		myText = text;
		String myVal = " ";   
		myMap.clear();
		for(int i=0; i <= myText.length() - myOrder ; i++){
			String myKey = myText.substring(i, i+ myOrder);
			if(! myMap.containsKey(myKey)) {
				myMap.put(myKey, new ArrayList<String>());
			}
			myVal = Character.toString(myText.charAt(i + myOrder));
			ArrayList<String> next = new ArrayList<String>();
			next = myMap.get(myKey);
			next.add(myVal);
			myMap.put(myKey, next );
			
			if(i == myText.length() - myOrder) {
				next.add(PSEUDO_EOS);
			}
			
			//myMap.put(myKey, myMap.get(myKey);
		}

	}
	
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