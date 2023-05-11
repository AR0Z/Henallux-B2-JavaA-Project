package View.TableModels;

import Model.CustomerByProduct;
import Model.Purchase;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class CustomersByProductModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<CustomerByProduct> customersByProduct;

    public CustomersByProductModel(ArrayList<CustomerByProduct> customersByProduct){
        this.customersByProduct = customersByProduct;

        columnNames = new ArrayList<>();
        columnNames.add("ID");
        columnNames.add("Prénom");
        columnNames.add("Nom");
        columnNames.add("Quantité commandée");
        columnNames.add("Date de commande");
    }

    public void update(ArrayList<CustomerByProduct> customersByProduct){
        this.customersByProduct = customersByProduct;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column){
        return columnNames.get(column);
    }

    @Override
    public Class<?> getColumnClass(int colmunIndex){
        switch (colmunIndex){
            case 0:
            case 3:
                return Integer.class;
            case 4:
                return LocalDate.class;
            default:
                return String.class;
        }
    }

    @Override
    public int getRowCount(){
        return customersByProduct.size();
    }

    @Override
    public int getColumnCount(){
        return columnNames.size();
    }

    public CustomerByProduct getValuesRow(int rowIndex){
        return customersByProduct.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        CustomerByProduct customerByProduct = getValuesRow(rowIndex);
        switch (columnIndex){
            case 0:
                return customerByProduct.getCustomerID();
            case 1:
                return customerByProduct.getCustomerFirstName();
            case 2:
                return customerByProduct.getCustomerLastName();
            case 3:
                return customerByProduct.getQuantity();
            case 4:
                return customerByProduct.getDateOrder();
            default:
                return null;
        }
    }
}
