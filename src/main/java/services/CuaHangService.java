/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.CuaHang;
import java.util.ArrayList;
import java.util.List;
import repositories.CuaHangRepository;
import services.impl.IManageCuaHangService;
import viewModel.ViewModelCuaHang;

/**
 *
 * @author Phuong Bi
 */
public class CuaHangService implements IManageCuaHangService {

    private CuaHangRepository ch = new CuaHangRepository();

    @Override
    public List<ViewModelCuaHang> getAll() {

        List<CuaHang> sp = ch.getAll();
        try {
            List<ViewModelCuaHang> sanPhams = new ArrayList<>();
            for (CuaHang x : sp) {
                
                ViewModelCuaHang v = new ViewModelCuaHang();
                
                v.setId(x.getId());
                
                v.setMa(x.getMa());
                
                v.setTenCuaHang(x.getTenCuaHang());
                
                v.setDiaChi(x.getDiaChi());
                
                v.setTrangThai(x.getTrangThai());
                
                sanPhams.add(v);
            }
            return sanPhams;

        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<ViewModelCuaHang> getListSPByName(String ten) {
        
        
         List<CuaHang> sp = ch.getListSPByName(ten);
        try {
            List<ViewModelCuaHang> sanPhams = new ArrayList<>();
            for (CuaHang x : sp) {
                
                ViewModelCuaHang v = new ViewModelCuaHang();
                
                v.setId(x.getId());
                
                v.setMa(x.getMa());
                
                v.setTenCuaHang(x.getTenCuaHang());
                
                v.setDiaChi(x.getDiaChi());
                
                v.setTrangThai(x.getTrangThai());
                
                sanPhams.add(v);
            }
            return sanPhams;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(CuaHang c) {
        
        try {
            
            return ch.add(c);
            
        } catch (Exception e) {
            
            return false;
        }
    }

    @Override
    public boolean update(CuaHang c) {
        
        try {
            return ch.update(c);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(CuaHang c) {
        
        try {
            
            return ch.delete(c);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ViewModelCuaHang> getListSP(int i, int b) {
        
            List<Object[]> list = ch.getListSP(i, b);

        List<ViewModelCuaHang> ncc = new ArrayList<>();
        for (Object[] a : list) {

            ViewModelCuaHang x = new ViewModelCuaHang();

            x.setId(a[0].toString());
            
            x.setMa(a[1].toString());
            
            x.setTenCuaHang(a[2].toString());
            
            x.setDiaChi(a[3].toString());
            
            x.setTrangThai(1);
          
            ncc.add(x);

        }
        return ncc;
    }

    @Override
    public int row() {
        
           try {
            return ch.getListSLRow();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<ViewModelCuaHang> cuaHangNgungHoatDong() {
        
          List<CuaHang> sp = ch.cuaHangNgungHoatDong();
        try {
            List<ViewModelCuaHang> sanPhams = new ArrayList<>();
            for (CuaHang x : sp) {
                
                ViewModelCuaHang v = new ViewModelCuaHang();
                
                v.setId(x.getId());
                
                v.setMa(x.getMa());
                
                v.setTenCuaHang(x.getTenCuaHang());
                
                v.setDiaChi(x.getDiaChi());
                
                v.setTrangThai(x.getTrangThai());
                
                sanPhams.add(v);
            }
            return sanPhams;

        } catch (Exception e) {
            return null;
        }
              
    }

    @Override
    public boolean troLai(String id) {
        
          try {
            ch.troLai(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
