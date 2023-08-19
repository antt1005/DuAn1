/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.HoaDon;
import java.util.ArrayList;
import java.util.List;
import repositories.HoadonRepository;
import services.impl.IManageHoaDonService;
import viewModel.ViewModelHoadon;

/**
 *
 * @author PC
 */
public class HoadonService implements IManageHoaDonService {

     HoadonRepository hdrp = new HoadonRepository();

    @Override
    public List<ViewModelHoadon> getListHoaDon(int i, int b) {
        List<Object[]> lhd = hdrp.getList(i, b);

        List<ViewModelHoadon> hoadon = new ArrayList<>();
        for (Object[] a : lhd) {
            ViewModelHoadon c = new ViewModelHoadon();
            c.setId(a[0].toString());
            c.setMa(a[1].toString());
            c.setTenNV(a[2].toString());
            if (a[3] == null) {
                c.setTenKH("Không Chọn");
            } else {
                c.setTenKH(a[3].toString());
            }
            c.setNgayTao(a[4].toString());
            if (a[5] == null) {
                c.setNgayThanhToan("Chưa Thanh Toán");
            } else {
                c.setNgayThanhToan(a[5].toString());
            }
            if (a[6] == null) {
                c.setPhamtramKM("Không chọn");
            } else {
                c.setPhamtramKM(a[6].toString());
            }
            if (a[7].toString().equals("1")) {
                c.setTrangThaiHoaDon("Chưa Thanh Toán");
            } else if (a[7].toString().equals("2")) {
                c.setTrangThaiHoaDon("Đã Thanh Toán");
            } else {
                c.setTrangThaiHoaDon("Hóa Đơn Bảo Hành");
            }
            hoadon.add(c);
        }
        return hoadon;

    }

    @Override
    public boolean add(HoaDon hd) {
        try {
            return hdrp.addHoadon(hd);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            return hdrp.delete(id);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ViewModelHoadon> loc(int Tranngthai) {
        List<HoaDon> lhd = hdrp.loc(Tranngthai);
        try {
            List<ViewModelHoadon> hoadon = new ArrayList<>();
            for (HoaDon x : lhd) {
                ViewModelHoadon l = new ViewModelHoadon();
                l.setId(x.getId());
                l.setMa(x.getMa());
                l.setNgayTao(x.getNgayTao() + "");
                if (x.getNgayThanhToan() == null) {
                    l.setNgayThanhToan("Chưa Thanh Toán");
                } else {
                    l.setNgayThanhToan(x.getNgayThanhToan() + "");
                }

                if (x.getIdKhuyenMai() == null) {
                    l.setPhamtramKM("Không chọn");
                } else {
                    l.setPhamtramKM(x.getIdKhuyenMai().getPhanTramKM() + "");
                }
                if (x.getIdKhachHang() == null) {
                    l.setTenKH("Không chọn");
                } else {
                    l.setTenKH(x.getIdKhachHang().getTenKhachHang());
                }

                l.setTenNV(x.getIdNhanVien().getHoTen());
                if (x.getTrangThai() == 1) {
                    l.setTrangThaiHoaDon("Chưa Thanh Toán");
                } else if (x.getTrangThai() == 2) {
                    l.setTrangThaiHoaDon("Đã Thanh Toán");
                } else {
                    l.setTrangThaiHoaDon("Hóa Đơn Bảo Hành");
                }

                hoadon.add(l);
            }
            return hoadon;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int row() {
        try {
            return hdrp.getListSL();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<ViewModelHoadon> getListHD() {

        try {
            List<ViewModelHoadon> list = new ArrayList<>();
            List<HoaDon> sps = hdrp.getListHD();

            for (HoaDon sp : sps) {

                ViewModelHoadon x = new ViewModelHoadon();

                x.setId(sp.getId());

                x.setMa(sp.getMa());

                x.setTenNV(sp.getIdNhanVien().getHoTen());

                x.setTenKH(sp.getIdKhachHang().getTenKhachHang());

                x.setNgayTao(String.valueOf(sp.getNgayTao()));

                x.setNgayThanhToan(String.valueOf(sp.getNgayThanhToan()));

                if (sp.getIdKhuyenMai() == null) {
                    x.setPhamtramKM("Khong Chon");
                } else {

                    x.setPhamtramKM(sp.getIdKhuyenMai().getPhanTramKM() + "");
                }

                x.setTrangThaiHoaDon(sp.getTrangThai() + "");

                list.add(x);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int soLuong() {
        try {
            return hdrp.getSoLuongHd();
        } catch (Exception e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        HoadonService i = new HoadonService();
        System.out.println(i.soLuong());
    }

    @Override
    public List<ViewModelHoadon> timKiemMa(String ma) {
          List<HoaDon> lhd = hdrp.listtk(ma);
        try {
            List<ViewModelHoadon> hoadon = new ArrayList<>();
            for (HoaDon x : lhd) {
                ViewModelHoadon l = new ViewModelHoadon();
                l.setId(x.getId());
                l.setMa(x.getMa());
                l.setNgayTao(x.getNgayTao() + "");
                if (x.getNgayThanhToan() == null) {
                    l.setNgayThanhToan("Chưa Thanh Toán");
                } else {
                    l.setNgayThanhToan(x.getNgayThanhToan() + "");
                }

                if (x.getIdKhuyenMai() == null) {
                    l.setPhamtramKM("Không chọn");
                } else {
                    l.setPhamtramKM(x.getIdKhuyenMai().getPhanTramKM() + "");
                }
                if (x.getIdKhachHang() == null) {
                    l.setTenKH("Không chọn");
                } else {
                    l.setTenKH(x.getIdKhachHang().getTenKhachHang());
                }

                l.setTenNV(x.getIdNhanVien().getHoTen());
                if (x.getTrangThai() == 1) {
                    l.setTrangThaiHoaDon("Chưa Thanh Toán");
                } else if (x.getTrangThai() == 2) {
                    l.setTrangThaiHoaDon("Đã Thanh Toán");
                } else {
                    l.setTrangThaiHoaDon("Hóa Đơn Bảo Hành");
                }

                hoadon.add(l);
            }
            return hoadon;
        } catch (Exception e) {
            return null;
        }
    }

}
