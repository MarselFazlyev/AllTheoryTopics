package innopolisLection.semipro;

public class Driver {
    private int id;
    private String name;
    private String last_name;

    public Driver(int id, String name, String last_name) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return
                "id " + id + "|" +
                        "name " + name + "|" +
                        "last_name " + last_name + "|"+"\n"
                ;
    }
}
