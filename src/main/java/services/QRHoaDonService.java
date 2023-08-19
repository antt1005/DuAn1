/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.HoaDon;
import java.util.ArrayList;
import java.util.List;
import repositories.QRHoaDonRepository;
import services.impl.IManageQRHoaDonService;
import viewModel.ViewModelHoaDonBanHang;
import viewModel.ViewModelQRHoaDon;

/**
 *
 * @author admin
 */
public class QRHoaDonService implements IManageQRHoaDonService{
    private QRHoaDonRepository qr = new QRHoaDonRepository();
    @Override
    public List<ViewModelQRHoaDon> getList() {
         List<HoaDon> hds = qr.getList();
        List<ViewModelQRHoaDon> lists = new ArrayList<>();;
        try {
            for (HoaDon a : hds) {
                ViewModelQRHoaDon b = new ViewModelQRHoaDon();
                b.setId(a.getId());
                b.setMa(a.getMa());
                b.setNgayThanhToan(a.getNgayThanhToan()+ "");
                
                b.setTenNV(a.getIdNhanVien().getHoTen());

                lists.add(b);
            }

            return lists;
        } catch (Exception e) {
            return null;
        }
    }
    
}
