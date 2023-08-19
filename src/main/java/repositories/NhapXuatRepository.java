/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.ChiTietDoGo;
import domainModels.HoaDon;
import domainModels.LichSuNhap;
import domainModels.SanPham;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Admin
 */
public class NhapXuatRepository {

    public List<LichSuNhap> getList() {

        Session session = HibernatUtil.getFACTORY().openSession();
        Query q = session.createQuery("from LichSuNhap order by NgayNhap desc");
        List<LichSuNhap> list = q.getResultList();
        return list;

    }

    public boolean add(LichSuNhap ls) {
        java.util.Date date = java.util.Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String now = format.format(date);
        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            LichSuNhap i = new LichSuNhap();

            ChiTietDoGo sp = session.get(ChiTietDoGo.class, ls.getIdSpNhap().getId());
            i.setIdSpNhap(sp);
            i.setSoLongNhap(ls.getSoLongNhap());
            i.setNgayNhap(Date.valueOf(now));
            i.setTrangthai(1);
            i.setTongTienNhap(ls.getTongTienNhap());

            session.getTransaction().begin();
            session.save(i);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        NhapXuatRepository i = new NhapXuatRepository();
        for (LichSuNhap a : i.getList()) {
            System.out.println(a.toString());
        }
    }
}
