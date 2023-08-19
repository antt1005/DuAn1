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
@Table(name = "DonViTinh")
public class DonViTinh implements Serializable{
    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private String Id;
    private String Ma;
    private String DonViTinh;
    private int TrangThai;
   
   @OneToMany(mappedBy = "IdDonViTinh")
    private Set<ChiTietDoGo> chitietdogos;  
    
    public DonViTinh(String Id, String Ma, String DonViTinh, int TrangThai) {
        this.Id = Id;
        this.Ma = Ma;
        this.DonViTinh = DonViTinh;
        this.TrangThai = TrangThai;
    }

    public DonViTinh() {
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

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "DonViTinh{" + "Id=" + Id + ", Ma=" + Ma + ", DonViTinh=" + DonViTinh + ", TrangThai=" + TrangThai + '}';
    }

   
    
    
}
