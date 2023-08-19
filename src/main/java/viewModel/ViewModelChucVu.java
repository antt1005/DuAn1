/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author Phuong Bi
 */
public class ViewModelChucVu {
    private String Id;
    private String Ma;
    private String Ten;
    private String TrangThai;

    public ViewModelChucVu() {
        
    }

    public ViewModelChucVu(String Id, String Ma, String Ten, String TrangThai) {
        this.Id = Id;
        this.Ma = Ma;
        this.Ten = Ten;
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

    public String getTen() {
        
        return Ten;
    }

    public void setTen(String Ten) {
        
        this.Ten = Ten;
    }

    public String getTrangThai() {
        
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        
        return "ViewModelChucVu{" + "Id=" + Id + ", Ma=" + Ma + ", Ten=" + Ten + ", TrangThai=" + TrangThai + '}';
    }
    
    
    
    
}
