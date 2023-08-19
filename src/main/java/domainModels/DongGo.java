/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "DongGo")
public class DongGo implements Serializable{
    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private String Id;
    private String Ma;
    private String TenLoaiGo;
    private int TrangThai;

    @OneToMany(mappedBy = "IdDongGo")
    private Set<ChiTietDoGo> chitietdogos; 
    
    public DongGo(String Id, String Ma, String TenLoaiGo, int TrangThai) {
        this.Id = Id;
        this.Ma = Ma;
        this.TenLoaiGo = TenLoaiGo;
        this.TrangThai = TrangThai;
    }

    public DongGo() {
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

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "DongGo{" + "Id=" + Id + ", Ma=" + Ma + ", TenLoaiGo=" + TenLoaiGo + ", TrangThai=" + TrangThai + '}';
    }

   
    
    
}
