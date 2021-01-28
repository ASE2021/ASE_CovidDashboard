package com.ase.ase.entities;

import javax.persistence.*;

@Entity
@Table(indexes = @Index(name = "p_index", columnList = "id", unique = true))
public class Population {

    @Id
    private int id;
    private String name;
    private int population;

    protected Population() { }

    public Population(String name, int id, int population) {
        this.id = id;
        this.name = name;
        this.population = population;
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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Population{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                '}';
    }
}
