import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.net.MalformedURLException;
import java.net.URL;

public class BookmarkList {
	private Bookmark[] bookmarkList;
	// Bookmark array형 변수 bookmarkList
	private int bookmarkCounter; 
	// 현재 입력된 bookmark의 수를 저장하는 변수
	private File bookmarkDB; 
	// bookmark가 저장된 파일을 불러올 File 객체 
	private Scanner bookmarkDBScanner; 
	// bookmarkDB를 스캔할 Scanner 객체
	
	BookmarkList(String bookmarkFileName){
		bookmarkList = new Bookmark[100];
		// 최대 개수인 100개 만큼 할당
		bookmarkCounter = 0;
		bookmarkDB = new File(bookmarkFileName);
		
		try {
			bookmarkDBScanner = new Scanner(bookmarkDB);
			// bookmark가 기록되어 있는 파일을 읽는 Scanner
		} catch (FileNotFoundException e) {
			// 파일을 찾을 수 없을 때 발생시킴
			System.out.println("Unknwon BookmarkList data File ");
			return;
		}
		
		while (bookmarkDBScanner.hasNextLine()) {
			String data = bookmarkDBScanner.nextLine(); 
			// 한 줄을 읽어 data에 저장
			if (data.startsWith("//") || data.isEmpty()) {
				// //로 시작하거나 공백이면 주석으로 처리
				continue;
			}
			
			Bookmark tempBookmark = this.splitLine(data);
			// tempBookmark에 data를 split한 결과를 저장
			if (this.checkRule(tempBookmark) == true) {
				// tempBookmark가 규칙을 통과했다면
				bookmarkList[bookmarkCounter] = tempBookmark;
				// bookmarkList에 추가해준다.
				bookmarkCounter++;
			}
			else { // 규칙 통과하지 못했다면 프로그램 종
				System.exit(0);
			}
		}
	}
	
	private Bookmark splitLine(String line) {
		String[] bookmarkData = line.split(",|;", -1);
		// 매개변수로 받은 line을 ,나;로 split된 값을 bookmarkData에 저장한다.
		// split에 -1 옵션을 넣어줘서 값이 없는 칸도 null로 저장해준다.
		for (int i = 0; i < bookmarkData.length; ++i) {
			bookmarkData[i] = bookmarkData[i].trim(); // 공백 제거
		}
		
		Bookmark tempBookmark; // 공백이 제거된 data를 저장할 임시 북마크 객체
		tempBookmark = new Bookmark(bookmarkData[0], bookmarkData[1], 
				bookmarkData[2], bookmarkData[3], bookmarkData[4]);
		
		return tempBookmark;
	}
	
	private Boolean checkRule(Bookmark addedBookmark) {
		// 규칙을 통과하면 true 반환, 규칙을 통과하지 못하면 false 반환
		final String yearPattern = "20[0-9]{2}";
		final String monthPattern = "(0[0-9]|1[0-2]|[0-9])";
		final String dayPattern = "([0-2][0-9]|3[01]|[0-9])";
		final String hourPattern = "([01][0-9]|2[0-4]|[0-9])";
		final String minutePattern = "([0-5][0-9]|[0-9])";
		final String pattern = yearPattern+"-"+monthPattern+"-"+dayPattern+"_"+hourPattern+":"+minutePattern;
		// addedTime 패턴을 검사해줄 pattern
		
		try {
			if (!addedBookmark.getAddedTime().matches(pattern)) {
				// pattern과 bookmark의 addedTime이 일치하지 않으면
				Exception datePatternErr = new Exception();
				// 위의 경우에 발생시킬 exception 객체 생성
				throw datePatternErr;
			}
		} catch (Exception datePatternErr) {
			System.out.print("Date Format Error -> No Created Time invalid Bookmark info line: ");
			addedBookmark.print();	
		}
		
		try {
			new URL(addedBookmark.getUrl());
			// 객체 생성을 해보아 url 형식 검사 
		} catch (MalformedURLException e) {
			// 잘못된 url로 URL 객체를 생성하려 했을 때 발생하는 Exception
			System.out.print("MalformedURLException: wrong URL - No URL ; invalid Bookmark info line: ");
			addedBookmark.print();
		}
		
		return true; // 예외처리 구문을 모두 통과하면 true를 반환
	}
	
	public int numBookmarks() { // 리스트에 있는 전체 북마크 숫자를 알려줌
		return bookmarkCounter;
	}
	
	public Bookmark getBookmark(int i) { // 리스트의 i번째 북마크 정보를 알려줌
		return bookmarkList[i];
	}
	
	public void mergeByGroup() {
		Bookmark[] tempBookmarkList = new Bookmark[100];
		int indexCounter = 0;
		// 새롭게 생성될 객체의 index를 count 해주는 변수
		Boolean[] flagArray;
		// group별로 정렬 시에 BookmarkList의 Bookmark들이 tempBookmarkList로 옮겨졌는지를 기록하는 Boolean array형 변수
		// true -> 아직 옮겨지지 않음, false -> 이미 옮겨짐
		
		flagArray = new Boolean[bookmarkCounter];
		Arrays.fill(flagArray, true); // true로 flagArray를 채워 놓음 
		
		for(int i = 0; i < bookmarkCounter; ++i) {
			if (flagArray[i] == true && !bookmarkList[i].getGroupName().isEmpty()) {
				// bookmarkList의 i번째 bookmark가 아직 옮겨지지 않았고, group이 공백이 아니라면 
				tempBookmarkList[indexCounter++] = bookmarkList[i];
				// tempBookmarkList로 옮김
				flagArray[i] = false; 
				// 옮겨졌으므로 flagArray[i]는 false
				for(int j = i + 1; j < bookmarkCounter; ++j) {
					// i 이후의 bookmark들 중 i번째 bookmark와 group이 같은 bookmark search
					if(bookmarkList[i].getGroupName().equals(bookmarkList[j].getGroupName())) {
						// group이 같은 경우
						tempBookmarkList[indexCounter++] = bookmarkList[j];
						flagArray[j] = false;
					}
				}
			}
			else if (bookmarkList[i].getGroupName().isEmpty()){
				// group이 공백인 북마크는 따로 묶어주지 않고 임시 객체에 순서대로 옮겨줌
				tempBookmarkList[indexCounter++] = bookmarkList[i];
				flagArray[i] = false;
			}
			else continue;
			// flagArray[i]가 false인 경우는 건너뜀
		}
		bookmarkList = tempBookmarkList;
		// bookmarkList에 반영
	}
}