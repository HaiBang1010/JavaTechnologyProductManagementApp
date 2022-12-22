/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.SanPham;
import java.util.*;

public class SanPhamModel {
     public  boolean them(SanPham s) {
        return DAO.SanPhamDAO.them(s);
    }
    
    public  boolean xoa(int maSanPham){
        return DAO.SanPhamDAO.xoa(maSanPham);
    }
    
    public  boolean sua(SanPham s){
        return DAO.SanPhamDAO.sua(s);
    }
    
    public  ArrayList<SanPham> layTatCaSanPham(){
        return DAO.SanPhamDAO.layDanhSachSanPham();
    }
    
     public  ArrayList<SanPham> laySanPhamTheoLoai(String maLoai){
        return DAO.SanPhamDAO.laySanPhamTheoLoai(maLoai);
    }
    
    public  ArrayList<SanPham> timSanPhamTheoTen(String tenSanPham){
        return DAO.SanPhamDAO.timSanPhamTheoTen(tenSanPham);
    }
    
    public  ArrayList<SanPham> sapXepTheoTen( ArrayList<SanPham> spList){
    
     ArrayList<SanPham> result = (ArrayList<SanPham>) spList.clone();
        Collections.sort(result, new Comparator <SanPham> (){
            @Override
            public int  compare(SanPham s1, SanPham s2){
                return s1.getTenSanPham().compareTo(s2.getTenSanPham());
            }
        });
        return result;
    }
    
    public  ArrayList<SanPham> sapXepTheoLoaiRoiTheoGiaBan(ArrayList<SanPham> spList){
        ArrayList<SanPham> result = (ArrayList<SanPham>) spList.clone();
        Collections.sort(result, new Comparator<SanPham>(){
            @Override
            public int compare(SanPham s1, SanPham s2){
                return Double.compare(s1.getGiaBan(), s2.getGiaBan());
            }        
        });
        Collections.sort(result, new Comparator<SanPham>(){
            @Override
            public int compare(SanPham s1, SanPham s2){
                return s1.getMaLoai().compareTo(s2.getMaLoai());
            }        
        });            
        return result; 
    }
}
