/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainModels.ChucVu;

import domainModels.CuaHang;

import domainModels.NhanVien;
import java.io.File;
import java.io.FileOutputStream;

import java.sql.Date;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

import java.util.ArrayList;

import java.util.List;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.JFileChooser;

import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pagination.EventPagination;

import pagination.style.PaginationItemRenderStyle1;

import services.ChucVuSerivce;

import services.CuaHangService;

import services.NhanVienService;
import services.NhanVienService1;

import services.impl.IManageChucVuService;

import services.impl.IManageCuaHangService;

import services.impl.IManageNhanVienService;
import services.impl.IManageNhanVienService1;

import viewModel.ViewModelChucVu;

import viewModel.ViewModelCuaHang;

import viewModel.ViewModelNhanVien;
import viewModel.ViewModelNhanVien1;

import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class NhanVienFView extends javax.swing.JFrame {

    private IManageNhanVienService1 nhanVienService1 = new NhanVienService1();

    private IManageNhanVienService nhanVienService = new NhanVienService();

    private IManageCuaHangService cuaHangService = new CuaHangService();

    private IManageChucVuService chucVuService = new ChucVuSerivce();

    DefaultTableModel model = new DefaultTableModel();

    String IdNV;

    String TenNV;

    String CV;

    /**
     * Creates new form NhanVienFView
     */
    public NhanVienFView(String Id, String Ten, String cv) {
        initComponents();

        this.setDefaultCloseOperation(NhanVienFView.DO_NOTHING_ON_CLOSE);
        IdNV = Id;

        TenNV = Ten;

        CV = cv;

        setLocationRelativeTo(null);

        ngaySinh.getDateEditor().setEnabled(false);

        this.icon2.setVisible(false);
        loadCBB();

        loadTableDangLam(1);
        pagination1.setPaginationItemRender(new PaginationItemRenderStyle1());
        pagination1.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadTableDangLam(page);
            }
        });

        loadTableNghiLam();

        loadTbCuaHangPhanTrang(1);
        pagination2.setPaginationItemRender(new PaginationItemRenderStyle1());
        pagination2.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadTbCuaHangPhanTrang(page);
            }
        });

        loadTBCuaHangNgungHoatDong();

        loadTBChucVu();
//
//        loadPhanTu1();
//
        //  loadTBCuaHang();
//        loadPhanTuDAUTIEN();

        //  loadTable1NhanVien(1);
    }

    public void loadTableTKDangLamCV(String idCV) {
        model = (DefaultTableModel) TBBANG.getModel();
        List<ViewModelNhanVien1> nv1 = nhanVienService1.listtkChucVu(idCV);
        model.setColumnCount(0);

        model.addColumn("Id");

        model.addColumn("Mã");

        model.addColumn("Họ tên");

        model.addColumn("SĐT");

        model.addColumn("Địa chỉ");

        model.addColumn("Ngày sinh");

        model.addColumn("Cửa hàng");

        model.addColumn("Chức vụ");

        model.addColumn("Mật khẩu");

        model.addColumn("Email");

        model.addColumn("Trạng thái");

        model.setRowCount(0);
        for (ViewModelNhanVien1 viewModelNhanVien1 : nv1) {
            model.addRow(new Object[]{
                viewModelNhanVien1.getId(),
                viewModelNhanVien1.getMa(),
                viewModelNhanVien1.getHoTen(),
                viewModelNhanVien1.getSdt(),
                viewModelNhanVien1.getDiaChi(),
                viewModelNhanVien1.getNgaySinh(),
                viewModelNhanVien1.getIdCH(),
                viewModelNhanVien1.getIdCV(),
                viewModelNhanVien1.getMatKhau(),
                viewModelNhanVien1.getEmail(),
                viewModelNhanVien1.getTrangThai() == 1 ? "Đang làm" : "Nghỉ làm"
            });

        }
    }

    public void loadTableTKCH(String idCH) {
        model = (DefaultTableModel) TBBANG.getModel();
        List<ViewModelNhanVien1> nv1 = nhanVienService1.listtkCuaHang(idCH);
        model.setColumnCount(0);

        model.addColumn("Id");
        model.addColumn("Mã");
        model.addColumn("Họ tên");
        model.addColumn("SĐT");
        model.addColumn("Địa chỉ");
        model.addColumn("Ngày sinh");
        model.addColumn("Cửa hàng");
        model.addColumn("Chức vụ");
        model.addColumn("Mật khẩu");
        model.addColumn("Email");
        model.addColumn("Trạng thái");

        model.setRowCount(0);
        for (ViewModelNhanVien1 viewModelNhanVien1 : nv1) {
            model.addRow(new Object[]{
                viewModelNhanVien1.getId(),
                viewModelNhanVien1.getMa(),
                viewModelNhanVien1.getHoTen(),
                viewModelNhanVien1.getSdt(),
                viewModelNhanVien1.getDiaChi(),
                viewModelNhanVien1.getNgaySinh(),
                viewModelNhanVien1.getIdCH(),
                viewModelNhanVien1.getIdCV(),
                viewModelNhanVien1.getMatKhau(),
                viewModelNhanVien1.getEmail(),
                viewModelNhanVien1.getTrangThai() == 1 ? "Đang làm" : "Nghỉ làm"
            });

        }
    }

    public String getCBBTKCuaHang() {
        String id = null;
        List<ViewModelCuaHang> a = cuaHangService.getAll();

        for (ViewModelCuaHang viewModelCuaHang : a) {

            if (cbbtimKiemCuaHang.getSelectedItem().equals(viewModelCuaHang.getTenCuaHang())) {
                id = viewModelCuaHang.getId();
            }

        }
        return id;
    }

    public String getCBBCVTK() {

        String id = null;

        List<ViewModelChucVu> a = chucVuService.getAll();

        for (ViewModelChucVu viewModelChucVu : a) {

            if (cbbtimKiem.getSelectedItem().equals(viewModelChucVu.getTen())) {

                id = viewModelChucVu.getId();
            }

        }
        return id;
    }

    public void loadTableDangLam(int page) {
        try {
            int limit = 5;
            int count = 0;
            List<ViewModelNhanVien1> nv = nhanVienService1.getAll((page - 1) * limit, limit);
            if (nv == null) {

                System.out.println("1");
                return;
            }
            count = nhanVienService1.getRow((page - 1) * limit, limit);

            model = (DefaultTableModel) TBBANG.getModel();

            model.setColumnCount(0);

            model.addColumn("Id");

            model.addColumn("Mã");

            model.addColumn("Họ tên");

            model.addColumn("SĐT");

            model.addColumn("Địa chỉ");

            model.addColumn("Ngày sinh");

            model.addColumn("Cửa hàng");

            model.addColumn("Chức vụ");

            model.addColumn("Mật khẩu");

            model.addColumn("Email");

            model.addColumn("Trạng thái");

            model.setRowCount(0);

            for (ViewModelNhanVien1 viewModelNhanVien : nv) {
                model.addRow(new Object[]{
                    viewModelNhanVien.getId(), viewModelNhanVien.getMa(), viewModelNhanVien.getHoTen(),
                    viewModelNhanVien.getSdt(), viewModelNhanVien.getDiaChi(), viewModelNhanVien.getNgaySinh(),
                    viewModelNhanVien.getIdCH(), viewModelNhanVien.getIdCV(), viewModelNhanVien.getMatKhau(), viewModelNhanVien.getEmail(),
                    viewModelNhanVien.getTrangThai() == 1 ? "Đang làm" : "Nghỉ làm"
                });

            }
            int totalPage = (int) Math.ceil(count / limit);
            if (count / limit == 0) {
                pagination1.setPagegination(page, totalPage);
            } else {

                pagination1.setPagegination(page, totalPage + 1);

            }
        } catch (Exception e) {
        }
    }

//      public void loadTableDangLam(int page) {
//        try {
//            int limit = 5;
//            int count = 0;
//            List<ViewModelNhanVien1> nv = nhanVienService1.getAll((page - 1) * limit, limit);
//            if (nv == null) {
//
//                System.out.println("1");
//                return;
//            }
//            count = nhanVienService1.getRow((page - 1) * limit, limit);
//
//            model = (DefaultTableModel) TBBANG.getModel();
//
//            model.setColumnCount(0);
//
//            model.addColumn("Id");
//
//            model.addColumn("Mã");
//
//            model.addColumn("Họ tên");
//
//            model.addColumn("SĐT");
//
//            model.addColumn("Địa chỉ");
//
//            model.addColumn("Ngày sinh");
//
//            model.addColumn("Cửa hàng");
//
//            model.addColumn("Chức vụ");
//
//            model.addColumn("Mật khẩu");
//
//            model.addColumn("Email");
//
//            model.addColumn("Trạng thái");
//
//            model.setRowCount(0);
//
//            for (ViewModelNhanVien1 viewModelNhanVien : nv) {
//                model.addRow(new Object[]{
//                    viewModelNhanVien.getId(), viewModelNhanVien.getMa(), viewModelNhanVien.getHoTen(),
//                    viewModelNhanVien.getSdt(), viewModelNhanVien.getDiaChi(), viewModelNhanVien.getNgaySinh(),
//                    viewModelNhanVien.getIdCH(), viewModelNhanVien.getIdCV(), viewModelNhanVien.getMatKhau(), viewModelNhanVien.getEmail(),
//                    viewModelNhanVien.getTrangThai() == 1 ? "Đang làm" : "Nghỉ làm"
//                });
//
//            }
//            int totalPage = (int) Math.ceil(count / limit);
//            if (count / limit == 0) {
//                pagination1.setPagegination(page, totalPage);
//            } else {
//
//                pagination1.setPagegination(page, totalPage + 1);
//
//            }
//        } catch (Exception e) {
//        }
//    }
//    
//    
    public void loadTableNghiLam() {
        model = (DefaultTableModel) tbbangnghiviec.getModel();
        List<ViewModelNhanVien1> nv1 = nhanVienService1.getListNVNghiLam();
        model.setColumnCount(0);

        model.addColumn("Id");
        model.addColumn("Mã");
        model.addColumn("Họ tên");
        model.addColumn("SĐT");
        model.addColumn("Địa chỉ");
        model.addColumn("Ngày sinh");
        model.addColumn("Cửa hàng");
        model.addColumn("Chức vụ");
        model.addColumn("Mật khẩu");
        model.addColumn("Email");
        model.addColumn("Trạng thái");

        model.setRowCount(0);
        for (ViewModelNhanVien1 viewModelNhanVien1 : nv1) {
            model.addRow(new Object[]{
                viewModelNhanVien1.getId(),
                viewModelNhanVien1.getMa(),
                viewModelNhanVien1.getHoTen(),
                viewModelNhanVien1.getSdt(),
                viewModelNhanVien1.getDiaChi(),
                viewModelNhanVien1.getNgaySinh(),
                viewModelNhanVien1.getIdCH(),
                viewModelNhanVien1.getIdCV(),
                viewModelNhanVien1.getMatKhau(),
                viewModelNhanVien1.getEmail(),
                viewModelNhanVien1.getTrangThai() == 1 ? "Đang làm" : "Nghỉ làm"
            });

        }

    }

    public void loadTableAll() {
        model = (DefaultTableModel) TBBANG.getModel();
        List<ViewModelNhanVien1> nv1 = nhanVienService1.getListNVDangLam();
        model.setColumnCount(0);

        model.addColumn("Id");
        model.addColumn("Mã");
        model.addColumn("Họ tên");
        model.addColumn("SĐT");
        model.addColumn("Địa chỉ");
        model.addColumn("Ngày sinh");
        model.addColumn("Cửa hàng");
        model.addColumn("Chức vụ");
        model.addColumn("Mật khẩu");
        model.addColumn("Email");
        model.addColumn("Trạng thái");

        model.setRowCount(0);
        for (ViewModelNhanVien1 viewModelNhanVien1 : nv1) {
            model.addRow(new Object[]{
                viewModelNhanVien1.getId(),
                viewModelNhanVien1.getMa(),
                viewModelNhanVien1.getHoTen(),
                viewModelNhanVien1.getSdt(),
                viewModelNhanVien1.getDiaChi(),
                viewModelNhanVien1.getNgaySinh(),
                viewModelNhanVien1.getIdCH(),
                viewModelNhanVien1.getIdCV(),
                viewModelNhanVien1.getMatKhau(),
                viewModelNhanVien1.getEmail(),
                viewModelNhanVien1.getTrangThai() == 1 ? "Đang làm" : "Nghỉ làm"
            });

        }

    }

    public void loadTbCuaHangPhanTrang(int page) {
        int limit = 3;
        int count = 0;
        List<ViewModelCuaHang> sp = cuaHangService.getListSP((page - 1) * limit, limit);
        if (sp == null) {
            return;
        }
        count = cuaHangService.row();

        model = (DefaultTableModel) tbbang.getModel();
        model.setColumnCount(0);

        model.addColumn("Id");

        model.addColumn("Mã");

        model.addColumn("Tên");

        model.addColumn("Địa chỉ");

        model.addColumn("Trạng thái");

        model.setRowCount(0);

        for (ViewModelCuaHang v : sp) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(),
                v.getTenCuaHang(),
                v.getDiaChi(),
                v.getTrangThai() == 1 ? "Đang hoạt động" : "Ngừng hoạt động"
            });
        }

        int totalPage = (int) Math.ceil(count / limit);
        if (count / limit != 0) {
            pagination2.setPagegination(page, totalPage + 1);
        } else if (count / limit == 0) {
            pagination2.setPagegination(page, totalPage);
        }

    }

    public void loadTBCuaHangNgungHoatDong() {

        model = (DefaultTableModel) tbbangNgungHoatDong.getModel();

        model.setColumnCount(0);

        model.addColumn("ID");

        model.addColumn("Mã");

        model.addColumn("Tên");

        model.addColumn("Địa chỉ");

        model.addColumn("Trạng thái");

        model.setRowCount(0);

        List<ViewModelCuaHang> sp = cuaHangService.cuaHangNgungHoatDong();

        for (ViewModelCuaHang v : sp) {

            model.addRow(new Object[]{
                v.getId(), v.getMa(),
                v.getTenCuaHang(),
                v.getDiaChi(),
                v.getTrangThai() == 1 ? "Đang họat động" : "Ngừng hoạt động"
            });
        }
    }

    public void loadTableTimKiem(String ten) {
        model = (DefaultTableModel) TBBANG.getModel();
        model.setColumnCount(0);

        model.addColumn("Id");

        model.addColumn("Mã");

        model.addColumn("Họ tên");

        model.addColumn("SĐT");

        model.addColumn("Địa chỉ");

        model.addColumn("Ngày sinh");

        model.addColumn("Cửa hàng");

        model.addColumn("Chức vụ");

        model.addColumn("Mật khẩu");

        model.addColumn("Email");

        model.setRowCount(0);
        List<ViewModelNhanVien> nv = nhanVienService.listtk(ten);
        for (ViewModelNhanVien viewModelNhanVien : nv) {
            model.addRow(new Object[]{
                viewModelNhanVien.getId(), viewModelNhanVien.getMa(), viewModelNhanVien.getHoTen(),
                viewModelNhanVien.getSdt(), viewModelNhanVien.getDiaChi(), viewModelNhanVien.getNgaySinh(),
                viewModelNhanVien.getIdCH(), viewModelNhanVien.getIdCV(), viewModelNhanVien.getMatKhau(), viewModelNhanVien.getEmail()
            });

        }
    }

    public void loadTableTimKiemSDT(String sdt) {
        model = (DefaultTableModel) tbbangnghiviec.getModel();
        model.setColumnCount(0);

        model.addColumn("Id");

        model.addColumn("Mã");

        model.addColumn("Họ tên");

        model.addColumn("SĐT");

        model.addColumn("Địa chỉ");

        model.addColumn("Ngày sinh");

        model.addColumn("Cửa hàng");

        model.addColumn("Chức vụ");

        model.addColumn("Mật khẩu");

        model.addColumn("Email");

        model.setRowCount(0);
        List<ViewModelNhanVien1> nv = nhanVienService1.listtkSDT(sdt);
        for (ViewModelNhanVien1 viewModelNhanVien : nv) {
            model.addRow(new Object[]{
                viewModelNhanVien.getId(),
                viewModelNhanVien.getMa(),
                viewModelNhanVien.getHoTen(),
                viewModelNhanVien.getSdt(),
                viewModelNhanVien.getDiaChi(),
                viewModelNhanVien.getNgaySinh(),
                viewModelNhanVien.getIdCH(),
                viewModelNhanVien.getIdCV(),
                viewModelNhanVien.getMatKhau(),
                viewModelNhanVien.getEmail(),
                viewModelNhanVien.getTrangThai() == 1 ? "Đang làm việc" : "Nghỉ việc"
            });

        }
    }

    public String getCHCBB() {

        String id = null;
        List<ViewModelCuaHang> a = cuaHangService.getAll();

        for (ViewModelCuaHang viewModelCuaHang : a) {

            if (CBCCUAHANG.getSelectedItem().equals(viewModelCuaHang.getTenCuaHang())) {
                id = viewModelCuaHang.getId();
            }

        }
        return id;
    }

    public String getCBBCV() {

        String id = null;

        List<ViewModelChucVu> a = chucVuService.getAll();

        for (ViewModelChucVu viewModelChucVu : a) {

            if (CBCCHUCVU.getSelectedItem().equals(viewModelChucVu.getTen())) {

                id = viewModelChucVu.getId();
            }

        }
        return id;
    }

    public void loadCBB() {

        List<ViewModelCuaHang> a = cuaHangService.getAll();
        CBCCUAHANG.removeAllItems();

        for (ViewModelCuaHang viewModelCuaHang : a) {

            CBCCUAHANG.addItem(viewModelCuaHang.getTenCuaHang());

        }

        List<ViewModelCuaHang> a1 = cuaHangService.getAll();

        for (ViewModelCuaHang viewModelCuaHang : a) {

            cbbcuaHangNghiViec1.addItem(viewModelCuaHang.getTenCuaHang());

        }

        List<ViewModelChucVu> b = chucVuService.getAll();
        CBCCHUCVU.removeAllItems();

        for (ViewModelChucVu viewModelChucVu : b) {

            CBCCHUCVU.addItem(viewModelChucVu.getTen());

        }

        List<ViewModelChucVu> b1 = chucVuService.getAll();
        cbbchucVuNghiViec1.removeAllItems();

        for (ViewModelChucVu viewModelChucVu : b) {

            cbbchucVuNghiViec1.addItem(viewModelChucVu.getTen());

        }

        List<ViewModelCuaHang> c = cuaHangService.getAll();
        cbbtimKiemCuaHang.removeAllItems();
        // String x = null;
        System.out.println(c.size());
        // cbbtimKiemCuaHang.remove(icon1);

        cbbtimKiemCuaHang.addItem("All");

        for (ViewModelCuaHang viewModelCuaHang : c) {

            cbbtimKiemCuaHang.addItem(viewModelCuaHang.getTenCuaHang());

        }

    }

    public void loadTBChucVu() {

        model = (DefaultTableModel) tbbangcv.getModel();

        model.setColumnCount(0);

        model.addColumn("ID");

        model.addColumn("Mã");

        model.addColumn("Tên");
        model.setRowCount(0);

        List<ViewModelChucVu> sp = chucVuService.getAll();

        for (ViewModelChucVu v : sp) {

            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTen()
            });
        }
    }

    public void loadTBChucVuTimKiem(String ten) {

        model = (DefaultTableModel) tbbangcv.getModel();

        model.setColumnCount(0);

        model.addColumn("ID");

        model.addColumn("Mã");

        model.addColumn("Tên");

        model.setRowCount(0);

        List<ViewModelChucVu> sp = chucVuService.getListSPByName(ten);

        for (ViewModelChucVu v : sp) {

            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTen()
            });
        }
    }

    public void loadPhanTu1() {

        List<ViewModelChucVu> sp = chucVuService.getAll();

        if (sp.isEmpty()) {

            return;
        }
        int index = 0;

        txtID.setText(tbbangcv.getValueAt(index, 0).toString());

        txtma.setText(tbbangcv.getValueAt(index, 1).toString());

        txttenChucVu.setText(tbbangcv.getValueAt(index, 2).toString());
    }

    public void loadTable1NhanVien(int page) {

        try {
            int limit = 5;
            int count = 0;
            List<ViewModelNhanVien> nv = nhanVienService.getAll((page - 1) * limit, limit);
            if (nv == null) {
                System.out.println("1");
                return;
            }
            count = nhanVienService.getRow((page - 1) * limit, limit);

            int index = 0;

            String ns = TBBANG.getValueAt(index, 5).toString();

            // Date ngay = Date.valueOf(ngaySinh);
            SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date result;

            result = sdt.parse(ns);

            ngaySinh.setDate(result);

            TXTID.setText(TBBANG.getValueAt(index, 0).toString());

            TXTMA.setText(TBBANG.getValueAt(index, 1).toString());

            TXTHOTEN.setText(TBBANG.getValueAt(index, 2).toString());

            TXTSDT.setText(TBBANG.getValueAt(index, 3).toString());

            TXTDIACHI.setText(TBBANG.getValueAt(index, 4).toString());

            CBCCUAHANG.setSelectedItem(TBBANG.getValueAt(index, 6).toString());

            CBCCHUCVU.setSelectedItem(TBBANG.getValueAt(index, 7).toString());

            txtmatKhau.setText(TBBANG.getValueAt(index, 8).toString());

            txtemailNhanVien.setText(TBBANG.getValueAt(index, 9).toString().trim());

//            int totalPage = (int) Math.ceil(count / limit);
//            if (count / limit == 0) {
//                pagination1.setPagegination(page, totalPage);
//            } else {
//
//                pagination1.setPagegination(page, totalPage + 1);
//
//            }
        } catch (Exception e) {
        }
    }

    public boolean checkTenCHUCVU() { //check ten sp

        String ten = txttenChucVu.getText();

        List<ViewModelChucVu> sp = chucVuService.getAll();

        for (ViewModelChucVu v : sp) {

            if (ten.equals(v.getTen())) {

                JOptionPane.showMessageDialog(this, "Tên đã tồn tại!");
                return false;
            }
        }
        return true;
    }

    public void loadTBCuaHang() {

        model = (DefaultTableModel) tbbang.getModel();

        model.setColumnCount(0);

        model.addColumn("ID");

        model.addColumn("Mã");

        model.addColumn("Tên");

        model.addColumn("Địa chỉ");

        model.setRowCount(0);

        List<ViewModelCuaHang> sp = cuaHangService.getAll();

        for (ViewModelCuaHang v : sp) {

            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTenCuaHang(), v.getDiaChi()
            });
        }
    }

    public void loadTBCuaHang(String ten) {

        model = (DefaultTableModel) tbbang.getModel();

        model.setColumnCount(0);

        model.addColumn("ID");

        model.addColumn("Mã");

        model.addColumn("Tên");

        model.addColumn("Địa chỉ");

        model.setRowCount(0);

        List<ViewModelCuaHang> sp = cuaHangService.getListSPByName(ten);

        for (ViewModelCuaHang v : sp) {

            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTenCuaHang(), v.getDiaChi()
            });
        }
    }

    public void loadPhanTuDAUTIEN() {
        List<ViewModelCuaHang> sp = cuaHangService.getAll();

        if (sp.isEmpty()) {
            return;
        }
        int index = 0;

        txtIDCH.setText(tbbang.getValueAt(index, 0).toString());

        txtmaCH.setText(tbbang.getValueAt(index, 1).toString());

        txttenCH.setText(tbbang.getValueAt(index, 2).toString());

        txtdiaChi.setText(tbbang.getValueAt(index, 3).toString());
    }

    public boolean checkTen() { //check ten sp
        String ten = txttenChucVu.getText();

        List<ViewModelCuaHang> sp = cuaHangService.getAll();

        for (ViewModelCuaHang v : sp) {

            if (ten.equals(v.getTenCuaHang())) {

                JOptionPane.showMessageDialog(this, "Tên đã tồn tại!");
                return false;
            }
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel30 = new javax.swing.JLabel();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel26 = new javax.swing.JLabel();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBBANG = new javax.swing.JTable();
        CBCCUAHANG = new javax.swing.JComboBox<>();
        CBCCHUCVU = new javax.swing.JComboBox<>();
        txttimKiemnhanvien = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TXTID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TXTMA = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TXTSDT = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TXTDIACHI = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        TXTHOTEN = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ngaySinh = new com.toedter.calendar.JDateChooser();
        txtemailNhanVien = new javax.swing.JTextField();
        pagination1 = new pagination.Pagination();
        jButton1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        rdodangLam = new javax.swing.JRadioButton();
        rdonghiLam = new javax.swing.JRadioButton();
        icon2 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        icon1 = new javax.swing.JLabel();
        txtmatKhau = new javax.swing.JPasswordField();
        jLabel19 = new javax.swing.JLabel();
        cbbtimKiem = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cbbtimKiemCuaHang = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        btnxoa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnsua = new javax.swing.JButton();
        btnthem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbbangcv = new javax.swing.JTable();
        txttimKiemchucvu = new javax.swing.JTextField();
        txttenChucVu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtma = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtdiaChi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtmaCH = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnxoa1 = new javax.swing.JButton();
        txtIDCH = new javax.swing.JTextField();
        btnsua1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        btnthem1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbbang = new javax.swing.JTable();
        txttimKiemCuaHang = new javax.swing.JTextField();
        txttenCH = new javax.swing.JTextField();
        pagination2 = new pagination.Pagination();
        jButton3 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        rdodangHoatDong = new javax.swing.JRadioButton();
        rdongungHoatDong = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        txtidNghiViec1 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txtdienThoaiNghiViec1 = new javax.swing.JTextField();
        txtdiaChiNghiViec1 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtmaNghiViec1 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txthoTenNghiViec1 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txtemailNghiViec1 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        cbbcuaHangNghiViec1 = new javax.swing.JComboBox<>();
        jLabel61 = new javax.swing.JLabel();
        cbbchucVuNghiViec1 = new javax.swing.JComboBox<>();
        txtmatKhauNghiViec1 = new javax.swing.JPasswordField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        ngaySinh1 = new com.toedter.calendar.JDateChooser();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbbangnghiviec = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        rdodangLamViec1 = new javax.swing.JRadioButton();
        rdoNghiViec1 = new javax.swing.JRadioButton();
        btnback1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txttimKiemSDT = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbbangNgungHoatDong = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        txtidCHNgungHD = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtmaCHNgungHD = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txttenCHNgungHD = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        rdoCHDangHD = new javax.swing.JRadioButton();
        rdoCHNgungHD = new javax.swing.JRadioButton();
        btnback = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        txtdiaChiNgungHD = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jLabel30.setText("jLabel30");

        jLabel26.setText("jLabel26");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));

        TBBANG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Ma", "HoTen", "sdt", "DiaChi", "NgaySinh", "TenCV", "TenCuaHang", "Email"
            }
        ));
        TBBANG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBBANGMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBBANG);

        CBCCUAHANG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBCCUAHANGActionPerformed(evt);
            }
        });

        txttimKiemnhanvien.setText("Nhập tên cần tìm kiếm");
        txttimKiemnhanvien.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttimKiemnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttimKiemnhanvienMouseClicked(evt);
            }
        });
        txttimKiemnhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimKiemnhanvienActionPerformed(evt);
            }
        });
        txttimKiemnhanvien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimKiemnhanvienKeyReleased(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(255, 204, 204));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setText("Add");
        btnThem.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 204, 204));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setText("Delete");
        btnXoa.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 204, 204));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setText("Update");
        btnSua.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("ID");

        TXTID.setBackground(new java.awt.Color(255, 204, 204));
        TXTID.setActionCommand("<Not Set>");
        TXTID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Ma");

        TXTMA.setBackground(new java.awt.Color(255, 204, 204));
        TXTMA.setActionCommand("<Not Set>");
        TXTMA.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        TXTMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTMAActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("SÐT");

        TXTSDT.setBackground(new java.awt.Color(255, 204, 204));
        TXTSDT.setActionCommand("<Not Set>");
        TXTSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("DiaChi");

        TXTDIACHI.setBackground(new java.awt.Color(255, 204, 204));
        TXTDIACHI.setActionCommand("<Not Set>");
        TXTDIACHI.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        TXTDIACHI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTDIACHIActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("HoTen");

        TXTHOTEN.setBackground(new java.awt.Color(255, 204, 204));
        TXTHOTEN.setActionCommand("<Not Set>");
        TXTHOTEN.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        TXTHOTEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTHOTENActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("NgaySinh");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("CuaHang");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("TenCV");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Email");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mật khẩu");

        ngaySinh.setDateFormatString("yyyy-MM-dd");

        txtemailNhanVien.setBackground(new java.awt.Color(255, 204, 204));
        txtemailNhanVien.setActionCommand("<Not Set>");
        txtemailNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtemailNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailNhanVienActionPerformed(evt);
            }
        });

        pagination1.setOpaque(false);

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jButton1.setText("Xuất File Excel");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Trạng thái");

        buttonGroup1.add(rdodangLam);
        rdodangLam.setText("Đang làm");

        buttonGroup1.add(rdonghiLam);
        rdonghiLam.setText("Nghỉ làm");

        icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/view.png"))); // NOI18N
        icon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                icon2MousePressed(evt);
            }
        });

        icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hide.png"))); // NOI18N
        icon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                icon1MousePressed(evt);
            }
        });

        txtmatKhau.setBackground(new java.awt.Color(255, 204, 204));
        txtmatKhau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Lọc theo chức vụ");

        cbbtimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Quan Ly", "Nhan Vien" }));
        cbbtimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbtimKiemActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Lọc theo cửa hàng");

        cbbtimKiemCuaHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cbbtimKiemCuaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbtimKiemCuaHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txttimKiemnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbtimKiemCuaHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbtimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TXTID, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(70, 70, 70)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TXTSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(100, 100, 100)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CBCCUAHANG, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(txtemailNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(45, 45, 45)
                                    .addComponent(txtmatKhau))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TXTMA, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(70, 70, 70)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TXTDIACHI, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TXTHOTEN, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(70, 70, 70)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addGap(10, 10, 10)
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(140, 140, 140)
                                            .addComponent(jLabel1)))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(100, 100, 100)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CBCCHUCVU, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(rdodangLam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(rdonghiLam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(12, 12, 12)
                                            .addComponent(jLabel18))))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(icon1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                        .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(24, 24, 24)
                                            .addComponent(jButton1))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGap(15, 15, 15))))
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(265, 265, 265))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 37, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(TXTID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(TXTSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(CBCCUAHANG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(CBCCHUCVU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(TXTMA, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(TXTDIACHI, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(TXTHOTEN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rdodangLam)
                                        .addComponent(rdonghiLam))
                                    .addComponent(ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(22, 22, 22))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)))
                        .addComponent(txtemailNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtmatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(icon1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimKiemnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(cbbtimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cbbtimKiemCuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jTabbedPane2.addTab("Nhân Viên", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));
        jPanel4.setBorder(new javax.swing.border.MatteBorder(null));

        btnxoa.setBackground(new java.awt.Color(255, 204, 204));
        btnxoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("ID");

        btnsua.setBackground(new java.awt.Color(255, 204, 204));
        btnsua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsua.setText("Sửa");
        btnsua.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnthem.setBackground(new java.awt.Color(255, 204, 204));
        btnthem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnthem.setText("Thêm");
        btnthem.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        tbbangcv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbbangcv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbangcvMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbbangcv);

        txttimKiemchucvu.setText("   Nhập tên cần tìm kiếm");
        txttimKiemchucvu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttimKiemchucvu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttimKiemchucvuMouseClicked(evt);
            }
        });
        txttimKiemchucvu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimKiemchucvuActionPerformed(evt);
            }
        });
        txttimKiemchucvu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimKiemchucvuKeyReleased(evt);
            }
        });

        txttenChucVu.setBackground(new java.awt.Color(255, 204, 204));
        txttenChucVu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tên");

        txtma.setBackground(new java.awt.Color(255, 204, 204));
        txtma.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Mã");

        txtID.setBackground(new java.awt.Color(255, 204, 204));
        txtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jButton2.setBackground(new java.awt.Color(255, 204, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Xuất File Excel");
        jButton2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txttimKiemchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txttenChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtma))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtID)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttimKiemchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttenChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(300, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Chức Vụ", jPanel4);

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));

        txtdiaChi.setBackground(new java.awt.Color(255, 204, 204));
        txtdiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtdiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiaChiActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Địa chỉ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tên");

        txtmaCH.setBackground(new java.awt.Color(255, 204, 204));
        txtmaCH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Mã");

        btnxoa1.setBackground(new java.awt.Color(255, 204, 204));
        btnxoa1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnxoa1.setText("Xóa");
        btnxoa1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnxoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoa1ActionPerformed(evt);
            }
        });

        txtIDCH.setBackground(new java.awt.Color(255, 204, 204));
        txtIDCH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        btnsua1.setBackground(new java.awt.Color(255, 204, 204));
        btnsua1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsua1.setText("Sửa");
        btnsua1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnsua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsua1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("ID");

        btnthem1.setBackground(new java.awt.Color(255, 204, 204));
        btnthem1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnthem1.setText("Thêm");
        btnthem1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnthem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthem1ActionPerformed(evt);
            }
        });

        tbbang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbbang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbbang);

        txttimKiemCuaHang.setText("  Nhập tên cần tìm kiếm");
        txttimKiemCuaHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttimKiemCuaHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttimKiemCuaHangMouseClicked(evt);
            }
        });
        txttimKiemCuaHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimKiemCuaHangKeyReleased(evt);
            }
        });

        txttenCH.setBackground(new java.awt.Color(255, 204, 204));
        txttenCH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        pagination2.setOpaque(false);

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jButton3.setText("Xuất File Excel");
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("Trạng thái");

        buttonGroup2.add(rdodangHoatDong);
        rdodangHoatDong.setText("Đang họat động");

        buttonGroup2.add(rdongungHoatDong);
        rdongungHoatDong.setText("Ngừng hoạt động");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnthem1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnsua1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(btnxoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pagination2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(235, 235, 235)))
                .addGap(0, 55, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(30, 30, 30)
                        .addComponent(rdodangHoatDong)
                        .addGap(61, 61, 61)
                        .addComponent(rdongungHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtmaCH, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txttenCH, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtdiaChi))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtIDCH, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(68, 68, 68)
                        .addComponent(txttimKiemCuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtIDCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttimKiemCuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtmaCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txttenCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtdiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(rdodangHoatDong)
                    .addComponent(rdongungHoatDong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsua1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(pagination2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );

        jTabbedPane2.addTab("Cửa Hàng", jPanel2);

        jPanel7.setBackground(new java.awt.Color(255, 204, 204));

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel54.setText("ID");

        txtidNghiViec1.setBackground(new java.awt.Color(255, 204, 204));
        txtidNghiViec1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel55.setText("SĐT");

        txtdienThoaiNghiViec1.setBackground(new java.awt.Color(255, 204, 204));
        txtdienThoaiNghiViec1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        txtdiaChiNghiViec1.setBackground(new java.awt.Color(255, 204, 204));
        txtdiaChiNghiViec1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel56.setText("Địa chỉ");

        txtmaNghiViec1.setBackground(new java.awt.Color(255, 204, 204));
        txtmaNghiViec1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel57.setText("Mã");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel58.setText("Họ tên");

        txthoTenNghiViec1.setBackground(new java.awt.Color(255, 204, 204));
        txthoTenNghiViec1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txthoTenNghiViec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthoTenNghiViec1ActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel59.setText("Email");

        txtemailNghiViec1.setBackground(new java.awt.Color(255, 204, 204));
        txtemailNghiViec1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel60.setText("Cửa hàng");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel61.setText("Chức vụ");

        txtmatKhauNghiViec1.setBackground(new java.awt.Color(255, 204, 204));
        txtmatKhauNghiViec1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel62.setText("Mật khẩu");

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel63.setText("NgaySinh");

        ngaySinh1.setDateFormatString("yyyy-MM-dd");

        tbbangnghiviec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbbangnghiviec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbangnghiviecMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbbangnghiviec);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Trạng thái");

        buttonGroup3.add(rdodangLamViec1);
        rdodangLamViec1.setText("Đang làm việc");

        buttonGroup3.add(rdoNghiViec1);
        rdoNghiViec1.setText("Nghỉ việc");

        btnback1.setBackground(new java.awt.Color(255, 204, 204));
        btnback1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnback1.setText("Back");
        btnback1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnback1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnback1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 204, 204));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jButton4.setText("Xuất File Excel");
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txttimKiemSDT.setText("Nhập số điện thoại cần tìm");
        txttimKiemSDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttimKiemSDTMouseClicked(evt);
            }
        });
        txttimKiemSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimKiemSDTKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel22)
                        .addGap(44, 44, 44)
                        .addComponent(rdodangLamViec1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtidNghiViec1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel60)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbbcuaHangNghiViec1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtmaNghiViec1))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txthoTenNghiViec1))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtemailNghiViec1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.LEADING)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtdienThoaiNghiViec1)
                                .addComponent(txtdiaChiNghiViec1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbbchucVuNghiViec1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtmatKhauNghiViec1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ngaySinh1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoNghiViec1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnback1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(txttimKiemSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(txttimKiemSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(txtidNghiViec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55)
                    .addComponent(txtdienThoaiNghiViec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(txtmaNghiViec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56)
                    .addComponent(txtdiaChiNghiViec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel58)
                            .addComponent(txthoTenNghiViec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(txtemailNghiViec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62)
                            .addComponent(txtmatKhauNghiViec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ngaySinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel60)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbcuaHangNghiViec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel61)
                        .addComponent(cbbchucVuNghiViec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(rdodangLamViec1)
                    .addComponent(rdoNghiViec1))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnback1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        jTabbedPane1.addTab("Nhân viên nghỉ việc", jPanel6);

        jPanel8.setBackground(new java.awt.Color(255, 204, 204));

        tbbangNgungHoatDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbbangNgungHoatDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbangNgungHoatDongMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbbangNgungHoatDong);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("ID");

        txtidCHNgungHD.setBackground(new java.awt.Color(255, 204, 204));
        txtidCHNgungHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("Mã");

        txtmaCHNgungHD.setBackground(new java.awt.Color(255, 204, 204));
        txtmaCHNgungHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("Tên");

        txttenCHNgungHD.setBackground(new java.awt.Color(255, 204, 204));
        txttenCHNgungHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("Trạng thái");

        buttonGroup4.add(rdoCHDangHD);
        rdoCHDangHD.setText("Đang hoạt động");

        buttonGroup4.add(rdoCHNgungHD);
        rdoCHNgungHD.setText("Ngừng hoạt động");

        btnback.setBackground(new java.awt.Color(255, 204, 204));
        btnback.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnback.setText("Back");
        btnback.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("Địa chỉ");

        txtdiaChiNgungHD.setBackground(new java.awt.Color(255, 204, 204));
        txtdiaChiNgungHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jButton5.setBackground(new java.awt.Color(255, 204, 204));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jButton5.setText("Xuất File Excel");
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoCHDangHD)
                        .addGap(60, 60, 60)
                        .addComponent(rdoCHNgungHD))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtidCHNgungHD, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtmaCHNgungHD)
                            .addComponent(txttenCHNgungHD)
                            .addComponent(txtdiaChiNgungHD, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))))
                .addContainerGap(114, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtidCHNgungHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtmaCHNgungHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txttenCHNgungHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtdiaChiNgungHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(rdoCHDangHD)
                    .addComponent(rdoCHNgungHD))
                .addGap(107, 107, 107)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cửa hàng ngừng hoạt động", jPanel8);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 745, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Quản lý chung", jPanel5);

        jButton6.setBackground(new java.awt.Color(255, 204, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("BACK");
        jButton6.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(575, 575, 575))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txttimKiemnhanvienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimKiemnhanvienKeyReleased
        // TODO add your handling code here:

//     String timKiemTenNV = txttimKiemnhanvien.getText();
//        List<ViewModelNhanVien> dg = nhanVienService.getAll(2, 5);
//        for (ViewModelNhanVien v : dg) {
//            if (timKiemTenNV.equals(v.getHoTen())) {
//                JOptionPane.showMessageDialog(this, "Không tìm thấy tên cần tìm!");
//                return;
//            }
//        }
        loadTableTimKiem(txttimKiemnhanvien.getText());
    }//GEN-LAST:event_txttimKiemnhanvienKeyReleased

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

        if (TXTSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
            return;
        }

        if (TXTHOTEN.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Họ tên không được để trống");
            return;
        }

        if (txtemailNhanVien.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Email không được để trống");
            return;

        }

        if (txtmatKhau.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
            return;
        }

        if (TXTDIACHI.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
            return;
        }

        if (TXTHOTEN.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Họ và tên không được quá 50 ký tự");
            return;
        }

        if (txtemailNhanVien.getText().length() > 254) {
            JOptionPane.showMessageDialog(this, "Email không được quá 254 ký tự");
            return;
        }

        if (txtdiaChi.getText().length() > 200) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được quá 50 ký tự");
            return;
        }

        if (txtmatKhau.getText().length() < 8 || txtmatKhau.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải ít nhất 8 ký tự và nhiều nhất 15 ký tự");
            return;
        }

        if (!TXTHOTEN.getText().matches("[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Họ tên phải là chữ, không được chứa số và ký tự đặc biệt");
            return;

        }

        String tuoi = ((JTextField) ngaySinh.getDateEditor().getUiComponent()).getText();
        String dob[] = tuoi.split("-");
        int day = Integer.parseInt(dob[2]);
        int month = Integer.parseInt(dob[1]);
        int year = Integer.parseInt(dob[0]);

        LocalDate tinhTuoi = LocalDate.of(year, month, day);
        LocalDate nam = LocalDate.now();

        int resulYear = Period.between(tinhTuoi, nam).getYears();

        if (resulYear < 18) {
            JOptionPane.showMessageDialog(this, "Tuổi nhân viên phải lớn hơn 18");
            return;
        }

        if (!txtmatKhau.getText().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,15}$")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải chứa 1 ký tự số, 1 ký tự hoa và 1 ký tự đặc biệt");
            return;
        }
        if (!txtemailNhanVien.getText().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dang email");
            return;
        }

        String dienThoai1 = "^03\\d{8}$";
        String dienThoai2 = "^05\\d{8}$";
        String dienThoai3 = "^07\\d{8}$";
        String dienThoai4 = "^08\\d{8}$";
        String dienThoai5 = "^09\\d{8}$";

        if (!(TXTSDT.getText().matches(dienThoai1)
                || TXTSDT.getText().matches(dienThoai2)
                || TXTSDT.getText().matches(dienThoai3)
                || TXTSDT.getText().matches(dienThoai4)
                || TXTSDT.getText().matches(dienThoai5))) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại đúng định dạng ");
            return;
        }

        //    Validate email không được trùng
//         String ten = txtemailNhanVien.getText();
//        List<ViewModelNhanVien> dg = nhanVienService.getAll();
//        for (ViewModelNhanVien v : dg) {
//            if (ten.equals(v.getEmail())) {
//                JOptionPane.showMessageDialog(this, "Tên email  đã tồn tại!");
//                return;
//            }
//        }
        String dienThoai = TXTSDT.getText();
        List<ViewModelNhanVien1> dg = nhanVienService1.getAll();
        for (ViewModelNhanVien1 v : dg) {
            if (dienThoai.equals(v.getSdt())) {
                JOptionPane.showMessageDialog(this, "Số điện thoại  đã tồn tại!");
                return;
            }
        }

        String email1 = txtemailNhanVien.getText();
        List<ViewModelNhanVien1> em = nhanVienService1.getAll();
        for (ViewModelNhanVien1 v : em) {
            if (email1.equals(v.getEmail())) {
                JOptionPane.showMessageDialog(this, "Email đã tồn tại!");
                return;
            }
        }

        java.util.Date date = ngaySinh.getDate();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String now = format.format(date);

        String idCH = getCHCBB();

        String idCV = getCBBCV();

        String hoTen = TXTHOTEN.getText();

        String sdt = TXTSDT.getText();

        String diaChi = TXTDIACHI.getText();

        // String ngaySinh = getDate.getDateFormatString();
        String matKhau = txtmatKhau.getText();

        String email = txtemailNhanVien.getText();

        NhanVien nv = new NhanVien();

        CuaHang ch = new CuaHang();

        ch.setId(idCH);

        ChucVu cv = new ChucVu();

        cv.setId(idCV);

        nv.setIdCuaHang(ch);

        nv.setIdChucVu(cv);

        nv.setHoTen(hoTen);

        nv.setSdt(sdt);

        nv.setDiaChi(diaChi);

        //nv.setNgaySinh(ngaySinh);
        nv.setNgaySinh(Date.valueOf(now));

        nv.setMatKhau(matKhau);

        nv.setEmail(email);

        //nv.setTrangThai(1);
        boolean b = nhanVienService1.add(nv);

        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Thêm sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTableDangLam(1);

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Trùng Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:

        boolean b = nhanVienService.delete(TXTID.getText());

        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Delete sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTableDangLam(1);

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Delete Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

        if (TXTSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
            return;
        }

        if (TXTHOTEN.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Họ tên không được để trống");
            return;
        }

        if (txtemailNhanVien.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Email không được để trống");
            return;

        }

        if (txtmatKhau.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
            return;
        }

        if (TXTDIACHI.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
            return;
        }

        java.util.Date date = ngaySinh.getDate();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String now = format.format(date);

        String id = TXTID.getText();

        String idCH = getCHCBB();

        String idCV = getCBBCV();

        String hoTen = TXTHOTEN.getText();

        String sdt = TXTSDT.getText();

        String diaChi = TXTDIACHI.getText();

        // String ngaySinh = getDate.getDateFormatString();
        String matKhau = txtmatKhau.getText();

        String email = txtemailNhanVien.getText();

        NhanVien nv = new NhanVien();

        CuaHang ch = new CuaHang();
        ch.setId(idCH);

        ChucVu cv = new ChucVu();
        cv.setId(idCV);

        nv.setId(id);

        nv.setIdCuaHang(ch);

        nv.setIdChucVu(cv);

        nv.setHoTen(hoTen);

        nv.setSdt(sdt);

        nv.setDiaChi(diaChi);

        //nv.setNgaySinh(ngaySinh);
        nv.setNgaySinh(Date.valueOf(now));

        nv.setMatKhau(matKhau);

        nv.setEmail(email);

        nv.setTrangThai(rdodangLam.isSelected() ? 1 : 2);

        boolean b = nhanVienService1.update(nv);

        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Sửa sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTableDangLam(1);
            loadTableNghiLam();

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Sửa thất Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void TXTMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTMAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTMAActionPerformed

    private void TXTDIACHIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTDIACHIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTDIACHIActionPerformed

    private void TXTHOTENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTHOTENActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTHOTENActionPerformed

    private void txtemailNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailNhanVienActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:

        ChucVu sp = new ChucVu();

        sp.setId(txtID.getText());
        boolean b = chucVuService.delete(sp);

        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Delete sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTBChucVu();

        } else {
            JOptionPane.showMessageDialog(this, "Delete Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:

        if (txttenChucVu.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên chức vụ không được để trống");
            return;
        }

        if (txttenChucVu.getText().length() > 100) {
            JOptionPane.showMessageDialog(this, "Tên chức vụ không được quá 100 ký tự");
            return;
        }

        if (!txttenChucVu.getText().matches("[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Tên chức vụ phải là chữ, không được chứa số và ký tự đặc biệt");
            return;

        }

        ChucVu cv = new ChucVu();
        cv.setTenChucVu(txttenChucVu.getText());

        if (checkTenCHUCVU()) {
            boolean b = chucVuService.add(cv);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTBChucVu();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Trùng Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void tbbangcvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbangcvMouseClicked
        // TODO add your handling code here:
        int index = tbbangcv.getSelectedRow();

        txtID.setText(tbbangcv.getValueAt(index, 0).toString());

        txtma.setText(tbbangcv.getValueAt(index, 1).toString());

        txttenChucVu.setText(tbbangcv.getValueAt(index, 2).toString());
    }//GEN-LAST:event_tbbangcvMouseClicked

    private void txttimKiemchucvuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimKiemchucvuKeyReleased
        // TODO add your handling code here:
        loadTBChucVuTimKiem(txttimKiemchucvu.getText());
    }//GEN-LAST:event_txttimKiemchucvuKeyReleased

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:
        if (txttenChucVu.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên chức vụ không được để trống");
            return;
        }

        ChucVu cv = new ChucVu();
        cv.setId(txtID.getText());
        cv.setTenChucVu(txttenChucVu.getText());

        if (checkTenCHUCVU()) {
            boolean b = chucVuService.update(cv);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Sửa sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTBChucVu();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Sửa thất bại", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void txttimKiemchucvuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimKiemchucvuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimKiemchucvuActionPerformed

    private void txtdiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiaChiActionPerformed

    private void btnxoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoa1ActionPerformed
        // TODO add your handling code here:
        CuaHang ch = new CuaHang();

        ch.setId(txtIDCH.getText());

        boolean b = cuaHangService.delete(ch);

        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Delete sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTbCuaHangPhanTrang(1);

        } else {
            JOptionPane.showMessageDialog(this, "Delete Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnxoa1ActionPerformed

    private void btnsua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsua1ActionPerformed
        // TODO add your handling code here:
        if (txttenCH.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên cửa hàng không được để trống");
            return;
        }

        if (txtdiaChi.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ cửa hàng không được để trống");
            return;
        }

        CuaHang ch = new CuaHang();

        ch.setId(txtIDCH.getText());
        ch.setTenCuaHang(txttenCH.getText());

        ch.setDiaChi(txtdiaChi.getText());

        ch.setTrangThai(rdodangHoatDong.isSelected() ? 1 : 2);

        if (checkTen()) {
            boolean b = cuaHangService.update(ch);

            if (b == true) {

                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));

                JOptionPane.showMessageDialog(this, "Sửa sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);

                loadTbCuaHangPhanTrang(1);

                loadTBCuaHangNgungHoatDong();
                loadCBB();
                loadTableDangLam(1);

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Sửa SP thất bại", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }//GEN-LAST:event_btnsua1ActionPerformed

    private void btnthem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthem1ActionPerformed
        // TODO add your handling code here:
        if (txttenCH.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên cửa hàng không được để trống");
            return;
        }

        if (txtdiaChi.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ cửa hàng không được để trống");
            return;
        }

        if (txttenCH.getText().length() > 150) {
            JOptionPane.showMessageDialog(this, "Tên cửa hàng không được quá 150 ký tự");
            return;
        }

        if (txtdiaChi.getText().length() > 250) {
            JOptionPane.showMessageDialog(this, "Tên cửa hàng không được quá 250 ký tự");
            return;
        }

        String tenCH = txttenCH.getText();

        String diaChi = txtdiaChi.getText();

        List<ViewModelCuaHang> dg = cuaHangService.getAll();

        for (ViewModelCuaHang v : dg) {
            if (tenCH.equals(v.getTenCuaHang()) && diaChi.equals(v.getDiaChi())) {
                JOptionPane.showMessageDialog(this, "Tên cửa hàng và địa chỉ này đã tồn tại");
                return;

            }
        }

        CuaHang ch = new CuaHang();

        ch.setTenCuaHang(txttenCH.getText());

        ch.setDiaChi(txtdiaChi.getText());

        boolean b = cuaHangService.add(ch);
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Thêm sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTbCuaHangPhanTrang(1);

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Trùng Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }

    }//GEN-LAST:event_btnthem1ActionPerformed

    private void tbbangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbangMouseClicked
        // TODO add your handling code here:

        int index = tbbang.getSelectedRow();

        txtIDCH.setText(tbbang.getValueAt(index, 0).toString());

        txtmaCH.setText(tbbang.getValueAt(index, 1).toString());

        txttenCH.setText(tbbang.getValueAt(index, 2).toString());

        txtdiaChi.setText(tbbang.getValueAt(index, 3).toString());

        int intdex1 = tbbang.getSelectedRow();
        List<ViewModelCuaHang> xm = cuaHangService.getAll();
        ViewModelCuaHang xm1 = xm.get(intdex1);

        if (xm1.getTrangThai() == 1) {
            rdodangHoatDong.setSelected(true);
        } else {
            rdongungHoatDong.setSelected(true);
        }

    }//GEN-LAST:event_tbbangMouseClicked

    private void txttimKiemCuaHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimKiemCuaHangKeyReleased
        // TODO add your handling code here:

        loadTBCuaHang(txttimKiemCuaHang.getText());
    }//GEN-LAST:event_txttimKiemCuaHangKeyReleased

    private void txttimKiemnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimKiemnhanvienMouseClicked
        // TODO add your handling code here:
        txttimKiemnhanvien.setText("");
    }//GEN-LAST:event_txttimKiemnhanvienMouseClicked

    private void txttimKiemchucvuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimKiemchucvuMouseClicked
        // TODO add your handling code here:
        txttimKiemchucvu.setText("");
    }//GEN-LAST:event_txttimKiemchucvuMouseClicked

    private void txttimKiemCuaHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimKiemCuaHangMouseClicked
        // TODO add your handling code here:
        txttimKiemCuaHang.setText("");
    }//GEN-LAST:event_txttimKiemCuaHangMouseClicked

    private void txttimKiemnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimKiemnhanvienActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txttimKiemnhanvienActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        MenuView v = new MenuView(IdNV, TenNV, CV);

        v.setLocationRelativeTo(null);

        v.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        XSSFWorkbook w = new XSSFWorkbook();

        XSSFSheet sheet = w.createSheet("danhsach");

        XSSFRow r = null;

        Cell cell = null;

        r = sheet.createRow(0);//số dòng cách đầu ở excel

        cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Id");

        cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Mã");

        cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Họ Tên");

        cell = r.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("SĐT");

        cell = r.createCell(5, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Địa chỉ");

        cell = r.createCell(6, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Ngày sinh");

        cell = r.createCell(7, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Cửa hàng");

        cell = r.createCell(8, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Chức vụ");

        cell = r.createCell(9, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Mật khẩu");

        cell = r.createCell(10, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Email");

        List<ViewModelNhanVien> v = nhanVienService.getListNV();
        // List<NhanVien> sps = nhanVienService.getListNV();

        int s = v.size();
        for (int i = 0; i < s; i++) {
            ViewModelNhanVien chh = v.get(i);
            r = sheet.createRow(1 + i);

            cell = r.createCell(0, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(i + 1);

            cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getId());

            cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getMa());

            cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getHoTen());

            cell = r.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getSdt());

            cell = r.createCell(5, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getDiaChi());

            cell = r.createCell(6, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getNgaySinh());

            cell = r.createCell(7, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getIdCH());

            cell = r.createCell(8, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getIdCV());

            cell = r.createCell(9, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getMatKhau());

            cell = r.createCell(10, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getEmail());

        }
        JFileChooser chooser = new JFileChooser();// mở file lên 
        chooser.showOpenDialog(null);//để chọn lưu vào đâu
        File f = chooser.getSelectedFile();

        try {

            FileOutputStream f1 = new FileOutputStream(new File(f + ".xlsx"));

            w.write(f1);
            f1.close();

        } catch (Exception e) {

        }
        JOptionPane
                .showMessageDialog(this, "In thành công");


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        XSSFWorkbook w = new XSSFWorkbook();

        XSSFSheet sheet = w.createSheet("danhsach");
        XSSFRow r = null;

        Cell cell = null;
        r = sheet.createRow(0);//số dòng cách đầu ở excel

        cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Id");

        cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Mã");

        cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Tên chức vụ");

        List<ViewModelChucVu> v = chucVuService.getAll();
        // List<NhanVien> sps = nhanVienService.getListNV();

        int s = v.size();
        for (int i = 0; i < s; i++) {
            ViewModelChucVu chh = v.get(i);
            r = sheet.createRow(1 + i);

            cell = r.createCell(0, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(i + 1);

            cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getId());

            cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getMa());

            cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getTen());

        }

        File f = new File("danhsachchucvu.xlsx");

        try {

            FileOutputStream f1 = new FileOutputStream(f);

            w.write(f1);
            f1.close();

        } catch (Exception e) {

        }
        JOptionPane
                .showMessageDialog(this, "In thành công");


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        XSSFWorkbook w = new XSSFWorkbook();

        XSSFSheet sheet = w.createSheet("danhsach");

        XSSFRow r = null;

        Cell cell = null;

        r = sheet.createRow(0);//số dòng cách đầu ở excel
        cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Id");

        cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Mã");

        cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Tên cửa hàng");

        cell = r.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Trạng thái");

        List<ViewModelCuaHang> v = cuaHangService.getAll();
        // List<NhanVien> sps = nhanVienService.getListNV();

        int s = v.size();
        for (int i = 0; i < s; i++) {

            ViewModelCuaHang chh = v.get(i);

            r = sheet.createRow(1 + i);

            cell = r.createCell(0, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(i + 1);

            cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getId());

            cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getMa());

            cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getTenCuaHang());

            cell = r.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getTrangThai() == 1 ? "Đang hoạt động" : "Dừng hoạt động");

        }
        JFileChooser chooser = new JFileChooser();// mở file lên 
        chooser.showOpenDialog(null);//để chọn lưu vào đâu
        File f = chooser.getSelectedFile();

        try {
            FileOutputStream f1 = new FileOutputStream(new File(f + ".xlsx"));

            w.write(f1);
            f1.close();

        } catch (Exception e) {

        }
        JOptionPane
                .showMessageDialog(this, "In thành công");


    }//GEN-LAST:event_jButton3ActionPerformed

    private void TBBANGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBBANGMouseClicked
        // TODO add your handling code here:

        int index = TBBANG.getSelectedRow();

        String ns = TBBANG.getValueAt(index, 5).toString();

        // Date ngay = Date.valueOf(ngaySinh);
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date result;
        try {
            result = sdt.parse(ns);

            ngaySinh.setDate(result);

            TXTID.setText(TBBANG.getValueAt(index, 0).toString());

            TXTMA.setText(TBBANG.getValueAt(index, 1).toString());

            TXTHOTEN.setText(TBBANG.getValueAt(index, 2).toString());

            TXTSDT.setText(TBBANG.getValueAt(index, 3).toString());

            TXTDIACHI.setText(TBBANG.getValueAt(index, 4).toString());

            CBCCUAHANG.setSelectedItem(TBBANG.getValueAt(index, 6).toString());

            CBCCHUCVU.setSelectedItem(TBBANG.getValueAt(index, 7).toString());

            txtmatKhau.setText(TBBANG.getValueAt(index, 8).toString());

            txtemailNhanVien.setText(TBBANG.getValueAt(index, 9).toString().trim());

        } catch (ParseException ex) {
            Logger.getLogger(NhanVienViewTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        int intdex = TBBANG.getSelectedRow();
        List<ViewModelNhanVien1> xm = nhanVienService1.getListNVDangLam();
        ViewModelNhanVien1 xm1 = xm.get(intdex);

        if (xm1.getTrangThai() == 1) {
            rdodangLam.setSelected(true);
        } else {
            rdonghiLam.setSelected(true);
        }
    }//GEN-LAST:event_TBBANGMouseClicked

    private void icon2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon2MousePressed
        icon1.setVisible(true);
        icon2.setVisible(false);
        txtmatKhau.setEchoChar('*');

    }//GEN-LAST:event_icon2MousePressed

    private void icon1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon1MousePressed
        // TODO add your handling code here:
        icon2.setVisible(true);
        icon1.setVisible(false);
        txtmatKhau.setEchoChar((char) 0);

    }//GEN-LAST:event_icon1MousePressed

    private void cbbtimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtimKiemActionPerformed
        // TODO add your handling code here:
        //        if (cbbtimKiem.getSelectedItem().equals("Quan Ly")) {
        //            System.out.println(getCBBCVTK());
        //            loadTableTKDangLamCV(getCBBCVTK());
        //
        //        } else {
        //            loadTableTKDangLamCV(getCBBCVTK());
        //            System.out.println(getCBBCVTK());
        //        }

        if (cbbtimKiem.getSelectedItem().equals("All")) {
            loadTableAll();
        } else if (cbbtimKiem.getSelectedItem().equals("Quan Ly")) {
            loadTableTKDangLamCV(getCBBCVTK());

        } else if (cbbtimKiem.getSelectedItem().equals("Nhan Vien")) {
            loadTableTKDangLamCV(getCBBCVTK());
        }
    }//GEN-LAST:event_cbbtimKiemActionPerformed

    private void cbbtimKiemCuaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtimKiemCuaHangActionPerformed
        // TODO add your handling code here:

        try {
            if (cbbtimKiemCuaHang.getSelectedItem().equals("All")) {
                loadTableAll();
                return;
            }
            List<ViewModelCuaHang> v = cuaHangService.getAll();
            for (ViewModelCuaHang viewModelCuaHang : v) {
                if (cbbtimKiemCuaHang.getSelectedItem().equals(viewModelCuaHang.getTenCuaHang())) {
                    loadTableTKCH(getCBBTKCuaHang());
                    return;
                }

            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_cbbtimKiemCuaHangActionPerformed

    private void txthoTenNghiViec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthoTenNghiViec1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthoTenNghiViec1ActionPerformed

    private void tbbangnghiviecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbangnghiviecMouseClicked
        // TODO add your handling code here:

        int index = tbbangnghiviec.getSelectedRow();

        String ns = tbbangnghiviec.getValueAt(index, 5).toString();

        // Date ngay = Date.valueOf(ngaySinh);
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date result;
        try {
            result = sdt.parse(ns);

            ngaySinh1.setDate(result);

            txtidNghiViec1.setText(tbbangnghiviec.getValueAt(index, 0).toString());

            txtmaNghiViec1.setText(tbbangnghiviec.getValueAt(index, 1).toString());

            txthoTenNghiViec1.setText(tbbangnghiviec.getValueAt(index, 2).toString());

            txtdienThoaiNghiViec1.setText(tbbangnghiviec.getValueAt(index, 3).toString());

            txtdiaChiNghiViec1.setText(tbbangnghiviec.getValueAt(index, 4).toString());

            cbbcuaHangNghiViec1.setSelectedItem(tbbangnghiviec.getValueAt(index, 6).toString());

            cbbchucVuNghiViec1.setSelectedItem(tbbangnghiviec.getValueAt(index, 7).toString());

            txtmatKhauNghiViec1.setText(tbbangnghiviec.getValueAt(index, 8).toString());

            txtemailNghiViec1.setText(tbbangnghiviec.getValueAt(index, 9).toString().trim());

        } catch (ParseException ex) {
            Logger.getLogger(NhanVienViewTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        int intdex = tbbangnghiviec.getSelectedRow();
        List<ViewModelNhanVien1> xm = nhanVienService1.getListNVNghiLam();
        ViewModelNhanVien1 xm1 = xm.get(intdex);

        if (xm1.getTrangThai() == 1) {
            rdodangLamViec1.setSelected(true);
        } else {
            rdoNghiViec1.setSelected(true);
        }
    }//GEN-LAST:event_tbbangnghiviecMouseClicked

    private void btnback1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnback1ActionPerformed
        // TODO add your handling code here:6

        boolean kt = nhanVienService1.troLai(txtidNghiViec1.getText());

        if (kt == true) {
            JOptionPane.showMessageDialog(this, "Trở lại thành công");
            loadTableNghiLam();
            loadTableDangLam(1);
        } else {
            JOptionPane.showMessageDialog(this, "Trở lại thất bại");
        }
    }//GEN-LAST:event_btnback1ActionPerformed

    private void tbbangNgungHoatDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbangNgungHoatDongMouseClicked
        // TODO add your handling code here:

        int index = tbbangNgungHoatDong.getSelectedRow();

        txtidCHNgungHD.setText(tbbangNgungHoatDong.getValueAt(index, 0).toString());

        txtmaCHNgungHD.setText(tbbangNgungHoatDong.getValueAt(index, 1).toString());

        txttenCHNgungHD.setText(tbbangNgungHoatDong.getValueAt(index, 2).toString());

        txtdiaChiNgungHD.setText(tbbangNgungHoatDong.getValueAt(index, 3).toString());

        int intdex1 = tbbangNgungHoatDong.getSelectedRow();
        List<ViewModelCuaHang> xm = cuaHangService.cuaHangNgungHoatDong();
        ViewModelCuaHang xm1 = xm.get(intdex1);

        if (xm1.getTrangThai() == 1) {
            rdoCHDangHD.setSelected(true);
        } else {
            rdoCHNgungHD.setSelected(true);
        }
    }//GEN-LAST:event_tbbangNgungHoatDongMouseClicked

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // TODO add your handling code here:

        boolean kt = cuaHangService.troLai(txtidCHNgungHD.getText());
        if (kt == true) {
            JOptionPane.showMessageDialog(this, "Trở lại thành công");
            loadTBCuaHangNgungHoatDong();
            loadTbCuaHangPhanTrang(1);

            loadCBB();
            //  loadTable1NhanVien(1);
            loadTableDangLam(1);

        } else {
            JOptionPane.showMessageDialog(this, "Trở lại thất bại");

        }
    }//GEN-LAST:event_btnbackActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        XSSFWorkbook w = new XSSFWorkbook();

        XSSFSheet sheet = w.createSheet("nhanviennghilam");

        //   XSSFSheet sheet2 = w.createSheet("danhsachnghilam");
        XSSFRow r = null;

        Cell cell = null;

        r = sheet.createRow(0);//số dòng cách đầu ở excel

        cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Id");

        cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Mã");

        cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Họ Tên");

        cell = r.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("SĐT");

        cell = r.createCell(5, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Địa chỉ");

        cell = r.createCell(6, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Ngày sinh");

        cell = r.createCell(7, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Cửa hàng");

        cell = r.createCell(8, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Chức vụ");

        cell = r.createCell(9, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Mật khẩu");

        cell = r.createCell(10, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Email");

        cell = r.createCell(11, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Trạng thái");

        List<ViewModelNhanVien1> v = nhanVienService1.getListNVNghiLam();
        // List<NhanVien> sps = nhanVienService.getListNV();

        int s = v.size();
        for (int i = 0; i < s; i++) {
            ViewModelNhanVien1 chh = v.get(i);
            r = sheet.createRow(1 + i);

            cell = r.createCell(0, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(i + 1);

            cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getId());

            cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getMa());

            cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getHoTen());

            cell = r.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getSdt());

            cell = r.createCell(5, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getDiaChi());

            cell = r.createCell(6, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getNgaySinh());

            cell = r.createCell(7, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getIdCH());

            cell = r.createCell(8, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getIdCV());

            cell = r.createCell(9, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getMatKhau());

            cell = r.createCell(10, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getEmail());

            cell = r.createCell(11, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getTrangThai() == 1 ? "Đang làm việc" : "Nghỉ việc");

        }

        JFileChooser chooser = new JFileChooser();// mở file lên
        chooser.showOpenDialog(null);//để chọn lưu vào đâu
        File f = chooser.getSelectedFile();

        try {

            FileOutputStream f1 = new FileOutputStream(new File(f + ".xlsx"));

            w.write(f1);
            f1.close();

        } catch (Exception e) {

        }
        JOptionPane
                .showMessageDialog(this, "In thành công");


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        XSSFWorkbook w = new XSSFWorkbook();

        XSSFSheet sheet = w.createSheet("cuahangngunghoatdong");

        XSSFRow r = null;

        Cell cell = null;

        r = sheet.createRow(0);//số dòng cách đầu ở excel
        cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Id");

        cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Mã");

        cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Tên cửa hàng");

        cell = r.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue("Trạng thái");

        List<ViewModelCuaHang> v = cuaHangService.cuaHangNgungHoatDong();
        // List<NhanVien> sps = nhanVienService.getListNV();

        int s = v.size();
        for (int i = 0; i < s; i++) {

            ViewModelCuaHang chh = v.get(i);

            r = sheet.createRow(1 + i);

            cell = r.createCell(0, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(i + 1);

            cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getId());

            cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getMa());

            cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getTenCuaHang());

            cell = r.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getTrangThai() == 1 ? "Đang hoạt động" : "Dừng hoạt động");

        }
        JFileChooser chooser = new JFileChooser();// mở file lên 
        chooser.showOpenDialog(null);//để chọn lưu vào đâu
        File f = chooser.getSelectedFile();

        try {
            FileOutputStream f1 = new FileOutputStream(new File(f + ".xlsx"));

            w.write(f1);
            f1.close();

        } catch (Exception e) {

        }
        JOptionPane
                .showMessageDialog(this, "In thành công");


    }//GEN-LAST:event_jButton5ActionPerformed

    private void txttimKiemSDTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimKiemSDTMouseClicked
        // TODO add your handling code here:

        txttimKiemSDT.setText("");
    }//GEN-LAST:event_txttimKiemSDTMouseClicked

    private void txttimKiemSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimKiemSDTKeyReleased
        // TODO add your handling code here:

        loadTableTimKiemSDT(txttimKiemSDT.getText());

    }//GEN-LAST:event_txttimKiemSDTKeyReleased

    private void CBCCUAHANGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBCCUAHANGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBCCUAHANGActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NhanVienFView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienFView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienFView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienFView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new NhanVienFView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBCCHUCVU;
    private javax.swing.JComboBox<String> CBCCUAHANG;
    private javax.swing.JTable TBBANG;
    private javax.swing.JTextField TXTDIACHI;
    private javax.swing.JTextField TXTHOTEN;
    private javax.swing.JTextField TXTID;
    private javax.swing.JTextField TXTMA;
    private javax.swing.JTextField TXTSDT;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnback1;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnsua1;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnthem1;
    private javax.swing.JButton btnxoa;
    private javax.swing.JButton btnxoa1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cbbchucVuNghiViec1;
    private javax.swing.JComboBox<String> cbbcuaHangNghiViec1;
    private javax.swing.JComboBox<String> cbbtimKiem;
    private javax.swing.JComboBox<String> cbbtimKiemCuaHang;
    private javax.swing.JLabel icon1;
    private javax.swing.JLabel icon2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private com.toedter.calendar.JDateChooser ngaySinh;
    private com.toedter.calendar.JDateChooser ngaySinh1;
    private pagination.Pagination pagination1;
    private pagination.Pagination pagination2;
    private javax.swing.JRadioButton rdoCHDangHD;
    private javax.swing.JRadioButton rdoCHNgungHD;
    private javax.swing.JRadioButton rdoNghiViec1;
    private javax.swing.JRadioButton rdodangHoatDong;
    private javax.swing.JRadioButton rdodangLam;
    private javax.swing.JRadioButton rdodangLamViec1;
    private javax.swing.JRadioButton rdonghiLam;
    private javax.swing.JRadioButton rdongungHoatDong;
    private javax.swing.JTable tbbang;
    private javax.swing.JTable tbbangNgungHoatDong;
    private javax.swing.JTable tbbangcv;
    private javax.swing.JTable tbbangnghiviec;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDCH;
    private javax.swing.JTextField txtdiaChi;
    private javax.swing.JTextField txtdiaChiNghiViec1;
    private javax.swing.JTextField txtdiaChiNgungHD;
    private javax.swing.JTextField txtdienThoaiNghiViec1;
    private javax.swing.JTextField txtemailNghiViec1;
    private javax.swing.JTextField txtemailNhanVien;
    private javax.swing.JTextField txthoTenNghiViec1;
    private javax.swing.JTextField txtidCHNgungHD;
    private javax.swing.JTextField txtidNghiViec1;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtmaCH;
    private javax.swing.JTextField txtmaCHNgungHD;
    private javax.swing.JTextField txtmaNghiViec1;
    private javax.swing.JPasswordField txtmatKhau;
    private javax.swing.JPasswordField txtmatKhauNghiViec1;
    private javax.swing.JTextField txttenCH;
    private javax.swing.JTextField txttenCHNgungHD;
    private javax.swing.JTextField txttenChucVu;
    private javax.swing.JTextField txttimKiemCuaHang;
    private javax.swing.JTextField txttimKiemSDT;
    private javax.swing.JTextField txttimKiemchucvu;
    private javax.swing.JTextField txttimKiemnhanvien;
    // End of variables declaration//GEN-END:variables
}
