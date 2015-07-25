package tree;

//JAVA接口可以多继承
public interface BinaryTreeInterface<T> extends TreeInterface<T> ,TreeIteratorInterface<T>{
	//以下这两个方法定义了如何构造二叉树
	public void setTree(T rootData);//构造一颗以rootData为根的二叉树
	//构造一颗以rootData为根,leftTree为左子树，rightTree为右子树的二叉树
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree);
}
