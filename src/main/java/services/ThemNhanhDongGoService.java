/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.DongGo;
import repositories.ThemNhanhDongGoRepository;
import services.impl.IManageThemNhanhDongGoService;

/**
 *
 * @author ktkha
 */
public class ThemNhanhDongGoService implements IManageThemNhanhDongGoService {

    private ThemNhanhDongGoRepository sv = new ThemNhanhDongGoRepository();

    @Override
    public boolean add(DongGo dg) {
        try {
            return sv.add(dg);
        } catch (Exception e) {
            return false;
        }
    }

}
