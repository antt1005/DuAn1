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
 * @author ktkha
 */
public interface IManageDoiMatKhauService {
    
    List<ViewModelNhanVien> getNVbyId(String id);

    String getMKById(String id);

    boolean updateMKById(String id, String mk);

}
