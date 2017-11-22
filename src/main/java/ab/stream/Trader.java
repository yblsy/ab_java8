package ab.stream;

public class Trader {

    public Trader(String name,String city){
        this.name = name;
        this.city = city;
    }

    private String name;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
