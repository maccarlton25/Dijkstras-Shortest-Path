package DiGraph_A5;

public class DiGraphEdge {
	private long id;
	private DiGraphNode destination;
	private long weight;
	private String eLabel;
	public DiGraphEdge(long idNum, DiGraphNode dLabel, long weight, String eLabel) {
		this.id = idNum;
		this.destination = dLabel;
		this.weight = weight;
		this.eLabel = eLabel;
	}
	public long getIdNum() {
		return id;
	}
	public DiGraphNode getDestination() {
		return destination;
	}
	public long getWeight() {
		return weight;
	}
	public String getELabel() {
		return eLabel;
	}
}
