import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bookmark {
	private String name; // 북마크 이름
	private String addedTime; // 북마크가 추가된 시간
	private String url; // 북마크 url
	private String groupName; // 북마크 그룹명
	private String memo; // 북마크 메모
	
	Bookmark(String name, String url, String groupName, String memo){
		this.name = name;
		this.url = url;
		this.groupName = groupName;
		this.memo = memo;
		LocalDateTime now = LocalDateTime.now();
		// 현재 시간을 LocalDateTime형 객체 now에 저장
		DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
		// now를 저장할 패턴을 DateTimeFormatter형 객체 newPattern에 저장
		addedTime = now.format(newPattern);
		// now를 newPattern으로 addedTime에 저장
	}
	
	Bookmark(String name, String addedTime, String url, String groupName, String memo){ 
		// file에서 값을 읽어서 객체를 만들 때 사용
		this.name = name;
		this.addedTime = addedTime;
		this.url = url;
		this.groupName = groupName;
		this.memo = memo;
	}
	
	public void print() { // bookmark 출력 메서드
		System.out.print(name+","+addedTime+","+url+","+groupName+","+memo+"\n");
	};	
	
	public String getName() {return name;}
	public String getAddedTime() {return addedTime;}
	public String getUrl() {return url;}
	public String getGroupName() {return groupName;}
	public String getMemo() {return memo;}
	// 멤버변수의 getter 함수
	
	public void setName(String newName) {name = newName;}
	public void setAddedTime(String newAddedTime) {addedTime = newAddedTime;}
	public void setUrl(String newUrl) {addedTime = newUrl;}
	public void setGroupName(String newGroupName) {addedTime = newGroupName;}
	public void setMemo(String newMemo) {addedTime = newMemo;}
	// 멤버변수의 setter 함수
}

