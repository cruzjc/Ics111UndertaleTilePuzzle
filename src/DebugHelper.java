/*
 * http://www.logicaltrinkets.com/wordpress/?p=153
 * http://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
 */


import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DebugHelper {
	//var declaration
	Scanner scanner=new Scanner(new InputStreamReader(System.in));
	File folder;
	File[] listOfFiles;
	
	DebugHelper(){
	}
	
	//outputs the value of the variable
	void o(){
		System.out.println("This statement is being executed");
	}
	void o(String s){
		System.out.println(s);
	}	
	void o(int i){
		System.out.println(i);
	}	
	void o(float f){
		System.out.println(f);
	}	
	void o(double d){
		System.out.println(d);
	}	
	void o(char c){
		System.out.println(c);
	}
	void o(boolean bool){
		System.out.println(bool);
	}
	
	//waits until enter is pressed
	void w(){
		scanner=new Scanner(new InputStreamReader(System.in));
		System.out.println("Press enter to continue");
		scanner.nextLine();
	}
	
	//wait for the specified amount of time
	void ts(long timeSeconds){
		try {
			Thread.sleep(timeSeconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//exit
	void q(){
		System.exit(0);
	}
	
	//current time in milliseconds
	void t(){
		System.out.println(System.currentTimeMillis());
	}
	
	//directory list
	void dl(){
		folder=new File("./");
		listOfFiles=folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println(i+": File "+listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println(i+": Directory " + listOfFiles[i].getName());
			}
		}
	}
	
	//use "./" to specify folders, example: dl("./bin");
	void dl(String s){
		folder=new File(s);
		listOfFiles=folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println(i+": File "+listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println(i+": Directory " + listOfFiles[i].getName());
			}
		}
	}
}
