/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Bang
 */
public class SanPham {

    private int maSanPham;
    private String tenSanPham;
    private String maLoai;
    private int soLuong;
    private double giaBan;

    @Override
    public String toString() {
        return "SanPham{" + "maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", maLoai=" + maLoai + ", soLuong=" + soLuong + ", giaBan=" + giaBan + '}';
    }

    public SanPham() {
    }

    public SanPham(String tenSanPham, String maLoai, int soLuong, double giaBan) {
        this.tenSanPham = tenSanPham;
        this.maLoai = maLoai;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    public SanPham(int maSanPham, String tenSanPham, String maLoai, int soLuong, double giaBan) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maLoai = maLoai;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

}
