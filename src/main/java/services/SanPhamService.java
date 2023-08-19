/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.HoaDon;
import domainModels.SanPham;
import java.util.ArrayList;
import java.util.List;
import repositories.SanPhamRepository;
import services.impl.IManageSanPhamService;
import viewModel.ViewModelSanPham;

/**
 *
 * @author ktkha
 */
public class SanPhamService implements IManageSanPhamService {

    private SanPhamRepository spRp = new SanPhamRepository();

    @Override
    public List<ViewModelSanPham> getListSP() {
        List<SanPham> sp = spRp.getListSP();
        try {
            List<ViewModelSanPham> sanPhams = new ArrayList<>();
            for (SanPham x : sp) {
                ViewModelSanPham v = new ViewModelSanPham();
                v.setId(x.getId());
                v.setMa(x.getMa());
                v.setTen(x.getTen());
                v.setTrangThai(String.valueOf(x.getTrangThai()));
                sanPhams.add(v);
            }
            return sanPhams;

        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<ViewModelSanPham> getListSPByName(String ten) {
        List<SanPham> sp = spRp.getListSPByName(ten);
        try {
            List<ViewModelSanPham> sanPhams = new ArrayList<>();
            for (SanPham x : sp) {
                ViewModelSanPham v = new ViewModelSanPham();
                v.setId(x.getId());
                v.setMa(x.getMa());
                v.setTen(x.getTen());
                v.setTrangThai(String.valueOf(x.getTrangThai()));
                sanPhams.add(v);
            }
            return sanPhams;

        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public boolean add(SanPham s) {
        try {
            return spRp.add(s);
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean update(SanPham s) {
        try {
            return spRp.update(s);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(SanPham s) {
        try {
            return spRp.delete(s);
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        SanPhamService spSV = new SanPhamService();
        List<ViewModelSanPham> sp = spSV.getListSP();
        for (ViewModelSanPham viewModelSanPham : sp) {
            System.out.println(viewModelSanPham.toString());
        }
    }

    @Override
    public List<ViewModelSanPham> getListSP(int i, int b) {
        List<Object[]> lsp = spRp.getListSP(i, b);

        List<ViewModelSanPham> sp = new ArrayList<>();
        for (Object[] a : lsp) {
            ViewModelSanPham x = new ViewModelSanPham();
            x.setId(a[0].toString());
            x.setMa(a[1].toString());
            x.setTen(a[2].toString());
            if (a[3].toString().equals("1")) {
                x.setTrangThai("Còn Hàng");
            } else {
                x.setTrangThai("Hết hàng");
            }
            sp.add(x);

        }

        return sp;

    }

    @Override
    public int row() {
        try {
            return spRp.getListSLRow();
        } catch (Exception e) {
            return 0;
        }
    }

}
