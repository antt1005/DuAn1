/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.ChiTietDoGo;
import domainModels.HoaDon;
import domainModels.HoaDonChiTiet;
import domainModels.KhachHang;
import domainModels.NhanVien;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author ktkha
 */
public class HoaDonDeBaoHanhRepository {

    public List<HoaDon> getListHD(String id) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession(); //Ket noi DB thuc hien hien truy van
            Query q = session.createQuery("FROM HoaDon A WHERE A.TrangThai = 2 and A.NgayThanhToan "
                    + ">= CONVERT(date,Getdate()-7,23)  and A.IdKhachHang = '" + id + "'"); //Tao cau truy van lay du lieu tu bang dong go
            List<HoaDon> list = q.list();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<HoaDon> getListHDBH() {
        try {
            Session session = HibernatUtil.getFACTORY().openSession(); //Ket noi DB thuc hien hien truy van
            Query q = session.createQuery(" FROM HoaDon A WHERE A.TrangThai = 3"); //Tao cau truy van lay du lieu tu bang dong go
            List<HoaDon> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<HoaDonChiTiet> getListCTHD(String id) {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();
            javax.persistence.Query q = session.createQuery("from HoaDonChiTiet A where A.IdHoaDon = '" + id + "' ");
            List<HoaDonChiTiet> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }

    }

    public List<HoaDonChiTiet> getListCTHDbaoHanh(String id) {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();
            javax.persistence.Query q = session.createQuery("from HoaDonChiTiet A where A.IdHoaDon = '" + id + "' ");
            List<HoaDonChiTiet> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }

    }

    public int getMaxMa() {
        Session session = HibernatUtil.getFACTORY().openSession();
        String soMaLonNhat = null;
        javax.persistence.Query q = session.createQuery(" select A.Ma From HoaDon A  ");
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

    public boolean addHoadon(HoaDon hd) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();
            java.util.Date date = java.util.Calendar.getInstance().getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            String now = format.format(date);

            HoaDon i = new HoaDon();
            i.setMa(String.valueOf(getMaxMa() + 1)); // mã lớn nhất + thêm 1         
            i.setNgayTao(Date.valueOf(now)); // lấy thời gian ở trên gán vô 
            KhachHang kh = session.get(KhachHang.class, hd.getIdKhachHang().getId());
            i.setIdKhachHang(kh);
            NhanVien nv = session.get(NhanVien.class, hd.getIdNhanVien().getId());
            i.setIdNhanVien(nv);
            i.setTrangThaiHoaDon(0); // 0 là chưa thanh toán - 1 là đã hoàn thành 
            i.setTrangThai(3);// trang thai outo la 1 , 0 là đã xóa , 3 la bao hanh
            i.setThanhTien(new BigDecimal("0"));

            transaction.begin();
            session.save(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean updateHoadon(HoaDon hd) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();
            java.util.Date date = java.util.Calendar.getInstance().getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            String now = format.format(date);
            
            HoaDon i = session.get(HoaDon.class, hd.getId());
            
            NhanVien nv = session.get(NhanVien.class, hd.getIdNhanVien().getId());
            i.setIdNhanVien(nv);
            KhachHang kh = session.get(KhachHang.class, hd.getIdKhachHang().getId());
            i.setIdKhachHang(kh);
            i.setNgayTao(Date.valueOf(now)); // lấy thời gian ở trên gán vô 
            i.setTrangThai(4);// trang thai outo la 1 , 0 là đã xóa , 3 la bao hanh

            transaction.begin();
            session.save(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean add(HoaDonChiTiet hd) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            HoaDonChiTiet i = new HoaDonChiTiet();

            HoaDon h = session.get(HoaDon.class, hd.getIdHoaDon().getId());

            i.setIdHoaDon(h);

            ChiTietDoGo b = session.get(ChiTietDoGo.class, hd.getIdChiTietDoGo().getId());

            i.setIdChiTietDoGo(b);
            i.setSoLuong(hd.getSoLuong());

            session.getTransaction().begin();
            session.save(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(HoaDonChiTiet hd) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            HoaDonChiTiet i = new HoaDonChiTiet();

            HoaDon h = session.get(HoaDon.class, hd.getIdHoaDon().getId());

            i.setIdHoaDon(h);

            ChiTietDoGo b = session.get(ChiTietDoGo.class, hd.getIdChiTietDoGo().getId());

            i.setIdChiTietDoGo(b);
            i.setSoLuong(hd.getSoLuong());
//            i.setDonGia(hd.getDonGia());

            session.getTransaction().begin();
            session.update(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(String idsp, String idhd) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            HoaDonChiTiet i = new HoaDonChiTiet();

            HoaDon a = session.get(HoaDon.class, idhd);

            ChiTietDoGo b = session.get(ChiTietDoGo.class, idsp);

            i.setIdHoaDon(a);
            i.setIdChiTietDoGo(b);

            session.getTransaction().begin();
            session.delete(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        HoaDonDeBaoHanhRepository a = new HoaDonDeBaoHanhRepository();
        List<HoaDon> list = a.getListHDBH();
        if (list == null) {
            System.out.println("OKE");
        }
        for (HoaDon hoaDon : list) {
            System.out.println(hoaDon.toString());
        }

//        NhanVien nv = new NhanVien();
//        nv.setId("97D42683-2927-4807-930A-FC948BA31B8F");
//        HoaDon hd = new HoaDon();
//        hd.setIdNhanVien(nv);
//        a.addHoadon(hd);
//        List<HoaDon> list = a.getListHDBH();
//        for (HoaDon hoaDon : list) {
//            System.out.println(hoaDon.toString());
//        }
    }

}
