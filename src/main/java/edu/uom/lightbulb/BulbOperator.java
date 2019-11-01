package edu.uom.lightbulb;

public class BulbOperator {
		private boolean lowBrightness = false, mediumBrightness = false, highBrightness = false;

	boolean turnOn() {
		if (!(lowBrightness || mediumBrightness || highBrightness)) {
			mediumBrightness = true;
			return true;
		} else {
			throw new IllegalStateException();
		}
	}

	boolean turnOff() {
		if (lowBrightness || mediumBrightness || highBrightness) {
			lowBrightness = false;
			mediumBrightness = false;
			highBrightness = false;
			return true;
		} else {
			throw new IllegalStateException();
		}
	}

	boolean increaseBrightness() {
		if (lowBrightness) {
			lowBrightness = false;
			mediumBrightness = true;
		} else if (mediumBrightness) {
			mediumBrightness = false;
			highBrightness = true;
		} else {
			throw new IllegalStateException();
		}
		return true;
	}

	boolean decreaseBrightness() {
		if (highBrightness) {
			highBrightness = false;
			mediumBrightness = true;
		} else if (mediumBrightness) {
			mediumBrightness = false;
			lowBrightness = true;
		} else {
			throw new IllegalStateException();
		}
		return true;
	}

	boolean isLowBrightness() {
		return lowBrightness;
	}

	boolean isMediumBrightness() {
		return mediumBrightness;
	}

	boolean isHighBrightness() {
		return highBrightness;
	}

	/**
	 *
	 * @return false if zero or one variable is true. true otherwise
	 */
	boolean onlyOneStateTrue() {
		if (lowBrightness) {
			return mediumBrightness || highBrightness;
		} else {
			return mediumBrightness && highBrightness;
		}
	}

	boolean isTurnedOn() {
		return lowBrightness || mediumBrightness || highBrightness;
	}

    /*public static void main(String[] args) {
	final Scanner sc = new Scanner(System.in);
        final BulbOperator bulbOperator = new BulbOperator();

        System.out.println("---BULB MANAGER---");
        int choice;
        do{
            System.out.println("Chose an option to control your bulb.");
            System.out.println("1. Turn on bulb.");
            System.out.println("2. Turn off bulb.");
            System.out.println("3. Increase brightness.");
            System.out.println("4. Decrease brightness.");
            System.out.println("5. Exit.");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    if(bulbOperator.turnOn()){
                        System.out.println("Bulb turned on.");
                    } else {
                        System.out.println("Cannot turn on bulb now.");
                    }
                    break;
                case 2:
                    if(bulbOperator.turnOff()){
                        System.out.println("Bulb turned off.");
                    } else {
                        System.out.println("Bulb can't be turned off now.");
                    }
                    break;
                case 3:
                    if(bulbOperator.increaseBrightness()){
                        System.out.println("Bulb brightness increased.");
                    } else {
                        System.out.println("Can't increase bulb brightness.");
                    }
                    break;
                case 4:
                    if(bulbOperator.decreaseBrightness()){
                        System.out.println("Bulb brightness decreased.");
                    } else {
                        System.out.println("Can't decrease bulb brightness.");
                    }
                    break;
                case 5:
                    System.out.println("Bye.");
                    break;
                default:
                    System.out.println("Incorrect option chosen.");
            }
        } while(choice != 5);
    }*/
}
