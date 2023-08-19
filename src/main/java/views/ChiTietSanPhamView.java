/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainModels.ChiTietDoGo;

import domainModels.DonViTinh;

import domainModels.DongGo;

import domainModels.LoaiSP;

import domainModels.NguonGoc;

import domainModels.NhaCungCap;

import domainModels.SanPham;
import java.io.File;
import java.io.FileOutputStream;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.Icon;
import javax.swing.JFileChooser;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pagination.EventPagination;
import pagination.style.PaginationItemRenderStyle1;

import services.ChiTietDoGoService;

import services.DonViTinhService;

import services.DongGoService;

import services.LoaiSanPhamService;

import services.NguonGocService;

import services.NhaCungCapService;
import services.NhapXuatService;

import services.SanPhamService;
import services.TimKiemCTSPService;

import services.impl.IManageChiTietDoGoService;

import services.impl.IManageDonViTinhService;

import services.impl.IManageDongGoService;

import services.impl.IManageLoaiSanPhamService;

import services.impl.IManageNguonGocService;

import services.impl.IManageNhaCungCapService;

import services.impl.IManageSanPhamService;
import services.impl.IManageTimKiemCTSPSService;
import services.impl.IManagerNhapXuat;

import viewModel.ChiTietDoGoViewModel;
import viewModel.ViewModelChiTietSanPhamBanHang;

import viewModel.ViewModelDonViTinh;

import viewModel.ViewModelDongGo;

import viewModel.ViewModelLoaiSanPham;

import viewModel.ViewModelNguonGoc;

import viewModel.ViewModelNhaCungCap;
import viewModel.ViewModelNhapView;

import viewModel.ViewModelSanPham;

/**
 *
 * @author Admin
 */
public class ChiTietSanPhamView extends javax.swing.JFrame {

    /**
     * Creates new form ChiTietSanPhamView
     */
    private static IManageChiTietDoGoService a = new ChiTietDoGoService();

    private static IManageSanPhamService spSV = new SanPhamService();

    private static IManageLoaiSanPhamService lSpSv = new LoaiSanPhamService();

    private static IManageNhaCungCapService nhaCungCapService = new NhaCungCapService();

    private static IManageDonViTinhService dvtrp = new DonViTinhService();

    private static IManageNguonGocService ngSv = new NguonGocService();

    private static IManageDongGoService dgSV = new DongGoService();

    static DefaultTableModel model = new DefaultTableModel();

    private static IManagerNhapXuat nhapxuatServic = new NhapXuatService();

    private static IManageTimKiemCTSPSService timKiemSV = new TimKiemCTSPService();

    private static int page1 = 1;

    private static int page2 = 1;

    String IdNV;

    String TenNV;

    String CV;

    public ChiTietSanPhamView(String Id, String Ten, String cv) {

        this.setDefaultCloseOperation(ChiTietSanPhamView.DO_NOTHING_ON_CLOSE);

        initComponents();

        IdNV = Id;

        TenNV = Ten;

        CV = cv;

        setLocationRelativeTo(null);

        loadcbc();

        phanTrang(page1);
        pagination1.setPaginationItemRender(new PaginationItemRenderStyle1());
        pagination1.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                if (txtTimKiem.getText().equals("")) {
                    page1 = page;
                    phanTrang(page);
                } else {
                    page2 = page;
                    searchByTen(txtTimKiem.getText(), page);
                }
            }
        });

    }

    
    public void loadTBTu1Den3M() {
        
        model = (DefaultTableModel) tbl.getModel();
        
        model.setRowCount(0);

        List<ChiTietDoGoViewModel> list = a.getListTu1Den3M();
        for (ChiTietDoGoViewModel n : list) {
            model.addRow(new Object[]{
                model.getRowCount() + 1,
                n.getId(),
                n.getTensp(),
                n.getSp(),
                n.getLoad(),
                n.getDonggo(),
                n.getNcc(),
                n.getNguongoc(),
                n.getDonvi(),
                n.getMota(),
                n.getSoluong(),
                String.valueOf(n.getGiaNhap().intValue()),
                String.valueOf(n.getGiaBan().intValue())
            });
        }
    }
    
    public void loadTBTu3Den5M() {
        
        model = (DefaultTableModel) tbl.getModel();
        
        model.setRowCount(0);

        List<ChiTietDoGoViewModel> list = a.getListTu3Den5M();
        for (ChiTietDoGoViewModel n : list) {
            model.addRow(new Object[]{
                model.getRowCount() + 1,
                n.getId(),
                n.getTensp(),
                n.getSp(),
                n.getLoad(),
                n.getDonggo(),
                n.getNcc(),
                n.getNguongoc(),
                n.getDonvi(),
                n.getMota(),
                n.getSoluong(),
                String.valueOf(n.getGiaNhap().intValue()),
                String.valueOf(n.getGiaBan().intValue())
            });
        }
    }
    
    public void loadTBTu5Den10M() {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);

        List<ChiTietDoGoViewModel> list = a.getListTu5Den10M();
        for (ChiTietDoGoViewModel n : list) {
            model.addRow(new Object[]{
                model.getRowCount() + 1,
                n.getId(),
                n.getTensp(),
                n.getSp(),
                n.getLoad(),
                n.getDonggo(),
                n.getNcc(),
                n.getNguongoc(),
                n.getDonvi(),
                n.getMota(),
                n.getSoluong(),
                String.valueOf(n.getGiaNhap().intValue()),
                String.valueOf(n.getGiaBan().intValue())
            });
        }
    }
    
    public void loadTBLonHon10M() {
        
        model = (DefaultTableModel) tbl.getModel();
        
        model.setRowCount(0);

        List<ChiTietDoGoViewModel> list = a.getListLonHon10Trieu();
        for (ChiTietDoGoViewModel n : list) {
            model.addRow(new Object[]{
                model.getRowCount() + 1,
                n.getId(),
                n.getTensp(),
                n.getSp(),
                n.getLoad(),
                n.getDonggo(),
                n.getNcc(),
                n.getNguongoc(),
                n.getDonvi(),
                n.getMota(),
                n.getSoluong(),
                String.valueOf(n.getGiaNhap().intValue()),
                String.valueOf(n.getGiaBan().intValue())
            });
        }
    }

    public void loadTBNhoHon1Trieu() {
        
        model = (DefaultTableModel) tbl.getModel();
        
        model.setRowCount(0);

        List<ChiTietDoGoViewModel> list = a.getListNhoHon1Trieu();
        for (ChiTietDoGoViewModel n : list) {
            model.addRow(new Object[]{
                model.getRowCount() + 1,
                n.getId(),
                n.getTensp(),
                n.getSp(),
                n.getLoad(),
                n.getDonggo(),
                n.getNcc(),
                n.getNguongoc(),
                n.getDonvi(),
                n.getMota(),
                n.getSoluong(),
                String.valueOf(n.getGiaNhap().intValue()),
                String.valueOf(n.getGiaBan().intValue())
            });
        }
    }

    public static void phanTrang(int page) {
        
        int limit = 3;
        int count = 0;

        List<ChiTietDoGoViewModel> ct = a.phanTrangCTDG((page - 1) * limit, limit);
        if (ct == null) {
            return;
        }

        count = a.row();

        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);

        for (ChiTietDoGoViewModel n : ct) {
            model.addRow(new Object[]{
                model.getRowCount() + 1,
                n.getId(),
                n.getTensp(),
                n.getSp(),
                n.getLoad(),
                n.getDonggo(),
                n.getNcc(),
                n.getNguongoc(),
                n.getDonvi(),
                n.getMota(),
                n.getSoluong(),
                String.valueOf(n.getGiaNhap().intValue()),
                String.valueOf(n.getGiaBan().intValue()),});
        }

        int totalPage = (int) Math.ceil(count / limit);

        pagination1.setPagegination(page, totalPage + 1);

    }

    public void searchByTen(String ten, int page1) {
        
        try {
            int limit = 3;
            int count = 0;

            List<ChiTietDoGoViewModel> list = timKiemSV.timKiemPhanTrang((page1 - 1) * limit, limit, ten);

            if (list == null) {
                return;
            }

            count = timKiemSV.row(ten);

            int totalPage = (int) Math.ceil(count / limit);

            model = (DefaultTableModel) tbl.getModel();
            model.setColumnCount(0);
            model.addColumn("STT");
            model.addColumn("Id");
            model.addColumn("TenSP");
            model.addColumn("SPham");
            model.addColumn("LoaiSP");
            model.addColumn("DongGo");
            model.addColumn("NhaCungCap");
            model.addColumn("NguonGoc");
            model.addColumn("Dv Tinh");
            model.addColumn("MoTa");
            model.addColumn("SoLuong");
            model.addColumn("GiaNhap");
            model.addColumn("GiaBan");
            model.setRowCount(0);

            for (ChiTietDoGoViewModel x : list) {
                model.addRow(new Object[]{
                    model.getRowCount() + 1,
                    x.getId(),
                    x.getTensp(),
                    x.getSp(),
                    x.getLoad(),
                    x.getDonggo(),
                    x.getNcc(),
                    x.getNguongoc(),
                    x.getDonvi(),
                    x.getMota(),
                    x.getSoluong(),
                    String.valueOf(x.getGiaNhap().intValue()),
                    String.valueOf(x.getGiaBan().intValue()),});
            }

            if (count / limit != 0) {
                pagination1.setPagegination(page1, totalPage + 1);
            } else if (count / limit == 0) {
                pagination1.setPagegination(page1, totalPage);
            }
        } catch (Exception e) {
        }
    }

    public static void load() {

        model = (DefaultTableModel) tbl.getModel();

        model.setRowCount(0);

        phanTrang(page1);
    }

    public void timKiem() {

        model = (DefaultTableModel) tbl.getModel();

        model.setRowCount(0);

        List<ChiTietDoGoViewModel> list = a.listtk(txtTimKiem.getText());

        for (ChiTietDoGoViewModel n : list) {

            model.addRow(new Object[]{
                model.getRowCount() + 1,
                n.getId(),
                n.getTensp(),
                n.getSp(),
                n.getLoad(),
                n.getDonggo(),
                n.getNcc(),
                n.getNguongoc(),
                n.getDonvi(),
                n.getMota(),
                n.getSoluong(),
                n.getGiaNhap(),
                n.getGiaBan()

            });

        }

    }

    public String getSPCBC() {

        String id = null;

        List<ViewModelSanPham> a = spSV.getListSP();

        for (ViewModelSanPham sp : a) {

            if (cbcSP.getSelectedItem().equals(sp.getTen())) {

                id = sp.getId();

            }
        }

        return id;
    }

    public String getDGCBC() {

        String id = null;

        List<ViewModelDongGo> b = dgSV.getListDongGo();

        for (ViewModelDongGo dg : b) {

            if (cbcDongGo.getSelectedItem().equals(dg.getTenLoaiGo())) {

                id = dg.getId();

            }

        }

        return id;

    }

    public String getLoaiCBC() {

        String id = null;

        List<ViewModelLoaiSanPham> c = lSpSv.getListLoaiSP();

        for (ViewModelLoaiSanPham l : c) {

            if (cbcLoaiSP.getSelectedItem().equals(l.getTenDongSP())) {

                id = l.getId();

            }

        }

        return id;

    }

    public String getNCCCBC() {

        String id = null;

        List<ViewModelNhaCungCap> d = nhaCungCapService.getAll();

        for (ViewModelNhaCungCap ncc : d) {

            if (ncc.getTenNCC().equals(cbNhaCC.getSelectedItem())) {

                id = ncc.getId();

            }

        }

        return id;

    }

    public String getDVTCBC() {

        String id = null;

        List<ViewModelDonViTinh> e = dvtrp.getListDVT();

        for (ViewModelDonViTinh dvt : e) {

            if (cbcDVT.getSelectedItem().equals(dvt.getDonViTinh())) {

                id = dvt.getId();

            }

        }

        return id;

    }

    public String getNguonGocTCBC() {

        String id = null;

        List<ViewModelNguonGoc> f = ngSv.getAll();

        for (ViewModelNguonGoc ng : f) {

            if (cbcNguonGoc.getSelectedItem().equals(ng.getquocgia())) {

                id = ng.getId();

            }

        }

        return id;

    }

    public static void loadcbc() {

        List<ViewModelSanPham> a = spSV.getListSP();
        
        cbcSP.removeAllItems();

        for (ViewModelSanPham sp : a) {
            
            cbcSP.addItem(sp.getTen());

        }

        List<ViewModelDongGo> b = dgSV.getListDongGo();
        
        cbcDongGo.removeAllItems();

        for (ViewModelDongGo dg : b) {

            cbcDongGo.addItem(dg.getTenLoaiGo());

        }
        List<ViewModelLoaiSanPham> c = lSpSv.getListLoaiSP();
        
        cbcLoaiSP.removeAllItems();

        for (ViewModelLoaiSanPham l : c) {

            cbcLoaiSP.addItem(l.getTenDongSP());

        }

        List<ViewModelNhaCungCap> d = nhaCungCapService.getAll();
        
        cbNhaCC.removeAllItems();

        for (ViewModelNhaCungCap ncc : d) {

            cbNhaCC.addItem(ncc.getTenNCC());

        }

        List<ViewModelDonViTinh> e = dvtrp.getListDVT();
        
        cbcDVT.removeAllItems();

        for (ViewModelDonViTinh dvt : e) {

            cbcDVT.addItem(dvt.getDonViTinh());

        }

        List<ViewModelNguonGoc> f = ngSv.getAll();
        
        cbcNguonGoc.removeAllItems();

        for (ViewModelNguonGoc ng : f) {

            cbcNguonGoc.addItem(ng.getquocgia());

        }

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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        pagination1 = new pagination.Pagination();
        jLabel16 = new javax.swing.JLabel();
        CBBLocGia = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cbcLoaiSP = new javax.swing.JComboBox<>();
        cbcSP = new javax.swing.JComboBox<>();
        cbcDongGo = new javax.swing.JComboBox<>();
        cbcNguonGoc = new javax.swing.JComboBox<>();
        cbNhaCC = new javax.swing.JComboBox<>();
        cbcDVT = new javax.swing.JComboBox<>();
        txtid = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taMoTa = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "id", "Ten SP", "SPham", "LoaiSP", "DongGo", "NhaCungCap", "NguonGoc", "Dv Tinh", "MoTa", "SoLuong", "GiaNhap", "GiaBan"
            }
        ));
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        tbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);

        txtTimKiem.setBackground(new java.awt.Color(255, 204, 255));
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        pagination1.setOpaque(false);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Lọc giá bán :");

        CBBLocGia.setBackground(new java.awt.Color(255, 204, 255));
        CBBLocGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CBBLocGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "< 1m", "Từ 1 - 3m", "Từ 3 - 5m", "Từ 5 - 10m", "> 10m" }));
        CBBLocGia.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        CBBLocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBBLocGiaActionPerformed(evt);
            }
        });
        CBBLocGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CBBLocGiaKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText(": Tìm Kiếm ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CBBLocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(CBBLocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        cbcLoaiSP.setBackground(new java.awt.Color(255, 204, 255));
        cbcLoaiSP.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        cbcSP.setBackground(new java.awt.Color(255, 204, 255));
        cbcSP.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cbcSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcSPActionPerformed(evt);
            }
        });

        cbcDongGo.setBackground(new java.awt.Color(255, 204, 255));
        cbcDongGo.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        cbcNguonGoc.setBackground(new java.awt.Color(255, 204, 255));
        cbcNguonGoc.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        cbNhaCC.setBackground(new java.awt.Color(255, 204, 255));
        cbNhaCC.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        cbcDVT.setBackground(new java.awt.Color(255, 204, 255));
        cbcDVT.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        txtid.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtid.setText("id");
        txtid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Id");

        txtGiaNhap.setBackground(new java.awt.Color(255, 204, 255));
        txtGiaNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGiaNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên");

        txtTen.setBackground(new java.awt.Color(255, 204, 255));
        txtTen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Giá Nhập");

        txtGiaBan.setBackground(new java.awt.Color(255, 204, 255));
        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Giá Bán");

        taMoTa.setColumns(20);
        taMoTa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        taMoTa.setRows(5);
        jScrollPane2.setViewportView(taMoTa);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Mô Tả");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Sản Phẩm");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Dòng Gỗ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Đơn Vị Tính");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Loại Sp");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("  Nhà Cung Cấp ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Nguồn Gốc");

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(255, 204, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Add");
        jButton2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 111, 38));

        jButton3.setBackground(new java.awt.Color(255, 204, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Delete");
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 111, 40));

        jButton4.setBackground(new java.awt.Color(255, 204, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Update");
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 111, 40));

        jButton5.setBackground(new java.awt.Color(255, 204, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Nhập");
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 111, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Số  Lượng");

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSoLuong.setText("Soluong");

        jButton6.setBackground(new java.awt.Color(255, 204, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("BACK");
        jButton6.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 204, 255));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Lịch Sử Nhập");
        jButton7.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jButton1.setText("Xuất File Excel");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("VNĐ");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("VNĐ");

        jButton8.setBackground(new java.awt.Color(255, 204, 255));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setText("NEW");
        jButton8.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(255, 204, 255));
        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 204, 255));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/themSanPham.png"))); // NOI18N
        jButton12.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(255, 204, 255));
        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 204, 255));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/themSanPham.png"))); // NOI18N
        jButton15.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(255, 204, 255));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 204, 255));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/themSanPham.png"))); // NOI18N
        jButton13.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(255, 204, 255));
        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 204, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/themSanPham.png"))); // NOI18N
        jButton14.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(255, 204, 255));
        jButton16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 204, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/themSanPham.png"))); // NOI18N
        jButton16.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(255, 204, 255));
        jButton17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 204, 255));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/themSanPham.png"))); // NOI18N
        jButton17.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(177, 177, 177)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(84, 84, 84)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(38, 38, 38)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(56, 56, 56)
                            .addComponent(jLabel7)
                            .addGap(23, 23, 23)
                            .addComponent(cbcSP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel3)
                                .addGap(38, 38, 38)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(274, 274, 274)
                                .addComponent(jLabel8)
                                .addGap(32, 32, 32)
                                .addComponent(cbcDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel15)
                                        .addGap(281, 281, 281)
                                        .addComponent(jLabel10)
                                        .addGap(32, 32, 32)
                                        .addComponent(cbcLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(51, 51, 51)
                                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(5, 5, 5)
                                            .addComponent(jLabel14)
                                            .addGap(263, 263, 263)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(23, 23, 23)
                                            .addComponent(cbNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(38, 38, 38)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(71, 71, 71)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(27, 27, 27)
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(110, 110, 110)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel12))
                                            .addGap(22, 22, 22)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(cbcNguonGoc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(cbcDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(1, 1, 1))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbcSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbcDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbcLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbcDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbcNguonGoc, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        if (txtTimKiem.getText().equals("")) {

            phanTrang(page1);

        } else {

//            timKiem();
            searchByTen(txtTimKiem.getText(), page2);

        }

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        int index = tbl.getSelectedRow();

        txtid.setText(tbl.getValueAt(index, 1).toString());

        txtTen.setText(tbl.getValueAt(index, 2).toString());

        txtGiaNhap.setText(tbl.getValueAt(index, 11).toString());

        System.out.println(tbl.getValueAt(index, 10).toString());

        txtGiaBan.setText(tbl.getValueAt(index, 12).toString());

        taMoTa.setText(tbl.getValueAt(index, 9).toString());

        txtSoLuong.setText(tbl.getValueAt(index, 10).toString());

        cbcSP.setSelectedItem(tbl.getValueAt(index, 3).toString());

        cbcLoaiSP.setSelectedItem(tbl.getValueAt(index, 4).toString());

        cbcDongGo.setSelectedItem(tbl.getValueAt(index, 5).toString());

        cbNhaCC.setSelectedItem(tbl.getValueAt(index, 6).toString());

        cbcDVT.setSelectedItem(tbl.getValueAt(index, 7).toString());

        cbcNguonGoc.setSelectedItem(tbl.getValueAt(index, 8).toString());

    }//GEN-LAST:event_tblMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String idsp = getSPCBC();

        String iddonggo = getDGCBC();

        String idLoai = getLoaiCBC();

        String Ncc = getNCCCBC();

        String dvt = getDVTCBC();

        String nguongoc = getNguonGocTCBC();

        String tensp = txtTen.getText();

        BigDecimal giaNhap = new BigDecimal(txtGiaNhap.getText());

        BigDecimal giaBan = new BigDecimal(txtGiaBan.getText());

        String mota = taMoTa.getText();

        ChiTietDoGo dg = new ChiTietDoGo();

        SanPham aa = new SanPham();

        aa.setId(idsp);

        DongGo bb = new DongGo();

        bb.setId(iddonggo);

        LoaiSP c = new LoaiSP();

        c.setId(idLoai);

        NhaCungCap d = new NhaCungCap();

        d.setId(Ncc);

        DonViTinh e = new DonViTinh();

        e.setId(dvt);

        NguonGoc f = new NguonGoc();

        f.setId(nguongoc);

        dg.setIdSanPham(aa);

        dg.setIdDongGo(bb);

        dg.setIdLoaiSP(c);

        dg.setIdNhaCungCap(d);

        dg.setIdDonViTinh(e);

        dg.setIdNguocGoc(f);

        dg.setTenSP(tensp);

        dg.setMoTa(mota);

        dg.setGiaNhap(giaNhap);

        dg.setGiaBan(giaBan);

        boolean b = a.add(dg);

        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));

            JOptionPane.showMessageDialog(this, "Thêm sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);

            phanTrang(page1);

        } else {

            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));

            JOptionPane.showMessageDialog(this, "Lỗi Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String id = txtid.getText();

        String idsp = getSPCBC();

        String iddonggo = getDGCBC();

        String idLoai = getLoaiCBC();

        String Ncc = getNCCCBC();

        String dvt = getDVTCBC();

        String nguongoc = getNguonGocTCBC();

        String tensp = txtTen.getText();

        BigDecimal giaNhap = new BigDecimal(txtGiaNhap.getText());

        BigDecimal giaBan = new BigDecimal(txtGiaBan.getText());

        String mota = taMoTa.getText();

        ChiTietDoGo dg = new ChiTietDoGo();

        SanPham aa = new SanPham();

        aa.setId(idsp);

        DongGo bb = new DongGo();

        bb.setId(iddonggo);

        LoaiSP c = new LoaiSP();

        c.setId(idLoai);

        NhaCungCap d = new NhaCungCap();

        d.setId(Ncc);

        DonViTinh e = new DonViTinh();

        e.setId(dvt);

        NguonGoc f = new NguonGoc();

        f.setId(nguongoc);

        dg.setId(id);

        dg.setIdSanPham(aa);

        dg.setIdDongGo(bb);

        dg.setIdLoaiSP(c);

        dg.setIdNhaCungCap(d);

        dg.setIdDonViTinh(e);

        dg.setIdNguocGoc(f);

        dg.setTenSP(tensp);

        dg.setMoTa(mota);

        dg.setGiaNhap(giaNhap);

        dg.setGiaBan(giaBan);

        boolean b = a.update(dg);

        if (b == true) {

            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));

            JOptionPane.showMessageDialog(this, "Update sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);

            phanTrang(page1);

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));

            JOptionPane.showMessageDialog(this, "Update Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);

        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        boolean b = a.delete(txtid.getText());

        if (b == true) {

            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));

            JOptionPane.showMessageDialog(this, "Delete sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);

            phanTrang(page1);

        } else {

            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));

            JOptionPane.showMessageDialog(this, "Delete Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        NhapXuatView i = new NhapXuatView(txtid.getText(), txtTen.getText(), txtGiaNhap.getText(), txtSoLuong.getText());

        i.setVisible(true);

        i.pack();

        i.setLocationRelativeTo(null);

        i.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void tblKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblKeyTyped

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        MenuView v = new MenuView(IdNV, TenNV, CV);

        v.setLocationRelativeTo(null);

        v.setVisible(true);

        this.dispose();

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        LichSuNhapView v = new LichSuNhapView();

        v.setLocationRelativeTo(null);

        v.setVisible(true);

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        List<ChiTietDoGoViewModel> list = a.list();
        XSSFWorkbook workbook = new XSSFWorkbook(); // không cần quan tâm
        //Create a blank sheet
        XSSFSheet sheet1 = workbook.createSheet("Employee Data");//Các sheet trong excel hay còn gọi là Trang 1
        XSSFSheet sheet2 = workbook.createSheet("CTSP ");//Các sheet trong excel hay còn gọi là Trang 2

        //This data needs to be written (Object[]) load vào để chuẩn bị loat lên các dòng
        int i = 1;
        Map<String, Object[]> data1 = new TreeMap<String, Object[]>();
        
        for (ChiTietDoGoViewModel a : list) {
            data1.put(String.valueOf(i++),
                    new Object[]{a.getId(),
                        a.getTensp(),
                        a.getDonggo(),
                        a.getDonvi(),
                        a.getNguongoc(),
                        a.getNcc(),
                        a.getMota(),
                        a.getGiaNhap(),
                        a.getGiaBan()});
        }
        int j = 1;
        Map<String, Object[]> data2 = new TreeMap<String, Object[]>();
        for (ViewModelNhapView lView : nhapxuatServic.getList()) {
            data2.put(String.valueOf(i++), new Object[]{
                lView.getId(),
                lView.getIdsp(),
                lView.getTensp(),
                lView.getNgayNhap(),
                lView.getSlNhap(),
                lView.getTongTien()
            });
        }

        Set<String> keyset1 = data1.keySet();
        int rownum1 = 0;
        for (String key : keyset1) {
            Row row1 = sheet1.createRow(rownum1++);
            Object[] objArr1 = data1.get(key);
            int cellnum1 = 0;
            for (Object obj : objArr1) {
                Cell cell1 = row1.createCell(cellnum1++);
                if (obj instanceof String) {
                    cell1.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell1.setCellValue((Integer) obj);
                }
            }
        }
        //Iterate over data and write to sheet 2
        Set<String> keyset2 = data2.keySet();
        int rownum2 = 0;
        for (String key : keyset2) {
            Row row2 = sheet2.createRow(rownum2++);
            Object[] objArr2 = data2.get(key);
            int cellnum2 = 0;
            for (Object obj : objArr2) {
                Cell cell2 = row2.createCell(cellnum2++);
                if (obj instanceof String) {
                    cell2.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell2.setCellValue((Integer) obj);
                }
            }
        }

        try {
            //Write the workbook in file system
            JFileChooser chooser = new JFileChooser();// mở file lên 
            chooser.showOpenDialog(null);//để chọn lưu vào đâu
            File file = chooser.getSelectedFile();
            FileOutputStream out = new FileOutputStream(new File(file + ".xlsx"));// lưu dưới dạng excel 
            workbook.write(out);
            out.close();
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "In thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        txtid.setText("id");
        
        txtTen.setText("");
        
        txtGiaNhap.setText("");
        
        txtGiaBan.setText("");
        
        taMoTa.setText("");
        
        txtSoLuong.setText("0");
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cbcSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbcSPActionPerformed

    private void CBBLocGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CBBLocGiaKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_CBBLocGiaKeyReleased

    private void CBBLocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBBLocGiaActionPerformed
        // TODO add your handling code here:
        if (CBBLocGia.getSelectedItem().equals("All")) {
            
            phanTrang(page1);
            
        } else if (CBBLocGia.getSelectedItem().equals("< 1m")) {
            
            loadTBNhoHon1Trieu();
            
//            System.out.println("OKE");

        } else if (CBBLocGia.getSelectedItem().equals("Từ 1 - 3m")) {
            
            loadTBTu1Den3M();
            
//            System.out.println(" < 1 triệu");

        } else if (CBBLocGia.getSelectedItem().equals("Từ 3 - 5m")) {
            
            loadTBTu3Den5M();
            
//            System.out.println("Từ 3 - 5m");
            
        } else if (CBBLocGia.getSelectedItem().equals("Từ 5 - 10m")) {
            
            loadTBTu5Den10M();
            
//            System.out.println("Từ 5 - 10m");
            
        } else {
            
            loadTBLonHon10M();
            
//            System.out.println("> 10m");
            
        }
        
        
//             All
//            < 1m
//            Từ 1 - 3m
//            Từ 3 - 5m
//            Từ 5 - 10m
//            > 10m
    }//GEN-LAST:event_CBBLocGiaActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        ThemNhanhDongGoView tn = new ThemNhanhDongGoView();

        tn.setVisible(true);

        tn.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        ThemNhanhSanPhamView tn = new ThemNhanhSanPhamView();

        tn.setVisible(true);

        tn.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:

        ThemNhanhNCCView tn = new ThemNhanhNCCView();

        tn.setVisible(true);

        tn.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:

        ThemNhanhDVTView tn = new ThemNhanhDVTView();

        tn.setVisible(true);

        tn.setLocationRelativeTo(null);

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:

        ThemNhanhNguonGocView tn = new ThemNhanhNguonGocView();

        tn.setVisible(true);

        tn.setLocationRelativeTo(null);

    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        ThemNhanhLoaiSPView tn = new ThemNhanhLoaiSPView();

        tn.setVisible(true);

        tn.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        // TODO add your handling code here:
//        txtTimKiem.setText("");
    }//GEN-LAST:event_txtTimKiemMouseClicked

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
            java.util.logging.Logger.getLogger(ChiTietSanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new ChiTietSanPhamView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBBLocGia;
    private static javax.swing.JComboBox<String> cbNhaCC;
    private static javax.swing.JComboBox<String> cbcDVT;
    private static javax.swing.JComboBox<String> cbcDongGo;
    private static javax.swing.JComboBox<String> cbcLoaiSP;
    private static javax.swing.JComboBox<String> cbcNguonGoc;
    private static javax.swing.JComboBox<String> cbcSP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static pagination.Pagination pagination1;
    private javax.swing.JTextArea taMoTa;
    private static javax.swing.JTable tbl;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JLabel txtSoLuong;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JLabel txtid;
    // End of variables declaration//GEN-END:variables
}
