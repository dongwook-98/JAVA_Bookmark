package BookmarkProgram;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.Book;

import Bookmark.*;
class MyFrame extends JFrame{
    public MyFrame(){
        super("Bookmark Manager");
        setLayout(new BorderLayout());

        MyButtonPanel ButtonPanel = new MyButtonPanel();
        add(ButtonPanel, BorderLayout.EAST);

        MyBookmarkTable bookmarkTable = new MyBookmarkTable();
        add(bookmarkTable.scrollPane, BorderLayout.WEST);
        setVisible(true);
        setSize(400, 300);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class MyButtonPanel extends JPanel{
    public MyButtonPanel(){
        setLayout(new GridLayout(5,1));
        add(new JButton("ADD"));
        add(new JButton("DELETE"));
        add(new JButton("UP"));
        add(new JButton("DOWN"));
        add(new JButton("SAVE"));
    }
}

class MyBookmarkTablePanel extends JPanel{
    public MyBookmarkTablePanel(){

    }
}

class MyBookmarkTable extends JTable{
    DefaultTableModel model;
    JTable table;
    JScrollPane scrollPane;
    public MyBookmarkTable(){
        BookmarkList tableBookmark;
        tableBookmark = new BookmarkList("src/bookmark.txt");
        String header[] = {" ", "Group", "Name", "URL", "Created Time", "Memo"};
        String contents[][] = new String[100][6];
        for (int i = 0; i < tableBookmark.numBookmarks(); ++i){
            Bookmark temp = tableBookmark.getBookmark(i);
            contents[i][1] = temp.getGroupName();
            contents[i][2] = temp.getName();
            contents[i][3] = temp.getUrl();
            contents[i][4] = temp.getAddedTime();
            contents[i][5] = temp.getMemo();
        }

        model = new DefaultTableModel(contents, header);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(650, 300));
    }
}

class TestMyFrame {
    public static void main(String[] args){
        new MyFrame();
    }
}
