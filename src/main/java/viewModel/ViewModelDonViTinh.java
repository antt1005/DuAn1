/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author Admin
 */
public class ViewModelDonViTinh {
    private String Id;
    private String Ma;
    private String DonViTinh;
    private String TrangThai;

    public ViewModelDonViTinh() {
    }

    public ViewModelDonViTinh(String Id, String Ma, String DonViTinh, String TrangThai) {
        this.Id = Id;
        this.Ma = Ma;
        this.DonViTinh = DonViTinh;
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

    public String getDonViTinh() {
        
        return DonViTinh;
    }

    public void setDonViTinh(String DonViTinh) {
        
        this.DonViTinh = DonViTinh;
    }

    public String getTrangThai() {
        
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        
        return "ViewModelDonViTinh{" + "Id=" + Id + ", Ma=" + Ma + ", DonViTinh=" + DonViTinh + ", TrangThai=" + TrangThai + '}';
    }
    
    
    
}
