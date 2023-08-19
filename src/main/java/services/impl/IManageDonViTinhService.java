/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.DonViTinh;
import java.util.List;
import viewModel.ViewModelDonViTinh;

/**
 *
 * @author Admin
 */
public interface IManageDonViTinhService {
    List<ViewModelDonViTinh> getListDVT();

    List<ViewModelDonViTinh> getListDVTByName(String ten);

    boolean add(DonViTinh s);

    boolean update(DonViTinh s);

    boolean delete(DonViTinh s);
}
