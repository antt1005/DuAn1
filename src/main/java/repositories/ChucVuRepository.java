/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.ChucVu;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.LockOptions;

import org.hibernate.Session;

import org.hibernate.query.Query;

import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Phuong Bi
 */
public class ChucVuRepository {

    public List<ChucVu> getAll() {

        try {

            Session se = HibernatUtil.getFACTORY().openSession();
            
            Query q = se.createQuery("FROM ChucVu WHERE TrangThai = 1 order by Ma desc");
            
            List<ChucVu> list = q.getResultList();
            
            return list;
            

        } catch (Exception e) {
            return null;
        }
    }

    public List<ChucVu> tkTheoTen(String ten) {

        try {

            Session se = HibernatUtil.getFACTORY().openSession();
            
            Query q = se.createQuery("FROM ChucVu WHERE TrangThai = 1 AND TenChucVu like :ten");
            
            q.setParameter("ten", "%" + ten + "%");
            
            List<ChucVu> list = q.getResultList();
            
            return list;

            
        } catch (Exception e) {
            return null;
        }

    }

    public int getMaMax() {

        Session se = HibernatUtil.getFACTORY().openSession();
        
        String maLonNhat = null;
        
        Query q = se.createQuery("select A.Ma From ChucVu A Where TrangThai = 1");
        
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

    public boolean add(ChucVu cv) {

        String ma = String.valueOf(getMaMax() + 1);
        try {
            
            
            Session se = HibernatUtil.getFACTORY().openSession();

            ChucVu cvu = new ChucVu();
            
            cvu.setMa(ma);
            
            cvu.setTenChucVu(cv.getTenChucVu());
            
            cvu.setTrangThai(1);

            se.getTransaction().begin();
            se.save(cvu);
            se.getTransaction().commit();
            se.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(ChucVu c) {

        
        try {
            Session se = HibernatUtil.getFACTORY().openSession();
            
            ChucVu cv = se.get(ChucVu.class, c.getId());
            
            cv.setTenChucVu(c.getTenChucVu());
            
            cv.setTrangThai(1);

            se.getTransaction().begin();
            se.save(cv);
            se.getTransaction().commit();
            se.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(ChucVu c) {

        try {
            Session se = HibernatUtil.getFACTORY().openSession();
            
            ChucVu cv = se.get(ChucVu.class, c.getId());
            
            cv.setTrangThai(0);

            se.getTransaction().begin();
            se.save(cv);
            se.getTransaction().commit();
            se.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        ChucVuRepository chucVuRepository = new ChucVuRepository();
        ChucVu cv = new ChucVu();
        cv.setId("5E6AA4AC-3549-4C7E-9777-1A868A578EF3");
        // cv.setTenChucVu("hhhhhh");
        System.out.println(chucVuRepository.delete(cv));

    }
}
