package dictionary.common;

import java.util.Scanner;
import dictionary.ArrayDictionary;
import dictionary.DictionaryInterface;
import dictionary.common.Name;

public class TelephoneDirectory {
	private DictionaryInterface<Name,String> phoneBook;
	
	public TelephoneDirectory(){
		phoneBook = new ArrayDictionary<Name,String>();
	}
	
	/*
	 * @Task 以行为单位，依次读取整个文件
	 * @param data 读取文本文件中指定格式的数据：
	 * 每行含有三个字符串：名，姓，电话号码。它们用空格分开
	 */
	public void readFile(Scanner data){
		while(data.hasNext()){
			String firstName = data.next();
			String lastName = data.next();
			String phoneNumber = data.next();
			
			Name fullName = new Name(firstName, lastName);
			phoneBook.add(fullName, phoneNumber);
		}//end while
		data.close();
	}//end readFile()
	
	public String getPhoneNumber(Name personName){
		return phoneBook.getValue(personName);
	}
}