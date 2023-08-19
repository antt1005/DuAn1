/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.NhanVien;
import java.util.List;
import viewModel.ViewModelNhanVien;

/**
 *
 * @author Phuong Bi
 */
public interface IManageNhanVienService {

    List<ViewModelNhanVien> getAll(int a, int b);

    int getRow(int b, int c);

    List<ViewModelNhanVien> listtk(String Ten);
    
    List<ViewModelNhanVien> getListNV();

    boolean add(NhanVien nv);

    boolean update(NhanVien nv);

    boolean delete(String id);
}
