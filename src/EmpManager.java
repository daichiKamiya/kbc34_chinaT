import javax.swing.*;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JFrame;

public class EmpManager extends JFrame implements ActionListener{

// kamiya
	
	JPanel cardPanel;
  CardLayout layout;
  JTextField dltEmpIdTxtFld;
  JTextField dltEmpNameTxtFld;
  JTextField dltDeptTxtFld;
  JLabel Label;
  JTextField empIdTxtFld;
  JTextField psssTxtFld;
  JLabel titleLabel;
private String title;
public String cmd;

  public static void main(String[] args){
	  EmpManager frame = new EmpManager();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(50, 50, 650, 550);
    frame.setTitle("管理システム");
    frame.setVisible(true);
  }

  EmpManager(){
	  ////////////カード0/////////////////////////////
	  
	 final JPanel card0 = new JPanel();
	    card0.setLayout(null);
	    card0.add(new JButton("Login"));
            // 画面の作成
		    setTitle(title);
		   setBounds(100, 100, 200, 250);
		   JLabel Title1 = new JLabel("ログイン画面");
		  Title1.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 24));
			  Title1.setBounds(200,10,200,30);
		    //ラベルの作成
		   JLabel ID = new JLabel("ID:");
		    ID.setBounds(120,104,50,20);
		    ID.setFont(new Font("MS　ゴシック", Font.BOLD, 20));
		     empIdTxtFld = new JTextField("", 20);
		     empIdTxtFld.setBounds(150,100,200,30);
		    JLabel PW = new JLabel("PW:");
		    PW.setBounds(105,204,50,20);
		    PW.setFont(new Font("MS　ゴシック", Font.BOLD, 20));
		    psssTxtFld = new JTextField("", 20);
		    psssTxtFld.setBounds(150,200,200,30);
		    
		    //ログイン処理
		    final JButton loginBtn = new JButton("ログイン");
		    
		    loginBtn.addActionListener( new ActionListener(){
		    	
				@Override
				public void actionPerformed(ActionEvent e) {
					 //TODO 自動生成されたメソッド・スタブ
			    	
					String str1 = empIdTxtFld.getText() + psssTxtFld.getText();
					        if (str1.equals("roothimitu"))
					            cardPanel.add(card0,"Login");

					        	 
				}
		    });



		    //ボタンの作成
		    loginBtn.setBounds(400,350,100,30);
		    loginBtn.addActionListener(this);
		    
		    titleLabel = new JLabel();

		    card0.add(Title1);
		    card0.add(empIdTxtFld);
		    card0.add(psssTxtFld);
		    card0.add(ID);
		    card0.add(PW);
		    card0.add(loginBtn);


   //////////////////// /* カード1　（トップページ） *///////////////////////////
			   final JPanel card1 = new JPanel();
			    card1.add(new JButton("Button"));
		        //パネルを作成
		  		card1.setLayout(null);
		  		card1.setSize(600, 500);
		  		
		  		//ラベル作成
		  		JLabel ToppageLabel = new JLabel("トップページ");
		  		card1.add(ToppageLabel);
		  		ToppageLabel.setFont(new Font("Aril", Font.PLAIN, 50));
		  		//ボタン作成
		  		JButton addBtn = new JButton("追加");
		  		addBtn.addActionListener(this);
		  	    addBtn.setActionCommand("Insert");
		  	    
		  		JButton searchBtn = new JButton("検索");
		  		searchBtn.addActionListener(this);
		  		searchBtn.setActionCommand("Search");
		  	    
		  		JButton deleteBtn = new JButton("削除");
		  		deleteBtn.addActionListener(this);
		  		deleteBtn.setActionCommand("Delete");
		  		
		  		JButton logoutBtn = new JButton("ログアウト");
		  		logoutBtn.addActionListener(this);
		  		logoutBtn.setActionCommand("Login");
		  		
		  		//ボタンサイズと配置とラベル
		  		ToppageLabel.setBounds(50,50,300,50 );
		  		logoutBtn.setBounds(400, 50, 150, 40);
				addBtn.setBounds(125, 150, 360, 40);
				searchBtn.setBounds(125, 250, 360, 40);
				deleteBtn.setBounds(125, 350, 360, 40);
		  		
				card1.add(ToppageLabel);
		  		card1.add(addBtn);
		  		card1.add(searchBtn);
		  		card1.add(deleteBtn);
		  		card1.add(logoutBtn);

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
		  		JTextField insEmpIdTxtFld = new JTextField();
		  		JTextField insNameTxtFld = new JTextField();
		  		JTextField insDeptTxtFld = new JTextField();
		  		JTextField insPostTxtFld = new JTextField();
		  		
		  		//Buttonの作成
		  		JButton insTopBtn = new JButton("TOP");
		  		insTopBtn.addActionListener(this);
		  		insTopBtn.setActionCommand("Toppage");

		  		JButton insertBtn = new JButton("追加");
		  		insertBtn.addActionListener(this);
		  		insertBtn.setActionCommand("Inser");
		  		
		  		//Label,TxtFld,ButtonをPanelに挿入
		  		card2.add(insInsertLabel);card2.add(insTopBtn);
		  		card2.add(insEmpIdLabel); card2.add(insEmpIdTxtFld);
		  		card2.add(insNameLabel); card2.add(insNameTxtFld);
		  		card2.add(insDeptLabel); card2.add(insDeptTxtFld);
		  		card2.add(insPostLabel); card2.add(insPostTxtFld);
		  		card2.add(insertBtn);
		  		
		  		//Label,TxtFld,Buttonの位置を座標で指定
		  		insInsertLabel.setBounds(50,10,300,50); insTopBtn.setBounds(380,20,100,50);
		  		insEmpIdLabel.setBounds(170,125,70,30); insEmpIdTxtFld.setBounds(250,125,150,30);
		  		insNameLabel. setBounds(150,200,70,30); insNameTxtFld.setBounds(250,200,150,30);
		  		insDeptLabel. setBounds(150,275,70,30); insDeptTxtFld.setBounds(250,275,150,30);
		  		insPostLabel. setBounds(150,350,70,30); insPostTxtFld.setBounds(250,350,150,30);
		  		insertBtn.setBounds(230,410,100,30);
		  		
		  		
		  		//Container contentPanenu = getContentPane();

   /////////////////* カード3　　　（削除ページ） */////////////////////
		  		JPanel card3 = new JPanel();
		  	    card3.setLayout(null);
		  	    
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
		  	    enterBtn.setBounds(450, 300, 100, 30);
		  	    enterBtn.addActionListener(this);
		  	    enterBtn.setActionCommand("Del");
		  	    
		  	    //TOPボタン作成
		  	    JButton dltTopBtn = new JButton("TOP");
		  	    dltTopBtn.addActionListener(this);
		  	    dltTopBtn.setActionCommand("Toppage");
		  	    dltTopBtn.setBounds(380, 20, 100, 50);
		  	    
		  	    Label = new JLabel();

		  	    //コンテンツ追加
		  	    JLabel deleteLabel = new JLabel("削除画面");
		  		  deleteLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
		  		  deleteLabel.setBounds(50, 10, 300, 50);
		  	    
		  		  card3.add(deleteLabel);
		  	    
		  		  card3.add(dltEmpIdLabel);
		  		  card3.add(dltEmpIdTxtFld);
		  	    
		  		  card3.add(dltEmpNameLabel);
		  		  card3.add(dltEmpNameTxtFld);
		  	    
		  		  card3.add(dltDeptLabel);
		  		  card3.add(dltDeptTxtFld);
		  	    
		  		  card3.add(enterBtn);
		  		  card3.add(dltTopBtn);

		  		  Label.setHorizontalAlignment(JLabel.CENTER);
		  	      Label.setVerticalAlignment(JLabel.TOP);
		  	      
		  	    Container contentPane1 = getContentPane();
		  	    contentPane1.add(card3, BorderLayout.CENTER);
		  	    contentPane1.add(Label, BorderLayout.SOUTH);

		  	    
    
    /////////////////* カード4　　　（検索ページ） */////////////////////
    JPanel card4 = new JPanel();

    
    
    
    
    cardPanel = new JPanel();
    layout = new CardLayout();
    cardPanel.setLayout(layout);
    cardPanel.add(card0,"Login");
    cardPanel.add(card1,"Toppage");
    cardPanel.add(card2, "Insert");
    cardPanel.add(card3, "Delete");

    getContentPane().add(cardPanel, BorderLayout.CENTER);
  }



  public void actionPerformed(ActionEvent e){
	    String cmd = e.getActionCommand();

	    layout.show(cardPanel, cmd);
	    
	    String daiya = e.getActionCommand();

	    if (daiya.equals("Del")){
	      JOptionPane.showMessageDialog(this, "削除完了", "削除メッセージ",
	        JOptionPane.PLAIN_MESSAGE);
	    }else if(daiya.equals("Inser")){
	    	  JOptionPane.showMessageDialog(this, "社員を一人追加しました。", "追加メッセージ",
	    		        JOptionPane.PLAIN_MESSAGE);
	      
	    
	    }

	  }
	}