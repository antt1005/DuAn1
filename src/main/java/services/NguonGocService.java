/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.LoaiSP;
import domainModels.NguonGoc;
import domainModels.NhaCungCap;
import java.util.ArrayList;
import java.util.List;
import repositories.NguonGocRepository;
import services.impl.IManageNguonGocService;
import viewModel.ViewModelLoaiSanPham;
import viewModel.ViewModelNguonGoc;

/**
 *
 * @author Phuong Bi
 */
public class NguonGocService implements IManageNguonGocService{

    private NguonGocRepository ngr = new NguonGocRepository();

    @Override
    public List<ViewModelNguonGoc> getAll() {
        List<NguonGoc> ng = ngr.getAll();
        try {
            List<ViewModelNguonGoc> ngg = new ArrayList<>();
            for (NguonGoc n : ng) {
                ViewModelNguonGoc v = new ViewModelNguonGoc();
                v.setId(n.getId());
                v.setMa(n.getMa());
              
                v.setquocgia(n.getQuocGia());
                v.setTrangThai(String.valueOf(n.getTrangThai()));
                ngg.add(v);

            }
            return ngg;

        } catch (Exception e) {
            return null;
        }
    }


   @Override
    public boolean add(NguonGoc s) {
        try {
            return ngr.add(s);
        } catch (Exception e) {
            return false;
        }
    }

   @Override
    public boolean update(NguonGoc s) {
        try {
            return ngr.update(s);
        } catch (Exception e) {
            return false;
        }
    }

   @Override
    public boolean delete(NguonGoc s) {
        try {
            return ngr.delete(s);
        } catch (Exception e) {
            return false;
        }
    }
    
    public static void main(String[] args) {
        NguonGocService nc = new NguonGocService();
        
      
    }

    @Override
    public List<ViewModelNguonGoc> getTenQuocGia(String ten) {
         List<NguonGoc> ng = ngr.getTenQuocGia(ten);
        try {
            List<ViewModelNguonGoc> nguonGoc = new ArrayList<>();
            for (NguonGoc ngS : ng) {
                ViewModelNguonGoc v = new ViewModelNguonGoc();
                v.setId(ngS.getId());
                v.setMa(ngS.getMa());
                v.setquocgia(ngS.getQuocGia());;
                v.setTrangThai(String.valueOf(ngS.getTrangThai()));
               nguonGoc.add(v);
            }
            return nguonGoc;
        } catch (Exception e) {
            return null;
        }
    }



}
