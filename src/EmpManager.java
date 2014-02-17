import javax.swing.*;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class EmpManager extends JFrame implements ActionListener {
	JPanel cardPanel;
	
	CardLayout layout;
	JTextField dltEmpIdTxtFld;
	JTextField dltEmpNameTxtFld;
	JTextField dltDeptTxtFld;
	JLabel Label;
	JTextField empIdTxtFld;
	JPasswordField passFld;
	JLabel titleLabel;

	//Search panel
	JTable resultTable = new JTable();
	DefaultTableModel modelTable = new DefaultTableModel();
	JScrollPane resultScrollPanel;

	private String title;
	public String cmd;

	public static void main(String[] args) {
		EmpManager frame = new EmpManager();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, 650, 550);
		frame.setResizable(false);
		frame.setTitle("管理システム");
		frame.setVisible(true);
	}

	EmpManager() {
		
		// DataBase access
		final AccessDB access = new AccessDB();
		
		// ////////// card0 loginPanel /////////////////////////////

		final JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.add(new JButton("Login"));

		// screen setting
		setTitle(title);
		setBounds(100, 100, 200, 250);
		JLabel Title1 = new JLabel("ログイン画面");
		Title1.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 24));
		Title1.setBounds(200, 10, 200, 30);
		
		// Label setting
		JLabel ID = new JLabel("ID:");
		ID.setBounds(120, 104, 50, 20);
		ID.setFont(new Font("MS　ゴシック", Font.BOLD, 20));
		empIdTxtFld = new JTextField("", 20);
		empIdTxtFld.setBounds(150, 100, 200, 30);
		JLabel PW = new JLabel("PW:");
		PW.setBounds(105, 204, 50, 20);
		PW.setFont(new Font("MS　ゴシック", Font.BOLD, 20));
		passFld = new JPasswordField("", 20);
		passFld.setBounds(150, 200, 200, 30);

		// login action
		final JButton loginBtn = new JButton("ログイン");

		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passFld.getPassword());
				if (access.loginCheck(empIdTxtFld.getText(),password)){
					cardPanel.add(loginPanel, "Login");
					empIdTxtFld.setText("");
					passFld.setText("");
				}
				else{
					empIdTxtFld.setText("");
					passFld.setText("");
				}
			}
		});

		// btn setting
		loginBtn.setBounds(400, 350, 100, 30);
		loginBtn.addActionListener(this);

		titleLabel = new JLabel();

		loginPanel.add(Title1);
		loginPanel.add(empIdTxtFld);
		loginPanel.add(passFld);
		loginPanel.add(ID);
		loginPanel.add(PW);
		loginPanel.add(loginBtn);

		// ////////////////// / card1 topPanel ///////////////////////////
		final JPanel topPanel = new JPanel();
		topPanel.add(new JButton("Button"));
		// パネルを作成
		topPanel.setLayout(null);
		topPanel.setSize(600, 500);

		// ラベル作成
		JLabel ToppageLabel = new JLabel("トップページ");
		topPanel.add(ToppageLabel);
		ToppageLabel.setFont(new Font("Aril", Font.PLAIN, 50));
		// ボタン作成
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

		// ボタンサイズと配置とラベル
		ToppageLabel.setBounds(50, 50, 300, 50);
		logoutBtn.setBounds(400, 50, 150, 40);
		addBtn.setBounds(125, 150, 360, 40);
		searchBtn.setBounds(125, 250, 360, 40);
		deleteBtn.setBounds(125, 350, 360, 40);

		topPanel.add(ToppageLabel);
		topPanel.add(addBtn);
		topPanel.add(searchBtn);
		topPanel.add(deleteBtn);
		topPanel.add(logoutBtn);

		// //////////////////* insertPanel insertPanel */////////////////////
		final JPanel insertPanel = new JPanel();
		insertPanel.setLayout(null);
		insertPanel.add(new JButton("button"));
		// 　Labelに文字をいれる
		JLabel insInsertLabel = new JLabel("社員追加");
		JLabel insEmpIdLabel = new JLabel("ID");
		JLabel insNameLabel = new JLabel("名前");
		JLabel insDeptLabel = new JLabel("所属");
		JLabel insPostLabel = new JLabel("役職");

		// フォントの大きさ指定
		insInsertLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
		insEmpIdLabel.setFont(new Font("Aril", Font.PLAIN, 28));
		insNameLabel.setFont(new Font("Aril", Font.PLAIN, 28));
		insDeptLabel.setFont(new Font("Aril", Font.PLAIN, 28));
		insPostLabel.setFont(new Font("Aril", Font.PLAIN, 28));

		// Labelに対応したTextFildの作成
		final JTextField insEmpIdTxtFld = new JTextField();
		final JTextField insNameTxtFld = new JTextField();
		final JTextField insDeptTxtFld = new JTextField();
		final JTextField insPostTxtFld = new JTextField();

		// Buttonの作成
		JButton insTopBtn = new JButton("TOP");
		insTopBtn.addActionListener(this);
		insTopBtn.setActionCommand("Toppage");

		final JButton insertBtn = new JButton("追加");
		insertBtn.addActionListener(new ActionListener(){
			@Override
				public void actionPerformed(ActionEvent e) {
					if(access.insertDB(insEmpIdTxtFld.getText(),insNameTxtFld.getText(),insDeptTxtFld.getText(),insPostTxtFld.getText())){
						JOptionPane.showMessageDialog(insertPanel, "追加しました。");
					}else{
						JOptionPane.showMessageDialog(insertPanel, "追加できませんでした。");
						
					}
				}
			});

		// Label,TxtFld,ButtonをPanelに挿入
		insertPanel.add(insInsertLabel);
		insertPanel.add(insTopBtn);
		insertPanel.add(insEmpIdLabel);
		insertPanel.add(insEmpIdTxtFld);
		insertPanel.add(insNameLabel);
		insertPanel.add(insNameTxtFld);
		insertPanel.add(insDeptLabel);
		insertPanel.add(insDeptTxtFld);
		insertPanel.add(insPostLabel);
		insertPanel.add(insPostTxtFld);
		insertPanel.add(insertBtn);

		// Label,TxtFld,Buttonの位置を座標で指定
		insInsertLabel.setBounds(50, 10, 300, 50);
		insTopBtn.setBounds(380, 20, 100, 50);
		insEmpIdLabel.setBounds(170, 125, 70, 30);
		insEmpIdTxtFld.setBounds(250, 125, 150, 30);
		insNameLabel.setBounds(150, 200, 70, 30);
		insNameTxtFld.setBounds(250, 200, 150, 30);
		insDeptLabel.setBounds(150, 275, 70, 30);
		insDeptTxtFld.setBounds(250, 275, 150, 30);
		insPostLabel.setBounds(150, 350, 70, 30);
		insPostTxtFld.setBounds(250, 350, 150, 30);
		insertBtn.setBounds(230, 410, 100, 30);

		// ///////////////* card3 deletPanel */////////////////////
		final JPanel deletePanel = new JPanel();
		deletePanel.setLayout(null);

		// 社員番号入力フィールド
		JLabel dltEmpIdLabel = new JLabel("社員ID");
		dltEmpIdLabel.setFont(new Font("Aril", Font.PLAIN, 28));
		dltEmpIdLabel.setBounds(150, 100, 100, 30);
		dltEmpIdTxtFld = new JTextField("", 20);
		dltEmpIdTxtFld.setBounds(250, 100, 150, 30);

		// 名前入力フィールド
		JLabel dltEmpNameLabel = new JLabel("社員名");
		dltEmpNameLabel.setBounds(150, 200, 100, 30);
		dltEmpNameLabel.setFont(new Font("Aril", Font.PLAIN, 28));
		dltEmpNameTxtFld = new JTextField("", 20);
		dltEmpNameTxtFld.setBounds(250, 200, 150, 30);

		// 部署入力フィールド
		JLabel dltDeptLabel = new JLabel("部署");
		dltDeptLabel.setFont(new Font("Aril", Font.PLAIN, 28));
		dltDeptLabel.setBounds(150, 300, 100, 30);
		dltDeptTxtFld = new JTextField("", 10);
		dltDeptTxtFld.setBounds(250, 300, 150, 30);

		// enterボタン作成
		JButton enterBtn = new JButton("Enter");
		enterBtn.setBounds(450, 300, 100, 30);
		
		enterBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(dltEmpIdTxtFld.getText().equals("") && dltEmpNameTxtFld.getText().equals("") && dltDeptTxtFld.getText().equals("")){
					JOptionPane.showMessageDialog(deletePanel, "入力されていません。");
				}else{
					if(access.dataExists(dltEmpIdTxtFld.getText(), dltEmpNameTxtFld.getText(), dltDeptTxtFld.getText())){
						if(access.deleteDB(dltEmpIdTxtFld.getText(),dltEmpNameTxtFld.getText(),dltDeptTxtFld.getText())){
							JOptionPane.showMessageDialog(deletePanel, "削除しました。");
						}else{
							JOptionPane.showMessageDialog(deletePanel, "削除できませんでした。");
						}
					}else{
						JOptionPane.showMessageDialog(deletePanel, "データがありません。");
					}
				}
			}
		});

		// TOPボタン作成
		JButton dltTopBtn = new JButton("TOP");
		dltTopBtn.addActionListener(this);
		dltTopBtn.setActionCommand("Toppage");
		dltTopBtn.setBounds(380, 20, 100, 50);

		Label = new JLabel();

		// コンテンツ追加
		JLabel deleteLabel = new JLabel("削除画面");
		deleteLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
		deleteLabel.setBounds(50, 10, 300, 50);

		deletePanel.add(deleteLabel);
		deletePanel.add(dltEmpIdLabel);
		deletePanel.add(dltEmpIdTxtFld);
		deletePanel.add(dltEmpNameLabel);
		deletePanel.add(dltEmpNameTxtFld);
		deletePanel.add(dltDeptLabel);
		deletePanel.add(dltDeptTxtFld);
		deletePanel.add(enterBtn);
		deletePanel.add(dltTopBtn);

		Label.setHorizontalAlignment(JLabel.CENTER);
		Label.setVerticalAlignment(JLabel.TOP);

		Container contentPane1 = getContentPane();
		contentPane1.add(deletePanel, BorderLayout.CENTER);
		contentPane1.add(Label, BorderLayout.SOUTH);

		// ///////////////* card4　 search & resultPanel */////////////////////
		// Search panels
		final JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null); // レイアウト手動

		JLabel searchTitle = new JLabel("社員検索");
		searchTitle.setFont(new Font("ＭＳ 明朝", Font.BOLD, 26));

			// content setting
		JLabel id = new JLabel("ID検索");
		JLabel name = new JLabel("社員名検索");
		JLabel dept = new JLabel("部署名検索");
		final JTextField idTextFld = new JTextField();
		final JTextField nameTextFld = new JTextField();
		final JTextField deptTextFld = new JTextField();
		final JButton idSearchBtn = new JButton("検索");
		JButton nameSearchBtn = new JButton("検索");
		JButton deptSearchBtn = new JButton("検索");
		JButton searchReturnBtn = new JButton("TOP");

		idTextFld.setText("");
		nameTextFld.setText("");
		deptTextFld.setText("");
		
			// layout setting
		searchTitle.setBounds(250, 5, 150, 25);
		id.setBounds(110, 100, 70, 40);
		name.setBounds(110, 180, 70, 40);
		dept.setBounds(110, 260, 70, 40);
		idTextFld.setBounds(220, 100, 160, 50);
		nameTextFld.setBounds(220, 180, 160, 50);
		deptTextFld.setBounds(220, 260, 160, 50);
		idSearchBtn.setBounds(400, 100, 60, 50);
		nameSearchBtn.setBounds(400, 180, 60, 50);
		deptSearchBtn.setBounds(400, 260, 60, 50);
		searchReturnBtn.setBounds(280, 410, 60, 40);

		searchPanel.add(searchTitle);
		searchPanel.add(id);
		searchPanel.add(idTextFld);
		searchPanel.add(name);
		searchPanel.add(nameTextFld);
		searchPanel.add(dept);
		searchPanel.add(deptTextFld);
		searchPanel.add(idSearchBtn);
//		searchPanel.add(nameSearchBtn);
//		searchPanel.add(deptSearchBtn);
		searchPanel.add(searchReturnBtn);

		// ボタンの設定
		idSearchBtn.addActionListener(this);
		idSearchBtn.setActionCommand("Result");
		nameSearchBtn.addActionListener(this);
		nameSearchBtn.setActionCommand("Result");
		deptSearchBtn.addActionListener(this);
		deptSearchBtn.setActionCommand("Result");

		idSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(idTextFld.getText().equals("") && nameTextFld.getText().equals("") && deptTextFld.getText().equals("") && dltDeptTxtFld.getText().equals("")){
					JOptionPane.showMessageDialog(searchPanel, "入力されていません。");
					cardPanel.add(searchPanel, "Result");
				}
					
				else{
					// id check
					if(!idTextFld.getText().equals("")){
						// is id check
						System.out.println("is id");
						if(!access.dataExists(idTextFld.getText(),nameTextFld.getText().toUpperCase(),deptTextFld.getText().toUpperCase())){
							JOptionPane.showMessageDialog(searchPanel, "検索結果がありません");
							cardPanel.add(searchPanel, "Result");
							cardPanel.add(searchPanel, "Result");
						}
						modelTable = access.idSelectDB(Integer.parseInt(idTextFld.getText()),
								nameTextFld.getText().toUpperCase(),deptTextFld.getText().toUpperCase());
						resultTable.setModel(modelTable);
					}else{
						// no id check
						if(!access.dataExists(idTextFld.getText(),nameTextFld.getText().toUpperCase(),deptTextFld.getText().toUpperCase())){
							JOptionPane.showMessageDialog(searchPanel, "検索結果がありません");
							cardPanel.add(searchPanel, "Result");
						}
						
						System.out.println("no id");
						modelTable = access.idSelectDB(nameTextFld.getText().toUpperCase(),
								deptTextFld.getText().toUpperCase());
						resultTable.setModel(modelTable);
					}
				}	
			}
		});
/*		
		nameSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelTable = access.nameSelectDB(nameTextFld.getText());
				resultTable.setModel(modelTable);
			}
		});

		deptSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelTable = access.deptSelectDB(deptTextFld.getText()
						.toUpperCase());
				resultTable.setModel(modelTable);
			}
		});
		
		searchReturnBtn.addActionListener(new ActionListener() { public
		  void actionPerformed(ActionEvent e){ 
			
		} });
*/
		
		searchReturnBtn.addActionListener(this);
		searchReturnBtn.setActionCommand("Toppage");
		
		// resultPanels
		final JPanel resultPanel = new JPanel();
		resultPanel.setLayout(null);
		resultScrollPanel = new JScrollPane(resultTable);

		JLabel setName = new JLabel();
		JButton excelOutBtn = new JButton("Excelで出力");
		excelOutBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(access.excelOut(idTextFld.getText(),nameTextFld.getText().toUpperCase(),deptTextFld.getText().toUpperCase()))
					JOptionPane.showMessageDialog(resultPanel, "作成しました");
				else
					JOptionPane.showMessageDialog(resultPanel, "作成できませんでした。");
			}
		});
		JButton backBtn = new JButton("戻る");

		resultScrollPanel.setBounds(20, 40, 390, 400);
		excelOutBtn.setBounds(460, 100, 120, 40);
		backBtn.setBounds(480, 300, 60, 40);

		backBtn.addActionListener(this);
		backBtn.setActionCommand("Search");

		
//////////// cardlayout setting ///////////////////////////////		
		
		resultPanel.add(setName);
		resultPanel.add(excelOutBtn);
		resultPanel.add(resultScrollPanel);
		resultPanel.add(backBtn);

		cardPanel = new JPanel();
		layout = new CardLayout();
		cardPanel.setLayout(layout);
		cardPanel.add(loginPanel, "Login");
		cardPanel.add(topPanel, "Toppage");
		cardPanel.add(insertPanel, "Insert");
		cardPanel.add(deletePanel, "Delete");
		cardPanel.add(searchPanel, "Search");
		cardPanel.add(resultPanel, "Result");
		
		getContentPane().add(cardPanel, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		layout.show(cardPanel, cmd);
	}
}