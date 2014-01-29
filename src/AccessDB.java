import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AccessDB {

	Connection connection = null;
    Statement statement = null; 
    ResultSet result = null;
    
	AccessDB(){   
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:scott/tiger@localhost/myorcl");
            statement = connection.createStatement();
            
            connection.setAutoCommit(false);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
		catch (SQLException e){
            e.printStackTrace();
        }
    }
	
	DefaultListModel idSelectDB(int id){
		DefaultListModel defaultList = new DefaultListModel();
		String resultData = new String();
		try {
			//resultは書き直しが必要
			result = statement.executeQuery("SELECT emp_id,emp_name,dept FROM temp_emp where emp_id = "+ id +" and DEL_FLG = 0");
			 while(result.next()) {
	               System.out.print(result.getInt(1));
	               resultData = result.getString(1)+ result.getString(2)+ result.getString(3);
	               defaultList.addElement(resultData);
	           }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return defaultList;
	}

	DefaultListModel nameSelectDB(String name){
		DefaultListModel defaultList = new DefaultListModel();		
		String resultData = new String();
		try {
			//resultは書き直しが必要
			result = statement.executeQuery("SELECT emp_id,emp_name,dept FROM temp_emp where emp_name = '"+ name +"' and DEL_FLG = 0");
			while(result.next()) {
	               resultData = result.getString(1) + result.getString(2) + result.getString(3);
	               defaultList.addElement(resultData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return defaultList;
	}
	
	/*	
	String nameSelectDB(String name){
		String resultData = new String();
		try {
			//resultは書き直しが必要
			result = statement.executeQuery("SELECT * FROM emp where ename = '"+ name +"'");
			while(result.next()) {
	               System.out.print(result.getInt(1) + "\t");
	               System.out.print(result.getString(2) +"\t");
	               System.out.println(result.getString(3) +"\t");
	               resultData = result.getString(1) + result.getString(2) + result.getString(2);
			 }
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultData;
	}
*/
	
	
  // 部署名をリストで戻す。	
	DefaultListModel deptSelectDB(String dept){
		DefaultListModel defaultList = new DefaultListModel();		
		String resultData = new String();
		try {
			//resultは書き直しが必要
			result = statement.executeQuery("SELECT * FROM temp_emp where dept = '"+ dept+"' and DEL_FLG = 0");
			 while(result.next()) {
//	               System.out.print(result.getInt(1));System.out.println(result.getString(2));System.out.println(result.getString(3));
	               resultData = result.getString(1) + result.getString(2) + result.getString(3);
	               defaultList.addElement(resultData);
	           }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return defaultList;
	}
	
	
	
	
	DefaultTableModel deptSelectDB2(String dept){		
		String resultData = new String();
		String[] clamNames = {"社員番号","名前","部署"};
		String[] sqlResult = new String[3];
		DefaultTableModel returnTable = new DefaultTableModel(clamNames,0);
		
		try {
			//resultは書き直しが必要
			result = statement.executeQuery("SELECT * FROM temp_emp where dept = '"+ dept+"' and DEL_FLG = 0");
			
			while(result.next()) {				
//	             System.out.print(result.getInt(1));System.out.println(result.getString(2));System.out.println(result.getString(3));
//	             resultData = result.getString(1) + result.getString(2) + result.getString(3);
	             for(int i=0; i<3;i++){
	            	 sqlResult[i] = result.getString(i+1);
	             }
	             
	             returnTable.addRow(sqlResult);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnTable;
	}
	
	
	
	void insertDB(int id,String name,String dept){
		try {
			//  boolean 型を返すようにする。 ture なら成功　false　なら失敗；
			result = statement.executeQuery(
					"INSERT INTO temp_emp (EMP_ID,EMP_NAME,DEPT,DEL_FLG,ACC_FLG) "
					+ "VALUES ("+ id +",'"+ name +"','"+ dept +"',0,0)");
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	void updateDB(int id){
		int updateSql = 0;
		try {
//			System.out.println("UPDATE TEMP_EMP set DEL_FLG = 1 where EMP_ID = "+ id);
			updateSql = statement.executeUpdate("UPDATE TEMP_EMP set DEL_FLG = 1 where EMP_ID = "+ id);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	void finishDB(){
		try {
			result.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}