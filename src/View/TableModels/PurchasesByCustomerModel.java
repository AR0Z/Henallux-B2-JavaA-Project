package View.TableModels;

import Model.Purchase;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PurchasesByCustomerModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Purchase> purchases;

    public PurchasesByCustomerModel(ArrayList<Purchase> purchases){
        this.purchases = purchases;
        columnNames = new ArrayList<>();
        columnNames.add("ID de la commande");
        columnNames.add("Nom d'article");
        columnNames.add("Prix");
        columnNames.add("Quantité");
        columnNames.add("Catégorie");
    }

    public void update(ArrayList<Purchase> purchases){
        this.purchases = purchases;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column){
        return columnNames.get(column);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex) {
            case 0:
            case 3:
                return Integer.class;
            case 2:
                return Double.class;
            default:
                return String.class;
        }
    }

    @Override
    public int getRowCount(){
        return purchases.size();
    }

    @Override
    public int getColumnCount(){
        return columnNames.size();
    }

    public Purchase getValuesRow(int rowIndex){
        return purchases.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Purchase purchase = getValuesRow(rowIndex);

        switch (columnIndex) {
            case 0:
                return purchase.getOrderID();
            case 1:
                return purchase.getArticleName();
            case 2:
                return purchase.getArticlePrice();
            case 3:
                return purchase.getArticleQuantity();
            case 4:
                return purchase.getCategoryLabel();
            default:
                return null;
        }
    }
}
