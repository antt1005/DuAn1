/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.ChucVu;
import java.util.ArrayList;
import java.util.List;
import repositories.ChucVuRepository;
import services.impl.IManageChucVuService;
import viewModel.ViewModelChucVu;

/**
 *
 * @author Phuong Bi
 */
public class ChucVuSerivce implements IManageChucVuService {

    private ChucVuRepository ch = new ChucVuRepository();

    @Override
    public List<ViewModelChucVu> getAll() {
        List<ChucVu> cv = ch.getAll();
        try {
            List<ViewModelChucVu> chucVu = new ArrayList<>();
            for (ChucVu c : cv) {

                ViewModelChucVu v = new ViewModelChucVu();
                
                v.setId(c.getId());
                
                v.setMa(c.getMa());
                
                v.setTen(c.getTenChucVu());
                
                v.setTrangThai(String.valueOf(c.getTrangThai()));
                chucVu.add(v);

            }

            return chucVu;
            
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelChucVu> getListSPByName(String ten) {
        
        
        List<ChucVu> sp = ch.tkTheoTen(ten);
        try {
            List<ViewModelChucVu> chuc = new ArrayList<>();
            for (ChucVu x : sp) {
                ViewModelChucVu v = new ViewModelChucVu();
                v.setId(x.getId());
                
                v.setMa(x.getMa());
                
                v.setTen(x.getTenChucVu());
                
                v.setTrangThai(String.valueOf(x.getTrangThai()));
                
                chuc.add(v);

            }
            return chuc;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(ChucVu c) {
        
        try {
            return ch.add(c);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(ChucVu c) {
        
        try {
            return ch.update(c);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(ChucVu c) {
        
        try {
            return ch.delete(c);
        } catch (Exception e) {
            return false;
        }
    }

}
