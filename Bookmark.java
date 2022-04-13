
public class Bookmark {
	private String name; // 북마크 이름
	private String addedTime; // 북마크가 추가된 시간
	private String url; // 북마크 url
	private String groupName; // 북마크 그룹명
	private String memo; // 북마크 메모
	
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
}