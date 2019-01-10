import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class Kinematics extends Parent
{
    Scanner scan = new Scanner (System.in);

    private double velocity_final;

    private double velocity_initial;

    private double acceleration;

    private double time;

    private double displacement;


    // Constructor(s):

    public Kinematics()
    {
        // I'll set three of the above variables to random values, and then use the Kinematics equations to derive
        // the values for the two remaining variables:

        set_time();

        set_velocity_initial();

        set_acceleration();


        // Now to calculate the values for displacement and time:

        velocity_final = velocity_initial + (acceleration * time);

        displacement = (velocity_initial * time) + (0.5 * acceleration * Math.pow(time, 2));


        main(); // Call the main method in this Class to execute the rest of the program.
    }

    public void main()
    {
        // Now to make a String for each of the five variables.  These will be used to tell the user what the known
        // variables are:

        String vf_string = ("The final velocity of the object is: " + velocity_final + " m/s");

        String vi_string = ("The initial velocity of the object is: " + velocity_initial + " m/s");

        String a_string = ("The acceleration of the object is: " + acceleration + " m/s^2");

        String t_string = ("The time the object spends moving is: " + time + " s");

        String d_string = ("The change in position of the object is: " + displacement + " m");


        // Now to store these five Strings in an ArrayList:

        ArrayList <String> list = new ArrayList <String>();

        list.add(vf_string);

        list.add(vi_string);

        list.add(a_string);

        list.add(t_string);

        list.add(d_string);


        list = display_strings(list, 3);

        // Calling the method in the parent class that will display 3 of the Strings in list, and then set list to the
        // updated ArrayList the method returns(with the 3 random elements removed).


        // At this point, 3 of the elements from list have been displayed, and these 3 elements are now removed from
        // list.  list only contains the two remaining Strings that weren't displayed to the user.

        // One of these Strings must be chosen as the unknown one, that the user must solve.  Java must also be told
        // what the unknown variable is.


        String chosen = pick_unknown(list);

        // This calls the method in the parent class to randomly pick one of the Strings in list to be the unknown variable.


        // Now to figure out which String was chosen.  This is how I will figure out which variable the user must solve:

        double unknown; // The variable the user must solve.


        if (chosen.equals(vf_string))
        {
            print_unknown("final velocity"); // Calling the method in Parent class to tell the user what the unknown var is.

            unknown = velocity_final;
        }

        else if (chosen.equals(vi_string))
        {
            print_unknown("initial velocity");  // Calling the method in Parent class to tell the user what the unknown var is.

            unknown = velocity_initial;
        }

        else if (chosen.equals(a_string))
        {
            print_unknown("acceleration");  // Calling the method in Parent class to tell the user what the unknown var is.

            unknown = acceleration;
        }

        else if (chosen.equals(t_string))
        {
            print_unknown("time");  // Calling the method in Parent class to tell the user what the unknown var is.

            unknown = time;
        }

        else // meaning: (chosen.equals(d_string))
        {
            print_unknown("displacement");  // Calling the method in Parent class to tell the user what the unknown var is.

            unknown = displacement;
        }


        // Now I need to let the user enter their answer for what the unknown variable is:

        double user_answer = scan.nextDouble();

        // Now to use the method in the Parent class to test if the user's answer is sufficiently close enough to the
        // exact answer (the unknown var):

        if (close_enough(unknown, user_answer) == true)
        {
            correct_package();
        }

        else
        {
            System.out.println ("Incorrect.");
        }

        System.out.println ("The Correct answer is: " + unknown);

    }


    // METHODS:



    // MUTATORS:

    public void set_time() // Gives time a random value from 0 - 100:
    {
        time = random_int(100);
    }

    public void set_velocity_initial() // Gives initial velocity a random value from 0 - 100:
    {
        velocity_initial = random_int(100);
    }

    public void set_acceleration() // Gives acceleration a random value from -100 to 100:
    {
        acceleration = random_int(-100, 200);
    }



}
















