/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author admin
 */
public class ViewModelLoaiSanPham {
    private String Id;
    private String Ma;
    private String TenDongSP;
    private String TrangThai;

    public ViewModelLoaiSanPham() {
        
    }

    public ViewModelLoaiSanPham(String Id, String Ma, String TenDongSP, String TrangThai) {
        this.Id = Id;
        this.Ma = Ma;
        this.TenDongSP = TenDongSP;
        this.TrangThai = TrangThai;
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

    public String getTenDongSP() {
        
        return TenDongSP;
    }

    public void setTenDongSP(String TenDongSP) {
        
        this.TenDongSP = TenDongSP;
    }

    public String getTrangThai() {
        
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        
        return "ViewModelLoaiSanPham{" + "Id=" + Id + ", Ma=" + Ma + ", TenDongSP=" + TenDongSP + ", TrangThai=" + TrangThai + '}';
    }
    
    
}
