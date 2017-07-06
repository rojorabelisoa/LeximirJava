/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rojo Rabelisoa
 */
public class GridModel extends AbstractTableModel {

    private Object[][] donnees;
    private String[] entete;

    /**
     * @param aDonnees the donnees to set
     */
    public void setDonnees(Object[][] aDonnees) {
        donnees = aDonnees;
    }

    /**
     * @param aEntete the entete to set
     */
    public void setEntete(String[] aEntete) {
        entete = aEntete;
    }

    public GridModel(String[] entete, Object[][] data) {

        this.donnees = data;/*ClassDeRechercheSwing.rechercher(o, condition, null);*/
                //doit retourner un Object[][]
            /*Field[] attr = o.getClass().getDeclaredFields();
         String title[] = new String[attr.length];
         for(int m=0;m<attr.length;m++)
         title[m]=attr[m].getName();*/

        this.entete = entete;//title;//doit retourner un [];

    }

    /**
     * @return the donnees
     */
    public Object[][] getDonnees() {
        return donnees;
    }

    /**
     * @return the entete
     */
    public String[] getEntete() {
        return entete;
    }
    public void removeRow(int row) {
        // remove a row from your internal data structure
        fireTableRowsDeleted(row, row);
    }
    /**
     * @return 
     */
    public void tableChanged(TableModelEvent e) {
        int firstRow = e.getFirstRow();
        int lastRow = e.getLastRow();
        int index = e.getColumn();

        switch (e.getType()) {
        case TableModelEvent.INSERT:
          for (int i = firstRow; i <= lastRow; i++) {
            System.out.println(i);
          }
          break;
        case TableModelEvent.UPDATE:
          if (firstRow == TableModelEvent.HEADER_ROW) {
            if (index == TableModelEvent.ALL_COLUMNS) {
              System.out.println("A column was added");
            } else {
              System.out.println(index + "in header changed");
            }
          } else {
            for (int i = firstRow; i <= lastRow; i++) {
              if (index == TableModelEvent.ALL_COLUMNS) {
                System.out.println("All columns have changed");
              } else {
                System.out.println(index);
              }
            }
          }
          break;
        case TableModelEvent.DELETE:
          for (int i = firstRow; i <= lastRow; i++) {
            System.out.println(i);
          }
          break;
        }
    }
    @Override // MAKA NBRE DE LIGNE
    public int getRowCount() {
        return getDonnees().length;
    }

    @Override // MAKA NBRE DE COLONNE
    public int getColumnCount() {
        return getDonnees()[0].length;
    }

    @Override // MAKA ILAY OBJECT 
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getDonnees()[rowIndex][columnIndex];
    }

    //MAKA ANARAN'ILAY COLONNE SELON ID
    @Override
    public String getColumnName(int col) {
        return getEntete()[col];

    }

    // maka ilay ligne modifier
    public Object[] getObjetLigne(int id) {
        return getDonnees()[id];
    }

    public void insererObjet(Object[] obj) {
        System.out.println("\n");
        System.out.println("INSERTION");
        //ClassMAPTable u=ClassDeRecherche.ResultsetToClassMappingTable(obj, getOb());
        for (String entete1 : getEntete()) {
            //System.out.println(getEntete()[i]+" = "+u.getFieldList().toString());
        }
        System.out.println("\n");
    }

    public void updateObjet(Object[] obj) { // obj: ligne modifier        
        // fonction pour inserer un objet
        System.out.println("MISE A JOUR");
        for (int i = 0; i < getEntete().length; i++) {
            System.out.println(getEntete()[i] + " = " + obj[i]);
        }

    }
}
