/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.sql.Date;

/**
 *
 * @author PC
 */
public class ViewModelHoadon {
    private String Id;
    private String ma;
    private String NgayTao;
    private String NgayThanhToan;
    private String TrangThaiHoaDon;
    private String phamtramKM;
    private String tenKH;
    private String tenNV;

    public ViewModelHoadon(String Id, String ma, String NgayTao, String NgayThanhToan, String TrangThaiHoaDon, String phamtramKM, String tenKH, String tenNV) {
        this.Id = Id;
        this.ma = ma;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.TrangThaiHoaDon = TrangThaiHoaDon;
        this.phamtramKM = phamtramKM;
        this.tenKH = tenKH;
        this.tenNV = tenNV;
    }

    public ViewModelHoadon() {
        
    }

    public String getId() {
        
        return Id;
    }

    public void setId(String Id) {
        
        this.Id = Id;
    }

    public String getMa() {
        
        return ma;
    }

    public void setMa(String ma) {
        
        this.ma = ma;
    }

    public String getNgayTao() {
        
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        
        this.NgayTao = NgayTao;
    }

    public String getNgayThanhToan() {
        
        return NgayThanhToan;
    }

    public void setNgayThanhToan(String NgayThanhToan) {
        
        this.NgayThanhToan = NgayThanhToan;
    }

    public String getTrangThaiHoaDon() {
        
        return TrangThaiHoaDon;
    }

    public void setTrangThaiHoaDon(String TrangThaiHoaDon) {
        
        this.TrangThaiHoaDon = TrangThaiHoaDon;
    }

    public String getPhamtramKM() {
        
        return phamtramKM;
    }

    public void setPhamtramKM(String phamtramKM) {
        
        this.phamtramKM = phamtramKM;
    }

    public String getTenKH() {
        
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        
        this.tenKH = tenKH;
    }

    public String getTenNV() {
        
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        
        this.tenNV = tenNV;
    }

    @Override
    public String toString() {
        
        return "ViewModelHoadon{" + "Id=" + Id + ", ma=" + ma + ", NgayTao=" + NgayTao + ", NgayThanhToan=" + NgayThanhToan + ", TrangThaiHoaDon=" + TrangThaiHoaDon + ", phamtramKM=" + phamtramKM + ", tenKH=" + tenKH + ", tenNV=" + tenNV + '}';
    }

  
  
   
    
}
