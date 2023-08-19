/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.NhanVien;
import java.util.ArrayList;
import java.util.List;
import repositories.NhanVienRepository1;
import services.impl.IManageNhanVienService1;
import viewModel.ViewModelNhanVien1;

/**
 *
 * @author Phuong Bi
 */
public class NhanVienService1 implements IManageNhanVienService1 {

    private NhanVienRepository1 nhanVien1 = new NhanVienRepository1();

    @Override
    public List<ViewModelNhanVien1> getListNVDangLam() {
        List<NhanVien> nv = nhanVien1.getListNVDangLam();
        try {
            List<ViewModelNhanVien1> viewNhanVien = new ArrayList<>();
            for (NhanVien viewModelNhanVien1 : nv) {

                ViewModelNhanVien1 v = new ViewModelNhanVien1();

                v.setId(viewModelNhanVien1.getId());

                v.setMa(viewModelNhanVien1.getMa());

                v.setHoTen(viewModelNhanVien1.getHoTen());

                v.setSdt(viewModelNhanVien1.getSdt());

                v.setDiaChi(viewModelNhanVien1.getDiaChi());

                v.setNgaySinh(viewModelNhanVien1.getNgaySinh() + "");

                v.setIdCH(viewModelNhanVien1.getIdCuaHang().getTenCuaHang());

                v.setIdCV(viewModelNhanVien1.getIdChucVu().getTenChucVu());

                v.setMatKhau(viewModelNhanVien1.getMatKhau());

                v.setEmail(viewModelNhanVien1.getEmail());

                v.setTrangThai(viewModelNhanVien1.getTrangThai());

                viewNhanVien.add(v);

            }
            return viewNhanVien;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelNhanVien1> getListNVNghiLam() {
        List<NhanVien> nv = nhanVien1.getListNVNghiLam();
        try {
            List<ViewModelNhanVien1> viewNV = new ArrayList<>();
            for (NhanVien viewModelNhanVien1 : nv) {

                ViewModelNhanVien1 v = new ViewModelNhanVien1();

                v.setId(viewModelNhanVien1.getId());

                v.setMa(viewModelNhanVien1.getMa());

                v.setHoTen(viewModelNhanVien1.getHoTen());

                v.setSdt(viewModelNhanVien1.getSdt());

                v.setDiaChi(viewModelNhanVien1.getDiaChi());

                v.setNgaySinh(viewModelNhanVien1.getNgaySinh() + "");

                v.setIdCH(viewModelNhanVien1.getIdCuaHang().getTenCuaHang());

                v.setIdCV(viewModelNhanVien1.getIdChucVu().getTenChucVu());

                v.setMatKhau(viewModelNhanVien1.getMatKhau());

                v.setEmail(viewModelNhanVien1.getEmail());

                v.setTrangThai(viewModelNhanVien1.getTrangThai());
                viewNV.add(v);
            }
            return viewNV;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelNhanVien1> getAll() {

        List<NhanVien> nv = nhanVien1.getAll();
        try {
            List<ViewModelNhanVien1> viewNV = new ArrayList<>();
            for (NhanVien viewModelNhanVien1 : nv) {

                ViewModelNhanVien1 v = new ViewModelNhanVien1();

                v.setId(viewModelNhanVien1.getId());

                v.setMa(viewModelNhanVien1.getMa());

                v.setHoTen(viewModelNhanVien1.getHoTen());

                v.setSdt(viewModelNhanVien1.getSdt());

                v.setDiaChi(viewModelNhanVien1.getDiaChi());

                v.setNgaySinh(viewModelNhanVien1.getNgaySinh() + "");

                v.setIdCH(viewModelNhanVien1.getIdCuaHang().getTenCuaHang());

                v.setIdCV(viewModelNhanVien1.getIdChucVu().getTenChucVu());

                v.setMatKhau(viewModelNhanVien1.getMatKhau());

                v.setEmail(viewModelNhanVien1.getEmail());

                v.setTrangThai(viewModelNhanVien1.getTrangThai());

                viewNV.add(v);
            }
            return viewNV;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(NhanVien nv) {
        try {
            nhanVien1.add(nv);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(NhanVien nv) {
        try {
            nhanVien1.update(nv);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            nhanVien1.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ViewModelNhanVien1> listtk(String Ten) {
        try {
            List<ViewModelNhanVien1> list = new ArrayList<>();
            List<NhanVien> sps = nhanVien1.listtk(Ten);
            for (NhanVien sp : sps) {
                list.add(new ViewModelNhanVien1(sp.getId(),
                        
                        sp.getMa(),
                        
                        sp.getHoTen(),
                        
                        sp.getSdt(),
                        
                        sp.getDiaChi(),
                        
                        sp.getNgaySinh() + "",
                        
                        sp.getIdCuaHang().getTenCuaHang(),
                        
                        sp.getIdChucVu().getTenChucVu(),
                        
                        sp.getMatKhau(),
                        
                        sp.getEmail(),
                        
                        sp.getTrangThai()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean troLai(String id) {
        try {
            nhanVien1.troLai(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ViewModelNhanVien1> listtkChucVu(String idCV) {
        try {
            List<ViewModelNhanVien1> list = new ArrayList<>();
            List<NhanVien> sps = nhanVien1.listtkChucVu(idCV);
            for (NhanVien sp : sps) {
                list.add(new ViewModelNhanVien1(sp.getId(),
                        
                        sp.getMa(),
                        
                        sp.getHoTen(),
                        
                        sp.getSdt(),
                        
                        sp.getDiaChi(),
                        
                        sp.getNgaySinh() + "",
                        
                        sp.getIdCuaHang().getTenCuaHang(),
                        
                        sp.getIdChucVu().getTenChucVu(),
                        
                        sp.getMatKhau(),
                        
                        sp.getEmail(),
                        
                        sp.getTrangThai()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelNhanVien1> listtkCuaHang(String idCH) {
        try {
            List<ViewModelNhanVien1> list = new ArrayList<>();
            List<NhanVien> sps = nhanVien1.listtkCuaHang(idCH);
            for (NhanVien sp : sps) {
                list.add(new ViewModelNhanVien1(sp.getId(),
                        
                        sp.getMa(),
                        
                        sp.getHoTen(),
                        
                        sp.getSdt(),
                        
                        sp.getDiaChi(),
                        
                        sp.getNgaySinh() + "",
                        
                        sp.getIdCuaHang().getTenCuaHang(),
                        
                        sp.getIdChucVu().getTenChucVu(),
                        
                        sp.getMatKhau(),
                        
                        sp.getEmail(),
                        
                        sp.getTrangThai()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ViewModelNhanVien1> getAll(int a, int b) {
        List<ViewModelNhanVien1> list = new ArrayList<>();
        List<Object[]> sps = nhanVien1.getAll(a, b);
        for (Object[] sp : sps) {
            ViewModelNhanVien1 v = new ViewModelNhanVien1();
            v.setId(sp[0].toString());

            v.setMa(sp[1].toString());

            v.setHoTen(sp[2].toString());

            v.setSdt(sp[3].toString());

            v.setDiaChi(sp[4].toString());

            v.setNgaySinh(sp[5].toString());

            v.setEmail(sp[6].toString());

            v.setMatKhau(sp[7].toString());

            v.setIdCV(sp[8].toString());

            if (sp[10].toString().equals("2")) {
                v.setIdCH("Dừng hoạt động");

            } else {
                v.setIdCH(sp[9].toString());
            }

            v.setTrangThai(1);

            list.add(v);
        }
        return list;
    }

    @Override
    public int getRow(int b, int c) {
        try {
            return nhanVien1.getRow(b, c);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<ViewModelNhanVien1> listtkSDT(String sdt) {

        try {
            List<ViewModelNhanVien1> list = new ArrayList<>();
            List<NhanVien> sps = nhanVien1.listtkSDT(sdt);
            for (NhanVien sp : sps) {
                list.add(new ViewModelNhanVien1(sp.getId(),
                        
                        sp.getMa(),
                        
                        sp.getHoTen(),
                        
                        sp.getSdt(),
                        
                        sp.getDiaChi(),
                        
                        sp.getNgaySinh() + "",
                        
                        sp.getIdCuaHang().getTenCuaHang(),
                        
                        sp.getIdChucVu().getTenChucVu(),
                        
                        sp.getMatKhau(),
                        
                        sp.getEmail(),
                        
                        sp.getTrangThai()));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

}
