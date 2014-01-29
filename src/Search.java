import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Search extends JFrame implements ActionListener{
	
	JPanel changePanels;
	CardLayout cardLayout;
	String responsArray;
	DefaultListModel responsList;
	JList resultList = new JList();
	JScrollPane resultScrollPanel;
	
	JTable resultTable = new JTable();
	DefaultTableModel modelTable = new DefaultTableModel();

	
	public static void main(String[] args){
		Search frame = new Search("社員検索");
		frame.setVisible(true);
	}
	
	public Search(String title){
		setTitle(title);
		setBounds(100,100,600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final AccessDB access = new AccessDB();
		
		Container contentPanel = getContentPane();
		
		
		//Search panels
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null); // レイアウト手動
		
		JLabel searchTitle = new JLabel("社員検索");
		searchTitle.setFont(new Font("ＭＳ 明朝", Font.BOLD, 26));
		
		
		JLabel id = new JLabel("ID検索");
		JLabel name = new JLabel("社員名検索");
		JLabel dept = new JLabel("部署名検索");
		final JTextField idText = new JTextField();
		final JTextField nameText = new JTextField();
		final JTextField deptText = new JTextField();
		JButton idSearchBtn = new JButton("検索");
		JButton nameSearchBtn = new JButton("検索");
		JButton deptSearchBtn = new JButton("検索");
		JButton searchReturnBtn = new JButton("TOP");
		
		//layout null
		searchTitle.setBounds(250,5,150,25);
		id.setBounds(110,100 , 70, 40);
		name.setBounds(110, 180 , 70, 40);
		dept.setBounds(110, 260 , 70, 40);
		idText.setBounds(220, 100 , 160, 50);
		nameText.setBounds(220, 180 , 160, 50);
		deptText.setBounds(220, 260 , 160, 50);
		idSearchBtn.setBounds(400,100,60,50);
		nameSearchBtn.setBounds(400,180,60,50);
		deptSearchBtn.setBounds(400,260,60,50);
		searchReturnBtn.setBounds(280,410,60,40);
		
		searchPanel.add(searchTitle);
		searchPanel.add(id);
		searchPanel.add(idText);
		searchPanel.add(name);
		searchPanel.add(nameText);
		searchPanel.add(dept);
		searchPanel.add(deptText);
		searchPanel.add(idSearchBtn);
		searchPanel.add(nameSearchBtn);
		searchPanel.add(deptSearchBtn);
		searchPanel.add(searchReturnBtn);
		
		
		//ボタンの設定
		idSearchBtn.addActionListener(this);
	    idSearchBtn.setActionCommand("searchPanel");
	    nameSearchBtn.addActionListener(this);
	    nameSearchBtn.setActionCommand("searchPanel");
	    deptSearchBtn.addActionListener(this);
	    deptSearchBtn.setActionCommand("searchPanel");
	    
	    idSearchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
//            	resultList = access.idSelectDB(idText.getText().toUpperCase());
//            	responsList = access.nameSelectDB(idText.getText().toUpperCase());
            	responsList = access.idSelectDB(Integer.parseInt(idText.getText()));
            	resultList.setModel(responsList);
			}
	    });
	    
	    nameSearchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	responsList = access.nameSelectDB(nameText.getText().toUpperCase());
//            	resultList = access.nameSelectDB(nameText.getText().toUpperCase());
            	resultList.setModel(responsList);
            }
	    });
	    
	    deptSearchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
 //          	System.out.print(deptText.getText());        	
//            	access.deptSelectDB(deptText.getText().toUpperCase());
//            	resultList = access.deptSelectDB(deptText.getText().toUpperCase());
//            	responsList = access.deptSelectDB(deptText.getText());
            	modelTable = access.deptSelectDB2(deptText.getText());
            	resultTable.setModel(modelTable);
            	
//            	resultList.setModel(responsList);
            }
	    });
/*	  //topに戻る　未実装
	  		topBtn.addActionListener(new ActionListener() {
	              public void actionPerformed(ActionEvent e){
	              
	              }
	  		});
*/	    
		//resultPanels
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(null);
		resultScrollPanel = new JScrollPane(resultTable);
		
		JLabel setName = new JLabel();
		JButton excelOutBtn = new JButton("Excelで出力");
		JButton backBtn = new JButton("戻る");
		
//		resultList.setBounds(60,40,300,400);
		resultScrollPanel.setBounds(60,40,300,400);
		excelOutBtn.setBounds(420,100,120,40);
		backBtn.setBounds(450,300,60,40);
		
		backBtn.addActionListener(this);
	    backBtn.setActionCommand("responsPanel");
	    
	    resultPanel.add(setName);
	    resultPanel.add(excelOutBtn);
//	    resultPanel.add(resultList);
	    resultPanel.add(resultScrollPanel);
		resultPanel.add(backBtn);

		// cardLayout set
		changePanels = new JPanel();
		cardLayout = new CardLayout();
		changePanels.setLayout(cardLayout);	
		changePanels.add(searchPanel);
		changePanels.add(resultPanel);
		
        contentPanel.add(changePanels,BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();

        if (cmd.equals("searchPanel")){
            cardLayout.last(changePanels);
        }else if(cmd.equals("responsPanel")){
            cardLayout.first(changePanels);
       }
    }

}