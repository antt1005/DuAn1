/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "BaoHanh")
public class BaoHanh implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private String Id;
    private String Ma;
    @ManyToOne
    @JoinColumn(name = "IdSPBaoHanh")
    private SanPham IdSPBaoHanh;
    @ManyToOne
    @JoinColumn(name = "IdKH")
    private KhachHang IdKH;
    private int TrangThai;
    private Date ThoiGian;

    public BaoHanh(String Id, String Ma, SanPham IdSPBaoHanh, KhachHang IdKH, int TrangThai, Date ThoiGian) {
        this.Id = Id;
        this.Ma = Ma;
        this.IdSPBaoHanh = IdSPBaoHanh;
        this.IdKH = IdKH;
        this.TrangThai = TrangThai;
        this.ThoiGian = ThoiGian;
    }

    public BaoHanh() {
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

    public SanPham getIdSPBaoHanh() {
        return IdSPBaoHanh;
    }

    public void setIdSPBaoHanh(SanPham IdSPBaoHanh) {
        this.IdSPBaoHanh = IdSPBaoHanh;
    }

    public KhachHang getIdKH() {
        return IdKH;
    }

    public void setIdKH(KhachHang IdKH) {
        this.IdKH = IdKH;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Date getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(Date ThoiGian) {
        this.ThoiGian = ThoiGian;
    }

    @Override
    public String toString() {
        return "BaoHanh{" + "Id=" + Id + ", Ma=" + Ma + ", IdSPBaoHanh=" + IdSPBaoHanh + ", IdKH=" + IdKH + ", TrangThai=" + TrangThai + ", ThoiGian=" + ThoiGian + '}';
    }

}
