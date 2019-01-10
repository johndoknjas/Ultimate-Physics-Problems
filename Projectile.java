import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class Projectile extends Parent
{
    Scanner scan = new Scanner (System.in);

    // For projectile motion, there is the resultant vector:

    private double v_resultant;

    // The angle at which it is launched, above the horizontal.

    private double angle;

    // Time:

    private double time;

    // VERTICAL VARIABLES:

    private double vyi; // Initial vertical velocity

    private double vyf; // Final vertical velocity

    private double dy;  // Vertical displacement when object hits ground

    // Vertical acceleration is the "g" variable in parent class (-9.8)

    // HORIZONTAL VARIABLES:

    private double vx; // Horizontal velocity

    private double dx; // Horizontal distance travelled.

    // CONSTRUCTOR:

    public Projectile()
    {
        v_resultant = random_int(101);

        angle = random_int(91); // Angle is set to any degree from 0 - 90.

        // Now to find vyi and vx from the above data:

        vyi = find_vertical_leg(v_resultant, angle); // Calls method in Parent class.

        vx = find_horizontal_leg(v_resultant, angle); // Calls method in Parent class.

        // The "toRadians" function is needed since Java works in radians, not degrees.

        /* The four remaining variables that have not got values:

           - time
           - vyf
           - dy
           - dx

            These must be set to values:
        */

        // Since the ball lands on the ground at the end of the problems, dy and vyf must always be some negative value.

        // So, I'll give dy a negative random value, and then solve for the three remaining vars:

        dy = -1 * random_int(1001); // 1000m high is the maximum height for the cliff.

        // Now to set vyf using the formula:  vf^2 = vi^2 + 2ad

        // I'll make a temporary var to hold vf^2:

        double temp_vyf = Math.pow(vyi, 2) + 2 * g * dy;

        vyf = Math.pow(temp_vyf, 0.5) * -1; // *-1 is needed to make vyf equal the negative (not positive) square root.
        // This is needed since vyf is always towards down when the object falls and hits the ground.

        // Now to set time using the formula:  vf = vi + at

        // Can be manipulated to express:  t = (vf - vi) / a

        // In this program it is:  t = (vyf - vyi) / g:

        time = (vyf - vyi) / g;

        // Now to set dx by using the formula:  d = v*t:

        dx = vx * time;

        // BAD CODE (since I didn't set dy and vyf first, to ensure that they were always negative):

        /*

        // One of them must be given a random value first... I'll give it to time:

        time = random_int(100);

        vyf = vyi + g * time; // g is the 9.8 constant... from the Parent class.

        dy = (vyi * time) + (0.5 * g * Math.pow(time, 2));

        dx = vx * time;

        */

        main();
    }

    public void main()
    {
        // Now to display the resultant velocity and the angle to the user:

        System.out.println ("An object is launched at " + angle + " degrees above the horizontal, with a velocity of "
                + v_resultant + " m/s");

        /* The next step is to display one of the following, and then get the user to solve one of the following:

           - time
           - vyf
           - dy
           - dx
        */

        // I'll put them in an Array:

        double list [] = {time, vyf, dy, dx};

        double revised_list [] = new double [3]; // This list will hold the updated list when one of the variables is
        // removed (after it is chosen to be told to the user).

        double chosen = pick_unknown(list); // This variable is the one that will be told to the user:

        if (chosen == time)
        {
            System.out.println ("The object is in the air for " + time + " seconds, and then lands on the ground.");

            revised_list = make_list(vyf, dy, dx); // Calling method in Parent Class.
        }

        else if (chosen == vyf)
        {
            System.out.println ("The object's vertical velocity when hitting the ground is " + vyf + " m/s");

            revised_list = make_list(time, dy, dx); // Calling method in Parent Class.
        }

        else if (chosen == dy)
        {
            System.out.println ("The object's vertical displacement when hitting the ground is: " + dy + " m");

            revised_list = make_list(time, vyf, dx); // Calling method in Parent Class.
        }

        else if (chosen == dx)
        {
            System.out.println ("The object's horizontal displacement when hitting the ground is: " + dx + " m");

            revised_list = make_list(time, vyf, dy); // Calling method in Parent Class.
        }

        System.out.print("\n");

        // Now I want to pick one of the remaining three doubles from revised_list, to be the unknown variable:

        double unknown = pick_unknown(revised_list);

        if (unknown == time)
        {
            System.out.println ("How long is the object in the air before hitting the ground?  Enter the numerical value for it, to as many digits as you can.");
        }

        else if (unknown == vyf)
        {
            System.out.println ("What is the object's vertical velocity when it hits the ground?  Enter the numerical value for it, to as many digits as you can.");
        }

        else if (unknown == dy)
        {
            System.out.println ("What is the object's vertical displacement when it hits the ground?  Enter the numerical value for it, to as many digits as you can.");
        }

        else if (unknown == dx)
        {
            System.out.println ("What is the object's horizontal displacement when it hits the ground?  Enter the numerical value for it, to as many digits as you can.");
        }

        // At this point you want to test if the user's input is close enough to "unknown".

        double user_answer = scan.nextDouble();

        if (close_enough(unknown, user_answer) == true)
        {
            correct_package(); // Calls the method in Parent class to complete what is done when the user gets a problem right.
        }

        else
        {
            System.out.println ("Incorrect.");
        }

        System.out.println ("The Correct answer is: " + unknown);

    }
}
