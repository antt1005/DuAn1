/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainModels.DonViTinh;
import domainModels.DongGo;
import domainModels.LoaiSP;
import domainModels.NguonGoc;
import domainModels.NhaCungCap;
import domainModels.SanPham;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pagination.EventPagination;
import pagination.style.PaginationItemRenderStyle1;
import services.DonViTinhService;
import services.DongGoService;
import services.LoaiSanPhamService;
import services.NguonGocService;
import services.NhaCungCapService;
import services.SanPhamService;
import services.impl.IManageDonViTinhService;
import services.impl.IManageDongGoService;
import services.impl.IManageLoaiSanPhamService;
import services.impl.IManageNguonGocService;
import services.impl.IManageNhaCungCapService;
import services.impl.IManageSanPhamService;
import viewModel.ViewModelDonViTinh;
import viewModel.ViewModelDongGo;
import viewModel.ViewModelLoaiSanPham;
import viewModel.ViewModelNguonGoc;
import viewModel.ViewModelNhaCungCap;
import viewModel.ViewModelSanPham;

/**
 *
 * @author ktkha
 */
public class SanPhamView extends javax.swing.JFrame {

    /**
     * Creates new form SanPhamView
     */
    private IManageSanPhamService spSV = new SanPhamService();
    private IManageLoaiSanPhamService lSpSv = new LoaiSanPhamService();
    private IManageNhaCungCapService nhaCungCapService = new NhaCungCapService();
    private IManageDonViTinhService dvtrp = new DonViTinhService();
    private IManageNguonGocService ngSv = new NguonGocService();
    private IManageDongGoService dgSV = new DongGoService();
    DefaultTableModel model = new DefaultTableModel();

    String IdNV;
    String TenNV;
    String CV;

    public SanPhamView(String Id, String Ten, String cv) {
        initComponents();
        
        loadTbSanPhamPhanTrang(1);
        pagination1.setPaginationItemRender(new PaginationItemRenderStyle1());
        pagination1.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadTbSanPhamPhanTrang(page);
            }
        });
        
        loadTbNXSPhanTrang(1);
        pagination2.setPaginationItemRender(new PaginationItemRenderStyle1());
        pagination2.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadTbNXSPhanTrang(page);
            }
        });


        this.setDefaultCloseOperation(SanPhamView.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        IdNV = Id;
        TenNV = Ten;
        CV = cv;
        //San Pham
        txtID.setEnabled(false);
        txtMa.setEnabled(false);
        loadTB();
        load1st();

        //LoaiSanPham
        txtIDLoaiSp.setEnabled(false);
        txtMaLoaiSp.setEnabled(false);
        loadTbLoaiSP();
        loadSp1();

        // dong go
        txtIDDongGo.setEnabled(false);
        txtMaDongGo.setEnabled(false);
        loadTBDongGo();
        load1stDongGo();

        // Nhà cung cấp
        txtma.setEnabled(false);
        txtid.setEnabled(false);
        loadTbaleNhaCungCap();
        load1stNhaCungCap();

        //don vi tinh
        TXTIDDVT.setEnabled(false);
        TXTMADVT.setEnabled(false);
        loadTBDVT();
        loadthgdautien();

        //Nguon Goc
        txtIDNguonGoc.setEnabled(false);
        txtMaNguonGoc.setEnabled(false);
        loadTBNguonGoc();
        loadPhanTu1();
    }

    //NguonGoc
    public void loadTBNguonGoc() {
        model = (DefaultTableModel) tblNguonGoc.getModel();
        model.setRowCount(0);
        List<ViewModelNguonGoc> sp = ngSv.getAll();
        for (ViewModelNguonGoc v : sp) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getquocgia()
            });
        }
    }

    public void loadPhanTu1() {
        List<ViewModelNguonGoc> sp = ngSv.getAll();
        if (sp.isEmpty()) {
            return;
        }
        int index = 0;

        txtIDNguonGoc.setText(tblNguonGoc.getValueAt(index, 0).toString());
        txtMaNguonGoc.setText(tblNguonGoc.getValueAt(index, 1).toString());
        txtQuocGia.setText(tblNguonGoc.getValueAt(index, 2).toString());
    }

    public void getQuocGia(String ten) {
        model = (DefaultTableModel) tblNguonGoc.getModel();
        model.setRowCount(0);
        List<ViewModelNguonGoc> dg = ngSv.getTenQuocGia(ten);
        for (ViewModelNguonGoc v : dg) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getquocgia()
            });
        }
    }

    // Don vi tinh
    public void loadTBDVT() {
        model = (DefaultTableModel) TBDONVITINH.getModel();
        model.setRowCount(0);
        List<ViewModelDonViTinh> sp = dvtrp.getListDVT();
        for (ViewModelDonViTinh v : sp) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getDonViTinh()
            });
        }
    }

    public void getNameDONVITINH(String ten) {
        model = (DefaultTableModel) TBDONVITINH.getModel();
        model.setRowCount(0);
        List<ViewModelDonViTinh> dg = dvtrp.getListDVTByName(ten);
        for (ViewModelDonViTinh v : dg) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getDonViTinh()
            });
        }
    }

    public void loadthgdautien() {
        List<ViewModelDonViTinh> dg = dvtrp.getListDVT();
        if (dg.isEmpty()) {
            return;
        }
        int index = 0;

        TXTIDDVT.setText(TBDONVITINH.getValueAt(index, 0).toString());
        TXTMADVT.setText(TBDONVITINH.getValueAt(index, 1).toString());
        TXTDVT.setText(TBDONVITINH.getValueAt(index, 2).toString());
    }

    public void loadTB() { // Table San Pham
        model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        List<ViewModelSanPham> sp = spSV.getListSP();
        for (ViewModelSanPham v : sp) {
            model.addRow(new Object[]{
                v.getId(), "Sp " + v.getMa(), v.getTen()
            });
        }
    }

    public void timKiemSP(String ten) { // Table San Pham
        model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        List<ViewModelSanPham> sp = spSV.getListSPByName(ten);
        for (ViewModelSanPham v : sp) {
            model.addRow(new Object[]{
                v.getId(), "Sp " + v.getMa(), v.getTen()
            });
        }
    }

    public boolean checkTen() { //check ten sp

        String ten = txtTen.getText();
        List<ViewModelSanPham> sp = spSV.getListSP();
        for (ViewModelSanPham v : sp) {

            if (ten.isBlank()) {
                JOptionPane.showMessageDialog(this, "Tên trong !");
                return false;
            }
        }
        return true;

    }

    public boolean checkTenNguocGoc() { //check ten nguồn quốc

        String ten = txtQuocGia.getText();
        List<ViewModelNguonGoc> sp = ngSv.getAll();
        for (ViewModelNguonGoc v : sp) {
            if (ten.isBlank()) {
                JOptionPane.showMessageDialog(this, "Tên trong !");
                return false;
            }

        }
        return true;
    }

    public boolean checkTenDongGo() { //check ten dòng gỗ
        String ten = txtTenDongGo.getText();
        List<ViewModelDongGo> dg = dgSV.getListDongGo();
        for (ViewModelDongGo v : dg) {
            if (ten.isBlank()) {
                JOptionPane.showMessageDialog(this, "Tên trong !");
                return false;
            }
        }
        return true;
    }

    public boolean checkDVT() { //check DVT
        String ten = TXTDVT.getText();
        List<ViewModelDonViTinh> sp = dvtrp.getListDVT();
        for (ViewModelDonViTinh v : sp) {
            if (ten.isBlank()) {
                JOptionPane.showMessageDialog(this, "DVT trong!");
                return false;
            }
        }
        return true;
    }

    public void load1st() { // load doi tuong 1 sp
        List<ViewModelSanPham> sp = spSV.getListSP();
        if (sp.isEmpty()) {

            return;
        }
        int index = 0;

        txtID.setText(tblSanPham.getValueAt(index, 0).toString());
        txtMa.setText(tblSanPham.getValueAt(index, 1).toString());
        txtTen.setText(tblSanPham.getValueAt(index, 2).toString());
    }

    public void loadTbSanPhamPhanTrang(int page) {
        int limit = 3;
        int count = 0;
        List<ViewModelSanPham> sp = spSV.getListSP((page - 1) * limit, limit);
        if (sp == null) {
            return;
        }
        count = spSV.row();

        model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);

        for (ViewModelSanPham v : sp) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTen(), v.getTrangThai()
            });
        }

        int totalPage = (int) Math.ceil(count / limit);
        if (count / limit != 0) {
            pagination1.setPagegination(page, totalPage + 1);
        } else if (count / limit == 0) {
            pagination1.setPagegination(page, totalPage);
        }

    }

    public void loadTbNXSPhanTrang(int page) {
        int limit = 3;
        int count = 0;
        
        List<ViewModelNhaCungCap> ncc = nhaCungCapService.getListSP((page - 1) * limit, limit);
        if (ncc == null) {
            return;
        }
        count = nhaCungCapService.row();

        model = (DefaultTableModel) tbNhaSanXuat.getModel();
        model.setRowCount(0);

        for (ViewModelNhaCungCap v : ncc) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTenNCC(), v.getDiaChi(), v.getSdt(), v.getTrangThai()
            });
        }

        int totalPage = (int) Math.ceil(count / limit);
        if (count / limit != 0) {
            pagination2.setPagegination(page, totalPage + 1);
        } else if (count / limit == 0) {
            pagination2.setPagegination(page, totalPage);
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

        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtIDDongGo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtMaDongGo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTenDongGo = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDongGo = new javax.swing.JTable();
        btnTimKiemDongGo = new javax.swing.JButton();
        txtTimKiemDongGo = new javax.swing.JTextField();
        btnThem1 = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblNguonGoc = new javax.swing.JTable();
        btnXoaNG = new javax.swing.JButton();
        btnSuaNG = new javax.swing.JButton();
        btnTimKiemDongGo1 = new javax.swing.JButton();
        btnThemNG = new javax.swing.JButton();
        txtTimKiemNG = new javax.swing.JTextField();
        txtQuocGia = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtMaNguonGoc = new javax.swing.JTextField();
        txtIDNguonGoc = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        TXTIDDVT = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        TXTMADVT = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        TXTDVT = new javax.swing.JTextField();
        btnThem2 = new javax.swing.JButton();
        btnSua2 = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TBDONVITINH = new javax.swing.JTable();
        TXTTIMKIEMDVT = new javax.swing.JTextField();
        btnTimKiem1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtIDLoaiSp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTenDongSp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMaLoaiSp = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLoaiSanPham = new javax.swing.JTable();
        btnThemLoaiSp = new javax.swing.JButton();
        btnSuaLoaiSp = new javax.swing.JButton();
        btnXoaLoaiSp = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        txtTimkiemSanPham = new javax.swing.JTextField();
        btntimkiemSanPham = new javax.swing.JButton();
        pagination1 = new pagination.Pagination();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtma = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtten = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtdiachi = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtdienthoai = new javax.swing.JTextField();
        btntimkiem = new javax.swing.JButton();
        txttimkiem = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbNhaSanXuat = new javax.swing.JTable();
        btnadd = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        pagination2 = new pagination.Pagination();
        jButton5 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 204));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 204, 204));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));
        jPanel6.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("ID");

        txtIDDongGo.setBackground(new java.awt.Color(255, 204, 204));
        txtIDDongGo.setActionCommand("<Not Set>");
        txtIDDongGo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Mã");

        txtMaDongGo.setBackground(new java.awt.Color(255, 204, 204));
        txtMaDongGo.setActionCommand("<Not Set>");
        txtMaDongGo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Tên");

        txtTenDongGo.setBackground(new java.awt.Color(255, 204, 204));
        txtTenDongGo.setActionCommand("<Not Set>");
        txtTenDongGo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        tblDongGo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tên Loại Gỗ"
            }
        ));
        tblDongGo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDongGoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDongGo);

        btnTimKiemDongGo.setBackground(new java.awt.Color(255, 204, 255));
        btnTimKiemDongGo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiemDongGo.setText("Search Name");
        btnTimKiemDongGo.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnTimKiemDongGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemDongGoActionPerformed(evt);
            }
        });

        txtTimKiemDongGo.setBackground(new java.awt.Color(255, 204, 204));
        txtTimKiemDongGo.setActionCommand("<Not Set>");
        txtTimKiemDongGo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiemDongGo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemDongGoKeyReleased(evt);
            }
        });

        btnThem1.setBackground(new java.awt.Color(255, 204, 255));
        btnThem1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem1.setText("Add");
        btnThem1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnSua1.setBackground(new java.awt.Color(255, 204, 255));
        btnSua1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua1.setText("Update");
        btnSua1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        btnXoa1.setBackground(new java.awt.Color(255, 204, 255));
        btnXoa1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa1.setText("Delete");
        btnXoa1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaDongGo, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIDDongGo))
                                .addGap(33, 33, 33)
                                .addComponent(txtTimKiemDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnTimKiemDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMaDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTenDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dòng Gỗ", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 204, 204));
        jPanel7.setBorder(new javax.swing.border.MatteBorder(null));

        tblNguonGoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblNguonGoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tên Quốc Gia"
            }
        ));
        tblNguonGoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguonGocMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblNguonGoc);

        btnXoaNG.setBackground(new java.awt.Color(255, 204, 255));
        btnXoaNG.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaNG.setText("Delete");
        btnXoaNG.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnXoaNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNGActionPerformed(evt);
            }
        });

        btnSuaNG.setBackground(new java.awt.Color(255, 204, 255));
        btnSuaNG.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaNG.setText("Update");
        btnSuaNG.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnSuaNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNGActionPerformed(evt);
            }
        });

        btnTimKiemDongGo1.setBackground(new java.awt.Color(255, 204, 255));
        btnTimKiemDongGo1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiemDongGo1.setText("Search Name");
        btnTimKiemDongGo1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnTimKiemDongGo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemDongGo1ActionPerformed(evt);
            }
        });

        btnThemNG.setBackground(new java.awt.Color(255, 204, 255));
        btnThemNG.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemNG.setText("Add");
        btnThemNG.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnThemNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNGActionPerformed(evt);
            }
        });

        txtTimKiemNG.setBackground(new java.awt.Color(255, 204, 204));
        txtTimKiemNG.setActionCommand("<Not Set>");
        txtTimKiemNG.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiemNG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemNGKeyReleased(evt);
            }
        });

        txtQuocGia.setBackground(new java.awt.Color(255, 204, 204));
        txtQuocGia.setActionCommand("<Not Set>");
        txtQuocGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtQuocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuocGiaActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Quốc Gia");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Mã");

        txtMaNguonGoc.setBackground(new java.awt.Color(255, 204, 204));
        txtMaNguonGoc.setActionCommand("<Not Set>");
        txtMaNguonGoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtMaNguonGoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNguonGocActionPerformed(evt);
            }
        });

        txtIDNguonGoc.setBackground(new java.awt.Color(255, 204, 204));
        txtIDNguonGoc.setActionCommand("<Not Set>");
        txtIDNguonGoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("ID");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(btnThemNG, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(btnXoaNG, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addComponent(btnSuaNG, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaNguonGoc, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIDNguonGoc)
                                    .addComponent(txtQuocGia))
                                .addGap(52, 52, 52)
                                .addComponent(txtTimKiemNG, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiemDongGo1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDNguonGoc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiemNG, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiemDongGo1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaNguonGoc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemNG, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaNG, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaNG, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(240, 240, 240))))
        );

        jTabbedPane1.addTab("Nguồn Gốc", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 204, 204));
        jPanel8.setBorder(new javax.swing.border.MatteBorder(null));

        jPanel12.setBackground(new java.awt.Color(255, 204, 204));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("ID");

        TXTIDDVT.setBackground(new java.awt.Color(255, 204, 204));
        TXTIDDVT.setActionCommand("<Not Set>");
        TXTIDDVT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Mã");

        TXTMADVT.setBackground(new java.awt.Color(255, 204, 204));
        TXTMADVT.setActionCommand("<Not Set>");
        TXTMADVT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Đơn Vị Tính");

        TXTDVT.setBackground(new java.awt.Color(255, 204, 204));
        TXTDVT.setActionCommand("<Not Set>");
        TXTDVT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        btnThem2.setBackground(new java.awt.Color(255, 204, 255));
        btnThem2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem2.setText("Add");
        btnThem2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        btnSua2.setBackground(new java.awt.Color(255, 204, 255));
        btnSua2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua2.setText("Update");
        btnSua2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnSua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua2ActionPerformed(evt);
            }
        });

        btnXoa2.setBackground(new java.awt.Color(255, 204, 255));
        btnXoa2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa2.setText("Delete");
        btnXoa2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });

        TBDONVITINH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tên"
            }
        ));
        TBDONVITINH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBDONVITINHMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TBDONVITINH);

        TXTTIMKIEMDVT.setBackground(new java.awt.Color(255, 204, 204));
        TXTTIMKIEMDVT.setActionCommand("<Not Set>");
        TXTTIMKIEMDVT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        TXTTIMKIEMDVT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTTIMKIEMDVTKeyReleased(evt);
            }
        });

        btnTimKiem1.setBackground(new java.awt.Color(255, 204, 255));
        btnTimKiem1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiem1.setText("Search Name");
        btnTimKiem1.setBorder(new javax.swing.border.MatteBorder(null));
        btnTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXTMADVT, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnThem2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(btnSua2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(btnXoa2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(TXTIDDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(TXTTIMKIEMDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TXTTIMKIEMDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TXTIDDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(TXTMADVT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(TXTDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 671, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Đơn Vị Tính", jPanel8);

        jPanel10.setBackground(new java.awt.Color(255, 204, 204));
        jPanel10.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("ID");

        txtIDLoaiSp.setBackground(new java.awt.Color(255, 204, 204));
        txtIDLoaiSp.setActionCommand("<Not Set>");
        txtIDLoaiSp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Tên Dòng SP");

        txtTenDongSp.setBackground(new java.awt.Color(255, 204, 204));
        txtTenDongSp.setActionCommand("<Not Set>");
        txtTenDongSp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtTenDongSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDongSpActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Mã");

        txtMaLoaiSp.setBackground(new java.awt.Color(255, 204, 204));
        txtMaLoaiSp.setActionCommand("<Not Set>");
        txtMaLoaiSp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtMaLoaiSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaLoaiSpActionPerformed(evt);
            }
        });

        tblLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblLoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tên Dòng SP"
            }
        ));
        tblLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLoaiSanPham);

        btnThemLoaiSp.setBackground(new java.awt.Color(255, 204, 255));
        btnThemLoaiSp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemLoaiSp.setText("Add");
        btnThemLoaiSp.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnThemLoaiSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiSpActionPerformed(evt);
            }
        });

        btnSuaLoaiSp.setBackground(new java.awt.Color(255, 204, 255));
        btnSuaLoaiSp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaLoaiSp.setText("Update");
        btnSuaLoaiSp.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnSuaLoaiSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLoaiSpActionPerformed(evt);
            }
        });

        btnXoaLoaiSp.setBackground(new java.awt.Color(255, 204, 255));
        btnXoaLoaiSp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaLoaiSp.setText("Delete");
        btnXoaLoaiSp.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnXoaLoaiSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLoaiSpActionPerformed(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(255, 204, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiem.setText("Tìm");
        btnTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        txtTimKiem.setBackground(new java.awt.Color(255, 204, 204));
        txtTimKiem.setActionCommand("<Not Set>");
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtIDLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDongSp, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnThemLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtMaLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenDongSp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(68, 68, 68))
        );

        jTabbedPane1.addTab("Loại SP", jPanel10);

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));
        jPanel4.setBorder(new javax.swing.border.MatteBorder(null));

        btnThem.setBackground(new java.awt.Color(255, 204, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setText("Add");
        btnThem.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tên"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("ID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tên");

        txtID.setBackground(new java.awt.Color(255, 204, 204));
        txtID.setActionCommand("<Not Set>");
        txtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        txtMa.setBackground(new java.awt.Color(255, 204, 204));
        txtMa.setActionCommand("<Not Set>");
        txtMa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        txtTen.setBackground(new java.awt.Color(255, 204, 204));
        txtTen.setActionCommand("<Not Set>");
        txtTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        btnSua.setBackground(new java.awt.Color(255, 204, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setText("Update");
        btnSua.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 204, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setText("Delete");
        btnXoa.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        txtTimkiemSanPham.setBackground(new java.awt.Color(255, 204, 204));
        txtTimkiemSanPham.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtTimkiemSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimkiemSanPhamKeyReleased(evt);
            }
        });

        btntimkiemSanPham.setBackground(new java.awt.Color(255, 153, 255));
        btntimkiemSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btntimkiemSanPham.setText("Tìm kiếm");
        btntimkiemSanPham.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btntimkiemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemSanPhamActionPerformed(evt);
            }
        });

        pagination1.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 89, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTimkiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btntimkiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btntimkiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTimkiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 204, 204));
        jPanel5.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Id");

        txtid.setBackground(new java.awt.Color(255, 204, 204));
        txtid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Mã");

        txtma.setBackground(new java.awt.Color(255, 204, 204));
        txtma.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Tên NCC");

        txtten.setBackground(new java.awt.Color(255, 204, 204));
        txtten.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Địa chỉ");

        txtdiachi.setBackground(new java.awt.Color(255, 204, 204));
        txtdiachi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Điện Thoại");

        txtdienthoai.setBackground(new java.awt.Color(255, 204, 204));
        txtdienthoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        btntimkiem.setBackground(new java.awt.Color(255, 153, 255));
        btntimkiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btntimkiem.setText("Tìm kiếm");
        btntimkiem.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btntimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemActionPerformed(evt);
            }
        });

        txttimkiem.setBackground(new java.awt.Color(255, 204, 204));
        txttimkiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        tbNhaSanXuat.setModel(new javax.swing.table.DefaultTableModel(
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
        tbNhaSanXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhaSanXuatMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbNhaSanXuat);

        btnadd.setBackground(new java.awt.Color(255, 153, 255));
        btnadd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnadd.setText("Add");
        btnadd.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnupdate.setBackground(new java.awt.Color(255, 153, 255));
        btnupdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnupdate.setText("Update");
        btnupdate.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelete.setBackground(new java.awt.Color(255, 153, 255));
        btndelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btndelete.setText("Delete");
        btndelete.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btndelete.setMaximumSize(new java.awt.Dimension(25, 18));
        btndelete.setMinimumSize(new java.awt.Dimension(25, 18));
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        pagination2.setOpaque(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel14)
                                    .addComponent(txtdienthoai)
                                    .addComponent(txtdiachi)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btntimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(205, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(pagination2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jLabel12)
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pagination2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhà Sản Xuất", jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jButton5.setBackground(new java.awt.Color(255, 102, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("BACK");
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        int index = tblSanPham.getSelectedRow();

        txtID.setText(tblSanPham.getValueAt(index, 0).toString());
        txtMa.setText(tblSanPham.getValueAt(index, 1).toString());
        txtTen.setText(tblSanPham.getValueAt(index, 2).toString());
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

        SanPham sp = new SanPham();
        sp.setTen(txtTen.getText());

        if (checkTen()) {
            boolean b = spSV.add(sp);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTB();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Trùng Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        SanPham sp = new SanPham();
        sp.setId(txtID.getText());
        sp.setTen(txtTen.getText());

        if (checkTen()) {
            boolean b = spSV.update(sp);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Update sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTB();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Update Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        SanPham sp = new SanPham();
        sp.setId(txtID.getText());
        boolean b = spSV.delete(sp);
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Delete sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTB();

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Delete Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }


    }//GEN-LAST:event_btnXoaActionPerformed

    public void loadTbLoaiSP() {   // table loai Sản Phẩm
        model = (DefaultTableModel) tblLoaiSanPham.getModel();
        model.setRowCount(0);
        List<ViewModelLoaiSanPham> lsp = lSpSv.getListLoaiSP();
        for (ViewModelLoaiSanPham v : lsp) {
            model.addRow(new Object[]{
                v.getId(), "Mã " + v.getMa(), v.getTenDongSP()
            });
        }
    }

    public void getNameLoaiGo(String ten) {
        model = (DefaultTableModel) tblLoaiSanPham.getModel();
        model.setRowCount(0);
        List<ViewModelLoaiSanPham> dg = lSpSv.getListLoaiSPByName(ten);
        for (ViewModelLoaiSanPham v : dg) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTenDongSP()
            });
        }
    }

    public boolean checkTenLoaiSp() { //check ten sp
        String ten = txtTenDongSp.getText();
        List<ViewModelLoaiSanPham> sp = lSpSv.getListLoaiSP();
        for (ViewModelLoaiSanPham v : sp) {
            if (ten.isBlank()) {
                JOptionPane.showMessageDialog(this, "Tên loại sp trống!");
                return false;
            }
        }
        return true;
    }

    public void loadSp1() {
        List<ViewModelLoaiSanPham> sp = lSpSv.getListLoaiSP();
        if (sp.isEmpty()) {
            return;
        }
        int index = 0;
        txtIDLoaiSp.setText(tblLoaiSanPham.getValueAt(index, 0).toString());
        txtMaLoaiSp.setText(tblLoaiSanPham.getValueAt(index, 1).toString());
        txtTenDongSp.setText(tblLoaiSanPham.getValueAt(index, 2).toString());
    }

    private void tblLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiSanPhamMouseClicked
        // TODO add your handling code here:
        int index = tblLoaiSanPham.getSelectedRow();
        txtIDLoaiSp.setText(tblLoaiSanPham.getValueAt(index, 0).toString());
        txtMaLoaiSp.setText(tblLoaiSanPham.getValueAt(index, 1).toString());
        txtTenDongSp.setText(tblLoaiSanPham.getValueAt(index, 2).toString());
    }//GEN-LAST:event_tblLoaiSanPhamMouseClicked

    private void txtMaLoaiSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaLoaiSpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaLoaiSpActionPerformed

    private void txtTenDongSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDongSpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDongSpActionPerformed

    private void btnThemLoaiSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiSpActionPerformed
        // TODO add your handling code here:
        LoaiSP sp = new LoaiSP();
        sp.setTenDongSP(txtTenDongSp.getText());

        if (checkTenLoaiSp() == true) {
            boolean b = lSpSv.add(sp);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm tên loại thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTbLoaiSP();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "add ko thành công!", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }

    }//GEN-LAST:event_btnThemLoaiSpActionPerformed

    private void btnSuaLoaiSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLoaiSpActionPerformed
        // TODO add your handling code here:
        LoaiSP sp = new LoaiSP();
        sp.setId(txtIDLoaiSp.getText());
        sp.setTenDongSP(txtTenDongSp.getText());
        if (checkTenLoaiSp() == true) {
            if (checkTenLoaiSp()) {
                boolean b = lSpSv.update(sp);
                if (b == true) {
                    Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                    JOptionPane.showMessageDialog(this, "Update tên dòng Sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                    loadTbLoaiSP();

                } else {
                    Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                    JOptionPane.showMessageDialog(this, "Update Tên dòng Sản Phẩm thất bại", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                }
            }
        }
    }//GEN-LAST:event_btnSuaLoaiSpActionPerformed

    private void btnXoaLoaiSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLoaiSpActionPerformed
        // TODO add your handling code here:
        int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dòng sản phẩm không??");
        if (xacNhan == JOptionPane.YES_NO_OPTION) {
            LoaiSP sp = new LoaiSP();
            sp.setId(txtIDLoaiSp.getText());
            boolean b = lSpSv.delete(sp);

            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Delete dòng sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTbLoaiSP();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Delete Tên dòng Sản Phẩm Thất bại", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        } else {
            return;
        }

    }//GEN-LAST:event_btnXoaLoaiSpActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        getNameLoaiGo(txtTimKiem.getText());

    }//GEN-LAST:event_btnTimKiemActionPerformed

    public void loadTBDongGo() {
        model = (DefaultTableModel) tblDongGo.getModel();
        model.setRowCount(0);
        List<ViewModelDongGo> dg = dgSV.getListDongGo();
        for (ViewModelDongGo v : dg) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTenLoaiGo()
            });
        }
    }

    public void getNameDongGo(String ten) {
        model = (DefaultTableModel) tblDongGo.getModel();
        model.setRowCount(0);
        List<ViewModelDongGo> dg = dgSV.getDongGoByName(ten);
        for (ViewModelDongGo v : dg) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTenLoaiGo()
            });
        }
    }

    public void load1stDongGo() {
        List<ViewModelDongGo> dg = dgSV.getListDongGo();
        if (dg.isEmpty()) {
            return;
        }
        int index = 0;

        txtIDDongGo.setText(tblDongGo.getValueAt(index, 0).toString());
        txtMaDongGo.setText(tblDongGo.getValueAt(index, 1).toString());
        txtTenDongGo.setText(tblDongGo.getValueAt(index, 2).toString());
    }
    private void tblDongGoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDongGoMouseClicked
        // TODO add your handling code here:
        int index = tblDongGo.getSelectedRow();

        txtIDDongGo.setText(tblDongGo.getValueAt(index, 0).toString());
        txtMaDongGo.setText(tblDongGo.getValueAt(index, 1).toString());
        txtTenDongGo.setText(tblDongGo.getValueAt(index, 2).toString());
    }//GEN-LAST:event_tblDongGoMouseClicked

    private void btnTimKiemDongGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemDongGoActionPerformed
        // TODO add your handling code here:
        //Search Dòng Gỗ
        getNameDongGo(txtTimKiemDongGo.getText());
    }//GEN-LAST:event_btnTimKiemDongGoActionPerformed

    private void txtTimKiemDongGoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemDongGoKeyReleased
        // TODO add your handling code here:
        if (txtTimKiemDongGo.getText().equals("")) {
            loadTBDongGo();
        }
    }//GEN-LAST:event_txtTimKiemDongGoKeyReleased

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:

        //Add Loại gỗ
        DongGo dongGo = new DongGo();
        dongGo.setTenLoaiGo(txtTenDongGo.getText());

        if (checkTenDongGo()) {
            boolean c = dgSV.add(dongGo);
            if (c == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm loại  thành công", "Loại Gỗ", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTBDongGo();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Lỗi!", "Loại Gỗ", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:

        //update loại gỗ
        DongGo dongGo = new DongGo();
        dongGo.setId(txtIDDongGo.getText());
        dongGo.setTenLoaiGo(txtTenDongGo.getText());
        if (checkTenDongGo()) {
            boolean c = dgSV.update(dongGo);
            if (c == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Update loại  thành công", "Loại Gỗ", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTBDongGo();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Lỗi!", "Loại Gỗ", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        // TODO add your handling code here:
        //Delete Dòng Gỗ
        DongGo dongGo = new DongGo();
        dongGo.setId(txtIDDongGo.getText());

        boolean c = dgSV.delete(dongGo);
        if (c == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Xóa loại  thành công", "Loại Gỗ", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTBDongGo();

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Lỗi!", "Loại Gỗ", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnXoa1ActionPerformed

    public void loadTbaleNhaCungCap() {
        List<ViewModelNhaCungCap> v = nhaCungCapService.getAll();
        model = (DefaultTableModel) tbNhaSanXuat.getModel();
        model.setColumnCount(0);
        model.addColumn("Id");
        model.addColumn("Ma");
        model.addColumn("Ten");
        model.addColumn("Dia chi");
        model.addColumn("SDT");

        model.setRowCount(0);
        for (ViewModelNhaCungCap viewModelNhaCungCap : v) {
            model.addRow(new Object[]{
                viewModelNhaCungCap.getId(), viewModelNhaCungCap.getMa(),
                viewModelNhaCungCap.getTenNCC(),
                viewModelNhaCungCap.getDiaChi(), viewModelNhaCungCap.getSdt()
            });

        }
    }

    public boolean checkTenNCC() { //check ten sp
        String ten = txtten.getText();
        List<ViewModelNhaCungCap> ncc = nhaCungCapService.getAll();
        for (ViewModelNhaCungCap v : ncc) {
            if (ten.isBlank()) {
                
                JOptionPane.showMessageDialog(this, "Tên NCC trống!");
                return false;
            }
        }
        return true;
    }

    public void load1stNhaCungCap() 
    {
        List<ViewModelNhaCungCap> v = nhaCungCapService.getAll();
        int index = 0;

        txtid.setText(tbNhaSanXuat.getValueAt(index, 0).toString());
        txtma.setText(tbNhaSanXuat.getValueAt(index, 1).toString());
        txtten.setText(tbNhaSanXuat.getValueAt(index, 2).toString());
        txtdiachi.setText(tbNhaSanXuat.getValueAt(index, 3).toString());
        txtdienthoai.setText(tbNhaSanXuat.getValueAt(index, 4).toString());

    }

    public void loadTbaleTimKiemTenNCC(String ten) {
        
        List<ViewModelNhaCungCap> v = nhaCungCapService.getNhaCungCap(ten);
        model = (DefaultTableModel) tbNhaSanXuat.getModel();
        model.setColumnCount(0);
        model.addColumn("Id");
        model.addColumn("Ma");
        model.addColumn("Ten");
        model.addColumn("Dia chi");
        model.addColumn("SDT");

        model.setRowCount(0);
        for (ViewModelNhaCungCap viewModelNhaCungCap : v) {
            model.addRow(new Object[]{
                viewModelNhaCungCap.getId(), viewModelNhaCungCap.getMa(),
                viewModelNhaCungCap.getTenNCC(),
                viewModelNhaCungCap.getDiaChi(), viewModelNhaCungCap.getSdt()
            });

        }
    }

    public void load1stNCC() {
        
        int index = 0;

        txtid.setText(tbNhaSanXuat.getValueAt(index, 0).toString());
        txtma.setText(tbNhaSanXuat.getValueAt(index, 1).toString());
        txtten.setText(tbNhaSanXuat.getValueAt(index, 2).toString());
        txtdiachi.setText(tbNhaSanXuat.getValueAt(index, 3).toString());
        txtdienthoai.setText(tbNhaSanXuat.getValueAt(index, 4).toString());

    }
    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
        // TODO add your handling code here:
        
        
        NhaCungCap sp = new NhaCungCap();

        // nhaCungCapService.getNhaCungCap(txttimkiem.getText());
        loadTbaleTimKiemTenNCC(txttimkiem.getText());
    }//GEN-LAST:event_btntimkiemActionPerformed

    private void tbNhaSanXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhaSanXuatMouseClicked
        // TODO add your handling code here:
        int index = tbNhaSanXuat.getSelectedRow();

        txtid.setText(tbNhaSanXuat.getValueAt(index, 0).toString());
        txtma.setText(tbNhaSanXuat.getValueAt(index, 1).toString());
        txtten.setText(tbNhaSanXuat.getValueAt(index, 2).toString());
        txtdiachi.setText(tbNhaSanXuat.getValueAt(index, 3).toString());
        txtdienthoai.setText(tbNhaSanXuat.getValueAt(index, 4).toString());
    }//GEN-LAST:event_tbNhaSanXuatMouseClicked

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
        NhaCungCap nc = new NhaCungCap();

        nc.setTenNCC(txtten.getText());
        nc.setDiaChi(txtdiachi.getText());
        nc.setSdt(txtdienthoai.getText());

        if (checkTenNCC()) {
            
            boolean b = nhaCungCapService.add(nc);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTbaleNhaCungCap();

            } else {
                JOptionPane.showMessageDialog(this, "Thêm Không thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:

        NhaCungCap nc = new NhaCungCap();
        nc.setId(txtid.getText());
        nc.setTenNCC(txtten.getText());
        nc.setDiaChi(txtdiachi.getText());
        nc.setSdt(txtdienthoai.getText());
        if (checkTenNCC()) {
            boolean b = nhaCungCapService.update(nc);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Sửa sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTbaleNhaCungCap();

            } else {
                JOptionPane.showMessageDialog(this, "Trùng Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        NhaCungCap sp = new NhaCungCap();
        sp.setId(txtid.getText());
        boolean b = nhaCungCapService.delete(sp);

        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Delete sp thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTbaleNhaCungCap();

        } else {
            JOptionPane.showMessageDialog(this, "Delete Tên Sản Phẩm", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        // TODO add your handling code here:
        DonViTinh sp = new DonViTinh();
        sp.setDonViTinh(TXTDVT.getText());
        if (checkDVT()) {
            boolean b = dvtrp.add(sp);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm dvt thành công", "DON VI TINH", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTBDVT();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, " thêm loi roi", "DON VI TINH", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }

    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnSua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua2ActionPerformed
        // TODO add your handling code here:
        DonViTinh sp = new DonViTinh();
        sp.setId(TXTIDDVT.getText());
        sp.setDonViTinh(TXTDVT.getText());
        if (checkDVT()) {
            boolean b = dvtrp.update(sp);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Update dvt thanh cong", "DON VI TINH", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTBDVT();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Update dvt that bai", "DON VI TINH", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }//GEN-LAST:event_btnSua2ActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        // TODO add your handling code here:
        DonViTinh sp = new DonViTinh();
        sp.setId(TXTIDDVT.getText());
        boolean b = dvtrp.delete(sp);
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Delete DVT THANH CONG", "DON VI TINH", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTBDVT();

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Delete DVT THAT BAI", "DON VI TINH", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void TBDONVITINHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBDONVITINHMouseClicked
        // TODO add your handling code here:
        int index = TBDONVITINH.getSelectedRow();

        TXTIDDVT.setText(TBDONVITINH.getValueAt(index, 0).toString());
        TXTMADVT.setText(TBDONVITINH.getValueAt(index, 1).toString());
        TXTDVT.setText(TBDONVITINH.getValueAt(index, 2).toString());
    }//GEN-LAST:event_TBDONVITINHMouseClicked

    private void TXTTIMKIEMDVTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTTIMKIEMDVTKeyReleased
        if (TXTTIMKIEMDVT.getText().equals("")) {
            loadTBDVT();
        }
    }//GEN-LAST:event_TXTTIMKIEMDVTKeyReleased

    private void btnTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem1ActionPerformed
        // TODO add your handling code here:
        getNameDONVITINH(TXTTIMKIEMDVT.getText());
    }//GEN-LAST:event_btnTimKiem1ActionPerformed

    private void tblNguonGocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguonGocMouseClicked
        // TODO add your handling code here:
        int index = tblNguonGoc.getSelectedRow();

        txtIDNguonGoc.setText(tblNguonGoc.getValueAt(index, 0).toString());
        txtMaNguonGoc.setText(tblNguonGoc.getValueAt(index, 1).toString());
        txtQuocGia.setText(tblNguonGoc.getValueAt(index, 2).toString());
    }//GEN-LAST:event_tblNguonGocMouseClicked

    private void btnXoaNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNGActionPerformed
        // TODO add your handling code here:
        //Delete Dòng Gỗ
        NguonGoc ng = new NguonGoc();
        ng.setId(txtIDNguonGoc.getText());

        boolean c = ngSv.delete(ng);
        if (c == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Xóa   thành công", "Loại Gỗ", JOptionPane.INFORMATION_MESSAGE, icon);
            loadTBNguonGoc();

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Lỗi!", "Loại Gỗ", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnXoaNGActionPerformed

    private void btnSuaNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNGActionPerformed
        // TODO add your handling code here:

        //update loại gỗ
        NguonGoc ng = new NguonGoc();
        ng.setId(txtIDNguonGoc.getText());
        ng.setQuocGia(txtQuocGia.getText());
        if (checkTenNguocGoc()) {
            boolean c = ngSv.update(ng);
            if (c == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Update   thành công", "Loại Gỗ", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTBNguonGoc();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Lỗi!", "Loại Gỗ", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }//GEN-LAST:event_btnSuaNGActionPerformed

    private void btnTimKiemDongGo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemDongGo1ActionPerformed
        // TODO add your handling code here:
        //Search Dòng Gỗ
        getQuocGia(txtTimKiemNG.getText());
    }//GEN-LAST:event_btnTimKiemDongGo1ActionPerformed

    private void btnThemNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNGActionPerformed
        // TODO add your handling code here:

        //Add Loại gỗ
        NguonGoc nguonGoc = new NguonGoc();
        nguonGoc.setQuocGia(txtQuocGia.getText());

        if (checkTenNguocGoc()) {
            boolean c = ngSv.add(nguonGoc);
            if (c == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm loại  thành công", "Loại Gỗ", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTBNguonGoc();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Lỗi!", "Loại Gỗ", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }//GEN-LAST:event_btnThemNGActionPerformed

    private void txtTimKiemNGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemNGKeyReleased
        // TODO add your handling code here:
        if (txtTimKiemNG.getText().equals("")) {
            loadTBDongGo();
        }
    }//GEN-LAST:event_txtTimKiemNGKeyReleased

    private void txtQuocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuocGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuocGiaActionPerformed

    private void txtMaNguonGocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNguonGocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNguonGocActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        MenuView v = new MenuView(IdNV, TenNV, CV);
        v.setLocationRelativeTo(null);
        v.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btntimkiemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemSanPhamActionPerformed
        // TODO add your handling code here:
        timKiemSP(txtTimkiemSanPham.getText());

    }//GEN-LAST:event_btntimkiemSanPhamActionPerformed

    private void txtTimkiemSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemSanPhamKeyReleased
        // TODO add your handling code here:
        if (txtTimkiemSanPham.getText().equals("")) {
            loadTB();
        }
    }//GEN-LAST:event_txtTimkiemSanPhamKeyReleased

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
            java.util.logging.Logger.getLogger(SanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new SanPhamView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TBDONVITINH;
    private javax.swing.JTextField TXTDVT;
    private javax.swing.JTextField TXTIDDVT;
    private javax.swing.JTextField TXTMADVT;
    private javax.swing.JTextField TXTTIMKIEMDVT;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnSua2;
    private javax.swing.JButton btnSuaLoaiSp;
    private javax.swing.JButton btnSuaNG;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnThemLoaiSp;
    private javax.swing.JButton btnThemNG;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiem1;
    private javax.swing.JButton btnTimKiemDongGo;
    private javax.swing.JButton btnTimKiemDongGo1;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JButton btnXoa2;
    private javax.swing.JButton btnXoaLoaiSp;
    private javax.swing.JButton btnXoaNG;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btntimkiemSanPham;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private pagination.Pagination pagination1;
    private pagination.Pagination pagination2;
    private javax.swing.JTable tbNhaSanXuat;
    private javax.swing.JTable tblDongGo;
    private javax.swing.JTable tblLoaiSanPham;
    private javax.swing.JTable tblNguonGoc;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDDongGo;
    private javax.swing.JTextField txtIDLoaiSp;
    private javax.swing.JTextField txtIDNguonGoc;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaDongGo;
    private javax.swing.JTextField txtMaLoaiSp;
    private javax.swing.JTextField txtMaNguonGoc;
    private javax.swing.JTextField txtQuocGia;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDongGo;
    private javax.swing.JTextField txtTenDongSp;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemDongGo;
    private javax.swing.JTextField txtTimKiemNG;
    private javax.swing.JTextField txtTimkiemSanPham;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtdienthoai;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtten;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
