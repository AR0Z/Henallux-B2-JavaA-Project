package View.TableModels;

import Model.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class AllProductsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Product> products;

    public AllProductsModel(ArrayList<Product> products) {
        this.products = products;

        columnNames = new ArrayList<>();
        columnNames.add("ID");
        columnNames.add("Nom");
        columnNames.add("Couleur");
        columnNames.add("Prix de vente");
        columnNames.add("Prix d'achat");
        columnNames.add("Taille");
        columnNames.add("Quantité en stock");
        columnNames.add("Date d'ajout");
        columnNames.add("Est livrable");
        columnNames.add("Catégorie");
        columnNames.add("Information");
        columnNames.add("Lien de l'image");
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 6:
                return Integer.class;
            case 3:
            case 4:
            case 5:
                return Double.class;
            case 7:
                return Date.class;
            case 8:
                return Boolean.class;
            default:
                return String.class;
        }
    }

    @Override
    public int getRowCount(){
        return products.size();
    }

    @Override
    public int getColumnCount(){
        return columnNames.size();
    }

    public Product getValuesRow(int rowIndex){
        return products.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = getValuesRow(rowIndex);
        switch (columnIndex) {
            case 0:
                return product.getId();
            case 1:
                return product.getName();
            case 2:
                return product.getColor();
            case 3:
                return product.getPrice();
            case 4:
                return product.getCost();
            case 5:
                return product.getSize();
            case 6:
                return product.getStock();
            case 7:
                return product.getAdditionDate();
            case 8:
                return product.getShippable();
            case 9:
                return product.getCategory().getLabel();
            case 10:
                return product.getDescription();
            case 11:
                return product.getImgLink();
            default:
                return null;
        }
    }
}
