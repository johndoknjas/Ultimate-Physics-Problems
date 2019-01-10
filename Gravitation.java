import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class Gravitation extends Parent
{
    Scanner scan = new Scanner (System.in);

    // All four variables are initially set to -1, as an indication in some cases for which variable is still unset
    // (meaning it is the unknown one).

    private double force = -1;

    private double mass_one = -1;

    private double mass_two = -1;

    private double distance = -1;

    // Big G is an inherited variable in the Parent class.

    // Now to make a list of possible objects that could be used in the problem for mass_one and mass_two (like "snail, "boulder", "planet", etc).

    private ArrayList <Properties> list = new ArrayList <Properties>();

    // Constructor(s):

    public Gravitation()
    {
        // I will call the mutator methods for all four of the above variables, except one (this is what the user
        // must solve).  Which three methods I call is decided by a Math.random function:

        int random = (int) (Math.random() * 4); // Four possible outcomes.

        if (random == 0)
        {
            set_force();

            set_mass_one();

            set_mass_two();
        }

        else if (random == 1)
        {
            set_mass_one();

            set_mass_two();

            set_distance();
        }

        else if (random == 2)
        {
            set_mass_two();

            set_distance();

            set_force();
        }

        else // meaning random = 3:
        {
            set_distance();

            set_force();

            set_mass_one();
        }

        // Now to call the main method of this class... the constructor should just be used for setting variables, not
        // running the entire program.

        main();
    }

    public void main()
    {
        // Now to return the values of the variables that got set (all except one):

        tell_user();

        // Now to create a temporary variable that holds what the user types in, for their answer to the unknown variable:

        double user_answer = scan.nextDouble();

        // Now to figure out which of the variables is the unknown one, that the user is trying to solve:

        if (force < 0) // Since force has no value, it must have not been set yet (meaning it's the unknown variable).
        {
            force = find_force(); // This method returns what the correct, exact value of force should be.

            // Now to test if the "user_answer" variable is within 0.9999 or 1.0001 of the exact answer:

            if ( close_enough(force, user_answer) == true) // Method call to "close_enough" in Parent class.
            {
                correct_package(); // Calls the method in Parent class to complete what is done when the user gets a problem right.
            }

            else
            {
                System.out.println ("Incorrect.");
            }

            System.out.println ("The Correct answer is: " + force);
        }

        else if (mass_one < 0)
        {
            mass_one = find_mass_one(); // This method returns what the correct, exact value of mass_one should be.

            // Now to test if the "user_answer" variable is within 0.9999 or 1.0001 of the exact answer:

            if ( close_enough(mass_one, user_answer) == true) // Method call to "close_enough" in Parent class.
            {
                correct_package(); // Calls the method in Parent class to complete what is done when the user gets a problem right.
            }

            else
            {
                System.out.println ("Incorrect.");
            }

            System.out.println ("The Correct answer is: " + mass_one);
        }

        else if (mass_two < 0)
        {
            mass_two = find_mass_two(); // This method returns what the correct, exact value of mass_two should be.

            // Now to test if the "user_answer" variable is within 0.9999 or 1.0001 of the exact answer:

            if ( close_enough(mass_two, user_answer) == true) // Method call to "close_enough" in Parent class.
            {
                correct_package(); // Calls the method in Parent class to complete what is done when the user gets a problem right.
            }

            else
            {
                System.out.println ("Incorrect.");
            }

            System.out.println ("The Correct answer is: " + mass_two);
        }

        else // meaning distance < 0
        {
            distance = find_distance(); // This method returns what the correct, exact value of distance should be.

            // Now to test if the "user_answer" variable is within 0.9999 or 1.0001 of the exact answer:

            if ( close_enough(distance, user_answer) == true) // Method call to "close_enough" in Parent class.
            {
                correct_package(); // Calls the method in Parent class to complete what is done when the user gets a problem right.
            }

            else
            {
                System.out.println ("Incorrect.");
            }

            System.out.println ("The Correct answer is: " + distance);
        }
    }

    // METHODS:

    // MUTATORS:

    public void set_force() // Gives force variable a random value.
    {
        force = random_int(100);
    }

    public void set_mass_one() // Gives mass_one variable a random value.
    {
        mass_one = random_int(100);
    }

    public void set_mass_two()  // Gives mass_two variable a random value.
    {
        mass_two = random_int(100);
    }

    public void set_distance()  // Gives distance variable a random value.
    {
        distance = random_int(100);
    }

    // ACCESSORS:

    public void tell_user() // Tells the user in main what the values of the known variables are (except for the unknown one, that they have to solve).
    {
        String info = ""; // This is the String that will be returned to main.

        // If a variable has a value >= 0, it will be added to the String.  All the four variables start off at
        // -1, so if they have a negative value that means it is the one that has not been altered.

        String unknown = "The unknown variable is: ";

        // This String will tell the user which variable needs to be solved.
        // The variable that has a -1 value will be added to this:

        if (force >= 0)
        {
            info += ("Force is: " + force + " N  \n");
        }

        else
        {
            unknown += "force";
        }

        if (mass_one >= 0)
        {
            info += ("The first mass is: " + mass_one + " kg  \n");
        }

        else
        {
            unknown += "the first mass";
        }

        if (mass_two >= 0)
        {
            info += ("The second mass is: " + mass_two + " kg  \n");
        }

        else
        {
            unknown += "the second mass";
        }

        if (distance >= 0)
        {
            info += ("Distance is: " + distance + " m   \n");
        }

        else
        {
            unknown += "distance";
        }

        System.out.println (info);

        System.out.print ("\n");

        unknown += ".  Enter the value for it, with as many digits as you can.";

        System.out.println (unknown);
    }

    public double find_force() // This method returns what the correct value of force is:
    {
        return (G * mass_one * mass_two) / (Math.pow(distance, 2));
    }

    public double find_mass_one() // This method returns what the correct value of mass_one is:
    {
        return (force * Math.pow(distance, 2)) / (G * mass_two);
    }

    public double find_mass_two() // This method returns what the correct value of mass_two is:
    {
        return (force * Math.pow(distance, 2)) / (G * mass_one);
    }

    public double find_distance() // This method returns what the correct value of distance is:
    {
        double temp = (G * mass_one * mass_two) / force;

        return Math.pow (temp, 0.5);
    }
}
