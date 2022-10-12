public class StateAndReward {

	
	/* State discretization function for the angle controller */
	public static String getStateAngle(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */

		int discAngle = discretize(angle, 50 , -Math.PI, +Math.PI);
//		int discVx = discretize2(vx, 50, -5, +5);
//		int discVy = discretize2(vy, 50, -5, +5);
		
//		String state = Integer.toString(discAngle) + "#" + Integer.toString(discVx) + "#" + Integer.toString(discVy) + "/";
		String state = Integer.toString(discAngle) + "/";
		return state;
	}

	/* Reward function for the angle controller */
	public static double getRewardAngle(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */
		
		double reward = (Math.PI - Math.abs(angle))*(Math.PI - Math.abs(angle));

		return reward;
	}

	/* State discretization function for the full hover controller */
	public static String getStateHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */
		
		int discAngle = discretize(angle, 20, -Math.PI, +Math.PI);
		int discVx = discretize2(vx, 5, -10, +10);
		int discVy = discretize2(vy, 15, -3, +3);
		
		String state = Integer.toString(discAngle) + "#" + Integer.toString(discVx) + "#" + Integer.toString(discVy) + "/";
		
		return state;
	}

	/* Reward function for the full hover controller */
	public static double getRewardHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */
		
		double rewardVx = ((15 - Math.abs(vx))*(15 - Math.abs(vx)))/(15*15); 
		double rewardVy = ((15 - Math.abs(vy))*(15 - Math.abs(vy)))/(15*15); 
		double rewardAngle = ((Math.PI - Math.abs(angle))*(Math.PI - Math.abs(angle)))/(Math.PI * Math.PI);
		
		double reward = 10*rewardVx*((15-Math.abs(vy))/15) + 80*rewardVy + 20*rewardAngle;

		return reward;
	}

	// ///////////////////////////////////////////////////////////
	// discretize() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 1 and nrValues-2 is returned.
	//
	// Use discretize2() if you want a discretization method that does
	// not handle values lower than min and higher than max.
	// ///////////////////////////////////////////////////////////
	public static int discretize(double value, int nrValues, double min,
			double max) {
		if (nrValues < 2) {
			return 0;
		}

		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * (nrValues - 2)) + 1;
	}

	// ///////////////////////////////////////////////////////////
	// discretize2() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 0 and nrValues-1 is returned.
	// ///////////////////////////////////////////////////////////
	public static int discretize2(double value, int nrValues, double min,
			double max) {
		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * nrValues);
	}

}
