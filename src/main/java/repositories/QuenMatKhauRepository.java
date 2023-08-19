/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.KhachHang;

import domainModels.KhuyenMai;

import domainModels.NhanVien;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author admin
 */
public class QuenMatKhauRepository {
    
       public List<NhanVien> getListSDT(String ten) {
           
        try {
            
            Session session = HibernatUtil.getFACTORY().openSession();
            
            org.hibernate.query.Query q = session.createQuery("FROM NhanVien WHERE   Sdt = :Sdt AND TrangThai = 1 ");
            
            q.setParameter("Sdt",ten );
            
            List<NhanVien> list = q.getResultList();
            
            return list;
            
        } catch (Exception e) {
            
            return null;
        }
    }
    
}
