/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.DonViTinh;
import java.util.ArrayList;
import java.util.List;
import repositories.DonViTinhRepository;
import services.impl.IManageDonViTinhService;
import viewModel.ViewModelDonViTinh;

/**
 *
 * @author Admin
 */
public class DonViTinhService implements IManageDonViTinhService {

    private DonViTinhRepository dvrp = new DonViTinhRepository();

    @Override
    public List<ViewModelDonViTinh> getListDVT() {
        List<DonViTinh> sp = dvrp.getListDVT();
        try {
            List<ViewModelDonViTinh> sanPhams = new ArrayList<>();
            for (DonViTinh x : sp) {
                ViewModelDonViTinh v = new ViewModelDonViTinh();
                v.setId(x.getId());
                v.setMa(x.getMa());
                v.setDonViTinh(x.getDonViTinh());
                v.setTrangThai(String.valueOf(x.getTrangThai()));
                sanPhams.add(v);
            }
            return sanPhams;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelDonViTinh> getListDVTByName(String ten) {
        List<DonViTinh> dg = dvrp.getListDVTByName(ten);
        try {
            List<ViewModelDonViTinh> dongGos = new ArrayList<>();
            for (DonViTinh d : dg) {
                ViewModelDonViTinh v = new ViewModelDonViTinh();
                v.setId(d.getId());
                v.setMa(d.getMa());
                v.setDonViTinh(d.getDonViTinh());
                v.setTrangThai(String.valueOf(d.getTrangThai()));
                dongGos.add(v);
            }
            return dongGos;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(DonViTinh s) {
        try {
            return dvrp.add(s);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(DonViTinh s) {
        try {
            return dvrp.update(s);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(DonViTinh s) {
        try {
            return dvrp.delete(s);
        } catch (Exception e) {
            return false;
        }
    }
    public static void main(String[] args) {
         DonViTinhService dvtrp = new DonViTinhService();
         List<ViewModelDonViTinh> dvt = dvtrp.getListDVT();
         for (ViewModelDonViTinh viewModelSanPham : dvt) {
             System.out.println(viewModelSanPham.toString());
        }
    }

}
