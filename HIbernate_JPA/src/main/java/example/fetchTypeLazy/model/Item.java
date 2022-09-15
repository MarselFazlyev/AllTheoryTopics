package example.fetchTypeLazy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Item")
public class Item {

    @Column(name = "id")
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id ;

    @JoinColumn(name = "person_id",referencedColumnName = "id")
    @ManyToOne
    private Person owner;

    @Column(name = "item_name")
    private String item_name;

    public Item(){
    }

    public Item(Person owner, String item_name) {
        this.owner = owner;
        this.item_name = item_name;
    }

    public Item (String item_name) {
        this.item_name = item_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Person getOwner() {

        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", owner=" + owner +
                ", item_name='" + item_name + '\'' +
                '}';
    }
}
