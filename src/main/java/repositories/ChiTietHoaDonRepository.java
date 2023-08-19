/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.ChiTietDoGo;
import domainModels.HoaDon;
import domainModels.HoaDonChiTiet;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDonRepository {
    
    public List<HoaDonChiTiet> getList(String id) {
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();
            Query q = session.createQuery("from HoaDonChiTiet A where A.IdHoaDon = '" + id + "' ");
            List<HoaDonChiTiet> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
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
            i.setDonGia(hd.getDonGia());
            i.setLuot(0);
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
            i.setDonGia(hd.getDonGia());
            
            session.getTransaction().begin();
            session.update(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean delete(String idsp ,String idhd) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            HoaDonChiTiet i = new HoaDonChiTiet();
            
            HoaDon a = session.get(HoaDon.class,idhd);
            
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
    
    public boolean updateSLSP(String id, int soluong) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            ChiTietDoGo i = session.get(ChiTietDoGo.class, id);
            i.setSoLuong(soluong);
            session.getTransaction().begin();
            session.update(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public int TongTien(String id) {
        int i = 0;
        
        Session session = HibernatUtil.getFACTORY().openSession();
        Query q = session.createQuery("select A.Id , Sum(B.DonGia) From HoaDon A JOIN HoaDonChiTiet B ON A.Id = B.IdHoaDon"
                + " Where A.Id = '" + id + "' Group by A.Id");
        List<Object[]> list = q.getResultList();
        if (list.isEmpty()) {
            i = 0;
        } else {
            Object[] hh = list.get(0);
            BigDecimal tongtien = (BigDecimal) hh[1];
            i = Integer.valueOf(tongtien.intValue());
        }
        return i;
    }
    
    public static void main(String[] args) {
        ChiTietHoaDonRepository i = new ChiTietHoaDonRepository();
        List<HoaDonChiTiet> list = i.getList("EBA6848C-7FEB-4CD4-8537-188B4631068C");
        for (HoaDonChiTiet hoaDonChiTiet : list) {
            System.out.println(hoaDonChiTiet.toString());
        }
    }
}
