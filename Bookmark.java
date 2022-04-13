
public class Bookmark {
	private String name; // �ϸ�ũ �̸�
	private String addedTime; // �ϸ�ũ�� �߰��� �ð�
	private String url; // �ϸ�ũ url
	private String groupName; // �ϸ�ũ �׷��
	private String memo; // �ϸ�ũ �޸�
	
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
}