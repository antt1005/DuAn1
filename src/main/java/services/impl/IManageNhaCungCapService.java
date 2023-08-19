/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.NhaCungCap;
import java.util.List;
import viewModel.ViewModelNhaCungCap;

/**
 *
 * @author Phuong Bi
 */
public interface IManageNhaCungCapService {
    
    List<ViewModelNhaCungCap> getListSP(int i, int b);
    
    int row();

    List<ViewModelNhaCungCap> getAll();

    List<ViewModelNhaCungCap> getNhaCungCap(String ten);

    boolean add(NhaCungCap s);

    boolean update(NhaCungCap s);

    boolean delete(NhaCungCap s);
}
