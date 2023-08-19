/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.ChucVu;
import domainModels.CuaHang;
import domainModels.NhanVien;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author ktkha
 */
public class DoiMatKhauRepository {

    public List<NhanVien> getNVbyId(String id) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM NhanVien where TrangThai = 1 AND Id = '" + id + "'");
            List<NhanVien> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public String getMKById(String id) {

        String mk = null;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createNativeQuery("select MatKhau,Sdt from NhanVien where Id = '" + id + "'");
            List<Object[]> list = q.getResultList();
            for (Object[] n : list) {
                mk = n[0].toString();
            }
            return mk;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateMKById(String id, String mk) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            NhanVien v = session.get(NhanVien.class, id);

            v.setMatKhau(mk);

            session.getTransaction().begin();
            session.save(v);
            session.getTransaction().commit();
            session.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static void main(String[] args) {
        DoiMatKhauRepository a = new DoiMatKhauRepository();
        System.out.println(a.getNVbyId("121D16CE-B94A-4AFF-8E6C-887C2657A694"));

//        a.getNVbyId("121D16CE-B94A-4AFF-8E6C-887C2657A694");
        
    }
}
