/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Bang
 */
import DAO.Loai;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.LoaiModel;
import view.LoaiView;

public class LoaiController {

    LoaiModel model;
    LoaiView view;
    private String statusbtn = "";

    public LoaiController(LoaiModel model, LoaiView view) {
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

    public LoaiController() {
        view = new LoaiView();
        model = new LoaiModel();
        init();
        setDefaultForm();
    }

    private void setDefaultForm() {
        view.getTxt_MaLoai().setText("");
        view.getTxt_TenLoai().setText("");
        view.getTxt_MaLoai().setEnabled(false);
        view.getTxt_TenLoai().setEnabled(false);

        view.getBtn_Luu().setEnabled(false);
        view.getBtn_Huy().setEnabled(false);
        view.getBtn_Xoa().setEnabled(false);
        view.getBtn_Sua().setEnabled(false);
        view.getBtn_Moi().setEnabled(true);
    }

    private void init() {
        showTable();

        view.getTbl_Loai().addMouseListener(TableLoaiListener());

        view.getBtn_Tim().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenCanTim = view.getTxt_Tim().getText();
                DefaultTableModel tableModel = (DefaultTableModel) view.getTbl_Loai().getModel();
                tableModel.setRowCount(0);
                ArrayList<Loai> dsLoai = model.timLoaiTheoten(tenCanTim);
                for (Loai l : dsLoai) {
                    tableModel.addRow(new Object[]{l.getMaLoai(), l.getTenLoai()});
                }
                view.getTbl_Loai().setModel(tableModel);
            }
        });

        view.getBtn_Luu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (statusbtn == "them") {

                    String maLoai = view.getTxt_MaLoai().getText();
                    String tenLoai = view.getTxt_TenLoai().getText();
                    model.them(new Loai(maLoai, tenLoai));

                    setDefaultForm();
                    showTable();
                    statusbtn = "";
                } else if (statusbtn == "sua") {
                    String maLoai = view.getTxt_MaLoai().getText();
                    String tenLoai = view.getTxt_TenLoai().getText();
                    model.sua(new Loai(maLoai, tenLoai));

                    setDefaultForm();
                    showTable();
                    statusbtn = "";
                }
            }
        });

        view.getBtn_Huy().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultForm();
            }
        });

        view.getBtn_Xoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int confirm = JOptionPane.showConfirmDialog(view, "Bạn có muốn xóa mã loại " + view.getTxt_MaLoai().getText() + " không?");
                if (confirm == JOptionPane.YES_OPTION) {
                    String maLoai = view.getTxt_MaLoai().getText();
                    model.xoa(maLoai);
                    setDefaultForm();
                    showTable();
                }

            }
        });

        view.getBtn_Sua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                view.getTxt_TenLoai().setEnabled(true);
                view.getBtn_Huy().setEnabled(true);
                view.getBtn_Luu().setEnabled(true);
                view.getBtn_Moi().setEnabled(false);
                view.getBtn_Xoa().setEnabled(false);
                view.getBtn_Sua().setEnabled(false);

                statusbtn = "sua";
                showTable();
            }
        });

        view.getBtn_Moi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTxt_MaLoai().setText("");
                view.getTxt_TenLoai().setText("");
                view.getTxt_MaLoai().setEnabled(true);
                view.getTxt_TenLoai().setEnabled(true);
                statusbtn = "them";
                view.getBtn_Luu().setEnabled(true);
                view.getBtn_Huy().setEnabled(true);
                view.getBtn_Sua().setEnabled(false);
                view.getBtn_Xoa().setEnabled(false);
                view.getBtn_Moi().setEnabled(false);
            }
        });

    }

    private void showTable() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTbl_Loai().getModel();
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);

        tableModel.addColumn("Mã Loại");
        tableModel.addColumn("Tên Loại");

        ArrayList<Loai> dsLoai = model.layTatCaLoai();
        for (Loai l : dsLoai) {
            tableModel.addRow(new Object[]{l.getMaLoai(), l.getTenLoai()});
        }

        view.getTbl_Loai().setModel(tableModel);
    }

    private MouseListener TableLoaiListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = view.getTbl_Loai().getSelectedRow();
                view.getTxt_MaLoai().setText((String) view.getTbl_Loai().getValueAt(index, 0));
                view.getTxt_TenLoai().setText((String) view.getTbl_Loai().getValueAt(index, 1));
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
