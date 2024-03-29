/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import BLL.DepartmentBLL;
import DAL.DBConnect.ConnectXamppMySQL;
import DTO.DepartmentDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class JInternalFrameDepartmentList extends javax.swing.JInternalFrame {
    private DepartmentBLL departmentBLL;
    DefaultTableModel model = new DefaultTableModel();
    
    public JInternalFrameDepartmentList() {
        initComponents();
        departmentBLL = new DepartmentBLL();
        loadData();
    }
    
    public void outModel(DefaultTableModel model, ArrayList<DepartmentDTO> listDepartment) {
        Vector row;
        model.setRowCount(0);
        for (DepartmentDTO departmentDTO : listDepartment) {
            row = new Vector();
            row.add(departmentDTO.getDepartmentID());
            row.add(departmentDTO.getName());
            row.add(departmentDTO.getBudget());
            row.add(departmentDTO.getStartDate());
            row.add(departmentDTO.getAdministrator());
            model.addRow(row);
        }
        tableDepartmentList.setModel(model);
    }
    
    private void loadData() {
        try {
            Vector header = new Vector();
            header.add("Mã khoa");
            header.add("Tên khoa");
            header.add("Ngân sách");
            header.add("Ngày bắt đầu");
            header.add("Quản trị viên");

            if (model.getRowCount() == 0) {
                model = new DefaultTableModel(header, 0);
            }

            ArrayList<DepartmentDTO> listDepartment = departmentBLL.getListDepartment(departmentBLL);
            if (listDepartment != null) {
                outModel(model, listDepartment);
                tableDepartmentList.setModel(model);
            } else {
                System.out.println("dữ liệu trống");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        comboboxFind = new javax.swing.JComboBox<>();
        buttonFind = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        textFind = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDepartmentList = new javax.swing.JTable();
        buttonClose = new javax.swing.JButton();

        jLabel1.setText("DANH SÁCH KHOA");

        jLabel3.setText("Thông tin tìm kiếm");

        comboboxFind.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã khoa", "Tên khoa", "Ngân sách", "Ngày bắt đầu", "Quản trị viên", " " }));

        buttonFind.setText("Lọc");

        jLabel4.setText("Nhập tìm kiếm");

        textFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboboxFind, 0, 130, Short.MAX_VALUE)
                    .addComponent(textFind))
                .addGap(18, 18, 18)
                .addComponent(buttonFind)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboboxFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonFind)))
                .addGap(280, 280, 280))
        );

        tableDepartmentList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã khoa", "Tên khoa", "Ngân sách", "Quản trị viên", "Ngày bắt đầu"
            }
        ));
        jScrollPane1.setViewportView(tableDepartmentList);

        buttonClose.setText("Đóng");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(168, 168, 168))
            .addGroup(layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(buttonClose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonClose)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFindActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFindActionPerformed

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        setVisible(false);
    }//GEN-LAST:event_buttonCloseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonFind;
    private javax.swing.JComboBox<String> comboboxFind;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDepartmentList;
    private javax.swing.JTextField textFind;
    // End of variables declaration//GEN-END:variables
}
