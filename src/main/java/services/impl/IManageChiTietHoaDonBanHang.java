/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.HoaDonChiTiet;
import java.util.List;
import viewModel.ViewModelHoaDonChiTietBanHang;

/**
 *
 * @author Admin
 */
public interface IManageChiTietHoaDonBanHang {
    List<ViewModelHoaDonChiTietBanHang> list(String id);
    
    public boolean add(HoaDonChiTiet hd) ;
    
    public boolean update(HoaDonChiTiet hd) ;
    
    public boolean delete(String idsp , String idhd) ;
    
    public boolean updatesol(String id, int soluong) ;
    
    int TongTien(String id);
}
