/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.HoaDon;
import domainModels.HoaDonChiTiet;
import java.util.List;
import viewModel.ViewModelBAOHANHCHITIET;
import viewModel.ViewModelHDCTBH;
import viewModel.ViewModelHoaDonBaoHanh;
import viewModel.ViewModelHoaDonDeBaoHanh;

/**
 *
 * @author ktkha
 */
public interface IManageHoaDonDeBaoHanhService {
    List<ViewModelHoaDonDeBaoHanh> getListHD(String id);
    
    List<ViewModelHDCTBH> getListCTHD(String id);
    
    List<ViewModelHoaDonBaoHanh> getListHDBH();
    
    List<ViewModelBAOHANHCHITIET> getListCTHDbaoHanh(String id);
    
     boolean addHoadon(HoaDon hd);
     
     boolean updateHoadon(HoaDon hd);
          
     boolean add(HoaDonChiTiet hd);
     
     boolean update(HoaDonChiTiet hd);
     
     boolean delete(String idsp ,String idhd);
     
}
