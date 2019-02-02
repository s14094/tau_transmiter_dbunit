package pawellakomiec.com.domain;

import javax.persistence.*;

@Entity
@Table(name = "transmiter", schema = "tau", catalog = "")
public class Transmiter {
    private int id;
    private String name;
    private String model;
    private Integer code;
    private String description;

    public Transmiter(String name, String model, Integer code, String description) {
        this.name = name;
        this.model = model;
        this.code = code;
        this.description = description;
    }

    public Transmiter() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

