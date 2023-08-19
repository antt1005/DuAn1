/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.HoaDon;
import domainModels.KhachHang;
import java.util.ArrayList;
import java.util.List;
import repositories.BaoHanhRepository;
import services.impl.IManageBaoHanhService;
import viewModel.ViewModelHoadon;
import viewModel.ViewModelKhachHang;

/**
 *
 * @author Phuong Bi
 */
public class BaoHanhService implements IManageBaoHanhService {

    private BaoHanhRepository baoHanh = new BaoHanhRepository();

    @Override
    public List<ViewModelKhachHang> getListKhachHang() {

        List<KhachHang> lsp = baoHanh.getListKhachHang();
        try {
            List<ViewModelKhachHang> khachhang = new ArrayList<>();
            for (KhachHang loaiSp : lsp) {
                ViewModelKhachHang v = new ViewModelKhachHang();
                v.setID(loaiSp.getId());
                v.setMa(loaiSp.getMa());
                v.setTenkh(loaiSp.getTenKhachHang());
                v.setSdt(loaiSp.getSdt());
                v.setDiachi(loaiSp.getDiaChi());
                v.setTrangthai(String.valueOf(loaiSp.getTrangThai()));
                khachhang.add(v);
            }
            return khachhang;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelKhachHang> getTKSDT(String sdt) {
        List<KhachHang> lsp = baoHanh.getTKSDT(sdt);
        try {
            List<ViewModelKhachHang> khachhang = new ArrayList<>();
            for (KhachHang loaiSp : lsp) {
                ViewModelKhachHang v = new ViewModelKhachHang();
                v.setID(loaiSp.getId());
                v.setMa(loaiSp.getMa());
                v.setTenkh(loaiSp.getTenKhachHang());
                v.setSdt(loaiSp.getSdt());
                v.setDiachi(loaiSp.getDiaChi());
                v.setTrangthai(String.valueOf(loaiSp.getTrangThai()));
                khachhang.add(v);
            }
            return khachhang;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void main(String[] args) {
        BaoHanhService i = new BaoHanhService();
        for (String arg : args) {
            
        }
    }

  

}
