import javax.swing.*;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;
//maru
public class EmpManager extends JFrame implements ActionListener{

  JPanel cardPanel;
  CardLayout layout;
  JTextField dltEmpIdTxtFld;
  JTextField dltEmpNameTxtFld;
  JTextField dltDeptTxtFld;
  JLabel Label;
  
  JPanel changePanels;
	CardLayout cardLayout;
	String responsArray;
	DefaultListModel responsList;
	JList resultList = new JList();
	JScrollPane resultScrollPanel = new JScrollPane();

  public static void main(String[] args){
	  EmpManager frame = new EmpManager();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(50, 50, 650, 550);
    frame.setTitle("管理システム");
    frame.setVisible(true);
  }

  EmpManager(){
	  
	  final AccessDB access = new AccessDB();
	  
   //////////////////// /* カード1　（トップページ） *///////////////////////////
    JPanel card1 = new JPanel();
    card1.add(new JButton("button"));
        //パネルを作成
  		card1.setLayout(null);
  		card1.setSize(600, 500);
  		
  		//ボタン作成
  		JButton addBtn = new JButton("追加");
  		JButton searchBtn = new JButton("検索");
  		JButton deleteBtn = new JButton("削除");
  		
  		//ボタンサイズと配置
  		addBtn.setBounds(100, 100, 100, 30);
  		searchBtn.setBounds(200, 200, 100, 30);
  		deleteBtn.setBounds(300, 300, 100, 30);
  		
  		card1.add(addBtn);
  		card1.add(searchBtn);
  		card1.add(deleteBtn);

    ////////////////////* カード2 (追加ページ） */////////////////////
    JPanel card2 = new JPanel();
    card2.setLayout(null);
    card2.add(new JButton("button"));
	//　Labelに文字をいれる
	JLabel insInsertLabel = new JLabel("社員追加");
	JLabel insEmpIdLabel  = new JLabel("ID");
	JLabel insNameLabel   = new JLabel("名前");
	JLabel insDeptLabel   = new JLabel("所属");
	JLabel insPostLabel   = new JLabel("役職");
	
	// フォントの大きさ指定
	insInsertLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
	insEmpIdLabel. setFont(new Font("Aril", Font.PLAIN, 28));
	insNameLabel.  setFont(new Font("Aril", Font.PLAIN, 28));
	insDeptLabel.  setFont(new Font("Aril", Font.PLAIN, 28));
	insPostLabel.  setFont(new Font("Aril", Font.PLAIN, 28));
	
	// Labelに対応したTextFildの作成
	final JTextField insEmpIdTxtFld = new JTextField();
	final JTextField insNameTxtFld = new JTextField();
	final JTextField insDeptTxtFld = new JTextField();
//	JTextField insPostTxtFld = new JTextField();
	
	//Buttonの作成
	JButton insTopBtn = new JButton("TOP");
	JButton insertBtn = new JButton("追加");
	insertBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
        	access.insertDB(Integer.parseInt(insEmpIdTxtFld.getText()),insNameTxtFld.getText(),insDeptTxtFld.getText());
        }
    });
	
	//Label,TxtFld,ButtonをPanelに挿入
	card2.add(insInsertLabel);card2.add(insTopBtn);
	card2.add(insEmpIdLabel); card2.add(insEmpIdTxtFld);
	card2.add(insNameLabel); card2.add(insNameTxtFld);
	card2.add(insDeptLabel); card2.add(insDeptTxtFld);
//	card2.add(insPostLabel); card2.add(insPostTxtFld);
	card2.add(insertBtn);
	
	//Label,TxtFld,Buttonの位置を座標で指定
	insInsertLabel.setBounds(50,10,300,50); insTopBtn.setBounds(380,20,100,50);
	insEmpIdLabel.setBounds(170,125,70,30); insEmpIdTxtFld.setBounds(250,125,150,30);
	insNameLabel. setBounds(150,200,70,30); insNameTxtFld.setBounds(250,200,150,30);
	insDeptLabel. setBounds(150,275,70,30); insDeptTxtFld.setBounds(250,275,150,30);
//	insPostLabel. setBounds(150,350,70,30); insPostTxtFld.setBounds(250,350,150,30);
	insertBtn.setBounds(230,410,100,30);
	
	
	//Container contentPanenu = getContentPane();

   /////////////////* カード3　　　（削除ページ） */////////////////////
    JPanel Deletepanel = new JPanel();
    Deletepanel.setLayout(null);

    //社員番号入力フィールド
    JLabel dltEmpIdLabel = new JLabel("社員ID");
    dltEmpIdLabel.setFont(new Font("Aril", Font.PLAIN, 28));
    dltEmpIdLabel.setBounds(150, 100, 100, 30);
    dltEmpIdTxtFld = new JTextField("", 20);
    dltEmpIdTxtFld.setBounds(250, 100, 150, 30);
    
    //名前入力フィールド
    JLabel dltEmpNameLabel = new JLabel("社員名");
    dltEmpNameLabel.setBounds(150, 200, 100, 30);
    dltEmpNameLabel.setFont(new Font("Aril", Font.PLAIN, 28));
    dltEmpNameTxtFld = new JTextField("", 20);
    dltEmpNameTxtFld.setBounds(250, 200, 150, 30);
    
    //部署入力フィールド
    JLabel dltDeptLabel = new JLabel("部署");
    dltDeptLabel.setFont(new Font("Aril", Font.PLAIN, 28));
    dltDeptLabel.setBounds(150, 300, 100, 30);
    dltDeptTxtFld = new JTextField("", 10);
    dltDeptTxtFld.setBounds(250, 300, 150, 30);
    
    //enterボタン作成
    JButton enterBtn = new JButton("Enter");
    enterBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
        	access.updateDB(Integer.parseInt(dltEmpIdTxtFld.getText()));
        }
    });
    enterBtn.setBounds(450, 300, 100, 30);
    
    //TOPボタン作成
    JButton dltTopBtn = new JButton("TOP");
    dltTopBtn.setBounds(380, 20, 100, 50);
    
    enterBtn.addActionListener(this);
    Label = new JLabel();

    //コンテンツ追加
    JLabel deleteLabel = new JLabel("削除画面");
	  deleteLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
	  deleteLabel.setBounds(50, 10, 300, 50);
    
	  Deletepanel.add(deleteLabel);
    
	  Deletepanel.add(dltEmpIdLabel);
	  Deletepanel.add(dltEmpIdTxtFld);
    
	  Deletepanel.add(dltEmpNameLabel);
	  Deletepanel.add(dltEmpNameTxtFld);
    
	  Deletepanel.add(dltDeptLabel);
	  Deletepanel.add(dltDeptTxtFld);
    
	  Deletepanel.add(enterBtn);
	  Deletepanel.add(dltTopBtn);

	  Label.setHorizontalAlignment(JLabel.CENTER);
      Label.setVerticalAlignment(JLabel.TOP);
      
    Container contentPane = getContentPane();
    contentPane.add(Deletepanel, BorderLayout.CENTER);
    contentPane.add(Label, BorderLayout.SOUTH);
    
    /////////////////* カード4　　　（検索ページ） */////////////////////
   
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
  		
  		//layout setting
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
  		
  		
  		//button setting
  		idSearchBtn.addActionListener(this);
  	    idSearchBtn.setActionCommand("resultPanel");
  	    nameSearchBtn.addActionListener(this);
  	    nameSearchBtn.setActionCommand("resultPanel");
  	    deptSearchBtn.addActionListener(this);
  	    deptSearchBtn.setActionCommand("resultPanel");
  	    
  	    idSearchBtn.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e){
              	responsList = access.idSelectDB(Integer.parseInt(idText.getText()));
              	resultList.setModel(responsList);
  			}
  	    });
  	    
  	    nameSearchBtn.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e){
              	responsList = access.nameSelectDB(nameText.getText().toUpperCase());
              	resultList.setModel(responsList);
              }
  	    });
  	    
  	    deptSearchBtn.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e){
              	responsList = access.deptSelectDB(deptText.getText());
              	resultList.setModel(responsList);
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
  		JLabel setName = new JLabel();
  		JButton backBtn = new JButton("戻る");
  		backBtn.setBounds(450,150,60,40);
  		backBtn.addActionListener(this);
  	    backBtn.setActionCommand("resultPanel");
  	    resultList.setBounds(160,40,300,400);
  	    resultPanel.add(setName);
  	    resultPanel.add(resultList);
  	    
  		resultPanel.add(backBtn);
    
    
    
    JPanel card4 = new JPanel();

    cardPanel = new JPanel();
    layout = new CardLayout();
    cardPanel.setLayout(layout);

    cardPanel.add(card1, "button");
    cardPanel.add(card2, "label");
    cardPanel.add(Deletepanel, "checkbox");
    cardPanel.add(searchPanel,"searchPanel");
    cardPanel.add(resultPanel,"resultPanel");

    /* カード移動用ボタン */
    JButton button1 = new JButton("トップページ");
    button1.addActionListener(this);
    button1.setActionCommand("button");

    JButton button4 = new JButton("検索");
    button4.addActionListener(this);
    button4.setActionCommand("searchPanel");
    
    JButton button2 = new JButton("追加");
    button2.addActionListener(this);
    button2.setActionCommand("label");

    JButton button3 = new JButton("削除");
    button3.addActionListener(this);
    button3.setActionCommand("checkbox");

    JPanel btnPanel = new JPanel();
    btnPanel.add(button1);
    btnPanel.add(button4);
    btnPanel.add(button2);
    btnPanel.add(button3);

    getContentPane().add(cardPanel, BorderLayout.CENTER);
    getContentPane().add(btnPanel, BorderLayout.PAGE_END);
  }

  public void actionPerformed(ActionEvent e){
    String cmd = e.getActionCommand();

    layout.show(cardPanel, cmd);
  }
}