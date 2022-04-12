import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.net.MalformedURLException;
import java.net.URL;

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
			System.out.println("Unknwon BookmarkList data File ");
			return;
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
				System.exit(0);
			}
		}
	}
	
	public Bookmark splitLine(String line) {
		String[] bookmarkData = line.split(",|;", -1);
		
		if (bookmarkData.length == 4) { // input �ϴ� ��� (addedTime�� X)
			for (int i = 0; i < 4; ++i) {
				bookmarkData[i] = bookmarkData[i].trim(); // ���� ����
			}
			
			Bookmark tempBookmark;
			tempBookmark = new Bookmark(bookmarkData[0], bookmarkData[1], 
					bookmarkData[2], bookmarkData[3]);
			return tempBookmark;
		}
		
		else { // bookmarkData.length == 5 // file���� �д� ���
			for (int i = 0; i < 5; ++i) {
				bookmarkData[i] = bookmarkData[i].trim(); // ���� ����
			}
			
			Bookmark tempBookmark;
			tempBookmark = new Bookmark(bookmarkData[0], bookmarkData[1], 
					bookmarkData[2], bookmarkData[3], bookmarkData[4]);
			return tempBookmark;
		}
	}
	
	public Boolean checkRule(Bookmark addedBookmark) { // ��Ģ ��� ���ϸ� ���α׷� ����

		final String yearPattern = "[0-9]{4}";
		final String monthPattern = "(0[0-9]|1[0-2]|[0-9])";
		final String dayPattern = "([0-2][0-9]|3[01]|[0-9])";
		final String hourPattern = "([01][0-9]|2[0-4]|[0-9])";
		final String minutePattern = "([0-5][0-9]|[0-9])";
		final String pattern = yearPattern+"-"+monthPattern+"-"+dayPattern+"_"+hourPattern+":"+minutePattern;
		
		try {
			if (!addedBookmark.addedTime.matches(pattern)) {
				Exception e = new Exception();
				throw e;
			}
		} catch (Exception e) {
			System.out.print("Date Format Error -> No Created Time invalid Bookmark info line: ");
			addedBookmark.print();	
		}
		try {
			new URL(addedBookmark.url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.print("MalformedURLException: wrong URL - No URL ; invalid Bookmark info line: ");
			addedBookmark.print();
			
		}
		return true;
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