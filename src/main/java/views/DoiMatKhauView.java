/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.awt.Color;

import java.awt.HeadlessException;

import java.util.List;

import java.util.Properties;

import javax.mail.Authenticator;

import javax.mail.Message;

import javax.mail.MessagingException;

import javax.mail.Multipart;

import javax.mail.PasswordAuthentication;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.AddressException;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeBodyPart;

import javax.mail.internet.MimeMessage;

import javax.mail.internet.MimeMultipart;

import javax.swing.Icon;

import javax.swing.JOptionPane;

import services.DoiMatKhauService;

import services.NhanVienService;

import services.impl.IManageDoiMatKhauService;

import services.impl.IManageNhanVienService;

import viewModel.ViewModelNhanVien;

/**
 *
 * @author ktkha
 */
public class DoiMatKhauView extends javax.swing.JFrame {

    /**
     * Creates new form DoiMatKhauView
     */
    IManageNhanVienService nvSV = new NhanVienService();

    IManageDoiMatKhauService dmkSV = new DoiMatKhauService();

    String idnv = null;

    public DoiMatKhauView(String id) {

        initComponents();

        idnv = id;

        setLocationRelativeTo(null);

        lbCaptcha.setText(captcha());

    }

    public String captcha() {

        String captchaString = "ABCDEFGHIKLMMNORSTUIZXVC123456789";

        String captcha = "";

        for (int i = 0; i < 5; i++) {

            int index = (int) (Math.random() * captchaString.length());

            captcha = captcha + captchaString.charAt(index);

        }

        return captcha;
    }

    public boolean checkCaptcha() {

        boolean a = false;

        try {
            if (txtCaptcha.getText().equals(lbCaptcha.getText())) {
                a = true;
            } else {
                a = false;
            }
        } catch (Exception e) {
            return false;
        }
        return a;
    }

//    private final static String emailGui = "duymaidinh2003@gmail.com";
//
//    private final static String matKhau = "kbryrjbmkrrxyiub";
//
//    public void guiMail(String emailNhan,
//            String tieuDe, String noiDung, String ten, String pass)
//            throws AddressException, MessagingException {
//
//        Properties prop = new Properties();
//
//        prop.put("mail.smtp.auth", true);
//
//        prop.put("mail.smtp.starttls.enable", "true");
//
//        prop.put("mail.smtp.host", "smtp.gmail.com");
//
//        prop.put("mail.smtp.port", "587");
//
//        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
//
//        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//
//        Session session = Session.getInstance(prop, new Authenticator() {
//
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//
//                return new PasswordAuthentication(emailGui, matKhau);
//            }
//        });
//
//        Message message = new MimeMessage(session);
//
//        message.setFrom(new InternetAddress(emailGui));
//
//        message.setRecipients(
//                Message.RecipientType.TO, InternetAddress.parse(emailNhan));
//
//        // Tiêu đề
//        message.setSubject(tieuDe);
//
//        // Nội dung
//        MimeBodyPart mimeBodyPart = new MimeBodyPart();
//
//        MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
//        //
//        mimeBodyPart.setContent(tieuDe, "text/html; charset=utf-8");
//
//        mimeBodyPart1.setContent(noiDung + "<br>" + "Ten  : " + ten + "<br> Pass : " + pass, "text/html; charset=utf-8");
//
//        Multipart multipart = new MimeMultipart();
//
//        multipart.addBodyPart(mimeBodyPart);
//
//        multipart.addBodyPart(mimeBodyPart1);
//
//        message.setContent(multipart);
//
//        Transport.send(message);
//    }
//    public boolean checkMKCu() {
//        
//        boolean check = false;
//
//        List<ViewModelNhanVien> list = nvSV.getListNV();
//        try {
//            for (ViewModelNhanVien x : list) {
//                if (!(txtSDT.getText().equals(x.getSdt()))) {
//                    System.out.println(x.getSdt());
//                    Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));
//                    JOptionPane.showMessageDialog(this, "SDT Ko Tồn tại", "Đổi Mật Khẩu!", JOptionPane.INFORMATION_MESSAGE, icon);
//                    check = false;
//                } else {
//                    return check = true;
//                }
//            }
//        } catch (HeadlessException headlessException) {
//            return false;
//        }
//        return check;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     *
     * WARNING: Do NOT modify this code. The content of this method is always
     *
     * regenerated by the Form Editor.
     *
     *
     *
     *
     *
     *
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnReloadCaptcha = new javax.swing.JButton();
        txtCaptcha = new javax.swing.JTextField();
        txtMKCu = new javax.swing.JPasswordField();
        txtMkMoi = new javax.swing.JPasswordField();
        txtNhapLaiMK = new javax.swing.JPasswordField();
        lbCaptcha = new javax.swing.JLabel();
        btnDoiMK = new javax.swing.JButton();
        PassMoi = new javax.swing.JLabel();
        RePass = new javax.swing.JLabel();
        CBXemMKMoi = new javax.swing.JCheckBox();
        CBXemMKCu = new javax.swing.JCheckBox();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText(" Đổi Mật Khẩu");

        btnReloadCaptcha.setBackground(new java.awt.Color(255, 102, 102));
        btnReloadCaptcha.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReloadCaptcha.setText("O");
        btnReloadCaptcha.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnReloadCaptcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadCaptchaActionPerformed(evt);
            }
        });
        btnReloadCaptcha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnReloadCaptchaKeyReleased(evt);
            }
        });

        txtCaptcha.setBackground(new java.awt.Color(255, 153, 153));
        txtCaptcha.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0))), "Captcha"));
        txtCaptcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCaptchaActionPerformed(evt);
            }
        });
        txtCaptcha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCaptchaKeyReleased(evt);
            }
        });

        txtMKCu.setBackground(new java.awt.Color(255, 153, 153));
        txtMKCu.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0))), "Mật Khẩu Cũ"));

        txtMkMoi.setBackground(new java.awt.Color(255, 153, 153));
        txtMkMoi.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "Mật Khẩu Mới")));
        txtMkMoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMkMoiKeyReleased(evt);
            }
        });

        txtNhapLaiMK.setBackground(new java.awt.Color(255, 153, 153));
        txtNhapLaiMK.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "Nhập Lại Mật Khẩu")));
        txtNhapLaiMK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNhapLaiMKKeyReleased(evt);
            }
        });

        lbCaptcha.setFont(new java.awt.Font("Tempus Sans ITC", 2, 24)); // NOI18N
        lbCaptcha.setText("-");

        btnDoiMK.setBackground(new java.awt.Color(255, 102, 102));
        btnDoiMK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDoiMK.setText("OK");
        btnDoiMK.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMKActionPerformed(evt);
            }
        });

        PassMoi.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        PassMoi.setForeground(new java.awt.Color(255, 51, 51));
        PassMoi.setText("-");

        RePass.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        RePass.setForeground(new java.awt.Color(255, 51, 51));
        RePass.setText("-");

        CBXemMKMoi.setBackground(new java.awt.Color(255, 153, 153));
        CBXemMKMoi.setText("    Show Password");
        CBXemMKMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBXemMKMoiActionPerformed(evt);
            }
        });

        CBXemMKCu.setBackground(new java.awt.Color(255, 153, 153));
        CBXemMKCu.setText("    Show Password");
        CBXemMKCu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBXemMKCuActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(255, 153, 153));
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CBXemMKMoi)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNhapLaiMK)
                                .addComponent(txtMKCu)
                                .addComponent(txtMkMoi)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtCaptcha, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lbCaptcha, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnReloadCaptcha, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(CBXemMKCu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PassMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RePass, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(btnDoiMK, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMKCu, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(CBXemMKCu, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMkMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PassMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNhapLaiMK, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RePass, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(CBXemMKMoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCaptcha, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCaptcha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReloadCaptcha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btnDoiMK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void txtCaptchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCaptchaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCaptchaActionPerformed

    private void txtCaptchaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCaptchaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCaptchaKeyReleased

    private void btnReloadCaptchaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnReloadCaptchaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReloadCaptchaKeyReleased

    private void btnReloadCaptchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadCaptchaActionPerformed
        // TODO add your handling code here:
        lbCaptcha.setText(captcha());

    }//GEN-LAST:event_btnReloadCaptchaActionPerformed

    private void btnDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMKActionPerformed
        // TODO add your handling code here:

//        boolean a = checkMKCu();
//        if (a == true) {
//            JOptionPane.showMessageDialog(this, "OKE thành công");
//        }
        String a = dmkSV.getMKById(idnv);

        String id = idnv;

        boolean captcha = checkCaptcha();

        if (txtMKCu.getText().equals("") || txtMkMoi.getText().equals("") || txtNhapLaiMK.getText().equals("")) {

            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));

            JOptionPane.showMessageDialog(this, "Nhập đầy đủ các trường!", "Đổi Mật Khẩu!", JOptionPane.INFORMATION_MESSAGE, icon);

            return;

        }

        if (!txtMKCu.getText().equals(a)) {

            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));

            JOptionPane.showMessageDialog(this, "Mật khẩu cũ sai!", "Đổi Mật Khẩu!", JOptionPane.INFORMATION_MESSAGE, icon);

            return;

        }

        if (!RePass.getText().equals("V")) {

            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));

            JOptionPane.showMessageDialog(this, "Mật khẩu khác mật khẩu mới!", "Đổi Mật Khẩu!", JOptionPane.INFORMATION_MESSAGE, icon);

            return;

        }

        if (captcha == false) {

            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));

            JOptionPane.showMessageDialog(this, "Sai mã Captcha!", "Đổi Mật Khẩu!", JOptionPane.INFORMATION_MESSAGE, icon);

            lbCaptcha.setText(captcha());

            return;

        }

        boolean update = dmkSV.updateMKById(id, txtNhapLaiMK.getText());

        if (update == true) {

            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/themmoiicon.png"));

            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công ", "Đổi Mật Khẩu", JOptionPane.INFORMATION_MESSAGE, icon);

            txtMKCu.setText("");

            txtMkMoi.setText("");

            txtNhapLaiMK.setText("");

            txtCaptcha.setText("");

            PassMoi.setText("-");

            PassMoi.setForeground(Color.RED);

            RePass.setText("-");

            RePass.setForeground(Color.RED);

            CBXemMKCu.setSelected(false);

            CBXemMKMoi.setSelected(false);

            lbCaptcha.setText(captcha());

        } else {

            Icon icon = new javax.swing.ImageIcon(getClass().getResource("/img/deleteicon.png"));

            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thất bại!", "Đổi Mật Khẩu", JOptionPane.INFORMATION_MESSAGE, icon);

        }


    }//GEN-LAST:event_btnDoiMKActionPerformed

    private void txtMkMoiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMkMoiKeyReleased
        // TODO add your handling code here:
        try {

            if (!txtMkMoi.getText().matches("^[a-zA-Z0-9]+")) {

                PassMoi.setForeground(Color.RED);

                PassMoi.setText("X");

            } else if (txtMkMoi.getText().length() > 10) {

                PassMoi.setForeground(Color.RED);

                PassMoi.setText("< 10 ");

            } else {

                PassMoi.setForeground(Color.GREEN);

                PassMoi.setText("V");

            }

        } catch (Exception e) {

            PassMoi.setForeground(Color.RED);

            PassMoi.setText("X");

        }
    }//GEN-LAST:event_txtMkMoiKeyReleased

    private void CBXemMKMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBXemMKMoiActionPerformed
        // TODO add your handling code here:
        if (CBXemMKMoi.isSelected()) {

            txtMkMoi.setEchoChar((char) 0); //password = JPasswordField

            txtNhapLaiMK.setEchoChar((char) 0); //password = JPasswordField

        } else {

            txtMkMoi.setEchoChar('*');

            txtNhapLaiMK.setEchoChar('*');

        }
    }//GEN-LAST:event_CBXemMKMoiActionPerformed

    private void txtNhapLaiMKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNhapLaiMKKeyReleased
        // TODO add your handling code here:
        try {

            if (!txtNhapLaiMK.getText().equals(txtMkMoi.getText())) {

                RePass.setForeground(Color.RED);

                RePass.setText("X");

            } else {

                RePass.setForeground(Color.GREEN);

                RePass.setText("V");

            }

        } catch (Exception e) {

            RePass.setForeground(Color.RED);

            RePass.setText("X");

        }
    }//GEN-LAST:event_txtNhapLaiMKKeyReleased

    private void CBXemMKCuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBXemMKCuActionPerformed
        // TODO add your handling code here:
        if (CBXemMKCu.isSelected()) {

            txtMKCu.setEchoChar((char) 0); //password = JPasswordField

        } else {

            txtMKCu.setEchoChar('*');

        }
    }//GEN-LAST:event_CBXemMKCuActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.dispose();

    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(DoiMatKhauView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhauView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhauView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhauView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

//                new DoiMatKhauView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CBXemMKCu;
    private javax.swing.JCheckBox CBXemMKMoi;
    private javax.swing.JLabel PassMoi;
    private javax.swing.JLabel RePass;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDoiMK;
    private javax.swing.JButton btnReloadCaptcha;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbCaptcha;
    private javax.swing.JTextField txtCaptcha;
    private javax.swing.JPasswordField txtMKCu;
    private javax.swing.JPasswordField txtMkMoi;
    private javax.swing.JPasswordField txtNhapLaiMK;
    // End of variables declaration//GEN-END:variables
}
