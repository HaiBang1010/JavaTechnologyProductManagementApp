/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.*;
import java.util.*;

public class LoaiDAO {

    public static ArrayList<Loai> layTatCaLoai() {
        ArrayList<Loai> result = new ArrayList<>();
        try {
            Connection conn = DataBaseUtil.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM loai");

            while (rs.next()) {
                String maLoai = rs.getString("ma_loai");
                String tenLoai = rs.getString("ten_loai");

                Loai l = new Loai(maLoai, tenLoai);
                result.add(l);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public static boolean them(Loai l) {
        try {
            Connection conn = DataBaseUtil.getConnection();
            String sql = "INSERT INTO `loai`(`ma_loai`, `ten_loai`) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, l.getMaLoai());
            ps.setString(2, l.getTenLoai());
            
            int kq = ps.executeUpdate();
            return kq==1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean xoa(String maLoai){
        Connection conn;
        try {
            conn = DataBaseUtil.getConnection();
            String sql = "DELETE FROM `loai` WHERE ma_loai = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, maLoai);
            int kq = ps.executeUpdate();
            return kq==1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean sua(Loai loai){
        Connection conn;
        try {
            conn = DataBaseUtil.getConnection();
            String sql = "UPDATE `loai` SET `ten_loai`=? WHERE ma_loai = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, loai.getTenLoai());
            ps.setString(2, loai.getMaLoai());
            int kq = ps.executeUpdate();
            return kq==1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static ArrayList<Loai> timLoaiTheoTen(String tenLoai){
        ArrayList<Loai> result = new ArrayList<>();
        try {
            Connection conn = DataBaseUtil.getConnection();
            String sql = "SELECT * FROM loai WHERE `ten_loai` like '%"+tenLoai+"%'";

            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String ml = rs.getString("ma_loai");
                String tl = rs.getString("ten_loai");

                Loai l = new Loai(ml, tl);
                result.add(l);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
