/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.ChiTietDoGo;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author ktkha
 */
public class TimKiemCTSPRepository {
    
    Session session = HibernatUtil.getFACTORY().openSession();
    Transaction transaction = session.getTransaction();
    
    public List<Object[]> timKiemPhanTrang(int i, int b, String Ten) {
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
                    + "WHERE A.TrangThai = 1 And A.TenSP LIKE :Ten \n"
                    + "order by A.SoLuong asc\n"
                    + "OFFSET " + i + " ROWS \n"
                    + "FETCH NEXT " + b + " ROWS ONLY"
            );
            q.setParameter("Ten", "%" + Ten + "%");
            List<Object[]> list = q.getResultList();
            session.close();
            
            return list;
        } catch (Exception e) {
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
    
    public static void main(String[] args) {
        TimKiemCTSPRepository a = new TimKiemCTSPRepository();
        for (Object[] arg : a.timKiemPhanTrang(0, 3, "B")) {
            System.out.println(arg[0] + "    " + arg[1]);
        }
        int row = a.getRowTimKiem("B");
        System.out.println(row);
    }
    
}
