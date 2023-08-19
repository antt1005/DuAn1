/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.KhuyenMai;
import java.util.ArrayList;
import java.util.List;
import repositories.KhuyenMaiRepository;
import services.impl.IManageKhuyenMaiService;
import viewModel.KhuyenMaiViewModel;

/**
 *
 * @author ktkha
 */
public class KhuyenMaiService implements IManageKhuyenMaiService {

    KhuyenMaiRepository kmRepo = new KhuyenMaiRepository();

    @Override
    public List<KhuyenMaiViewModel> getListKMAll() {
        List<KhuyenMai> km = kmRepo.getListKMAll();
        try {
            List<KhuyenMaiViewModel> khuyenMaiViewModels = new ArrayList<>();
            for (KhuyenMai x : km) {
                KhuyenMaiViewModel i = new KhuyenMaiViewModel();
                i.setId(x.getId());
                i.setMa(x.getMa());
                i.setTenKhuyenMai(x.getTenKhuyenMai());
                i.setNgayBatDau(String.valueOf(x.getNgayBatDau()));
                i.setNgayKetThuc(String.valueOf(x.getNgayKetThuc()));
                i.setPhanTramKM(x.getPhanTramKM());
                khuyenMaiViewModels.add(i);
            }
            return khuyenMaiViewModels;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<KhuyenMaiViewModel> getListKMCon() {
        List<KhuyenMai> km = kmRepo.getListKMCon();
        try {
            List<KhuyenMaiViewModel> khuyenMaiViewModels = new ArrayList<>();
            for (KhuyenMai x : km) {
                KhuyenMaiViewModel i = new KhuyenMaiViewModel();
                i.setId(x.getId());
                i.setMa(x.getMa());
                i.setTenKhuyenMai(x.getTenKhuyenMai());
                i.setNgayBatDau(String.valueOf(x.getNgayBatDau()));
                i.setNgayKetThuc(String.valueOf(x.getNgayKetThuc()));
                i.setPhanTramKM(x.getPhanTramKM());
                khuyenMaiViewModels.add(i);
            }
            return khuyenMaiViewModels;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<KhuyenMaiViewModel> getListKMHet() {
        List<KhuyenMai> km = kmRepo.getListKMHet();
        try {
            List<KhuyenMaiViewModel> khuyenMaiViewModels = new ArrayList<>();
            for (KhuyenMai x : km) {
                KhuyenMaiViewModel i = new KhuyenMaiViewModel();
                i.setId(x.getId());
                i.setMa(x.getMa());
                i.setTenKhuyenMai(x.getTenKhuyenMai());
                i.setNgayBatDau(String.valueOf(x.getNgayBatDau()));
                i.setNgayKetThuc(String.valueOf(x.getNgayKetThuc()));
                i.setPhanTramKM(x.getPhanTramKM());
                khuyenMaiViewModels.add(i);
            }
            return khuyenMaiViewModels;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<KhuyenMaiViewModel> getListKMByDate(String ten) {
        List<KhuyenMai> km = kmRepo.getListKMByDate(ten);
        try {
            List<KhuyenMaiViewModel> khuyenMaiViewModels = new ArrayList<>();
            for (KhuyenMai x : km) {
                KhuyenMaiViewModel i = new KhuyenMaiViewModel();
                i.setId(x.getId());
                i.setMa(x.getMa());
                i.setTenKhuyenMai(x.getTenKhuyenMai());
                i.setNgayBatDau(String.valueOf(x.getNgayBatDau()));
                i.setNgayKetThuc(String.valueOf(x.getNgayKetThuc()));
                i.setPhanTramKM(x.getPhanTramKM());
                khuyenMaiViewModels.add(i);
            }
            return khuyenMaiViewModels;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(KhuyenMai km) {
        try {
            return kmRepo.add(km);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(KhuyenMai km) {
        try {
            return kmRepo.update(km);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(KhuyenMai km) {
        try {
            return kmRepo.delete(km);
        } catch (Exception e) {
            return false;
        }
    }

//    public static void main(String[] args) {
//        KhuyenMaiService kmSV = new KhuyenMaiService();
//        List<KhuyenMaiViewModel> sp = kmSV.getListKMAll();
//        for (KhuyenMaiViewModel v : sp) {
//            System.out.println(v.toString());
//        }
//
//    }

    @Override
    public List<KhuyenMaiViewModel> getListKMByDateContg(String ten) {
       List<KhuyenMai> km = kmRepo.getListKMByDateContg(ten);
        try {
            List<KhuyenMaiViewModel> khuyenMaiViewModels = new ArrayList<>();
            for (KhuyenMai x : km) {
                KhuyenMaiViewModel i = new KhuyenMaiViewModel();
                i.setId(x.getId());
                i.setMa(x.getMa());
                i.setTenKhuyenMai(x.getTenKhuyenMai());
                i.setNgayBatDau(String.valueOf(x.getNgayBatDau()));
                i.setNgayKetThuc(String.valueOf(x.getNgayKetThuc()));
                i.setPhanTramKM(x.getPhanTramKM());
                khuyenMaiViewModels.add(i);
            }
            return khuyenMaiViewModels;
        } catch (Exception e) {
            return null;
        }
    }

}
