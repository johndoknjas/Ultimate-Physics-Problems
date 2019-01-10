import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class Parent
{
    // NOTE:  Properties is now an extension of Parent, and it seems to run fine.  TO DO:

    // Put all of Properties' methods into Parent, so that all classes (including Properties) can reference it.

    // Make the METHODS in Parent normal, but the VARIABLES static (depending on the situation).

    // Check why the gravitation class is malfunctioning.

    // The "round" method of Properties is commented out, because there already is a method that does that in Parent.  Deal with this (just delete the round method in Propreties).

    // This class is the parent class of all different problem type classes (Gravitation, Kinematics, etc)

    private static int counter = 0; // This variable counts how many times this Parent class is called.
    // It is increased immediately in the Constructor.

    // Variables all the child classes will use for their problems (the Physics Constants):

    // These are all protected because child classes cannot access private variables in its parent's class.

    protected final double G = 6.67 * (Math.pow(10, -11));

    protected final double k = 9.00 * (Math.pow(10, 9));

    protected final double e = 1.60 * (Math.pow(10, -19));

    protected final double m_electron = 9.11 * (Math.pow(10, -31));

    protected final double m_proton = 1.67 * (Math.pow(10, -27));

    protected final double m_neutron = 1.67 * (Math.pow(10, -27));

    protected final double m_hydrogen = 1.67 * (Math.pow(10, -27));

    protected final double m_nitrogen = 2.33 * (Math.pow(10, -26));

    protected final double m_oxygen = 2.66 * (Math.pow(10, -26));

    protected final double m_sodium = 3.82 * (Math.pow(10, -26));

    protected final double m_iron = 9.27 * (Math.pow(10, -26));

    protected final double m_silver = 1.79 * (Math.pow(10, -25));

    protected final double m_gold = 3.27 * (Math.pow(10, -25));

    protected final double m_uranium = 3.95 * (Math.pow(10, -25));

    protected final double m_plutonium = 4.05 * (Math.pow(10, -25));

    protected final double c = 3.00 * (Math.pow(10, 8));

    protected final double pi = Math.PI;

    protected final double g = -9.8;

    protected boolean is_correct; // This variable stores whether or not the user got the current problem correct.

    // The following ArrayLists are lists of objects that can be used in the problems classes (like "snail",
    // "ball", "arrow", "Earth", etc).  They are instances of the "Properties" class, which contain the properties of
    // these objects (name, speed, mass, name of movement, etc).

    // There are three lists (subatomic objects, everyday objects, and cosmic objects).  I wouldn't want something
    // like an electron interacting with a planet in a problem (it wouldn't affect anything, like momentum of the planet):

    protected static final ArrayList <Properties> subatomic = new ArrayList <Properties>(); // Hold subatomic objects.

    protected static final ArrayList <Properties> everyday = new ArrayList <Properties>(); // Holds everyday objects.

    protected static final ArrayList <Properties> cosmic = new ArrayList <Properties>(); // Holds cosmic objects.

    // These will be given elements in the constructor.

    // CONSTRUCTOR:

    public Parent()
    {
        counter ++;

        // Now, if this is the first time Parent has been called, I'll want to add elements to the lists:

        if (counter == 1)
        {
            // subatomic list:

            subatomic.add(new Properties ("an electron", "moves", m_electron, m_electron, Math.pow(10, 7), 9 * Math.pow(10, 7)));

            subatomic.add(new Properties ("a proton", "moves", m_proton, m_proton, Math.pow(10, 4), 9 * Math.pow(10, 4)));

            subatomic.add(new Properties ("a neutron", "moves", m_neutron, m_neutron, Math.pow(10, 4), 9 * Math.pow(10, 4)));

            subatomic.add(new Properties ("a hydrogen atom", "moves", m_hydrogen, m_hydrogen, Math.pow(10, 4), 9 * Math.pow(10, 4)));

            subatomic.add(new Properties ("a nitrogen atom", "moves", m_nitrogen, m_nitrogen, Math.pow(10, 3), 9 * Math.pow(10, 3)));

            subatomic.add(new Properties ("an oxygen atom", "moves", m_oxygen, m_oxygen, Math.pow(10, 3), 9 * Math.pow(10, 3)));

            subatomic.add(new Properties ("a sodium atom", "moves", m_sodium, m_sodium, Math.pow(10, 3), 9 * Math.pow(10, 3)));

            subatomic.add(new Properties ("an iron atom", "moves", m_iron, m_iron, Math.pow(10, 3), 9 * Math.pow(10, 3)));

            subatomic.add(new Properties ("a silver atom", "moves", m_silver, m_silver, Math.pow(10, 2), 9 * Math.pow(10, 2)));

            subatomic.add(new Properties ("a gold atom", "moves", m_gold, m_gold, Math.pow(10, 2), 9 * Math.pow(10, 2)));

            subatomic.add(new Properties ("a uranium atom", "moves", m_uranium, m_uranium, Math.pow(10, 2), 9 * Math.pow(10, 2)));

            subatomic.add(new Properties ("a plutonium atom", "moves", m_plutonium, m_plutonium, Math.pow(10, 2), 9 * Math.pow(10, 2)));

            // everyday list:

            everyday.add(new Properties ("a bowling ball", "rolls", 3, 7, 4, 9));

            everyday.add(new Properties ("a shot put", "is rolled", 2, 7, 3, 8));

            everyday.add(new Properties ("a bike", "is ridden", 10, 17, 5, 13));

            everyday.add(new Properties ("a person", "runs", 30, 90, 3, 9));

            everyday.add(new Properties ("a cheetah", "dashes", 21, 72, 17, 26));

            everyday.add(new Properties ("a table", "is pushed", 50, 70, 1, 3));

            everyday.add(new Properties ("a shopping cart", "is pushed", 20, 32, 1, 5));

            everyday.add(new Properties ("a computer", "is pushed", 3, 14, 2, 8));

            everyday.add(new Properties ("a medicine ball", "is rolled", 4, 22, 2, 8));

            everyday.add(new Properties ("a dog", "runs", 5, 70, 5, 18));

            // cosmic list:

            // Objects that do not need "a" or "an" prefix before them:

            cosmic.add(new Properties ("Earth", "moves", 5.98 * Math.pow(10, 24), 5.98 * Math.pow(10, 24), Math.pow(10, 6), 9 * Math.pow(10, 6)));

            cosmic.add(new Properties ("the Moon", "moves", 7.35 * Math.pow(10, 22), 7.35 * Math.pow(10, 22), Math.pow(10, 8), 9 * Math.pow(10, 8)));

            cosmic.add(new Properties ("Mars", "moves", 6.39 * Math.pow(10, 23), 6.39 * Math.pow(10, 23), Math.pow(10, 7), 9 * Math.pow(10, 7)));

            cosmic.add(new Properties ("Jupiter", "moves", 1.90 * Math.pow(10, 27), 1.90 * Math.pow(10, 27), Math.pow(10, 3), 9 * Math.pow(10, 3)));

            cosmic.add(new Properties ("Saturn", "moves", 5.68 * Math.pow(10, 26), 5.68 * Math.pow(10, 26), Math.pow(10, 4), 9 * Math.pow(10, 4)));

            cosmic.add(new Properties ("Neptune", "moves", 1.02 * Math.pow(10, 26), 1.02 * Math.pow(10, 26), Math.pow(10, 4), 9 * Math.pow(10, 4)));

            cosmic.add(new Properties ("Uranus", "moves", 8.68 * Math.pow(10, 25), 8.68 * Math.pow(10, 25), Math.pow(10, 5), 9 * Math.pow(10, 5)));

            cosmic.add(new Properties ("Venus", "moves", 4.87 * Math.pow(10, 24), 4.87 * Math.pow(10, 24), Math.pow(10, 6), 9 * Math.pow(10, 6)));

            cosmic.add(new Properties ("Mercury", "moves", 3.29 * Math.pow(10, 23), 3.29 * Math.pow(10, 23), Math.pow(10, 7), 9 * Math.pow(10, 7)));

            cosmic.add(new Properties ("the Sun", "moves", 1.98 * Math.pow(10, 30), 1.98 * Math.pow(10, 30), Math.pow(10, 0), 9 * Math.pow(10, 0)));

            // Objects that do need "a" or "an" prefix before them:

            cosmic.add(new Properties ("a black hole", "moves", 1.98 * Math.pow(10, 30), 5.97 * Math.pow(10, 31), Math.pow(10, 0), 9 * Math.pow(10, 0)));

            cosmic.add(new Properties ("a star", "moves", 1.98 * Math.pow(10, 30), 5.97 * Math.pow(10, 31), Math.pow(10, 0), 9 * Math.pow(10, 0)));

            cosmic.add(new Properties ("an asteroid", "moves", Math.pow(10, 12), 9 * Math.pow(10, 13), Math.pow(10, 18), 9 * Math.pow(10, 19)));
        }
    }

    // Methods that the child "problem types" classes will use:

    // Method that rounds a value to the nearest decimal place parameter.

    // Ex: 1000 for decimal place parameter means the method rounds to the nearest thousandth.

    public double round(double num, int decimal_place)
    {
        num = num * decimal_place;

        num = Math.round(num);

        num = num / decimal_place;

        return num;
    }

    // Method that provides a random integer, less than the given "max" parameter:

    public int random_int(double max)
    {
        int value; // This will be returned.

        value = (int) (Math.random() * max);

        return value;
    }

    // Method that provides a random integer, larger than the "min" parameter and within the given range above the min:

    // NOTE: it shares the same name as the above method (I'm doing overloading due to this method having 2 params).

    public int random_int(double min, double range)
    {
        int value;

        value = (int) (Math.random() * range + min);

        return value;
    }

    public boolean close_enough(double exact_answer, double user_answer)
    {
        // This method figures out if user_answer is within 0.0001 of exact_answer.

        // true is returned if user_answer is in the allowed range, false is returned if it is not:

        // What I will do is create a variable that is 0.0001 of the exact answer:

        double error_margin = exact_answer * 0.0001;

        // If user_answer is within exact_answer +/- error_margin, user_answer is close enough.

        double max = exact_answer + error_margin;

        double min = exact_answer - error_margin;

        // user_answer must be between min and max.

        // In case of negative numbers, the "max" var would actually be smaller than the "min" var.  This case has
        // to be checked:

        if (max < min)
        {
            double temp = max;

            max = min;

            min = temp;

            // max and min have been successfully switched.
        }

        // Now to check if user_answer is in between min and max:

        if (user_answer <= max && user_answer >= min)

            return true;

        return false;
    }

    public ArrayList <String> display_strings(ArrayList <String> list, int num)
    {
        // This method randomly displays a certain number of Strings in the list param.

        // The number of Strings it displays is the "num" param.

        // To do this, I will use a for loop that runs "num" times.  Each iteration prints (and then deletes) one
        // String of the list randomly.

        for (int i = 1; i <= num; i++)
        {
            int current_length = list.size();

            // Now I need to pick a random element of the list.  I'll do this by sending current_length to the
            // random_int method from this class:

            int chosen_index = random_int(current_length);

            System.out.println(list.get(chosen_index));

            list.remove(chosen_index);
        }

        return list; // Returning the new list (since some elements got removed).
    }

    public String pick_unknown(ArrayList <String> list)
    {
        // This method randomly picks a String out of the list param to be the unknown variable that the user must
        // solve.  The method then returns this variable to main.

        int current_length = list.size();

        // Now I need to pick a random element of the list.  I'll do this by sending current_length to the
        // random_int method from this class:

        int chosen_index = random_int(current_length);

        return list.get(chosen_index);
    }

    public double pick_unknown(double list [])
    {
        // This method randomly picks a double out of the list param to be the unknown variable that the user must
        // solve.  The method then returns this variable to main.

        // Now I need to pick a random element of the list.  I'll do this by sending list's length to the
        // random_int method from this class:

        int chosen_index = random_int(list.length);

        return list[chosen_index];
    }

    public void print_unknown(String unknown)
    {
        // This method tells the user which variable they must solve:

        System.out.println ("\nType in the value for: " + unknown + ", to as many digits as you can.");
    }

    public boolean get_is_correct()
    {
        return is_correct;
    }

    public void correct_package()
    {
        // This method completes what happens when the user gets a problem right.  It tells the user they got the
        // problem right, and also makes the "is_correct" variable true.

        System.out.println ("Correct!");

        is_correct = true;
    }

    public double[] make_list(double a, double b, double c) // Three doubles passed in, will be made into a list.
    {
        // This method puts the three double parameters into a list, and then returns the list.

        double list [] = {a, b, c};

        return list;
    }

    public double find_vertical_leg(double hypotenuse, double angle)
    {
        // This method finds the vertical leg in a right triangle.

        double leg = hypotenuse * Math.sin(Math.toRadians(angle));

        return leg;
    }

    public double find_horizontal_leg(double hypotenuse, double angle)
    {
        // This method finds the horizontal leg in a right triangle.

        double leg = hypotenuse * Math.cos(Math.toRadians(angle));

        return leg;
    }

    public double find_hypotenuse(double a, double b)
    {
        // Finds the hypotenuse, given two legs of a right triangle:

        double hypotenuse = Math.pow (a, 2) + Math.pow (b, 2);

        hypotenuse = Math.pow (hypotenuse, 0.5);

        return hypotenuse;
    }

    public double inverse_sin(double opposite, double hypotenuse)
    {
        // This method takes in the values the opposite and hypotenuse legs, and outputs the angle between them.

        double angle = Math.asin(opposite / hypotenuse);

        angle = Math.toDegrees(angle); // Converting the angle from radians to degrees.

        return angle;
    }

    public double inverse_cos(double adjacent, double hypotenuse)
    {
        // This method takes in the values the adjacent and hypotenuse legs, and outputs the angle between them.

        double angle = Math.acos(adjacent / hypotenuse);

        angle = Math.toDegrees(angle); // Converting the angle from radians to degrees.

        return angle;
    }

    public double inverse_tan(double opposite, double adjacent)
    {
        // This method takes in the values the opposite and adjacent legs, and outputs the angle between them.

        double angle = Math.atan(opposite / adjacent);

        angle = Math.toDegrees(angle); // Converting the angle from radians to degrees.

        return angle;
    }

    public static int num_digits(int num)
    {
        // Method returns the number of digits in a positive integer sent up:

        int num_digits = 1;

        int max = 10;

        while (num >= max)
        {
            num_digits ++;

            max *= 10;
        }

        return num_digits;
    }

    public double [] delete_element(double [] list, int index)
    {
        // This method returns the list parameter, with the element at the index param removed.

        // To do this, a new list will be created and returned instead:

        double [] revised_list = new double [list.length - 1];

        // The revised_list is 1 element smaller than list, since the element at the index is not included.

        // Now I want to add each element of list to revised_list, with the exception of the element at the index param.

        int i = 0; // i represents the current position of the loop through list.

        int x = 0; // x represents the current position of the loop through revised_list.

        for (; i < list.length; i++)
        {
            if (i == index) // So if current position is at the element that must be axed.
            {
                i++;

                // If this happens on the last iteration, i will be equal to list.length, which will cause an
                // out of bounds error.  If this is the case, the loop must be immediately terminated:

                if (i == list.length)
                    break;
            }

            revised_list [x] = list [i];

            x++;
        }

        return revised_list;
    }

    public boolean in_list(ArrayList <String> list, String text)
    {
        // This method returns true if text is in list.  False if it is not in the list.

        // A for-each loop through list can be used to accomplish this:

        boolean var = false; // var holds whether or not text is in list.

        for (String current: list)
        {
            if (current.equals(text))
            {
                var = true; // Since text is equal to a String in list.

                break; // No need to continue searching through loop.
            }
        }

        return var;
    }

    public boolean list_contains_one(ArrayList <String> list, String one, String two, String three)
    {
        // This method returns true if list contains one of the String params.

        boolean var = false; // var holds whether list contains one of the String params.

        // I will make a for-each loop that goes through list.  If the current element equals one of the three
        // String vars, I will add 1 to the following counter variable:

        int counter = 0;

        // If counter = 1 by the end of the loop (no more, no less), boolean var = true.

        for (String current: list)
        {
            if (current.equals(one) || current.equals(two) || current.equals(three))
            {
                counter++;
            }
        }

        if (counter == 1) // Meaning the list contained just one of the String params.
        {
            var = true;
        }

        return var;
    }

    public boolean list_contains_one(ArrayList <String> list, String one, String two)
    {
        // This method returns true if list contains one of the String params.

        boolean var = false; // var holds whether list contains one of the String params.

        // I will make a for-each loop that goes through list.  If the current element equals one of the two
        // String vars, I will add 1 to the following counter variable:

        int counter = 0;

        // If counter = 1 by the end of the loop (no more, no less), boolean var = true.

        for (String current: list)
        {
            if (current.equals(one) || current.equals(two))
            {
                counter++;
            }
        }

        if (counter == 1) // Meaning the list contained just one of the String params.
        {
            var = true;
        }

        return var;
    }

    public int num_Strings_list(ArrayList <String> list, String first_letter)
    {
        // This method returns the number of Strings in list, starting with the first letter param.

        int counter = 0; // var stores how many of the Strings are in the list.

        for (String current: list)
        {
            if (first_letter.equals(current.substring(0, 1)))
            {
                counter++;
            }
        }

        return counter;
    }

    public String first_character(String text)
    {
        // This method returns the first character of a text:

        return text.substring(0, 1);
    }

    public String last_character(String text)
    {
        // This method returns the last character of a text:

        return text.substring(text.length() - 1, text.length());
    }

    public String add_prefix(String text, String prefix)
    {
        // This method adds the prefix at the beginning of text:

        String updated = "";

        updated += prefix;

        updated += text;

        return updated;
    }

    public String remove_character (String text, int index)
    {
        // This method removes the character at "index" of the text String, and returns the updated String:

        String first_half = text.substring(0, index);

        String second_half = text.substring(index + 1, text.length());

        String updated = first_half + second_half;

        return updated;
    }

    public String capitalize_first(String text)
    {
        // This method capitalizes the first letter of text, and then returns the updated String:

        String first_letter = text.substring(0, 1);

        first_letter = first_letter.toUpperCase();

        String updated = ""; // this String will be returned

        updated += first_letter;

        // Now to add all the letters of "text", except the first letter, to updated:

        if (text.length() > 1)
        {
            updated += text.substring(1, text.length());
        }

        return updated;
    }

    public int get_sig_figs(String num)
    {
        // Parameter takes in a number, in String format.

        // It then returns the number of sig figs in the number, in the form of an int.

        int sig_figs = 0; // Holds the number of sig figs in "num".  This variable will be returned.

        // The first step is to the remove the E"value" part of String num, if it exists:

        if (num.contains("E") == true)
        {
            // So, E exists in the num.  I must find the index of E in num:

            int index = num.indexOf("E");

            // Now to set "num" to all the text in "num" before the E:

            num = num.substring(0, index);
        }

        // Now the "E" part of num has been removed, if it existed.

        // The next step is to figure out if there is a decimal point in the number.  This is important, since whether
        // there is a decimal or not affects sig figs a lot (mainly the rules with zeros).

        if (num.contains(".") == true)
        {
            // This if statement will handle all the calculations with sig figs when there IS a decimal:

            // When there is a decimal, there are three scenarios:

            // First scenario: there is a zero before the decimal.

            // Second scenario: there is a positive number before the decimal.

            // Third scenario:  all the digits are zeroes (ex: 0.0000).  This is unlikely, but will pop up time to time.
            // This is a SUBSECTION of the first scenario.

            // FIRST SCENARIO:

            if (num.substring(0, 1).equals("0"))
            {
                // This is the scenario where there is a zero before the decimal.

                // Before proceeding further, I must check if all the digits are zeroes (third scenario):

                boolean flag = true; // if flag is true, all the digits are zeroes.

                for (int i = 0; i < num.length(); i++)
                {
                    // This loop runs through "num" and examines each character.  If a non zero or non decimal character
                    // is found, flag is set to false:

                    String current_character = num.substring(i, i+1);

                    if (current_character.equals("0") || current_character.equals("."))
                    {
                        // Loop repeats.
                    }

                    else // So, a non zero or non decimal was found:
                    {
                        flag = false;
                    }
                }

                // If flag is still true, that means there are only zeroes in the number (ex: 0.0000):

                // In this case, I'll make the number of sig figs simply the number of zeroes after the decimal:

                if (flag == true)
                {
                    // I'll remove the zero in front of the decimal, as well as the decimal itself:

                    num = num.substring(2, num.length());

                    sig_figs = num.length();

                    return sig_figs;
                }

                // CONTINUING WITH FIRST SCENARIO:

                // In this case, I only start counting sig figs when I reach the first non-zero number.

                // When reaching the first non-zero number, I will keep counting until I reach the end of the number.

                // I'll run a loop through the number, from left to right, that removes all 0's and the decimal point.
                // When I reach a non zero number, the loop stops and I'll start counting sig figs:

                while (true)
                {
                    String current_character = num.substring(0, 1);

                    if (current_character.equals("0") || current_character.equals("."))
                    {
                        // I must remove this current character from num, since it's either a zero or decimal point:

                        num = remove_character(num, 0);

                        // Loop runs again:
                    }

                    else // meaning the current character is NOT a zero or decimal point (ie, it is the first sig fig):
                    {
                        break;
                    }
                }

                // Now to start counting how many characters are left in num, since only sig figs are left:

                sig_figs = num.length();

                return sig_figs;
            }

            // SECOND SCENARIO:

            else // So, there is a positive number before the decimal:
            {
                // In this case, calculating sig figs is easy.  I just want to remove the decimal, and then find the
                // length of the num.

                int index_of_decimal = num.indexOf(".");

                num = remove_character(num, index_of_decimal);

                sig_figs = num.length();

                return sig_figs;
            }

        }

        else // meaning a decimal point does not exist in "num":
        {
            // This else statement will handle all the calculations with sig figs when there is NOT a decimal:

            // When there is not a decimal, zeros at the end of the number are not significant.

            // So, I will start at the end of "num", at the far right, and work backwards, removing any zeros in my path.

            // When I reach the first non-zero number, the loop will stop:

            while (true)
            {
                String current_character = num.substring(num.length() - 1, num.length());

                if (current_character.equals("0"))
                {
                    // I must remove this zero from "num":

                    num = remove_character(num, num.length() - 1);

                    // Loop runs again:
                }

                else // meaning the current character is not a zero (it is a significant figure).
                {
                    break;
                }
            }

            // Now to find the number of sig figs by just finding the length of "num" (since only sig figs are left in "num"):

            sig_figs = num.length();

            return sig_figs;
        }
    }
}
