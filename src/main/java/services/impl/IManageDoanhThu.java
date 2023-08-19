/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.impl;

import java.util.List;
import viewModel.ViewModelDoanhThu;
import viewModel.ViewModelDoanhThuThongKe;
import viewModel.ViewModelNhanVienDoanhThu;

/**
 *
 * @author Admin
 */
public interface IManageDoanhThu {

    List<ViewModelDoanhThu> getList();

    List<ViewModelDoanhThu> getList1();

    List<ViewModelDoanhThu> getListTUBEDENLON();

    List<ViewModelDoanhThu> getListTULONDENBE();

    List<ViewModelDoanhThu> getListtop3();

    int getListGanHet();

    int getListSanPham();

    int getListhethang();

    int getListNgungKinhdoanh();

    //doanhthunhanvien
    
    List<ViewModelNhanVienDoanhThu> getListNhanVien();

    //doanhthu
    
    public List<ViewModelDoanhThuThongKe> getListDoanhthu();

    int getDoanhthuTheoNam();

    int getDoanhthutheoThang();

    int getDoanhtHUTHEOnGAY();

    //so tien bo ra mua sp
    
    int getsotiennhaptheonam();

    int getsotiennhaptheothang();

    int getsotiennhaptheoNgay();

    List<ViewModelDoanhThuThongKe> getListHoaDonDaChon(String nbd, String nkt);

    int getListTongtiendachon(String nbd, String nkt);

    int getListTongBoRaDaChon(String nbd, String nkt);

    List<ViewModelDoanhThu> getList1(int i, int b);

    int getListSL();

    List<ViewModelDoanhThu> getListBdenL(int i, int b);

    int getListSLBdenL();
}
