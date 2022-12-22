/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.SanPhamModel;
import model.LoaiModel;
import view.SanPhamView;
import DAO.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bang
 */
public class SanPhamController {

    SanPhamModel model;
    SanPhamView view;
    private String statusbtn = "";

    public SanPhamController(SanPhamModel model, SanPhamView view) {
        this.model = model;
        this.view = view;
        init();
        view.setVisible(true);
        setDefaultForm();
    }

    public boolean getVisible() {
        return view.isVisible();
    }

    public void setVisible(boolean value) {
        view.setVisible(value);
    }

    public SanPhamController() {
        view = new SanPhamView();
        model = new SanPhamModel();
        init();
        setDefaultForm();
    }

    private void init() {
        showTable();
        showComboBox();

        view.getCb_SapXep().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<SanPham> spList = new ArrayList<>();
                int indexSapXep = view.getCb_SapXep().getSelectedIndex();
                if (view.getCb_LocTheoLoai().getSelectedItem().toString() != "Mặc định") {
                    spList = model.laySanPhamTheoLoai(view.getCb_LocTheoLoai().getSelectedItem().toString());
                } else {
                    spList = model.layTatCaSanPham();
                }
                if (view.getCb_SapXep().getItemAt(indexSapXep).equals("Theo tên sản phẩm")) {
                    spList = model.sapXepTheoTen(spList);
                } else if (view.getCb_SapXep().getItemAt(indexSapXep).equals("Theo loại rồi theo giá")) {
                    spList = model.sapXepTheoLoaiRoiTheoGiaBan(spList);
                } else {
                    if (view.getCb_LocTheoLoai().getSelectedItem().toString() == "Mặc định") {

                        spList = model.layTatCaSanPham();
                    }
                }

                DefaultTableModel tableModel = (DefaultTableModel) view.getTbl_SanPham().getModel();
                tableModel.setRowCount(0);

                for (SanPham sp : spList) {
                    tableModel.addRow(new Object[]{sp.getMaSanPham(), sp.getTenSanPham(), sp.getMaLoai(), sp.getSoLuong(), sp.getGiaBan()});
                }
                view.getTbl_SanPham().setModel(tableModel);

            }
        });

        view.getCb_LocTheoLoai().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<SanPham> spList = new ArrayList<>();
                int indexSapXep = view.getCb_SapXep().getSelectedIndex();
                if (view.getCb_LocTheoLoai().getSelectedItem().toString() != "Mặc định") {
                    spList = model.laySanPhamTheoLoai(view.getCb_LocTheoLoai().getSelectedItem().toString());
                } else {
                    spList = model.layTatCaSanPham();
                }
                if (view.getCb_SapXep().getItemAt(indexSapXep).equals("Theo tên sản phẩm")) {
                    spList = model.sapXepTheoTen(spList);
                } else if (view.getCb_SapXep().getItemAt(indexSapXep).equals("Theo loại rồi theo giá")) {
                    spList = model.sapXepTheoLoaiRoiTheoGiaBan(spList);
                } else {
                    if (view.getCb_LocTheoLoai().getSelectedItem().toString() == "Mặc định") {

                        spList = model.layTatCaSanPham();
                    }
                }

                DefaultTableModel tableModel = (DefaultTableModel) view.getTbl_SanPham().getModel();
                tableModel.setRowCount(0);

                for (SanPham sp : spList) {
                    tableModel.addRow(new Object[]{sp.getMaSanPham(), sp.getTenSanPham(), sp.getMaLoai(), sp.getSoLuong(), sp.getGiaBan()});
                }
                view.getTbl_SanPham().setModel(tableModel);
            }
        });

        view.getTbl_SanPham().addMouseListener(TableSanPhamListener());

        view.getBtn_Moi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultForm();
                view.getBtn_Huy().setEnabled(true);
                view.getBtn_Luu().setEnabled(true);
                view.getTxt_TenSP().setEnabled(true);
                view.getjSpinnerGiaBan().setEnabled(true);
                view.getjSpinnerSoLuong().setEnabled(true);
                view.getBtn_Moi().setEnabled(false);
                view.getBtn_Sua().setEnabled(false);
                view.getBtn_Xoa().setEnabled(false);
                statusbtn = "them";
            }
        });

        view.getBtn_Luu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (statusbtn == "them") {
                    String tenSP = view.getTxt_TenSP().getText();
                    String maLoai = (String) view.getCb_MaLoai().getSelectedItem();
                    int soLuong = (int) view.getjSpinnerSoLuong().getValue();
                    double giaBan = Double.parseDouble(String.valueOf(view.getjSpinnerSoLuong().getValue()));
                    SanPham sp = new SanPham(tenSP, maLoai, soLuong, giaBan);
                    model.them(sp);
                    setDefaultForm();
                    showTable();
                } else if (statusbtn == "sua") {
                    int maSP = Integer.parseInt(view.getTxt_MaSP().getText());
                    String tenSP = view.getTxt_TenSP().getText();
                    String maLoai = (String) view.getCb_MaLoai().getSelectedItem();
                    int soLuong = (int) view.getjSpinnerSoLuong().getValue();
                    double giaBan = Double.parseDouble(String.valueOf(view.getjSpinnerGiaBan().getValue()));
                    SanPham sp = new SanPham(maSP, tenSP, maLoai, soLuong, giaBan);
                    model.sua(sp);
                    setDefaultForm();
                    showTable();
                }
            }
        });

        view.getBtn_Huy().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultForm();
            }
        });

        view.getBtn_Sua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getBtn_Huy().setEnabled(true);
                view.getBtn_Luu().setEnabled(true);
                view.getTxt_TenSP().setEnabled(true);
                view.getjSpinnerGiaBan().setEnabled(true);
                view.getjSpinnerSoLuong().setEnabled(true);
                view.getBtn_Moi().setEnabled(false);
                view.getBtn_Sua().setEnabled(false);
                view.getBtn_Xoa().setEnabled(false);
                statusbtn = "sua";
            }
        });

        view.getBtn_Xoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(view, "Bạn có muốn xóa mã loại " + view.getTxt_MaSP().getText() + " không?");
                if (confirm == JOptionPane.YES_OPTION) {
                    int maSP = Integer.parseInt(view.getTxt_MaSP().getText());
                    model.xoa(maSP);
                    setDefaultForm();
                    showTable();
                }
            }
        });

        view.getBtn_Tim().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenCanTim = view.getTxt_Tim().getText();
                DefaultTableModel tableModel = (DefaultTableModel) view.getTbl_SanPham().getModel();
                tableModel.setRowCount(0);

                ArrayList<SanPham> dsSanPham = model.timSanPhamTheoTen(tenCanTim);
                for (SanPham sp : dsSanPham) {
                    tableModel.addRow(new Object[]{
                        sp.getMaSanPham(),
                        sp.getTenSanPham(),
                        sp.getMaLoai(),
                        sp.getSoLuong(),
                        sp.getGiaBan()});
                }
                view.getTbl_SanPham().setModel(tableModel);
            }
        });
    }

    private void setDefaultForm() {
        view.getTxt_MaSP().setEnabled(false);
        view.getTxt_TenSP().setEnabled(false);
        view.getjSpinnerGiaBan().setEnabled(false);
        view.getjSpinnerSoLuong().setEnabled(false);

        view.getTxt_MaSP().setText("");
        view.getTxt_TenSP().setText("");
        view.getjSpinnerGiaBan().setValue(0);
        view.getjSpinnerSoLuong().setValue(0);

        view.getBtn_Luu().setEnabled(false);
        view.getBtn_Huy().setEnabled(false);
        view.getBtn_Xoa().setEnabled(false);
        view.getBtn_Sua().setEnabled(false);
        view.getBtn_Moi().setEnabled(true);
    }

    private void showTable() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTbl_SanPham().getModel();
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);

        tableModel.addColumn("Mã sản phẩm");
        tableModel.addColumn("Tên sản phẩm");
        tableModel.addColumn("Mã loại");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giá bán($)");

        ArrayList<SanPham> dsSanPham = model.layTatCaSanPham();
        for (SanPham sp : dsSanPham) {
            tableModel.addRow(new Object[]{sp.getMaSanPham(), sp.getTenSanPham(), sp.getMaLoai(), sp.getSoLuong(), sp.getGiaBan()});
        }
        view.getTbl_SanPham().setModel(tableModel);
    }

    private void showComboBox() {
        // combobox mã loại
        LoaiModel lModel = new LoaiModel();
        ArrayList<Loai> dsLoai = lModel.layTatCaLoai();
        view.getCb_LocTheoLoai().addItem("Mặc định");
        for (Loai l : dsLoai) {
            view.getCb_MaLoai().addItem(l.getMaLoai());
            view.getCb_LocTheoLoai().addItem(l.getMaLoai());
        }

        // combobox tieu chi sap xep
        view.getCb_SapXep().addItem("Mặt định");
        view.getCb_SapXep().addItem("Theo tên sản phẩm");
        view.getCb_SapXep().addItem("Theo loại rồi theo giá");

    }

    private MouseListener TableSanPhamListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setDefaultForm();
                int index = view.getTbl_SanPham().getSelectedRow();

                view.getTxt_MaSP().setText(String.valueOf(view.getTbl_SanPham().getValueAt(index, 0)));
                view.getTxt_TenSP().setText((String) view.getTbl_SanPham().getValueAt(index, 1));
                view.getCb_MaLoai().setSelectedItem((String) view.getTbl_SanPham().getValueAt(index, 2));
                view.getjSpinnerSoLuong().setValue((int) view.getTbl_SanPham().getValueAt(index, 3));
                view.getjSpinnerGiaBan().setValue((double) view.getTbl_SanPham().getValueAt(index, 4));

                view.getBtn_Sua().setEnabled(true);
                view.getBtn_Xoa().setEnabled(true);
                view.getBtn_Moi().setEnabled(true);
                view.getBtn_Huy().setEnabled(false);
                view.getBtn_Luu().setEnabled(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }

}
