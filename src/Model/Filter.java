package Model;

public class Filter {
    private Category category;
    private Supplier supplier;
    private String startDate;
    private String endDate;
    private String order;

    // create constructor where each parameter is optional
    public Filter(Category category, Supplier supplier, String startDate, String endDate, String order) {
        this.category = category;
        this.supplier = supplier;
        this.startDate = startDate;
        this.endDate = endDate;
        this.order = order;
    }

    public Category getCategory() {
        return category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getOrder() {
        return order;
    }
}
