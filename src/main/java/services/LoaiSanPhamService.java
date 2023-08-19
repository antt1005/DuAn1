/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.LoaiSP;
import java.util.ArrayList;
import java.util.List;
import repositories.LoaiSanPhamRepository;
import services.impl.IManageLoaiSanPhamService;
import viewModel.ViewModelLoaiSanPham;
import viewModel.ViewModelSanPham;

/**
 *
 * @author admin
 */
public class LoaiSanPhamService implements IManageLoaiSanPhamService{
    private LoaiSanPhamRepository lspRp = new LoaiSanPhamRepository();
    @Override
    public List<ViewModelLoaiSanPham> getListLoaiSP() {
        List<LoaiSP> lsp = lspRp.getListLoaiSp();
        try {
            List<ViewModelLoaiSanPham> loaisp = new ArrayList<>();
            for (LoaiSP loaiSp : lsp) {
                ViewModelLoaiSanPham v = new ViewModelLoaiSanPham();
                v.setId(loaiSp.getId());
                v.setMa(loaiSp.getMa());
                v.setTenDongSP(loaiSp.getTenDongSP());
                v.setTrangThai(String.valueOf(loaiSp.getTrangThai()));
                loaisp.add(v);
            }
            return loaisp;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelLoaiSanPham> getListLoaiSPByName(String ten) {
        List<LoaiSP> lsp = lspRp.getListSPByName(ten);
        try {
            List<ViewModelLoaiSanPham> loaisp = new ArrayList<>();
            for (LoaiSP loaiSP : lsp) {
                ViewModelLoaiSanPham v = new ViewModelLoaiSanPham();
                v.setId(loaiSP.getId());
                v.setMa(loaiSP.getMa());
                v.setTenDongSP(loaiSP.getTenDongSP());
                v.setTrangThai(String.valueOf(loaiSP.getTrangThai()));
               loaisp.add(v);
            }
            return loaisp;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(LoaiSP lsp) {
        try {
            return lspRp.add(lsp);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(LoaiSP lsp) {
        try {
            return lspRp.update(lsp);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(LoaiSP lsp) {
        try {
            return lspRp.delete(lsp);
        } catch (Exception e) {
            return false;
        }
    }
        
    public static void main(String[] args) {
        LoaiSanPhamService lspSV = new LoaiSanPhamService();
         List<ViewModelLoaiSanPham> sp = lspSV.getListLoaiSP();
        for (ViewModelLoaiSanPham viewModelLoaiSanPham : sp) {
            System.out.println(viewModelLoaiSanPham.toString());
        }
    }
     public List<LoaiSP> timkiem(String ma){
         List<LoaiSP> loaiSp = new ArrayList<>();
         for (LoaiSP x : lspRp.getListLoaiSp()) {
             if(x.getMa().toLowerCase().contains(ma.toLowerCase())){
                 loaiSp.add(x);
             }
         }
         return loaiSp;
     }
}
