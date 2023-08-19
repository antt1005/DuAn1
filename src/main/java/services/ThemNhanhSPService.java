/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.SanPham;
import repositories.ThemNhanhSPRepository;
import services.impl.IManageThemNhanhSPService;

/**
 *
 * @author ktkha
 */
public class ThemNhanhSPService implements IManageThemNhanhSPService{

    ThemNhanhSPRepository a = new ThemNhanhSPRepository();

    @Override
    public boolean add(SanPham s) {
        try {
            return  a.add(s);
        } catch (Exception e) {
            return false;
        }
        
    }
    
    

    
    
}
