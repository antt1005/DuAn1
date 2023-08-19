/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import domainModels.ChiTietDoGo;
import domainModels.HoaDon;
import domainModels.HoaDonChiTiet;
import domainModels.NhanVien;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pagination.EventPagination;
import pagination.style.PaginationItemRenderStyle1;
import services.ChiTietDoGoBanHangService;
import services.ChiTietDoGoService;
import services.HoaDonBanHangService;
import services.HoaDonChiTietService;
import services.KhuyenMaiService;
import viewModel.ViewModelChiTietSanPhamBanHang;
import services.impl.IManageChiTietDoGoBanHangService;
import services.impl.IManageChiTietDoGoService;
import services.impl.IManageChiTietHoaDonBanHang;
import services.impl.IManageHoaDonBanHangService;
import services.impl.IManageKhuyenMaiService;
import viewModel.KhuyenMaiViewModel;
import viewModel.ViewModelHoaDonBanHang;
import viewModel.ViewModelHoaDonChiTietBanHang;

/**
 *
 * @author Admin
 */
public class BanHangView extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    String ten = null;

    DefaultTableModel model = new DefaultTableModel();

    private IManageChiTietDoGoBanHangService spService = new ChiTietDoGoBanHangService();

    private IManageHoaDonBanHangService hdService = new HoaDonBanHangService();

    private static IManageChiTietHoaDonBanHang cthdService = new HoaDonChiTietService();

    private IManageChiTietDoGoService ctdgSV = new ChiTietDoGoService();

    private IManageKhuyenMaiService kmSV = new KhuyenMaiService();

    private static String idkh = null;
    private static String TenKH = null;
    private static String SdtKH = null;

    private static String idkm = null;
    private static String ptkm = null;

    String IdNV;
    String TenNV;
    String CV;
    int page1 = 1;
    int page2 = 1;

    public BanHangView(String Id, String Ten, String cv) {
        initComponents();
        setLocationRelativeTo(null);
        cbTaoHoaDon.setSelected(true);
        this.setDefaultCloseOperation(BanHangView.DO_NOTHING_ON_CLOSE);
        addtxtNew();
        loadSP(page1);
        pagination1.setPaginationItemRender(new PaginationItemRenderStyle1());
        pagination1.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                if (result_field.getText().equals("")) {
                    page1 = page;
                    loadSP(page);
                } else {
                    page2 = page;
                    loadSPByTen(result_field.getText(), page);
                }

            }
        });

        IdNV = Id;
        TenNV = Ten;
        CV = cv;
        initWebcam();
        loadHD();
        loadCTHH(txtIdhd.getText());
        if (!txtIdhd.getText().equals("")) {
            int tongtien = cthdService.TongTien(txtIdhd.getText());
            txtTongTien.setText(tongtien + "");
        }
        txtIdhd.setEditable(false);
        txtMahd.setEditable(false);
        txtNgayTao.setEditable(false);
        txtTenNV.setEditable(false);
        txtTongTien.setEditable(false);
        txtTenKH.setEditable(false);
        txtSdt.setEditable(false);
        txtkm.setEditable(false);

    }

    public static void TTKHView(String id, String kh, String sdt) {
        BanHangView.idkh = id;
        BanHangView.TenKH = kh;
        BanHangView.SdtKH = sdt;
        txtTenKH.setText(TenKH);
        txtSdt.setText(SdtKH);
//        System.out.println(idkh);
    }

    public static void ChonKhuyenMaiBanHang(String id, String kmpt) {
        BanHangView.idkm = id;
        BanHangView.ptkm = kmpt;
        txtkm.setText(ptkm);
//        System.out.println(idkm);
    }

    public int layGiaSanPhamTheoId(String idsp) {
        int dongia = 0;
        List<ViewModelChiTietSanPhamBanHang> listsp = spService.TimKiemTheoId(idsp);
        for (ViewModelChiTietSanPhamBanHang a : listsp) {
            if (idsp.equals(a.getId())) {
                dongia = Integer.parseInt(String.valueOf(a.getGiaBan()));
            }
        }
        return dongia;
    }

    public void loadSP(int page) {
        try {
            int limit = 5;
            int count = 0;

            List<ViewModelChiTietSanPhamBanHang> listsp = spService.getList((page - 1) * limit, limit);
            if (listsp == null) {
                return;
            }

            count = spService.getRow();

            int totalPage = (int) Math.ceil(count / limit);

            model = (DefaultTableModel) tblSanPham.getModel();
            model.setColumnCount(0);
            model.addColumn("Id");
            model.addColumn("TenSP");
            model.addColumn("sp");
            model.addColumn("LoaiSP");
            model.addColumn("DongGo");
            model.addColumn("NhaCungCap");
            model.addColumn("NguonGoc");
            model.addColumn("SoLuong");
            model.addColumn("MoTa");
            model.addColumn("GiaNhap");
            model.addColumn("GiaBan");
            model.setRowCount(0);

            for (ViewModelChiTietSanPhamBanHang a : listsp) {
                model.addRow(new Object[]{
                    a.getId(), a.getTenSp(), a.getSanPham(), a.getLoaiSP(), a.getDongGo(), a.getNhaCungCap(), a.getNguonGoc(),
                    a.getSoLuong(), a.getMoTa(), a.getGiaBan(), a.getGiaBan()

                });
            }

            if (count / limit != 0) {
                pagination1.setPagegination(page, totalPage + 1);
            } else if (count / limit == 0) {
                pagination1.setPagegination(page, totalPage);
            }
        } catch (Exception e) {
        }
    }

    public void loadTheoId(String id) {
        try {
            model = (DefaultTableModel) tblSanPham.getModel();
            model.setColumnCount(0);
            model.addColumn("Id");
            model.addColumn("TenSP");
            model.addColumn("sp");
            model.addColumn("LoaiSP");
            model.addColumn("DongGo");
            model.addColumn("NhaCungCap");
            model.addColumn("NguonGoc");
            model.addColumn("SoLuong");
            model.addColumn("MoTa");
            model.addColumn("GiaNhap");
            model.addColumn("GiaBan");
            model.setRowCount(0);
            List<ViewModelChiTietSanPhamBanHang> listsp = spService.TimKiemTheoId(id);
            for (ViewModelChiTietSanPhamBanHang a : listsp) {
                model.addRow(new Object[]{
                    a.getId(), a.getTenSp(), a.getSanPham(), a.getLoaiSP(), a.getDongGo(), a.getNhaCungCap(), a.getNguonGoc(),
                    a.getSoLuong(), a.getMoTa(), a.getGiaBan(), a.getGiaBan()

                });
            }
        } catch (Exception e) {
        }

    }

    public void loadSPByTen(String ten, int page1) {
        try {
            int limit = 5;
            int count = 0;

            List<ViewModelChiTietSanPhamBanHang> listsp = spService.TimKiemTen(ten, (page1 - 1) * limit, limit);

            if (listsp == null) {
                return;
            }

            count = spService.getRowTimKiem(ten);

            int totalPage = (int) Math.ceil(count / limit);

            model = (DefaultTableModel) tblSanPham.getModel();
            model.setColumnCount(0);
            model.addColumn("Id");
            model.addColumn("TenSP");
            model.addColumn("sp");
            model.addColumn("LoaiSP");
            model.addColumn("DongGo");
            model.addColumn("NhaCungCap");
            model.addColumn("NguonGoc");
            model.addColumn("SoLuong");
            model.addColumn("MoTa");
            model.addColumn("GiaNhap");
            model.addColumn("GiaBan");
            model.setRowCount(0);

            for (ViewModelChiTietSanPhamBanHang a : listsp) {
                model.addRow(new Object[]{
                    a.getId(), a.getTenSp(), a.getSanPham(), a.getLoaiSP(), a.getDongGo(), a.getNhaCungCap(), a.getNguonGoc(),
                    a.getSoLuong(), a.getMoTa(), a.getGiaBan(), a.getGiaBan()

                });
            }

            if (count / limit != 0) {
                pagination1.setPagegination(page1, totalPage + 1);
            } else if (count / limit == 0) {
                pagination1.setPagegination(page1, totalPage);
            }
        } catch (Exception e) {
        }
    }

    public void loadHD() {
        List<ViewModelHoaDonBanHang> lists = hdService.getList();

        model = (DefaultTableModel) tblHoaDon.getModel();
        model.setColumnCount(0);
        model.addColumn("Id");
        model.addColumn("Ma HD");
        model.addColumn("NgayTao");
        model.addColumn("TenNV");
        model.addColumn("Trang Thai");

        model.setRowCount(0);

        if (lists == null) {
            return;
        }

        for (ViewModelHoaDonBanHang a : lists) {
            model.addRow(new Object[]{
                a.getId(), "Hóa Đơn " + a.getMa(), a.getNgayTao(), a.getTenNV(), a.getTrangThaiHoaDon()
            });
        }
    }

    public static void sale() {
        try {
            int tongtien = cthdService.TongTien(txtIdhd.getText());
            if (txtkm.getText().equals("")) {
                txtTongTien.setText(cthdService.TongTien(txtIdhd.getText()) + "");
                return;
            }
            int pt = Integer.parseInt(txtkm.getText());

            float a = 100 - pt;
            float b = a / 100;
            float c = tongtien * b;
            int d = (int) c;
            txtTongTien.setText(d + "");
        } catch (Exception e) {
        }
    }

    public void addtxtNew() {
        String idhd = null;
        String ma = null;
        String nv = null;
        String date = null;

        List<ViewModelHoaDonBanHang> lists = hdService.getList();
        int i = hdService.maxma();
        for (ViewModelHoaDonBanHang list : lists) {
            if (String.valueOf(i).equals(list.getMa())) {
                idhd = list.getId();
                ma = list.getMa();
                nv = list.getTenNV();
                date = list.getNgayTao();
            }
        }
        txtIdhd.setText(idhd);
        txtMahd.setText(ma);
        txtTenNV.setText(nv);
        txtNgayTao.setText(date);
    }

    public void loadCTHH(String id) {
        model = (DefaultTableModel) tblCTHH.getModel();
        model.setRowCount(0);
        List<ViewModelHoaDonChiTietBanHang> lisst = cthdService.list(id);
        if (lisst == null) {
            return;
        }
        for (ViewModelHoaDonChiTietBanHang a : lisst) {
            model.addRow(new Object[]{
                model.getRowCount() + 1, a.getIdsp(), a.getTen(), a.getSoluong(), a.getDonGia()
            });
        }
    }

    public int getSoluong(String idsp) {
        int i = 0;
        List<ViewModelHoaDonChiTietBanHang> lisst = cthdService.list(txtIdhd.getText());
        if (lisst == null) {
            i = 0;
        } else {
            for (ViewModelHoaDonChiTietBanHang a : lisst) {
                if (idsp.equals(a.getIdsp())) {
                    i = a.getSoluong();
                }
            }
        }
        return i;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        cbTaoHoaDon = new javax.swing.JCheckBox();
        LB = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtIdhd = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMahd = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtkm = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtKhachTra = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        result_field = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTHH = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbcLoaiThanhToan = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        pagination1 = new pagination.Pagination();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));

        jPanel8.setBackground(new java.awt.Color(255, 153, 153));

        jButton3.setBackground(new java.awt.Color(255, 153, 153));
        jButton3.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jButton3.setText("Create Invoice");
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cbTaoHoaDon.setBackground(new java.awt.Color(255, 153, 153));
        cbTaoHoaDon.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        cbTaoHoaDon.setText("create invoice");

        LB.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        LB.setText("Tien Thua ");

        txtTienThua.setBackground(new java.awt.Color(255, 153, 153));
        txtTienThua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienThua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtTienThua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienThuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(LB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTaoHoaDon))))
                .addGap(11, 11, 11))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(LB))
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(cbTaoHoaDon)
                .addGap(0, 0, 0)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 153, 153));

        jLabel4.setFont(new java.awt.Font("NSimSun", 1, 12)); // NOI18N
        jLabel4.setText("Id Hoa Don");

        txtIdhd.setBackground(new java.awt.Color(255, 153, 153));
        txtIdhd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtIdhd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtIdhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdhdActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("NSimSun", 1, 12)); // NOI18N
        jLabel5.setText("Ma HD");

        txtMahd.setBackground(new java.awt.Color(255, 153, 153));
        txtMahd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMahd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("NSimSun", 1, 12)); // NOI18N
        jLabel6.setText("Ten Nhan Vien");

        txtTenNV.setBackground(new java.awt.Color(255, 153, 153));
        txtTenNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTenNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("NSimSun", 1, 12)); // NOI18N
        jLabel7.setText("Ngay tao");

        txtNgayTao.setBackground(new java.awt.Color(255, 153, 153));
        txtNgayTao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtNgayTao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtNgayTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayTaoActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 153, 153));
        jButton2.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jButton2.setText("CHOOSE CUSTOMERS ONLY");
        jButton2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jLabel9.setText("Khach Hang");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMahd, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdhd, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdhd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMahd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(12, 12, 12)
                .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        txtTenKH.setBackground(new java.awt.Color(255, 153, 153));
        txtTenKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTenKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jLabel17.setText("Ten Khach Hang");

        jLabel18.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jLabel18.setText("Sdt Khach Hang");

        txtSdt.setBackground(new java.awt.Color(255, 153, 153));
        txtSdt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 153, 153));
        jButton4.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jButton4.setText("CHOOSE SALE");
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jLabel19.setText("Sale");

        jLabel20.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jLabel20.setText("Sale Money");

        txtkm.setBackground(new java.awt.Color(255, 153, 153));
        txtkm.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtkm.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtkm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkmActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jLabel21.setText("Tong Tien");

        txtTongTien.setBackground(new java.awt.Color(255, 153, 153));
        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jLabel22.setText("Khach Tra");

        txtKhachTra.setBackground(new java.awt.Color(255, 153, 153));
        txtKhachTra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtKhachTra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtKhachTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhachTraActionPerformed(evt);
            }
        });
        txtKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKhachTraKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel17)
                .addGap(44, 44, 44)
                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel18)
                .addGap(44, 44, 44)
                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel19)
                .addGap(79, 79, 79)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel20)
                .addGap(74, 74, 74)
                .addComponent(txtkm, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel21)
                .addGap(82, 82, 82)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel22)
                .addGap(82, 82, 82)
                .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(jLabel17))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel18))
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel20))
                    .addComponent(txtkm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel21))
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel22))
                    .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));

        result_field.setBackground(new java.awt.Color(255, 153, 153));
        result_field.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        result_field.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 0, 102)));
        result_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result_fieldActionPerformed(evt);
            }
        });
        result_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                result_fieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(result_field)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(result_field, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));

        tblCTHH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblCTHH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Idsp", "Ten", "soluong", "dongia"
            }
        ));
        tblCTHH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTHHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCTHH);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jLabel8.setText("Payment Methods");

        cbcLoaiThanhToan.setBackground(new java.awt.Color(255, 153, 153));
        cbcLoaiThanhToan.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        cbcLoaiThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASH PAYING BILLION", "BANK TRANSFER" }));
        cbcLoaiThanhToan.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        cbcLoaiThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcLoaiThanhToanActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 153, 153));
        jButton1.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"))); // NOI18N
        jButton1.setText("Create Invoice");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel21.setBackground(new java.awt.Color(204, 204, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(cbcLoaiThanhToan, 0, 255, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbcLoaiThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(204, 204, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));

        tblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));

        tblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tblSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblSanPham.setGridColor(new java.awt.Color(255, 51, 204));
        tblSanPham.setRowHeight(23);
        tblSanPham.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jLabel2.setText("-- HoaDon  --");

        jLabel3.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jLabel3.setText("-- Gio Hang  --");

        jLabel1.setFont(new java.awt.Font("NSimSun", 1, 14)); // NOI18N
        jLabel1.setText("-- San Pham  --");

        jButton5.setBackground(new java.awt.Color(255, 153, 153));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("BACK");
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        pagination1.setOpaque(false);
        pagination1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pagination1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(412, 412, 412))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(533, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(402, 402, 402)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(8, 8, 8)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(487, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 777, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNgayTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayTaoActionPerformed

    }//GEN-LAST:event_txtNgayTaoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        TTKHView i = new TTKHView();
        i.setVisible(true);
        i.pack();
        i.setLocationRelativeTo(null);
        i.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void result_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_result_fieldKeyReleased
        if (result_field.getText().equals("")) {
            loadSP(1);
        } else {
            loadSPByTen(result_field.getText(), page2);

        }
    }//GEN-LAST:event_result_fieldKeyReleased

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void txtSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ChonKhuyenMaiBanHangView i = new ChonKhuyenMaiBanHangView();
        i.setVisible(true);
        i.pack();
        i.setLocationRelativeTo(null);
        i.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtkmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkmActionPerformed

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void txtKhachTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhachTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKhachTraActionPerformed

    private void txtTienThuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienThuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienThuaActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int index = tblHoaDon.getSelectedRow();

        txtIdhd.setText(tblHoaDon.getValueAt(index, 0).toString());
        txtMahd.setText(tblHoaDon.getValueAt(index, 1).toString());
        txtNgayTao.setText(tblHoaDon.getValueAt(index, 2).toString());
        txtTenNV.setText(tblHoaDon.getValueAt(index, 3).toString());

        loadCTHH(tblHoaDon.getValueAt(index, 0).toString());
        int tongtien = cthdService.TongTien(txtIdhd.getText());
        txtTongTien.setText(tongtien + "");
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        HoaDon hd = new HoaDon();
        NhanVien nv = new NhanVien();
        nv.setId(IdNV);
        hd.setIdNhanVien(nv);

        boolean b = hdService.add(hd);
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Tạo Hóa Đơn Thành Công", "Hóa Đơn", JOptionPane.INFORMATION_MESSAGE, icon);
            loadHD();
            addtxtNew();

            int tongtien = cthdService.TongTien(txtIdhd.getText());
            txtTongTien.setText(tongtien + "");

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Trùng Mã Hóa Đơn", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {

            if (tblCTHH.getRowCount() == 0) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Ủa Anh Chưa Mua Đã Tính Tiền À!", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);
                return;
            }

            if (txtTenKH.getText().equals("")) {
                String[] buttons = {"Chosse", "Cancel"};
                int rc = JOptionPane.showOptionDialog(null, "Vui Lòng Chọn Khách Hàng!", "Khach Hang",
                        JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
                if (rc == 0) {
                    TTKHView i = new TTKHView();
                    i.setVisible(true);
                    i.pack();
                    i.setLocationRelativeTo(null);
                    i.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    return;
                } else {
                    return;
                }
            }

            int tongTien = Integer.parseInt(txtTongTien.getText());

            int tienTra = Integer.parseInt(txtKhachTra.getText());

            int tienThua = tienTra - tongTien;

            String TT = String.valueOf(tienThua);

            if (LB.getText().equals("Chua Du")) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Khách trả chưa đủ tiền!", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);
                return;
            }
            if (tienTra < tongTien) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Khách trả chưa đủ tiền!", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);
                return;
            }

            String idkh1 = BanHangView.idkh;

            String idkm1 = BanHangView.idkm;

            boolean b = hdService.update(txtIdhd.getText(), new BigDecimal(tongTien), idkh1, idkm1);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, " Thành Công", "Hóa Đơn", JOptionPane.INFORMATION_MESSAGE, icon);
                if (cbTaoHoaDon.isSelected()) {
                    CreateBill a = new CreateBill(txtIdhd.getText());
                    a.setLocationRelativeTo(null);
                    a.setVisible(true);
                }
                loadHD();
                addtxtNew();
                txtIdhd.setText("");
                txtMahd.setText("");
                txtTenNV.setText("");
                txtNgayTao.setText("");
                txtSdt.setText("");
                txtTienThua.setText("");
                txtTongTien.setText("");
                txtKhachTra.setText("");
                txtTenKH.setText("");
                txtkm.setText("");
                BanHangView.idkh = null;
                BanHangView.idkm = null;
                model = (DefaultTableModel) tblCTHH.getModel();
                model.setRowCount(0);

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Thất bại", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);

            }
        } catch (NumberFormatException numberFormatException) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Nhập Tiền Vào Thì Mới Thanh Toán", "Khuyến Mãi", JOptionPane.INFORMATION_MESSAGE, icon);
            return;
        } catch (HeadlessException headlessException) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Từ Từ Trong Ngày Vẫn Còn", "Khuyến Mãi", JOptionPane.INFORMATION_MESSAGE, icon);
            return;
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        MenuView v = new MenuView(IdNV, TenNV, CV);
        v.setLocationRelativeTo(null);
        v.setVisible(true);
        webcam.close();
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int index = tblSanPham.getSelectedRow();

        String IdSp = (String) tblSanPham.getValueAt(index, 0);// id sản phẩm

        String Tensp = (String) tblSanPham.getValueAt(index, 1); // tên sp
        int check = getSoluong(IdSp);// nếu sản phẩm tồn tại thì lấy số lượng nếu nó là 0 thì không tồn tại
        System.out.println(check);
        if (check == 0) {
            String soLuongNhapinpit = JOptionPane.showInputDialog("Nhập Số Lượng Sản Phẩm " + " " + Tensp);
            // NHẬP SỐ LƯỢNG ĐỂ INSERT 
            if (soLuongNhapinpit == null) {
                JOptionPane.showMessageDialog(this, "oke");
                return;
            }
            if (Integer.parseInt(soLuongNhapinpit) > Integer.parseInt(tblSanPham.getValueAt(index, 7).toString())) {
                JOptionPane.showMessageDialog(this, "Số lượng không đủ!");
                return;
            }

            BigDecimal giaBanBig = (BigDecimal) tblSanPham.getValueAt(index, 10); // giá bán 
            String giaban = String.valueOf(giaBanBig);
            String idhd = txtIdhd.getText();// id hóa đơn

            int soluongnhap = Integer.parseInt(soLuongNhapinpit);
            int dongia = soluongnhap * Integer.parseInt(giaban);

            HoaDonChiTiet hd = new HoaDonChiTiet();
            HoaDon a = new HoaDon();
            a.setId(idhd);

            ChiTietDoGo b = new ChiTietDoGo();
            b.setId(IdSp);

            hd.setIdHoaDon(a);
            hd.setIdChiTietDoGo(b);
            hd.setSoLuong(soluongnhap);
            hd.setDonGia(BigDecimal.valueOf(dongia));

            boolean c = cthdService.add(hd);

            if (c == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm  thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                ctdgSV.truSanPham(IdSp, soluongnhap);
                if (result_field.getText().equals("")) {
                    loadSP(page1);
                } else {
                    loadSPByTen(result_field.getText(), page2);
                }
                loadCTHH(idhd);
                int tongtien = cthdService.TongTien(txtIdhd.getText());
                txtTongTien.setText(tongtien + "");

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "lỗi", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        } else { //////////////////////////////////////////////////////////////////////////////////////////////////////////
            String soLuongNhapinpit = JOptionPane.showInputDialog("Nhập Số Lượng Sản Phẩm " + " " + Tensp);
            // NHẬP SỐ LƯỢNG ĐỂ INSERT 
            if (soLuongNhapinpit == null) {
                JOptionPane.showMessageDialog(this, "oke");
                return;
            }
            if (!soLuongNhapinpit.matches("^[0-9]+$")) {
                JOptionPane.showMessageDialog(this, "0-9");
                return;
            }
            int soluonghientai = Integer.parseInt(tblSanPham.getValueAt(index, 7).toString());
            if (Integer.parseInt(soLuongNhapinpit) > soluonghientai) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Sản Phẩm Không Đủ", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                return;
            }
            BigDecimal giaBanBig = (BigDecimal) tblSanPham.getValueAt(index, 10); // giá bán 
            String giaban = String.valueOf(giaBanBig);
            String idhd = txtIdhd.getText();// id hóa đơn

            int soluongnhap = Integer.parseInt(soLuongNhapinpit) + check;
            int soluongnhap1 = Integer.parseInt(soLuongNhapinpit);
            int dongia = soluongnhap * Integer.parseInt(giaban);

            HoaDonChiTiet hd = new HoaDonChiTiet();
            HoaDon a = new HoaDon();
            a.setId(idhd);

            ChiTietDoGo b = new ChiTietDoGo();
            b.setId(IdSp);

            hd.setIdHoaDon(a);
            hd.setIdChiTietDoGo(b);
            hd.setSoLuong(soluongnhap);
            hd.setDonGia(BigDecimal.valueOf(dongia));

            boolean c = cthdService.update(hd);

            if (c == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm  thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                ctdgSV.truSanPham(IdSp, soluongnhap1);
                System.out.println(soluongnhap1);
                if (result_field.getText().equals("")) {
                    loadSP(page1);
                } else {
                    loadSPByTen(result_field.getText(), page2);
                }
                loadCTHH(idhd);
                int tongtien = cthdService.TongTien(txtIdhd.getText());
                txtTongTien.setText(tongtien + "");

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Lỗi Trống Hóa Đơn", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }


    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblCTHHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHHMouseClicked
        String[] buttons = {"Update", "Delete", "Cancel"};

        int index = tblCTHH.getSelectedRow();

        String idsp = (String) tblCTHH.getValueAt(index, 1);

        List<ViewModelChiTietSanPhamBanHang> listsp = spService.TimKiemTheoId(idsp);

        int soluonghientai = 0;

        for (ViewModelChiTietSanPhamBanHang a : listsp) {
            soluonghientai = a.getSoLuong();
        }

        int rc = JOptionPane.showOptionDialog(null, "Question ?", "Confirmation",
                JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[2]);
        if (rc == 0) {
            String soLuongNhapinpit = JOptionPane.showInputDialog("Nhập Số Lượng Update của Sản Phẩm Sản Phẩm ");
            // NHẬP SỐ LƯỢNG ĐỂ INSERT 
            if (soLuongNhapinpit.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập");
                return;
            }
            if (!soLuongNhapinpit.matches("^[0-9]+$")) {
                JOptionPane.showMessageDialog(this, "0-9");
                return;
            }
            int SoLuongNhap = Integer.parseInt(soLuongNhapinpit);

            int giaBan = layGiaSanPhamTheoId(idsp);

            int dongia = SoLuongNhap * giaBan;

            int soLuong = Integer.parseInt(tblCTHH.getValueAt(index, 3).toString());

            HoaDonChiTiet hd = new HoaDonChiTiet();
            HoaDon a = new HoaDon();
            a.setId(txtIdhd.getText());

            ChiTietDoGo b = new ChiTietDoGo();
            b.setId(idsp);

            hd.setIdHoaDon(a);
            hd.setIdChiTietDoGo(b);
            hd.setSoLuong(SoLuongNhap);
            hd.setDonGia(BigDecimal.valueOf(dongia));

            boolean c = cthdService.update(hd);

            if (c == true) {//update
                if (SoLuongNhap > soLuong) {
                    int tru = SoLuongNhap - soLuong;
                    if (tru > soluonghientai) {
                        Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                        JOptionPane.showMessageDialog(this, "Sản Phẩm Không Đủ Để Update", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                        return;
                    }
                    ctdgSV.truSanPham(idsp, tru);
                    Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                    JOptionPane.showMessageDialog(this, "Update thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                    loadCTHH(txtIdhd.getText());
                    int tongtien = cthdService.TongTien(txtIdhd.getText());
                    txtTongTien.setText(tongtien + "");
                    if (result_field.getText().equals("")) {
                        loadSP(page1);
                    } else {
                        loadSPByTen(result_field.getText(), page2);
                    }
                } else if (SoLuongNhap < soLuong) {
                    int cong = soLuong - SoLuongNhap;
                    ctdgSV.congSanPham(idsp, cong);
                    Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                    JOptionPane.showMessageDialog(this, "Update thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                    loadCTHH(txtIdhd.getText());
                    int tongtien = cthdService.TongTien(txtIdhd.getText());
                    txtTongTien.setText(tongtien + "");
                    if (result_field.getText().equals("")) {
                        loadSP(page1);
                    } else {
                        loadSPByTen(result_field.getText(), page2);
                    }
                }
            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "lỗi", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
        if (rc == 1) {// delete

            boolean c = cthdService.delete(idsp, txtIdhd.getText());

            if (c == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "delete thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                ctdgSV.congSanPham(idsp, Integer.parseInt(tblCTHH.getValueAt(index, 3).toString()));
                if (result_field.getText().equals("")) {
                    loadSP(page1);
                } else {
                    loadSPByTen(result_field.getText(), page2);
                }
                loadCTHH(txtIdhd.getText());
                int tongtien = cthdService.TongTien(txtIdhd.getText());
                txtTongTien.setText(tongtien + "");

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "lỗi", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
        if (rc == 2) {// thoat
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "DẠ Dạ", "Hóa Đơn ChiTiet", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_tblCTHHMouseClicked

    private void txtKhachTraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhachTraKeyReleased
        // TODO add your handling code here:
        if (txtKhachTra.getText().isEmpty()) {
            return;
        } else {
            int tongTien = Integer.parseInt(txtTongTien.getText());

            int tienTra = Integer.parseInt(txtKhachTra.getText());

            int tienThua = tienTra - tongTien;

            String TT = String.valueOf(tienThua);

            txtTienThua.setText(TT.replaceAll("-", ""));

            if (tongTien > tienTra) {
                LB.setText("Chua Du");
            } else {
                LB.setText("Tien Thua");
            }
        }


    }//GEN-LAST:event_txtKhachTraKeyReleased

    private void result_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result_fieldActionPerformed

    private void cbcLoaiThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcLoaiThanhToanActionPerformed

    }//GEN-LAST:event_cbcLoaiThanhToanActionPerformed

    private void txtIdhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdhdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdhdActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        loadSP(page1);
    }//GEN-LAST:event_formWindowOpened

    private void pagination1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pagination1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pagination1MouseClicked

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
            java.util.logging.Logger.getLogger(BanHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new BanHangView().setVisible(true);
            }
        });
    }

    private void initWebcam() {

        try {
            Dimension size = WebcamResolution.QVGA.getSize();
            webcam = Webcam.getWebcams().get(0); //0 is default webcam
            webcam.setViewSize(size);

            panel = new WebcamPanel(webcam);
            panel.setPreferredSize(size);
            panel.setFPSDisplayed(true);

            jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));

            executor.execute(this);
        } catch (WebcamException webcamException) {
        }

    }

    @Override
    public void run() {
        try {
            do {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Result result = null;
                BufferedImage image = null;

                if (webcam.isOpen()) {
                    if ((image = webcam.getImage()) == null) {
                        continue;
                    }
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {

                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                    //No result...
                }

                if (result != null) {
                    System.out.println(result);
                    loadTheoId(result.getText());
                    System.out.println(result);
                    result = null;
                }
            } while (true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Thread newThread(Runnable r) {
        try {
            Thread t = new Thread(r, "My Thread");
            t.setDaemon(true);
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LB;
    private javax.swing.JCheckBox cbTaoHoaDon;
    private javax.swing.JComboBox<String> cbcLoaiThanhToan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private pagination.Pagination pagination1;
    private javax.swing.JTextField result_field;
    private javax.swing.JTable tblCTHH;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private static javax.swing.JTextField txtIdhd;
    private static javax.swing.JTextField txtKhachTra;
    private javax.swing.JTextField txtMahd;
    private javax.swing.JTextField txtNgayTao;
    private static javax.swing.JTextField txtSdt;
    private static javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNV;
    private static javax.swing.JTextField txtTienThua;
    private static javax.swing.JTextField txtTongTien;
    private static javax.swing.JTextField txtkm;
    // End of variables declaration//GEN-END:variables
}
