package io.code.nasa.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nasa")
public class Info {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "ket")
    private String ket;

    public Info(){}

    public Info(Integer id, String ket) {
        this.id = id;
        this.ket = ket;
    }

    public Integer getId() {
        return id;
    }

    public String getKet() {
        return ket;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", ket='" + ket + '\'' +
                '}';
    }
}
