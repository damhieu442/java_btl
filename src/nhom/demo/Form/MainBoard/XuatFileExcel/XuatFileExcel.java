/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package nhom.demo.Form.MainBoard.XuatFileExcel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.poi.ss.usermodel.Font;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nhom.demo.Model.SinhVien;
import nhom.demo.Repo.SinhVienRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author hlade
 */
public class XuatFileExcel extends javax.swing.JPanel {
    List<SinhVien> list = SinhVienRepository.readData();
    public static final int COLUMN_INDEX_ID         = 0;
    public static final int COLUMN_INDEX_TITLE      = 1;
    public static final int COLUMN_INDEX_PRICE      = 2;
    public static final int COLUMN_INDEX_QUANTITY   = 3;
    public static final int COLUMN_INDEX_TOTAL      = 4;
    private static CellStyle cellStyleFormatNumber = null;
    /**
     * Creates new form XuatFileExcel
     */
    public XuatFileExcel() {
        initComponents();
    }
    
    private void showTable() {
        int n = list.size();
        Object[][] data = new Object[n][6];
        for (int i= 0;i < n;i++)
        {

            for (int j = 0;j<6;j++)
            {
                if(j == 0)
                {
                    data[i][j] = list.get(i).getMaSv();
                }
                if(j == 1)
                {
                    data[i][j] = list.get(i).getHoTen();
                }
                if(j == 2)
                {
                    data[i][j] = list.get(i).getGioiTinh();
                }
                if(j == 3)
                {
                    data[i][j] = list.get(i).getDiaChi();
                }
                if(j == 4)
                {
                    data[i][j] = list.get(i).getLop();
                }
                if(j == 5)
                {
                    data[i][j] = list.get(i).getNganh();
                }
            }
        }

        jTable1.setModel(new javax.swing.table.DefaultTableModel(data,
                new String [] {
                        "MaSV", "Họ Tên", "Giới Tính", "Địa Chỉ", "Lớp", "Ngành",
                }
        ));
        jScrollPane1.setViewportView(jTable1);
    }
    
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = (Font) sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman"); 
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color
 
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);
         
        // Create row
        Row row = sheet.createRow(rowIndex);
         
        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("MaSV");
 
        cell = row.createCell(COLUMN_INDEX_TITLE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ho ten");
 
        cell = row.createCell(COLUMN_INDEX_PRICE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Lop");
 
        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Gioi tinh");
 
        cell = row.createCell(COLUMN_INDEX_TOTAL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("So tin dk");
    }
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;
 
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
    
    private static void writeFooter(Sheet sheet, int rowIndex) {
        // Create row
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
        cell.setCellFormula("SUM(E2:E6)");
    }
    private static void writeSinhVien(SinhVien sv, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");
             
            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
         
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(sv.getMaSv());
 
        cell = row.createCell(COLUMN_INDEX_TITLE);
        cell.setCellValue(sv.getHoTen());
 
        cell = row.createCell(COLUMN_INDEX_PRICE);
        cell.setCellValue(sv.getLop());
        
 
        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellValue(sv.getGioiTinh());
         
        // Create cell formula
        // totalMoney = price * quantity
        cell = row.createCell(COLUMN_INDEX_TOTAL);
        cell.setCellValue(sv.getEmail());
        cell.setCellStyle(cellStyleFormatNumber);
        int currentRow = row.getRowNum() + 1;
        String columnPrice = CellReference.convertNumToColString(COLUMN_INDEX_PRICE);
        String columnQuantity = CellReference.convertNumToColString(COLUMN_INDEX_QUANTITY);
        cell.setCellFormula(columnPrice + currentRow + "*" + columnQuantity + currentRow);
    }
     public static void writeExcel(List<SinhVien> sinhViens, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);
 
        // Create sheet
        Sheet sheet = workbook.createSheet("Books"); // Create sheet with sheet name
 
        int rowIndex = 0;
         
        // Write header
        writeHeader(sheet, rowIndex);
 
        // Write data
        rowIndex++;
        for (SinhVien sv : sinhViens) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeSinhVien(sv, row);
            rowIndex++;
        }
         
        // Write footer
        writeFooter(sheet, rowIndex);
 
        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
 
        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
      // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
     
    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
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

        btnQLSV = new javax.swing.JButton();
        btnQLHP = new javax.swing.JButton();
        btnAll = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        btnQLSV.setText("Qu?n Lý Sinh Viên");
        btnQLSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLSVActionPerformed(evt);
            }
        });

        btnQLHP.setText("Qu?n Lý H?c Phí");
        btnQLHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLHPActionPerformed(evt);
            }
        });

        btnAll.setText("T?t C?");
        btnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(btnQLSV)
                .addGap(18, 18, 18)
                .addComponent(btnQLHP)
                .addGap(18, 18, 18)
                .addComponent(btnAll)
                .addGap(261, 261, 261))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQLSV)
                    .addComponent(btnQLHP)
                    .addComponent(btnAll))
                .addContainerGap(297, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(76, 76, 76)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(68, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnQLSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLSVActionPerformed
        // TODO add your handling code here:
        showTable();
       
        final String excelFilePath = "C:/Users/hlade/Downloads/sinhvien.xlsx";
        try {
            writeExcel(list, excelFilePath);
        } catch (IOException ex) {
            Logger.getLogger(XuatFileExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnQLSVActionPerformed

    private void btnQLHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLHPActionPerformed
        // TODO add your handling code here:
        showTable();
    }//GEN-LAST:event_btnQLHPActionPerformed

    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
        // TODO add your handling code here:
        showTable();
    }//GEN-LAST:event_btnAllActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
//        // get maSV selected
//        int row = jTable1.getSelectedRow();
//        maSVSelected = jTable1.getValueAt(row, 0).toString();

        //        JTable source = (JTable)evt.getSource();
        //        int row = source.rowAtPoint( evt.getPoint() );
        //
        //        String s=source.getModel().getValueAt(row,0)+"";
        //        System.out.println(s);
        //
        //        SinhVien sinhVien = new SinhVien();
        //        for (SinhVien sinhVien1 : list) {
            //            System.out.println("123");
            //            if(sinhVien1.getMaSv().equals(s)) sinhVien = sinhVien1;
            //        }
        //        System.out.println(sinhVien.getMaSv());
        //
        //
        //        (new MainBoardForm()).showEditForm();
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnQLHP;
    private javax.swing.JButton btnQLSV;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
