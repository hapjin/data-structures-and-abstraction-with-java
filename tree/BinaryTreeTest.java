package tree;

import java.util.Iterator;

public class BinaryTreeTest {
	public static void main(String[] args) {
		BinaryTree<String> bitree1 = new BinaryTree<String>("a");
		BinaryTree<String> bitree2 = new BinaryTree<String>("b");
		BinaryTree<String> bitree3 = new BinaryTree<String>("c");
		bitree1.setTree("d", bitree2, bitree3);
		System.out.println("前序遍历：");
		bitree1.preorderTraverse();
		System.out.println("中序遍历");
		bitree1.inorderTraverse();
		System.out.println("后序遍历");
		bitree1.postorderTraverse();
		
		System.out.println("使用中序迭代器遍历：");
		Iterator<String> inorder_iterator = bitree1.getInorderIterator();
		while(inorder_iterator.hasNext()){
			String str;
			str = inorder_iterator.next();
			System.out.println(str);
		}
		System.out.println("树的高度为：" + bitree1.getHeight());
		System.out.println("树中节点的个数为: " + bitree1.getNumberOfNodes());
	}
}
