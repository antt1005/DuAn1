/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.NguonGoc;

import java.util.List;
import viewModel.ViewModelNguonGoc;
import viewModel.ViewModelSanPham;

/**
 *
 * @author Phuong Bi
 */
public interface IManageNguonGocService {

    List<ViewModelNguonGoc> getAll();

    List<ViewModelNguonGoc> getTenQuocGia(String ten);

    boolean add(NguonGoc s);

    boolean update(NguonGoc s);

    boolean delete(NguonGoc s);
}
