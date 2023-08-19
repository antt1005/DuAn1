/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories.test;

import domainModels.DongGo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import repositories.DongGoRepository;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author PC
 */
public class DongGoRepositorytesst {

    public List<DongGo> getListDongGo() {
        
        
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession(); //Ket noi DB thuc hien hien truy van
            Query q = session.createQuery("FROM DongGo WHERE TrangThai = 1"); //Tao cau truy van lay du lieu tu bang dong go
            List<DongGo> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<DongGo> getDongGoByName(String ten) {
        
        
        
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM DongGo WHERE TrangThai = 1 AND TenLoaiGo like :ten ");
            q.setParameter("ten", "%" + ten + "%");
            List<DongGo> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public int getMaxMa() { //Lấy mã lớn nhất + 1 => Tự động tăng
        
        
        
        Session session = HibernatUtil.getFACTORY().openSession();
        String soMaLonNhat = null;
        Query q = session.createQuery(" select A.Ma From DongGo A Where TrangThai = 1 ");
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

    public boolean add(DongGo dg) {
        
        
        
        
        String getMaxMa = String.valueOf(getMaxMa() + 1);
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            DongGo dongGo = new DongGo();
            dongGo.setMa(getMaxMa);
            dongGo.setTenLoaiGo(dg.getTenLoaiGo());
            dongGo.setTrangThai(1);

            session.getTransaction().begin();
            session.save(dongGo);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(DongGo dg) {
        
        
        
        String getMaxMa = String.valueOf(getMaxMa() + 1);
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            DongGo dongGo = session.get(DongGo.class, dg.getId());
            dongGo.setTenLoaiGo(dg.getTenLoaiGo());

            session.getTransaction().begin();
            session.save(dongGo);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(DongGo dg) {
        
        
        
        String getMaxMa = String.valueOf(getMaxMa() + 1);
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            DongGo dongGo = session.get(DongGo.class, dg.getId());
            dongGo.setTrangThai(0);

            session.getTransaction().begin();
            session.save(dongGo);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        
        
        
        
        DongGoRepository dg = new DongGoRepository();
        List<DongGo> dongGo = dg.getListDongGo();
        for (DongGo d : dongGo) {
            System.out.println(d.toString());
        }
    }
}
