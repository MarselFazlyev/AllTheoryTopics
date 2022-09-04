package innopolisLection.semipro;

public class Car {
    private int id;
    private String brand;
    private String model;
    private String color;
    private int driver_id;


    public Car(int id, String brand, String model, String color, int driver_id) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.driver_id = driver_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    @Override
    public String toString() {
        return
                "id " + id + "|"
                        + "brand " + brand + "|"
                        + "model " + model + "|"
                        + "color " + color + "|"
                        + "driver_id " + driver_id + "|"+"\n"
                ;
    }
}

