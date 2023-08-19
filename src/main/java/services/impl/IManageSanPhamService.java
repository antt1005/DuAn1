/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.SanPham;
import java.util.List;
import viewModel.ViewModelSanPham;

/**
 *
 * @author ktkha
 */
public interface IManageSanPhamService {
    
    List<ViewModelSanPham> getListSP(int i, int b);
    
    int row();

    List<ViewModelSanPham> getListSP();

    List<ViewModelSanPham> getListSPByName(String ten);

    boolean add(SanPham s);

    boolean update(SanPham s);

    boolean delete(SanPham s);
}
