package dictionary.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import dictionary.common.FrequencyCounterDictionary;

public class PrintWordsFrequency {
	public static void main(String[] args) {
		FrequencyCounterDictionary wordCounter = new FrequencyCounterDictionary();
		String fileName = "word.txt";//存放单词的文本文件，统计其中的单词出现次数
		try{
			Scanner data = new Scanner(new File(fileName));
			wordCounter.readFile(data);
		}catch(FileNotFoundException e){
			System.out.println("File not found: " + e.getMessage());
		}catch(IOException e){
			System.out.println("I/O error" + e.getMessage());
		}
		
		wordCounter.display();
		System.out.println("Bye");
	}
}
