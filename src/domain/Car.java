/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Pablo Rojas Martínez
 */
public class Car {

    //attributes 
    private String name;
    private int year;
    private float mileage;
    private boolean american;
    private int id;

    public Car() {
        this.name = "";
        this.year = 0;
        this.mileage = 0;
        this.american = false;
        this.id = 0;
    }//Constructor

    public Car(String name, int year, float mileage, boolean american, int id) {
        this.name = name;
        this.year = year;
        this.mileage = mileage;
        this.american = american;
        this.id = id;
    }//Overload constructor

    //Sets && Gets
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public boolean getAmerican() {
        return american;
    }

    public void setAmerican(boolean american) {
        this.american = american;
    }

    public int getId() {
        return id;
    }

    public void setId(int iid) {
        this.id = iid;
    }

    //toString
    @Override
    public String toString() {
        return "Car" + "  Name=" + this.name + " Year=" + this.year + " Mileage=" + mileage + " American=" + american + " Id=" + id + "\n";
    }//toString

    //This method return the size in bytes of the attributes
    public int sizeInBytes() {
        return this.getName().length() * 2
                + 4 + 4 + 1 + 4;
    }//sizeInBytes

}
