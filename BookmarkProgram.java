/*
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class BookmarkProgram {
	BookmarkList bookmarkList; // Bookmark[], bookmarkcounter
	Scanner inputScanner;
	
	BookmarkProgram() throws IOException{
		inputScanner = new Scanner(System.in);
		String bookmarkName = inputScanner.next();
		bookmarkList = new BookmarkList(bookmarkName);
		System.out.println("BookmarkProgram started!");
		
		if (bookmarkList.bookmarkDB.createNewFile()) {
		System.out.println("New file created");
		}
		
		bookmarkList.fileToBookmarkList();
		
		mainProgram();
	}
	
	public void mainProgram() throws IOException {
		System.out.println("1. Input new bookmark");
		System.out.println("2. Load bookmark from bookmark DB (overwrite)"); // check ÇÊ¿ä
		System.out.println("3. Retrieve certain Bookmark");
		System.out.println("4. Retrieve bookmark List");
		System.out.println("5. Merge by group");
		System.out.println("6. Export to bookmark DB");
		System.out.println("7. Exit");
		
		System.out.print("Go To : ");
		String select = inputScanner.next();
		System.out.println("-------------------");
		
		switch(select){
			case "1":
				//Runtime.getRuntime().exec("cls");
				System.out.println("Input new bookmark\n");
				bookmarkList.inputNewBookmark();
				System.out.println("-------------------");
				mainProgram();
				break;
			case "2":
				//Runtime.getRuntime().exec("cls");
				System.out.println("Load bookmark from bookmark DB (overwrite)\n");
				bookmarkList.fileToBookmarkList();
				System.out.println("-------------------");
				mainProgram();
				break;
			case "3":
				System.out.println("Retrieve certain Bookmark");
				System.out.println("Current number of bookmark: "+bookmarkList.bookmarkCounter);
				System.out.print("Input index: ");
				String strIndex = inputScanner.next();
				int index = Integer.parseInt(strIndex) - 1;
				bookmarkList.getBookmark(index).print();
				System.out.println("-------------------");
				mainProgram();
			case "4":
				System.out.println("Retrieve bookmark List");
				System.out.println("Current number of bookmark: "+bookmarkList.bookmarkCounter);
				for(int i = 0; i < bookmarkList.bookmarkCounter; ++i) {
					bookmarkList.getBookmark(i).print();
				}
				System.out.println("-------------------");
				mainProgram();
				break;
			case "5":
				System.out.println("Merge by group");
				bookmarkList.mergeByGroup();
				System.out.println("-------------------");
				mainProgram();
				break;
			case "6":
				System.out.println("Export to bookmark DB");
				bookmarkList.bookmarkListToFile();
				System.out.println("-------------------");
				mainProgram();
				break;
			case "7":
				System.out.println("Exit\n");
				System.exit(0);
				break;
			default:
				System.out.println("Wrong number");
				mainProgram();
		}
	}
}
*/
