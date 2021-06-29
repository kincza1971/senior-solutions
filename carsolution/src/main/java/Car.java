import java.util.ArrayList;
import java.util.List;

public class Car {
    private String brand;
    private String type;
    private int age;
    private CarState condition;
    List<KmState> kmStateList = new ArrayList<>();

    public Car(String brand, String type, int age, CarState condition, List<KmState> kmStateList) {
        this.brand = brand;
        this.type = type;
        this.age = age;
        this.condition = condition;
        this.kmStateList = kmStateList;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public CarState getCondition() {
        return condition;
    }

    public void setCondition(CarState condition) {
        this.condition = condition;
    }

    public List<KmState> getKmStateList() {
        return kmStateList;
    }

    public void setKmStateList(List<KmState> kmStateList) {
        this.kmStateList = kmStateList;
    }
}
