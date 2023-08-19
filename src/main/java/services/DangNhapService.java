/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.NhanVien;
import java.util.List;
import repositories.DangNhapRepository;

/**
 *
 * @author Admin
 */
public class DangNhapService {

    private DangNhapRepository i = new DangNhapRepository();

    public List<NhanVien> getTK() {
        try {
            return i.getTK();
        } catch (Exception e) {
            return null;
        }
    }
}
