package searchtree;

import tree.BinaryNode;
import tree.BinaryNodeInterface;
import tree.BinaryTree;
import tree.BinaryTreeInterface;

public class BinarySearchTree<T extends Comparable<? super T>> extends
		BinaryTree<T> implements SearchTreeInterface<T> {
	public BinarySearchTree() {
		super();
	}

	public BinarySearchTree(T rootEntry) {
		super();
		setRootNode(new BinaryNode<T>(rootEntry));
	}

	// 排序树不支持从二叉树中继承来的修改树中结点的值的操作
	// 因为二叉树排序中要求结点中的值是中序遍历有序的，不能随便进行修改
	public void setTree(T rootData) {
		throw new UnsupportedOperationException();
	}

	public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
			BinaryTreeInterface<T> rightTree) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(T entry) {
		return entry == getEntry(entry);
	}

	// 查找是否有数据域为entry的节点，若有，则返回该数据域，否则返回null
	@Override
	public T getEntry(T entry) {
		return findEntry(getRootNode(), entry);
	}

	private T findEntry(BinaryNodeInterface<T> rootNode, T entry) {
		T result = null;
		if (rootNode != null) {
			T rootEntry = rootNode.getData();
			// if(entry.compareTo(rootEntry) == 0)
			if (entry.equals(rootEntry))
				result = rootEntry;
			else if (entry.compareTo(rootEntry) < 0)
				// 理解这里的递归调用的返回值，result不为空的唯一情况是equals()为真
				result = findEntry(rootNode.getLeftChild(), entry);
			else
				result = findEntry(rootNode.getRightChild(), entry);
		}
		return result;
	}

	@Override
	public T add(T newEntry) {
		T result = null;
		if (isEmpty())
			setRootNode(new BinaryNode(newEntry));// 处理树为空时，添加新节点的情况
		else
			result = addEntry(getRootNode(), newEntry);
		return result;
	}

	private T addEntry(BinaryNodeInterface<T> rootNode, T newEntry) {
		assert rootNode != null;
		T result = null;
		// 将根结点和待插入的结点进行比较
		int comprasion = newEntry.compareTo(rootNode.getData());
		/*
		 * 如果将一个与树中某个元素相匹配的元素插入树时 将新元素替换旧元素，并返回旧元素。其实这样做，是为了保证插入的完整性
		 * 即相当于，给定一个元素，它总能插入成功。 如果待插入的元素不在树中，插入成功，并返回null
		 */
		if (comprasion == 0) {
			result = rootNode.getData();
			rootNode.setData(newEntry);
		} else if (comprasion < 0) {
			if (rootNode.hasLeftChild())
				// 递归调用.注意调用的result的返回值
				result = addEntry(rootNode.getLeftChild(), newEntry);
			else
				rootNode.setLeftChild(new BinaryNode<T>(newEntry));
		} else {
			assert comprasion > 0;
			if (rootNode.hasRightChild())
				result = addEntry(rootNode.getRightChild(), newEntry);
			else
				rootNode.setRightChild(new BinaryNode<T>(newEntry));
		}
		return result;
	}

	// 插入新元素的迭代方法实现
	private T addEntry(T newEntry) {
		BinaryNodeInterface<T> currentNode = getRootNode();
		assert currentNode != null;// 根结点为空的情况已经在add()中处理
		T result = null;
		boolean found = false;// 用来标记待插入的元素是否已经在树中
		while (!found) {
			T currentEntry = currentNode.getData();
			int comparison = newEntry.compareTo(currentEntry);
			if (comparison == 0) {// 待插入的元素在树中"已存在"(根据compareTo()来决定)
				found = true;// 待插入的元素已经找到了位置，不需要再while循环了
				result = currentEntry;// 将树中的旧元素数据域赋值给result，由result返回
				currentNode.setData(newEntry);// 将新的数据域替换掉旧的数据域
			} else if (comparison < 0) {// 待插入的结点值比当前结点值要小
				if (currentNode.hasLeftChild())// 判断当前结点是否有左孩子
					// 在下一轮while中，需要将待插入结点与其左孩子比较
					currentNode = currentNode.getLeftChild();
				else {// 若没有左孩子，则说明待插入的结点的位置即为currentNode的左孩子
					found = true;// 因而将found设为true，不需要再进行循环了
					// 已经找到插入的位置了，下面语句即将结点插入。整个插入过程就完成了
					currentNode.setLeftChild(new BinaryNode<T>(newEntry));
				}
			} else {
				assert comparison > 0;
				if (currentNode.hasRightChild())
					currentNode = currentNode.getRightChild();
				else {
					found = true;
					currentNode.setRightChild(new BinaryNode<T>(newEntry));
				}
			}
		}// end while
		return result;
	}

	class ReturnObject{
		T entry;
		public ReturnObject(T entry){
			this.entry = entry;
		}
		public void set(T entry){
			this.entry = entry;
		}
		public T get(){
			return entry;
		}
	}
	
	@Override
	public T remove(T entry) {
		//将oldEntry的属性entry设置为空。这样，当remove()没有找到删除元素时将返回null
		ReturnObject oldEntry = new ReturnObject(null);
		BinaryNodeInterface<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
		setRootNode(newRoot);
		return oldEntry.get();
	}
	/*由于remove()方法可以删除根结点，因此须保留一个指向树的根的引用
	 * 因而让removeEntry()返回删除结点后的树的根结点,该树根就可以用remove()来保存了
	 * 但是，remove()方法删除结点时，它需要返回待删除的结点的值，而具体执行删除操作的removeEntry()返回的是新的树根
	 * 因此，需要一个内部类ReturnObject来获取被删除结点的值
	 */
	private BinaryNodeInterface<T> removeEntry(BinaryNodeInterface<T> rootNode, T entry, ReturnObject oldEntry){
		if(rootNode != null){
			T rootData = rootNode.getData();
			int comparison = entry.compareTo(rootData);
			if(comparison == 0){//找到了待删除的结点，它相当于"根结点"
			//将其对应的数据域放到oldEntry的entry属性中，而在remove方法中调用oldEntry的get方法会得到它
				oldEntry.set(rootData);
				rootNode = removeFromRoot(rootNode);
			}//end if
			else if(comparison < 0){//待删结点在当前结点的左子树中
				BinaryNodeInterface<T> leftChild = rootNode.getLeftChild();
				//递归调用说明：真正删除结点的操作是从树根开始直至找到该结点(相当于找到了以待删结点为根的子树)然后调用removeFromRoot方法进行删除
				BinaryNodeInterface<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
				rootNode.setLeftChild(subtreeRoot);//???
			}
			else{//待删结点在当前结点的右子树中
				BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
				BinaryNodeInterface<T> subtreeRoot = removeEntry(rightChild, entry, oldEntry);
				rootNode.setRightChild(subtreeRoot);//???
			}
		}//end if
		return rootNode;
	}
	private BinaryNodeInterface<T> removeFromRoot(BinaryNodeInterface<T> rootNode){
		if(rootNode.hasLeftChild() && rootNode.hasRightChild()){//当待删结点即有左孩子又有右孩子时
			BinaryNodeInterface<T> leftSubtreeRoot = rootNode.getLeftChild();//某结点的前驱结点的值为其左子树中结点的最大值,因而先获取其左子树的根结点
			BinaryNodeInterface<T> largestNode = findLargest(leftSubtreeRoot);//寻找待删结点的前驱结点
			rootNode.setData(largestNode.getData());//将待删结点的值替换为其前驱结点的值
			rootNode.setLeftChild(removeLargest(leftSubtreeRoot));//删除其前驱结点
		}
		//待删结点只有一个孩子时
		else if(rootNode.hasRightChild())//待删结点只有一个右孩子时
			rootNode = rootNode.getRightChild();//直接删除该结点，即将其右孩子覆盖它
		else//待删结点只有一个左孩子(或者没有孩子时)
			rootNode = rootNode.getLeftChild();
		return rootNode;//返回的是待删除后的(子)树的根结点
	}

	/*
	 * @Task: 在指定树中寻找含最大元素的结点
	 * @param rootNode 树的根结点
	 * @return 数据域值最大的结点
	 * 用迭代怎么来实现？？？？
	 */
	private BinaryNodeInterface<T> findLargest(BinaryNodeInterface<T> rootNode){
		if(rootNode.hasRightChild())
			rootNode = findLargest(rootNode.getRightChild());
		return rootNode;
	}
	
	/*
	 * @Task: 在指定树中删除含最大元素的结点
	 * @param rootNode 树的根结点
	 * @return 修改后的树的根结点
	 * 用迭代怎么来实现？？？？
	 */
	private BinaryNodeInterface<T> removeLargest(BinaryNodeInterface<T> rootNode){
		if(rootNode.hasLeftChild()){
			BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
			BinaryNodeInterface<T> root = removeLargest(rightChild);
			rootNode.setRightChild(root);
		}
		else
			rootNode = rootNode.getLeftChild();
		return rootNode;
	}
	
	/*
	 * 以下为上面一些递归方法的迭代实现
	 * 1，寻找前驱的迭代实现
	 */
}
