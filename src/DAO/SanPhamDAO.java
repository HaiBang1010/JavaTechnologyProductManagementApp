/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.*;

public class SanPhamDAO {

    public static ArrayList<SanPham> layDanhSachSanPham() {

        ArrayList<SanPham> result = new ArrayList<>();
        try {
            Connection conn = DataBaseUtil.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM sanpham");

            while (rs.next()) {
                int maSP = rs.getInt("ma_sp");
                String tenSP = rs.getString("ten_sp");
                String maL = rs.getString("ma_loai");
                int sl = rs.getInt("so_luong");
                double gia = rs.getDouble("gia_ban");

                SanPham s = new SanPham(maSP, tenSP, maL, sl, gia);
                result.add(s);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public static boolean them(SanPham s) {
        try {
            Connection conn = DataBaseUtil.getConnection();
            String sql = "INSERT INTO `sanpham`( `ten_sp`, `ma_loai`, `so_luong`, `gia_ban`) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getTenSanPham());
            ps.setString(2, s.getMaLoai());
            ps.setInt(3, s.getSoLuong());
            ps.setDouble(4, s.getGiaBan());

            int kq = ps.executeUpdate();
            return kq == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean xoa(int maSanPham) {
        try {
            Connection con = DataBaseUtil.getConnection();
            String sql = "DELETE FROM `sanpham` WHERE  ma_sp = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maSanPham);

            int kq = ps.executeUpdate();
            return kq == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public static boolean sua(SanPham s) {
        try {
            Connection conn = DataBaseUtil.getConnection();
            String sql = "UPDATE `sanpham` SET `ten_sp`= ?,`ma_loai`=?,`so_luong`=?,`gia_ban`=? WHERE ma_sp = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getTenSanPham());
            ps.setString(2, s.getMaLoai());
            ps.setInt(3, s.getSoLuong());
            ps.setDouble(4, s.getGiaBan());
            ps.setInt(5, s.getMaSanPham());
            int kq = ps.executeUpdate();

            return kq == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public static ArrayList<SanPham> timSanPhamTheoTen(String tenSanPham) {

        ArrayList<SanPham> result = new ArrayList<>();
        try {
            Connection conn = DataBaseUtil.getConnection();
            String sql = "SELECT * FROM sanpham where ten_sp like '%" + tenSanPham + "%'";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                int maSP = rs.getInt("ma_sp");
                String tenSP = rs.getString("ten_sp");
                String maL = rs.getString("ma_loai");
                int sl = rs.getInt("so_luong");
                double gia = rs.getDouble("gia_ban");

                SanPham s = new SanPham(maSP, tenSP, maL, sl, gia);
                result.add(s);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public static ArrayList<SanPham> laySanPhamTheoLoai(String maLoai) {

        ArrayList<SanPham> result = new ArrayList<>();
        try {
            Connection conn = DataBaseUtil.getConnection();
            String sql = "SELECT * FROM sanpham where ma_loai like '%" + maLoai + "%'";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                int maSP = rs.getInt("ma_sp");
                String tenSP = rs.getString("ten_sp");
                String maL = rs.getString("ma_loai");
                int sl = rs.getInt("so_luong");
                double gia = rs.getDouble("gia_ban");

                SanPham s = new SanPham(maSP, tenSP, maL, sl, gia);
                result.add(s);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
