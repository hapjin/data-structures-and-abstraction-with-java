package graph;

import java.util.Queue;
import java.util.Stack;

public class TestGraph {
	public static void main(String[] args) {
		GraphInterface<String> graph = new DirectedGraph<>();
		System.out.println("Graph is empty? " + graph.isEmpty());
		
		System.out.println("Adding vertexs...");
		graph.addVertex("A");graph.addVertex("B");
		graph.addVertex("C");graph.addVertex("D");
		graph.addVertex("E");
		System.out.println("Number of graph's vertex = " + graph.getNumberOfVertices());//5
		
		/*
		 *   <A,D>  <A,C>  <A,B>  <D,C>  <C,E>
		 */
		System.out.println("Adding edges...");
		graph.addEdge("A", "D");graph.addEdge("A", "C");
		graph.addEdge("A", "B");graph.addEdge("D", "C");
		graph.addEdge("C", "E");
		System.out.println("Number of graph's edge = " + graph.getNumberOfEdges());//5
		
		System.out.println("vertexs between B and C has Edges? " + graph.hasEdge("B", "C"));//false
		System.out.println("vertex between D and C has Edges? " + graph.hasEdge("D", "C"));//true
		
		System.out.println("Breadth First traverse graph with initial vertex 'A'...");
		Queue<String> bfsTraversalOrder = graph.getBreadthFirstTraversal("A");//A D C B E
		while(!bfsTraversalOrder.isEmpty())
			System.out.print(bfsTraversalOrder.poll() + " ");
		
		System.out.println("\nDFS traverse graph with inital vertex 'A'...");
		Queue<String> dfsTraversalOrder = graph.getDepthFirstTraversal("A");
		while(!dfsTraversalOrder.isEmpty())
			System.out.print(dfsTraversalOrder.poll() + " ");
		
		System.out.println("\nTopological Order");
		Stack<String> stack = graph.getTopologicalSort();
		while(!stack.isEmpty())
			System.out.print(stack.pop() + " ");
		
		System.out.println("\ncleanning graph");
		graph.clear();
		System.out.println("Now, number of vertexs = " + graph.getNumberOfVertices());
		
	}
}
