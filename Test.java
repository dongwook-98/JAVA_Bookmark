import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		BookmarkList newBookmarkList;
		newBookmarkList = new BookmarkList("bookmark-normal.txt");
		//for (int i = 0; i < newBookmarkList.bookmarkCounter; ++i) newBookmarkList.bookmarkList[i].print();
		
		System.out.println("\n\n\n");
		newBookmarkList.mergeByGroup();
	}
}

