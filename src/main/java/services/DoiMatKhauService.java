/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.NhanVien;
import java.util.ArrayList;
import java.util.List;
import repositories.DoiMatKhauRepository;
import services.impl.IManageDoiMatKhauService;
import viewModel.ViewModelNhanVien;

/**
 *
 * @author ktkha
 */
public class DoiMatKhauService implements IManageDoiMatKhauService {

    DoiMatKhauRepository dmkRepo = new DoiMatKhauRepository();

    @Override
    public List<ViewModelNhanVien> getNVbyId(String id) {
        try {
            List<NhanVien> nv = dmkRepo.getNVbyId(id);
            List<ViewModelNhanVien> list = new ArrayList<>();

            for (NhanVien sp : nv) {
                ViewModelNhanVien x = new ViewModelNhanVien();
                x.setId(sp.getId());
                x.setMa(sp.getMa());
                x.setHoTen(sp.getHoTen());
                x.setSdt(sp.getSdt());
                x.setDiaChi(sp.getDiaChi());
                x.setNgaySinh(String.valueOf(sp.getNgaySinh()));
                x.setIdCH(String.valueOf(sp.getIdCuaHang().getTenCuaHang()));
                x.setIdCV(String.valueOf(sp.getIdChucVu().getTenChucVu()));
                x.setMatKhau(sp.getMatKhau());
                x.setEmail(sp.getEmail());

                list.add(x);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getMKById(String id) {
        try {
            return dmkRepo.getMKById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateMKById(String id, String mk) {
        try {
            return dmkRepo.updateMKById(id, mk);
        } catch (Exception e) {
            return false;
        }
    }

}
