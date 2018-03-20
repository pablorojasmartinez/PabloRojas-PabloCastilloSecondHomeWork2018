/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import domain.Car;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javafx.scene.shape.Arc;

/**
 *
 * @author Pablo Rojas Martínez
 */
public class CarFile {
    //attributes

    public RandomAccessFile randomAccessFile;
    private int regQuantity;
    private int regSize;
    private String path;

    public CarFile() throws IOException {
        this.path = "ArchivoAuto";
        File file = new File(this.path);
        start(file);
    }//Constructor

    //This method initialize the attributes and create the file.
    public void start(File file) throws IOException {
        path = file.getPath();

        this.regSize = 100;
        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + " Is an invalid file");
        } else {
            randomAccessFile = new RandomAccessFile(file, "rw");

            this.regQuantity = (int) Math.ceil((double) randomAccessFile.length() / regSize);
        }
    }//start

    //This method close the read and write in the file
    public void closeFile() throws IOException {
        randomAccessFile.close();
    }//closeFile

    //This method returns the amount of registers in the file
    public int sizeFile() {
        return this.regQuantity;
    }//sizeFile

    //This method is used to insert a car in a position, if insertion is correct the method return true;
    public boolean insert(int position, Car car) throws IOException {

        if (position < 0 && position > this.regQuantity) {
            System.err.println(" 1001 -  Record position is out of bounds");
            return false;
        } else {
            if (car.sizeInBytes() > this.regSize) {
                System.err.println(" 1002 -   Record size is out of bounds");
                return false;
            } else {
                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeUTF(car.getName());
                randomAccessFile.writeInt(car.getYear());
                randomAccessFile.writeFloat(car.getMileage());
                randomAccessFile.writeBoolean(car.getAmerican());
                randomAccessFile.writeInt(car.getId());
                return true;
            }
        }
    }//insert

    //This method return the method insert
    public boolean insertCar(Car car) throws IOException {

        return insert(this.regQuantity, car);
    }//insertCar

    //This method get a car from a specific postion.
    public Car getCar(int position) throws IOException {
        if (position >= 0 && position <= this.regQuantity) {
            randomAccessFile.seek(position * this.regSize);
            Car currentCar = new Car();
            currentCar.setName(randomAccessFile.readUTF());
            currentCar.setYear(randomAccessFile.readInt());
            currentCar.setMileage(randomAccessFile.readFloat());
            currentCar.setAmerican(randomAccessFile.readBoolean());
            currentCar.setId(randomAccessFile.readInt());

            if (currentCar.getId() == -1) {
                return null;
            } else {
                return currentCar;
            }
        } else {
            System.err.println("1003  - Position is out of bounds");
            return null;
        }
    }//getCar

    //This method delete a car
    public boolean deleteCar(int id) throws IOException {
        for (int i = 0; i < this.regQuantity; i++) {
            Car currentCar = this.getCar(i);
            if (currentCar != null) {
                if (currentCar.getId() == id) {
                    currentCar.setId(-1);
                    return this.insert(i, currentCar);
                }
            }
        }
        return false;
    }//deleteCar

    //This method return an ArrayList with all the cars in the file.
    public ArrayList<Car> getAllCars() throws IOException {
        ArrayList<Car> arrayCar = new ArrayList<Car>();
        for (int i = 0; i < this.regQuantity; i++) {
            Car currentCar = this.getCar(i);
            if (arrayCar != null) {
                arrayCar.add(currentCar);
            }

        }
        return arrayCar;
    }// getAllCars

    //This method is used for update a car. 
    public boolean updateCar(Car automovil) throws IOException {
        int i = 0;
        for (i = i; i < this.regQuantity; i++) {
            if (automovil.getId() == this.randomAccessFile.readInt()) {
                break;
            }
        }
        randomAccessFile.seek(i * this.regSize);
        randomAccessFile.writeUTF(automovil.getName());
        randomAccessFile.writeInt(automovil.getYear());
        randomAccessFile.writeFloat(automovil.getMileage());
        randomAccessFile.writeBoolean(automovil.getAmerican());
        randomAccessFile.writeInt(automovil.getId());
        return true;
    }//updateCar

    //This method receive an id for searching  if it is in the file
    public Car search(int id) throws IOException {
        for (int i = 0; i < this.regQuantity; i++) {
            Car currentCar = getCar(i);
            if (currentCar != null) {
                if (currentCar.getId() == id) {
                    return currentCar;
                }
            }
        }
        return null;
    }//search

    //This method is used for check that only exist one car with this id
    public boolean check(int id) throws IOException {
        for (int i = 0; i < this.regQuantity; i++) {
            Car currentCar = this.getCar(i);
            if (currentCar != null) {
                if (currentCar.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }//check

}//class
