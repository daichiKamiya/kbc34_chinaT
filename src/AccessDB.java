import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

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
	
	// loginCheck
	boolean loginCheck(String id,String pass){
		try {
			if(id == null){
				System.out.println("null");
			}
			int idNum = Integer.parseInt(id);
//			int passNum = Integer.parseInt(pass);
			result = statement.executeQuery("SELECT ACC_FLG FROM empmanager where emp_id = "+ idNum +" and pass = '"+ pass +"'");	
			result.next();
			if(result.getInt(1) == 0)
				return true;
		} catch (SQLException e) {
//			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	// id Search
	DefaultTableModel idSelectDB(int id){
		String[] clamNames = {"社員番号","名前","部署","役職"};
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
		String[] clamNames = {"社員番号","名前","部署","役職"};
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
		String[] clamNames = {"社員番号","名前","部署","役職"};
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
			//  boolean 型を返すようにする。 ture なら成功　false　なら失敗；
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
	
	void excelOut() {
		HSSFWorkbook pushExcelData = new HSSFWorkbook();
		Sheet empSheet = pushExcelData.createSheet("出力データ");

		Row topRow = empSheet.createRow(0);
		Cell titleCell = topRow.createCell(0);
		titleCell.setCellValue("出力データ");

		try {
			result = statement.executeQuery(
					"SELECT emp_id,emp_name,dept_name,post "
					+ "FROM empmanager "
					+ "where DEL_FLG = 0");
			int i = 2;
			while(result.next()){
				++i;
				
				Row row = empSheet.createRow(i);
				Cell empIdCell = row.createCell(0);
				Cell empNameCell = row.createCell(1);
				Cell deptCell = row.createCell(2);
				Cell postCell = row.createCell(3);
				
				empIdCell.setCellValue(result.getInt(1));
				empNameCell.setCellValue(result.getString(2));
				deptCell.setCellValue(result.getString(3));
				postCell.setCellValue(result.getString(4));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		FileOutputStream out = null;
		try {

			out = new FileOutputStream("C:/Users/user/Desktop/データベース出力.xls");
			pushExcelData.write(out);

		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
			System.out.println("created");
		}

	}
}
