/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.KhachHang;
import java.util.ArrayList;
import java.util.List;
import repositories.KhachHangRepository;
import services.impl.IManageKhachHangService;
import viewModel.ViewModelKhachHang;

/**
 *
 * @author Admin
 */
public class KhachHangService  implements IManageKhachHangService{
       private KhachHangRepository khrp = new KhachHangRepository();
    @Override
    public List<ViewModelKhachHang> getListKhachHang() {
      List<KhachHang> lsp = khrp.getListKhachHang();
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
    public List<ViewModelKhachHang> getListKhachHangByName(String ten) {
        List<KhachHang> lsp = khrp.getListKHByName(ten);
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
    public boolean add(KhachHang kh) {
        try {
            return khrp.add(kh);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(KhachHang kh) {
        try {
            return khrp.update(kh);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(KhachHang kh) {
        try {
            return khrp.delete(kh);
        } catch (Exception e) {
            return false;
        }       
    }
    
    public List<KhachHang> timkiem(String ma){
         List<KhachHang> khachhang = new ArrayList<>();
         for (KhachHang x : khrp.getListKhachHang()) {
             if(x.getMa().toLowerCase().contains(ma.toLowerCase())){
                 khachhang.add(x);
             }
         }
         return khachhang;
     }

    @Override
    public List<ViewModelKhachHang> getListKhachHangBysdt(String sdt) {
       List<KhachHang> lsp = khrp.getListKHBysdt(sdt);
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
}
