package View.TableModels;

import Controller.ApplicationController;
import Exceptions.ConnectionException;
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
            try {
                ApplicationController controller = new ApplicationController();
                switch (columnIndex) {
                    case 0:
                        return product.getProductName();
                    case 1:
                        return controller.getQuantitySold(product.getLines());
                    case 2:
                        return controller.getTotalRevenue(product.getLines());
                    case 3:
                        return product.getCategoryName();
                    default:
                        return null;
                }
            } catch (ConnectionException e) {
                throw new RuntimeException(e);
            }
        }

}
