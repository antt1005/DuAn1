/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.sql.Date;

/**
 *
 * @author Phuong Bi
 */
public class ViewModelNhanVien {
    private String id;
    private String ma;
    private String hoTen;
    private String sdt;
    private String diaChi;
    private String ngaySinh;
    private String idCH;
    private String idCV;
    private String matKhau;
    private String email;
  //  private int trangThai;

    public ViewModelNhanVien() {
        
    }

    public ViewModelNhanVien(String id, String ma, String hoTen, String sdt, String diaChi, String ngaySinh, String idCH, String idCV, String matKhau, String email) {
        this.id = id;
        this.ma = ma;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.idCH = idCH;
        this.idCV = idCV;
        this.matKhau = matKhau;
        this.email = email;
    }

    public String getId() {
        
        return id;
    }

    public void setId(String id) {
        
        this.id = id;
    }

    public String getMa() {
        
        return ma;
    }

    public void setMa(String ma) {
        
        this.ma = ma;
    }

    public String getHoTen() {
        
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        
        this.hoTen = hoTen;
    }

    public String getSdt() {
        
        return sdt;
    }

    public void setSdt(String sdt) {
        
        this.sdt = sdt;
    }

    public String getDiaChi() {
        
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        
        this.diaChi = diaChi;
    }

    public String getNgaySinh() {
        
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        
        this.ngaySinh = ngaySinh;
    }

    public String getIdCH() {
        
        return idCH;
    }

    public void setIdCH(String idCH) {
        
        this.idCH = idCH;
    }

    public String getIdCV() {
        
        return idCV;
    }

    public void setIdCV(String idCV) {
        
        this.idCV = idCV;
    }

    public String getMatKhau() {
        
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        
        this.matKhau = matKhau;
    }

    public String getEmail() {
        
        return email;
    }

    public void setEmail(String email) {
        
        this.email = email;
    }

   
    
}
