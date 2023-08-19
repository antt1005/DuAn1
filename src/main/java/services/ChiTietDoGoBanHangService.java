/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.ChiTietDoGo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import repositories.BanHangChiTietDoGoRepository;
import viewModel.ViewModelChiTietSanPhamBanHang;
import services.impl.IManageChiTietDoGoBanHangService;

/**
 *
 * @author Admin
 */
public class ChiTietDoGoBanHangService implements IManageChiTietDoGoBanHangService {

    private BanHangChiTietDoGoRepository a = new BanHangChiTietDoGoRepository();

    @Override
    public List<ViewModelChiTietSanPhamBanHang> getList(int i, int b) {
        List<Object[]> list = a.getList(i, b);
        List<ViewModelChiTietSanPhamBanHang> listsp = new ArrayList<>();
        try {
            for (Object[] a : list) {
                ViewModelChiTietSanPhamBanHang v = new ViewModelChiTietSanPhamBanHang();
                v.setId(a[0].toString());
                v.setTenSp(a[1].toString());
                v.setSanPham(a[2].toString());
                v.setLoaiSP(a[3].toString());
                v.setDongGo(a[4].toString());
                v.setNhaCungCap(a[5].toString());
                v.setNguonGoc(a[6].toString());
                v.setDonViTinh(a[7].toString());
                v.setSoLuong(Integer.parseInt(a[8].toString()));
                v.setGiaNhap(new BigDecimal(a[9].toString()));
                v.setGiaBan(new BigDecimal(a[10].toString()));
                v.setMoTa(a[11].toString());
                v.setTrangThai(Integer.parseInt(a[12].toString()));

                listsp.add(v);
            }
            return listsp;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelChiTietSanPhamBanHang> TimKiemTheoId(String Id) {
        List<ChiTietDoGo> list = a.TimKiemTheoId(Id);
        List<ViewModelChiTietSanPhamBanHang> listsp = new ArrayList<>();
        try {
            for (ChiTietDoGo h : list) {
                ViewModelChiTietSanPhamBanHang spnew = new ViewModelChiTietSanPhamBanHang(
                        h.getId(), h.getTenSP(), h.getIdSanPham().getTen(),
                        h.getIdLoaiSP().getTenDongSP(), h.getIdDongGo().getTenLoaiGo(),
                        h.getIdNhaCungCap().getTenNCC(), h.getIdNguocGoc().getQuocGia(),
                        h.getIdDonViTinh().getDonViTinh(), h.getSoLuong(), h.getGiaNhap(),
                        h.getGiaBan(), h.getMoTa(), h.getTrangThai());

                listsp.add(spnew);
            }
            return listsp;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelChiTietSanPhamBanHang> TimKiemTen(String Ten, int i, int b) {
        List<Object[]> list = a.TimKiemTen(Ten, i, b);
        List<ViewModelChiTietSanPhamBanHang> listsp = new ArrayList<>();
        try {
            for (Object[] a : list) {
                ViewModelChiTietSanPhamBanHang v = new ViewModelChiTietSanPhamBanHang();
                v.setId(a[0].toString());
                v.setTenSp(a[1].toString());
                v.setSanPham(a[2].toString());
                v.setLoaiSP(a[3].toString());
                v.setDongGo(a[4].toString());
                v.setNhaCungCap(a[5].toString());
                v.setNguonGoc(a[6].toString());
                v.setDonViTinh(a[7].toString());
                v.setSoLuong(Integer.parseInt(a[8].toString()));
                v.setGiaNhap(new BigDecimal(a[9].toString()));
                v.setGiaBan(new BigDecimal(a[10].toString()));
                v.setMoTa(a[11].toString());
                v.setTrangThai(Integer.parseInt(a[12].toString()));

                listsp.add(v);
            }
            return listsp;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(ChiTietDoGo go) {
        try {
            return add(go);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(ChiTietDoGo go) {
        try {
            return update(go);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String Id) {
        try {
            return delete(Id);
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        ChiTietDoGoBanHangService i = new ChiTietDoGoBanHangService();
        List<ViewModelChiTietSanPhamBanHang> listsp = i.getList(0, 10);
        for (ViewModelChiTietSanPhamBanHang viewModelChiTietSanPhamBanHang : listsp) {
            System.out.println(viewModelChiTietSanPhamBanHang.toString());
        }
    }

    @Override
    public int getRow() {
        try {
            return a.getRow();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getRowTimKiem(String Ten) {

        try {
            return a.getRowTimKiem(Ten);
        } catch (Exception e) {
            return -1;
        }
    }

}
