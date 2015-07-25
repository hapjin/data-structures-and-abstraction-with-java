package tree;

public class BinaryNode<T> implements BinaryNodeInterface<T>, java.io.Serializable{
	private T data;//结点的数据域
	private BinaryNode<T> left;//左孩子
	private BinaryNode<T> right;//右孩子
	
	public BinaryNode(){
		this(null);
	}
	//构造一个值为dataPortaion的结点
	public BinaryNode(T dataPortion){
		this(dataPortion, null, null);
	}
	
	public BinaryNode(T dataPortion, BinaryNode<T> leftChild, BinaryNode<T> rightChild){
		data = dataPortion;
		left = leftChild;
		right = rightChild;
	}
	
	
	@Override
	//返回结点的数据域的值
	public T getData() {
		return data;
	}

	@Override
	//更改结点数据域的值
	public void setData(T newData) {
		data = newData;
		
	}

	@Override
	//获得结点的左孩子
	public BinaryNodeInterface<T> getLeftChild() {
		return left;
	}

	@Override
	//获得结点的右孩子
	public BinaryNodeInterface<T> getRightChild() {
		return right;
	}

	@Override
	//更改结点的左孩子
	public void setLeftChild(BinaryNodeInterface<T> leftChild) {
		left = (BinaryNode<T>)leftChild;
	}

	@Override
	//更改结点的右孩子
	public void setRightChild(BinaryNodeInterface<T> rightChild) {
		right = (BinaryNode<T>)rightChild;
	}

	@Override
	public boolean hasLeftChild() {
		return left != null;
	}

	@Override
	public boolean hasRightChild() {
		return right != null;
	}

	@Override
	public boolean isLeaf() {
		return (left == null) && (right == null);
	}

	@Override
	//返回以该结点为根的子树中的结点的个数(包括根结点)
	public int getNumberOfNodes() {
		int leftNumber = 0;
		int rightNumber = 0;
		if(left != null)
			leftNumber = left.getNumberOfNodes();
		if(right != null)
			rightNumber = right.getNumberOfNodes();
		return 1 + leftNumber + rightNumber;
	}

	@Override
	//返回以此结点为根的子树的高度
	public int getHeight() {
		return getHeight(this);
	}
	
	private int getHeight(BinaryNode<T> node){
		int height = 0;
		if(node != null)
			height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
		return height;
	}

	@Override
	//该方法被构造二叉树的setTree()方法调用
	public BinaryNodeInterface<T> copy() {
		BinaryNode<T> newRoot = new BinaryNode<T>(data);
		if(left != null)
			newRoot.left = (BinaryNode<T>)left.copy();
		if(right != null)
			newRoot.right = (BinaryNode<T>)right.copy();
		return newRoot;
	}
}
