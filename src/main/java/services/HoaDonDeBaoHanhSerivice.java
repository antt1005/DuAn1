/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.HoaDon;
import domainModels.HoaDonChiTiet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import repositories.HoaDonDeBaoHanhRepository;
import services.impl.IManageHoaDonDeBaoHanhService;
import viewModel.ViewModelBAOHANHCHITIET;
import viewModel.ViewModelHDCTBH;
import viewModel.ViewModelHoaDonBaoHanh;
import viewModel.ViewModelHoaDonDeBaoHanh;

/**
 *
 * @author ktkha
 */
public class HoaDonDeBaoHanhSerivice implements IManageHoaDonDeBaoHanhService {

    HoaDonDeBaoHanhRepository hdBH = new HoaDonDeBaoHanhRepository();

    @Override
    public List<ViewModelHoaDonDeBaoHanh> getListHD(String id) {
        List<HoaDon> hd = hdBH.getListHD(id);
        List<ViewModelHoaDonDeBaoHanh> list = new ArrayList<>();
        try {
            for (HoaDon a : hd) {
                ViewModelHoaDonDeBaoHanh x = new ViewModelHoaDonDeBaoHanh();
                x.setId(a.getId());
                x.setMa(a.getMa());
                x.setNgayThanhToan(String.valueOf(a.getNgayThanhToan()));
                x.setTenNV(a.getIdNhanVien().getHoTen());
                x.setIdKH(a.getIdKhachHang().getTenKhachHang());
                x.setTongTien(a.getThanhTien());
                x.setSLBH(a.getTrangThaiHoaDon());
                list.add(x);
            }

            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelHDCTBH> getListCTHD(String id) {
        List<HoaDonChiTiet> hdct = hdBH.getListCTHD(id);
        List<ViewModelHDCTBH> list = new ArrayList<>();
        try {
            for (HoaDonChiTiet a : hdct) {
                ViewModelHDCTBH x = new ViewModelHDCTBH();
                x.setId(a.getIdChiTietDoGo().getId());
                x.setIdHD(a.getIdHoaDon().getId());
                x.setSoLuong(a.getSoLuong());
                x.setThanhTien(a.getDonGia());
                list.add(x);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        HoaDonDeBaoHanhSerivice a = new HoaDonDeBaoHanhSerivice();
        List<ViewModelHoaDonDeBaoHanh> b = a.getListHD("3A0EC275-3876-4C1C-9808-0EB84385F009");
        for (ViewModelHoaDonDeBaoHanh viewModelHoaDonDeBaoHanh : b) {
            System.out.println(viewModelHoaDonDeBaoHanh.toString());
        }

//        List<ViewModelHDCTBH> c = a.getListCTHD("0FF804AB-10D0-4694-A332-21A688669FB8");
//        for (ViewModelHDCTBH viewModelHDCTBH : c) {
//            System.out.println(viewModelHDCTBH.toString());
//        }
    }

    @Override
    public boolean addHoadon(HoaDon hd) {
        try {
            return hdBH.addHoadon(hd);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ViewModelHoaDonBaoHanh> getListHDBH() {
        List<HoaDon> hd = hdBH.getListHDBH();
        List<ViewModelHoaDonBaoHanh> list = new ArrayList<>();
//        try {
        for (HoaDon a : hd) {
            ViewModelHoaDonBaoHanh x = new ViewModelHoaDonBaoHanh();
            x.setId(a.getId());
            x.setNgayTao(String.valueOf(a.getNgayTao()));
            x.setTenNV(a.getIdNhanVien().getHoTen());
            x.setTenKH(a.getIdKhachHang().getTenKhachHang());
            x.setTrangThai(3);

            list.add(x);
        }
        return list;
//        } catch (Exception e) {
//            return null;
//        }
    }

    @Override
    public List<ViewModelBAOHANHCHITIET> getListCTHDbaoHanh(String id) {
        List<HoaDonChiTiet> hdct = hdBH.getListCTHD(id);
        List<ViewModelBAOHANHCHITIET> list = new ArrayList<>();
        try {
            for (HoaDonChiTiet a : hdct) {
                ViewModelBAOHANHCHITIET x = new ViewModelBAOHANHCHITIET();

                x.setIdbh(a.getIdHoaDon().getId());
                x.setIdsp(a.getIdChiTietDoGo().getId());
                x.setTensp(a.getIdChiTietDoGo().getTenSP());
                x.setSoluong(a.getSoLuong());
                
                list.add(x);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(HoaDonChiTiet hd) {
       try {
            return hdBH.add(hd);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(HoaDonChiTiet hd) {
       try {
            return hdBH.update(hd);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String idsp, String idhd) {
      try {
            return hdBH.delete(idsp, idhd);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateHoadon(HoaDon hd) {
        try {
            return hdBH.updateHoadon(hd);
        } catch (Exception e) {
            return false;
        }
    }


}
