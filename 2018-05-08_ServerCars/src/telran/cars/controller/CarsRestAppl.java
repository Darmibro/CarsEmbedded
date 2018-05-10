package telran.cars.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import telran.cars.model.IRentCompany;
import telran.cars.model.RentCompanyEmbedded;
import telran.cars.dto.*;

@SpringBootApplication
@RestController
public class CarsRestAppl {
	IRentCompany company=RentCompanyEmbedded.restoreFromFile("cars.data");
	@PostMapping(value=CarsApiConstants.ADD_CAR_MODEL)
	CarsReturnCode addCarModel(@RequestBody Model carModel) {
		return company.addModel(carModel);
	}
	@PostMapping(value=CarsApiConstants.ADD_DRIVER)
	CarsReturnCode addDriver(Driver driver){
		return company.addDriver(driver);
	}
	@PostMapping(value=CarsApiConstants.ADD_CAR)
	CarsReturnCode addCar(@RequestBody Car car) {
		return company.addCar(car);
	}
	
	@PostMapping(value=CarsApiConstants.CLEAR_CARS)
	List<Car> clear(@RequestBody DateDays dateDays){
		return company.clear(dateDays.currentDate, dateDays.days);
	}
	@PostMapping(value=CarsApiConstants.REMOVE_CAR)
	CarsReturnCode removeCar(String carNumber) {
		return company.removeCar(carNumber);
	}
	
	@PostMapping(value=CarsApiConstants.RENT_CAR)
	CarsReturnCode removeCar(String carNumber, long licenseId, LocalDate rentDate, int rentDays) {
		return company.rentCar(carNumber, licenseId, rentDate, rentDays);
	}
	
	@PostMapping(value=CarsApiConstants.RETURN_CAR)
	CarsReturnCode returnCar(String carNumber, long licenseId, LocalDate returnDate, int gasTankPercent,
			int damages) {
		return company.returnCar(carNumber, licenseId, returnDate, gasTankPercent, damages);
	}
	
	@PostMapping(value=CarsApiConstants.SAVE)
	void save() {
		company.save();
	}
	
	@RequestMapping(value =CarsApiConstants.GET_MODEL)
	Model getModel(String modelName) {
		return company.getModel(modelName);
	}
	@GetMapping(value=CarsApiConstants.GET_DRIVER)
	 Driver getDriver(long licenseId) {
		 return company.getDriver(licenseId);
	 }
	
	@RequestMapping(value=CarsApiConstants.GET_ALL_CARS)
	Stream<Car> getAllCars(){
		return company.getAllCars();
	}
	
	@RequestMapping(value=CarsApiConstants.GET_ALL_DRIVERS)
	Stream<Driver> getAllDrives(){
		return company.getAllDrivers();
	}
	
	@RequestMapping(value=CarsApiConstants.GET_ALL_MODELS)
	List<String> getAllModels(){
		return company.getAllModels();
	}
	
	@RequestMapping(value=CarsApiConstants.GET_ALL_RECORDS)
	Stream<RentRecord> getAllRecords(){
		return company.getAllRecords();
	}
	
	@GetMapping(value=CarsApiConstants.GET_CAR)
	Car getCar(String carNumber){
		return company.getCar(carNumber);
	}
	
	@GetMapping(value=CarsApiConstants.GET_CAR_DRIVERS)
	List<Driver> getCarDriver(String carNumber){
		return company.getCarDrivers(carNumber);
	}
	
	@GetMapping(value =CarsApiConstants.GET_DRIVER_CARS)
	List<Car> getDriverCars(long licenseId){
		return company.getDriverCars(licenseId);
	}
	
public static void main(String[] args) {
	SpringApplication.run(CarsRestAppl.class, args); 
}
}
