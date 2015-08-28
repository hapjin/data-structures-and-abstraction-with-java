package graph;

import java.util.Stack;

public class TestGraph2 {
	public static void main(String[] args) {
		DirectedGraph<String> graph = new DirectedGraph<>();
		String[] labelGroup = "1 2 3 4 5 6 7 8 9 10".split(" ");
		for(String str : labelGroup)
			graph.addVertex(str);
		graph.addEdge(labelGroup[0],labelGroup[1]);//<1, 2>
		graph.addEdge(labelGroup[1], labelGroup[2]);//<2, 3>
		graph.addEdge(labelGroup[1], labelGroup[4]);//<2, 5>
		graph.addEdge(labelGroup[1], labelGroup[3]);//<2, 4>
		graph.addEdge(labelGroup[3], labelGroup[5]);//<4, 6>
		graph.addEdge(labelGroup[3], labelGroup[6]);//<4, 7>
		graph.addEdge(labelGroup[5], labelGroup[7]);//<6, 8>
		graph.addEdge(labelGroup[6], labelGroup[7]);//<7, 8>
		graph.addEdge(labelGroup[6], labelGroup[8]);//<7, 9>
		graph.addEdge(labelGroup[4], labelGroup[9]);//<5, 10>
		graph.addEdge(labelGroup[8], labelGroup[9]);//<9, 10>
		
		System.out.println("Topological Sort:");
		Stack<String> stack = graph.getTopologicalSort();
		while(!stack.isEmpty())
			System.out.print(stack.pop() + " ");//1 2 4 7 9 5 10 6 8 3 
	}
}
