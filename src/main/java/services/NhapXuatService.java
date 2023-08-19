/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.LichSuNhap;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import repositories.NhapXuatRepository;
import services.impl.IManagerNhapXuat;
import viewModel.ViewModelNhapView;

/**
 *
 * @author Admin
 */
public class NhapXuatService implements IManagerNhapXuat {

    private NhapXuatRepository i = new NhapXuatRepository();

    @Override
    public List<ViewModelNhapView> getList() {
        try {
            List<LichSuNhap> list = i.getList();
            List<ViewModelNhapView> b = new ArrayList<>();
            for (LichSuNhap c : list) {
                b.add(new ViewModelNhapView(c.getIdNhap(), c.getIdSpNhap().getId(), c.getIdSpNhap().getTenSP(),
                        c.getSoLongNhap(), c.getNgayNhap() + "", c.getTongTienNhap()));
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(LichSuNhap ls) {
        try {
            return i.add(ls);
        } catch (Exception e) {
            return false;
        }
    }

}
