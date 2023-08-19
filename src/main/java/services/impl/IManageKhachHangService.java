/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.KhachHang;
import java.util.List;
import viewModel.ViewModelKhachHang;

/**
 *
 * @author Admin
 */
public interface IManageKhachHangService {
    List<ViewModelKhachHang> getListKhachHang();
    List<ViewModelKhachHang> getListKhachHangByName(String ten);
    List<ViewModelKhachHang> getListKhachHangBysdt(String sdt);
    boolean add(KhachHang kh);
    boolean update(KhachHang kh);
    boolean delete(KhachHang kh);
}
