import javax.swing.*;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class EmpManager extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	JPanel cardPanel;
	CardLayout layout;
	JTextField dltEmpIdTxtFld;
	JTextField dltEmpNameTxtFld;
	JLabel Label;
	JTextField empIdTxtFld;
	JPasswordField passFld;
	JLabel titleLabel;
	JTable resultTable = new JTable();
	DefaultTableModel modelTable = new DefaultTableModel();
	JScrollPane resultScrollPanel;

	
	public static void main(String[] args) {
		EmpManager frame = new EmpManager();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, 650, 550);
		frame.setResizable(false);
		
		frame.setTitle("社員管理システム");
		frame.setVisible(true);
	}

	EmpManager() {
		// DataBase access
		final AccessDB access = new AccessDB();

		// ////////// card0 loginPanel /////////////////////////////

		final JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.add(new JButton("Login"));

		
		JLabel Title1 = new JLabel("ログイン");

		Title1.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
		Title1.setBounds(50, 10, 400, 110);

		JLabel loginIdLabel = new JLabel("ID:");
		JLabel PW = new JLabel("PW:");
		loginIdLabel.setBounds(150, 204, 50, 20);
		loginIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
		PW.setBounds(135, 284, 50, 20);
		PW.setFont(new Font("Arial", Font.BOLD, 20));
		empIdTxtFld = new JTextField("", 20);
		empIdTxtFld.setBounds(180, 200, 200, 30);
		passFld = new JPasswordField("", 20);
		passFld.setBounds(180, 280, 200, 30);

		// login action
		final JButton loginBtn = new JButton("ログイン");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				char[] password = passFld.getPassword();
				String passStr = new String(password);
					if (access.loginCheck(empIdTxtFld.getText(), passStr)) {
					cardPanel.add(loginPanel, "Login");
					empIdTxtFld.setText("");
					passFld.setText("");
				} else {
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
		loginPanel.add(loginIdLabel);
		loginPanel.add(PW);
		loginPanel.add(loginBtn);

		// ////////////////// / card1 topPanel ///////////////////////////
		
		final JPanel topPanel = new JPanel();
		topPanel.add(new JButton("Button"));
		// パネルを作成
		topPanel.setLayout(null);

		// ラベル作成
		JLabel ToppageLabel = new JLabel("トップページ");
		topPanel.add(ToppageLabel);
		ToppageLabel.setFont(new Font("MSゴシック", Font.BOLD, 50));

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
		ToppageLabel.setBounds(50, 10, 400, 110);
		logoutBtn.setBounds(430, 50, 150, 40);
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

		// Labelに文字をいれる
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
		insertBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (access.insertDB(insEmpIdTxtFld.getText(),
						insNameTxtFld.getText(), insDeptTxtFld.getText(),
						insPostTxtFld.getText())) {
					JOptionPane.showMessageDialog(insertPanel, "追加しました。");
				} else {
					JOptionPane.showMessageDialog(insertPanel, "追加できませんでした。");

				}
			}
		});

		// Label,TxtFld,Buttonの位置を座標で指定
		insInsertLabel.setBounds(50, 10, 400, 110);
		insTopBtn.setBounds(530, 45, 70, 40);
		insEmpIdLabel.setBounds(170, 125, 90, 30);
		insEmpIdTxtFld.setBounds(250, 125, 180, 30);
		insNameLabel.setBounds(150, 200, 90, 30);
		insNameTxtFld.setBounds(250, 200, 180, 30);
		insDeptLabel.setBounds(150, 275, 90, 30);
		insDeptTxtFld.setBounds(250, 275, 180, 30);
		insPostLabel.setBounds(150, 350, 90, 30);
		insPostTxtFld.setBounds(250, 350, 180, 30);
		insertBtn.setBounds(450, 420, 70, 50);

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

		// ///////////////* card3 deletPanel */////////////////////
		final JPanel deletePanel = new JPanel();
		deletePanel.setLayout(null);
		
		//タイトル
		JLabel deleteLabel = new JLabel("社員削除");
		deleteLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
		deleteLabel.setBounds(50, 10, 400, 110);
		
		// 社員番号入力フィールド
		JLabel dltEmpIdLabel = new JLabel("社員ID");
		dltEmpIdLabel.setFont(new Font("Aril", Font.PLAIN, 28));
		dltEmpIdLabel.setBounds(140, 160, 110, 40);
		dltEmpIdTxtFld = new JTextField("", 20);
		dltEmpIdTxtFld.setBounds(280, 160, 200, 40);

		// 名前入力フィールド
		JLabel dltEmpNameLabel = new JLabel("社員名");
		dltEmpNameLabel.setBounds(140, 240, 110, 40);
		dltEmpNameLabel.setFont(new Font("Aril", Font.PLAIN, 28));
		dltEmpNameTxtFld = new JTextField("", 20);
		dltEmpNameTxtFld.setBounds(280, 240, 200, 40);

		// 部署入力フィールド
		JLabel dltDeptLabel = new JLabel("部署");
		dltDeptLabel.setFont(new Font("Aril", Font.PLAIN, 28));
		dltDeptLabel.setBounds(140, 320, 110, 40);
		dltDeptTxtFld = new JTextField("", 10);
		dltDeptTxtFld.setBounds(280, 320, 200, 40);

		// enterボタン作成
		JButton enterBtn = new JButton("削除");
		enterBtn.setBounds(450, 420, 70, 50);
		enterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dltEmpIdTxtFld.getText().equals("")
						&& dltEmpNameTxtFld.getText().equals("")
						&& dltDeptTxtFld.getText().equals("")) {
					JOptionPane.showMessageDialog(deletePanel, "入力されていません。");
				} else {
					if (access.dataExists(dltEmpIdTxtFld.getText(),
							dltEmpNameTxtFld.getText(), dltDeptTxtFld.getText())) {
						if (access.deleteDB(dltEmpIdTxtFld.getText(),
								dltEmpNameTxtFld.getText(),
								dltDeptTxtFld.getText())) {
							JOptionPane.showMessageDialog(deletePanel,
									"削除しました。");
						} else {
							JOptionPane.showMessageDialog(deletePanel,
									"削除できませんでした。");
						}
					} else {
						JOptionPane.showMessageDialog(deletePanel, "データがありません。");
					}
				}
			}
		});

		// TOPボタン作成
		JButton dltTopBtn = new JButton("TOP");
		dltTopBtn.addActionListener(this);
		dltTopBtn.setActionCommand("Toppage");
		dltTopBtn.setBounds(530, 45, 70, 40);


		deletePanel.add(deleteLabel);
		deletePanel.add(dltEmpIdLabel);
		deletePanel.add(dltEmpIdTxtFld);
		deletePanel.add(dltEmpNameLabel);
		deletePanel.add(dltEmpNameTxtFld);
		deletePanel.add(dltDeptLabel);
		deletePanel.add(dltDeptTxtFld);
		deletePanel.add(enterBtn);
		deletePanel.add(dltTopBtn);


		// ///////////////* card4　 search & resultPanel */////////////////////

		
		// Search panels
		final JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null);
		
		JLabel searchLabel = new JLabel("社員検索");
		searchLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
		
		// content setting
		JLabel searchIdLabel = new JLabel("ID検索");
		JLabel searchNameLabel = new JLabel("社員名検索");
		JLabel searchDeptLabel = new JLabel("部署名検索");
		
		searchIdLabel.setFont(new Font("Aril", Font.BOLD, 20));
		searchNameLabel.setFont(new Font("Aril", Font.BOLD, 20));
		searchDeptLabel.setFont(new Font("Aril", Font.BOLD, 20));
		
		final JTextField idTextFld = new JTextField();
		final JTextField nameTextFld = new JTextField();
		final JTextField deptTextFld = new JTextField();
		final JButton searchEnterBtn = new JButton("検索");
		
		JButton searchReturnBtn = new JButton("TOP");
		idTextFld.setText("");
		nameTextFld.setText("");
		deptTextFld.setText("");

		// layout setting
		searchLabel.setBounds(50,10,400,110);
		
		searchIdLabel.setBounds(140, 160, 110, 40);
		searchNameLabel.setBounds(140, 240, 110, 40);
		searchDeptLabel.setBounds(140, 320, 110, 40);
		idTextFld.setBounds(280, 160, 200, 40);
		nameTextFld.setBounds(280, 240, 200, 40);
		deptTextFld.setBounds(280, 320, 200, 40);
		searchEnterBtn.setBounds(450, 420, 70, 50);
		searchReturnBtn.setBounds(530, 45, 70, 40);

		searchPanel.add(searchLabel);
		searchPanel.add(searchIdLabel);
		searchPanel.add(idTextFld);
		searchPanel.add(searchNameLabel);
		searchPanel.add(nameTextFld);
		searchPanel.add(searchDeptLabel);
		searchPanel.add(deptTextFld);
		searchPanel.add(searchEnterBtn);
		searchPanel.add(searchReturnBtn);

		// ボタンの設定
		searchReturnBtn.addActionListener(this);
		searchReturnBtn.setActionCommand("Toppage");
		searchEnterBtn.addActionListener(this);
		
		searchEnterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (idTextFld.getText().equals("")
						&& nameTextFld.getText().equals("")
						&& deptTextFld.getText().equals("")
						&& dltDeptTxtFld.getText().equals("")) {
					JOptionPane.showMessageDialog(searchPanel, "入力されていません。");
				}else {
					// id check
					if (!idTextFld.getText().equals("")) {
						if (!access.dataExists(idTextFld.getText(), 
								nameTextFld.getText().toUpperCase(),
								deptTextFld.getText().toUpperCase())) {
							JOptionPane.showMessageDialog(searchPanel,"検索結果がありません");
						}
						layout.show(cardPanel, "Result");
						modelTable = access.idSelectDB(
								Integer.parseInt(idTextFld.getText()),
								nameTextFld.getText().toUpperCase(),
								deptTextFld.getText().toUpperCase());
						
						resultTable.setModel(modelTable);
					} else {
						// no id check
						if (!access.dataExists(idTextFld.getText(), 
								nameTextFld.getText().toUpperCase(),
								deptTextFld.getText().toUpperCase())) {
							JOptionPane.showMessageDialog(searchPanel,"検索結果がありません");
						}
						layout.show(cardPanel, "Result");
						modelTable = access.idSelectDB(nameTextFld.getText().toUpperCase(),
								deptTextFld.getText().toUpperCase());
						resultTable.setModel(modelTable);
					}
				}
			}
		});
		
		
		// resultPanels
		final JPanel resultPanel = new JPanel();
		resultPanel.setLayout(null);
		resultScrollPanel = new JScrollPane(resultTable);
		JLabel setName = new JLabel();
		JButton excelOutBtn = new JButton("Excelで出力");
		excelOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (access.excelOut(idTextFld.getText(), nameTextFld.getText()
						.toUpperCase(), deptTextFld.getText().toUpperCase()))
					JOptionPane.showMessageDialog(resultPanel, "作成しました");
				else
					JOptionPane.showMessageDialog(resultPanel, "作成できませんでした。");
			}
		});
		
		JButton backBtn = new JButton("戻る");
		backBtn.addActionListener(this);
		backBtn.setActionCommand("Search");

		resultScrollPanel.setBounds(20, 40, 390, 400);
		excelOutBtn.setBounds(460, 100, 120, 40);
		backBtn.setBounds(480, 300, 60, 40);

		resultPanel.add(setName);
		resultPanel.add(excelOutBtn);
		resultPanel.add(resultScrollPanel);
		resultPanel.add(backBtn);

		
		// ////////// cardlayout setting ///////////////////////////////
		
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