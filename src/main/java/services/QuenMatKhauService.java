/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.NhanVien;
import java.util.ArrayList;
import java.util.List;
import repositories.NhanVienRepository;
import repositories.QuenMatKhauRepository;
import services.impl.IManageQuenMatKhauService;
import viewModel.ViewModelNhanVien;
import viewModel.ViewModelQuenMatKhau;

/**
 *
 * @author admin
 */
public class QuenMatKhauService implements IManageQuenMatKhauService {

    private QuenMatKhauRepository qkmRepo = new QuenMatKhauRepository();

    @Override
    public List<ViewModelQuenMatKhau> getSDT(String ten) {
        
        try {
            
            List<ViewModelQuenMatKhau> list = new ArrayList<>();
            
            List<NhanVien> sps = qkmRepo.getListSDT(ten);
            
            for (NhanVien sp : sps) {
                
                list.add(new ViewModelQuenMatKhau(sp.getId(), sp.getMa(), sp.getHoTen(),
                        
                        sp.getSdt(),
                        
                        sp.getDiaChi(),
                        
                        sp.getNgaySinh() + "",
                        
                        sp.getIdCuaHang().getTenCuaHang(), 
                        
                        sp.getIdChucVu().getTenChucVu(),
                        
                        sp.getMatKhau(),
                        
                        sp.getEmail()));
                
            }
            return list;
            
        } catch (Exception e) {
            
            return null;
        }
    }
}
