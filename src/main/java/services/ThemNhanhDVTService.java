/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.DonViTinh;
import repositories.ThemNhanhDVTRepository;
import services.impl.IManageThemNhanhDVTService;

/**
 *
 * @author ktkha
 */
public class ThemNhanhDVTService implements IManageThemNhanhDVTService{
    
    private ThemNhanhDVTRepository a = new ThemNhanhDVTRepository();

    @Override
    public boolean add(DonViTinh dv) {
        try {
            return a.add(dv);
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
}
