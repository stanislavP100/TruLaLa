package com.exampleTruLaLa.TruLaLa.Entity;

import javax.persistence.*;

@Entity
@Table(name="budsumishi", catalog = "truladb")
public class BudivelniSumish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private  Long id;

    @Column(name="name")
    private  String name;

    @Column(name="price")
    private  Float price;

    @Column(name="imagePath")
    private  String imagePath;


    public BudivelniSumish(String name, Float price, String imagePath, String category) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;

    }

    public static String getClassName(){
        return "BudivelniSumish";
    }

    public BudivelniSumish() {}

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BudivelniSumish(Long id, String name, Float price, String imagePath) {
        this.id=id;
        this.name=name;
        this.price=price;
        this.imagePath=imagePath;

    }

    public BudivelniSumish(BudivelniSumish good)
    {
        this.id=good.id;
        this.name=good.name;
        this.price=good.price;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price =  price;
    }

    @Override
    public String toString() {
        return "BudivelniSumish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
