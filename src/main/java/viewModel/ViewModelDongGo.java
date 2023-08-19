/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author ktkha
 */
public class ViewModelDongGo {
    private String Id;
    private String Ma;
    private String TenLoaiGo;
    private String TrangThai;

    public ViewModelDongGo() {
        
    }

    public ViewModelDongGo(String Id, String Ma, String TenLoaiGo, String TrangThai) {
        this.Id = Id;
        this.Ma = Ma;
        this.TenLoaiGo = TenLoaiGo;
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

    public String getTenLoaiGo() {
        
        return TenLoaiGo;
    }

    public void setTenLoaiGo(String TenLoaiGo) {
        
        this.TenLoaiGo = TenLoaiGo;
    }

    public String getTrangThai() {
        
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        
        return "ViewModelDongGo{" + "Id=" + Id + ", Ma=" + Ma + ", TenLoaiGo=" + TenLoaiGo + ", TrangThai=" + TrangThai + '}';
    }
    
    
}
