package GameEngnie;

public class Player {
	float x = 5;
	float y = 7;
	float z = 0;
	
	float pitch = 0.0f;
	float yaw = 0.0f;
	float roll = 0.0f;
	
	static double fov = Math.PI / 4;
	
	void rotateYaw(float amount)
	{
		this.yaw += 90;
	}
	
	void checkRotations()
	{
		if(yaw > 360)
			yaw = 0;
		if(yaw < 0)
			yaw = 360;
		
		if(roll > 360)
		    roll = 0;
		if(roll < 0)
		    roll = 360;
		
		if(pitch > 360)
		    pitch = 0;
		if(pitch < 0)
		    pitch = 360;

	}
}
