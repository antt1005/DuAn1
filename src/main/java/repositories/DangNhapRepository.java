/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.ChiTietDoGo;
import domainModels.NhanVien;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Admin
 */
public class DangNhapRepository {

    public List<NhanVien> getTK() {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM NhanVien Where TrangThai = 1");
            List<NhanVien> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
