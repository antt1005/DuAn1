/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.LoaiSP;
import repositories.ThemNhanhLoaiSPRepository;
import services.impl.IManageThemNhanhLoaiSPService;

/**
 *
 * @author ktkha
 */
public class ThemNhanhLoaiSPService implements IManageThemNhanhLoaiSPService{
    
    private ThemNhanhLoaiSPRepository t = new ThemNhanhLoaiSPRepository();

    @Override
    public boolean add(LoaiSP sp) {
        try {
            return t.add(sp);
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
}
