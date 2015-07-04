package list;

import java.util.Iterator;

public class LinkListWithIteratorTest {
	public static void main(String[] args) {
		LinkListWithIterator<String> lli = new LinkListWithIterator<String>();
		lli.add("a");
		lli.add("b");
		lli.add("c");
		lli.add("d");
		Iterator<String> it = lli.getIterator();
		while(it.hasNext())
			System.out.println(it.next());
	}
}
