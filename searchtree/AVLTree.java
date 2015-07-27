package searchtree;

import tree.BinaryNode;
import tree.BinaryNodeInterface;

public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> implements SearchTreeInterface<T>,java.io.Serializable {
	public AVLTree(){
		super();
	}
	
	public AVLTree(T rootEntry){
		super(rootEntry);
	}

	/*
	 *@Task : 在AVL树中添加元素
	 */
	public T add(T newEntry){
		T result = null;
		if(isEmpty())
			setRootNode(new BinaryNode<T>(newEntry));
		else
		{
			BinaryNodeInterface<T> rootNode = getRootNode();
			result = addEntry(rootNode, newEntry);
			setRootNode(rebalance(rootNode));
		}
		return result;
	}
	
	private T addEntry(BinaryNodeInterface<T> rootNode, T newEntry){
		assert rootNode != null;
		T result = null;
		int comparison = newEntry.compareTo(rootNode.getData());//待添加元素与树中已有元素比较以确定添加的位置
		if(comparison == 0){//待添加元素已存在于树中
			result = rootNode.getData();
			rootNode.setData(newEntry);//将新元素替换旧元素
		}
		else if(comparison < 0){//添加到左子树中
			if(rootNode.hasLeftChild()){//继承递归比较
				BinaryNodeInterface<T> leftChild = rootNode.getLeftChild();
				result = addEntry(leftChild, newEntry);
				rootNode.setLeftChild(rebalance(leftChild));
			}
			else
				rootNode.setLeftChild(new BinaryNode<T>(newEntry));
		}
		else//添加到右子树中
		{
			assert comparison > 0;
			if(rootNode.hasRightChild()){
				BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
				result = addEntry(rightChild, newEntry);
				rootNode.setRightChild(rebalance(rightChild));
			}
			else
				rootNode.setRightChild(new BinaryNode<T>(newEntry));
		}
		return result;
	}
	
	public T remove(T newEntry){
		return null;//暂未实现删除操作
	}
	
	/*
	 * @Task: 在 nodeN 结点上进行右旋操作
	 */
	private BinaryNodeInterface<T> rotateRight(BinaryNodeInterface<T> nodeN){
		BinaryNodeInterface<T> nodeL = nodeN.getLeftChild();
		nodeN.setLeftChild(nodeL.getRightChild());
		nodeL.setRightChild(nodeN);
		return nodeL;
	}
	
	private BinaryNodeInterface<T> rotateLeft(BinaryNodeInterface<T> nodeN){
		BinaryNodeInterface<T> nodeR = nodeN.getRightChild();
		nodeN.setRightChild(nodeR.getLeftChild());
		nodeR.setLeftChild(nodeN);
		return nodeR;
	}
	
	private BinaryNodeInterface<T> rotateRightLeft(BinaryNodeInterface<T> nodeN){
		BinaryNodeInterface<T> nodeR = nodeN.getRightChild();
		nodeN.setRightChild(rotateRight(nodeR));
		return rotateLeft(nodeN);
	}
	
	private BinaryNodeInterface<T> rotateLeftRight(BinaryNodeInterface<T> nodeN){
		BinaryNodeInterface<T> nodeL = nodeN.getLeftChild();
		nodeN.setLeftChild(rotateLeft(nodeL));
		return rotateRight(nodeN);
	}
	
	private BinaryNodeInterface<T> rebalance(BinaryNodeInterface<T> nodeN){
		int heightDifference = getHeightDifference(nodeN);
		if(heightDifference > 1){//左子树比右子树高
			if(getHeightDifference(nodeN.getLeftChild()) > 0)
				nodeN = rotateRight(nodeN);//插入在左子树的左孩子中
			else
				nodeN = rotateLeftRight(nodeN);
		}
		else if(heightDifference < -1){//右子树比左子树高
			if(getHeightDifference(nodeN.getRightChild()) < 0)
				nodeN = rotateLeft(nodeN);//插入在右子树的右孩子中
			else
				nodeN = rotateRightLeft(nodeN);
		}
		return nodeN;
	}
	
	//获得结点nodeN的左右子树的高度之差
	private int getHeightDifference(BinaryNodeInterface<T> nodeN){
		int leftHeight = 0;
		int rightHeight = 0;
		if(nodeN.getLeftChild() != null){
			leftHeight = nodeN.getLeftChild().getHeight();
		}
		if(nodeN.getRightChild() != null){
			rightHeight = nodeN.getRightChild().getHeight();
		}
		return leftHeight - rightHeight;
	}
}