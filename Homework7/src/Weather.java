/* Samuel Bartholomew
 * Homework 7 part 5
 * Write a class stored in Weather.java encapsulating the concept of the weather forecast, 
 * assuming that it has the following attributes: the temperature and the sky conditions, 
 * which could be sunny, snowy, cloudy, or rainy. Include a constructor, the accessors and 
 * mutators, and methods toString and equals. Temperature, in Fahrenheit, should be between –50 
 * and +150; the default value is 70, if needed. The default sky condition is sunny. Include a 
 * method that converts Fahrenheit to Celsius. Celsius temperature = (Fahrenheit temperature – 32) * 5 / 9. 
 * Also include a method that checks whether the weather attributes are consistent 
 * (there are two cases where they are not consistent: when the temperature is above 32 and it is snowy, 
 * and when the temperature is below 32 and it is rainy). Write a driver called WeatherDriver.java to 
 * test all the methods in your class.
 */
public class Weather 
{
	private double temp;
	private String sky;
	
	//Default constructors
	public Weather()
	{
		temp = 70;
		sky = "sunny";
	}
	//Get Temp
	public double getTemp()
	{
		return temp;
	}
	
	public String getSky()
	{
		return sky;
	}
	//Set temp to setTo
	public void setTemp(double setTo)
	{
		if(setTo > -50 && setTo < 150)
		{
			this.temp = setTo;
		}
		else
		{
		System.out.println("The temperature you want to set to is out of the range. It must be between -50 and 150.");	
		}
	}
	//Set the sky to conditions
	public void setSky(String conditions) 
	{
        this.sky = conditions;
    }
	//Take Celcius and convert to Farenhiet
	public double convertToF(double c) 
	{
        return (c * 9 / 5) + 32;
    }
	//Take Farenheit and convert to Celcius
	public double convertToC(double fahrenheit) 
	{
        return (fahrenheit - 32) * 5 / 9;
    }
	
	//See if temp is greater to 32 and if it is snowy its false and if its less than 32 and raining it is false
	//Other wise return true
	public boolean isConsistent() 
	{
        if ((temp > 32 && sky.equals("snowy")) || (temp < 32 && sky.equals("rainy"))) {
            return false;
        }
        return true;
    }
	//Take the class and return the information as a string.
	public String toString() 
	{
        return "Temperature: " + temp + " F, Sky Conditions: " + temp;
    }
	//See if another weather object equals another weather
	public boolean equals(Object weather) 
	{
		//if this object is == to the testing object return true
        if (this == weather)
        {
        	return true;
        }
        //if weather is empty or not of the same class return false
        if (weather == null || getClass() != weather.getClass())
        {
        	return false;
        }
        //set weather th a new weather object and compare their values
        Weather otherWeather = (Weather) weather;
        return Double.compare(otherWeather.temp, temp) == 0 && sky.equals(otherWeather.sky);
    }
}
