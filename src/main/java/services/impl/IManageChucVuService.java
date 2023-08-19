/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.ChucVu;
import java.util.List;
import viewModel.ViewModelChucVu;

/**
 *
 * @author Phuong Bi
 */
public interface IManageChucVuService {
    
    List<ViewModelChucVu> getAll();

    List<ViewModelChucVu> getListSPByName(String ten);

    boolean add(ChucVu c);

    boolean update(ChucVu c);

    boolean delete(ChucVu c);
    
}
