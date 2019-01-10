import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

import java.util.Timer;

public class Main extends Parent
{
    private static double correct_num = 0;  // The variable that stores how many problems the user got right.
    private static double attempted_num = 0; // The variable that stores how many problems the user attempted.

    // This program will have a collection of different types of physics problems in it, such as gravitation,
    // kinematics, electric circuits, etc.

    // Each of these types of problems will have their own class, with their own variables (one or two of which must
    // be solved by the user).

    public static void main(String[] args)
    {
        Properties tool = new Properties ("", "", 99.994, 99.996, 9999.994, 9999.996); // The arguments I sent are purely arbitrary - they mean nothing.

        // The above variable "tool" is simply a way for the Main class to influence the Properties class.  Through
        // "tool", which is an object of the Properties class, Main can get and set static variables of the Properties class.

        // An instantiated object can be used to set static variables of a class, since that static variable will be the same for all other objects.

        // Currently I will be using this "tool" variable to set the "problem_type" variable of the Properties class
        // after the user chooses which type of problem they want to do (like momentum, energy, gravitation, etc).

        Scanner scan = new Scanner (System.in);

        // Tell user this important piece of info before they start solving problems:

        System.out.println("For all of the problems, assume that the downward direction is negative.  +/- signs matter in your answers. \n\n");

        // Now to make an ArrayList of the types of problems available to the user:

        ArrayList <String> list = new ArrayList <String>();

        list.add("Gravitation");

        list.add("Kinematics");

        list.add("Projectile");

        list.add("Momentum");

        list.add("Inclined plane");

        list.add("Circuits");

        // NOTE:  Keep adding to this list as you are able to provide more types of physics problems.

        boolean did_problem = true;

        // This variable holds whether or not the user did a problem.  If it is true, the program will display the
        // user's updated percentage score after finishing the problem.  This variable is false when the user enters an
        // invaid problem option and so does not get to attempt a problem.

        // A while loop that will contain the rest of the program (this allows the user to repeat the program after finishing):

        while (true)
        {
            did_problem = true;

            // Now to display this list of problems to the user, using a for-each loop:

            System.out.println ("Type in one of the types of problems listed below: \n");

            for (String temp: list)
            {
                System.out.println(temp);
            }

            // Create a var that stores what kind of problem the user would like to do:

            String problem_type = scan.nextLine();

            // Before going further, I want to make this String in the format of the Strings in the above list, so that
            // they can be compared.  The format is: first letter uppercased, everything else lowercased.

            String temp = ""; // This String will store the improved String (in correct format) at the end of the loop,
            // and then "problem_type" will be set to it:

            for (int i = 0; i < problem_type.length(); i++)
            {

                if (i == 0) // First character, so it must be made uppercase:
                {
                    temp += problem_type.substring(i, i+1).toUpperCase();
                }

                else // Meaning any other character, so it must be lowercase:
                {
                    temp += problem_type.substring(i, i+1).toLowerCase();
                }
            }

            // The loop has ended, so I'll set "problem_type" to the "temp" var:

            problem_type = temp;

            // Now to create an object in the class of the problem type the user entered:

            // "problem" is the object here, and it will be used throughout the rest of this program.

            if (problem_type.equals(list.get(0))) // Gravitation is at index 0 of list.
            {
                tool.set_problem_type("Gravitation");

                Gravitation problem = new Gravitation();

                if (problem.get_is_correct() == true)
                {
                    correct_num ++;
                }
            }

            else if (problem_type.equals(list.get(1)))  // Kinematics is at index 1 of list.
            {
                tool.set_problem_type("Kinematics");

                Kinematics problem = new Kinematics();

                if (problem.get_is_correct() == true)
                {
                    correct_num ++;
                }
            }

            else if (problem_type.equals(list.get(2)))  // Projectile is at index 2 of list.
            {
                tool.set_problem_type("Projectile");

                Projectile problem = new Projectile();

                if (problem.get_is_correct() == true)
                {
                    correct_num ++;
                }
            }

            else if (problem_type.equals(list.get(3)))  // Momentum is at index 3 of list.
            {
                tool.set_problem_type("Momentum");

                Momentum problem = new Momentum();

                if (problem.get_is_correct() == true)
                {
                    correct_num ++;
                }
            }

            else if (problem_type.equals(list.get(4)))  // Incline is at index 4 of list.
            {
                tool.set_problem_type("Incline");

                Incline problem = new Incline();

                if (problem.get_is_correct() == true)
                {
                    correct_num ++;
                }
            }

            else if (problem_type.equals(list.get(5)))  // Circuits is at index 5 of list.
            {
                tool.set_problem_type("Circuits");

                Circuits_2 problem = new Circuits_2();

                if (problem.get_is_correct() == true)
                {
                    correct_num ++;
                }
            }

            // NOTE: Keep adding to this list of else if statements for problem classes as the list of problems you have grows.

            // else statement for if the user does not enter a String that matches one of the physics problems options:

            else // Ex, user enters "apple"
            {
                // The user entered an invalid option and so did not get to do a problem:

                System.out.println("\nYou did not enter a valid option.\n");

                did_problem = false;
            }

            // At this point the program has finished since the rest of the code was taken care of in the classes.

            // I'll only want to execute the following code block in the if statement if the user did a problem:

            if (did_problem == true)
            {
                attempted_num ++; // Since the user just finished a problem.

                // I'll tell the user their current percentage_score:

                double percentage = (correct_num / attempted_num) * 100;

                System.out.println ("The current percent of problems you have correct is: " + percentage + "%");

            }

            // Now I'll ask the user whether they want to repeat the program (the big while loop the program's in now):

            System.out.println ("To try another problem, press the enter key.");

            String again = scan.nextLine();

            if (!again.equals(""))
            {
                // Since the user didn't press the enter key, the program breaks out of the while loop and doesn't run again:

                break;
            }

        }

        // At this point the big while loop has stopped, so the program is done.  I will now tell the user their
        // percentage score, assuming any problems were attempted in the first place:

        if (attempted_num > 0) // Meaning the user must have attempted some problems.
        {
            double total_percentage = (correct_num / attempted_num) * 100;

            System.out.println ("\nThe percent of problems you got correct in this session is: " + total_percentage + "%");

            // Now I'd like to display the correct_num in a fraction over attempted_num to the user.

            // I want to display correct_num and attempted_num as integers (since the user couldn't have gotten, let's say, "half" a problem right / wong):

            int correct_int_version = (int) correct_num;

            int attempted_int_version = (int) attempted_num;

            // Now to create a String to hold the fraction "dash" between numerator and denominator.  The # of dashes
            // it will have will eqaul the number of digits in attempted_int_version:

            String dash = "";

            int attempted_length = num_digits(attempted_int_version); // Calling method in Parent class.

            // Now to give "dash" var the appropriate number of dashes:

            for (int i = 1; i <= attempted_length; i++)
            {
                dash += "-";
            }

            // Another String must also be created to push the numerator to the right by a certain number of spaces,
            // since it could have less digits than the attempted denominator, and numerator should be centralized.

            String spaces = "";

            // I need to find the difference in the length of "dash" of fraction and length of numerator:

            int difference = dash.length() - num_digits(correct_int_version); // Calling method in Parent.

            // To centralize the numerator, "half of the difference" spaces will be added before the numerator:

            for (int i = 1; i <= difference / 2; i++)
            {
                spaces += " ";
            }

            System.out.println("\n\t\t\t" + spaces + correct_int_version + "\n\t\t\t" + dash + "\n\t\t\t" + attempted_int_version);

            // TEST:

            System.out.println ("\n\n" + tool.get_problem_type());
        }

        else // So the user must have not attempted any problems at all, if attempted_num is not > 0.
        {
            System.out.println("\nYou attempted no problems in this session.");
        }
    }
}
