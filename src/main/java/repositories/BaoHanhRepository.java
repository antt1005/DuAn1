/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.HoaDon;
import domainModels.KhachHang;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Phuong Bi
 */
public class BaoHanhRepository {

    public List<KhachHang> getListKhachHang() {
        try {
            Session session = HibernatUtil.getFACTORY().openSession(); //Ket noi DB thuc hien hien truy van
            Query q = session.createQuery("FROM KhachHang WHERE TrangThai = 1  order by ma "); //Tao cau truy van lay du lieu tu bang dong go
            List<KhachHang> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    
     public List<KhachHang> getTKSDT(String sdt) {
        try {
            Session se = HibernatUtil.getFACTORY().openSession();
            Query q = se.createQuery("FROM KhachHang WHERE TrangThai = 1 AND Sdt like :sdt");
            q.setParameter("sdt","%" + sdt + "%");
            List<KhachHang> list = q.getResultList();
            return list;

        } catch (Exception e) {
            return null;
        }
    }
        
        
     
     
     

   

  
}
