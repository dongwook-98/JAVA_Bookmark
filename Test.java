import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		BookmarkList newBookmarkList;
		newBookmarkList = new BookmarkList("bookmark-normal.txt");
		
		System.out.println("\n\n\n");
		newBookmarkList.mergeByGroup();
	}
}

