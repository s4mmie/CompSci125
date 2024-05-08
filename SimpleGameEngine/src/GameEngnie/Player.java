//Samuel Bartholomew
//Scott Weiss
//Final Project

//Problem: Turn a randomized string into a 3d enviorment where you can move and look around

package GameEngnie;
//A CLASS TO STORE THE PLAYER'S DATA
public class Player {
	float x = 0; //Position X Y Z
	float y = 0;
	float z = 0;
	
	float pitch = 0.0f; //X ANGLE
	float yaw = 0.1f; //  Y ANGLE
	float roll = 0.0f; // Z ANGLE
	
	static double fov = Math.PI / 4; //FOV is 90 degrees I believe
	//Rotate Yaw (Y ANGLE)
	void rotateYaw(float amount)
	{
		this.yaw += amount;
		checkRotations(); //Make sure rotation is between pi and -pi
	}
	
	void checkRotations()
	{
		if(yaw > 3.14f)
			yaw = -3.14f;
		if(yaw < -3.14f)
			yaw = 3.14f;
		
		if(roll > 6.28f)
		    roll = 0;
		if(roll < 0)
		    roll = 6.28f;
		
		if(pitch > 6.28f)
		    pitch = 0;
		if(pitch < 0)
		    pitch = 6.28f;

	}
}
