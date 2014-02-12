import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class AccessDB {

	Connection connection = null;
    Statement statement = null; 
    ResultSet result = null;
    
	AccessDB(){   
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Class.forName("oracle.jdbc.OracleDriver");
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
	
	// loginCheck
	boolean loginCheck(String id,String pass){
		try {
			int idNum = Integer.parseInt(id);
//			int passNum = Integer.parseInt(pass);
			result = statement.executeQuery("SELECT ACC_FLG FROM empmanager where emp_id = "+ idNum +" and pass = '"+ pass +"'");	
			result.next();
			if(result.getInt(1) == 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
	DefaultTableModel deptSelectDB(String dept){
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
	
	boolean insertDB(int id,String name,String dept){
		try {
			//  boolean å^Çï‘Ç∑ÇÊÇ§Ç…Ç∑ÇÈÅB ture Ç»ÇÁê¨å˜Å@falseÅ@Ç»ÇÁé∏îsÅG
			result = statement.executeQuery(
					"INSERT INTO empmanager (EMP_ID,EMP_NAME,DEPT_NAME,DEL_FLG,ACC_FLG) "
					+ "VALUES ("+ id +",'"+ name +"','"+ dept +"',0,0)");
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	boolean updateDB(int id){
		int updateSql = 0;
		try {
			System.out.println("UPDATE TEMP_EMP set DEL_FLG = 1 where EMP_ID = "+ id);
			updateSql = statement.executeUpdate("UPDATE EMPMANAGER set DEL_FLG = 1 where EMP_ID = "+ id);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
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
