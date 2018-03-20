/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Car;
import file.CarFile;
import java.io.IOException;

/**
 *
 * @author Pablo Rojas Martínez
 */
public class BusinessCar {

    CarFile carFile;

    public BusinessCar() throws IOException {
        carFile = new CarFile();
    }
    
    public boolean insertarAuto(Car car) throws IOException{
        return this.carFile.insertCar(car);
    }
    
    public Car getCar(int position) throws IOException{
        return this.carFile.getCar(position);
    } 
    
    public Car search(int id) throws IOException{
        return this.carFile.search(id);
    }
    
    public boolean  delete(int id) throws IOException{
        return this.carFile.deleteCar(id);
    }
    
    public boolean update(Car car) throws IOException{
        return this.carFile.updateCar(car);
    }
    public boolean check(int id) throws IOException{
        return this.carFile.check(id);
    
    }

}
