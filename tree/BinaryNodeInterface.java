package tree;

//二叉树结点的接口
public interface BinaryNodeInterface<T> {
	public T getData();//返回结点的数据部分
	public void setData(T newData);//设置结点的数据域的值
	
	public BinaryNodeInterface<T> getLeftChild();//获取结点的左孩子
	public BinaryNodeInterface<T> getRightChild();//获取结点的右孩子
	
	public void setLeftChild(BinaryNodeInterface<T> leftChild);//设置结点的左孩子为指定结点
	public void setRightChild(BinaryNodeInterface<T> rightChild);//设置结点的右孩子为指定结点
	
	public boolean hasLeftChild();//判断结点是否有左孩子
	public boolean hasRightChild();//判断结点是否有右孩子
	
	public boolean isLeaf();//检查结点是否是叶子结点
	
	public int getNumberOfNodes();//计算以该结点为根的子树的结点数目
	public int getHeight();//计算以该结点为根的子树的高度
	public BinaryNodeInterface<T> copy();//复制以该结点为根的子树
}
