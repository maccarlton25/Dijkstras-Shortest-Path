package DiGraph_A5;

import java.util.HashMap;
import java.util.LinkedList;

public class DiGraphNode {
	private long idNum;
	private String label;
	private boolean visited;
	private LinkedList<DiGraphEdge> edges;
	private HashMap<String, Integer> edge_label;
	// shortest path instance fields
	private long distance;
	private String destination;
	public DiGraphNode(long idNum, String label) {
		this.idNum = idNum;
		this.label = label;
		edges = new LinkedList<DiGraphEdge>();
		edge_label = new HashMap<String, Integer>();
	}
	public long getIdNum() {
		return idNum;
	}
	public String getLabel() {
		return label;
	}
	public LinkedList<DiGraphEdge> getEdges() {
		return edges;
	}
	public void addEdge(long idNum, DiGraphNode dLabel, long weight, String eLabel) {
		DiGraphEdge temp = new DiGraphEdge(idNum, dLabel, weight, eLabel);
		edges.add(temp);
		edge_label.put(dLabel.getLabel(), edges.size()-1);
	}
	public int checkEdgeExists(String dLabel) { // returns -1 if edge does not exist, index otherwise
		if(edge_label.containsKey(dLabel))
			return edge_label.get(dLabel);
		return -1;
	}
	public long removeEdge(String destination) {
		int temp = checkEdgeExists(destination);
		if(temp >= 0) {
			long edge_id = edges.get(temp).getIdNum();
			removeEdge(temp);
			edge_label.remove(destination);
			return edge_id;
		}
		return -1;
	}
	public void removeEdge(int index) {
		edges.remove(index);
	}
	public void removeAllEdges() {
		edges = new LinkedList<DiGraphEdge>();
		edge_label = new HashMap<String, Integer>();
	}
	public int countEdges() {
		return edges.size();
	}
	public long getDistance() {
		return this.distance;
	}
	public void setDistance(long qrstuv) {
		this.distance = qrstuv;
	}
	public String getDestination() {
		return this.destination;
	}
	public void setDestination(String qwerty) {
		this.destination = qwerty;
		return;
	}
	public boolean getVisited() {
		return this.visited;
	}
	public void setVisited(boolean bool) {
		this.visited = bool;
	}
}
