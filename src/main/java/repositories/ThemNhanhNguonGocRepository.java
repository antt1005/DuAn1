/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.NguonGoc;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author ktkha
 */
public class ThemNhanhNguonGocRepository {
    
       public int getMaLonNhat() {

        Session se = HibernatUtil.getFACTORY().openSession();
        String maLonNhat = null;
        Query q = se.createQuery("select A.Ma From NguonGoc A Where TrangThai = 1");
        List<String> i = q.getResultList();
        if (i.isEmpty()) {
            return 0;
        } else {
            List<Integer> c = new ArrayList<>();
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
     public List<NguonGoc> getTenQuocGia(String ten) {
        try {
            Session se = HibernatUtil.getFACTORY().openSession();
            Query q = se.createQuery("FROM NguonGoc WHERE TrangThai = 1 AND QuocGia like :ten");
            q.setParameter("ten", "%" + ten + "%");
            List<NguonGoc> list = q.getResultList();
            return list;

        } catch (Exception e) {
            return null;
        }
    }

    public boolean add(NguonGoc ng) {
        String getMa = String.valueOf(getMaLonNhat() + 1);
        try {
            Session se = HibernatUtil.getFACTORY().openSession();

            NguonGoc nc = new NguonGoc();
            nc.setMa(getMa);
            nc.setQuocGia(ng.getQuocGia());
            
            nc.setTrangThai(1);

            se.getTransaction().begin();
            se.save(nc);
            se.getTransaction().commit();
            se.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
