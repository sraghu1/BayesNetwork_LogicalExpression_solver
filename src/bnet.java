import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class bnet {

	// Creating table
	static Map<String, Boolean> cause = new HashMap<String, Boolean>();
	static Map<String, Boolean> given = new HashMap<String, Boolean>();
	static Map<String, Nodes> tree = new HashMap<String, Nodes>();
	static Set<String> allElements = new HashSet<String>();
	
	static {
		Nodes burglary = new Nodes();
		Nodes earthquake = new Nodes();
		Nodes alarm = new Nodes();
		Nodes johnCalls = new Nodes();
		Nodes maryCalls = new Nodes();
		burglary.addChild(alarm);
		burglary.addVal(String.valueOf(true), 0.001f);
		earthquake.addChild(alarm);
		earthquake.addVal(String.valueOf(true), 0.002f);
		alarm.addParents(burglary);
		alarm.addParents(earthquake);
		alarm.addChild(johnCalls);
		alarm.addChild(maryCalls);
		alarm.addVal("truetrue", 0.95f);
		alarm.addVal("truefalse", 0.94f);
		alarm.addVal("falsetrue", 0.29f);
		alarm.addVal("falsefalse", 0.001f);
		johnCalls.addParents(alarm);
		johnCalls.addVal(String.valueOf(true), 0.90f);
		johnCalls.addVal(String.valueOf(false), 0.05f);
		maryCalls.addParents(alarm);
		maryCalls.addVal(String.valueOf(true), 0.70f);
		maryCalls.addVal(String.valueOf(false), 0.01f);
		tree.put("B", burglary);
		tree.put("E", earthquake);
		tree.put("A", alarm);
		tree.put("J", johnCalls);
		tree.put("M", maryCalls);
		allElements.add("B");
		allElements.add("E");
		allElements.add("A");
		allElements.add("J");
		allElements.add("M");
	}

	private float computeProbability(boolean b, boolean e, boolean a,	boolean j, boolean m) {
		float b_value, e_value, a_value, j_value, m_value;		
		a_value = tree.get("A").getMap().get(String.valueOf(b) + String.valueOf(e));
		b_value = tree.get("B").getMap().get("true");
		e_value = tree.get("E").getMap().get("true");		
		j_value = tree.get("J").getMap().get(String.valueOf(a));
		m_value = tree.get("M").getMap().get(String.valueOf(a));
		if (a == false)	a_value = (1 - a_value);
		if (b == false) b_value = (1 - b_value);
		if (e == false) e_value = (1 - e_value);
		if (j == false) j_value = (1 - j_value);
		if (m == false) m_value = (1 - m_value);
		
		// System.out.printf("%.15f",b_value * e_value * a_value * j_value * m_value);
	    // System.out.println();
		return (b_value * e_value * a_value * j_value * m_value);
	}

	public static void main(String[] args) {

		bnet bn = new bnet();
		String sentence = "";
		for (String s : args)
			sentence += s + " ";
		sentence = sentence.substring(0, sentence.length() - 1);
		String[] splitWithGiven = sentence.split("given");
		String[] firstPart = splitWithGiven[0].split(" ");

		for (int i = 0; i < firstPart.length; i++) {
			switch (firstPart[i].charAt(1)) {
			case 't':
				String val = String.valueOf(firstPart[i].charAt(0));
				if(cause.get(val)!=null && cause.get(val)==false){
				System.out.print("Probablity for '" + sentence + "' is : 0.0" );
				return;
				}
				cause.put(String.valueOf(firstPart[i].charAt(0)), true);
				break;
			case 'f':
				val = String.valueOf(firstPart[i].charAt(0));
				if(cause.get(val)!=null && cause.get(val)==true){
					System.out.print("Probablity for '" + sentence + "' is : 0.0" );
					return;
					}
				cause.put(String.valueOf(firstPart[i].charAt(0)), false);
				break;
			}
		}
		if (splitWithGiven.length == 2) {
			splitWithGiven[1] = splitWithGiven[1].substring(1, splitWithGiven[1].length());
			String[] secondPart = splitWithGiven[1].split(" ");
			for (int i = 0; i < secondPart.length; i++) {
				switch (secondPart[i].charAt(1)) {
				case 't':
					String val = String.valueOf(secondPart[i].charAt(0));
					if(cause.get(val)!=null && cause.get(val)==false){
						System.out.print("Probablity for '" + sentence + "' is : 0.0" );
						return;						
					}
					given.put(String.valueOf(secondPart[i].charAt(0)), true);
					break;
				case 'f':
					val = String.valueOf(secondPart[i].charAt(0));
					if(cause.get(val)!=null && cause.get(val)==true){
						System.out.print("Probablity for '" + sentence + "' is : 0.0" );
						return;						
					}
					given.put(String.valueOf(secondPart[i].charAt(0)), false);
					break;
				}
			}
		}

		double number = bn.compute(cause, given);
		String result = String.format("%f", number);
		
		if(!result.equals("0.000000"))
			System.out.print("Probablity for '" + sentence + "' is : " + result);
		else{
			System.out.print("Probablity for '" + sentence + "' is : ");
			System.out.printf("%.10f", number);
		}		
	}
	
	
	private float compute(Map<String, Boolean> cause2, Map<String, Boolean> given2) {
		Map<String, Boolean> numerator = new HashMap<String, Boolean>();
		Set<String> numeratorAbsentElements = new HashSet<String>();
		numeratorAbsentElements.addAll(allElements);
		Set<String> denominatorAbsentElements = new HashSet<String>();
		denominatorAbsentElements.addAll(allElements);
		Iterator it = cause.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			numerator.put(String.valueOf(pair.getKey()),(Boolean) pair.getValue());		
		}
		it = cause.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Boolean booleanVal = (Boolean)pair.getValue();			
		}		
		
		it = given2.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			numerator.put(String.valueOf(pair.getKey()),(Boolean) pair.getValue());
		}
		it = numerator.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			numeratorAbsentElements.remove(String.valueOf(pair.getKey()));

		}

		List<String> absentNumeratorElements = new ArrayList<String>();
		absentNumeratorElements.addAll(numeratorAbsentElements);
		
		float denom = 1;
		Map<String, Boolean> denominator = given2;
		it = denominator.entrySet().iterator();
		while (it.hasNext()) {			
			Map.Entry pair = (Map.Entry) it.next();
			denominatorAbsentElements.remove(String.valueOf(pair.getKey()));
		}
		
		float num = computePermutation(numerator, absentNumeratorElements);

		if (given2.size() > 0) {
			List<String> absentDenominatorElements = new ArrayList<String>();
			absentDenominatorElements.addAll(denominatorAbsentElements);
			denom = computePermutation(numerator, absentDenominatorElements);
		}
		return num / denom;
	}

	float computePermutation(Map<String, Boolean> map,	List<String> absentElements) {
		float result = 0;
		int n = absentElements.size();
		if (n <= 4 && n > 0)
			for (int i = 0; i < Math.pow(2, n); i++) {
				String bin = Integer.toBinaryString(i);
				while (bin.length() < n)
					bin = "0" + bin;
				char[] chars = bin.toCharArray();
				boolean[] boolArray = new boolean[n];
				for (int j = 0; j < chars.length; j++) {
					boolArray[j] = chars[j] == '0' ? true : false;
					map.put(absentElements.get(j), boolArray[j]);
				}
				result += computeProbability(map.get("B"), map.get("E"),map.get("A"), map.get("J"), map.get("M"));
			}
		else
			result = computeProbability(map.get("B"), map.get("E"),	map.get("A"), map.get("J"), map.get("M"));
		return result;
	}
}