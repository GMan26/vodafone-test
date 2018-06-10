package vodaone.test.george;

public class Exit {

	/*
	 * This class will cancel the operation of CLI interaction
	 * Once the user types exit
	 */
	
	public static boolean wantsExit(String getInput) {
		if(getInput.equals("exit")) {new Menu(); return true;}
		return false;
	}
	

}
