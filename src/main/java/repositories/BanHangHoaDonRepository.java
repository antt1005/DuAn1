/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.HoaDon;
import domainModels.KhachHang;
import domainModels.KhuyenMai;
import domainModels.NhanVien;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Admin
 */
public class BanHangHoaDonRepository {

    Session session = HibernatUtil.getFACTORY().openSession();
    Transaction transaction = session.getTransaction();

    public List<HoaDon> getList() {
        Transaction transaction = session.getTransaction();
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("from HoaDon A Where A.TrangThaiHoaDon = 0 and A.TrangThai = 1");
            List<HoaDon> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }

    }

    public int getMaxMa() {
        Session session = HibernatUtil.getFACTORY().openSession();
        String soMaLonNhat = null;
        Query q = session.createQuery(" select A.Ma From HoaDon A  where A.TrangThai >=1");
        List<String> i = q.getResultList();
        if (i.isEmpty()) {
            return 0;
        } else {
            List<Integer> c = new ArrayList<>();
            for (String a : i) {
                c.add(Integer.parseInt(a));
            }
            System.out.println(c + "\n");

            int max = c.get(0);
            for (int j = 0; j < c.size(); j++) {
                if (c.get(j).compareTo(max) > 0) {
                    max = c.get(j);
                }
            }

            return max;
        }
    }

    public boolean addhoadon(HoaDon hd) {
        // lấy thời gian tạo hóa đơn 
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();

            java.util.Date date = java.util.Calendar.getInstance().getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            String now = format.format(date);

            HoaDon i = new HoaDon();
            i.setMa(String.valueOf(getMaxMa() + 1)); // mã lớn nhất + thêm 1         
            i.setNgayTao(Date.valueOf(now)); // lấy thời gian ở trên gán vô 
            NhanVien nv = session.get(NhanVien.class, hd.getIdNhanVien().getId());
            i.setIdNhanVien(nv);
            i.setTrangThaiHoaDon(0); // 0 là chưa thanh toán - 1 là đã hoàn thành 
            i.setTrangThai(1);// trang thai outo la 1 , 0 là đã xóa 
            i.setThanhTien(new BigDecimal("0"));

            transaction.begin();
            session.save(i);
            session.getTransaction().commit();
            session.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.toString());
            transaction.rollback();
            return false;
        }

    }

    public boolean update(String id, BigDecimal thanhTien, String idkh, String idKM) {
        // lấy thời gian tạo hóa đơn 
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();

            java.util.Date date = java.util.Calendar.getInstance().getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            String now = format.format(date);
            HoaDon hd = session.get(HoaDon.class, id);

            if (idKM!=null) {
                KhuyenMai km = session.get(KhuyenMai.class, idKM);
                hd.setIdKhuyenMai(km);

            }
            if (idkh!=null) {
                KhachHang kh = session.get(KhachHang.class, idkh);
                hd.setIdKhachHang(kh);
            }
            
            hd.setNgayThanhToan(Date.valueOf(now));
            hd.setThanhTien(thanhTien);
            hd.setTrangThai(2);

            transaction.begin();
            session.save(hd);
            session.getTransaction().commit();
            session.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.toString());
            transaction.rollback();
            return false;
        }

    }

    public static void main(String[] args) {
        BanHangHoaDonRepository o = new BanHangHoaDonRepository();
//        HoaDon i = new HoaDon();
//
//        NhanVien a = new NhanVien();
//        a.setId("8DAA9DA3-B007-4052-9CE3-6724AF09D2D3");
//
//        i.setIdNhanVien(a);

        System.out.println(o.getMaxMa());

    }
}
