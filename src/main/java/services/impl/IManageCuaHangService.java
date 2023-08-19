/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.CuaHang;
import java.util.List;
import viewModel.ViewModelCuaHang;

/**
 *
 * @author Phuong Bi
 */
public interface IManageCuaHangService {

    List<ViewModelCuaHang> getAll();
    
    List<ViewModelCuaHang> cuaHangNgungHoatDong();

    List<ViewModelCuaHang> getListSPByName(String ten);

    boolean add(CuaHang c);

    boolean update(CuaHang c);

    boolean delete(CuaHang c);

    List<ViewModelCuaHang> getListSP(int i, int b);

    int row();
    
    boolean troLai(String id);
}
