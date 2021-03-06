/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leximir.delac.menu;

import helper.DelasHelper;
import helper.GridHelper;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import leximir.EditorDelac;
import model.StaticValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import util.CompoundsUtils;
import util.Utils;

/**
 *
 * @author Rojo Rabelisoa
 */
public class MenuDelac extends javax.swing.JFrame {

    private EditorDelac editorDelac;
    private Object[] obj;
    private int valueSelected;
    private DefaultTableModel tableModel;
    private boolean edit=false;
    int idedit=0;

    /**
     * Creates new form MenuAddBeforeDelac
     */
    public MenuDelac() {
        initComponents();
    }

    public MenuDelac(EditorDelac aThis,String menuSelected, Object[] obj, int selectedRow) {
        initComponents();
        DefaultTableCellRenderer color = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object valueLemaAll, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, valueLemaAll, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                c.setForeground(Color.black);
                return c;
            }
        };
        jMenuPrediction.setVisible(false);
        editorDelac = aThis;
        this.obj = obj;
        switch (menuSelected) {
            case "insertBefore":
                this.valueSelected = selectedRow;
                this.idedit = (int) obj[7];
                break;
            case "insertAfter":
                this.valueSelected = selectedRow + 1;
                this.idedit = ((int) obj[7])+1;
                break;
            case "copyBefore":
                jTextFieldLema.setText((String) this.obj[2]);
                jTextFieldLemaAll.setText((String) this.obj[1]);
                jTextFieldComment.setText((String) this.obj[5]);
                this.valueSelected = selectedRow;
                this.idedit = (int) obj[7];
                completeJtableDelaf((String) this.obj[1]);
                break;
            case "copyAfter":
                jTextFieldLema.setText((String) this.obj[2]);
                jTextFieldLemaAll.setText((String) this.obj[1]);
                jTextFieldComment.setText((String) this.obj[5]);
                this.valueSelected = selectedRow + 1;
                this.idedit = ((int) obj[7])+1;
                completeJtableDelaf((String) this.obj[1]);
                break;
            case "view":
                jTextFieldLema.setText((String) this.obj[2]);
                jTextFieldLemaAll.setText((String) this.obj[1]);
                jTextFieldComment.setText((String) this.obj[5]);
                jMenuSave.setVisible(false);
                completeJtableDelaf((String) this.obj[1]);
                break;
            case "edit":
                edit=true;
                jTextFieldLema.setText((String) this.obj[2]);
                jTextFieldLemaAll.setText((String) this.obj[1]);
                jTextFieldComment.setText((String) this.obj[5]);
                this.valueSelected = selectedRow;
                this.idedit = (int) obj[7];
                completeJtableDelaf((String) this.obj[1]);
                break;
        }
        
        jTextFieldPos.setText((String) this.obj[0]);
        jTextFieldCFlx.setText((String) this.obj[3]);
        jTextFieldDictionnary.setText((String) this.obj[8]);
        jTextFieldsynSem.setText((String) this.obj[4]);
        int lemaid = Integer.parseInt(this.obj[7].toString()) + 1;
        jTextFieldLemaId.setText(String.valueOf(lemaid));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.jTableFLX.setDefaultRenderer(Object.class, color);
        this.jTableDelaf.setDefaultRenderer(Object.class, color);
    }
    private void completeJtableDelaf(String LemmaAll){
        String[] words = LemmaAll.split("-|\\ ");
        int separatorSpace = LemmaAll.indexOf(" ");
        int separatorIndex = LemmaAll.indexOf("-");
        char separator = 0;
        if (separatorSpace > -1) {
            separator = LemmaAll.charAt(separatorSpace);
        } else if (separatorIndex > -1) {
            separator = LemmaAll.charAt(separatorIndex);
        }
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel)jTableFLX.getModel();
        model.addColumn("RB");
        model.addColumn("Form");
        model.addColumn("Lemma");
        model.addColumn("FST Code");
        model.addColumn("GramCat");
        model.addColumn("Separator");
        for(int i = 0;i<words.length;i++){
            String word = words[i];
            if(word.contains("(")){
                int indexPosBegin = word.indexOf(".");
                int indexGramCat = word.indexOf(":");
                String lema = word.substring(word.indexOf("(")+1, indexPosBegin);
                String fst = word.substring(indexPosBegin+1, indexGramCat);
                String gramCat=word.substring(indexGramCat+1, word.length()-1);
                try{
                    Object[] obj = new Object[6];
                    if(i+1==words.length){
                        obj = new Object[]{i,lema,lema,fst,gramCat,""};
                    }else{
                        obj = new Object[]{i,lema,lema,fst,gramCat,separator};
                    }
                    model.addRow(obj);
                }
                catch(java.lang.ArrayIndexOutOfBoundsException e){
                }
                
            }
            else{
                try{
                    Object[] obj = new Object[]{i,word,"","","",""};
                    model.addRow(obj);
                }
                catch(java.lang.ArrayIndexOutOfBoundsException e){
                }
            }
        }
        jTableFLX.setModel(model);
        jTableFLX.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupRules = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPaneTable = new javax.swing.JTabbedPane();
        jPanelCompound = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldLemaAll = new javax.swing.JTextField();
        jTextFieldLema = new javax.swing.JTextField();
        jTextFieldComment = new javax.swing.JTextField();
        WorldNet = new javax.swing.JTextField();
        jTextFieldPos = new javax.swing.JTextField();
        jTextFieldCFlx = new javax.swing.JTextField();
        jTextFieldsynSem = new javax.swing.JTextField();
        jTextFieldDictionnary = new javax.swing.JTextField();
        jTextFieldLemaId = new javax.swing.JTextField();
        jPanelPrediction = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldClemaAll = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTablePredict = new javax.swing.JTable();
        jPanelTable = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableDlf = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableRule = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFLX = new javax.swing.JTable();
        jButtonAddSimpleForm = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDelaf = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuSave = new javax.swing.JMenu();
        jMenuClose = new javax.swing.JMenu();
        jMenuInflect = new javax.swing.JMenu();
        jMenuPrediction = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Lemma (All)");

        jLabel3.setText("Lemma");

        jLabel4.setText("Comment");

        jLabel5.setText("WorldNet");

        jLabel6.setText("POS");

        jLabel7.setText("CFlx");

        jLabel8.setText("SynSem");

        jLabel9.setText("Dictionary");

        jLabel11.setText("Lemma id");

        jTextFieldLemaAll.setEditable(false);
        jTextFieldLemaAll.setBackground(new java.awt.Color(204, 204, 204));

        jTextFieldLema.setEditable(false);
        jTextFieldLema.setBackground(new java.awt.Color(204, 204, 204));

        jTextFieldPos.setEditable(false);
        jTextFieldPos.setBackground(new java.awt.Color(204, 204, 204));

        jTextFieldDictionnary.setEditable(false);
        jTextFieldDictionnary.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanelCompoundLayout = new javax.swing.GroupLayout(jPanelCompound);
        jPanelCompound.setLayout(jPanelCompoundLayout);
        jPanelCompoundLayout.setHorizontalGroup(
            jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCompoundLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCompoundLayout.createSequentialGroup()
                        .addGroup(jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldComment, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addComponent(WorldNet)
                            .addComponent(jTextFieldLema))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPos, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCFlx, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldsynSem, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextFieldLemaAll))
                .addGap(37, 37, 37)
                .addGroup(jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldDictionnary, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(jTextFieldLemaId))
                .addGap(40, 40, 40))
        );
        jPanelCompoundLayout.setVerticalGroup(
            jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCompoundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldLemaAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel9)
                        .addComponent(jTextFieldLema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldDictionnary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCFlx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(WorldNet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldsynSem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldLemaId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPaneTable.addTab("Compound", jPanelCompound);

        jLabel1.setText("Coumpound for prediction of CLemaAll and  CFlx :");

        buttonGroupRules.add(jRadioButton1);
        jRadioButton1.setText("First rule strategy");

        buttonGroupRules.add(jRadioButton2);
        jRadioButton2.setText("Second rule strategy");

        jTablePredict.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTablePredict);

        javax.swing.GroupLayout jPanelPredictionLayout = new javax.swing.GroupLayout(jPanelPrediction);
        jPanelPrediction.setLayout(jPanelPredictionLayout);
        jPanelPredictionLayout.setHorizontalGroup(
            jPanelPredictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPredictionLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanelPredictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPredictionLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldClemaAll, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPredictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPredictionLayout.setVerticalGroup(
            jPanelPredictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPredictionLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanelPredictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldClemaAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297))
            .addGroup(jPanelPredictionLayout.createSequentialGroup()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPaneTable.addTab("Prediction", jPanelPrediction);

        jTableDlf.setAutoCreateRowSorter(true);
        jScrollPane3.setViewportView(jTableDlf);

        jScrollPane5.setViewportView(jTableRule);

        javax.swing.GroupLayout jPanelTableLayout = new javax.swing.GroupLayout(jPanelTable);
        jPanelTable.setLayout(jPanelTableLayout);
        jPanelTableLayout.setHorizontalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTableLayout.setVerticalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneTable.addTab("table", jPanelTable);

        jTableFLX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableFLX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableFLXKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFLX);

        jButtonAddSimpleForm.setText("Add simple form");
        jButtonAddSimpleForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddSimpleFormActionPerformed(evt);
            }
        });

        jButtonRefresh.setText("Refresh Lema");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        jTableDelaf.setAutoCreateRowSorter(true);
        jTableDelaf.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableDelaf);

        jLabel12.setText("Defaf<Ctrl+F> - Copy <Ctrl+C>");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButtonAddSimpleForm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRefresh)
                    .addComponent(jButtonAddSimpleForm)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneTable)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuSave.setText("Save&Close");
        jMenuSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuSaveMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuSave);

        jMenuClose.setText("Just close");
        jMenuClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuCloseMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuClose);

        jMenuInflect.setText("Inflect");
        jMenuInflect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuInflectMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuInflect);

        jMenuPrediction.setText("Prediction");
        jMenuPrediction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuPredictionMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuPrediction);

        jMenu7.setText("Selected Rule");
        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        String valueLemaAll = "";
        String valueLema = "";
        for (int row = 0; row < jTableFLX.getRowCount(); row++) {
            if (!jTableFLX.getModel().getValueAt(row, 1).equals("")) {
                valueLemaAll = valueLemaAll + jTableFLX.getModel().getValueAt(row, 1);
                valueLema = valueLema + jTableFLX.getModel().getValueAt(row, 1);
                if (!jTableFLX.getModel().getValueAt(row, 2).equals("")) {
                    valueLemaAll = valueLemaAll + "(" + jTableFLX.getModel().getValueAt(row, 2);
                    if (!jTableFLX.getModel().getValueAt(row, 3).equals("")) {
                        valueLemaAll = valueLemaAll + "." + jTableFLX.getModel().getValueAt(row, 3);
                        if (!jTableFLX.getModel().getValueAt(row, 4).equals("")) {
                            valueLemaAll = valueLemaAll + ":" + jTableFLX.getModel().getValueAt(row, 4) + ")";
                        }
                    }
                }
                if (jTableFLX.getModel().getValueAt(row, 5)!=null) {
                    valueLema = valueLema + jTableFLX.getModel().getValueAt(row, 5);
                    valueLemaAll = valueLemaAll + jTableFLX.getModel().getValueAt(row, 5);
                } else {
                    valueLema = row==jTableFLX.getRowCount()-1?valueLema:valueLema + " ";
                    valueLemaAll = row==jTableFLX.getRowCount()-1?valueLema:valueLemaAll + " ";
                }
            }
        }
        jTextFieldLemaAll.setText(valueLemaAll);
        jTextFieldLema.setText(valueLema);

    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jMenuSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSaveMouseClicked
        try {
            String lemmaAll = jTextFieldLemaAll.getText();


            String FST = jTextFieldCFlx.getText();
            String synSem = "";
            try{
                synSem = jTextFieldsynSem.getText().substring(0, 1).equals("+")?jTextFieldsynSem.getText():"+" + jTextFieldsynSem.getText();
            }
            catch(java.lang.StringIndexOutOfBoundsException ex){
                synSem="";
            }
            String dic = jTextFieldDictionnary.getText();
            String comment = jTextFieldComment.getText();

            Object[] row = Utils.delacToObject(lemmaAll, FST, synSem, comment, dic);
            if(edit){
                for(int i=0;i<row.length;i++){
                    if(i!=7){
                        editorDelac.getTableModel().setValueAt(row[i],idedit,i);
                    }
                }
            }
            else{
                editorDelac.getTableModel().insertRow(idedit, row);
                editorDelac.getjTable1().setModel(editorDelac.getTableModel());
                for(int i=idedit;i<editorDelac.getTableModel().getRowCount();i++){
                    editorDelac.getTableModel().setValueAt(i,i,7);
                }
                editorDelac.getJLablel13().setText(String.valueOf(editorDelac.getjTable1().getRowCount()));
            }
            editorDelac.setUnsaved(true);
            this.setVisible(false);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jMenuSaveMouseClicked

    private void jTableFLXKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableFLXKeyPressed
        if (this.jTableFLX.getSelectedRow() != -1) {
            if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_F) {
                try {
                    rowIdSelectedDelaf= this.jTableFLX.getSelectedRow();
                    String value = (String) this.jTableFLX.getModel().getValueAt(this.jTableFLX.getSelectedRow(), 1);
                    
                    Utils.generateDelaf(value);
                    tableModel = GridHelper.getDelafInDelacForDelac();
                    JTable table = new JTable(tableModel);
                    this.jTableDelaf.setModel(table.getModel());
                    this.jTableDelaf.removeAll();//dd
                    
                    //
                    this.jTableDelaf.repaint();
                    final JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem selectItem = new JMenuItem("Select");
                    selectItem.addActionListener(selectMenuJTableDelaf());
                    popupMenu.add(selectItem);
                    jTableDelaf.setComponentPopupMenu(popupMenu);
                
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
            if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_C) {
                String value = (String) this.jTableFLX.getModel().getValueAt(this.jTableFLX.getSelectedRow(), 1);
                this.jTableFLX.getModel().setValueAt(value, this.jTableFLX.getSelectedRow(), 2);
            }
        }
    }//GEN-LAST:event_jTableFLXKeyPressed

    private void jMenuInflectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuInflectMouseClicked
        if (jTextFieldLemaAll.getText().equals("") || jTextFieldCFlx.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "something wrong");
        } else {
            try {
                String lema = jTextFieldLemaAll.getText();
                String code = jTextFieldCFlx.getText();
                Utils.InflectDelas(lema, code);
                JOptionPane.showMessageDialog(null, "done");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "error :" + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "error :" + ex.getMessage());
            }

        }
    }//GEN-LAST:event_jMenuInflectMouseClicked

    private void jButtonAddSimpleFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddSimpleFormActionPerformed
        DefaultTableModel newmodel = (DefaultTableModel) jTableFLX.getModel();
        newmodel.addRow(new Object[]{jTableFLX.getModel().getRowCount() + 1, "", "", "", "", ""});
    }//GEN-LAST:event_jButtonAddSimpleFormActionPerformed

    private void jMenuCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuCloseMouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_jMenuCloseMouseClicked

    private void jMenuPredictionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuPredictionMouseClicked
        String value = jTextFieldClemaAll.getText();
        if (!value.equals("")) {
            try {
                String[] words = value.split("-|\\ ");
                int separatorSpace = value.indexOf(" ");
                int separatorIndex = value.indexOf("-");
                char separator = 0;
                String flxGroup = "AC_2XA";

                if (separatorSpace > -1) {
                    separator = value.charAt(separatorSpace);
                } else if (separatorIndex > -1) {
                    separator = value.charAt(separatorIndex);
                }
                List<String> result = CompoundsUtils.getDlfInFile(value);


                jTableDlf.setModel(GridHelper.getDataforjTableDlf(result));
                jTableDlf.repaint();

                /**
                 * *** complete datatableRule **
                 */
                Object[][] getAllDelas = DelasHelper.getAllDelasFromDicToObject();
                Object[][] allDelasImportant = new Object[jTableDlf.getRowCount()][6];
                int indexImportantDelas = 0;
                List<String> wordFoundInDico = new ArrayList<>();
                wordFoundInDico.addAll(Arrays.asList(words));
                for (Object[] allDela : getAllDelas) {

                    for (int i=0;i<jTableDlf.getRowCount();i++) {
                        String text = (String) jTableDlf.getModel().getValueAt(i, 1);
                        String compare = (String) allDela[1];
                        try {
                            if (text.equals(compare)) {
                                String word = (String) jTableDlf.getModel().getValueAt(i, 0);
                                word = word.split(",")[0];
                                allDelasImportant[indexImportantDelas][0] = 1;// Rule

                                allDelasImportant[indexImportantDelas][1] = allDela[1];// Lema
                                allDelasImportant[indexImportantDelas][2] = allDela[2];// FLX CODE
                                allDelasImportant[indexImportantDelas][3] = jTableDlf.getModel().getValueAt(i, 3);// GramCat
                                allDelasImportant[indexImportantDelas][4] = allDela[0];// POS
                                allDelasImportant[indexImportantDelas][5] = word;// words
                                indexImportantDelas++;
                                wordFoundInDico.remove((String) allDela[1]);
                            }
                        } catch (java.lang.NullPointerException e) {
                            continue;
                        }
                    }
                }
                String[] enteteRule = {"Rule", "Lema", "FLX", "GramCat", "POS", "words"};
                jTableRule.setModel((new JTable(new DefaultTableModel(allDelasImportant, enteteRule))).getModel());
                jTableRule.repaint();

                String[] PosWords = new String[words.length];
                Set<String> pos = new HashSet();
                for (int j = 0; j < words.length; j++) {
                    for (int k = 0; k < jTableDlf.getRowCount(); k++) {
                        if (jTableDlf.getValueAt(k, 0) != null) {
                            int indexLema = ((String) jTableDlf.getValueAt(k, 0)).indexOf(",");
                            String lema = ((String) jTableDlf.getValueAt(k, 0)).substring(0, indexLema);
                            if (lema.equals(words[j])) {
                                pos.add((String) jTableDlf.getValueAt(k, 2));
                            }
                        }
                    }
                    String w = "";
                    for (String s : pos) {
                        w = s + ",";
                    }
                    PosWords[j] = "".equals(w) ? w : w.substring(0, w.length() - 1);
                }

                List<String> predict = getLemaFromXmlRule(words, separator, flxGroup, PosWords);

                jTablePredict.setModel(GridHelper.getDataforjTablePredict(predict));
                jTablePredict.repaint();
                final JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem selectItem = new JMenuItem("Select");
                selectItem.addActionListener(selectMenuJTableFLX());
                popupMenu.add(selectItem);
                jTablePredict.setComponentPopupMenu(popupMenu);
                /*for (String t : getLemaFromXmlRule(words, separator, flxGroup, PosWords)) {
                    System.out.println(t);
                }
                wordFoundInDico.stream().forEach((s) -> {
                    System.err.println(s + " is not found in dico");
                });*/
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "error :" + ex.getMessage());
            } catch (ParserConfigurationException | SAXException ex) {
                Logger.getLogger(MenuDelac.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Value Clma is Empty");
        }
    }//GEN-LAST:event_jMenuPredictionMouseClicked

    private ActionListener selectMenuJTableFLX() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTablePredict.getSelectedRow() != -1) {
                    try {
                        String lema = (String) jTablePredict.getModel().getValueAt(jTablePredict.getSelectedRow(), 0);
                        jTableFLX.removeAll();
                        jTableFLX.setModel(GridHelper.getDataforjTableFlx(lema));
                        jTableFLX.repaint();
                        jTextFieldCFlx.setText((String) jTablePredict.getModel().getValueAt(jTablePredict.getSelectedRow(), 1));
                    } catch (IOException ex) {
                        Logger.getLogger(MenuDelac.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No selected value");
                }
            }
            
        };
    }
    private ActionListener selectMenuJTableDelaf() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTableDelaf.getSelectedRow() != -1) {
                    String lema = (String) jTableDelaf.getModel().getValueAt(jTableDelaf.getSelectedRow(), 2);
                    String gramCat = (String) jTableDelaf.getModel().getValueAt(jTableDelaf.getSelectedRow(), 3);
                    String fst = (String) jTableDelaf.getModel().getValueAt(jTableDelaf.getSelectedRow(), 4);
                    DefaultTableModel model = (DefaultTableModel)jTableFLX.getModel();
                    model.setValueAt(lema, rowIdSelectedDelaf, 2);
                    model.setValueAt(gramCat, rowIdSelectedDelaf, 4);
                    model.setValueAt(fst, rowIdSelectedDelaf, 3);
                    jTableFLX.setModel(model);
                    jTableFLX.repaint();

                } else {
                    JOptionPane.showMessageDialog(null, "No selected value");
                }
            }
            
        };
    }

    

    

    private String getFlex(String words, String poss) {
        String flexion = "";
        for (int k = 0; k < jTableRule.getRowCount(); k++) {
            if (jTableRule.getValueAt(k, 1) != null) {
                if (!((String) jTableRule.getValueAt(k, 5)).equals(words)) {
                    flexion = "";
                } else {
                    if (words.equals((String) jTableRule.getValueAt(k, 5)) && (poss.equals((String) jTableRule.getValueAt(k, 4)) || poss.equals("MOT"))) {//get lemma in Table
                        String lema = (String) jTableRule.getValueAt(k, 1);
                        String flex = (String) jTableRule.getValueAt(k, 2);
                        String gramcat = (String) jTableRule.getValueAt(k, 3);
                        flexion = "(" + lema + "." + flex + ":" + gramcat + ")";
                        return flexion;
                    }
                }
            }
        }
        return "";
    }

    

    private  List<String> getLemaFromXmlRule(String[] words, char separator, String flxGroup, String[] POSwords) throws ParserConfigurationException, SAXException, IOException {
        boolean ruleIdFound = false;
        boolean flxFound = false;
        String flx = new String();
        String ruleNo = "";
        NodeList nList = getNodeList();
        String returns = "";
        List<String> ret = new ArrayList<>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getAttribute("WordNo").equals(Integer.toString(words.length))) {
                    ruleIdFound = true;
                    NodeList ndRule = eElement.getElementsByTagName("Rule");
                    for (int i = 0; i < ndRule.getLength(); i++) {
                        Node nNodeRule = ndRule.item(i);
                        if (nNodeRule.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementRule = (Element) nNodeRule;
                            if (eElementRule.getAttribute("CflxGroup").equals(flxGroup)) {
                                flxFound = true;
                                flx = eElementRule.getAttribute("CFLX");
                                ruleNo = eElementRule.getAttribute("ID");
                                /**
                                 * **** For General Rules ****
                                 */
                                NodeList ndRuleGenCond = eElementRule.getElementsByTagName("RuleGenCond");
                                for (int j = 0; j < ndRuleGenCond.getLength(); j++) {
                                    Node nNodeRuleGenCond = ndRuleGenCond.item(j);
                                    if (nNodeRuleGenCond.getNodeType() == Node.ELEMENT_NODE) {
                                        Element eElementRuleGenCond = (Element) nNodeRuleGenCond;
                                        NodeList ndWord = eElementRuleGenCond.getElementsByTagName("Word");
                                        for (int k = 0; k < ndWord.getLength(); k++) {
                                            Node nNodeWord = ndWord.item(k);
                                            if (nNodeWord.getNodeType() == Node.ELEMENT_NODE) {
                                                Element eElementWord = (Element) nNodeWord;
                                                if (eElementWord.getAttribute("POS").equals(POSwords[k]) || eElementWord.getAttribute("POS").equals("MOT")) {
                                                    if (eElementWord.getAttribute("Flex").equals("false") || eElementWord.getAttribute("Flex") == null || eElementWord.getAttribute("Flex").equals("")) {
                                                        //returns = returns+words[k]+"(flexion off)";
                                                        returns = returns + words[k];
                                                    } else {
                                                        String flex = getFlex(words[k], eElementWord.getAttribute("POS"));
                                                        //returns = returns+words[k]+"(flexion on)"+flex;
                                                        returns = returns + words[k] + flex;
                                                    }
                                                } else {
                                                    //returns = returns+words[k]+"(flexion off)";
                                                    returns = returns + words[k];
                                                }
                                            }
                                            returns = returns + separator;
                                        }
                                    }
                                }
                                /**
                                 * **** End For General Rules ****
                                 */
                                ret.add(returns.substring(0, returns.length() - 1) + "," + flx + "," + ruleNo);
                                returns = "";
                            }
                        }
                    }
                }
            }
        }
        if (flxFound == false) {
            throw new NullPointerException(flxGroup + " flx not found in rule " + words.length);
        }
        if (ruleIdFound == false) {
            throw new NullPointerException(words.length + " rule not found");
        }
        return ret;
    }

    public static NodeList getNodeList() throws ParserConfigurationException, SAXException, IOException {
        File inputFile = new File("CompoundsStrat7_Ver5-3.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName("Rules");
    }

    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuDelac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuDelac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuDelac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuDelac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuDelac().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField WorldNet;
    private javax.swing.ButtonGroup buttonGroupRules;
    private javax.swing.JButton jButtonAddSimpleForm;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuClose;
    private javax.swing.JMenu jMenuInflect;
    private javax.swing.JMenu jMenuPrediction;
    private javax.swing.JMenu jMenuSave;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelCompound;
    private javax.swing.JPanel jPanelPrediction;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPaneTable;
    private javax.swing.JTable jTableDelaf;
    private javax.swing.JTable jTableDlf;
    private javax.swing.JTable jTableFLX;
    private javax.swing.JTable jTablePredict;
    private javax.swing.JTable jTableRule;
    private javax.swing.JTextField jTextFieldCFlx;
    private javax.swing.JTextField jTextFieldClemaAll;
    private javax.swing.JTextField jTextFieldComment;
    private javax.swing.JTextField jTextFieldDictionnary;
    private javax.swing.JTextField jTextFieldLema;
    private javax.swing.JTextField jTextFieldLemaAll;
    private javax.swing.JTextField jTextFieldLemaId;
    private javax.swing.JTextField jTextFieldPos;
    private javax.swing.JTextField jTextFieldsynSem;
    // End of variables declaration//GEN-END:variables
    private int rowIdSelectedDelaf;
}
