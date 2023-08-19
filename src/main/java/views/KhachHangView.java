/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainModels.KhachHang;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.KhachHangService;
import services.impl.IManageKhachHangService;
import viewModel.ViewModelKhachHang;

/**
 *
 * @author Admin
 */
public class KhachHangView extends javax.swing.JFrame {

    private DefaultTableModel model;
    private IManageKhachHangService khrp = new KhachHangService();

    /**
     * Creates new form KhachHang
     */
    String IdNV;
    String TenNV;
    String CV;

    public KhachHangView(String Id, String Ten, String cv) {
        initComponents();
        this.setDefaultCloseOperation(KhachHangView.DO_NOTHING_ON_CLOSE);
        IdNV = Id;
        TenNV = Ten;
        CV = cv;
        setLocationRelativeTo(null);
        loadtb();
        loadthangst1();
    }

    public void loadtb() {
        model = (DefaultTableModel) tbtable.getModel();
        model.setRowCount(0);
        List<ViewModelKhachHang> kh = khrp.getListKhachHang();
        for (ViewModelKhachHang x : kh) {
            model.addRow(new Object[]{
                x.getID(), x.getMa(), x.getTenkh(), x.getSdt(), x.getDiachi()
            });
        }

    }

    public void loadthangst1() {
        int index = 0;
        txtID.setText(tbtable.getValueAt(index, 0).toString());
        txtmaKH.setText(tbtable.getValueAt(index, 1).toString());
        txttenKH.setText(tbtable.getValueAt(index, 2).toString());
        txtdienThoai.setText(tbtable.getValueAt(index, 3).toString());
        txtdiaChi.setText(tbtable.getValueAt(index, 4).toString());
    }

    public void getNameKhachHang(String ten) {
        model = (DefaultTableModel) tbtable.getModel();
        model.setRowCount(0);
        List<ViewModelKhachHang> kh = khrp.getListKhachHangByName(ten);
        for (ViewModelKhachHang x : kh) {
            model.addRow(new Object[]{
                x.getID(), x.getMa(), x.getTenkh(), x.getSdt(), x.getDiachi()
            });
        }
    }
//    public void getsdtKhachHang(String sdt) {
//        model = (DefaultTableModel) tbtable.getModel();
//        model.setRowCount(0);
//        List<ViewModelKhachHang> kh = khrp.getListKhachHangBysdt(sdt);
//        for (ViewModelKhachHang x : kh) {
//            model.addRow(new Object[]{
//                x.getID(), x.getMa(), x.getTenkh(),x.getSdt(),x.getDiachi()
//            });
//        }
//    }

    public boolean valydatefrom() {
        try {
            if (txttenKH.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "không được để trống");
                return false;
            }
            if (txtdienThoai.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "không được để trống");
                return false;
            }
            if (txtdiaChi.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "không được để trống");
                return false;
            }
            try {
                if (txttenKH.getText().length() > 11) {
                    JOptionPane.showMessageDialog(this, "tên khách hàng không được để quá 10 ký tự");
                    return false;
                }
            } catch (HeadlessException headlessException) {
                return false;
            }

            try {
                if (txtdienThoai.getText().length() > 11) {
                    JOptionPane.showMessageDialog(this, "sdt < 11");
                    return false;
                }
            } catch (HeadlessException headlessException) {
                return false;
            }
            try {
                if (txtdiaChi.getText().length() > 30) {
                    JOptionPane.showMessageDialog(this, "địa chỉ k đc 30 ký tự");
                    return false;
                }
            } catch (HeadlessException headlessException) {
                return false;
            }

            if (!txttenKH.getText().matches("^[a-zA-Z ]+")) {
                JOptionPane.showMessageDialog(this, "Tên chỉ chữ ");
                return false;
            }
            if (!txtdienThoai.getText().matches("0[1-9]{1}[0-9 ]{8}")) {
                JOptionPane.showMessageDialog(this, "số điện thoại số nguyên vàđúng form 0(1-9)xxxxxxxx");
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean checksdt() { //check sdt khachhang
        String sdt = txtdienThoai.getText();
        List<ViewModelKhachHang> kh = khrp.getListKhachHang();
        for (ViewModelKhachHang v : kh) {
            if (sdt.equals(v.getSdt())) {
                JOptionPane.showMessageDialog(this, "so dien thoai đã tồn tại!");
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

        jPanel1 = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        txtmaKH = new javax.swing.JTextField();
        txttenKH = new javax.swing.JTextField();
        txtdienThoai = new javax.swing.JTextField();
        txtdiaChi = new javax.swing.JTextField();
        txttimKiem = new javax.swing.JTextField();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbtable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(255, 153, 204));
        txtID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtID.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "  ID  "));
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 208, -1));

        txtmaKH.setEditable(false);
        txtmaKH.setBackground(new java.awt.Color(255, 153, 204));
        txtmaKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtmaKH.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "  MA KH  "));
        jPanel1.add(txtmaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 208, -1));

        txttenKH.setBackground(new java.awt.Color(255, 153, 204));
        txttenKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txttenKH.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "  TEN KH  "));
        jPanel1.add(txttenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 208, -1));

        txtdienThoai.setBackground(new java.awt.Color(255, 153, 204));
        txtdienThoai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtdienThoai.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "  SÐT  "));
        jPanel1.add(txtdienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 208, -1));

        txtdiaChi.setBackground(new java.awt.Color(255, 153, 204));
        txtdiaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtdiaChi.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "  DIA CHI  "));
        jPanel1.add(txtdiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 208, -1));

        txttimKiem.setBackground(new java.awt.Color(255, 153, 204));
        txttimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "TIM KIEM"));
        txttimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimKiemActionPerformed(evt);
            }
        });
        txttimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimKiemKeyReleased(evt);
            }
        });
        jPanel1.add(txttimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 208, -1));

        btnthem.setBackground(new java.awt.Color(255, 153, 204));
        btnthem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthem.setText("Thêm");
        btnthem.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });
        jPanel1.add(btnthem, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 90, 50));

        btnsua.setBackground(new java.awt.Color(255, 153, 204));
        btnsua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnsua.setText("Sửa");
        btnsua.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnsua, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 90, 50));

        btnxoa.setBackground(new java.awt.Color(255, 153, 204));
        btnxoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnxoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 80, 50));

        btnback.setBackground(new java.awt.Color(255, 153, 204));
        btnback.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnback.setText("BACK");
        btnback.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });
        jPanel1.add(btnback, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 17, 81, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("BANG QUAN LY KHACH HANG");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(293, 16, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 153, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)), "  BANG KHACH HANG  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tbtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Ma", "Ten", "SDT", "DiaChi"
            }
        ));
        tbtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbtable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 153, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jButton1.setText("Xuất File Excel");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, -1, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbtableMouseClicked
        int index = tbtable.getSelectedRow();
        txtID.setText(tbtable.getValueAt(index, 0).toString());
        txtmaKH.setText(tbtable.getValueAt(index, 1).toString());
        txttenKH.setText(tbtable.getValueAt(index, 2).toString());
        txtdienThoai.setText(tbtable.getValueAt(index, 3).toString());
        txtdiaChi.setText(tbtable.getValueAt(index, 4).toString());
    }//GEN-LAST:event_tbtableMouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:

        KhachHang kh = new KhachHang();
        kh.setTenKhachHang(txttenKH.getText());
        kh.setSdt(txtdienThoai.getText());
        kh.setDiaChi(txtdiaChi.getText());
        if (checksdt() && valydatefrom() == true) {
            boolean b = khrp.add(kh);
            if (b == true) {

                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
                loadtb();

            } else {
                Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
                JOptionPane.showMessageDialog(this, "Thêm ko thành công!", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        } else {

        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        KhachHang kh = new KhachHang();
        txtdienThoai.setEditable(true);
        kh.setId(txtID.getText());
        kh.setTenKhachHang(txttenKH.getText());
        kh.setSdt(txtdienThoai.getText());
        kh.setDiaChi(txtdiaChi.getText());

        boolean b = khrp.update(kh);
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "update thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadtb();

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "update ko thành công!", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        KhachHang kh = new KhachHang();
        // txtdienThoai.setEditable(true);
        kh.setId(txtID.getText());
        boolean b = khrp.delete(kh);
        if (b == true) {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));
            JOptionPane.showMessageDialog(this, "delete thành công", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
            loadtb();

        } else {
            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
            JOptionPane.showMessageDialog(this, "delete ko thành công!", "Sản Phẩm", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void txttimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimKiemKeyReleased
        if (txttimKiem.getText().equals("")) {
            loadtb();
        } else {
            getNameKhachHang(txttimKiem.getText());
        }
    }//GEN-LAST:event_txttimKiemKeyReleased

    private void txttimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimKiemActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        MenuView v = new MenuView(IdNV, TenNV, CV);
        setLocationRelativeTo(null);
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnbackActionPerformed

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

        List<ViewModelKhachHang> v = khrp.getListKhachHang();
        // List<NhanVien> sps = nhanVienService.getListNV();

        int s = v.size();
        for (int i = 0; i < s; i++) {
            ViewModelKhachHang chh = v.get(i);
            r = sheet.createRow(1 + i);

            cell = r.createCell(0, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(i + 1);

            cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getID());

            cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getMa());

            cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getTenkh());

            cell = r.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getSdt());

            cell = r.createCell(5, org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue(v.get(i).getDiachi());

        }

        JFileChooser chooser = new JFileChooser();// mở file lên
        chooser.showOpenDialog(null);//để chọn lưu vào đâu
        File f = chooser.getSelectedFile();

        try {

            FileOutputStream f1 = new FileOutputStream(new File(f + ".xlsx"));

            w.write(f1);
            f1.close();
            JOptionPane
                    .showMessageDialog(this, "In thành công");

        } catch (Exception e) {
        }

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(KhachHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new KhachHangView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbtable;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtdiaChi;
    private javax.swing.JTextField txtdienThoai;
    private javax.swing.JTextField txtmaKH;
    private javax.swing.JTextField txttenKH;
    private javax.swing.JTextField txttimKiem;
    // End of variables declaration//GEN-END:variables
}
