package Model;

public class Category {
    private int id;
    private String label;
    private String description;

    public Category(int id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
    }

    public Category(int id, String label) {
        this(id,label,null);
    }

    public String getLabel() {
        return label;
    }
}
