/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import java.util.List;
import viewModel.ChiTietDoGoViewModel;

/**
 *
 * @author ktkha
 */
public interface IManageTimKiemCTSPSService {
    
    List<ChiTietDoGoViewModel> timKiemPhanTrang(int i, int b, String ten);
    
    int row(String ten);
    
}
