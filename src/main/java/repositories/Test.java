/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author ktkha
 */
public class Test {

    public int getListGanHet() {
        try {
            int a = -1;
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();
            Query q = session.createQuery("select COUNT(A.SoLuong),COUNT(A.SoLuong) from ChiTietDoGo A where A.SoLuong <= 10 And A.TrangThai = 1");
            List<Object[]> list = q.getResultList();
            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }
            return a;
        } catch (Exception e) {
            return -1;
        }
    }

    public int getListHet() {
        try {
            int a = -1;
            Session session = HibernatUtil.getFACTORY().openSession();
            Transaction transaction = session.getTransaction();
            Query q = session.createQuery("select COUNT(A.SoLuong),COUNT(A.SoLuong) from ChiTietDoGo A where A.SoLuong = 0 And A.TrangThai = 1");
            List<Object[]> list = q.getResultList();
            for (Object[] o : list) {
                a = Integer.parseInt(o[0].toString());
            }
            return a;
        } catch (Exception e) {
            return -1;
        }
    }

    public void test() {
        Session session = HibernatUtil.getFACTORY().openSession();
        NativeQuery query = session.createNativeQuery("Select Id,Ma from SanPham ");
        List<Object[]> list = query.setMaxResults(3).getResultList();
        for (Object[] objects : list) { 
            System.out.println(objects[0].toString() + " " + objects[1].toString());
        }
    }

    public static void main(String[] args) {
        Test a = new Test();
//        int b = a.getListHet();
//        System.out.println(b);
            a.test();
    }

}
