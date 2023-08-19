/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.HoaDon;
import java.util.List;
import viewModel.ViewModelHoadon;

/**
 *
 * @author PC
 */
public interface IManageHoaDonService {

    List<ViewModelHoadon> getListHoaDon(int i, int b);

    int row();

    List<ViewModelHoadon> loc(int Tranngthai);

    boolean add(HoaDon hd);

    boolean delete(String id);
    
    int soLuong();
    
     List<ViewModelHoadon> getListHD();
     
     List<ViewModelHoadon> timKiemMa(String ma);
}
