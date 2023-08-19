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
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Admin
 */
public class BaoHanhVipProRepository {

    public List<HoaDon> getListHoaDonById(String Id) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession(); //Ket noi DB thuc hien hien truy van
            Query q = session.createQuery("FROM HoaDon A WHERE  A.NgayThanhToan "
                    + ">= CONVERT(date,Getdate()-7,23)  and A.Id like '" + Id + "' and A.TrangThai in(2,4)"); //Tao cau truy van lay du lieu tu bang dong go
            List<HoaDon> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public String getSDT(String sdt) {
        try {
            String idKH = null;
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("From KhachHang where Sdt like '" + sdt + "'");
            List<KhachHang> kh = q.getResultList();
            for (KhachHang khachHang : kh) {
                idKH = khachHang.getId();
            }
            return idKH;
        } catch (Exception e) {
            return null;
        }
    }

    public List<HoaDon> getListHoaDonBySDT(String sdt) {
        //Ket noi DB thuc hien hien truy van
        try {
            String idkh = getSDT(sdt);
            if (idkh == null) {
                return null;
            }
            System.out.println(idkh);
            Session session = HibernatUtil.getFACTORY().openSession(); //Ket noi DB thuc hien hien truy van
            Query q = session.createQuery("FROM HoaDon A WHERE  A.NgayThanhToan "
                    + ">= CONVERT(date,Getdate()-7,23)  and A.IdKhachHang = '" + idkh + "' and A.TrangThai in(2,4)"); //Tao cau truy van lay du lieu tu bang dong go
            List<HoaDon> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<HoaDonChiTiet> getListHDCT(String idhd) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();
            Query q = session.createQuery("from HoaDonChiTiet A where A.IdHoaDon = '" + idhd + "'");
            List<HoaDonChiTiet> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public int getMaxMaNangCap() {
        int i = -1;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();
            Query q = session.createNativeQuery("select Max(convert(int,Ma)),Max(convert(int,Ma)) as'no' from HoaDon A Where TrangThai >=1  ");
            List<Object[]> list = q.getResultList();
            for (Object[] max : list) {
                i = Integer.parseInt(max[0].toString());
            }
            return i;
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean addhoadonBaoHanh(HoaDon hd) {
        // lấy thời gian tạo hóa đơn 
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();

            java.util.Date date = java.util.Calendar.getInstance().getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            String now = format.format(date);

            HoaDon i = new HoaDon();
            i.setMa(String.valueOf(getMaxMaNangCap() + 1)); // mã lớn nhất + thêm 1         
            i.setNgayTao(Date.valueOf(now)); // lấy thời gian ở trên gán vô 
            NhanVien nv = session.get(NhanVien.class, hd.getIdNhanVien().getId());
            KhachHang kh = session.get(KhachHang.class, hd.getIdKhachHang().getId());
            i.setIdNhanVien(nv);
            i.setIdKhachHang(kh);
            i.setTrangThaiHoaDon(0); // 0 là chưa thanh toán - 1 là đã hoàn thành 
            i.setTrangThai(3);// trang thai outo la 1 , 0 là đã xóa 
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

    public boolean BaoHanhTC(String id, BigDecimal ThanhTien) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            java.util.Date date = java.util.Calendar.getInstance().getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String now = format.format(date);

            HoaDon hd = session.get(HoaDon.class, id);
            hd.setThanhTien(ThanhTien);
            hd.setNgayThanhToan(Date.valueOf(now)); // lấy thời gian ở trên gán vô 
            hd.setTrangThai(4);

            session.getTransaction().begin();
            session.save(hd);
            session.getTransaction().commit();
            session.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean addcthoadon(HoaDonChiTiet hd) {
//        try {
        Session session = HibernatUtil.getFACTORY().openSession();
        HoaDonChiTiet i = new HoaDonChiTiet();

        HoaDon h = session.get(HoaDon.class, hd.getIdHoaDon().getId());

        i.setIdHoaDon(h);

        ChiTietDoGo b = session.get(ChiTietDoGo.class, hd.getIdChiTietDoGo().getId());

        i.setIdChiTietDoGo(b);
        i.setSoLuong(hd.getSoLuong());
        i.setDonGia(hd.getDonGia());
        i.setLuot(0);
        session.getTransaction().begin();
        session.save(i);
        session.getTransaction().commit();
        session.close();
        return true;
//        } catch (Exception e) {
//            return false;
//
//        }

    }

    public List<HoaDon> getListHDBH() {
        try {
            Session session = HibernatUtil.getFACTORY().openSession(); //Ket noi DB thuc hien hien truy van
            Query q = session.createQuery("FROM HoaDon A WHERE A.TrangThai = 3 "); //Tao cau truy van lay du lieu tu bang dong go
            List<HoaDon> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateSPTheoId(String idsp, String idhd, int SoLuong, String DonGia) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            HoaDonChiTiet i = new HoaDonChiTiet();

            HoaDon h = session.get(HoaDon.class, idhd);

            i.setIdHoaDon(h);

            ChiTietDoGo b = session.get(ChiTietDoGo.class, idsp);

            i.setIdChiTietDoGo(b);

            i.setSoLuong(SoLuong);
            i.setDonGia(new BigDecimal(DonGia));
            System.out.println(i.toString());

            session.getTransaction().begin();
            session.update(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateLuotBaoHanh(String idsp, String idhd, int luot, int soluong, String dongia) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            HoaDonChiTiet i = new HoaDonChiTiet();

            HoaDon h = session.get(HoaDon.class, idhd);

            i.setIdHoaDon(h);

            ChiTietDoGo b = session.get(ChiTietDoGo.class, idsp);

            i.setIdChiTietDoGo(b);

            i.setLuot(luot);
            i.setSoLuong(soluong);
            i.setDonGia(new BigDecimal(dongia));

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

    public boolean updateLaiLuot(String idhd, String idsp, int Luot) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createNativeQuery("UPDATE HoaDonChiTiet   set Luot = Luot-:luot "
                    + " Where IdHoaDon = :idhd and IdChiTietDoGo = :idsp");
            q.setParameter("luot", Luot);
            q.setParameter("idhd", idhd);
            q.setParameter("idsp", idsp);
            session.beginTransaction();
            int i = q.executeUpdate();
            session.getTransaction().commit();
            if(i>0){
                System.out.println("oke");
            }else{
                System.out.println("no");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        BaoHanhVipProRepository i = new BaoHanhVipProRepository();
//        for (HoaDon a : i.getListHDBH()) {
//            System.out.println(a.toString());
//        }
            i.updateLaiLuot("4F70DF83-4A1F-4C0A-8C7F-A472013AA19F", "4771CF52-B40D-4A93-B0EB-13DE20BA0F2E", 0);
    }
}
