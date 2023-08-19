/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.HoaDon;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Admin
 */
public class DoanhThuRepository {

    Session session = HibernatUtil.getFACTORY().openSession();

    Transaction transaction = session.getTransaction();

    public List<Object[]> getList() {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select B.Id,B.TenSP,B.SoLuong ,Sum(A.SoLuong),SUM(A.DonGia)\n"
                    + "from HoaDonChiTiet A Join ChiTietDoGo B on A.IdChiTietDoGo = B.Id\n"
                    + "group by B.TenSP,B.Id,B.SoLuong \n"
                    + "order by Sum(A.SoLuong)  asc"
            );

            List<Object[]> list = q.getResultList();

            return list;

        } catch (Exception e) {

            return null;

        }

    }

    public List<Object[]> getList1() {

        try {

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select B.Id,B.TenSP,B.SoLuong ,Sum(A.SoLuong),SUM(C.ThanhTien)\n"
                    + "                from HoaDonChiTiet A Join ChiTietDoGo B on A.IdChiTietDoGo = B.Id \n"
                    + "				JOIN HoaDon C ON C.Id =A.IdHoaDon\n"
                    + "				WHERE C.TrangThai = 2\n"
                    + "                group by B.TenSP,B.Id,B.SoLuong \n"
                    + "                order by Sum(A.SoLuong)  asc"
            );

            List<Object[]> list = q.getResultList();

            return list;

        } catch (Exception e) {

            return null;

        }

    }

    public List<Object[]> getListTUBEDENLON() {

        try {

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select B.Id,B.TenSP,B.SoLuong ,Sum(A.SoLuong),SUM(A.DonGia)\n"
                    + "from HoaDonChiTiet A Join ChiTietDoGo B on A.IdChiTietDoGo = B.Id\n"
                    + "group by B.TenSP,B.Id,B.SoLuong \n"
                    + "order by Sum(A.SoLuong)  asc");

            List<Object[]> list = q.getResultList();

            return list;

        } catch (Exception e) {

            return null;

        }

    }

    public List<Object[]> getListTULONDENBE() {

        try {

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select B.Id,B.TenSP,B.SoLuong ,Sum(A.SoLuong),SUM(A.DonGia)\n"
                    + "from HoaDonChiTiet A Join ChiTietDoGo B on A.IdChiTietDoGo = B.Id\n"
                    + "group by B.TenSP,B.Id,B.SoLuong \n"
                    + "order by Sum(A.SoLuong)  DESC");

            List<Object[]> list = q.getResultList();

            return list;

        } catch (Exception e) {

            return null;

        }

    }

    public List<Object[]> getListtop3() {

//        try {
        Session session = HibernatUtil.getFACTORY().openSession();

        Transaction transaction = session.getTransaction();

        Query q = session.createQuery("select B.Id,B.TenSP,B.SoLuong ,Sum(A.SoLuong),SUM(A.DonGia)"
                + " from HoaDonChiTiet A , ChiTietDoGo B , HoaDon C\n"
                + "where A.IdChiTietDoGo = B.Id and A.IdHoaDon = C.Id And C.TrangThai =2\n"
                + "group by B.TenSP,B.Id,B.SoLuong \n"
                + "order by Sum(A.SoLuong)  desc");

        List<Object[]> list = (List<Object[]>) q.setMaxResults(3).getResultList();

        return list;

//        } catch (Exception e) {
//            
//            return null;
//            
//        }
    }

    public int getListSanPham() {

        try {

            int a = -1;

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select COUNT(A.SoLuong),COUNT(A.SoLuong) "
                    + "from ChiTietDoGo A "
                    + "where A.TrangThai = 1");

            List<Object[]> list = q.getResultList();

            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }

            return a;

        } catch (Exception e) {

            return -1;

        }

    }

    public int getListGanHet() {

        try {

            int a = -1;

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select COUNT(A.SoLuong),COUNT(A.SoLuong) "
                    + "from ChiTietDoGo A "
                    + "where A.SoLuong > 0 AND A.SoLuong < 10 And A.TrangThai = 1");
            List<Object[]> list = q.getResultList();

            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }

            return a;

        } catch (Exception e) {

            return -1;

        }

    }

    public int getListhethang() {

        try {

            int a = 0;

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select COUNT(A.SoLuong),COUNT(A.SoLuong) "
                    + "from ChiTietDoGo A"
                    + " where A.SoLuong = 0 And A.TrangThai = 1");
            List<Object[]> list = q.getResultList();

            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }

            return a;

        } catch (Exception e) {

            return 0;

        }

    }

    public int getListNgungKinhdoanh() {

        try {

            int a = 0;

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select COUNT(A.SoLuong),COUNT(A.SoLuong) "
                    + "from ChiTietDoGo A "
                    + "where A.TrangThai = 0");

            List<Object[]> list = q.getResultList();

            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }

            return a;

        } catch (Exception e) {

            return 0;

        }

    }

    //nhanviendoanhthu
    public List<Object[]> getListNhanVien() {

        try {

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select A.Id,A.Ma,A.HoTen,C.TenChucVu,COUNT(B.Id)"
                    + " from NhanVien A , HoaDon B ,ChucVu C\n"
                    + "where A.Id = B.IdNhanVien and A.IdChucVu = C.Id\n"
                    + "group by A.Id,A.Ma,A.HoTen,C.TenChucVu\n"
                    + "order by COUNT(B.Id)  desc");

            List<Object[]> list = (List<Object[]>) q.setMaxResults(3).getResultList();

            return list;

        } catch (Exception e) {

            return null;

        }
    }

    //doanhthusanpham
    public List<Object[]> getListDoanhthu() {

        //try {
        Session session = HibernatUtil.getFACTORY().openSession();
        Transaction transaction = session.getTransaction();
        Query q = session.createQuery("select C.Id,C.Ma,C.NgayThanhToan,C.ThanhTien "
                + "from HoaDonChiTiet A , ChiTietDoGo B , HoaDon C\n"
                + "where A.IdChiTietDoGo = B.Id and A.IdHoaDon = C.Id And C.TrangThai =2 \n"
                + "group by C.Id,C.Ma,C.NgayThanhToan,A.DonGia,C.ThanhTien "
                + "order by C.NgayThanhToan asc");
        List<Object[]> list = q.getResultList();
        return list;
//        } catch (Exception e) {
//            return null;
//        }
    }

    public int getDoanhthuTheoNam() {

        try {

            int a = 0;

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select sum(ThanhTien),sum(ThanhTien)"
                    + " from HoaDon \n"
                    + "where year(getdate()) =year(NgayThanhToan) And TrangThai =2 ");

            List<Object[]> list = q.getResultList();

            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }

            return a;

        } catch (Exception e) {

            return 0;

        }

    }

    public int getDoanhthutheoThang() {

        try {

            int a = 0;

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select Sum(ThanhTien),Sum(ThanhTien)"
                    + " from HoaDon \n"
                    + "where year(getdate()) =year(NgayThanhToan) AND TrangThai =2\n"
                    + "and month(getdate()) =MONTH(NgayThanhToan)");

            List<Object[]> list = q.getResultList();

            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }

            return a;

        } catch (Exception e) {

            return 0;

        }

    }

    public int getDoanhtHUTHEOnGAY() {

        try {

            int a = 0;

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select Sum(ThanhTien),Sum(ThanhTien)"
                    + " from HoaDon \n"
                    + "where year(getdate()) =year(NgayThanhToan) AND TrangThai =2\n"
                    + "and month(getdate()) =MONTH(NgayThanhToan)\n"
                    + "and CONVERT(varchar(20),GETDATE(),23) = NgayThanhToan");

            List<Object[]> list = q.getResultList();

            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }

            return a;

        } catch (Exception e) {

            return 0;

        }

    }

    //so tien bo ra de mua 
    public int getsotiennhaptheonam() {

        try {

            int a = 0;

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select Sum(TongTienNhap),Sum(TongTienNhap)"
                    + " from LichSuNhap\n"
                    + "where year(getdate()) =year(NgayNhap) AND TrangThai =1 ");
            List<Object[]> list = q.getResultList();

            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }

            return a;

        } catch (Exception e) {

            return 0;

        }

    }

    public int getsotiennhaptheothang() {

        try {

            int a = 0;

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select Sum(TongTienNhap),Sum(TongTienNhap) "
                    + "from LichSuNhap\n"
                    + "where year(getdate()) =year(NgayNhap) AND TrangThai =1\n"
                    + "and month(getdate()) =MONTH(NgayNhap)");

            List<Object[]> list = q.getResultList();

            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }

            return a;

        } catch (Exception e) {

            return 0;

        }

    }

    public int getsotiennhaptheoNgay() {

        try {

            int a = 0;

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select Sum(TongTienNhap),Sum(TongTienNhap) "
                    + "from LichSuNhap\n"
                    + "where year(getdate()) =year(NgayNhap) AND TrangThai =1\n"
                    + "and month(getdate()) =MONTH(NgayNhap)\n"
                    + "and CONVERT(varchar(20),GETDATE(),23) = NgayNhap");
            List<Object[]> list = q.getResultList();

            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }

            return a;

        } catch (Exception e) {

            return 0;

        }

    }

    public List<Object[]> getListHoaDonDaChon(String nbd, String nkt) {

        try {

            Session session = HibernatUtil.getFACTORY().openSession();

            Query q = session.createQuery("select Id,Ma,NgayThanhToan,Sum(ThanhTien)"
                    + " from  HoaDon \n"
                    + "where '" + nbd + "'<=NgayThanhToan and NgayThanhToan<='" + nkt + "'\n"
                    + "group by  Id,Ma,NgayThanhToan");

            List<Object[]> list = q.getResultList();

            return list;

        } catch (Exception e) {

            return null;

        }

    }

    public int getListTongtiendachon(String nbd, String nkt) {

        try {

            int a = 0;

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select Sum(ThanhTien),Sum(ThanhTien) "
                    + "from  HoaDon \n"
                    + "where '" + nbd + "'<=NgayThanhToan and NgayThanhToan<='" + nkt + "'\n"
                    + "");

            List<Object[]> list = q.getResultList();

            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }

            return a;

        } catch (Exception e) {

            return 0;

        }

    }

    public int getListTongBoRaDaChon(String nbd, String nkt) {

        try {

            int a = 0;

            Session session = HibernatUtil.getFACTORY().openSession();

            Transaction transaction = session.getTransaction();

            Query q = session.createQuery("select Sum(TongTienNhap),Sum(TongTienNhap)"
                    + " from LichSuNhap \n"
                    + "where '" + nbd + "'<=NgayNhap and NgayNhap<='" + nkt + "'\n"
                    + "");
            List<Object[]> list = q.getResultList();

            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }

            return a;

        } catch (Exception e) {

            return 0;
        }

    }

    public List<Object[]> getList1(int i, int b) {

        try {

            Transaction transaction = session.getTransaction();

            Session session = HibernatUtil.getFACTORY().openSession();

            Query q = session.createNativeQuery(" select B.Id,B.TenSP,B.SoLuong ,Sum(A.SoLuong),SUM(C.ThanhTien) as '2'\n"
                    + "                    from HoaDonChiTiet A Join ChiTietDoGo B on A.IdChiTietDoGo = B.Id Join HoaDon C on C.Id =A.IdHoaDon\n"
                    + "                    WHERE C.TrangThai = 2 \n"
                    + "                    group by B.TenSP,B.Id,B.SoLuong \n"
                    + "                    order by Sum(C.ThanhTien)  desc"
                    + " OFFSET " + i + " ROWS "
                    + " FETCH NEXT " + b + " ROWS ONLY ");
//                    
            List<Object[]> list = q.getResultList();

            return list;

        } catch (Exception e) {

            return null;

        }

    }

    public int getListSL() {

        int index = -1;

        try {

            Transaction transaction = session.getTransaction();

            Session session = HibernatUtil.getFACTORY().openSession();

            Query q = session.createNativeQuery("select B.Id,B.TenSP,B.SoLuong ,Sum(A.SoLuong),SUM(A.DonGia) as '2'\n"
                    + "from HoaDonChiTiet A Join ChiTietDoGo B on A.IdChiTietDoGo = B.Id\n"
                    + "group by B.TenSP,B.Id,B.SoLuong \n"
            );

            List<Object[]> list = q.getResultList();

            index = list.size();

            return index;

        } catch (Exception e) {

            return -1;

        }

    }

    public List<Object[]> getListBdenL(int i, int b) {

        try {

            Transaction transaction = session.getTransaction();

            Session session = HibernatUtil.getFACTORY().openSession();

            Query q = session.createNativeQuery(" select B.Id,B.TenSP,B.SoLuong ,Sum(A.SoLuong),SUM(C.ThanhTien) as '2'\n"
                    + "                    from HoaDonChiTiet A Join ChiTietDoGo B on A.IdChiTietDoGo = B.Id Join HoaDon C on C.Id =A.IdHoaDon\n"
                    + "                    WHERE C.TrangThai = 2 \n"
                    + "                    group by B.TenSP,B.Id,B.SoLuong \n"
                    + "                    order by Sum(C.ThanhTien)  asc"
                    + " OFFSET " + i + " ROWS "
                    + " FETCH NEXT " + b + " ROWS ONLY ");
//                    
            List<Object[]> list = q.getResultList();

            return list;

        } catch (Exception e) {

            return null;

        }

    }

    public int getListSLBdenL() {

        int index = -1;

        try {

            Transaction transaction = session.getTransaction();

            Session session = HibernatUtil.getFACTORY().openSession();

            Query q = session.createNativeQuery("select B.Id,B.TenSP,B.SoLuong ,Sum(A.SoLuong),SUM(A.DonGia) as '2'\n"
                    + "from HoaDonChiTiet A Join ChiTietDoGo B on A.IdChiTietDoGo = B.Id\n"
                    + "group by B.TenSP,B.Id,B.SoLuong \n"
            );

            List<Object[]> list = q.getResultList();

            index = list.size();

            return index;

        } catch (Exception e) {

            return -1;

        }

    }

    public static void main(String[] args) {

        DoanhThuRepository hd = new DoanhThuRepository();

//        int i = hd.getListSL(0, 1);
//        System.out.println(i);
//        List<Object[]> list = hd.getList1(0, 1);
//        for (Object[] hoaDon : list) {
//            System.out.println(hoaDon[1].toString());
//        }
        for (Object[] a : hd.getListtop3()) {
            System.out.println(a[0].toString());
        }

        //System.out.println(i.getDoanhtHUTHEOnGAY());
    }

}
