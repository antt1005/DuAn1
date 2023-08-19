/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class ViewModelHoaDonChiTietBanHang {
    private String idsp;
    private String idhd;
    private String ten;
    private int soluong;
    private BigDecimal donGia;

    public ViewModelHoaDonChiTietBanHang(String idsp, String idhd, String ten, int soluong, BigDecimal donGia) {
        this.idsp = idsp;
        this.idhd = idhd;
        this.ten = ten;
        this.soluong = soluong;
        this.donGia = donGia;
    }

    public ViewModelHoaDonChiTietBanHang() {
        
    }

    public String getIdsp() {
        
        return idsp;
    }

    public void setIdsp(String idsp) {
        
        this.idsp = idsp;
    }

    public String getIdhd() {
        
        return idhd;
    }

    public void setIdhd(String idhd) {
        
        this.idhd = idhd;
    }

    public String getTen() {
        
        return ten;
    }

    public void setTen(String ten) {
        
        this.ten = ten;
    }

    public int getSoluong() {
        
        return soluong;
    }

    public void setSoluong(int soluong) {
        
        this.soluong = soluong;
    }

    public BigDecimal getDonGia() {
        
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        
        return "ViewModelHoaDonChiTietBanHang{" + "idsp=" + idsp + ", idhd=" + idhd + ", ten=" + ten + ", soluong=" + soluong + ", donGia=" + donGia + '}';
    }

    
    
}
