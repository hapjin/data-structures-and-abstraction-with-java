package list;

public class SequenceTest {
	public static void main(String[] args) {
		SequenceStack<String> ss = new SequenceStack<String>();
		System.out.println("size " + ss.length());
		ss.push("a");
		ss.push("b");
		System.out.println(ss);
		System.out.println(ss.peek());
	}
}