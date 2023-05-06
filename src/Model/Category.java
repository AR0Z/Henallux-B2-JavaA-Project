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

    public Category() {
        this(0,null,null);
    }

    public String getLabel() {
        return label;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {return this.id;}
}
