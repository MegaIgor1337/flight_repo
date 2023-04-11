package by.itacademy.jdbc.starter.entity.aircraft;

import java.util.Objects;

public class Aircraft {
    private Long id;
    private String model;

    public Aircraft(Long id, String model) {
        this.id = id;
        this.model = model;
    }

    public Aircraft() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return Objects.equals(id, aircraft.id) && Objects.equals(model, aircraft.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
