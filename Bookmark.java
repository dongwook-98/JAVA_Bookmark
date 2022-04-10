import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Bookmark {
	String name;
	String addedTime;
	String url;
	String groupName;
	String memo;
	Scanner inputScanner;
	
	Bookmark(String name, String url, String groupName, String memo){
		this.name = name;
		this.url = url;
		this.groupName = groupName;
		this.memo = memo;
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
		addedTime = now.format(newPattern);
	}
	
	Bookmark(String name, String addedTime, String url, String groupName, String memo){ // file에서 읽어 생성 시 사용
		this.name = name;
		this.addedTime = addedTime;
		this.url = url;
		this.groupName = groupName;
		this.memo = memo;
	}
	
	public void print() {
		System.out.print(name+", "+addedTime+", "+url+", "+groupName+", "+memo+"\n");
	};	
	
}