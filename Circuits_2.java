import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class Circuits_2 extends Parent
{
    // First step: Create variables of this class:

    private ArrayList <String> names = new ArrayList <String>(); // This will hold the names of all the variables, such as "light bulb 1's resistance".

    private ArrayList <String> display = new ArrayList <String>(); // This will hold the names of the variables that Java will display to the user on screen.

    private ArrayList <Double> values = new ArrayList <Double>(); // This will hold the values of all the variables, such as the value of bulb 1's resistance.

    private ArrayList <Double> display_values = new ArrayList <Double>(); // This will hold the values of the variables that Java will display to the user on screen.

    private boolean is_series; // Holds "true" if circuit is series, holds "false" if circuit is parallel.

    private int num_bulbs; // Holds how many bulbs there will be.


    private int test_counter = 0; // Counts how many times the info_is_good method is used.


    // Constructor (both lists will be set, as well as the is_series variable and num_bulbs var):

    public Circuits_2()
    {
        // is_series will be randomly set to "true" or "false":

        if (random_int(2) == 1) // Method called in parent class, to generate 0 or 1 (the 2 is the range of nums).
        {
            is_series = true;
        }

        else
        {
            is_series = false;
        }

        // Now to give num_bulbs a value:

        num_bulbs = random_int(1, 3); // At least 1 bulb, at most 3 bulbs.


        // The first for loop will put in the names and values for Voltage of each bulb in the lists:

        // The number of iterations will equal the number of bulbs:

        for (int i = 1; i <= num_bulbs; i++)
        {
            names.add("voltage of bulb " + i);

            // The value of the current bulb will be set to a random val from 1 to 50:

            values.add((double)random_int(1, 50));

            // Now, in case the circuit is parallel, the voltage of each bulb will equal each other:

            // If this is the case, the current voltage must equal the voltage of the first bulb (at index 0 of values list):

            if (is_series == false)
            {
                values.set(values.size() - 1, values.get(0));  // size - 1 is index of current element in list.

                // Even if the current bulb is the first one, it will just be set to itself.
            }
        }

        // The second for loop will put the names and values for Current of each bulb in the lists:

        for (int i = 1; i <= num_bulbs; i++)
        {
            names.add("current of bulb " + i);

            // The value of the current bulb will be set to a random val from 1 to 50:

            values.add((double)random_int(1, 50));

            // Now, in case the circuit is series, the current of each bulb will equal each other:

            // If this is the case, the current of the current bulb must equal the current of the first bulb.

            if (is_series == true)
            {
                values.set(values.size() - 1, values.get(0 + num_bulbs));

                // size - 1 is index of current element in list.
                // 0 + num_bulbs is the index for the first current (amps) value in the list.
            }
        }

        // The third for loop will put the names and values for Resistance of each bulb in the lists:

        for (int i = 1; i <= num_bulbs; i++)
        {
            names.add("resistance of bulb " + i);

            // The value for the current bulb's resistance is calculated from its voltage and current:

            // Ohms Law:  R = V / I

            // To do this, I need to figure out which elements in the ArrayList are the bulb's voltage and current.


            // Voltage would be at the index of current element - (2 * num_bulbs).

            // Current would be at the index of current element - num_bulbs.

            int bulb_index = values.size(); // Not values.size() - 1, since I haven't created the new element yet.  The element isn't part of the array yet, making
            // the Array 1 smaller than it would be.

            Double bulb_voltage = values.get(bulb_index - (2 * num_bulbs));

            Double bulb_current = values.get(bulb_index - num_bulbs);

            // Now I have the current bulb's voltage and current.  I can add the resistance value to the values list:

            values.add(bulb_voltage / bulb_current);
        }



        // Next step is to put total voltage, total current, and total resistance in the lists:

        // temp vars that will be stored in values list:

        double total_voltage = 0;

        double total_current = 0;

        double total_resistance = 0;


        if (is_series == true)
        {
            // total_voltage equals the sum of all the voltage values in the values list (Vo = V1 + V2 + V3).

            // I must find the indexes of each of the voltage values in the values list.  To do this, I'll find the
            // indexes of all the Strings in the names list that start with "v".  Then, I'll add the element of that
            // index in the values list to total_voltage.

            for (int i = 0; i < names.size(); i++)
            {
                if (names.get(i).substring(0, 1).equals("v")) // If first character of current String is "v":
                {
                    total_voltage += values.get(i);
                }
            }

            // total_current equals any one of the current values in values list (Io = I1 = I2 = I3).

            // Algorithm for this is the same as the for loop above, except it breaks after one if statement runs.

            for (int i = 0; i < names.size(); i++)
            {
                if (names.get(i).substring(0, 1).equals("c")) // If first character of current String is "c":
                {
                    total_current = values.get(i);

                    break;
                }
            }

        }

        else // meaning is_series = false, and the circuit is parallel:
        {
            // Total voltage = any voltage value in values list (Vo = V1 = V2 = V3):

            for (int i = 0; i < names.size(); i++)
            {
                if (names.get(i).substring(0, 1).equals("v")) // If first character of current String is "v":
                {
                    total_voltage = values.get(i);

                    break;
                }
            }

            // Total current = sum of each current value in values list (Io = I1 + I2 + I3):

            for (int i = 0; i < names.size(); i++)
            {
                if (names.get(i).substring(0, 1).equals("c")) // If first character of current String is "c":
                {
                    total_current += values.get(i);
                }
            }
        }

        // total_resistance equals total_voltage / total_current.  It doesn't matter if the circuit is series or
        // parallel, which is why the following statement takes place after both of the above if/else statements.

        total_resistance = total_voltage / total_current;

        // Now to add to the lists:

        values.add(total_voltage);
        names.add("voltage of the entire circuit");

        values.add(total_current);
        names.add("current of the entire circuit");

        values.add(total_resistance);
        names.add("resistance of the entire circuit");


        show_circuit_type(); // Calling a method in this class to tell the user the type of circuit and how many bulbs there are.


        // Now to call the main method of this class ("runner") to display some of the variables to the user and get
        // the user to try to find the unknown variables:

        runner();
    }


    public void runner() // main method
    {
        Scanner scan = new Scanner (System.in);

        display.clear(); // Good to always clear this, in case the previous method run did not give good Strings to display list (user didn't have enough info).

        display_values.clear(); // Clear the list of values as well, since if the method is repeating again, the old display_values list must have been bad.


        // The first step is to display x+1 number of variables, for x number of bulbs:

        // If there are 2 bulbs, I will display 3 variables.



        for (int i = 1; i <= num_bulbs + 1; i++) // num_bulbs + 1 since I want to display those many variables to user.
        {
            int index = random_int(names.size()); // index is set to a random index in names list.

            // Now, I want to make sure that the chosen variable of "names" is not already in the "display" list:

            if (in_list(display, names.get(index)) == true)
            {
                i--;

                // Loop iterates again, incrementing i to the value it was.
            }

            else
            {
                // Add the element at names and values to the display lists:

                display.add(names.get(index));

                display_values.add(values.get(index));
            }
        }

        // Now to check if the variables in the display lists are sufficient for the user to solve the remaining
        // variables of the circuit.  To do this, I'll use my "is_info_good" method, which returns true if the
        // variables are enough info for the user:


        if (is_info_good() == false)
        {
            // So, the info in the display lists will not give the user enough info.

            // The runner method we're in now must be repeated from scratch:

            test_counter++;

            runner();
        }

        else // Meaning that the info in the display lists are enough for the user to solve the rest of the variables.
        {
            // The rest of the program is in this "else" statement:

            // If the program made it to this point, that means the info in the display lists are sufficient enough.

            // Now I will want to display the lists to the user:

            for (int i = 0; i < display.size(); i++)
            {
                // I'll need to know what units should be displayed for the current variable:

                String units = "";

                if (display.get(i).substring(0, 1).equals("v"))
                {
                    units = "V";
                }

                else if (display.get(i).substring(0, 1).equals("c"))
                {
                    units = "A"; // for Amperes - unit of current.
                }

                else // meaning the first character is "r" for resistance:
                {
                    units = "Ohms";
                }

                System.out.println ("The " + display.get(i) + " is " + display_values.get(i) + " " + units);
            }

            System.out.println ("\n\n");

            // At this point you have displayed the appropriate number of variables to the user.  Now, you
            // want to get the user to solve three of the variables of the circuit.  Use a for loop to run through the
            // lists, displaying names.get(i) and getting user to solve for values.get(i).

            boolean got_correct = true; // variable stores if the user got all the questions correct in the following loop:

            ArrayList <String> answers = new ArrayList <String>(); // This list stores the correct answers for the three problems asked to the user in the while loop.

            for (int i = 1; i <= 3; i++) // Loop runs 3 times, since 3 questions will be asked to the user.
            {
                // In this loop, a random index will be chosen.  This index of the names list will be displayed to user,
                // and the user will have to type in the correct value for the parallel index in the values list.

                int index = -1; // index will be given a random value below, in while loop.

                // If the user enters a wrong value, got_correct is set to false and the loop is exited with break command.

                // Any of the unknown variables displayed to the user must not be in the "display" lists (since those were already told to the user).

                while (true) // while loop in case the chosen index yields one of the variables in the "display" lists:
                {
                    index = random_int(names.size());

                    if (in_list(display, names.get(index)) == false)
                    {
                        // So, the chosen variable is not in the display lists.  This is good:

                        break;
                    }
                }


                answers.add("The " + names.get(index) + " = " + values.get(index));

                System.out.println ("Enter the value for " + names.get(index) + ", to as many digits as you can:");


                double user_answer = scan.nextDouble();


                if ( close_enough(values.get(index), user_answer) == false) // calling method in Parent class.
                {
                    // So, user got the answer wrong:

                    got_correct = false;

                    System.out.println("Incorrect.");

                    break; // while loop is exited, since user got a question wrong.
                }

                // If program reaches this point, user got the question correct.  I must remove the index from the lists
                // and then the while loop will iterate again:

                names.remove(index);

                values.remove(index);
            }


            // If the user got all the answers correct, I will display the correct_package:

            if (got_correct == true)
            {
                correct_package();
            }

            // Now to tell the user what the correct answers were.  If the user only did one question and got it wrong,
            // I'll want to use the singular form of "answers" and use "was" instead of "were":

            if (answers.size() == 1)
            {
                System.out.println("\nThe correct answer was:\n");
            }

            else
            {
                System.out.println("\nThe correct answers were:\n");
            }


            // Now use a for-each loop to display the 3 correct answers in the "answers" ArrayList:

            for (String solution: answers)
            {
                System.out.println(solution);
            }

            System.out.println("\n");

            System.out.println ("info_is_good method had to retry: " + test_counter + " times.");
        }
    }





    public boolean is_info_good()
    {
        // This method checks if the variables in the "display" ArrayList (what will be told to the user) are enough
        // to solve for all the variables in "names" ArrayList.  Essentially, can the user solve the rest of the
        // variables with what is provided to them?

        // "true" is returned if the info in "display" is enough, "false" is returned if it is not.


        // I want to edit "display" in this method, but I don't want to change the Strings that it holds back in the
        // main "runner" method.  So, I'll create a duplicate list, by adding Strings one at a time:

        ArrayList <String> display_2 = new ArrayList <String>();

        for (String current: display)
        {
            display_2.add(current);
        }

        // Now display_2 contains the same Strings as display.  I will only be using "display_2" and "names" from this
        // point forwards in this method.


        /* Algorithm to find if the info in "display_2" is enough:
         *
         * Run a for-loop through the "names" list.
         * At the current String, check if it can be found from the Strings in the display_2 list (few ways to do this):
         *    - If the String itself is in "display_2" list, then obviously it can be found in names.
         *    - If there are the right vars in "display_2".  Ex: If the current String in "names" is for voltage_total of a series circuit, then
         *         if current_total and resistance_total are in "display_2", or all the other voltage vars, then it is enough info.
         *
         * If the current String in "names" can be figured out, add it to "display_2".  However, you should only not do
         *  this if the String is already in "display_2".  No point in adding it twice to the list.
         *
         * Run through the loop for the number of variables there are, plus 20 or something.  Just to make sure all the
         * variables get the chance to be set.
         *
         * Now the for loop is eventually exited.  The next step is to check if all the Strings in "names" exist in
         * "display_2".
         *
         * If they do, that means all the variables were able to be solved.  Return true.
         * Else, return false.

         */

        // I will use two for loops.  An outside loop that runs 20 times or so, and an inner for-each loop that
        // performs the above algorithm:

        int num_iterations = (num_bulbs + 1) * 3 + 20; // three vars for each bulb, and the total section.  The "20" is additional iterations just in case.


        for (int i = 1; i <= num_iterations; i++)
        {
            for (String current: names)
            {
                // First, I will check if current is already in "display_2" list, using a method in the Parent Class:

                if (in_list(display_2, current) == true)
                {
                    // Loop will just run again and move on to the next String - the current String is already in "display_2".
                }

                else // Rest of loop is in here:
                {
                    // First character of current String:

                    String first_char = first_character(current); // calling method in Parent Class.

                    // Last character of the current String:

                    String last_char = last_character(current); // calling method in Parent class.


                    // At this point, there are two ways that a variable can be known.  Either there are enough of its
                    // type (ex, voltage) in other bulbs, or the other two variables of its bulb are known (ex,
                    // voltage and current are known for its bulb, so it (resistance) can be known).

                    // I'll check if the two other variables of its bulb are known first:

                    String other_1 = ""; // Holds the first character of the first other variable of the current bulb.

                    String other_2 = ""; // Holds the first character of the second other variable of the current bulb.

                    if (first_char.equals("v"))
                    {
                        other_1 = "c";

                        other_2 = "r";
                    }

                    else if (first_char.equals("c"))
                    {
                        other_1 = "v";

                        other_2 = "r";
                    }

                    else // meaning first_char = "r":
                    {
                        other_1 = "v";

                        other_2 = "c";
                    }

                    // A variable of the same bulb will have a different first character, but always have the same
                    // last character - eg, bulb 1 vars all end with "1" character.

                    int counter = 0; // This variable holds how many other variables of its bulb are in "display".

                    // Run a loop through "display":

                    for (String temp: display_2)
                    {
                        // Methods from Parent Class will be used:

                        if (first_character(temp).equals(other_1) && last_character(temp).equals(last_char))
                        {
                            counter++;
                        }

                        else if (first_character(temp).equals(other_2) && last_character(temp).equals(last_char))
                        {
                            counter++;
                        }
                    }

                    // If counter is at least 2, that means the other 2 variables of the current bulb must be in the
                    // "display" list.  So, the current String var can be solved.

                    if (counter >= 2)
                    {
                        display_2.add(current); // Since the current String can be solved.

                        // For - each loop now iterates again and moves onto the next String in "names".
                    }

                    else
                    {
                        // Now to check if there are enough vars of the same type as "current" in other bulbs:

                        if (first_char.equals("r"))
                        {
                            // If the current String is a resistance variable, all of the other resistance variables
                            // must be in the display list for it to be figured out (regardless of circuit type):

                            int num = num_Strings_list(display_2, "r");

                            // num holds number of other resistance variables in "display" list.

                            if (num >= num_bulbs)
                            {
                                display_2.add(current);
                            }
                        }

                        else if (first_char.equals("c"))
                        {
                            int num = num_Strings_list(display_2, "c");

                            if (is_series == true)
                            {
                                // Circuit is series:

                                if (num >= 1)
                                {
                                    display_2.add(current);
                                }

                            }

                            else if (is_series == false)
                            {
                                // Circuit is parallel:

                                if (num >= num_bulbs)
                                {
                                    display_2.add(current);
                                }

                            }
                        }

                        else // meaning first char = "v":
                        {
                            int num = num_Strings_list(display_2, "v");

                            if (is_series == true)
                            {
                                // Circuit is series:

                                if (num >= num_bulbs)
                                {
                                    display_2.add(current);
                                }

                            }

                            else if (is_series == false)
                            {
                                // Circuit is parallel:

                                if (num >= 1)
                                {
                                    display_2.add(current);
                                }

                            }
                        }
                    }

                }

            }
        }

        // Now the entire nestled loop is ended.

        // Now to check if all the Strings in "names" exist in "display".  If so, that means all the variables were
        // able to be figured out with the info provided to the user:

        for (String current: names)
        {
            if (in_list(display_2, current) == false)
            {
                return false;
            }
        }

        // If program made it through the for loop without returning "false", that means all the variables were
        // in "display" list.  So, "true" is returned:

        return true;
    }



    public void show_circuit_type()
    {
        if (is_series == true) // so circuit is a series circuit:
        {
            if (num_bulbs == 1)
            {
                System.out.println ("There is " + num_bulbs + " light bulb in a series circuit.\n");
            }

            else // meaning circuit has more than 1 bulb:
            {
                System.out.println ("There are " + num_bulbs + " light bulbs in a series circuit.\n");
            }
        }

        else // meaning circuit is a parallel circuit:
        {
            if (num_bulbs == 1)
            {
                System.out.println ("There is " + num_bulbs + " light bulb in a parallel circuit.\n");
            }

            else
            {
                System.out.println ("There are " + num_bulbs + " light bulbs in a parallel circuit.\n");
            }
        }
    }

}

