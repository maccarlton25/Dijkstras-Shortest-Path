package DiGraph_A5;

public class DiGraphPlayground {

	  public static void main (String[] args) {
	  
	      // thorough testing is your responsibility
	      //
	      // you may wish to create methods like 
	      //    -- print
	      //    -- sort
	      //    -- random fill
	      //    -- etc.
	      // in order to convince yourself your code is producing
	      // the correct behavior
		  efTest();
	    }
	  
	  	public static void delNode0() {
	  		DiGraph d = new DiGraph();
	  		d.addNode(1, "f");
	  		d.addNode(3, "s");
	  		System.out.println(d.numNodes());
	  		boolean temp = d.delNode("f");
	  		System.out.println(temp);
	  		System.out.println(d.numNodes());
	  	}
	  	
	    public static void test1(){
	      DiGraph d = new DiGraph();
		  for (int i = 0; i < 1000000; i++) {
		  d.addNode(i, i+"n");
		  int temp = i - 1;
		  d.addEdge((long)i, i+"n", temp +"n", null);
		  }
		  System.out.println("numNodes: "+d.numNodes());
		  System.out.println("numEdges: "+d.numEdges());
		  }
		  	
	  	public static void efTest() {
	  		DiGraph d = new DiGraph();
	  		int x = 100000; // change number
	  		String[] arr = new String[x];
	  		
	  		System.out.println("start");
	  		long start = System.currentTimeMillis();
	  		
	  		for (int i =0; i < x; i++) {
	  		arr[i] = i + ""; // just gives us a random label
	  		if( i == 0) {
	  		d.addNode(50000000, "0"); // needed solid start node
	  		}
	  		d.addNode(i,arr[i]);
	  		
	  		if( i % 2 == 0) {
	  		d.addEdge(i, "0", arr[i], i, "");
	  		}
	  		d.addEdge(i,arr[i], arr[i%(arr.length-1)], 1, "");
	  		d.addEdge(i,arr[i], arr[i%(arr.length-2)],1, "");
	  		}

	
	  		long end = System.currentTimeMillis();
	  		long elapsedTime = end - start;
	  		System.out.println("add: " + elapsedTime); // total time in milliseconds
	  		
	  		ShortestPathInfo[] t = d.shortestPath("0");
	  		//This prints each statement of ShortInfo
	  		//for (int i = 0; i < t.length; i++) {
	  		//	System.out.println(t[i]);
	  		//}
	  		
	  		long endShort = System.currentTimeMillis();
	  		long shortestEnd = endShort - start;
	  		System.out.println("shortest: " + shortestEnd);
	  		System.out.println("done");

	  		}

	  	
	    public static void delEdge1() {
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	d.addEdge(0, "f", "s", null);
	    	d.delEdge("f", "x");
	    	d.delEdge("x", "f");
	    }
	    public static void exTest(){
	      DiGraph d = new DiGraph();
	      d.addNode(1, "f");
	      d.addNode(3, "s");
	      d.addNode(7, "t");
	      d.addNode(0, "fo");
	      d.addNode(4, "fi");
	      d.addNode(6, "si");
	      d.addEdge(0, "f", "s", 0, null);
	      d.addEdge(1, "f", "si", 0, null);
	      d.addEdge(2, "s", "t", 0, null);
	      d.addEdge(3, "fo", "fi", 0, null);
	      d.addEdge(4, "fi", "si", 0, null);
	      System.out.println("numEdges: "+d.numEdges());
	      System.out.println("numNodes: "+d.numNodes());
	    }
	    public static void DelEdgeTest2() {
	    	DiGraph d = new DiGraph();
			d.delEdge("f","s");
			d.addNode(1,"f");
			d.addNode(3,"s");
			d.addEdge(0,"f","s", null);
			d.delEdge("f","s");
			d.delEdge("f","s");
			d.addEdge(0,"f","s", null);
			d.delEdge("f","s");
		    System.out.println("numEdges: "+d.numEdges());
		    System.out.println("numNodes: "+d.numNodes());
	    }
	    public static void SPT0() {
	    	DiGraph d = new DiGraph();
	    	for(int i = 0; i < 7; i++) {
	    		d.addNode(i, i + "");
	    	}
	    	d.addEdge(0, "0", "5", 3, null);
	    	d.addEdge(1, "3", "2", 6, null);
	    	d.addEdge(2, "4", "0", 1, null);
	    	d.addEdge(3, "4", "5", 2, null);
	    	d.addEdge(4, "6", "1", 4, null);
	    	d.shortestPath("0");
	    	
	    }
	    public static void SPT1() {
	    	DiGraph d = new DiGraph();
	    	d.addNode(0, "a");
	    	d.addNode(1, "b");
	    	d.addNode(2, "c");
	    	d.addEdge(1, "a", "b", 3, null);
	    	d.addEdge(2, "a", "c", 10, null);
	    	d.addEdge(3, "b", "c", 4, null);
	    	d.shortestPath("a");
	    	
	    }
	    public static void SPT3() {
	    	DiGraph d = new DiGraph();
	    	d.addNode(0, "p");
	    	d.addNode(1, "a");
	    	d.addNode(2, "g");
	    	d.addNode(3, "e");
	    	d.addEdge(0, "p", "a", 3, null);
	    	d.addEdge(0, "a", "c", 10, null);
	    	d.addEdge(0, "b", "c", 4, null);
	    	d.shortestPath("a");
	    	
	    }
}