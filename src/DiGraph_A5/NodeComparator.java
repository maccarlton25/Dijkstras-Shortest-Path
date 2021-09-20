package DiGraph_A5;

import java.util.Comparator;

public class NodeComparator implements Comparator<DiGraphNode>{

	@Override
	public int compare(DiGraphNode o1, DiGraphNode o2) {
		if(o1.getDistance() < o2.getDistance())
			return 1;
		else if (o1.getDistance() > o2.getDistance()) 
			return -1;
		else
			return 0;
	}

}
