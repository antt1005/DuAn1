/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.ChiTietDoGo;
import java.util.ArrayList;
import java.util.List;
import repositories.DoanhThuRepository;
import services.impl.IManageDoanhThu;
import viewModel.ViewModelDoanhThu;
import viewModel.ViewModelDoanhThuThongKe;
import viewModel.ViewModelNhanVienDoanhThu;

/**
 *
 * @author Admin
 */
public class DoanhThuService implements IManageDoanhThu {

    DoanhThuRepository dt = new DoanhThuRepository();

    @Override
    public List<ViewModelDoanhThu> getList() {
        
        List<ViewModelDoanhThu> list = new ArrayList<>();
        
        try {
            
            List<Object[]> lists = dt.getList();
            
            for (Object[] a : lists) {
                
                ViewModelDoanhThu b = new ViewModelDoanhThu();
                
                b.setIdsp(a[0].toString());
                b.setTensanpham(a[1].toString());
                b.setSoluongton(a[2].toString());
                b.setSoluongban(a[3].toString());
                b.setTongtien(a[4].toString());

                list.add(b);
            }

            return list;
            
        } catch (Exception e) {
            
            return null;
            
        }
        
    }

    @Override
    public List<ViewModelDoanhThu> getListtop3() {
        
        List<ViewModelDoanhThu> list = new ArrayList<>();
        
        try {
            
            List<Object[]> lists = dt.getListtop3();
            
            for (Object[] a : lists) {
                
                ViewModelDoanhThu b = new ViewModelDoanhThu();
                
                b.setIdsp(a[0].toString());
                b.setTensanpham(a[1].toString());
                b.setSoluongton(a[2].toString());
                b.setSoluongban(a[3].toString());
                b.setTongtien(a[4].toString());

                list.add(b);
                
            }

            return list;
            
        } catch (Exception e) {
            
            return null;
            
        }
        
    }

    @Override
    public List<ViewModelDoanhThu> getListTUBEDENLON() {
        
        List<ViewModelDoanhThu> list = new ArrayList<>();
        
        try {
            
            List<Object[]> lists = dt.getListTUBEDENLON();
            
            for (Object[] a : lists) {
                
                ViewModelDoanhThu b = new ViewModelDoanhThu();
                b.setIdsp(a[0].toString());
                b.setTensanpham(a[1].toString());
                b.setSoluongton(a[2].toString());
                b.setSoluongban(a[3].toString());
                b.setTongtien(a[4].toString());

                list.add(b);
                
            }

            return list;
            
        } catch (Exception e) {
            
            return null;
            
        }
        
    }

    @Override
    public List<ViewModelDoanhThu> getListTULONDENBE() {
        
        List<ViewModelDoanhThu> list = new ArrayList<>();
        
        try {
            
            List<Object[]> lists = dt.getListTULONDENBE();
            
            for (Object[] a : lists) {
                
                ViewModelDoanhThu b = new ViewModelDoanhThu();
                
                b.setIdsp(a[0].toString());
                b.setTensanpham(a[1].toString());
                b.setSoluongton(a[2].toString());
                b.setSoluongban(a[3].toString());
                b.setTongtien(a[4].toString());

                list.add(b);
            }

            return list;
            
        } catch (Exception e) {
            
            return null;
            
        }
        
    }

    @Override
    public int getListGanHet() {
        
        return dt.getListGanHet();
        
    }
    

    @Override
    public int getListSanPham() {
        
        return dt.getListSanPham();
        
    }

    @Override
    public int getListhethang() {
        
        return dt.getListhethang();
        
    }

    @Override
    public int getListNgungKinhdoanh() {
        
        return dt.getListNgungKinhdoanh();
        
    }

    //doanhthunhanvien
    @Override
    public List<ViewModelNhanVienDoanhThu> getListNhanVien() {
        
        List<ViewModelNhanVienDoanhThu> list = new ArrayList<>();
        
        try {
            
            List<Object[]> lists = dt.getListNhanVien();
            
            for (Object[] a : lists) {
                
                ViewModelNhanVienDoanhThu b = new ViewModelNhanVienDoanhThu();
                
                b.setId(a[0].toString());
                b.setMa(a[1].toString());
                b.setTennv(a[2].toString());
                b.setChucvu(a[3].toString());
                b.setHddalam(a[4].toString());

                list.add(b);
                
            }

            return list;
            
        } catch (Exception e) {
            
            return null;
            
        }
        
    }

    @Override
    public List<ViewModelDoanhThuThongKe> getListDoanhthu() {
        
        List<ViewModelDoanhThuThongKe> list = new ArrayList<>();
        
        try {
            
            List<Object[]> lists = dt.getListDoanhthu();
            
            for (Object[] a : lists) {
                
                ViewModelDoanhThuThongKe b = new ViewModelDoanhThuThongKe();
                
                b.setId(a[0].toString());
                b.setMa(a[1].toString());
                b.setNgaytt(a[2].toString());
                b.setTongtien(a[3].toString());

                list.add(b);
                
            }

            return list;
            
        } catch (Exception e) {
            
            return null;
            
        }
        
    }

    @Override
    public int getDoanhthuTheoNam() {
        
        return dt.getDoanhthuTheoNam();
        
    }

    @Override
    public int getDoanhthutheoThang() {
        
        return dt.getDoanhthutheoThang();
        
    }

    @Override
    public int getDoanhtHUTHEOnGAY() {
        
        return dt.getDoanhtHUTHEOnGAY();
        
    }

    @Override
    public int getsotiennhaptheonam() {
        
        return dt.getsotiennhaptheonam();
        
    }

    @Override
    public int getsotiennhaptheothang() {
        
        return dt.getsotiennhaptheothang();
        
    }

    @Override
    public int getsotiennhaptheoNgay() {
        
        return dt.getsotiennhaptheoNgay();
        
    }

    @Override
    public List<ViewModelDoanhThuThongKe> getListHoaDonDaChon(String nbd, String nkt) {
        
        List<ViewModelDoanhThuThongKe> list = new ArrayList<>();
        
        try {
            
            List<Object[]> lists = dt.getListHoaDonDaChon(nbd, nkt);
            
            for (Object[] a : lists) {
                
                ViewModelDoanhThuThongKe b = new ViewModelDoanhThuThongKe();
                
                b.setId(a[0].toString());
                b.setMa(a[1].toString());
                b.setNgaytt(a[2].toString());
                b.setTongtien(a[3].toString());

                list.add(b);
                
            }

            return list;
            
        } catch (Exception e) {
            
            return null;
            
        }
        
    }

    @Override
    public int getListTongtiendachon(String nbd, String nkt) {
        
        return dt.getListTongtiendachon(nbd, nkt);
        
    }

    @Override
    public int getListTongBoRaDaChon(String nbd, String nkt) {
        
        return dt.getListTongBoRaDaChon(nbd, nkt);
        
    }

    @Override
    public List<ViewModelDoanhThu> getList1() {
        
        List<ViewModelDoanhThu> list = new ArrayList<>();
        
        try {
            
            List<Object[]> lists = dt.getList1();
            
            for (Object[] a : lists) {
                
                ViewModelDoanhThu b = new ViewModelDoanhThu();
                
                b.setIdsp(a[0].toString());
                b.setTensanpham(a[1].toString());
                b.setSoluongton(a[2].toString());
                b.setSoluongban(a[3].toString());
                b.setTongtien(a[4].toString());

                list.add(b);
                
            }

            return list;
            
        } catch (Exception e) {
            
            return null;
            
        }
        
    }

    @Override
    public int getListSL() {
        
        try {
            return dt.getListSL();
        } catch (Exception e) {
            return 0;
        }
        
    }

    @Override
    public List<ViewModelDoanhThu> getList1(int i, int b) {

        List<ViewModelDoanhThu> list = new ArrayList<>();
        
        try {
            
            List<Object[]> lists = dt.getList1(i, b);
            
            for (Object[] a : lists) {
                
                ViewModelDoanhThu d = new ViewModelDoanhThu();
                
                d.setIdsp(a[0].toString());
                d.setTensanpham(a[1].toString());
                d.setSoluongton(a[2].toString());
                d.setSoluongban(a[3].toString());
                d.setTongtien(a[4].toString());

                list.add(d);
                
            }

            return list;
            
        } catch (Exception e) {
            
            return null;
            
        }
        
    }

    @Override
    public List<ViewModelDoanhThu> getListBdenL(int i, int b) {
        
      List<ViewModelDoanhThu> list = new ArrayList<>();
      
        try {
            
            List<Object[]> lists = dt.getListBdenL(i, b);
            
            for (Object[] a : lists) {
                
                ViewModelDoanhThu d = new ViewModelDoanhThu();
                
                d.setIdsp(a[0].toString());
                d.setTensanpham(a[1].toString());
                d.setSoluongton(a[2].toString());
                d.setSoluongban(a[3].toString());
                d.setTongtien(a[4].toString());

                list.add(d);
                
            }

            return list;
            
        } catch (Exception e) {
            
            return null;
            
        }
        
    }

    @Override
    public int getListSLBdenL() {
        
       try {
            return dt.getListSLBdenL();
        } catch (Exception e) {
            return 0;
        }
       
    }
    
}
