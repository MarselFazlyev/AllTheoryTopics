package example.oneToMany.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Person2 ")
public class Person {

    @Id
    @Column(name = "id ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name ")
    private String name;

    @Column(name = "age")
    private  Integer age;

    @OneToMany (mappedBy = "owner")
    private List<Item> items;

    public Person(){}

    public Person(String name, Integer age ){
        this.name = name;
        this.age = age;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
