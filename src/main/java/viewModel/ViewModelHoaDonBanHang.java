/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author Admin
 */
public class ViewModelHoaDonBanHang {
    private String Id;
    private String Ma;
    private String ngayTao;
    private String TrangThaiHoaDon;
    private String TenNV;

    public ViewModelHoaDonBanHang(String Id, String Ma, String ngayTao, String TrangThaiHoaDon, String TenNV) {
        this.Id = Id;
        this.Ma = Ma;
        this.ngayTao = ngayTao;
        this.TrangThaiHoaDon = TrangThaiHoaDon;
        this.TenNV = TenNV;
    }

    public ViewModelHoaDonBanHang() {
        
    }

    public String getId() {
        
        return Id;
    }

    public void setId(String Id) {
        
        this.Id = Id;
    }

    public String getMa() {
        
        return Ma;
    }

    public void setMa(String Ma) {
        
        this.Ma = Ma;
    }

    public String getNgayTao() {
        
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        
        this.ngayTao = ngayTao;
    }

    public String getTrangThaiHoaDon() {
        
        return TrangThaiHoaDon;
    }

    public void setTrangThaiHoaDon(String TrangThaiHoaDon) {
        
        this.TrangThaiHoaDon = TrangThaiHoaDon;
    }

    public String getTenNV() {
        
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        
        this.TenNV = TenNV;
    }
    
    
    
    
}
