/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.NhaCungCap;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TemporalType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Phuong Bi
 */
public class NhaCungCapRepository {

    Session session = HibernatUtil.getFACTORY().openSession();
    Transaction transaction = session.getTransaction();

    public List<Object[]> getListSP(int i, int b) {
        try {
            Transaction transaction = session.getTransaction();

            Session session = HibernatUtil.getFACTORY().openSession();
            org.hibernate.query.Query q = session.createNativeQuery(""
                    + "SELECT * FROM NhaCungCap WHERE TrangThai = 1 \n"
                    + "  ORDER BY Ma desc \n"
                    + "  OFFSET " + i + " ROWS \n"
                    + "  FETCH NEXT " + b + " ROWS ONLY");

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
            org.hibernate.query.Query q = session.createNativeQuery(""
                    + "SELECT * FROM NhaCungCap"
                    + "  WHERE TrangThai = 1\n"
                    + "  ORDER BY Ma asc ");

            List<Object[]> list = q.getResultList();
            index = list.size();
            return index;
        } catch (Exception e) {
            return -1;
        }

    }

    public List<NhaCungCap> getAll() {
        try {
            Session se = HibernatUtil.getFACTORY().openSession();
            Query q = se.createQuery(" from NhaCungCap where TrangThai = 1");
            List<NhaCungCap> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<NhaCungCap> getNhaCungCap(String ten) {
        try {
            Session se = HibernatUtil.getFACTORY().openSession();
            Query q = se.createQuery("FROM NhaCungCap WHERE TrangThai = 1 AND TenNCC like :ten");
            q.setParameter("ten", "%" + ten + "%");
            List<NhaCungCap> list = q.getResultList();
            return list;

        } catch (Exception e) {
            return null;
        }
    }

    public int getMaLonNhat() {

        Session se = HibernatUtil.getFACTORY().openSession();
        String maLonNhat = null;
        Query q = se.createQuery("select A.Ma From NhaCungCap A Where TrangThai = 1");
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

    public boolean add(NhaCungCap ncc) {
        String getMa = String.valueOf(getMaLonNhat() + 1);
        try {
            Session se = HibernatUtil.getFACTORY().openSession();

            NhaCungCap nc = new NhaCungCap();
            nc.setMa(getMa);
            nc.setTenNCC(ncc.getTenNCC());
            nc.setDiaChi(ncc.getDiaChi());
            nc.setSdt(ncc.getSdt());
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

    public boolean update(NhaCungCap ncc) {
        try {
            Session se = HibernatUtil.getFACTORY().openSession();

            NhaCungCap n = se.get(NhaCungCap.class, ncc.getId());
            n.setTenNCC(ncc.getTenNCC());
            n.setDiaChi(ncc.getDiaChi());
            n.setSdt(ncc.getSdt());
            n.setTrangThai(1);

            se.getTransaction().begin();
            se.save(n);
            se.getTransaction().commit();
            se.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(NhaCungCap ncc) {
        try {
            Session se = HibernatUtil.getFACTORY().openSession();

            NhaCungCap n = se.get(NhaCungCap.class, ncc.getId());
            n.setTrangThai(0);

            se.getTransaction().begin();
            se.save(n);
            se.getTransaction().commit();
            se.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        NhaCungCapRepository n = new NhaCungCapRepository();
        NhaCungCap ncc = new NhaCungCap();

//        ncc.setTenNCC("Nguyen E");
        //System.out.println(n.getNhaCungCap(ncc));
        //  ncc.setId("DE239AFE-3CE4-4E85-B790-6831793D6FB5");
        ncc.setTenNCC("Nguyen E");
        ncc.setDiaChi("Vinh Phuc");
        ncc.setSdt("0984676565");
        n.add(ncc);

        //System.out.println(n.add(ncc));
    }
}
