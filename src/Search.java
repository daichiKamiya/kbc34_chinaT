import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//comment

public class Search extends JFrame implements ActionListener{
	
	JPanel changePanels;
	CardLayout cardLayout;
	String responsArray;
	JScrollPane resultScrollPanel;
	
	JTable resultTable = new JTable();
	DefaultTableModel modelTable = new DefaultTableModel();

	
	public static void main(String[] args){
		Search frame = new Search("Ðõõ");
		frame.setVisible(true);
	}
	
	public Search(String title){
		setTitle(title);
		setBounds(100,100,600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		final AccessDB access = new AccessDB();
		
		Container contentPanel = getContentPane();
		
		
		//Search panels
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null); // CAEgè®
		
		JLabel searchTitle = new JLabel("Ðõõ");
		searchTitle.setFont(new Font("lr ¾©", Font.BOLD, 26));
		
		
		JLabel id = new JLabel("IDõ");
		JLabel name = new JLabel("Ðõ¼õ");
		JLabel dept = new JLabel("¼õ");
		final JTextField idText = new JTextField();
		final JTextField nameText = new JTextField();
		final JTextField deptText = new JTextField();
		JButton idSearchBtn = new JButton("õ");
		JButton nameSearchBtn = new JButton("õ");
		JButton deptSearchBtn = new JButton("õ");
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
		
		
		//{^ÌÝè
		idSearchBtn.addActionListener(this);
	    idSearchBtn.setActionCommand("searchPanel");
	    nameSearchBtn.addActionListener(this);
	    nameSearchBtn.setActionCommand("searchPanel");
	    deptSearchBtn.addActionListener(this);
	    deptSearchBtn.setActionCommand("searchPanel");
	    
	    idSearchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

//            	responsList = access.idSelectDB(Integer.parseInt(idText.getText()));
//            	resultList.setModel(responsList);
			}
	    });
	    
	    nameSearchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
//            	responsList = access.nameSelectDB(nameText.getText().toUpperCase());
//            	resultList.setModel(responsList);
            }
	    });
	    
	    deptSearchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
//            	modelTable = access.deptSelectDB2(deptText.getText());
//            	resultTable.setModel(modelTable);
            }
	    });

		//resultPanels
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(null);
		resultScrollPanel = new JScrollPane(resultTable);
		
		JLabel setName = new JLabel();
		JButton excelOutBtn = new JButton("ExcelÅoÍ");
		JButton backBtn = new JButton("ßé");
		
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