package graph;

import java.util.List;
import java.util.Queue;
import java.util.Stack;


public interface GraphAlgorithmsInterface<T>{
	/*
	 * Task 执行图的深度优先遍历
	 * @param origin 标识遍历的起点对象
	 * @return 遍历的顶点标识队列,起点的标记位于队列前端
	 */
	public Queue<T> getDepthFirstTraversal(T origin);
	
	public Queue<T> getBreadthFirstTraversal(T origin);
	
	/*
	 * Task 执行有向无环图的顶点的后拓扑排序
	 * @return 由栈顶开始按拓扑有序排列的顶点标识栈
	 */
	public Stack<T> getTopologicalSort();
	
	/**Task：寻找两个指定顶点之间的最短路径
	 * @param begin 标识路径起点
	 * @param end 标识路径终点
	 * @param path 初始为空的栈
	 * 			该栈保存沿最短路径的顶点
	 * 			起点标识位于栈顶,终点标识位于栈底
	 * @return 返回最短路径的长度
	 */
	public int getShortestPath(T begin, T end, Stack<T>path);
	
	/**Task: 寻找两个指定顶点间费用最低的路径
	 * @param begin 标识路径的起点
	 * @param end	标识路径的终点
	 * @param path	初始为空的栈
	 * 				该栈保存沿费用最低的路径的顶点
	 * 				起点标识位于栈顶,终点的标识位于栈底
	 * @return 返回费用最低路径的费用
	 */
	public double getCheapestPath(T begin, T end, Stack<T>path);
}