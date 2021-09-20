package DiGraph_A5;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;


public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph
  HashMap<Long, DiGraphNode> nodes;
  HashMap<String, Long> id_name;
  HashMap<Long, DiGraphEdge> edges;
  int numNodes;
  int numEdges;
  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
	nodes = new HashMap<>(); 
	id_name = new HashMap<>();
	edges = new HashMap<>();
  }
@Override
public boolean addNode(long idNum, String label) {
	// returns false if node number is not unique, or less than 0
	if(idNum < 0)
		return false;
	if(nodes.containsKey(idNum))
		return false;
	//returns false if label is not unique (or is null)
	if(label.equals(null))
		return false;
	if(id_name.containsKey(label))
		return false;
	// create node 
	DiGraphNode node = new DiGraphNode(idNum, label);
	// add node to HashMap
	nodes.put(idNum, node);
	// add label/id to id_name;
	id_name.put(label, idNum);
	numNodes++;
	return true;
}

public boolean addEdge(long idNum, String sLabel, String dLabel, String eLabel) {
	return addEdge(idNum, sLabel, dLabel, 1, eLabel);
}
@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	//returns false if edge number is not unique or less than 0
	if(idNum < 0 || edges.containsKey(idNum))
		return false;
    // returns false if source node is not in graph
	if(!id_name.containsKey(sLabel))
		return false;
    // returns false if destination node is not in graph
	if(!id_name.containsKey(dLabel))
		return false;
    // returns false is there already is an edge between these 2 nodes 
	if(nodes.get(id_name.get(sLabel)).checkEdgeExists(dLabel) >= 0)
		return false;
	//add edge to appropriate node
	DiGraphNode destination = nodes.get(id_name.get(dLabel));
	nodes.get(id_name.get(sLabel)).addEdge(idNum, destination, weight, eLabel);
	//add edge to edges
	DiGraphEdge edge = new DiGraphEdge(idNum, destination, weight, eLabel);
	edges.put(idNum, edge);
	numEdges++;
	return true;
}
@Override
public boolean delNode(String label) {
	// return false if node does not exist
	if(!id_name.containsKey(label))
		return false;
	// remove edges where this node was a source
	nodes.get(id_name.get(label)).removeAllEdges(); 
	// remove edges where this node was a destination 
	Iterator<DiGraphNode> iter = nodes.values().iterator();
	while(iter.hasNext()) { 
		DiGraphNode temp = iter.next();
		long temp_id = temp.removeEdge(label);
	}
	// remove node from nodes
	nodes.remove(id_name.get(label));
	// remove node from id_name
	id_name.remove(label);
	numNodes--;
	return true;
}
@Override
public boolean delEdge(String sLabel, String dLabel) {
	// return false if source does not exist
	if(!id_name.containsKey(sLabel))
		return false;
	// return false if destination does not exist
	if(!id_name.containsKey(dLabel))
		return false;
	// return false if the edge does not exist
	if(nodes.get(id_name.get(sLabel)).checkEdgeExists(dLabel) == -1)
		return false;
	// remove edge from source node and get edge ID 
	long edgeID = nodes.get(id_name.get(sLabel)).removeEdge(dLabel);
	// remove edge from edges
	edges.remove(edgeID);
	return true;
}
@Override
public long numNodes() {
	return numNodes;
}
@Override
public long numEdges() {
	 Iterator<DiGraphNode> iterator = nodes.values().iterator();
	 int count = 0;
	 while(iterator.hasNext()) {
	 DiGraphNode temp = iterator.next();
	 count = count + temp.countEdges();
	 }
	 return count;
}
@Override
public ShortestPathInfo[] shortestPath(String label) {
	for (DiGraphNode n : nodes.values()) {
		n.setDistance(Long.MAX_VALUE);
		n.setVisited(false);
		if (n.getLabel().equals(label)) {
			n.setDistance(0);
			n.setVisited(true);
		}
	}
	
	nodes.get(id_name.get(label)).setDistance(0);
	PriorityQueue<DiGraphNode> pq = new PriorityQueue<DiGraphNode>(nodes.size(), new NodeComparator());
	pq.add(nodes.get(id_name.get(label)));
	nodes.get(id_name.get(label)).setVisited(true);

	DiGraphNode head, adjacent;
	
	while(!pq.isEmpty()) {
		head = pq.poll();
		
		for(DiGraphEdge e : head.getEdges()) {
			adjacent = e.getDestination();
			if (!adjacent.getVisited()) {
				if (head.getDistance() + e.getWeight() < adjacent.getDistance()) {
					pq.remove(adjacent);
					adjacent.setDistance(head.getDistance() + e.getWeight());
					pq.add(adjacent);
				}
			}
		}
		head.setVisited(false);
	}
	
	// initialize ShortestPath array
	ShortestPathInfo[] paths = new ShortestPathInfo[numNodes];
	// filling in paths array
	int index = 0;
	for (DiGraphNode i : nodes.values()) {
		if (i.getDistance() == Long.MAX_VALUE)
			i.setDistance(-1); // for unreachable nodes
		paths[index] = new ShortestPathInfo(i.getLabel(), i.getDistance());
		index++;
	}
	return paths;
}

}
