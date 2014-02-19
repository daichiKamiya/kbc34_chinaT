import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class AccessDB {

	Connection connection = null;
    Statement statement = null; 
    ResultSet result = null;
    
    // データベースコネクションの確立
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
	
	// ログインメソッド
	boolean loginCheck(String id,String pass){
		try {
			int idNum = Integer.parseInt(id);
			pass = sanitizing(pass); //文字列のサニタイジング
			result = statement.executeQuery("SELECT ACC_FLG FROM empmanager where emp_id = "+ idNum +" and pass = '"+ pass +"'");	
			result.next();
			if(result.getInt(1) == 0)
				return true;
		} catch (SQLException e) {
			return false; //SQL文が不当な場合
		}catch (NumberFormatException e) {
			return false; //idが数字以外の入力だった場合
		}
		return false;
	}
	
	//データの存在確認メソッド
	boolean dataExists(String id,String name,String dept){
		
		name = sanitizing(name);
		dept = sanitizing(dept);
		
		try {
			if(id.equals("")){
			result = statement.executeQuery(
					"SELECT count(*) FROM empmanager "
					+"where emp_name like '%"+ name 
					+"%' and dept_name like '%"+ dept
					+"%' and DEL_FLG = 0");
			}else{
				int idNum = Integer.parseInt(id);
				result = statement.executeQuery(
						"SELECT count(*) FROM empmanager "
						+"where emp_id = "+ idNum
						+"and emp_name like '%"+ name
						+"%' and dept_name like '%"+ dept
						+"%' and DEL_FLG = 0");
			}
		
			result.next();
			if(result.getInt(1) == 0){
				return false;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
		
	}
	
	
	// idを含んだ社員検索メソッド
	DefaultTableModel idSelectDB(int id,String name,String dept){
		String[] clamNames = {"社員番号","名前","部署","役職"};
		String[] sqlResult = new String[4];
		DefaultTableModel returnTable = new DefaultTableModel(clamNames,0);
		try {
			
			name = sanitizing(name);
			dept = sanitizing(dept);
			
			result = statement.executeQuery(
					"SELECT * FROM empmanager "
					+"where emp_id = "+ id
					+"and emp_name like '%"+ name
					+"%' and dept_name like '%"+ dept
					+"%' and DEL_FLG = 0");
			
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
	
	
	
	// idを含まない社員検索メソッド
	DefaultTableModel idSelectDB(String name,String dept){
		String[] clamNames = {"社員番号","名前","部署","役職"};
		String[] sqlResult = new String[4];
		DefaultTableModel returnTable = new DefaultTableModel(clamNames,0);
		
		name = sanitizing(name);
		dept = sanitizing(dept);
		
		try {	
			result = statement.executeQuery(
					"SELECT * FROM empmanager "
					+"where emp_name like '%"+ name 
					+"%' and dept_name like '%"+ dept
					+"%' and DEL_FLG = 0");
			
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
	
	
	
	// 社員追加メソッド
	boolean insertDB(String id,String name,String dept,String post){
		try {
			int idNum = Integer.parseInt(id);
			
			if(!injectionCheck(name) || !injectionCheck(dept) || !injectionCheck(post))
				return false;
			
			result = statement.executeQuery(
					"INSERT INTO empmanager (EMP_ID,EMP_NAME,DEPT_NAME,POST,DEL_FLG,ACC_FLG) "
					+ "VALUES ("+ idNum +",'"+ name +"','"+ dept +"','"+ post +"',0,0)");
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	// 論理削除メソッド
	boolean deleteDB(String id,String name,String dept){
		try {
			name = sanitizing(name);
			dept = sanitizing(dept); 
			
			if(!id.equals("")){
				// idがある場合のupdate
				int idNum = Integer.parseInt(id);
				statement.executeUpdate("UPDATE EMPMANAGER set DEL_FLG = 1 where EMP_ID = "+ idNum +" and EMP_NAME like '%"+ name +"%' and DEPT_NAME like '%"+ name +"%'");
			}else{
				// idがない場合のupdate
				statement.executeUpdate("UPDATE EMPMANAGER set DEL_FLG = 1 where EMP_NAME like '%"+ name +"%' and DEPT_NAME like '%"+ name +"%'");
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // SQL文が正当でない場合
		} catch (NumberFormatException e){
			return false; //　数字以外が入力されていた場合
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
	
	
	// excelで出力メソッド
	boolean excelOut(String id,String name,String dept) {
		HSSFWorkbook pushExcelData = new HSSFWorkbook();
		
		Sheet empSheet = pushExcelData.createSheet("出力データ");
		
		try {
			if(!id.equals("")){
				int idNum = Integer.parseInt(id); 	
				result = statement.executeQuery(
					"SELECT * FROM empmanager "
					+"where emp_id = "+ idNum
					+" and emp_name like '%"+ name
					+"%' and dept_name like '%"+ dept
					+"%' and DEL_FLG = 0");
			}else{
				result = statement.executeQuery(
						"SELECT * FROM empmanager "
						+"where emp_name like '%"+ name
						+"%' and dept_name like '%"+ dept
						+"%' and DEL_FLG = 0");
			}
			
			Row topRow = empSheet.createRow(0);
			
			Cell titleCell = topRow.createCell(0);
			titleCell.setCellValue("出力データ");
			
			Row rowNameRow =empSheet.createRow(2); //列名列
			
			// Cellのスタイル
			CellStyle rowNameStyle = pushExcelData.createCellStyle();
			rowNameStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			rowNameStyle.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
			rowNameStyle.setBorderTop(CellStyle.BORDER_THIN);
			rowNameStyle.setBorderBottom(CellStyle.BORDER_DOUBLE);
			rowNameStyle.setBorderLeft(CellStyle.BORDER_THIN);
			rowNameStyle.setBorderRight(CellStyle.BORDER_THIN);
			rowNameStyle.setAlignment(CellStyle.ALIGN_CENTER);
			
			CellStyle standardStyle = pushExcelData.createCellStyle();
			standardStyle.setBorderTop(CellStyle.BORDER_THIN);
			standardStyle.setBorderBottom(CellStyle.BORDER_THIN);
			standardStyle.setBorderLeft(CellStyle.BORDER_THIN);
			standardStyle.setBorderRight(CellStyle.BORDER_THIN);
			
			// 列名列の設定
			Cell rowNameEmpIdCell = rowNameRow.createCell(0);
			Cell rowNameEmpNameCell = rowNameRow.createCell(1);
			Cell rowNameDeptCell = rowNameRow.createCell(2);
			Cell rowNamePostCell = rowNameRow.createCell(3);
			rowNameEmpIdCell.setCellValue("社員ID");
			rowNameEmpNameCell.setCellValue("社員名");
			rowNameDeptCell.setCellValue("部署名");
			rowNamePostCell.setCellValue("役職");
			rowNameEmpIdCell.setCellStyle(rowNameStyle);
			rowNameEmpNameCell.setCellStyle(rowNameStyle);
			rowNameDeptCell.setCellStyle(rowNameStyle);
			rowNamePostCell.setCellStyle(rowNameStyle);
			
			
			int rowNum = 2; //行ナンバー
			while(result.next()){
				rowNum++;
				Row row = empSheet.createRow(rowNum);

				Cell empIdCell = row.createCell(0);
				Cell empNameCell = row.createCell(1);
				Cell deptCell = row.createCell(2);
				Cell postCell = row.createCell(3);
				
				empIdCell.setCellValue(result.getInt(1));
				empIdCell.setCellStyle(standardStyle);
				empNameCell.setCellValue(result.getString(2));
				empNameCell.setCellStyle(standardStyle);
				deptCell.setCellValue(result.getString(3));
				deptCell.setCellStyle(standardStyle);
				postCell.setCellValue(result.getString(4));
				postCell.setCellStyle(standardStyle);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		
		FileOutputStream out = null;
		try {

			out = new FileOutputStream("C:/Users/user/Desktop/社員検索結果.xls");
			pushExcelData.write(out);
		
		} catch (FileNotFoundException e1){
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				System.out.println(e.toString());
				return false;
			}
			System.out.println("created");
		}
		return true;
	}

	// サニタイジングメソッド
	String  sanitizing(String str){
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("'", "&#39;");
		return str;
	}
	
	// 入力文字のチェックメソッド
	boolean injectionCheck(String str){
		
		String checkStr = sanitizing(str);
		if(checkStr.equals(str))
			return true;
		
		return false;
	}
}