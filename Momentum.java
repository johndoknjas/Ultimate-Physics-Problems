import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;
import java.math.BigDecimal;

public class Momentum extends Parent
{
    Scanner scan = new Scanner(System.in);

    // The following list will hold the chosen type of list in Parent class (subatomic, everyday, or cosmic):

    private static ArrayList <Properties> list_objects = new ArrayList <Properties>();

    // I will create two objects of a seperate "Mass" class that will hold the characteristics of each mass before the
    // collision between them.

    private Mass one;

    private Mass two;

    // The following variables are the momentum properties of when the two masses combine after colliding:

    private double combined_momentum;

    private double combined_angle;

    private double combined_quadrant; // The quadrant the combined masses end up in.

    public Momentum()
    {
        int val = (int) (Math.random() * 3);

        if (val == 0)
        {
            list_objects = subatomic;
        }

        else if (val == 1)
        {
            list_objects = everyday;
        }

        else // val = 2
        {
            list_objects = cosmic;
        }

        // Now list_objects has been chosen.  I'll now pick a random object from it to use for Mass one:

        int index = (int) (Math.random() * list_objects.size());

        Properties temp = list_objects.get(index);

        // Now to use the variables of temp to call Mass one's constructor:

        one = new Mass(temp.get_name(), temp.get_mass(), temp.get_speed(), temp.get_movement(), temp.get_mass_as_String(), temp.get_speed_as_String());

        // Now to remove the chosen element from the list_objects ArrayList:

        list_objects.remove(index);

        // Now to pick a random object from the list to use for Mass two:

        int index_2 = (int) (Math.random() * list_objects.size());

        Properties temp2 = list_objects.get(index_2);

        // Now to use the variables of temp2 to call Mass two's constructor:

        two = new Mass(temp2.get_name(), temp2.get_mass(), temp2.get_speed(), temp2.get_movement(), temp2.get_mass_as_String(), temp2.get_speed_as_String());

        main();
    }

    public void main()
    {
        // Tell the user the information about both masses:

        // First mass:

        System.out.println (capitalize_first(one.get_name()) + " has a mass of " + one.get_mass_as_String() + " kg."); // capitalize_first is a method in Parent - capitalizes the first letter.

        System.out.println ("It " + one.get_movement() + " at an angle of " + one.get_angle() + " degrees "
                + one.get_direction() + ", with a speed of " + one.get_velocity_as_String() + " m/s");

        System.out.print("\n");

        // Second mass:

        System.out.println (capitalize_first(two.get_name()) + " has a mass of " + two.get_mass_as_String() + " kg."); // capitalize_first is a method in Parent.

        System.out.println ("It " + two.get_movement() + " at an angle of " + two.get_angle() + " degrees "
                + two.get_direction() + ", with a speed of " + two.get_velocity_as_String() + " m/s");

        System.out.print("\n");

        // The next step is to add up the vertical momentum from both masses and to add up the horizontal momentum
        // from both masses:

        double combined_vertical_momentum = one.get_vertical_momentum() + two.get_vertical_momentum();

        double combined_horizontal_momentum = one.get_horizontal_momentum() + two.get_horizontal_momentum();

        // From these two values, the combined_momentum variable and combined_angle variable can be figured out.

        // The tricky part is figuring out the angle, since it is different depending which quadrant the
        // resultant momentum is in.

        combined_momentum = find_hypotenuse(combined_vertical_momentum, combined_horizontal_momentum);

        // To find combined_angle, I will first just find the reference angle:

        // Use the method in Parent class to find angle between combined_momentum (hypotenuse) and the horizontal
        // (adjacent):

        combined_angle = inverse_cos(Math.abs(combined_horizontal_momentum), Math.abs(combined_momentum));

        // The Math.abs thing is to only get an angle within 0 - 90 degrees (not negative, or more than 90).

        // I'm just looking for the reference angle, not the entire angle if it's in quads 2, 3, or 4.

        // Now I need to figure out which quadrant the combined_momentum is in.  This can be done by examining
        // the combined_vertical_momentum and combined_horizontal_momentum:

        if (combined_vertical_momentum < 0) // So quads 3 or 4
        {
            if (combined_horizontal_momentum < 0) // Quad 3
            {
                combined_quadrant = 3;

                combined_angle += 180;
            }

            else // Quad 4
            {
                combined_quadrant = 4;

                // The reference angle is currently combined_angle.  The entire angle around the whole unit circle
                // would be 360 degrees - reference angle:

                combined_angle = 360 - combined_angle;

                // Remember angles in quads 1 and 3 are simply reference_angle + 0 degrees or 180 degrees.

                // Angles in quads 2 and 4 are either 180 degrees - reference angle or 360 degrees - reference angle.
            }
        }

        else // So quads 1 or 2
        {
            if (combined_horizontal_momentum < 0) // Quad 2
            {
                combined_quadrant = 2;

                combined_angle = 180 - combined_angle;

                // Angle in quad 2 is 180 - reference_angle.
            }

            else // Quad 1
            {
                combined_quadrant = 1;

                // combined_angle needs no adjusting.
            }
        }

        // Now the quadrant of the combined_momentum has been figured out, and the angle has been set to its proper
        // value, depending on which quadrant it was.

        // At this point, user does their thing:

        // I want to display Mass one's name.  However, if it starts with an "a" prefix, I'll want to remove it and
        // replace it with "The":

        String name_first = one.get_name(); // I'll be displaying Mass one's name with this String.

        if (first_character(name_first).equals("a"))
        {
            name_first = remove_character(name_first, 0);

            // At this point, if "n" is the new first letter of the String, that means the previous prefix was "an".

            // I'll want to remove this too, if this is the case:

            if (first_character(name_first).equals("n"))
            {
                name_first = remove_character(name_first, 0);
            }

            // Now to make "The" the new prefix of name_first:

            name_first = add_prefix(name_first, "The");
        }

        // Now to capitalize the first letter of name_first, just in case this was not done already:

        name_first = capitalize_first(name_first);

        // I also want to display Mass two's name.  If it starts with an "a", I'll replace it with "the":

        String name_second = two.get_name(); // I'll be displaying Mass two's name with this String.

        if (first_character(name_second).equals("a"))
        {
            name_second = remove_character(name_second, 0);

            // At this point, if "n" is the new first letter of the String, that means the previous prefix was "an".

            // I'll want to remove this too, if this is the case:

            if (first_character(name_second).equals("n"))
            {
                name_second = remove_character(name_second, 0);
            }

            // Now to make "the" the new prefix of name_second:

            name_second = add_prefix(name_second, "the");
        }

        System.out.println (name_first + " and " + name_second + " collide and stick together at the origin point.");

        System.out.println ("Enter the magnitude of their combined momentum below, to as many digits as you can.");

        // TEST (DELETE):

        System.out.println (get_sig_figs(one.get_mass_as_String()));

        System.out.println (get_sig_figs(one.get_velocity_as_String()));

        System.out.println (get_sig_figs(two.get_mass_as_String()));

        System.out.println (get_sig_figs(two.get_velocity_as_String()));

        double user_answer = scan.nextDouble();

        if (close_enough(combined_momentum, user_answer) == true)
        {
            // Now the user must also enter the correct angle:

            System.out.println ("Correct!  Now enter the angle of their combined momentum, relative to the East arm.  Enter as many digits as you can.");

            double user_answer_angle = scan.nextDouble();

            if (close_enough (combined_angle, user_answer_angle) == true)
            {
                correct_package();
            }

            else
            {
                System.out.println("Incorrect.");
            }
        }

        else
        {
            System.out.println("Incorrect.");
        }

        System.out.println ("The correct answer is: " + combined_momentum + " at " + combined_angle + " degrees.");
    }
}
