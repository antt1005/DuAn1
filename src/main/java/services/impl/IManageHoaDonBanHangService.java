/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.HoaDon;
import java.math.BigDecimal;
import java.util.List;
import viewModel.ViewModelHoaDonBanHang;

/**
 *
 * @author Admin
 */
public interface IManageHoaDonBanHangService {
    List<ViewModelHoaDonBanHang> getList();
    
    boolean add(HoaDon hd);
    
    boolean update(String id, BigDecimal thanhTien ,String idkh , String idKM);
    
    int maxma();
    
}
