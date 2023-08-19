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
import java.beans.BeanProperty;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.BaoHanhService;
import services.HoaDonChiTietService;
import services.HoaDonDeBaoHanhSerivice;
import services.NhanVienService;
import services.impl.IManageBaoHanhService;
import services.impl.IManageChiTietHoaDonBanHang;
import services.impl.IManageHoaDonDeBaoHanhService;
import services.impl.IManageNhanVienService;
import viewModel.ViewModelBAOHANHCHITIET;
import viewModel.ViewModelChiTietSanPhamBanHang;
import viewModel.ViewModelHDCTBH;
import viewModel.ViewModelHoaDonBaoHanh;
import viewModel.ViewModelHoaDonChiTietBanHang;
import viewModel.ViewModelHoaDonDeBaoHanh;
import viewModel.ViewModelKhachHang;
import viewModel.ViewModelNhanVien;

/**
 *
 * @author Admin
 */
public class BaoHanhView extends javax.swing.JFrame {
    
    private IManageBaoHanhService bh = new BaoHanhService();
    
    private IManageHoaDonDeBaoHanhService hdbh = new HoaDonDeBaoHanhSerivice();
    
    private IManageNhanVienService nvSV = new NhanVienService();
    
    private IManageChiTietHoaDonBanHang ct = new HoaDonChiTietService();
    
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form BaoHanhView
     */
    public BaoHanhView() {

//        setUndecorated(true);
        initComponents();
        
        this.setDefaultCloseOperation(BaoHanhView.DO_NOTHING_ON_CLOSE);
        
        setLocationRelativeTo(null);
        loadtb();
        loadTBHDBH();
//        loadTBHD("A0A56FC7-A093-41FB-B0DF-D0FC62F46267");
//        loadTBHDCT("0FF804AB-10D0-4694-A332-21A688669FB8");
    }
    
    public void loadtb() {
        model = (DefaultTableModel) tbkhachhang.getModel();
        model.setRowCount(0);
        List<ViewModelKhachHang> kh = bh.getListKhachHang();
        for (ViewModelKhachHang x : kh) {
            model.addRow(new Object[]{
                x.getID(), x.getMa(), x.getTenkh(), x.getSdt()
            });
        }
        
    }
    
    public void loadtbTKSDT(String sdt) {
        model = (DefaultTableModel) tbkhachhang.getModel();
        model.setRowCount(0);
        List<ViewModelKhachHang> kh = bh.getTKSDT(sdt);
        for (ViewModelKhachHang x : kh) {
            model.addRow(new Object[]{
                x.getID(), x.getMa(), x.getTenkh(), x.getSdt()
            });
        }
        
    }
    
    public void loadTBHD(String id) {
        model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        List<ViewModelHoaDonDeBaoHanh> hd = hdbh.getListHD(id);
        for (ViewModelHoaDonDeBaoHanh x : hd) {
            model.addRow(new Object[]{
                x.getId(), x.getMa(), x.getNgayThanhToan(), x.getTenNV(), x.getIdKH(), x.getTongTien(), x.getSLBH()
            });
        }
    }
    
    public void loadTBHDCT(String id) {
        model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
        List<ViewModelHDCTBH> hd = hdbh.getListCTHD(id);
        for (ViewModelHDCTBH x : hd) {
            model.addRow(new Object[]{
                x.getId(), x.getIdHD(), x.getSoLuong(), x.getThanhTien()
            });
        }
    }
    
    public void loadTBBAOHANHCT(String id) {
        model = (DefaultTableModel) tblHDBHChiTiet.getModel();
        model.setRowCount(0);
        List<ViewModelBAOHANHCHITIET> hd = hdbh.getListCTHDbaoHanh(id);
        for (ViewModelBAOHANHCHITIET x : hd) {
            model.addRow(new Object[]{
                x.getIdbh(), x.getIdsp(), x.getTensp(), x.getSoluong()
            });
        }
    }
    
    public void loadTBHDBH() {
        model = (DefaultTableModel) tblHDBaoHanh.getModel();
        model.setRowCount(0);
        List<ViewModelHoaDonBaoHanh> hd = hdbh.getListHDBH();
        for (ViewModelHoaDonBaoHanh x : hd) {
            model.addRow(new Object[]{
                x.getId(), x.getNgayTao(), x.getTenNV(), x.getTenKH(), x.getTrangThai() == 3 ? "Hoa Don dang bao hanh" : "Hoa Don da bao hanh"
            });
        }
    }
    
    public void getIdNhanVien() {
        int index = tblHDBaoHanh.getSelectedRow();
        String ten = tblHDBaoHanh.getValueAt(index, 2).toString();
        List<ViewModelNhanVien> list = nvSV.getListNV();
        for (ViewModelNhanVien x : list) {
            if (ten.equals(x.getHoTen())) {
                x.getId();
                System.out.println(x.toString());
            }
        }
        
    }
    
    public int getSoluong(String idsp) {
        int i = 0;
        int index = tblHDBaoHanh.getSelectedRow();
        String idsp1 = tblHDBaoHanh.getValueAt(index, 0).toString();
        
        List<ViewModelHoaDonChiTietBanHang> lisst = ct.list(idsp1);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbkhachhang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHDBaoHanh = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHDBHChiTiet = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("KHACH HANG"));

        jLabel1.setText("NHAP SÐT");

        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemKeyReleased(evt);
            }
        });

        tbkhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "MA", "TEN", "SÐT"
            }
        ));
        tbkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkhachhangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbkhachhang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("HÐ "));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "MA", "NGAY THANH TOAN", "TEN NV", "ID KH", "TONG TIEN", "So Lan Bao Hanh"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("HÐ CHI TIET"));

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "ID HÐ", "SO LUONG", "THANH TIEN"
            }
        ));
        tblHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDonChiTiet);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("HÐ BAO HANH"));

        tblHDBaoHanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NGAY TAO", "TÊN NV", "TÊN KH", "TRANG THAI"
            }
        ));
        tblHDBaoHanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDBaoHanhMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblHDBaoHanh);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("HÐ BAO HANH CHI TIET"));

        tblHDBHChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "IDBH", "IDSP", "TENSP", "SO LUONG"
            }
        ));
        tblHDBHChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDBHChiTietMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblHDBHChiTiet);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jButton1.setText("TAO HÐ BAO HANH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("BAO HANH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("BACK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(22, 22, 22))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
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

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
        // TODO add your handling code here:
        loadtbTKSDT(txttimkiem.getText());
    }//GEN-LAST:event_txttimkiemKeyReleased

    private void tbkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkhachhangMouseClicked
        // TODO add your handling code here:
        int index = tbkhachhang.getSelectedRow();
        
        loadTBHD(tbkhachhang.getValueAt(index, 0).toString());
        
        model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_tbkhachhangMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        try {
            int index = tblHoaDon.getSelectedRow();
            
            loadTBHDCT(tblHoaDon.getValueAt(index, 0).toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int hdi = tblHoaDon.getSelectedRow();
        
        int indexhd = tblHoaDon.getSelectedRow();
        if (indexhd < 0) {
            JOptionPane.showMessageDialog(this, "Vui Long Chon Hoa Don Muon Bao Hanh");
            return;
        } else {
            String[] buttons = {"Update", "Delete", "Cancel"};
            
            int rc = JOptionPane.showOptionDialog(null, "Bạn Có Muốn Tạo Bảo Hành Cho Hóa Đơn Này Không", "Confirmation",
                    JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[2]);
            if (rc == 0) {
            }
        }
        
        int index = tbkhachhang.getSelectedRow();
        if (index
                < 0) {
            JOptionPane.showMessageDialog(this, "Chon khach hang`");
            return;
        }
        String idKH = tbkhachhang.getValueAt(index, 0).toString();
        
        if (tblHoaDon.getRowCount()
                == 0) {
            JOptionPane.showMessageDialog(this, "Khong co hoa don de bao hanh");
            return;
        }
        
        System.out.println(tblHoaDon.getRowCount());
        
        NhanVien nv = new NhanVien();
        
        nv.setId(
                "e8a8ea9a-d368-44f5-81f8-30f2ebddafb3");
        KhachHang kh = new KhachHang();
        
        kh.setId(idKH);
        
        HoaDon hd = new HoaDon();
        
        hd.setIdNhanVien(nv);
        
        hd.setIdKhachHang(kh);
        
        hd.setTrangThai(
                3);
        
        System.out.println(hd.toString());
        
        boolean b = hdbh.addHoadon(hd);
        if (b
                == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Tạo Hóa Đơn Bảo Hành Thành Công", "Hóa Đơn", JOptionPane.INFORMATION_MESSAGE, icon);
            
            loadTBHDBH();
            loadTBHD(idKH);
            
        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Lỗi!", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);
            
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        
        int bh = tblHDBaoHanh.getSelectedRow();
        
        String Idhdbh = (String) tblHDBaoHanh.getValueAt(bh, 0);// id sản phẩm
        //String IdHd = (String) tblHoaDonChiTiet.getValueAt(index, 1);

        int hdct = tblHoaDonChiTiet.getSelectedRow();
        String idSp = (String) tblHoaDonChiTiet.getValueAt(hdct, 0);
        System.out.println(idSp);
        System.out.println(Idhdbh);
        int check = getSoluong(idSp);// nếu sản phẩm tồn tại thì lấy số lượng nếu nó là 0 thì không tồn tại
        System.out.println(check);
        if (check == 0) {
            String soLuongNhapinpit = JOptionPane.showInputDialog("Nhập Số Lượng Sản Phẩm " + " ");
            // NHẬP SỐ LƯỢNG ĐỂ INSERT 
            if (soLuongNhapinpit == null) {
                JOptionPane.showMessageDialog(this, "oke");
                return;
            }
            if (Integer.parseInt(soLuongNhapinpit) > Integer.parseInt(tblHoaDonChiTiet.getValueAt(hdct, 2).toString())) {
                JOptionPane.showMessageDialog(this, "Số lượng không đủ!");
                return;
            }
            
            int soluongnhap = Integer.parseInt(soLuongNhapinpit);
            
            HoaDonChiTiet hd = new HoaDonChiTiet();
            HoaDon a = new HoaDon();
            a.setId(Idhdbh);
            
            ChiTietDoGo b = new ChiTietDoGo();
            b.setId(idSp);
            
            hd.setIdHoaDon(a);
            hd.setIdChiTietDoGo(b);
            hd.setSoLuong(soluongnhap);
            System.out.println(hd.toString());
            boolean c = hdbh.add(hd);
            
            if (c == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm  thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                
                loadTBBAOHANHCT(Idhdbh);
                
            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "lỗi", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        } else { //////////////////////////////////////////////////////////////////////////////////////////////////////////
            String soLuongNhapinpit = JOptionPane.showInputDialog("Nhập Số Lượng Sản Phẩm " + " ");
            // NHẬP SỐ LƯỢNG ĐỂ INSERT 
            if (soLuongNhapinpit == null) {
                JOptionPane.showMessageDialog(this, "oke");
                return;
            }
            if (!soLuongNhapinpit.matches("^[0-9]+$")) {
                JOptionPane.showMessageDialog(this, "0-9");
                return;
            }
            int soluonghientai = Integer.parseInt(tblHoaDonChiTiet.getValueAt(hdct, 2).toString());
            if (Integer.parseInt(soLuongNhapinpit) > soluonghientai) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Sản Phẩm Không Đủ", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                return;
            }
            
            HoaDonChiTiet hd = new HoaDonChiTiet();
            HoaDon a = new HoaDon();
            a.setId(Idhdbh);
            
            ChiTietDoGo b = new ChiTietDoGo();
            b.setId(idSp);
            int soluongnhap = Integer.parseInt(soLuongNhapinpit);
            hd.setIdHoaDon(a);
            hd.setIdChiTietDoGo(b);
            hd.setSoLuong(soluongnhap + check);
            
            boolean c = hdbh.update(hd);
            
            if (c == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm  thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                
                loadTBBAOHANHCT(Idhdbh);
                
            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Lỗi Trống Hóa Đơn", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
            
        }
    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void tblHDBaoHanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDBaoHanhMouseClicked
        int index = tblHDBaoHanh.getSelectedRow();
//       int bh = tblHDBaoHanh.getSelectedRow();

        String Idhdbh = (String) tblHDBaoHanh.getValueAt(index, 0);
        loadTBBAOHANHCT(Idhdbh);
        

    }//GEN-LAST:event_tblHDBaoHanhMouseClicked

    private void tblHDBHChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDBHChiTietMouseClicked
        String[] buttons = {"Update", "Delete", "Cancel"};
        
        int hdct = tblHDBHChiTiet.getSelectedRow();
        String idhdbh = (String) tblHDBHChiTiet.getValueAt(hdct, 0);
        String idspbh = (String) tblHDBHChiTiet.getValueAt(hdct, 1);
        int soLuong = Integer.parseInt(tblHDBHChiTiet.getValueAt(hdct, 3).toString());
        
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
            
            HoaDonChiTiet hd = new HoaDonChiTiet();
            HoaDon a = new HoaDon();
            a.setId(idhdbh);
            
            ChiTietDoGo b = new ChiTietDoGo();
            b.setId(idspbh);
            
            hd.setIdHoaDon(a);
            hd.setIdChiTietDoGo(b);
            hd.setSoLuong(soLuong - SoLuongNhap);
            
            boolean c = hdbh.update(hd);
            
            if (c == true) {//update
                if (SoLuongNhap == soLuong) {
                    JOptionPane.showMessageDialog(this, "xóa di dung update ve 0");
                    if (SoLuongNhap > soLuong) {
                        Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                        JOptionPane.showMessageDialog(this, "Sản Phẩm Không Đủ Để Update", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                        return;
                    }
                    
                    Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                    JOptionPane.showMessageDialog(this, "Update thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                    loadTBBAOHANHCT(idhdbh);
                    
                } else if (SoLuongNhap < soLuong) {
                    
                    Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                    JOptionPane.showMessageDialog(this, "Update thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                    loadTBBAOHANHCT(idhdbh);
                    
                }
            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "lỗi", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
        if (rc == 1) {// delete

            boolean c = hdbh.delete(idspbh, idhdbh);
            
            if (c == true) {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "delete thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                loadTBBAOHANHCT(idhdbh);
            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "lỗi", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
        if (rc == 2) {// thoat
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "DẠ Dạ", "Hóa Đơn ChiTiet", JOptionPane.INFORMATION_MESSAGE, icon);
        }

    }//GEN-LAST:event_tblHDBHChiTietMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int index = tbkhachhang.getSelectedRow();
        String idKH = tbkhachhang.getValueAt(index, 0).toString();
        int hdi = tblHDBaoHanh.getSelectedRow();
        String idhd = tblHDBaoHanh.getValueAt(hdi, 0).toString();
        //int solan = Integer.parseInt(tblHoaDon.getValueAt(hdi, 6).toString());
        NhanVien nv = new NhanVien();
        
        nv.setId("e8a8ea9a-d368-44f5-81f8-30f2ebddafb3");
        KhachHang kh = new KhachHang();
        
        kh.setId(idKH);
        
        HoaDon hd = new HoaDon();
        hd.setId(idhd);
        hd.setIdNhanVien(nv);
        
        hd.setIdKhachHang(kh);
        
        hd.setTrangThai(4);
        
        System.out.println(hd.toString());
        
        boolean b = hdbh.updateHoadon(hd);
        if (b == true){
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "Thành Công", "Hóa Đơn", JOptionPane.INFORMATION_MESSAGE, icon);
          
            loadTBHDBH();
            loadTBHD(idKH);
            
        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "Lỗi!", "Hóa Đơn !", JOptionPane.INFORMATION_MESSAGE, icon);
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(BaoHanhView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BaoHanhView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BaoHanhView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BaoHanhView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaoHanhView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tbkhachhang;
    private javax.swing.JTable tblHDBHChiTiet;
    private javax.swing.JTable tblHDBaoHanh;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
