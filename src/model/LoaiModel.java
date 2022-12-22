/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.Loai;
import java.util.*;

/**
 *
 * @author Bang
 */
public class LoaiModel {
    
     public  boolean them(Loai l) {
        return DAO.LoaiDAO.them(l);
    }

    public  boolean xoa(String maLoai) {
        return DAO.LoaiDAO.xoa(maLoai);
    }

    public  boolean sua(Loai l) {
        return DAO.LoaiDAO.sua(l);
    }

    public  ArrayList<Loai> layTatCaLoai() {
        return DAO.LoaiDAO.layTatCaLoai();
    }

    public  ArrayList<Loai> timLoaiTheoten(String tenLoai) {
        return DAO.LoaiDAO.timLoaiTheoTen(tenLoai);
    }
    
}
