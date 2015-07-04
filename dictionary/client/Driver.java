package dictionary.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import dictionary.common.Name;
import dictionary.common.TelephoneDirectory;

public class Driver {
	private static final Name INPUT_ERROR = new Name("error", "error");
	private static final Name QUIT = new Name("quit", "quit");

	public static void main(String[] args) {
		TelephoneDirectory directory = new TelephoneDirectory();
		String fileName = "data.txt";//file name can be read data into telephone book 
		Scanner data = null;
		try{
			data = new Scanner(new File(fileName));
		}catch(FileNotFoundException e){
			System.out.println("File not found: " + e.getMessage());
		}catch(IOException e){
			System.out.println("I/O exception: " + e.getMessage());
		}
		directory.readFile(data);
		
		Name nextName = getName();//get name for search from user
		while(! nextName.equals(QUIT)){
			if(nextName.equals(INPUT_ERROR)){
				System.out.println("Error in entering name.Try again. ");
				break;
			}
			else{
				String phoneNumber = directory.getPhoneNumber(nextName);
				if(phoneNumber == null)
					System.out.println(nextName + " is not in the directory");
				else
					System.out.println("The phone number for " + nextName + " is " + phoneNumber);
				nextName = getName();
			}
		}//end while
		System.out.println("Bye!");
	}//end main

	// return either the name read from user, INPUT_ERROR ,or QUIT
	private static Name getName() {
		Name result = null;
		Scanner keyboard = new Scanner(System.in);// 扫描从键盘的输入
		System.out.println("Enter first name and last name,or quit to end");
		String line = keyboard.nextLine();// 读取一行输入
		if (line.trim().toLowerCase().equals("quit"))
			result = QUIT;// 用户结束查询
		else {// 构造 查询的 key对象-Name对象
			String firstName = null;
			String lastName = null;
			Scanner scan = new Scanner(line);// 扫描输入的一行
			
			if (scan.hasNext()) {
				firstName = scan.next();// 提取出firstname
//				System.out.println("keyinput--firstName:" + firstName);
				if (scan.hasNext())
					lastName = scan.next();// 提取出lastname
				else
					result = INPUT_ERROR;// 用户没有输入lastname
			} else
				result = INPUT_ERROR;
			if (result == null)
				result = new Name(firstName, lastName);
		}
		return result;
	}
}