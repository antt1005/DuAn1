/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.ChiTietDoGo;
import domainModels.DonViTinh;
import domainModels.DongGo;
import domainModels.HoaDonChiTiet;
import domainModels.LoaiSP;
import domainModels.NguonGoc;
import domainModels.NhaCungCap;
import domainModels.SanPham;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Admin
 */
public class BanHangChiTietDoGoRepository {

    private Session session = HibernatUtil.getFACTORY().openSession();

    public List<Object[]> getList(int i, int b) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createNativeQuery("select A.Id,A.TenSP,B.Ten,C.TenDongSP,D.TenLoaiGo"
                    + ",E.TenNCC,F.QuocGia,G.DonViTinh \n"
                    + ", A.SoLuong,A.GiaNhap,A.GiaBan,A.MoTa,A.TrangThai\n"
                    + "from ChiTietDoGo A Left Join SanPham B\n"
                    + "ON A.IdSanPham = B.Id left join LoaiSP C On A.IdLoaiSP = C.Id \n"
                    + "left Join DongGo D ON A.IdDongGo = D.Id left Join NhaCungCap E ON \n"
                    + "A.IdNhaCungCap = E.Id LEFT JOIN NguonGoc F ON A.IdNguonGoc = F.Id \n"
                    + "LEFT JOIN DonViTinh G ON A.IdDonViTinh = G.Id\n"
                    + "where A.TrangThai = 1\n"
                    + "ORDER BY A.TrangThai desc \n"
                    + "OFFSET " + i + " ROWS \n"
                    + "FETCH NEXT " + b + " ROWS ONLY");
            List<Object[]> list = q.getResultList();
            session.close();
            return list;
        } catch (HibernateException hibernateException) {
            return null;
        }
    }

    public int getRow() {
        int index = -1;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("select count(A.Id),count(A.Id) From ChiTietDoGo A where A.TrangThai = 1");

            List<Object[]> list = q.getResultList();
            for (Object[] objects : list) {
                index = Integer.parseInt(objects[0].toString());
            }
            return index;
        } catch (HibernateException hibernateException) {
            return -1;
        }
    }

    public List<ChiTietDoGo> TimKiemTheoId(String Id) {
        Session session = HibernatUtil.getFACTORY().openSession();
        Query q = session.createQuery("FROM ChiTietDoGo Where TrangThai = 1 and Id =: Idsp");
        q.setParameter("Idsp", Id);

        List<ChiTietDoGo> list = q.getResultList();
        return list;
    }

    public List<Object[]> TimKiemTen(String Ten, int i, int b) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createNativeQuery("select A.Id,A.TenSP,B.Ten,C.TenDongSP,D.TenLoaiGo"
                    + ",E.TenNCC,F.QuocGia,G.DonViTinh \n"
                    + ", A.SoLuong,A.GiaNhap,A.GiaBan,A.MoTa,A.TrangThai\n"
                    + "from ChiTietDoGo A Left Join SanPham B\n"
                    + "ON A.IdSanPham = B.Id left join LoaiSP C On A.IdLoaiSP = C.Id \n"
                    + "left Join DongGo D ON A.IdDongGo = D.Id left Join NhaCungCap E ON \n"
                    + "A.IdNhaCungCap = E.Id LEFT JOIN NguonGoc F ON A.IdNguonGoc = F.Id \n"
                    + "LEFT JOIN DonViTinh G ON A.IdDonViTinh = G.Id\n"
                    + "where A.TrangThai = 1 and A.TenSP LIKE :Ten\n"
                    + "ORDER BY A.TrangThai desc \n"
                    + "OFFSET " + i + " ROWS \n"
                    + "FETCH NEXT " + b + " ROWS ONLY");
            q.setParameter("Ten", "%" + Ten + "%");
            List<Object[]> list = q.getResultList();
            session.close();
            return list;
        } catch (HibernateException hibernateException) {
            return null;
        }
    }

    public int getRowTimKiem(String Ten) {
        int i = -1;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("From ChiTietDoGo A Where  A.TrangThai = 1 and A.TenSP LIKE :Ten ");

            q.setParameter("Ten", "%" + Ten + "%");
            List<ChiTietDoGo> list = q.getResultList();
           
                i = list.size();
            
            return i;
        } catch (HibernateException hibernateException) {
            return -1;
        }
    }

    public boolean add(ChiTietDoGo go) {
        try {
            ChiTietDoGo i = new ChiTietDoGo();

            SanPham sp = session.get(SanPham.class, go.getIdSanPham().getId());// lấy id từ thuộc tính 
            LoaiSP loaisp = session.get(LoaiSP.class, go.getIdLoaiSP().getId());
            DongGo donggo = session.get(DongGo.class, go.getIdDongGo().getId());
            NhaCungCap ncc = session.get(NhaCungCap.class, go.getIdNhaCungCap().getId());
            NguonGoc nguongoc = session.get(NguonGoc.class, go.getIdNguocGoc().getId());
            DonViTinh dvtinh = session.get(DonViTinh.class, go.getIdDonViTinh().getId());
// add id các thuộc tính vào          
            i.setIdSanPham(sp);
            i.setIdLoaiSP(loaisp);
            i.setIdDongGo(donggo);
            i.setIdNhaCungCap(ncc);
            i.setIdNguocGoc(nguongoc);
            i.setIdDonViTinh(dvtinh);
// những thuộc tính còn lại
            i.setTenSP(go.getTenSP());
            i.setSoLuong(go.getSoLuong());
            i.setGiaNhap(go.getGiaNhap());
            i.setGiaBan(go.getGiaBan());
            i.setMoTa(go.getMoTa());
            i.setTrangThai(1);

            session.getTransaction().begin();
            session.save(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean update(ChiTietDoGo go) {
        try {
            ChiTietDoGo i = session.get(ChiTietDoGo.class, go.getId());

            SanPham sp = session.get(SanPham.class, go.getIdSanPham().getId());// lấy id từ thuộc tính 
            LoaiSP loaisp = session.get(LoaiSP.class, go.getIdLoaiSP().getId());
            DongGo donggo = session.get(DongGo.class, go.getIdDongGo().getId());
            NhaCungCap ncc = session.get(NhaCungCap.class, go.getIdNhaCungCap().getId());
            NguonGoc nguongoc = session.get(NguonGoc.class, go.getIdNguocGoc().getId());
            DonViTinh dvtinh = session.get(DonViTinh.class, go.getIdDonViTinh().getId());
// add id các thuộc tính vào          
            i.setIdSanPham(sp);
            i.setIdLoaiSP(loaisp);
            i.setIdDongGo(donggo);
            i.setIdNhaCungCap(ncc);
            i.setIdNguocGoc(nguongoc);
            i.setIdDonViTinh(dvtinh);
// những thuộc tính còn lại
            i.setTenSP(go.getTenSP());
            i.setSoLuong(go.getSoLuong());
            i.setGiaNhap(go.getGiaNhap());
            i.setGiaBan(go.getGiaBan());
            i.setMoTa(go.getMoTa());
            i.setTrangThai(1);

            session.getTransaction().begin();
            session.save(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean delete(String Id) {
        try {
            ChiTietDoGo i = session.get(ChiTietDoGo.class, Id);
            i.setTrangThai(0);
            session.getTransaction().begin();
            session.update(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        BanHangChiTietDoGoRepository i = new BanHangChiTietDoGoRepository();
        for (Object[] arg : i.TimKiemTen("Ghe", 0, 10)) {
            System.out.println(arg[0] + "    " + arg[1]);
        }
        int row = i.getRowTimKiem("Ghe");
        System.out.println(row);
    }
}
