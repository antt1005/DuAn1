/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.NhanVien;
import java.util.ArrayList;
import java.util.List;
import repositories.NhanVienRepository;
import services.impl.IManageNhanVienService;
import viewModel.ViewModelNhanVien;

/**
 *
 * @author Phuong Bi
 */
public class NhanVienService implements IManageNhanVienService{

    private NhanVienRepository nhan = new NhanVienRepository();
    
    @Override
    public List<ViewModelNhanVien> getAll(int a, int b) {
        
            List<ViewModelNhanVien> list = new ArrayList<>();
            List<Object[]> sps = nhan.getAll(a, b);
             for (Object[] sp : sps) {
                 ViewModelNhanVien v = new ViewModelNhanVien();
                 v.setId(sp[0].toString());
                 
                 v.setMa(sp[1].toString());
                 
                 v.setHoTen(sp[2].toString());
                 
                 v.setSdt(sp[3].toString());
                 
                 v.setDiaChi(sp[4].toString());
                 
                 v.setNgaySinh(sp[5].toString());
                 
                 v.setEmail(sp[6].toString());
                 
                 v.setMatKhau(sp[7].toString());
                 
                 v.setIdCV(sp[8].toString());
                 
                 v.setIdCH(sp[9].toString());
                 
                 list.add(v);
             }
            return list;
      
    }

    @Override
    public List<ViewModelNhanVien> listtk(String Ten) {
         try {
            List<ViewModelNhanVien> list = new ArrayList<>();
            List<NhanVien> sps = nhan.listtk(Ten);
            for (NhanVien sp : sps) {
                list.add(new ViewModelNhanVien(sp.getId(),
                        
                        sp.getMa(),sp.getHoTen(),
                        
                        sp.getSdt(),
                        
                        sp.getDiaChi(),
                        
                        sp.getNgaySinh()+ "",
                        
                        sp.getIdCuaHang().getTenCuaHang(),sp.getIdChucVu().getTenChucVu(), sp.getMatKhau(),
                        
                        sp.getEmail()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(NhanVien nv) {
        
        try {
            return nhan.add(nv);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(NhanVien nv) {
        
        try {
            return nhan.update(nv);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        
        try {
            return nhan.delete(id);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int getRow(int b, int c) {
        
        try {
            return nhan.getRow(b, c);
        } catch (Exception e) {
            return 0;
        }
    }
    public static void main(String[] args) {
        NhanVienService i = new NhanVienService();
//        for (ViewModelNhanVien arg : i.getAll(0, 5)) {
//            System.out.println(arg.toString());
//        }
            List<ViewModelNhanVien> list = i.getListNV();
            for (ViewModelNhanVien a : list) {
                System.out.println(a.toString());
        }
        
    }

    @Override
    public List<ViewModelNhanVien> getListNV() {
        
        try {
            List<ViewModelNhanVien> list = new ArrayList<>();
            List<NhanVien> sps = nhan.getListNV();
            
            for (NhanVien sp : sps) {
                
                ViewModelNhanVien x = new ViewModelNhanVien();
                
                x.setId(sp.getId());
                
                x.setMa(sp.getMa());
                
                x.setHoTen(sp.getHoTen());
                
                x.setSdt(sp.getSdt());
                
                x.setDiaChi(sp.getDiaChi());
                
                x.setNgaySinh(String.valueOf(sp.getNgaySinh()));
                
                x.setIdCH(String.valueOf(sp.getIdCuaHang().getTenCuaHang()));
                
                x.setIdCV(String.valueOf(sp.getIdChucVu().getTenChucVu()));
                
                x.setMatKhau(sp.getMatKhau());
                
                x.setEmail(sp.getEmail());
                
                list.add(x);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    
    
}
