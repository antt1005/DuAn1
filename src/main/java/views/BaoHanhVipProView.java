/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainModels.ChiTietDoGo;
import domainModels.HoaDon;
import domainModels.HoaDonChiTiet;
import domainModels.KhachHang;
import domainModels.NhanVien;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repositories.BaoHanhVipProRepository;
import viewModel.ViewModelHoaDonChiTietBanHang;

/**
 *
 * @author Admin
 */
public class BaoHanhVipProView extends javax.swing.JFrame {

    /**
     * Creates new form BaoHanhVipProView
     */
    private BaoHanhVipProRepository vip = new BaoHanhVipProRepository();

    DefaultTableModel model = new DefaultTableModel();

    String IdNV;

    String TenNV;

    String CV;

    public BaoHanhVipProView(String Id, String Ten, String cv) {
        initComponents();
        this.setLocationRelativeTo(null);
        IdNV = Id;

        TenNV = Ten;

        CV = cv;
        rdKH.setSelected(true);
        loadHDBH();
        loadst1();
    }

    public void loadst1() {
        int index = tblBaoHanh.getRowCount();

        if (index == 0) {
            model = (DefaultTableModel) tblBaoHanh.getModel();
            model.setRowCount(0);
        } else {
            loadCTHDBaoHanh(tblBaoHanh.getValueAt(0, 0).toString());
        }
    }

    public void loadByIdHoaDon(String idhd) {
        model = (DefaultTableModel) tblTimKiem.getModel();

        List<HoaDon> lisst = vip.getListHoaDonById(idhd);

        model.setRowCount(0);

        for (HoaDon a : lisst) {
            model.addRow(new Object[]{
                a.getId(), a.getMa(),
                a.getNgayThanhToan(),
                a.getIdKhachHang().getTenKhachHang(),
                a.getIdNhanVien().getHoTen(),
                a.getThanhTien(),
                a.getTrangThai() == 2 ? "HD" : "Bảo Hành"
            }
            );
        }
    }

    public void loadBysdtHoaDon(String sdt) {
        model = (DefaultTableModel) tblTimKiem.getModel();

        List<HoaDon> lisst = vip.getListHoaDonBySDT(sdt);

        model.setRowCount(0);

        for (HoaDon a : lisst) {
            model.addRow(new Object[]{
                a.getId(), a.getMa(),
                a.getNgayThanhToan(),
                a.getIdKhachHang().getTenKhachHang(),
                a.getIdNhanVien().getHoTen(),
                a.getThanhTien(),
                a.getTrangThai() == 2 ? "HD" : "Bảo Hành"
            }
            );
        }
    }

    public void loadCTHD(String idhd) {
        model = (DefaultTableModel) tblHoaDonChiTiet.getModel();

        model.setRowCount(0);

        for (HoaDonChiTiet a : vip.getListHDCT(idhd)) {
            model.addRow(new Object[]{
                a.getIdHoaDon().getId(),
                a.getIdChiTietDoGo().getId(),
                a.getIdChiTietDoGo().getTenSP(),
                a.getSoLuong(),
                a.getIdChiTietDoGo().getGiaBan(),
                a.getDonGia(),
                a.getLuot()
            });
        }
    }

    public void loadCTHDBaoHanh(String idhd) {
        model = (DefaultTableModel) tblBaoHanhChiTiet.getModel();

        model.setRowCount(0);

        for (HoaDonChiTiet a : vip.getListHDCT(idhd)) {
            model.addRow(new Object[]{
                a.getIdHoaDon().getId(),
                a.getIdChiTietDoGo().getId(),
                a.getIdChiTietDoGo().getTenSP(),
                a.getSoLuong(),
                a.getDonGia()});
        }
    }

    public void loadHDBH() {
        model = (DefaultTableModel) tblBaoHanh.getModel();

        model.setRowCount(0);

        for (HoaDon a : vip.getListHDBH()) {
            model.addRow(new Object[]{
                a.getId(),
                a.getMa(),
                a.getNgayTao(),
                a.getIdNhanVien().getHoTen(),
                a.getIdKhachHang().getTenKhachHang(),
                a.getTrangThai() == 3 ? "Đang Bảo Hành" : "Null"
            });
        }
    }

    public int getSoluong(String idsp) {
        int index = tblBaoHanh.getSelectedRow();

        int i = 0;
        List<HoaDonChiTiet> lisst = vip.getListHDCT(tblBaoHanh.getValueAt(0, 0).toString());
        if (lisst == null) {
            i = 0;
        } else {
            for (HoaDonChiTiet a : lisst) {
                if (idsp.equals(a.getIdChiTietDoGo())) {
                    i = a.getSoLuong();
                }
            }
        }
        return i;
    }

    public int laySoLuongKiemCheck(String idsp, String idhd) {
        int i = 0;
        try {
            for (HoaDonChiTiet a : vip.getListHDCT(idhd)) {
                if (a.getIdChiTietDoGo().getId().equals(idsp)) {
                    i = a.getSoLuong();
                }
            }
            return i;
        } catch (Exception e) {
            return 0;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        Check = new javax.swing.JButton();
        rdKH = new javax.swing.JRadioButton();
        rdoHD = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTimKiem = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBaoHanh = new javax.swing.JTable();
        BaoHanhbtn = new javax.swing.JButton();
        BaoHanh = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBaoHanhChiTiet = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTimKiem.setForeground(new java.awt.Color(204, 204, 204));
        txtTimKiem.setText("  Vui Lòng Nhập ID Hóa Đơn");
        txtTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "Vui Lòng Nhập    ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        Check.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Check.setText("Check");
        Check.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdKH);
        rdKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdKH.setText("Khách Hàng");
        rdKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdKHActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoHD);
        rdoHD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoHD.setText("Hóa Đơn");
        rdoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHDActionPerformed(evt);
            }
        });

        tblTimKiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Ma", "Ngay Thanh Toán", "Tên Khách Hàng", "Tên Nhân Viên", "Thành Tiền", "Trang Thai"
            }
        ));
        tblTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTimKiemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTimKiem);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Hóa Đơn Chi Tiết");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Hóa Đơn Còn Bảo Hành");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "Bảo Hành"));

        tblBaoHanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Ma", "NgayTao", "NhanVien", "KhachHang", "TrangThai"
            }
        ));
        tblBaoHanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBaoHanhMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblBaoHanh);

        BaoHanhbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BaoHanhbtn.setText("Tạo Bảo Hành");
        BaoHanhbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BaoHanhbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BaoHanhbtnActionPerformed(evt);
            }
        });

        BaoHanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BaoHanh.setText("Bảo Hành");
        BaoHanh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BaoHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BaoHanhActionPerformed(evt);
            }
        });

        tblBaoHanhChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id HoaDon", "Id San Pham ", "Tên SP", "SL", "DonGia"
            }
        ));
        tblBaoHanhChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBaoHanhChiTietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBaoHanhChiTiet);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BaoHanhbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(BaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BaoHanhbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id HoaDon", "Id San Pham ", "Tên SP", "SL", "GiaBan", "DonGia", "LuotBaoHanh"
            }
        ));
        tblHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblHoaDonChiTiet);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("7 ngày đổi trả       1 đổi 1 cho mỗi sản phẩm");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)))
                                .addGap(143, 143, 143))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdKH, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Check, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(588, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoHD)
                    .addComponent(Check, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdKH)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(402, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(11, 11, 11)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdKHActionPerformed
        try {
            if (rdKH.isSelected()) {
                txtTimKiem.setText("  Vui Lòng Nhập Số Điện Thoại Khách Hàng");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_rdKHActionPerformed

    private void rdoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHDActionPerformed
        try {
            if (rdoHD.isSelected()) {
                txtTimKiem.setText("   Vui Lòng Nhập ID Hóa Đơn");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_rdoHDActionPerformed

    private void BaoHanhbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BaoHanhbtnActionPerformed
        int index = tblTimKiem.getSelectedRow();

        if (index == -1) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Hóa Đơn Bảo Hành", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);
            return;
        } else {
            String[] buttons = {"Yes", "Cancel"};
            int rc = JOptionPane.showOptionDialog(null, "Bạn Có Muốn Tạo Bảo Hành Cho Hóa Đơn  "
                    + tblTimKiem.getValueAt(index, 1).toString() + "  Không ?", "Confirmation",
                    JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
            if (rc == 0) {
                tblTimKiem.setEnabled(false);
                Check.setEnabled(false);
            } else {
                return;
            }
        }
        List<HoaDon> lisst;
        if (rdoHD.isSelected()) {
            lisst = vip.getListHoaDonById(txtTimKiem.getText());
        } else {
            lisst = vip.getListHoaDonBySDT(txtTimKiem.getText());
        }

        HoaDon gethd = lisst.get(index);
        KhachHang getkh = gethd.getIdKhachHang();

        String idkh = getkh.getId();
        String idnv = IdNV;

        HoaDon hd = new HoaDon();

        KhachHang kh = new KhachHang();
        kh.setId(idkh);

        NhanVien nv = new NhanVien();
        nv.setId(idnv);

        hd.setIdKhachHang(kh);
        hd.setIdNhanVien(nv);
        boolean b = vip.addhoadonBaoHanh(hd);
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Tạo Hóa Đơn Thành Công", "Hóa Đơn", JOptionPane.INFORMATION_MESSAGE, icon);
            loadHDBH();
        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Trùng Mã Hóa Đơn", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);

        }

    }//GEN-LAST:event_BaoHanhbtnActionPerformed

    private void BaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BaoHanhActionPerformed
        int index = tblBaoHanh.getSelectedRow();
        String idhd = tblBaoHanh.getValueAt(index, 0).toString();
        int TongTien = 0;
        for (HoaDonChiTiet a : vip.getListHDCT(idhd)) {
            TongTien = TongTien + a.getDonGia().intValue();
        }

        boolean b = vip.BaoHanhTC(idhd, new BigDecimal(TongTien));
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Tạo Bảo Hành Thành Công", "Hóa Đơn", JOptionPane.INFORMATION_MESSAGE, icon);
            loadHDBH();
            tblTimKiem.setEnabled(true);
            Check.setEnabled(true);
            model = (DefaultTableModel) tblBaoHanhChiTiet.getModel();
            model.setRowCount(0);
            model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
            model.setRowCount(0);
            CreateBill a = new CreateBill(idhd);
            a.setLocationRelativeTo(null);
            a.setVisible(true);
        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Trùng Mã Hóa Đơn", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);

        }

    }//GEN-LAST:event_BaoHanhActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        try {
            txtTimKiem.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void CheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckActionPerformed
        try {
            if (rdoHD.isSelected()) {
                loadByIdHoaDon(txtTimKiem.getText());
            } else {
                loadBysdtHoaDon(txtTimKiem.getText());
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_CheckActionPerformed

    private void tblTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimKiemMouseClicked
        try {
            int index = tblTimKiem.getSelectedRow();

            loadCTHD(tblTimKiem.getValueAt(index, 0).toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblTimKiemMouseClicked

    private void tblBaoHanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBaoHanhMouseClicked
        try {
            int index = tblBaoHanh.getSelectedRow();
            loadCTHDBaoHanh(tblBaoHanh.getValueAt(index, 0).toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblBaoHanhMouseClicked

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        int index = tblHoaDonChiTiet.getSelectedRow();

        if (index == -1) {
            return;
        }
        String soLuongNhapinpit = JOptionPane.showInputDialog("Nhập Số Lượng bảo hành Sản Phẩm" + " "
                + tblHoaDonChiTiet.getValueAt(index, 2));
        // NHẬP SỐ LƯỢNG ĐỂ INSERT 
        if (soLuongNhapinpit == null) {
            JOptionPane.showMessageDialog(this, "oke");
            return;
        }

        int indexbh = tblBaoHanh.getSelectedRow();
        if (indexbh == -1) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Hóa Đơn Bảo Hành", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);
            return;
        }
        int luot = Integer.parseInt(tblHoaDonChiTiet.getValueAt(index, 6).toString());
        int sl = Integer.parseInt(tblHoaDonChiTiet.getValueAt(index, 3).toString());
        if (luot == sl) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Sản Phẩm Đã Hết Lượt Bảo Hành", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);
            return;
        }
        if (Integer.parseInt(soLuongNhapinpit) > sl) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Số Lượng Nhập Lớn Hơn Số Lượng Mua", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);
            return;
        }
        if (luot + Integer.parseInt(soLuongNhapinpit) > sl) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Số Lượng Nhập Lớn Hơn Số Lượng Mua", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);
            return;
        }
        String dongiahd = tblHoaDonChiTiet.getValueAt(index, 4).toString();
        int giaban = Integer.parseInt(tblHoaDonChiTiet.getValueAt(index, 4).toString());
        String idhdThuong = tblHoaDonChiTiet.getValueAt(index, 0).toString();
        String idhdbh = tblBaoHanh.getValueAt(indexbh, 0).toString();
        String idsp = tblHoaDonChiTiet.getValueAt(index, 1).toString();
        int soluongnhap = Integer.parseInt(soLuongNhapinpit);
        int dongia = giaban * soluongnhap;

        HoaDon hd = new HoaDon();
        hd.setId(idhdbh);
        ChiTietDoGo sp = new ChiTietDoGo();
        sp.setId(idsp);

        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setIdChiTietDoGo(sp);
        hdct.setIdHoaDon(hd);
        hdct.setSoLuong(soluongnhap);
        hdct.setDonGia(new BigDecimal(dongia));

        int soluongtest = laySoLuongKiemCheck(idsp, idhdbh);
        if (soluongtest == 0) {
            boolean b = vip.addcthoadon(hdct);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm Sản Phẩm Bảo Hành Thành Công", "Hóa Đơn", JOptionPane.INFORMATION_MESSAGE, icon);
                vip.updateLuotBaoHanh(idsp, idhdThuong, soluongnhap + luot, sl, dongiahd);
                loadCTHDBaoHanh(idhdbh);
                loadCTHD(idhdThuong);
            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Lỗi", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);

            }
        } else {
            boolean b = vip.updateSPTheoId(idsp, idhdbh, soluongtest + soluongnhap, String.valueOf(dongia));
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm Sản Phẩm Bảo Hành Thành Công", "Hóa Đơn", JOptionPane.INFORMATION_MESSAGE, icon);

                vip.updateLuotBaoHanh(idsp, idhdThuong, soluongnhap + luot, sl, dongiahd);

                loadCTHDBaoHanh(idhdbh);

                loadCTHD(idhdThuong);
            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Lỗi", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);

            }
        }


    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void tblBaoHanhChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBaoHanhChiTietMouseClicked
        int index = tblBaoHanhChiTiet.getSelectedRow();
        String idsp = tblBaoHanhChiTiet.getValueAt(index, 1).toString();
        String idhd = tblBaoHanhChiTiet.getValueAt(index, 0).toString();
        int Luot = Integer.parseInt(tblBaoHanhChiTiet.getValueAt(index, 3).toString());
        String[] buttons = {"Delete", "Cancel"};

        int rc = JOptionPane.showOptionDialog(null, "Question ?", "Confirmation",
                JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
        if (rc == 0) {
            boolean b = vip.delete(idsp, idhd);
            if (b == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Delete Thành Công", "Bảo Hành", JOptionPane.INFORMATION_MESSAGE, icon);

                int row = tblTimKiem.getSelectedRow();
                String idhoadon = tblTimKiem.getValueAt(row, 0).toString();

                vip.updateLaiLuot(idhoadon, idsp, Luot);

                loadCTHDBaoHanh(idhd);

                loadCTHD(idhoadon);
            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Lỗi", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);

            }
        } else {

        }

    }//GEN-LAST:event_tblBaoHanhChiTietMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        MenuView v = new MenuView(IdNV, TenNV, CV);

        v.setLocationRelativeTo(null);

        v.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(BaoHanhVipProView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BaoHanhVipProView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BaoHanhVipProView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BaoHanhVipProView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new BaoHanhVipProView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BaoHanh;
    private javax.swing.JButton BaoHanhbtn;
    private javax.swing.JButton Check;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rdKH;
    private javax.swing.JRadioButton rdoHD;
    private javax.swing.JTable tblBaoHanh;
    private javax.swing.JTable tblBaoHanhChiTiet;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblTimKiem;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
