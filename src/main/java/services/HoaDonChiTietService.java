/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.HoaDonChiTiet;
import java.util.ArrayList;
import java.util.List;
import repositories.ChiTietHoaDonRepository;
import services.impl.IManageChiTietHoaDonBanHang;
import viewModel.ViewModelHoaDonChiTietBanHang;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietService implements IManageChiTietHoaDonBanHang {
    
    private ChiTietHoaDonRepository i = new ChiTietHoaDonRepository();
    
    @Override
    public List<ViewModelHoaDonChiTietBanHang> list(String id) {
        try {
            List<ViewModelHoaDonChiTietBanHang> list = new ArrayList<>();
            List<HoaDonChiTiet> lists = i.getList(id);
            for (HoaDonChiTiet a : lists) {
                ViewModelHoaDonChiTietBanHang b = new ViewModelHoaDonChiTietBanHang();
                b.setIdsp(a.getIdChiTietDoGo().getId());
                b.setIdhd(a.getIdHoaDon().getId());
                b.setTen(a.getIdChiTietDoGo().getTenSP());
                b.setSoluong(a.getSoLuong());
                b.setDonGia(a.getDonGia());
                
                list.add(b);
            }
            
            return list;
        } catch (Exception e) {
            return null;
        }
        
    }
    
    @Override
    public boolean add(HoaDonChiTiet hd) {
        try {
            return i.add(hd);
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean update(HoaDonChiTiet hd) {
        try {
            return i.update(hd);
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean delete(String idsp , String idhd) {
        try {
            return i.delete(idsp, idhd);
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean updatesol(String id, int soluong) {
        try {
            return i.updateSLSP(id, soluong);
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public int TongTien(String id) {
        return i.TongTien(id);
    }
    
}
