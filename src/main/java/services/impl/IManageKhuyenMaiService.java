/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.KhuyenMai;
import java.util.List;
import viewModel.KhuyenMaiViewModel;

/**
 *
 * @author ktkha
 */
public interface IManageKhuyenMaiService {
    List<KhuyenMaiViewModel> getListKMAll();
    
    List<KhuyenMaiViewModel> getListKMCon();
    
    List<KhuyenMaiViewModel> getListKMHet();
    
    List<KhuyenMaiViewModel> getListKMByDate(String ten);
    
    List<KhuyenMaiViewModel> getListKMByDateContg(String ten);
    
    boolean add(KhuyenMai km);
    
    boolean update(KhuyenMai km);
    
    boolean delete(KhuyenMai km);
}
