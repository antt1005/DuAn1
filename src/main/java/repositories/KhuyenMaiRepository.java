/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.KhuyenMai;
import domainModels.SanPham;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import utilities.mycompany.DBConext.HibernatUtil;
import viewModel.KhuyenMaiViewModel;

/**
 *
 * @author ktkha
 */
public class KhuyenMaiRepository {

    java.util.Date date = java.util.Calendar.getInstance().getTime();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    String now = format.format(date);

    public List<KhuyenMai> getListKMAll() {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM KhuyenMai WHERE TrangThai = 1");
            List<KhuyenMai> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<KhuyenMai> getListKMCon() {
//        try {

        Session session = HibernatUtil.getFACTORY().openSession();
        Query q = session.createQuery("From KhuyenMai where CONVERT(varchar(20),GETDATE(),23) >= NgayBatDau AND\n"
                + "CONVERT(varchar(20),GETDATE(),23) <= NgayKetThuc	");
        List<KhuyenMai> list = q.getResultList();
        return list;
//        } catch (Exception e) {
//            return null;
//        }
        }

    public List<KhuyenMai> getListKMHet() {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM KhuyenMai WHERE \n"
                    + "TrangThai = 1 AND CONVERT(varchar(20),GETDATE(),23) > NgayKetThuc	");
            List<KhuyenMai> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<KhuyenMai> getListKMByDate(String ten) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM KhuyenMai WHERE TrangThai = 1 AND TenKhuyenMai like :ten ");
            q.setParameter("ten", "%" + ten + "%");
            List<KhuyenMai> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<KhuyenMai> getListKMByDateContg(String ten) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM KhuyenMai WHERE TrangThai = 1 AND TenKhuyenMai like :ten ");
            q.setParameter("ten", "%" + ten + "%");
            KhuyenMaiRepository kmRepo = new KhuyenMaiRepository();
            List<KhuyenMai> khuyenMais = kmRepo.getListKMCon();
            khuyenMais = q.getResultList();

            return khuyenMais;
        } catch (Exception e) {
            return null;
        }
    }

    public int getMaxMa() { //Lấy mã lớn nhất + 1 => Tự động tăng
        Session session = HibernatUtil.getFACTORY().openSession();
        String soMaLonNhat = null;
        Query q = session.createQuery(" select A.Ma From KhuyenMai A Where TrangThai = 1 ");
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

    public boolean add(KhuyenMai km) {
        String getMa = String.valueOf(getMaxMa() + 1);

        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            KhuyenMai khuyenMai = new KhuyenMai();
            khuyenMai.setMa(getMa);
            khuyenMai.setTenKhuyenMai(km.getTenKhuyenMai());
            khuyenMai.setPhanTramKM(km.getPhanTramKM());

            //convert date to string Date.valueOf()
            khuyenMai.setNgayBatDau(km.getNgayBatDau());
            khuyenMai.setNgayKetThuc(km.getNgayKetThuc());
            khuyenMai.setTrangThai(1);

            session.getTransaction().begin();
            session.save(khuyenMai);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(KhuyenMai km) {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            KhuyenMai khuyenMai = session.get(KhuyenMai.class, km.getId());
            khuyenMai.setTenKhuyenMai(km.getTenKhuyenMai());
            khuyenMai.setNgayBatDau(km.getNgayBatDau());
            khuyenMai.setNgayKetThuc(km.getNgayKetThuc());
            khuyenMai.setPhanTramKM(km.getPhanTramKM());

            session.getTransaction().begin();
            session.save(khuyenMai);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(KhuyenMai km) {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            KhuyenMai khuyenMai = session.get(KhuyenMai.class, km.getId());
            khuyenMai.setTrangThai(0);

            session.getTransaction().begin();
            session.save(khuyenMai);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        KhuyenMaiRepository kmRepo = new KhuyenMaiRepository();
        List<KhuyenMai> khuyenMais = kmRepo.getListKMHet();
        for (KhuyenMai khuyenMai : khuyenMais) {
            System.out.println(khuyenMai.toString());
        }
    }

}
