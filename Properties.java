import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

// NOTE:  This class cannot extend Parent because the Parent class, in its constructor, creates objects of this Properties class.

// If this class extended Parent, it would reference Parent's constructor, and then Parent's constructor creates objects
// in this class.  It would be an endless loop (Stack Overflow error).

// This is fine though, since the only method I use in this class from Parent was the random_int.  I can copy the method
// from parent into this class and create an identical method to use.  This method is at the bottom of this class.

public class Properties extends Parent
{
    // This class holds the properties (name, speed of movement, name of movement, mass) of objects being moved in problem
    // classes (such as a ball, boulder, etc).

    private String name; // Holds a String for the name of the object ("snail", "boulder", "ball", etc).

    private String movement; // Holds a String for how the object moves ("crawls", "rolls", "flies", etc).

    private double mass; // Holds a double for the mass of the object.

    private double speed; // Holds a double for the object's speed, which is randomly set. Slower objects
    // (like a snail) would have a smaller range of speed values (like random_int(1) ).

    private String mass_as_String; // Holds the value of mass, just with the number stored as a String.
    // This makes it easier to manipulate its length.

    private String speed_as_String;  // Holds the value of speed, just stored in the format of a String.

    private static String problem_type = ""; // This static variable holds the problem type that the user has currently selected from Main.

    public Properties(String nam, String move, double min_mass, double max_mass, double min_speed, double max_speed)
    {
        name = nam;

        movement = move;

        // SETTING MASS:

        // Now to use a while loop to set "mass".  If "mass" is not within the "min_mass" and "max_mass" params, loop runs again:

        while (true)
        {
            double range = max_mass - min_mass;

            mass = random_double(min_mass, range); // Calls random_double method of this class.

            if (mass >= min_mass && mass <= max_mass) // So "mass" is in between min_mass and max_mass (it is good):
            {
                break;
            }
        }

        // Now to round the mass value to two decimal places.

        // NOTE:  If a number is greater than 1, Java will only express it in scientific notation when it becomes
        // >= to 1.0 * 10^7, or 1.0E7

        // If a number is a decimal, Java will express it in scientific notation when it becomes
        // <= 9.9999... * 10^-4 or 9.9999...E-4

        // The reason for Java expressing decimals in scientific notation sooner is that decimals need those zeros
        // which act as place holders.  This takes up more digit spaces.

        // I will use the above info to decide when to use my "scientific round" method and when to use my normal "round" method, for both mass and speed:

        if (mass <= 9.99999 * Math.pow(10, -4) || mass >= 1.0 * Math.pow(10, 7)) // So the number will be expressed in scientific notation by Java:
        {
            // I'll use my "scientific_round" method, which
            // rounds to two decimal places if a number is in scientific notation form (like 3.53432E8).

            mass = scientific_round(mass, 100); // 100 for the hundredths decimal spot.
        }

        else // meaning mass will be expressed as a normal number by Java:
        {
            mass = round(mass, 100);
        }

        mass_as_String = double_to_String(mass, 2); // 2 for 2 decimal places.

        // SETTING SPEED:

        // Now to use a while loop to set "speed".  If "speed" is not within the "min_speed" and "max_speed" params, loop runs again:

        while (true)
        {
            double range = max_speed - min_speed;

            speed = random_double(min_speed, range); // Calls random_double method of this class.

            if (speed >= min_speed && speed <= max_speed)
            {
                break;
            }
        }

        // Now to round the speed value to two decimal places.

        if (speed <= 9.99999 * Math.pow(10, -4) || speed >= 1.0 * Math.pow(10, 7)) // So the number will be expressed in scientific notation by Java:
        {
            // I'll use my "scientific_round" method, which
            // rounds to two decimal places if a number is in scientific notation form (like 3.53432E8).

            speed = scientific_round(speed, 100); // 100 for the hundredths decimal spot.
        }

        else // meaning speed will be expressed as a normal number by Java:
        {
            speed = round(speed, 100);
        }

        speed_as_String = double_to_String(speed, 2); // 2 argument is for 2 decimal places.
    }

    // METHODS:

    // ACCESSORS:

    public String get_name()
    {
        return name;
    }

    public String get_movement()
    {
        return movement;
    }

    public double get_mass()
    {
        return mass;
    }

    public String get_mass_as_String()
    {
        return mass_as_String;
    }

    public double get_speed()
    {
        return speed;
    }

    public String get_speed_as_String()
    {
        return speed_as_String;
    }

    public String get_problem_type()
    {
        return problem_type;
    }

    // MUTATORS:

    public void set_name(String nam)
    {
        name = nam;
    }

    public void set_movement(String move)
    {
        movement = move;
    }

    public void set_mass(double mas)
    {
        mass = mas;
    }

    public void set_speed(double spee)
    {
        speed = spee;
    }

    public void set_mass_as_String(String text)
    {
        mass_as_String = text;
    }

    public void set_problem_type(String text)
    {
        problem_type = text;
    }

    // OTHER METHODS:

    //random_int method (copied from Parent class, since this class cannot extend Parent):

    public static double random_double(double min, double range)
    {
        double value;

        value = (Math.random() * range + min);

        return value;
    }

    // Method that rounds a value to the nearest decimal place parameter.

    // Ex: 1000 for decimal place parameter means the method rounds to the nearest thousandth.

    /*

    public static double round(double num, int decimal_place)
    {
        num = num * decimal_place;

        num = Math.round(num);

        num = num / decimal_place;

        return num;
    }

    */

    // Method that rounds a scientific notation number to a certain number of decimal places.  3.561213E9 would become:  3.56E9  if the specified decimal place was 100.

    public double scientific_round(double num, int decimal_place)
    {
        // Parameters:

        // num is the number being sent.

        // decimal_place is the decimal place that the number will be rounded to.

        if (num >= 1) // So the number is great (essentially, it's just not a decimal).
        {
            int counter = 0;

            // I will use a for loop that divides num by 10 until it becomes a number between 1 and 10:

            while (num >= 10)
            {
                num /= 10;

                counter ++;
            }

            // Now num is a number like, for example:  1.582729323

            // I want to round this number to the specified decimal places - I will use my round method from this class:

            num = round(num, decimal_place);

            // Now bring num back up to its original value, except now it has been rounded:

            while (counter > 0)
            {
                num *= 10;

                counter --;
            }
        }

        // IF THE NUMBER IS SMALL (A DECIMAL):

        else // so num is less than 1, meaning it is small / a decimal.
        {
            int counter = 0;

            // I will use a for loop that multiplies num by 10 until it becomes a number between 1 and 10:

            while (num < 1)
            {
                num *= 10;

                counter ++;
            }

            // Now num is a number like, for example:  1.582729323

            // I want to round this number to the specified decimal places - I will use my round method from this class:

            num = round(num, decimal_place);

            // Now bring num back down to its original value, except now it has been rounded:

            while (counter > 0)
            {
                num /= 10;

                counter --;
            }
        }

        return num;
    }

    public String replace_character(String text, int index, String replacement)
    {
        // Replaces the character at index in text, with the parameter replacement.

        // This will be done through breaking the String into three parts, and then assembling them back together again:

        String part_a = text.substring(0, index);

        String part_b = text.substring(index, index + 1);

        String part_c = text.substring(index + 1, text.length());

        // Now to replace part_b, the character that is to be replaced, with replacement:

        part_b = replacement;

        // Assemble the parts back together to form the revised text String:

        text = part_a + part_b + part_c;

        return text;
    }

    public String increase_by_one(String text, int index)
    {
        // This method increases the String at the index of text by 1.  "5" would be increased to "6" for example.

        // First to check if the index I'm currently on is either the decimal point or at -1.  These scenarios are
        // possible since the previous digits (to the right) could have been 9's, which meant the next digit to the
        // left had to be incremented by 1.

        // As a result, I could still be "going up the ladder", and could have arrived at the decimal point or index -1:

        if (index == -1)
        {
            // So I'm now at the point "before" the beginning of the entire number.  If I got here, it means the highest digit of the number
            // must have been "rounded up" from a 9 to 0.  This means I must add a 1 at the beginning of the number:

            String lead_digit = "1";

            text = lead_digit + text;

            // Now to return the text, since there's no further digits to go to the left of in the number:

            return text;
        }

        // The current index could be at the decimal point:

        if (text.substring(index, index + 1).equals("."))
        {
            // I'll just move the index one space to the left and call the method again:

            index -= 1;

            return increase_by_one(text, index);
        }

        ArrayList <String> nums = new ArrayList <String>();

        nums.add("0");
        nums.add("1");
        nums.add("2");
        nums.add("3");
        nums.add("4");
        nums.add("5");
        nums.add("6");
        nums.add("7");
        nums.add("8");
        nums.add("9");

        String replacement = ""; // This number will replace the digit at index (it will just be the next higher number).

        for (int i = 0; i < nums.size(); i++)
        {
            if (text.substring(index, index + 1).equals(nums.get(i)))
            {
                if (nums.get(i).equals("9"))
                {
                    replacement = "0";

                    text = replace_character(text, index, replacement);

                    // PROBLEM:  If this last digit "increases" from 9 to 0, the digit to the left of it must
                    // increase by 1 (since it's in a higher power of 10).  And if THIS higher digit used to be
                    // at 9 and then "increases" to 0, the next higher digit must increase by 1.  And so on, if this continues...

                    // Call this method again to make the digit to the left of the "index_last_digit" increase by
                    // one, since the current last digit above has "increased" from 9 to 0.

                    index -= 1; // Since I'm moving to the digit to the left of the current last digit.

                    return increase_by_one(text, index);
                }

                else // So the digit to be increased is not 9, meaning there are no further complications after increasing it.
                {
                    replacement = nums.get(i + 1);

                    text = replace_character(text, index, replacement);

                    return text;
                }
            }
        }

        // Program won't reach this point since the digit has to equal a number from 0-9, but I'm required to write in a return statement:

        return "Control shouldn't be able to reach this point.";
    }

    public String double_to_String(double num, int decimal_place)
    {
        // This method takes in a double, and returns it as a String.

        // The returned String is also cut off to the specified number of "decimal_places".

        String text = Double.toString(num); // text is the String that will be returned.

        // Now, if there are no digits after the "decimal_place" index, I'll just want to return "text" now:

        int length_String = text.length(); // This is the length of "text" now.

        int desired_length = 0; // This is the length I wanted, by sending by an argument to the "decimal_place" parameter.

        // The first part of "desired_length" is the whole number part:

        // I want to find the index of the decimal point in the String "text":

        int index_decimal = text.indexOf(".");

        desired_length += index_decimal + 1; // Now I have the length of the whole number part, and the decimal.

        // Now to add how many decimals I wanted:

        desired_length += decimal_place;

        // If the String contains "E", I'll want to add it and the numbers after the "E":

        if (text.contains("E") == true)
        {
            // Now to add the length of the text from the "E" to the end of the String:

            desired_length += (text.length() - text.indexOf("E"));
        }

        // If desired_length >= length_String, I will return "text" now:

        if (desired_length >= length_String)
        {
            return text;
        }

        // If the program reached this point, "text" is currently storing something like:  5.67999999999E11

        // The goal will be to make a text like this hold:  5.68E11 (presuming "decimal_place" equaled 2).

        // Now from here, I can find the index of the last decimal digit the user wants to keep:

        int index_last_digit = index_decimal + decimal_place; // parameter.

        // Now I'll want to increase the last digit, if the digit after it is greater than or equal to 5:

        String digit_after_last = text.substring(index_last_digit + 1, index_last_digit + 2);

        // Now I'll use a list of Strings to see if digit_after_last is greater than or equal to 5:

        ArrayList <String> nums = new ArrayList <String>();

        nums.add("0");
        nums.add("1");
        nums.add("2");
        nums.add("3");
        nums.add("4");
        nums.add("5");
        nums.add("6");
        nums.add("7");
        nums.add("8");
        nums.add("9");

        // Use a for loop to run through this list and see if digit_after_last is greater than or equal to "5":

        boolean indicator = false; // indicator is true if "digit_after_last" is greater than or equal to "5":

        for (int i = 5; i < nums.size(); i++) // Starting at index 5 in order to start at element "5":
        {
            if (digit_after_last.equals(nums.get(i)))
            {
                // Since digit_after_last is equal to a String greater than or equal to "5":

                indicator = true;

                break;
            }
        }

        // Now, if indicator = true, the digit after the last digit is greater than or equal to 5.

        // This means the last digit must be increased by 1.  To do this, I'll call my "increase_by_one" method
        // from this class.

        if (indicator == true)
        {
            text = increase_by_one(text, index_last_digit);
        }

        // Now to add all the numbers from the beginning of the text to the index of the last decimal place:

        String temp_text = text.substring(0, index_last_digit + 1);

        // Now to add the E"number" if it exists.  I can find out if "E" exists by using the "contains" String method:

        if (text.contains("E") == true)
        {
            // Now to add E and the number to text:

            temp_text += "E";

            // Now to find the index of "E", in order to add the number after it:

            int index_E = text.indexOf("E");

            temp_text += text.substring(index_E + 1, text.length());
        }

        // Now to set text to temp_text:

        text = temp_text;

        return text;
    }
}
