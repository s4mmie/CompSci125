/*Samuel Bartholomew
 * Homework 7 part 4
 * Writing System.out.println every time we want to output a message is annoying. 
 * Write a class that enables us to write S.pln( “Hello world” ) 
 * instead. Test your class in a driver called SDriver.java.
 */
public class SDriver {
	//Initialze S
	S s = new S();
	
	public static void main(String[] args)
	{
		//Use S Print Line 
		S.pln("Hello World!!");
	}
}
