package application;
import model.*;
public class DummyData {

	public static Vehicle vehicle()
	{
		Vehicle v = new Vehicle();
		v.setMake("Mitsubishi");
		v.setModel("Lancer");
		v.setYear(2014);
		v.setMileage(12500);
		v.setPrice(17500);
		v.setStyle(VehicleStyle.Sedan);
		v.setDescription("This is a 2014 Mitsubishi Lancer.\n It has new tires and a custom stereo.");
		
		return v;
	}

	
}
