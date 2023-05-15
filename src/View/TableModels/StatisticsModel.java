package View.TableModels;

import Model.ProductByFilter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StatisticsModel extends AbstractTableModel {

        private ArrayList<ProductByFilter> products;
        private String[] columnNames = {"Nom", "Vente", "Chiffre d'affaire", "Category"};

        public StatisticsModel(ArrayList<ProductByFilter> products) {
            this.products = products;
        }

        @Override
        public int getRowCount() {
            return products.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        public String getColumnName(int col) { return columnNames[col]; }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            ProductByFilter product = products.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return product.getProductName();
                case 1:
                    return product.getQuantitySold();
                case 2:
                    return product.getTotalRevenue();
                case 3:
                    return product.getCategoryName();
                default:
                    return null;
            }
        }

}
