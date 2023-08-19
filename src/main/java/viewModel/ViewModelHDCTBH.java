/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.math.BigDecimal;

/**
 *
 * @author ktkha
 */
public class ViewModelHDCTBH {
    private String id;
    private String idHD;
    private int soLuong; 
    private BigDecimal thanhTien;

    public ViewModelHDCTBH() {
        
    }

    public ViewModelHDCTBH(String id, String idHD, int soLuong, BigDecimal thanhTien) {
        this.id = id;
        this.idHD = idHD;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public String getId() {
        
        return id;
    }

    public void setId(String id) {
        
        this.id = id;
    }

    public String getIdHD() {
        
        return idHD;
    }

    public void setIdHD(String idHD) {
        
        this.idHD = idHD;
    }

    public int getSoLuong() {
        
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        
        this.soLuong = soLuong;
    }

    public BigDecimal getThanhTien() {
        
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
}
