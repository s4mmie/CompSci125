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
public class WeatherDriver 
{
	public static void main(String[] args) 
	{
        //Initializing weather
        Weather weather = new Weather();

        //Testing the default
        System.out.println("Temperature: " + weather.getTemp());
        System.out.println("Sky Conditions: " + weather.getSky());
        
        weather.setTemp(55);
        weather.setSky("cloudy");
        //Testing sky and temp
        System.out.println("Temperature: " + weather.getTemp());
        System.out.println("Sky Conditions: " + weather.getSky());

        //Testing temperature conversion
        double celsius = weather.convertToC(55);
        System.out.println("85 F in Celsius: " + celsius);
        
        //Testing consistency check
        weather.setTemp(40);
        weather.setSky("snowy");
        
        System.out.println("Is weather consistent? " + weather.isConsistent());
        
        //Testing consistency check
        weather.setTemp(55);
        weather.setSky("rainy");
        
        System.out.println("Is weather consistent? " + weather.isConsistent());
        
        //Testing toString method
        System.out.println(weather);

        //Testing equals method
        Weather anotherWeather = new Weather();
        anotherWeather.setTemp(85);
        anotherWeather.setSky("cloudy");

        System.out.println("Are weather objects equal? " + weather.equals(anotherWeather));
        
        //Testing matching weather
        Weather matchingWeather = new Weather();
        matchingWeather.setTemp(55);
        matchingWeather.setSky("rainy");
        
        System.out.println("Are weather objects equal? " + weather.equals(matchingWeather));
    }
}
