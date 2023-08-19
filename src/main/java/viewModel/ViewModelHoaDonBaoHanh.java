/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author ktkha
 */
public class ViewModelHoaDonBaoHanh {
    private String id;
    private String ngayTao;
    private String tenNV;
    private String tenKH;
    private int trangThai;

    public ViewModelHoaDonBaoHanh() {
    }

    public ViewModelHoaDonBaoHanh(String id, String ngayTao, String tenNV, String tenKH, int trangThai) {
        this.id = id;
        this.ngayTao = ngayTao;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.trangThai = trangThai;
    }

    public String getId() {
        
        return id;
    }

    public void setId(String id) {
        
        this.id = id;
    }

    public String getNgayTao() {
        
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        
        this.ngayTao = ngayTao;
    }

    public String getTenNV() {
        
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        
        this.tenNV = tenNV;
    }

    public String getTenKH() {
        
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        
        this.tenKH = tenKH;
    }

    public int getTrangThai() {
        
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        
        return "ViewModelHoaDonBaoHanh{" + "id=" + id + ", ngayTao=" + ngayTao + ", tenNV=" + tenNV + ", tenKH=" + tenKH + ", trangThai=" + trangThai + '}';
    }
    
    
}
