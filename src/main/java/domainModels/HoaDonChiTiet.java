/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTiet implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "IdChiTietDoGo")
    private ChiTietDoGo IdChiTietDoGo;

    @Id
    @ManyToOne
    @JoinColumn(name = "IdHoaDon")
    private HoaDon IdHoaDon;

    private int SoLuong;
    private BigDecimal DonGia;
    private int Luot;

    public HoaDonChiTiet(ChiTietDoGo IdChiTietDoGo, HoaDon IdHoaDon, int SoLuong, BigDecimal DonGia, int Luot) {
        this.IdChiTietDoGo = IdChiTietDoGo;
        this.IdHoaDon = IdHoaDon;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.Luot = Luot;
    }

    public HoaDonChiTiet() {
    }

    public ChiTietDoGo getIdChiTietDoGo() {
        return IdChiTietDoGo;
    }

    public void setIdChiTietDoGo(ChiTietDoGo IdChiTietDoGo) {
        this.IdChiTietDoGo = IdChiTietDoGo;
    }

    public HoaDon getIdHoaDon() {
        return IdHoaDon;
    }

    public void setIdHoaDon(HoaDon IdHoaDon) {
        this.IdHoaDon = IdHoaDon;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public BigDecimal getDonGia() {
        return DonGia;
    }

    public void setDonGia(BigDecimal DonGia) {
        this.DonGia = DonGia;
    }

    public int getLuot() {
        return Luot;
    }

    public void setLuot(int Luot) {
        this.Luot = Luot;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "IdChiTietDoGo=" + IdChiTietDoGo + ", IdHoaDon=" + IdHoaDon + ", SoLuong=" + SoLuong + ", DonGia=" + DonGia + ", Luot=" + Luot + '}';
    }

}
