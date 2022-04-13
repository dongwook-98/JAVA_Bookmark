import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.net.MalformedURLException;
import java.net.URL;

public class BookmarkList {
	private Bookmark[] bookmarkList;
	// Bookmark array�� ���� bookmarkList
	private int bookmarkCounter; 
	// ���� �Էµ� bookmark�� ���� �����ϴ� ����
	private File bookmarkDB; 
	// bookmark�� ����� ������ �ҷ��� File ��ü 
	private Scanner bookmarkDBScanner; 
	// bookmarkDB�� ��ĵ�� Scanner ��ü
	
	BookmarkList(String bookmarkFileName){
		bookmarkList = new Bookmark[100];
		// �ִ� ������ 100�� ��ŭ �Ҵ�
		bookmarkCounter = 0;
		bookmarkDB = new File(bookmarkFileName);
		
		try {
			bookmarkDBScanner = new Scanner(bookmarkDB);
			// bookmark�� ��ϵǾ� �ִ� ������ �д� Scanner
		} catch (FileNotFoundException e) {
			// ������ ã�� �� ���� �� �߻���Ŵ
			System.out.println("Unknwon BookmarkList data File ");
			return;
		}
		
		while (bookmarkDBScanner.hasNextLine()) {
			String data = bookmarkDBScanner.nextLine(); 
			// �� ���� �о� data�� ����
			if (data.startsWith("//") || data.isEmpty()) {
				// //�� �����ϰų� �����̸� �ּ����� ó��
				continue;
			}
			
			Bookmark tempBookmark = this.splitLine(data);
			// tempBookmark�� data�� split�� ����� ����
			if (this.checkRule(tempBookmark) == true) {
				// tempBookmark�� ��Ģ�� ����ߴٸ�
				bookmarkList[bookmarkCounter] = tempBookmark;
				// bookmarkList�� �߰����ش�.
				bookmarkCounter++;
			}
			else { // ��Ģ ������� ���ߴٸ� ���α׷� ��
				System.exit(0);
			}
		}
	}
	
	private Bookmark splitLine(String line) {
		String[] bookmarkData = line.split(",|;", -1);
		// �Ű������� ���� line�� ,��;�� split�� ���� bookmarkData�� �����Ѵ�.
		// split�� -1 �ɼ��� �־��༭ ���� ���� ĭ�� null�� �������ش�.
		for (int i = 0; i < bookmarkData.length; ++i) {
			bookmarkData[i] = bookmarkData[i].trim(); // ���� ����
		}
		
		Bookmark tempBookmark; // ������ ���ŵ� data�� ������ �ӽ� �ϸ�ũ ��ü
		tempBookmark = new Bookmark(bookmarkData[0], bookmarkData[1], 
				bookmarkData[2], bookmarkData[3], bookmarkData[4]);
		
		return tempBookmark;
	}
	
	private Boolean checkRule(Bookmark addedBookmark) {
		// ��Ģ�� ����ϸ� true ��ȯ, ��Ģ�� ������� ���ϸ� false ��ȯ
		final String yearPattern = "20[0-9]{2}";
		final String monthPattern = "(0[0-9]|1[0-2]|[0-9])";
		final String dayPattern = "([0-2][0-9]|3[01]|[0-9])";
		final String hourPattern = "([01][0-9]|2[0-4]|[0-9])";
		final String minutePattern = "([0-5][0-9]|[0-9])";
		final String pattern = yearPattern+"-"+monthPattern+"-"+dayPattern+"_"+hourPattern+":"+minutePattern;
		// addedTime ������ �˻����� pattern
		
		try {
			if (!addedBookmark.getAddedTime().matches(pattern)) {
				// pattern�� bookmark�� addedTime�� ��ġ���� ������
				Exception datePatternErr = new Exception();
				// ���� ��쿡 �߻���ų exception ��ü ����
				throw datePatternErr;
			}
		} catch (Exception datePatternErr) {
			System.out.print("Date Format Error -> No Created Time invalid Bookmark info line: ");
			addedBookmark.print();	
		}
		
		try {
			new URL(addedBookmark.getUrl());
			// ��ü ������ �غ��� url ���� �˻� 
		} catch (MalformedURLException e) {
			// �߸��� url�� URL ��ü�� �����Ϸ� ���� �� �߻��ϴ� Exception
			System.out.print("MalformedURLException: wrong URL - No URL ; invalid Bookmark info line: ");
			addedBookmark.print();
		}
		
		return true; // ����ó�� ������ ��� ����ϸ� true�� ��ȯ
	}
	
	public int numBookmarks() { // ����Ʈ�� �ִ� ��ü �ϸ�ũ ���ڸ� �˷���
		return bookmarkCounter;
	}
	
	public Bookmark getBookmark(int i) { // ����Ʈ�� i��° �ϸ�ũ ������ �˷���
		return bookmarkList[i];
	}
	
	public void mergeByGroup() {
		Bookmark[] tempBookmarkList = new Bookmark[100];
		int indexCounter = 0;
		// ���Ӱ� ������ ��ü�� index�� count ���ִ� ����
		Boolean[] flagArray;
		// group���� ���� �ÿ� BookmarkList�� Bookmark���� tempBookmarkList�� �Ű��������� ����ϴ� Boolean array�� ����
		// true -> ���� �Ű����� ����, false -> �̹� �Ű���
		
		flagArray = new Boolean[bookmarkCounter];
		Arrays.fill(flagArray, true); // true�� flagArray�� ä�� ���� 
		
		for(int i = 0; i < bookmarkCounter; ++i) {
			if (flagArray[i] == true && !bookmarkList[i].getGroupName().isEmpty()) {
				// bookmarkList�� i��° bookmark�� ���� �Ű����� �ʾҰ�, group�� ������ �ƴ϶�� 
				tempBookmarkList[indexCounter++] = bookmarkList[i];
				// tempBookmarkList�� �ű�
				flagArray[i] = false; 
				// �Ű������Ƿ� flagArray[i]�� false
				for(int j = i + 1; j < bookmarkCounter; ++j) {
					// i ������ bookmark�� �� i��° bookmark�� group�� ���� bookmark search
					if(bookmarkList[i].getGroupName().equals(bookmarkList[j].getGroupName())) {
						// group�� ���� ���
						tempBookmarkList[indexCounter++] = bookmarkList[j];
						flagArray[j] = false;
					}
				}
			}
			else if (bookmarkList[i].getGroupName().isEmpty()){
				// group�� ������ �ϸ�ũ�� ���� �������� �ʰ� �ӽ� ��ü�� ������� �Ű���
				tempBookmarkList[indexCounter++] = bookmarkList[i];
				flagArray[i] = false;
			}
			else continue;
			// flagArray[i]�� false�� ���� �ǳʶ�
		}
		bookmarkList = tempBookmarkList;
		// bookmarkList�� �ݿ�
	}
}