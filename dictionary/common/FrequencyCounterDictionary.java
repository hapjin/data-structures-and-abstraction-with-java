package dictionary.common;

import java.util.Iterator;
import java.util.Scanner;

import dictionary.ArrayListDictionary;
import dictionary.DictionaryInterface;

public class FrequencyCounterDictionary {
	private DictionaryInterface<String, Integer> wordTable;
	
	public FrequencyCounterDictionary(){
		wordTable = new ArrayListDictionary<String, Integer>();
	}
	
	public void readFile(Scanner data){
		data.useDelimiter("\\W+");
		while(data.hasNext()){
			String nextWord = data.next();//从Scanner打开的输入流中读取单词
			nextWord.toLowerCase();
			Integer frequency = wordTable.getValue(nextWord);//判断该单词在词典中已出现的次数
			
			if(frequency == null){//单词从未出现过
				wordTable.add(nextWord, new Integer(1));//将其出现次数置 1
			}
			else//单词已经在词典中
			{
				frequency++;//出现次数增 1
				wordTable.add(nextWord, frequency);
			}
		}//end while
		data.close();
	}
	
	public void display(){
		Iterator<String> keyIterator = wordTable.getKeyIterator();
		Iterator<Integer> valueIterator = wordTable.getValueIterator();
		while(keyIterator.hasNext()){
			System.out.println(keyIterator.next() + " : " + valueIterator.next());
	}
	}
}
