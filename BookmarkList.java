import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class BookmarkList {
	Bookmark[] bookmarkList;
	int bookmarkCounter;
	File bookmarkDB;
	Scanner inputScanner;
	Scanner bookmarkDBScanner;
	
	BookmarkList(String bookmarkFileName){
		bookmarkList = new Bookmark[100];
		bookmarkCounter = 0;
		bookmarkDB = new File(bookmarkFileName);
		
		try {
			bookmarkDBScanner = new Scanner(bookmarkDB);
		} catch (Exception e) {
			System.out.println("File not found!");
		}
		
		while(bookmarkDBScanner.hasNextLine()) {
			String data = bookmarkDBScanner.nextLine(); 
			if (data.startsWith("//") || data.isEmpty()) {
				continue;
			}
			
			Bookmark tempBookmark = this.splitLine(data);
			if(this.checkRule(tempBookmark) == true) {
				bookmarkList[bookmarkCounter] = this.splitLine(data);
				bookmarkCounter++;
			}
			else {
				// System.out.println("Rule wrong");
				System.exit(0);
			}
		}
		// System.out.println("Bookmark synchronization completed!");
		// System.out.println("Current number of bookmark: "+(bookmarkCounter));
	}
	
	public Bookmark splitLine(String line) {
		String[] bookmarkData = line.split(",|;", -1);
		/*
		if (bookmarkData.length == 4) { // input �ϴ� ��� (addedTime�� X)
			for (int i = 0; i < 4; ++i) {
				bookmarkData[i] = bookmarkData[i].trim(); // ���� ����
			}
			
			Bookmark tempBookmark;
			tempBookmark = new Bookmark(bookmarkData[0], bookmarkData[1], 
					bookmarkData[2], bookmarkData[3]);
			return tempBookmark;
		}
		*/
		//else { // bookmarkData.length == 5 // file���� �д� ���
			for (int i = 0; i < 5; ++i) {
				bookmarkData[i] = bookmarkData[i].trim(); // ���� ����
			}
			
			Bookmark tempBookmark;
			tempBookmark = new Bookmark(bookmarkData[0], bookmarkData[1], 
					bookmarkData[2], bookmarkData[3], bookmarkData[4]);
			return tempBookmark;
		//}
	}
	
	public Boolean checkRule(Bookmark addedBookmark) { // true -> ��Ģ ���, false -> ��Ģ Ż��

		final String yearPattern = "[0-9]{4}";
		final String monthPattern = "(0[0-9]|1[0-2]|[0-9])";
		final String dayPattern = "([0-2][0-9]|3[01]|[0-9])";
		final String hourPattern = "([01][0-9]|2[0-4]|[0-9])";
		final String minutePattern = "([0-5][0-9]|[0-9])";
		final String pattern = yearPattern+"-"+monthPattern+"-"+dayPattern+"_"+hourPattern+":"+minutePattern;
		
		if (addedBookmark.addedTime.isEmpty()) {
			System.out.println("addedTime is missing");
			return false;
		}
		else {
			if (!addedBookmark.addedTime.matches(pattern)) {
				System.out.println("addedTime is wrong");
				return false;
			}
		}
		
		if (addedBookmark.url.isEmpty()) { // url
			System.out.println("URL is missing");
			
			return false;
		}		
		
		return true;
	}
	
	public void inputNewBookmark() { // tempBookmark ��ü ����
		System.out.println("If you want to omit the field, just press enter");
		inputScanner = new Scanner(System.in);

		String inputValue;
		System.out.println("[name], url, [groupName], [memo] ([ ] can be skipped)");
		
		inputValue = inputScanner.nextLine();
		Bookmark tempBookmark = this.splitLine(inputValue);
		
		if(this.checkRule(tempBookmark) == true) {
			if(bookmarkCounter < 100) {
				bookmarkList[bookmarkCounter] = this.splitLine(inputValue);
				System.out.println("New bookmark added. "
						+ "Current number of bookmark: "+(++bookmarkCounter));
			}
			else {
				System.out.println("max bookmark added");
				return;
			}
		}
		else {
			System.out.println("Rule wrong");
			System.exit(0);
		}
	}	
	
	public int numBookmarks() { // ����Ʈ�� �ִ� ��ü �ϸ�ũ ���ڸ� �˷���
		return bookmarkCounter;
	}
	
	public Bookmark getBookmark(int i) { // ����Ʈ�� i��° �ϸ�ũ ������ �˷���
		return bookmarkList[i];
	}
	
	public void bookmarkListToFile() throws IOException { // BookmarkList��ü�� ���Ͽ� ����
		FileWriter fw = new FileWriter(bookmarkDB, false);
		
		for (int i = 0; i < bookmarkCounter; ++i) {
			String data = bookmarkList[i].name
					+", "+bookmarkList[i].addedTime
					+", "+bookmarkList[i].url
					+", "+bookmarkList[i].groupName
					+", "+bookmarkList[i].memo+"\n";
			fw.write(data);
		}
		fw.close();
	}
	
	/*
	public void mergeByGroup() {
		Boolean[] flagArray;
		flagArray = new Boolean[bookmarkCounter];
		
		Arrays.fill(flagArray, true); // true -> ���� ��� X, false -> �̹� ��� O 
		
		for(int i = 0; i < bookmarkCounter; ++i) {
			if (flagArray[i] == true) {
				bookmarkList[i].print();
				for(int j = i + 1; j < bookmarkCounter; ++j) {
					if(bookmarkList[i].groupName.equals(bookmarkList[j].groupName)) {
						flagArray[j] = false;
						bookmarkList[j].print();
					}
				}
			}
		}
	}
	*/
	
	public void mergeByGroup() {
		Bookmark[] tempBookmarkList = new Bookmark[100];
		int indexCounter = 0;
		Boolean[] flagArray;
		
		flagArray = new Boolean[bookmarkCounter];
		
		Arrays.fill(flagArray, true); // true -> ���� ��� X, false -> �̹� ��� O 
		
		for(int i = 0; i < bookmarkCounter; ++i) {
			if (flagArray[i] == true) {
				tempBookmarkList[indexCounter++] = bookmarkList[i];
				for(int j = i + 1; j < bookmarkCounter; ++j) {
					if(bookmarkList[i].groupName.equals(bookmarkList[j].groupName)) {
						flagArray[j] = false;
						tempBookmarkList[indexCounter++] = bookmarkList[j];
					}
				}
			}
		}
		
		bookmarkList = tempBookmarkList;
	}
}