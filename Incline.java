import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class Incline extends Parent
{
    Scanner scan = new Scanner(System.in);

    // This class holds the program for Inclined Plane problems.

    // Each of my variables will be objects of the class: "Variable".  The objects will hold both the name of the var
    // it is, as well as the value.

    // For example, an object that holds acceleration would hold the value for acceleration (randomly chosen), as well
    // as the name (String "acceleration").

    private Variable angle = new Variable();

    private Variable force_gravity = new Variable();

    private Variable force_normal = new Variable();

    private Variable force_y = new Variable();

    private Variable force_x = new Variable();

    private Variable force_friction = new Variable();

    private Variable mass = new Variable();

    private Variable net_acceleration = new Variable();

    private Variable coefficient_friction = new Variable();

    private Variable net_force = new Variable();

    private Variable force_applied = new Variable();

    // NOTE:  force_friction and force_x work down the incline.  force_applied works up the incline.

    public Incline()
    {
        // Now to give the objects' variables ("value" and "name") values:

        // I will give angle, mass, force_applied, and coefficient of friction random values.  Everything else will be derived:

        // These four are the independent variables:

        angle.set_value(random_int(1, 90));

        angle.set_name("angle");

        angle.set_unit("degrees");

        mass.set_value(random_int(1, 100));

        mass.set_name("mass");

        mass.set_unit("kg");

        // I'll use a while loop to set coefficient_friction.  This is just because in case it is set to 0,
        // I want to run the loop again:

        while (true)
        {
            coefficient_friction.set_value(Math.random()); // Any decimal number is fine.

            if (coefficient_friction.get_value() != 0) // This is what I want - coefficient to not be zero.
            {
                break;
            }
        }

        coefficient_friction.set_name("coefficient of friction");

        coefficient_friction.set_unit(""); // has no unit, is just a number.

        force_applied.set_value(random_int(100));

        force_applied.set_name("applied force");

        force_applied.set_unit("N");

        // Now I'll want to make force_applied a negative value (working down the incline) 33% of the time.

        int random = random_int(3); // Generates a number from 0 - 2.

        if (random == 0)
        {
            force_applied.change_negative();
        }

        /* Rest of variables (all these will be dervied):

           - force_gravity

           - force_y

           - force_x

           - force_normal

           - force_friction

           - net_force

           - net_acceleration

        */

        // force_gravity:

        force_gravity.set_value(Math.abs(mass.get_value() * g)); // Just dealing with magnitude

        force_gravity.set_name("force of gravity");

        force_gravity.set_unit("N");

        // force_y:

        force_y.set_value(find_horizontal_leg(force_gravity.get_value(), angle.get_value())); // force_y is horizontal to angle on inclined plane diagram.

        force_y.set_name("y_component of force of gravity");

        force_y.set_unit("N");

        // force_x:

        force_x.set_value(find_vertical_leg(force_gravity.get_value(), angle.get_value())); // force_x is vertical to angle on inclined plane diagram.

        force_x.set_name("x_component of force of gravity");

        force_x.set_unit("N");

        // force_normal:

        force_normal.set_value(force_y.get_value());

        force_normal.set_name("normal force");

        force_normal.set_unit("N");

        // force_friction:

        force_friction.set_value(force_normal.get_value() * coefficient_friction.get_value());

        force_friction.set_name("force of friction");

        force_friction.set_unit("N");

        // net_force:

        // For net_force, it is tricky.  The calculation depends on if force_applied or force_x is greater:

        if (force_applied.get_value() > force_x.get_value())
        {
            net_force.set_value(force_applied.get_value() - force_x.get_value() - force_friction.get_value());

            // At this point, if net_force is negative, even though force_applied was greater than force_x, it means
            // force_friction pushed it under zero.  Since force_friction cannot be greater than net motion,
            // force_friction must be adjusted:

            if (net_force.get_value() < 0)
            {
                force_friction.set_value(force_applied.get_value() - force_x.get_value());

                net_force.set_value(0);
            }
        }

        // If force_x > force_applied:

        else if (force_x.get_value() > force_applied.get_value())
        {
            net_force.set_value(force_x.get_value() - force_applied.get_value() - force_friction.get_value());

            // At this point, if net_force is negative, even though force_x was greater than force_applied, it means
            // force_friction pushed it under zero.  Since force_friction cannot be greater than net motion,
            // force_friction must be adjusted:

            if (net_force.get_value() < 0)
            {
                force_friction.set_value(force_x.get_value() - force_applied.get_value());

                net_force.set_value(0);
            }
        }

        net_force.set_name("net force");

        net_force.set_unit("N");

        // net_acceleration:

        net_acceleration.set_value(net_force.get_value() / mass.get_value());

        net_acceleration.set_name("net acceleration");

        net_acceleration.set_unit("m/s^2");

        // Now I need to give the vectors that point downwards or down the incline negative signs:

        // net_force and net_acceleration and force_friction:

        if (force_x.get_value() > force_applied.get_value()) // so net_force and net_acceleration are down the incline:
        {
            net_force.change_negative();

            net_acceleration.change_negative();

            // Since net_force is negative here, force_friction must be positive since it resists the negative.
        }

        else
        {
            // Meaning net_force is positive.  Since friction resists it, friction must be negative.

            force_friction.change_negative();
        }

        // Now to set Fg, Fx, and Fy to negative values:

        force_gravity.change_negative();

        force_x.change_negative();

        force_y.change_negative();

        main();

    }

    public void main()
    {
        System.out.println ("NOTE: If a vector has a negative sign, it means it is either working straight down, or down the incline.\n");

        if (force_applied.get_value() >= 0)
        {
            System.out.println ("A person is pushing an object up an incline.\n");
        }

        else // Meaning force_applied is negative (down the incline):
        {
            System.out.println ("A person is pushing an object down an incline.\n");
        }

        // Now to put all the Variable objects in 3 lists:

        // Two objects from list1 will be displayed.

        // One object from list2 will be displayed.

        // One object from list3 will be displayed.

        // This is so because each list has a type of variable that the user must know.

        ArrayList <Variable> list1 = new ArrayList <Variable>();

        list1.add(angle);

        list1.add(force_gravity);

        list1.add(force_normal);

        list1.add(force_y);

        list1.add(force_x);

        list1.add(mass);

        // Now two objects will be randomly chosen from list1 to be told to the user, and then removed from list1:

        // NOTE:  If Fg or mass is chosen, the other should be removed (since they are directly related thru 9.8).  Knowing both does not help the user, since
        // knowing one is sufficient.

        // ALSO:  If Fn or Fy is chosen, the other should be removed (since only one needs to be known).

        for (int i = 1; i <= 2; i++)
        {
            int index = random_int(list1.size());

            Variable temp = list1.get(index);

            // Now to display temp's name and value:

            System.out.println ("The " + temp.get_name() + " is " + temp.get_value() + " " + temp.get_unit());

            list1.remove(index);

            // Check if temp is Fg or mass:

            if (temp.get_name().equals("mass"))
            {
                // Now run a for-each loop thru the list and remove the "Fg" object:

                int position = 0;

                for (Variable current: list1)
                {
                    if (current.get_name().equals("force of gravity"))
                    {
                        list1.remove(position);

                        break;
                    }

                    position++;
                }
            }

            else if (temp.get_name().equals("force of gravity"))
            {
                // Now run a for-each loop thru the list and remove the "mass" object:

                int position = 0;

                for (Variable current: list1)
                {
                    if (current.get_name().equals("mass"))
                    {
                        list1.remove(position);

                        break;
                    }

                    position++;
                }
            }

            // Check for Fn or Fy:

            else if (temp.get_name().equals("normal force"))
            {
                // Now run a for-each loop thru the list and remove the "force_y" object:

                int position = 0;

                for (Variable current: list1)
                {
                    if (current.get_name().equals("y_component of force of gravity"))
                    {
                        list1.remove(position);

                        break;
                    }

                    position++;
                }
            }

            else if (temp.get_name().equals("y_component of force of gravity"))
            {
                // Now run a for-each loop thru the list and remove the "normal force" object:

                int position = 0;

                for (Variable current: list1)
                {
                    if (current.get_name().equals("normal force"))
                    {
                        list1.remove(position);

                        break;
                    }

                    position++;
                }
            }
        }

        // Now to create the second list and display one object from there:

        ArrayList <Variable> list2 = new ArrayList <Variable>();

        list2.add(force_friction);

        list2.add(coefficient_friction);

        int position = random_int(list2.size());

        Variable temp = list2.get(position);

        // Now to display temp's name and value:

        System.out.println ("The " + temp.get_name() + " is " + temp.get_value() + " " + temp.get_unit());

        list2.remove(position);

        // Create a third list and display one object from there:

        ArrayList <Variable> list3 = new ArrayList <Variable>();

        list3.add(force_applied);

        list3.add(net_force);

        list3.add(net_acceleration);

        int place = random_int(list3.size());

        Variable temporary = list3.get(place);

        // Now to display temp's name and value:

        System.out.println ("The " + temporary.get_name() + " is " + temporary.get_value() + " " + temporary.get_unit());

        list3.remove(place);

        // Now I'll want to set one of the remaining objects in list 2 or list 3 for the over to solve.  The objects
        // in list 1 are too straight forward to solve, with the info I've given the user.

        // I'll combine list 2 and list 3:

        for (Variable current: list3)
        {
            list2.add(current);
        }

        // Now to randomly choose one of the objects to be the unknown variable:

        int index = random_int(list2.size());

        Variable unknown = list2.get(index);

        System.out.println ("\nThe unknown variable is the " + unknown.get_name());

        System.out.println ("Enter the value for it, to as many digits as you can.  If it is a vector working downwards, include a negative sign.");

        double user_answer = scan.nextDouble();

        if(close_enough(unknown.get_value(), user_answer) == true)
        {
            correct_package();
        }

        else
        {
            System.out.println ("Incorrect.");
        }

        System.out.println ("The correct answer is: " + unknown.get_value());
    }
}
