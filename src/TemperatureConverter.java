import java.util.Scanner;

public class TemperatureConverter {
    public static double convertTemperature (double temperature, String unit) {
        double convertedTemp = 0.0;
        if (unit.equalsIgnoreCase("F")) {
            convertedTemp = (temperature - 32) * (5.0 / 9.0);
        }
        else if (unit.equalsIgnoreCase("C")) {
            convertedTemp = temperature * (9.0 / 5.0) + 32;
        }
        return convertedTemp;
    }

    public static void main (String [] args) {
        Scanner scnr = new Scanner (System.in);
        String tempOrStop = "Hi";
        double convertedTemp = 0.0;
        int i = 0;

        while (!tempOrStop.equals("stop")) {
            System.out.print("Please enter a temperature or type \"stop\" to quit: ");
            tempOrStop = scnr.next();

            if (!tempOrStop.equals("stop")) {
                boolean negative = false;
                boolean number = true;
                boolean decimal = false;
                double decimalPlace = 10.0;
                double temperature = 0.0;
                int index = 0;

                if (!tempOrStop.isEmpty() && tempOrStop.charAt(0) == '-') {
                    negative = true;
                    index = 1;
                }
                if (tempOrStop.length() == index) {
                    number = false;
                }

                for (i = index; i < tempOrStop.length(); ++i) {
                    char one = tempOrStop.charAt(i);

                    if (one == '.') {
                        if (decimal) {
                            number = false;
                        }
                        decimal = true;
                    } else if (one >= '0' && one <= '9') {
                        if (!decimal) {
                            temperature = (temperature * 10) + (one - '0');
                        } else {
                            temperature = temperature + ((one - '0') / decimalPlace);
                            decimalPlace = decimalPlace * 10;
                        }
                    } else {
                        number = false;
                    }
                }
                if (negative) {
                    temperature = temperature * -1;
                }
                if (number) {
                    System.out.print("Please enter the unit (type \"C\" or \"F\"): ");
                    String unit = scnr.next();

                    while (!unit.equals("C") && !unit.equals("F")) {
                        System.out.println("Error! You have typed an invalid unit. Please try again.");
                        System.out.println("Please enter the unit (type \"C\" or \"F\"): ");
                        unit = scnr.next();
                    }

                    convertedTemp = convertTemperature(temperature, unit);

                    if (unit.equals("C")) {
                        System.out.printf("%.2f", temperature);
                        System.out.print("\u00B0C is equal to ");
                        System.out.printf("%.2f", convertedTemp);
                        System.out.println("\u00B0F");
                    } else {
                        System.out.printf("%.2f", temperature);
                        System.out.print("\u00B0F is equal to ");
                        System.out.printf("%.2f", convertedTemp);
                        System.out.println("\u00B0C");
                    }
                } else {
                    System.out.println("Error! You have typed an invalid temperature input. Please try again.");
                }
            }
        }
        System.out.print("Program is complete.");
        scnr.close();
    }
}

