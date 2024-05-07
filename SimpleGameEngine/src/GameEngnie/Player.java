package GameEngnie;

public class Player {
	float x = 0;
	float y = 0;
	float z = 0;
	
	float pitch = 0.0f;
	float yaw = 0.1f;
	float roll = 0.0f;
	
	static double fov = Math.PI / 4;
	
	void rotateYaw(float amount)
	{
		this.yaw += amount;
		checkRotations();
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
