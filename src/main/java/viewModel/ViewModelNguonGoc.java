/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author Phuong Bi
 */
public class ViewModelNguonGoc {
    private String id;
    private String ma;
    private String quocgia;
  
    private String trangThai;

    public ViewModelNguonGoc() {
        
    }

    public ViewModelNguonGoc(String id, String ma, String quocgia, String trangThai) {
        this.id = id;
        this.ma = ma;
       
        this.quocgia = quocgia;
        this.trangThai = trangThai;
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

    public String getquocgia() {
        
        return quocgia;
    }

    public void setquocgia(String quocgia) {
        
        this.quocgia = quocgia;
    }

  
    public String getTrangThai() {
        
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        
        return "ViewModelNguonGoc{" + "id=" + id + ", ma=" + ma + ", quocgia=" + quocgia + ", trangThai=" + trangThai + '}';
    }

   
    
    
}
