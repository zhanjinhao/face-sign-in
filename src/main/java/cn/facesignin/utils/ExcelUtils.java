package cn.facesignin.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.facesignin.constant.ImgFilePathConfig;
import cn.facesignin.pojo.SigninRecord;

public class ExcelUtils {
	
	/**
	 * 
	 * @param list
	 * @param fileName
	 * @return
	 */
	public String createExcel(List<SigninRecord> list, Integer aid) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Workbook wb = new XSSFWorkbook();
	    
	    //标题行抽出字段
	    String[] title = {"序号", "学号", "到场时间", "离场时间", "验证方式", "签到状态"};
	    int cols = title.length;
	    
	    //设置sheet名称，并创建新的sheet对象
	    Sheet stuSheet = wb.createSheet(String.valueOf(aid));
	    //获取表头行
	    Row titleRow = stuSheet.createRow(0);
	    //创建单元格，设置style居中，字体，单元格大小等
	    CellStyle style = wb.createCellStyle();
	    Cell cell = null;
	    //把已经写好的标题行写入excel文件中
	    for (int i = 0; i < cols; i++) {
	        cell = titleRow.createCell(i);
	        cell.setCellValue(title[i]);
	        cell.setCellStyle(style);
	    }
	    //把从数据库中取得的数据一一写入excel文件中
	    Row row = null;
	    
	    Iterator<SigninRecord> iterator = list.iterator();
	    
	    for(int i = 0; iterator.hasNext(); i++) {
	    	
	    	SigninRecord signinRecord = iterator.next();
	    	row = stuSheet.createRow(i + 1);
	    	
	    	//第一列设置自动序号
	    	row.createCell(0).setCellValue(i + 1);
	    	
		    row.createCell(1).setCellValue(signinRecord.getUid());
		    row.createCell(2).setCellValue(sdf.format(signinRecord.getSinTime()));
		    row.createCell(3).setCellValue(sdf.format(signinRecord.getSoutTime()));
		    String scheckType = signinRecord.getScheckType();
		    String sstatus = signinRecord.getSstatus();
		    
		    if(cn.facesignin.constant.Type.SIGNIN_RECORD_SCHECK_TYPE_IMG.equals(scheckType))
		    	row.createCell(4).setCellValue("人脸录入");
		    else
		    	row.createCell(4).setCellValue("手动录入");
		    
		    if(cn.facesignin.constant.Type.SIGNIN_RECORD_SSTATUS_DELETE.equals(sstatus))
		    	row.createCell(5).setCellValue("删除");
		    else if(cn.facesignin.constant.Type.SIGNIN_RECORD_SSTATUS_NORMAL.equals(sstatus))
		    	row.createCell(5).setCellValue("签到成功");
		    else
		    	row.createCell(5).setCellValue("迟到");
	    }
	    
	    //设置单元格宽度自适应，在此基础上把宽度调至2倍
	    for (int i = 0; i < cols; i++) {
	        stuSheet.autoSizeColumn(i, true);
	        stuSheet.setColumnWidth(i, stuSheet.getColumnWidth(i) * 2);
	    }
	    
	    String filePath = ImgFilePathConfig.ROOT + File.separator + ImgFilePathConfig.VERIFY
	    		+ File.separator + aid + File.separator + aid + ".xlsx";

	    System.out.println("excelPath  ==>  " + filePath);

	    try(OutputStream fileOut = new FileOutputStream(filePath)){
	    	
	    	FileUtils.forceCreateFile(filePath);
	    	wb.write(fileOut);
	    	
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
	    
	    //返回文件保存全路径
	    return filePath;
	}
}
