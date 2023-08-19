/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.ChiTietDoGo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import repositories.ChiTietDoGoRepository;
import services.impl.IManageChiTietDoGoService;
import viewModel.ChiTietDoGoViewModel;

/**
 *
 * @author Admin
 */
public class ChiTietDoGoService implements IManageChiTietDoGoService {

    private ChiTietDoGoRepository a = new ChiTietDoGoRepository();

    @Override
    public List<ChiTietDoGoViewModel> list() {
        
        try {
            
            List<ChiTietDoGoViewModel> list = new ArrayList<>();
            
            List<ChiTietDoGo> sps = a.list();
            
            for (ChiTietDoGo sp : sps) {
                list.add(new ChiTietDoGoViewModel(
                        sp.getId(),
                        sp.getTenSP(),
                        sp.getIdLoaiSP().getTenDongSP(),
                        sp.getIdSanPham().getTen(),
                        sp.getIdDongGo().getTenLoaiGo(),
                        sp.getIdNhaCungCap().getTenNCC(),
                        sp.getIdNguocGoc().getQuocGia(), sp.getIdDonViTinh().getDonViTinh(), sp.getMoTa(),
                        sp.getSoLuong(), sp.getGiaNhap(), sp.getGiaBan()
                ));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ChiTietDoGoViewModel> listtk(String Ten) {
        try {
            List<ChiTietDoGoViewModel> list = new ArrayList<>();
            List<ChiTietDoGo> sps = a.listtk(Ten);
            for (ChiTietDoGo sp : sps) {
                list.add(new ChiTietDoGoViewModel(
                        sp.getId(), sp.getTenSP(),
                        sp.getIdLoaiSP().getTenDongSP(),
                        sp.getIdSanPham().getTen(),
                        sp.getIdDongGo().getTenLoaiGo(),
                        sp.getIdNhaCungCap().getTenNCC(),
                        sp.getIdNguocGoc().getQuocGia(),
                        sp.getIdDonViTinh().getDonViTinh(),
                        sp.getMoTa(),
                        sp.getSoLuong(),
                        sp.getGiaNhap(),
                        sp.getGiaBan()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(ChiTietDoGo go) {
        
        try {
            return a.add(go);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(ChiTietDoGo go) {
        
        try {
            return a.update(go);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        
        try {
            return a.delete(id);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean truSanPham(String id, int soLuong) {
        
        try {
            return a.truSanPham(id, soLuong);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean congSanPham(String id, int soLuong) {
        
        try {
            return a.congSanPham(id, soLuong);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateSLSanPham(String id, int soLuong) {
        
        try {
            return a.updateSLSanPham(id, soLuong);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ChiTietDoGoViewModel> phanTrangCTDG(int i, int b) {
        
        List<Object[]> list = a.phanTrangCTDG(i, b);

        List<ChiTietDoGoViewModel> ct = new ArrayList<>();
        for (Object[] a : list) {
            ChiTietDoGoViewModel x = new ChiTietDoGoViewModel();

            x.setId(a[0].toString());
            //
            x.setTensp(a[1].toString());

            x.setSp(a[2].toString());

            x.setLoad(a[3].toString());

            x.setDonggo(a[4].toString());

            x.setNcc(a[5].toString());

            x.setNguongoc(a[6].toString());

            x.setDonvi(a[7].toString());

            x.setMota(a[8].toString());

            x.setSoluong(Integer.parseInt(a[9].toString()));

            Double giaNhap = Double.parseDouble(a[10].toString());

            x.setGiaNhap(BigDecimal.valueOf(giaNhap));

            Double giaBan = Double.parseDouble(a[11].toString());

            x.setGiaBan(BigDecimal.valueOf(giaBan));

            ct.add(x);
        }
        return ct;
    }

    @Override
    public int row() {
        try {
            return a.getListSLRow();
        } catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        ChiTietDoGoService a = new ChiTietDoGoService();
        
        System.out.println(a.getListTu1Den3M().toString());
//        List<ChiTietDoGoViewModel> list = a.phanTrangCTDG(0, 2);
//        for (ChiTietDoGoViewModel objects : list) {
//            System.out.println(objects.toString());
//        }
    }

//    public List<ChiTietDoGoViewModel> getListLonHon1Trieu() {
//        try {
//            List<ChiTietDoGoViewModel> list = new ArrayList<>();
//            List<ChiTietDoGo> sps = a.getListTu1Den3M();
//            for (ChiTietDoGo sp : sps) {
//                list.add(new ChiTietDoGoViewModel(
//                        sp.getId(), sp.getTenSP(),
//                        sp.getIdLoaiSP().getTenDongSP(),
//                        sp.getIdSanPham().getTen(),
//                        sp.getIdDongGo().getTenLoaiGo(),
//                        sp.getIdNhaCungCap().getTenNCC(),
//                        sp.getIdNguocGoc().getQuocGia(),
//                        sp.getIdDonViTinh().getDonViTinh(),
//                        sp.getMoTa(),
//                        sp.getSoLuong(),
//                        sp.getGiaNhap(),
//                        sp.getGiaBan()));
//            }
//            return list;
//        } catch (Exception e) {
//            return null;
//        }
//    }
    @Override
    public List<ChiTietDoGoViewModel> getListNhoHon1Trieu() {
        
        try {
            List<ChiTietDoGoViewModel> list = new ArrayList<>();
            List<ChiTietDoGo> sps = a.getListNhoHon1Trieu();
            for (ChiTietDoGo sp : sps) {
                list.add(new ChiTietDoGoViewModel(
                        sp.getId(), sp.getTenSP(),
                        sp.getIdLoaiSP().getTenDongSP(),
                        sp.getIdSanPham().getTen(),
                        sp.getIdDongGo().getTenLoaiGo(),
                        sp.getIdNhaCungCap().getTenNCC(),
                        sp.getIdNguocGoc().getQuocGia(),
                        sp.getIdDonViTinh().getDonViTinh(),
                        sp.getMoTa(),
                        sp.getSoLuong(),
                        sp.getGiaNhap(),
                        sp.getGiaBan()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ChiTietDoGoViewModel> getListTu1Den3M() {
        try {
            List<ChiTietDoGoViewModel> list = new ArrayList<>();
            List<ChiTietDoGo> sps = a.getListTu1Den3M();
            for (ChiTietDoGo sp : sps) {
                list.add(new ChiTietDoGoViewModel(
                        sp.getId(), sp.getTenSP(),
                        sp.getIdLoaiSP().getTenDongSP(),
                        sp.getIdSanPham().getTen(),
                        sp.getIdDongGo().getTenLoaiGo(),
                        sp.getIdNhaCungCap().getTenNCC(),
                        sp.getIdNguocGoc().getQuocGia(),
                        sp.getIdDonViTinh().getDonViTinh(),
                        sp.getMoTa(),
                        sp.getSoLuong(),
                        sp.getGiaNhap(),
                        sp.getGiaBan()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ChiTietDoGoViewModel> getListTu3Den5M() {
        
        try {
            List<ChiTietDoGoViewModel> list = new ArrayList<>();
            List<ChiTietDoGo> sps = a.getListTu3Den5M();
            for (ChiTietDoGo sp : sps) {
                list.add(new ChiTietDoGoViewModel(
                        sp.getId(), sp.getTenSP(),
                        sp.getIdLoaiSP().getTenDongSP(),
                        sp.getIdSanPham().getTen(),
                        sp.getIdDongGo().getTenLoaiGo(),
                        sp.getIdNhaCungCap().getTenNCC(),
                        sp.getIdNguocGoc().getQuocGia(),
                        sp.getIdDonViTinh().getDonViTinh(),
                        sp.getMoTa(),
                        sp.getSoLuong(),
                        sp.getGiaNhap(),
                        sp.getGiaBan()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ChiTietDoGoViewModel> getListTu5Den10M() {
        
        try {
            
            List<ChiTietDoGoViewModel> list = new ArrayList<>();
            List<ChiTietDoGo> sps = a.getListTu5Den10M();
            for (ChiTietDoGo sp : sps) {
                list.add(new ChiTietDoGoViewModel(
                        sp.getId(), sp.getTenSP(),
                        sp.getIdLoaiSP().getTenDongSP(),
                        sp.getIdSanPham().getTen(),
                        sp.getIdDongGo().getTenLoaiGo(),
                        sp.getIdNhaCungCap().getTenNCC(),
                        sp.getIdNguocGoc().getQuocGia(),
                        sp.getIdDonViTinh().getDonViTinh(),
                        sp.getMoTa(),
                        sp.getSoLuong(),
                        sp.getGiaNhap(),
                        sp.getGiaBan()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ChiTietDoGoViewModel> getListLonHon10Trieu() {
        
        try {
            
            List<ChiTietDoGoViewModel> list = new ArrayList<>();
            List<ChiTietDoGo> sps = a.getListLonHon10Trieu();
            for (ChiTietDoGo sp : sps) {
                list.add(new ChiTietDoGoViewModel(
                        sp.getId(), sp.getTenSP(),
                        sp.getIdLoaiSP().getTenDongSP(),
                        sp.getIdSanPham().getTen(),
                        sp.getIdDongGo().getTenLoaiGo(),
                        sp.getIdNhaCungCap().getTenNCC(),
                        sp.getIdNguocGoc().getQuocGia(),
                        sp.getIdDonViTinh().getDonViTinh(),
                        sp.getMoTa(),
                        sp.getSoLuong(),
                        sp.getGiaNhap(),
                        sp.getGiaBan()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

}
