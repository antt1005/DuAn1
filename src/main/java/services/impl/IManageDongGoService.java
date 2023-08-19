/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import domainModels.DongGo;
import java.util.List;
import viewModel.ViewModelDongGo;

/**
 *
 * @author ktkha
 */
public interface IManageDongGoService{
    List<ViewModelDongGo> getListDongGo();
    
    List<ViewModelDongGo> getDongGoByName(String ten);
    
    boolean add(DongGo dg);
    
    boolean update(DongGo dg);
    
    boolean delete(DongGo dg);
}
