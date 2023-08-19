/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.NguonGoc;
import repositories.ThemNhanhNguonGocRepository;
import services.impl.IManageThemNhanhNguonGocService;

/**
 *
 * @author ktkha
 */
public class ThemNhanhNguonGocService implements IManageThemNhanhNguonGocService{
    
    private ThemNhanhNguonGocRepository SV = new ThemNhanhNguonGocRepository();

    @Override
    public boolean add(NguonGoc ng) {
        try {
            return SV.add(ng);
        } catch (Exception e) {
            return false;
        }
    }
    
}
