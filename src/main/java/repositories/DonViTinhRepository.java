/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.DonViTinh;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Admin
 */
public class DonViTinhRepository {

    public List<DonViTinh> getListDVT() {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM DonViTinh WHERE TrangThai = 1");
            List<DonViTinh> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    public List<DonViTinh> getListDVTByName(String ten) {
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            Query q = session.createQuery("FROM DonViTinh WHERE TrangThai = 1 AND DonViTinh like :ten ");
            q.setParameter("ten", "%" + ten + "%");
            List<DonViTinh> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public int getMaxMa() { //Lấy mã lớn nhất + 1 => Tự động tăng
        Session session = HibernatUtil.getFACTORY().openSession();
        String soMaLonNhat = null;
        Query q = session.createQuery(" select A.Ma From DonViTinh A Where TrangThai = 1 ");
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

    public boolean add(DonViTinh dv) {
        String getMaxMa = String.valueOf(getMaxMa() + 1);
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            DonViTinh donvt = new DonViTinh();
            donvt.setMa(getMaxMa);
            donvt.setDonViTinh(dv.getDonViTinh());
            donvt.setTrangThai(1);

            session.getTransaction().begin();
            session.save(donvt);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(DonViTinh dg) {
        String getMaxMa = String.valueOf(getMaxMa() + 1);
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            DonViTinh donvt = session.get(DonViTinh.class, dg.getId());
            donvt.setDonViTinh(dg.getDonViTinh());

            session.getTransaction().begin();
            session.save(donvt);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(DonViTinh dg) {
        String getMaxMa = String.valueOf(getMaxMa() + 1);
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            DonViTinh donvt = session.get(DonViTinh.class, dg.getId());
            donvt.setTrangThai(0);

            session.getTransaction().begin();
            session.save(donvt);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        DonViTinhRepository dg = new DonViTinhRepository();
//        List<DonViTinh> dongGo = dg.getListSP();
//        for (DonViTinh d : dongGo) {
//            System.out.println(d.toString());
//        }

        DonViTinh dongGo = new DonViTinh();
        dongGo.setId("A705A262-2E7D-4A98-984A-47B28A8232B9");
        dongGo.setDonViTinh("bo");
        dg.delete(dongGo);
    }
}
