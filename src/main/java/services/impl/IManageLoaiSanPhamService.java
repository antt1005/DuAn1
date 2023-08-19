/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.LoaiSP;
import java.util.List;
import viewModel.ViewModelLoaiSanPham;

/**
 *
 * @author admin
 */
public interface IManageLoaiSanPhamService {
    List<ViewModelLoaiSanPham> getListLoaiSP();
    List<ViewModelLoaiSanPham> getListLoaiSPByName(String ten);
    
    boolean add(LoaiSP lsp);
    boolean update(LoaiSP lsp);
    boolean delete(LoaiSP lsp);
    
}
