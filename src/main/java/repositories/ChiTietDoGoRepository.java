/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.ChiTietDoGo;
import domainModels.DonViTinh;
import domainModels.DongGo;
import domainModels.HoaDon;
import domainModels.LoaiSP;
import domainModels.NguonGoc;
import domainModels.NhaCungCap;
import domainModels.SanPham;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Admin
 */
public class ChiTietDoGoRepository {

    Session session = HibernatUtil.getFACTORY().openSession();
    Transaction transaction = session.getTransaction();

    public List<ChiTietDoGo> getListTu1Den3M() {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM ChiTietDoGo where GiaBan >= 1000000 AND GiaBan <= 3000000 AND TrangThai = 1");
            List<ChiTietDoGo> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    public List<ChiTietDoGo> getListTu3Den5M() {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM ChiTietDoGo where GiaBan >= 3000000 AND GiaBan <= 5000000 AND TrangThai = 1");
            List<ChiTietDoGo> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<ChiTietDoGo> getListTu5Den10M() {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM ChiTietDoGo where GiaBan >= 5000000 AND GiaBan <= 10000000 AND TrangThai = 1");
            List<ChiTietDoGo> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ChiTietDoGo> getListNhoHon1Trieu() {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM ChiTietDoGo where GiaBan < 1000000 AND TrangThai = 1");
            List<ChiTietDoGo> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    public List<ChiTietDoGo> getListLonHon10Trieu() {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM ChiTietDoGo where GiaBan > 10000000 AND TrangThai = 1");
            List<ChiTietDoGo> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    
    

    public List<Object[]> phanTrangCTDG(int i, int b) {
        
        try {
            Transaction transaction = session.getTransaction();

            Session session = HibernatUtil.getFACTORY().openSession();
            org.hibernate.query.Query q = session.createNativeQuery("SELECT A.Id,A.TenSP,B.Ten,C.TenDongSP,D.TenLoaiGo,\n"
                    + "E.TenNCC,F.QuocGia,G.DonViTinh,A.MoTa,A.SoLuong,\n"
                    + "A.GiaNhap,A.GiaBan FROM ChiTietDoGo A \n"
                    + "inner join SanPham B ON A.IdSanPham = B.Id\n"
                    + "inner join  LoaiSP C ON  A.IdLoaiSP = C.Id\n"
                    + "inner join  DongGo D ON A.IdDongGo = D.Id\n"
                    + "inner join  NhaCungCap E ON A.IdNhaCungCap = E.Id\n"
                    + "inner join  NguonGoc F ON A.IdNguonGoc = F.Id\n"
                    + "inner join  DonViTinh G ON A.IdDonViTinh = G.Id\n"
                    + "WHERE A.TrangThai = 1\n"
                    + "order by A.SoLuong asc\n"
                    + "OFFSET " + i + " ROWS \n"
                    + "FETCH NEXT " + b + " ROWS ONLY");

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
            org.hibernate.query.Query q = session.createNativeQuery("SELECT * FROM ChiTietDoGo A\n"
                    + "WHERE A.TrangThai = 1\n"
                    + "order by A.SoLuong asc");

            List<Object[]> list = q.getResultList();
            index = list.size();
            return index;
        } catch (Exception e) {
            return -1;
        }

    }

    public List<ChiTietDoGo> list() {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("from ChiTietDoGo where TrangThai = 1");
            List<ChiTietDoGo> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ChiTietDoGo> listtk(String ten) {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("from ChiTietDoGo where TrangThai = 1 and TenSP like :ten");
            q.setParameter("ten", "%" + ten + "%");
            List<ChiTietDoGo> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean add(ChiTietDoGo dg) {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            ChiTietDoGo v = new ChiTietDoGo();

            SanPham a = session.get(SanPham.class, dg.getIdSanPham().getId());
            LoaiSP b = session.get(LoaiSP.class, dg.getIdLoaiSP().getId());
            DongGo c = session.get(DongGo.class, dg.getIdDongGo().getId());
            NhaCungCap d = session.get(NhaCungCap.class, dg.getIdNhaCungCap().getId());
            NguonGoc f = session.get(NguonGoc.class, dg.getIdNguocGoc().getId());
            DonViTinh g = session.get(DonViTinh.class, dg.getIdDonViTinh().getId());

            v.setIdSanPham(a);
            v.setIdLoaiSP(b);
            v.setIdDongGo(c);
            v.setIdNhaCungCap(d);
            v.setIdNguocGoc(f);
            v.setIdDonViTinh(g);
            v.setTenSP(dg.getTenSP());
            v.setMoTa(dg.getMoTa());
            v.setTenSP(dg.getTenSP());
            v.setSoLuong(0);
            v.setGiaNhap(dg.getGiaNhap());
            v.setGiaBan(dg.getGiaBan());
            v.setTrangThai(1);

            session.getTransaction().begin();
            session.save(v);
            session.getTransaction().commit();
            session.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean update(ChiTietDoGo dg) {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            ChiTietDoGo v = session.get(ChiTietDoGo.class, dg.getId());

            SanPham a = session.get(SanPham.class, dg.getIdSanPham().getId());
            LoaiSP b = session.get(LoaiSP.class, dg.getIdLoaiSP().getId());
            DongGo c = session.get(DongGo.class, dg.getIdDongGo().getId());
            NhaCungCap d = session.get(NhaCungCap.class, dg.getIdNhaCungCap().getId());
            NguonGoc f = session.get(NguonGoc.class, dg.getIdNguocGoc().getId());
            DonViTinh g = session.get(DonViTinh.class, dg.getIdDonViTinh().getId());

            v.setTenSP(dg.getTenSP());
            v.setIdSanPham(a);
            v.setIdLoaiSP(b);
            v.setIdDongGo(c);
            v.setIdNhaCungCap(d);
            v.setIdNguocGoc(f);
            v.setIdDonViTinh(g);
            v.setMoTa(dg.getMoTa());
            v.setGiaNhap(dg.getGiaNhap());
            v.setGiaBan(dg.getGiaBan());

            session.getTransaction().begin();
            session.save(v);
            session.getTransaction().commit();
            session.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean delete(String id) {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            ChiTietDoGo v = session.get(ChiTietDoGo.class, id);

            v.setTrangThai(0);

            session.getTransaction().begin();
            session.save(v);
            session.getTransaction().commit();
            session.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean truSanPham(String id, int soLuong) {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            ChiTietDoGo ct = session.get(ChiTietDoGo.class, id);
            ct.setSoLuong(ct.getSoLuong() - soLuong);

            session.getTransaction().begin();
            session.save(ct);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean congSanPham(String id, int soLuong) {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            ChiTietDoGo ct = session.get(ChiTietDoGo.class, id);
            ct.setSoLuong(ct.getSoLuong() + soLuong);

            session.getTransaction().begin();
            session.save(ct);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean updateSLSanPham(String id, int soLuong) {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            ChiTietDoGo ct = session.get(ChiTietDoGo.class, id);
            ct.setSoLuong(soLuong);

            session.getTransaction().begin();
            session.save(ct);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static void main(String[] args) {
        ChiTietDoGoRepository ctRepo = new ChiTietDoGoRepository();
//        ctRepo.truSanPham("4771CF52-B40D-4A93-B0EB-13DE20BA0F2E", 1);

        System.out.println(ctRepo.getListNhoHon1Trieu().toString());
    }

}
