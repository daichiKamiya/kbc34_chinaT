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
	
	
	// id Search
	DefaultTableModel idSelectDB(int id){
		String[] clamNames = {"é–àıî‘çÜ","ñºëO","ïîèê","ñêE"};
		String[] sqlResult = new String[4];
		DefaultTableModel returnTable = new DefaultTableModel(clamNames,0);
		try {
			result = statement.executeQuery("SELECT * FROM empmanager where emp_id = "+ id +" and DEL_FLG = 0");
			while(result.next()) {
	           for(int i=0; i<4;i++){
	            	 sqlResult[i] = result.getString(i+1);
	            }	             
	             returnTable.addRow(sqlResult);    
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnTable;
	}
	
	
	// name Search
	DefaultTableModel nameSelectDB(String name){
		String[] clamNames = {"é–àıî‘çÜ","ñºëO","ïîèê","ñêE"};
		String[] sqlResult = new String[4];
		DefaultTableModel returnTable = new DefaultTableModel(clamNames,0);
		try {
			result = statement.executeQuery("SELECT * FROM empmanager where emp_name = '"+ name +"' and DEL_FLG = 0");
			while(result.next()) {
				for(int i=0; i<4;i++){
					sqlResult[i] = result.getString(i+1);
				}            
            returnTable.addRow(sqlResult);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnTable;
	}
	
	// dept Search
	DefaultTableModel deptSelectDB2(String dept){		
		String resultData = new String();
		String[] clamNames = {"é–àıî‘çÜ","ñºëO","ïîèê","ñêE"};
		String[] sqlResult = new String[4];
		DefaultTableModel returnTable = new DefaultTableModel(clamNames,0);
		
		try {
			result = statement.executeQuery("SELECT emp_id,emp_name,dept_name,post FROM empmanager where post = '"+ dept +"' and DEL_FLG = 0");
			while(result.next()) {				
				for(int i=0; i<4;i++){
	            	 sqlResult[i] = result.getString(i+1);
	            }
	             
	             returnTable.addRow(sqlResult);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnTable;
	}
	
	
	
	/*void insertDB(int id,String name,String dept){
		try {
			//  boolean å^Çï‘Ç∑ÇÊÇ§Ç…Ç∑ÇÈÅB ture Ç»ÇÁê¨å˜Å@falseÅ@Ç»ÇÁé∏îsÅG
			result = statement.executeQuery(
					"INSERT INTO temp_emp (EMP_ID,EMP_NAME,DEPT,DEL_FLG,ACC_FLG) "
					+ "VALUES ("+ id +",'"+ name +"','"+ dept +"',0,0)");
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}*/
	boolean insertDB(int id,String name,String dept){
		try {
			//  boolean å^Çï‘Ç∑ÇÊÇ§Ç…Ç∑ÇÈÅB ture Ç»ÇÁê¨å˜Å@falseÅ@Ç»ÇÁé∏îsÅG
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
			updateSql = statement.executeUpdate("UPDATE EMPMANAGER set DEL_FLG = 1 where EMP_ID = "+ id);
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
