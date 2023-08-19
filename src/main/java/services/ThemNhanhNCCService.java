/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.NhaCungCap;
import repositories.ThemNhanhNCCRepository;
import services.impl.IManageThemNhanhNCCService;

/**
 *
 * @author ktkha
 */
public class ThemNhanhNCCService implements IManageThemNhanhNCCService{
    
    private ThemNhanhNCCRepository a = new ThemNhanhNCCRepository();

    @Override
    public boolean add(NhaCungCap ncc) {
        try {
            return a.add(ncc);
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
}
