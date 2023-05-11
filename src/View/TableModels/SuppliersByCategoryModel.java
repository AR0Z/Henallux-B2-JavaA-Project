package View.TableModels;

import Model.CustomerByProduct;
import Model.SupplierByCategory;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class SuppliersByCategoryModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<SupplierByCategory> suppliersByCategory;

    public SuppliersByCategoryModel(ArrayList<SupplierByCategory> suppliersByCategory){
        this.suppliersByCategory = suppliersByCategory;

        columnNames = new ArrayList<>();
        columnNames.add("Nom du pays");
        columnNames.add("Nom de la ville");
        columnNames.add("Nom du fournisseur");
    }

    public void update(ArrayList<SupplierByCategory> suppliersByCategory){
        this.suppliersByCategory = suppliersByCategory;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column){
        return columnNames.get(column);
    }

    @Override
    public Class<?> getColumnClass(int colmunIndex){
        return String.class;
    }

    @Override
    public int getRowCount(){
        return suppliersByCategory.size();
    }

    @Override
    public int getColumnCount(){
        return columnNames.size();
    }

    public SupplierByCategory getValuesRow(int rowIndex){
        return suppliersByCategory.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        SupplierByCategory supplierByCategory = getValuesRow(rowIndex);
        switch (columnIndex){
            case 0:
                return supplierByCategory.getCountryName();
            case 1:
                return supplierByCategory.getCityName();
            case 2:
                return supplierByCategory.getSupplierName();
            default:
                return null;
        }
    }
}
