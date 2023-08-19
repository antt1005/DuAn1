/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.DongGo;
import java.util.ArrayList;
import java.util.List;
import repositories.DongGoRepository;
import services.impl.IManageDongGoService;
import viewModel.ViewModelDongGo;

/**
 *
 * @author ktkha
 */
public class DongGoService implements IManageDongGoService {

    private DongGoRepository i = new DongGoRepository();

    @Override
    public List<ViewModelDongGo> getListDongGo() {
        List<DongGo> dg = i.getListDongGo();
        try {
            List<ViewModelDongGo> dongGos = new ArrayList<>();
            for (DongGo d : dg) {
                ViewModelDongGo v = new ViewModelDongGo();
                v.setId(d.getId());
                v.setMa(d.getMa());
                v.setTenLoaiGo(d.getTenLoaiGo());
                v.setTrangThai(String.valueOf(d.getTrangThai()));
                dongGos.add(v);
            }
            return dongGos;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelDongGo> getDongGoByName(String ten) {
        List<DongGo> dg = i.getDongGoByName(ten);
        try {
            List<ViewModelDongGo> dongGos = new ArrayList<>();
            for (DongGo d : dg) {
                ViewModelDongGo v = new ViewModelDongGo();
                v.setId(d.getId());
                v.setMa(d.getMa());
                v.setTenLoaiGo(d.getTenLoaiGo());
                v.setTrangThai(String.valueOf(d.getTrangThai()));
                dongGos.add(v);
            }
            return dongGos;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(DongGo dg) {
        try {
            return i.add(dg);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(DongGo dg) {
        try {
            return i.update(dg);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(DongGo dg) {
        try {
            return i.delete(dg);
        } catch (Exception e) {
            return false;
        }
    }

}
