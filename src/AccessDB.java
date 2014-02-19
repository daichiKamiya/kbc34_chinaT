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
    
    // �f�[�^�x�[�X�R�l�N�V�����̊m��
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
	
	// ���O�C�����\�b�h
	boolean loginCheck(String id,String pass){
		try {
			int idNum = Integer.parseInt(id);
			pass = sanitizing(pass); //������̃T�j�^�C�W���O
			result = statement.executeQuery("SELECT ACC_FLG FROM empmanager where emp_id = "+ idNum +" and pass = '"+ pass +"'");	
			result.next();
			if(result.getInt(1) == 0)
				return true;
		} catch (SQLException e) {
			return false; //SQL�����s���ȏꍇ
		}catch (NumberFormatException e) {
			return false; //id�������ȊO�̓��͂������ꍇ
		}
		return false;
	}
	
	//�f�[�^�̑��݊m�F���\�b�h
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
	
	
	// id���܂񂾎Ј��������\�b�h
	DefaultTableModel idSelectDB(int id,String name,String dept){
		String[] clamNames = {"�Ј��ԍ�","���O","����","��E"};
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
	
	
	
	// id���܂܂Ȃ��Ј��������\�b�h
	DefaultTableModel idSelectDB(String name,String dept){
		String[] clamNames = {"�Ј��ԍ�","���O","����","��E"};
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
	
	
	
	// �Ј��ǉ����\�b�h
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
	
	// �_���폜���\�b�h
	boolean deleteDB(String id,String name,String dept){
		try {
			name = sanitizing(name);
			dept = sanitizing(dept); 
			
			if(!id.equals("")){
				// id������ꍇ��update
				int idNum = Integer.parseInt(id);
				statement.executeUpdate("UPDATE EMPMANAGER set DEL_FLG = 1 where EMP_ID = "+ idNum +" and EMP_NAME like '%"+ name +"%' and DEPT_NAME like '%"+ name +"%'");
			}else{
				// id���Ȃ��ꍇ��update
				statement.executeUpdate("UPDATE EMPMANAGER set DEL_FLG = 1 where EMP_NAME like '%"+ name +"%' and DEPT_NAME like '%"+ name +"%'");
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // SQL���������łȂ��ꍇ
		} catch (NumberFormatException e){
			return false; //�@�����ȊO�����͂���Ă����ꍇ
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
	
	
	// excel�ŏo�̓��\�b�h
	boolean excelOut(String id,String name,String dept) {
		HSSFWorkbook pushExcelData = new HSSFWorkbook();
		
		Sheet empSheet = pushExcelData.createSheet("�o�̓f�[�^");
		
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
			titleCell.setCellValue("�o�̓f�[�^");
			
			Row rowNameRow =empSheet.createRow(2); //�񖼗�
			
			// Cell�̃X�^�C��
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
			
			// �񖼗�̐ݒ�
			Cell rowNameEmpIdCell = rowNameRow.createCell(0);
			Cell rowNameEmpNameCell = rowNameRow.createCell(1);
			Cell rowNameDeptCell = rowNameRow.createCell(2);
			Cell rowNamePostCell = rowNameRow.createCell(3);
			rowNameEmpIdCell.setCellValue("�Ј�ID");
			rowNameEmpNameCell.setCellValue("�Ј���");
			rowNameDeptCell.setCellValue("������");
			rowNamePostCell.setCellValue("��E");
			rowNameEmpIdCell.setCellStyle(rowNameStyle);
			rowNameEmpNameCell.setCellStyle(rowNameStyle);
			rowNameDeptCell.setCellStyle(rowNameStyle);
			rowNamePostCell.setCellStyle(rowNameStyle);
			
			
			int rowNum = 2; //�s�i���o�[
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

			out = new FileOutputStream("C:/Users/user/Desktop/�Ј���������.xls");
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

	// �T�j�^�C�W���O���\�b�h
	String  sanitizing(String str){
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("'", "&#39;");
		return str;
	}
	
	// ���͕����̃`�F�b�N���\�b�h
	boolean injectionCheck(String str){
		
		String checkStr = sanitizing(str);
		if(checkStr.equals(str))
			return true;
		
		return false;
	}
}