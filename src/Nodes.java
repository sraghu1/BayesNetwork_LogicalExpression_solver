import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Nodes {

	
	private List<Nodes> parents = new ArrayList<Nodes>();
	
	private List<Nodes> child = new ArrayList<Nodes>();
	
	private Map<String, Float> map= new HashMap<String,Float>();

	public List<Nodes> getParents() {
		return parents;
	}

	public void addParents(Nodes node) {
		parents.add(node);
	}

	public List<Nodes> getChild() {
		return child;
	}

	public void addChild(Nodes node) {
		child.add(node);
	}

	public Map<String, Float> getMap() {
		return map;
	}

	public void addVal(String booleanVal, Float value) {
		map.put(booleanVal, value);
	}
	
	
	
}