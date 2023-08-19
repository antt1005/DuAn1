/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "LichSuNhap")
public class LichSuNhap implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "IdNhap", columnDefinition = "uniqueidentifier")
    private String IdNhap;

    @ManyToOne
    @JoinColumn(name = "IdSpNhap")
    private ChiTietDoGo IdSpNhap;

    private int SoLongNhap;
    private Date NgayNhap;
    private int Trangthai;
    private BigDecimal TongTienNhap;

    public LichSuNhap(String IdNhap, ChiTietDoGo IdSpNhap, int SoLongNhap, Date NgayNhap, int Trangthai, BigDecimal TongTienNhap) {
        this.IdNhap = IdNhap;
        this.IdSpNhap = IdSpNhap;
        this.SoLongNhap = SoLongNhap;
        this.NgayNhap = NgayNhap;
        this.Trangthai = Trangthai;
        this.TongTienNhap = TongTienNhap;
    }

    public LichSuNhap() {
    }

    public String getIdNhap() {
        return IdNhap;
    }

    public void setIdNhap(String IdNhap) {
        this.IdNhap = IdNhap;
    }

    public ChiTietDoGo getIdSpNhap() {
        return IdSpNhap;
    }

    public void setIdSpNhap(ChiTietDoGo IdSpNhap) {
        this.IdSpNhap = IdSpNhap;
    }

    public int getSoLongNhap() {
        return SoLongNhap;
    }

    public void setSoLongNhap(int SoLongNhap) {
        this.SoLongNhap = SoLongNhap;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public int getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(int Trangthai) {
        this.Trangthai = Trangthai;
    }

    public BigDecimal getTongTienNhap() {
        return TongTienNhap;
    }

    public void setTongTienNhap(BigDecimal TongTienNhap) {
        this.TongTienNhap = TongTienNhap;
    }

    @Override
    public String toString() {
        return "LichSuNhap{" + "IdNhap=" + IdNhap + ", IdSpNhap=" + IdSpNhap + ", SoLongNhap=" + SoLongNhap + ", NgayNhap=" + NgayNhap + ", Trangthai=" + Trangthai + ", TongTienNhap=" + TongTienNhap + '}';
    }

}
