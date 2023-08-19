/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.HoaDon;
import domainModels.HoaDonChiTiet;
import domainModels.SanPham;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author ktkha
 */
public class SanPhamRepository {

    Session session = HibernatUtil.getFACTORY().openSession();
    Transaction transaction = session.getTransaction();

    public List<Object[]> getListSP(int i, int b) {
        try {
            Transaction transaction = session.getTransaction();

            Session session = HibernatUtil.getFACTORY().openSession();
            org.hibernate.query.Query q = session.createNativeQuery(""
                    + "  SELECT * FROM SanPham WHERE TrangThai = 1 \n"
                    + "  ORDER BY Ma desc\n"
                    + "  OFFSET " + i + " ROWS\n"
                    + "  FETCH NEXT " + b + " ROWS ONLY");

            List<Object[]> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }

    }

    public int getListSLRow() {
        int index = -1;
        try {
            Transaction transaction = session.getTransaction();

            Session session = HibernatUtil.getFACTORY().openSession();
            org.hibernate.query.Query q = session.createNativeQuery("SELECT * FROM SanPham  WHERE TrangThai = 1\n"
                    + "  ORDER BY Ma desc ");

            List<Object[]> list = q.getResultList();
            index = list.size();
            return index;
        } catch (Exception e) {
            return -1;
        }

    }

    public List<SanPham> getListSP() {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM SanPham WHERE TrangThai = 1");
            List<SanPham> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<SanPham> getListSPByName(String ten) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM SanPham WHERE TrangThai = 1 AND Ten like :ten ");
            q.setParameter("ten", "%" + ten + "%");
            List<SanPham> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public int getMaxMa() { //Lấy mã lớn nhất + 1 => Tự động tăng
        Session session = HibernatUtil.getFACTORY().openSession();
        String soMaLonNhat = null;
        Query q = session.createQuery(" select A.Ma From SanPham A Where TrangThai = 1 ");
        List<String> i = q.getResultList(); //Lay list String 
        if (i.isEmpty()) {
            return 0;
        } else {
            List<Integer> c = new ArrayList<>(); //Convert list String sang List Integer
            for (String a : i) {
                c.add(Integer.parseInt(a));
            }
            System.out.println(c + "\n");

            int max = c.get(0); //Từ list int lấy ra số lớn nhất
            for (int j = 0; j < c.size(); j++) {
                if (c.get(j).compareTo(max) > 0) {
                    max = c.get(j);
                }
            }

            return max;
        }
    }

    public boolean add(SanPham s) {
        String getMa = String.valueOf(getMaxMa() + 1);

        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            SanPham sp = new SanPham();
            sp.setMa(getMa);
            sp.setTen(s.getTen());
            sp.setTrangThai(1);

            session.getTransaction().begin();
            session.save(sp);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(SanPham s) {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            SanPham sp = session.get(SanPham.class, s.getId());
            sp.setTen(s.getTen());
            sp.setTrangThai(1);

            session.getTransaction().begin();
            session.save(sp);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(SanPham s) { //Sau khi xoa thi trang thai update ve` 0

        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            SanPham sp = session.get(SanPham.class, s.getId());
            sp.setTrangThai(0);

            session.getTransaction().begin();
            session.save(sp);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        SanPhamRepository sp = new SanPhamRepository();
//        List<SanPham> list = sp.getListSP();
//        for (SanPham sanPham : list) {
//            System.out.println(sanPham.toString());
        SanPham s = new SanPham();
        s.setId("71161A28-2829-42E2-980D-8F36A9015B90");
        sp.delete(s);

//        int getMaxM = sp.getMaxMa();
//        System.out.println(getMaxM);
//        }
    }
}
