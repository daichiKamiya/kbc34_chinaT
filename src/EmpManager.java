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
    frame.setTitle("�Ǘ��V�X�e��");
    frame.setVisible(true);
  }

  EmpManager(){
	  ////////////�J�[�h0/////////////////////////////
	  
	 final JPanel card0 = new JPanel();
	    card0.setLayout(null);
	    card0.add(new JButton("Login"));
            // ��ʂ̍쐬
		    setTitle(title);
		   setBounds(100, 100, 200, 250);
		   JLabel Title1 = new JLabel("���O�C�����");
		  Title1.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
			  Title1.setBounds(200,10,200,30);
		    //���x���̍쐬
		   JLabel ID = new JLabel("ID:");
		    ID.setBounds(120,104,50,20);
		    ID.setFont(new Font("MS�@�S�V�b�N", Font.BOLD, 20));
		     empIdTxtFld = new JTextField("", 20);
		     empIdTxtFld.setBounds(150,100,200,30);
		    JLabel PW = new JLabel("PW:");
		    PW.setBounds(105,204,50,20);
		    PW.setFont(new Font("MS�@�S�V�b�N", Font.BOLD, 20));
		    psssTxtFld = new JTextField("", 20);
		    psssTxtFld.setBounds(150,200,200,30);
		    
		    //���O�C������
		    final JButton loginBtn = new JButton("���O�C��");
		    
		    loginBtn.addActionListener( new ActionListener(){
		    	
				@Override
				public void actionPerformed(ActionEvent e) {
					 //TODO �����������ꂽ���\�b�h�E�X�^�u
			    	
					String str1 = empIdTxtFld.getText() + psssTxtFld.getText();
					        if (str1.equals("roothimitu"))
					            cardPanel.add(card0,"Login");

					        	 
				}
		    });



		    //�{�^���̍쐬
		    loginBtn.setBounds(400,350,100,30);
		    loginBtn.addActionListener(this);
		    
		    titleLabel = new JLabel();

		    card0.add(Title1);
		    card0.add(empIdTxtFld);
		    card0.add(psssTxtFld);
		    card0.add(ID);
		    card0.add(PW);
		    card0.add(loginBtn);


   //////////////////// /* �J�[�h1�@�i�g�b�v�y�[�W�j *///////////////////////////
			   final JPanel card1 = new JPanel();
			    card1.add(new JButton("Button"));
		        //�p�l�����쐬
		  		card1.setLayout(null);
		  		card1.setSize(600, 500);
		  		
		  		//���x���쐬
		  		JLabel ToppageLabel = new JLabel("�g�b�v�y�[�W");
		  		card1.add(ToppageLabel);
		  		ToppageLabel.setFont(new Font("Aril", Font.PLAIN, 50));
		  		//�{�^���쐬
		  		JButton addBtn = new JButton("�ǉ�");
		  		addBtn.addActionListener(this);
		  	    addBtn.setActionCommand("Insert");
		  	    
		  		JButton searchBtn = new JButton("����");
		  		searchBtn.addActionListener(this);
		  		searchBtn.setActionCommand("Search");
		  	    
		  		JButton deleteBtn = new JButton("�폜");
		  		deleteBtn.addActionListener(this);
		  		deleteBtn.setActionCommand("Delete");
		  		
		  		JButton logoutBtn = new JButton("���O�A�E�g");
		  		logoutBtn.addActionListener(this);
		  		logoutBtn.setActionCommand("Login");
		  		
		  		//�{�^���T�C�Y�Ɣz�u�ƃ��x��
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

    ////////////////////* �J�[�h2 (�ǉ��y�[�W�j */////////////////////
		  		JPanel card2 = new JPanel();
		  	    card2.setLayout(null);
		  	    card2.add(new JButton("button"));
		  		//�@Label�ɕ����������
		  		JLabel insInsertLabel = new JLabel("�Ј��ǉ�");
		  		JLabel insEmpIdLabel  = new JLabel("ID");
		  		JLabel insNameLabel   = new JLabel("���O");
		  		JLabel insDeptLabel   = new JLabel("����");
		  		JLabel insPostLabel   = new JLabel("��E");
		  		
		  		// �t�H���g�̑傫���w��
		  		insInsertLabel.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 50));
		  		insEmpIdLabel. setFont(new Font("Aril", Font.PLAIN, 28));
		  		insNameLabel.  setFont(new Font("Aril", Font.PLAIN, 28));
		  		insDeptLabel.  setFont(new Font("Aril", Font.PLAIN, 28));
		  		insPostLabel.  setFont(new Font("Aril", Font.PLAIN, 28));
		  		
		  		// Label�ɑΉ�����TextFild�̍쐬
		  		JTextField insEmpIdTxtFld = new JTextField();
		  		JTextField insNameTxtFld = new JTextField();
		  		JTextField insDeptTxtFld = new JTextField();
		  		JTextField insPostTxtFld = new JTextField();
		  		
		  		//Button�̍쐬
		  		JButton insTopBtn = new JButton("TOP");
		  		insTopBtn.addActionListener(this);
		  		insTopBtn.setActionCommand("Toppage");

		  		JButton insertBtn = new JButton("�ǉ�");
		  		insertBtn.addActionListener(this);
		  		insertBtn.setActionCommand("Inser");
		  		
		  		//Label,TxtFld,Button��Panel�ɑ}��
		  		card2.add(insInsertLabel);card2.add(insTopBtn);
		  		card2.add(insEmpIdLabel); card2.add(insEmpIdTxtFld);
		  		card2.add(insNameLabel); card2.add(insNameTxtFld);
		  		card2.add(insDeptLabel); card2.add(insDeptTxtFld);
		  		card2.add(insPostLabel); card2.add(insPostTxtFld);
		  		card2.add(insertBtn);
		  		
		  		//Label,TxtFld,Button�̈ʒu�����W�Ŏw��
		  		insInsertLabel.setBounds(50,10,300,50); insTopBtn.setBounds(380,20,100,50);
		  		insEmpIdLabel.setBounds(170,125,70,30); insEmpIdTxtFld.setBounds(250,125,150,30);
		  		insNameLabel. setBounds(150,200,70,30); insNameTxtFld.setBounds(250,200,150,30);
		  		insDeptLabel. setBounds(150,275,70,30); insDeptTxtFld.setBounds(250,275,150,30);
		  		insPostLabel. setBounds(150,350,70,30); insPostTxtFld.setBounds(250,350,150,30);
		  		insertBtn.setBounds(230,410,100,30);
		  		
		  		
		  		//Container contentPanenu = getContentPane();

   /////////////////* �J�[�h3�@�@�@�i�폜�y�[�W�j */////////////////////
		  		JPanel card3 = new JPanel();
		  	    card3.setLayout(null);
		  	    
		  	    //�Ј��ԍ����̓t�B�[���h
		  	    JLabel dltEmpIdLabel = new JLabel("�Ј�ID");
		  	    dltEmpIdLabel.setFont(new Font("Aril", Font.PLAIN, 28));
		  	    dltEmpIdLabel.setBounds(150, 100, 100, 30);
		  	    dltEmpIdTxtFld = new JTextField("", 20);
		  	    dltEmpIdTxtFld.setBounds(250, 100, 150, 30);
		  	    
		  	    //���O���̓t�B�[���h
		  	    JLabel dltEmpNameLabel = new JLabel("�Ј���");
		  	    dltEmpNameLabel.setBounds(150, 200, 100, 30);
		  	    dltEmpNameLabel.setFont(new Font("Aril", Font.PLAIN, 28));
		  	    dltEmpNameTxtFld = new JTextField("", 20);
		  	    dltEmpNameTxtFld.setBounds(250, 200, 150, 30);
		  	    
		  	    //�������̓t�B�[���h
		  	    JLabel dltDeptLabel = new JLabel("����");
		  	    dltDeptLabel.setFont(new Font("Aril", Font.PLAIN, 28));
		  	    dltDeptLabel.setBounds(150, 300, 100, 30);
		  	    dltDeptTxtFld = new JTextField("", 10);
		  	    dltDeptTxtFld.setBounds(250, 300, 150, 30);
		  	    
		  	    //enter�{�^���쐬
		  	    JButton enterBtn = new JButton("Enter");
		  	    enterBtn.setBounds(450, 300, 100, 30);
		  	    enterBtn.addActionListener(this);
		  	    enterBtn.setActionCommand("Del");
		  	    
		  	    //TOP�{�^���쐬
		  	    JButton dltTopBtn = new JButton("TOP");
		  	    dltTopBtn.addActionListener(this);
		  	    dltTopBtn.setActionCommand("Toppage");
		  	    dltTopBtn.setBounds(380, 20, 100, 50);
		  	    
		  	    Label = new JLabel();

		  	    //�R���e���c�ǉ�
		  	    JLabel deleteLabel = new JLabel("�폜���");
		  		  deleteLabel.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 50));
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

		  	    
    
    /////////////////* �J�[�h4�@�@�@�i�����y�[�W�j */////////////////////
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
	      JOptionPane.showMessageDialog(this, "�폜����", "�폜���b�Z�[�W",
	        JOptionPane.PLAIN_MESSAGE);
	    }else if(daiya.equals("Inser")){
	    	  JOptionPane.showMessageDialog(this, "�Ј�����l�ǉ����܂����B", "�ǉ����b�Z�[�W",
	    		        JOptionPane.PLAIN_MESSAGE);
	      
	    
	    }

	  }
	}