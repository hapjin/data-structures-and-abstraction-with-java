package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import list.SequenceStack;
import list.Stack;

public class BinaryTree<T> implements BinaryTreeInterface<T>, java.io.Serializable{

	private BinaryNodeInterface<T> root;//树根结点

	private class InorderIterator<T> implements Iterator<T>{

		//定义一个顺序栈nodeStack来存放遍历过程中遇到的结点
		private Stack<BinaryNodeInterface<T>>nodeStack;//list包中有顺序栈的实现
		private BinaryNodeInterface<T> currentNode;
		public InorderIterator(){
			nodeStack = new SequenceStack<BinaryNodeInterface<T>>();
			currentNode = (BinaryNodeInterface<T>) root;//此处为什么需要强制转换呢？
		}
		
		/*
		 * 按照中序遍历的逻辑进行实现Iterator接口中的方法，从而实现一个迭代器
		 */
		@Override
		public boolean hasNext() {
			return (!nodeStack.empty()) || (currentNode != null);
		}

		@Override
		public T next() {
			BinaryNodeInterface<T> nextNode = null;
			while(currentNode != null){
				nodeStack.push(currentNode);
				currentNode = currentNode.getLeftChild();
			}
			if(!nodeStack.empty()){
				nextNode = nodeStack.pop();
				assert nextNode != null;
				currentNode = nextNode.getRightChild();
			}
			else
				throw new NoSuchElementException();
			return nextNode.getData();
		}

		@Override
		//仅仅是完成遍历的功能，删除功能是不需要有的。
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	public BinaryTree(){
		root = null;
	}
	public BinaryTree(T rootData){
		root = new BinaryNode<T>(rootData);
	}
	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
		privateSetTree(rootData, leftTree, rightTree);
	}
	
	@Override
	public void setTree(T rootData) {
		root = new BinaryNode<T>(rootData);
	}

	@Override
	/*
	 *以rootData为根，leftTree为左子树，rightTree为右子树
	 *生成一颗新的二叉树，setTree()实际调用了privateSetTree()来构造二叉树
	 */
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
			BinaryTreeInterface<T> rightTree) {
			privateSetTree(rootData, (BinaryTree)leftTree, (BinaryTree)rightTree);
	}
	private void privateSetTree(T rootData, BinaryTree<T>leftTree, BinaryTree<T> rightTree){
		root = new BinaryNode<T>(rootData);
		if((leftTree != null) && (!leftTree.isEmpty()))
			root.setLeftChild(leftTree.root);
		if((rightTree != null) && (!rightTree.isEmpty())){
			if(rightTree != leftTree)
				root.setRightChild(rightTree.root);
			else
				root.setRightChild(rightTree.root.copy());
		}
		if((leftTree != null) && (leftTree != this))
			leftTree.clear();
		if((rightTree != null) && (rightTree != this))
			rightTree.clear();	
	}
	
	//更改根结点的数据域
	protected void setRootData(T rootData){
		root.setData(rootData);
	}
	
	//更改根结点
	protected void setRootNode(BinaryNodeInterface<T> rootNode){
		root = rootNode;
	}
	protected BinaryNodeInterface<T> getRootNode(){
		return root;
	}
	
	@Override
	//返回树的根节点的数据域
	public T getRootData() {
		T rootData = null;
		if(root != null)
			rootData = root.getData();//调用节点的getData(),返回该节点的数据域
		return rootData;
	}

	@Override
	//返回二叉树的高度
	public int getHeight() {
		return root.getHeight();//二叉树的高度即为以根结点为根的子树的高度
	}

	@Override
	//返回二叉树中结点的个数
	public int getNumberOfNodes() {
		return root.getNumberOfNodes();
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void clear() {
		root = null;
	}

	//中序遍历二叉树
	public void inorderTraverse(){
		inorderTraverse(root);
	}
	
	private void inorderTraverse(BinaryNodeInterface<T> node){
		if(node != null){
			inorderTraverse((BinaryNode)node.getLeftChild());
			System.out.println(node.getData());
			inorderTraverse((BinaryNode)node.getRightChild());
		}
	}
	
	public void preorderTraverse(){
		preorderTraverse(root);
	}
	
	private void preorderTraverse(BinaryNodeInterface<T> node){
		if(node != null){
			System.out.println(node.getData());
			preorderTraverse((BinaryNode)node.getLeftChild());
			preorderTraverse((BinaryNode)node.getRightChild());
		}
	}
	
	public void postorderTraverse(){
		postorderTraverse(root);
	}
	private void postorderTraverse(BinaryNodeInterface<T> node){
		ArrayList<T> list = new ArrayList<T>();
		if(node != null){
			postorderTraverse((BinaryNode)node.getLeftChild());
			postorderTraverse((BinaryNode)node.getRightChild());
//			System.out.println(node.getData());
			list.add(node.getData());
		}
	}
	@Override
	//获得先序遍历器的方法，由于在该类中没有定义生成先序迭代器的私有内部类，因此该方法为空实现
	public Iterator<T> getPreorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//获得后序遍历器的方法，由于在该类中没有定义生成后序迭代器的私有内部类，因此该方法为空实现
	public Iterator<T> getPostorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> getInorderIterator() {
		return new InorderIterator();
	}

	@Override
	//获得层次遍历器的方法，由于在该类中没有定义生成层次迭代器的私有内部类，因此该方法为空实现
	public Iterator<T> getLevelOrderIterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
