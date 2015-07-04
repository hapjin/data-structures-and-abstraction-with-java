package list;

import java.util.Iterator;

public interface ListWithIteratorInterface<T> extends ListInterface<T>{
	public Iterator<T> getIterator();
}
