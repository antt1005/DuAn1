/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.LichSuNhap;
import java.util.List;
import viewModel.ViewModelNhapView;

/**
 *
 * @author Admin
 */
public interface IManagerNhapXuat {

    List<ViewModelNhapView> getList();

    boolean add(LichSuNhap ls);
}
