/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.KhachHang;
import java.util.List;
import viewModel.ViewModelHoadon;
import viewModel.ViewModelKhachHang;

/**
 *
 * @author Phuong Bi
 */
public interface IManageBaoHanhService {
    List<ViewModelKhachHang> getListKhachHang();
    
     List<ViewModelKhachHang> getTKSDT(String sdt);
}
