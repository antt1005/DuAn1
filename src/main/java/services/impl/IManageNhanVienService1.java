/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.NhanVien;
import java.util.List;
import viewModel.ViewModelNhanVien;
import viewModel.ViewModelNhanVien1;

/**
 *
 * @author Phuong Bi
 */
public interface IManageNhanVienService1 {

    List<ViewModelNhanVien1> getListNVDangLam();

    List<ViewModelNhanVien1> getListNVNghiLam();

    boolean add(NhanVien nv);

    boolean update(NhanVien nv);

    boolean troLai(String id);

    boolean delete(String id);

    List<ViewModelNhanVien1> getAll();

    List<ViewModelNhanVien1> listtk(String Ten);

    List<ViewModelNhanVien1> listtkSDT(String sdt);

    List<ViewModelNhanVien1> listtkChucVu(String idCV);

    List<ViewModelNhanVien1> listtkCuaHang(String idCH);

    List<ViewModelNhanVien1> getAll(int a, int b);

    int getRow(int b, int c);

}
