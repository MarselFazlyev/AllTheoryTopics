package StreamApi;

public class Artist {
    private String city;
    private String name;

    public Artist(String city, String name) {
        this.city = city;
        this.name = name;

    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    boolean isFrom(String city) {
        return city.equals(this.city);

    }
}
