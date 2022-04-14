import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bookmark {
	private String name; // �ϸ�ũ �̸�
	private String addedTime; // �ϸ�ũ�� �߰��� �ð�
	private String url; // �ϸ�ũ url
	private String groupName; // �ϸ�ũ �׷��
	private String memo; // �ϸ�ũ �޸�
	
	Bookmark(String name, String url, String groupName, String memo){
		this.name = name;
		this.url = url;
		this.groupName = groupName;
		this.memo = memo;
		LocalDateTime now = LocalDateTime.now();
		// ���� �ð��� LocalDateTime�� ��ü now�� ����
		DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
		// now�� ������ ������ DateTimeFormatter�� ��ü newPattern�� ����
		addedTime = now.format(newPattern);
		// now�� newPattern���� addedTime�� ����
	}
	
	Bookmark(String name, String addedTime, String url, String groupName, String memo){ 
		// file���� ���� �о ��ü�� ���� �� ���
		this.name = name;
		this.addedTime = addedTime;
		this.url = url;
		this.groupName = groupName;
		this.memo = memo;
	}
	
	public void print() { // bookmark ��� �޼���
		System.out.print(name+","+addedTime+","+url+","+groupName+","+memo+"\n");
	};	
	
	public String getName() {return name;}
	public String getAddedTime() {return addedTime;}
	public String getUrl() {return url;}
	public String getGroupName() {return groupName;}
	public String getMemo() {return memo;}
	// ��������� getter �Լ�
	
	public void setName(String newName) {name = newName;}
	public void setAddedTime(String newAddedTime) {addedTime = newAddedTime;}
	public void setUrl(String newUrl) {addedTime = newUrl;}
	public void setGroupName(String newGroupName) {addedTime = newGroupName;}
	public void setMemo(String newMemo) {addedTime = newMemo;}
	// ��������� setter �Լ�
}

