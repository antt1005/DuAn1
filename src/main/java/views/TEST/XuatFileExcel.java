/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.TEST;

import domainModels.ChiTietDoGo;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.persistence.Query;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Admin
 */
public class XuatFileExcel {

    public void d() {

        Session session = HibernatUtil.getFACTORY().openSession();
        session.beginTransaction();
        Query q = session.createQuery("FROM ChiTietDoGo where SoLuong > 0");
        List<ChiTietDoGo> list = q.getResultList();//Lấy List Danh Sách Muốn Xuất Excel

        
        
        
        XSSFWorkbook workbook = new XSSFWorkbook(); // không cần quan tâm
        //Create a blank sheet
        XSSFSheet sheet1 = workbook.createSheet("Employee Data");//Các sheet trong excel hay còn gọi là Trang 1
        XSSFSheet sheet2 = workbook.createSheet("CTSP ");//Các sheet trong excel hay còn gọi là Trang 2


        //This data needs to be written (Object[]) load vào để chuẩn bị loat lên các dòng
        int i = 1; 
        Map<String, Object[]> data1 = new TreeMap<String, Object[]>();
        for (ChiTietDoGo a : list) {
            data1.put(String.valueOf(i++), new Object[]{a.getId(), a.getTenSP(), a.getSoLuong()});
        }

        //Iterate over data and write to sheet 1
        Set<String> keyset1 = data1.keySet();
        int rownum1 = 0;
        for (String key : keyset1){
            Row row1 = sheet1.createRow(rownum1++);
            Object [] objArr1 = data1.get(key);
            int cellnum1 = 0;
            for (Object obj : objArr1)
            {
                Cell cell1 = row1.createCell(cellnum1++);
               if(obj instanceof String)
                    cell1.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell1.setCellValue((Integer)obj);
                }
            }
         //Iterate over data and write to sheet 2
        Set<String> keyset2 = data1.keySet();
        int rownum2 = 0;
        for (String key : keyset2){
            Row row2 = sheet2.createRow(rownum2++);
            Object [] objArr2 = data1.get(key);
            
            
            int cellnum2 = 0;
            for (Object obj : objArr2){
                Cell cell2 = row2.createCell(cellnum2++);
                
                
               if(obj instanceof String)
                    cell2.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell2.setCellValue((Integer)obj);
                }
            }
        
        
        try
        {
            //Write the workbook in file system
            JFileChooser chooser = new JFileChooser();// mở file lên 
            chooser.showOpenDialog(null);//để chọn lưu vào đâu
            File file = chooser.getSelectedFile();
            FileOutputStream out = new FileOutputStream(new File(file + ".xlsx"));// lưu dưới dạng excel 
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        XuatFileExcel i = new XuatFileExcel();
        i.d();
    }
}
