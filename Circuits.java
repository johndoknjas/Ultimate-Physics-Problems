import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class Circuits extends Parent
{
    // First step: Create variables of this class:

    private ArrayList <String> names = new ArrayList <String>(); // This will hold the names of all the variables, such as "light bulb 1's resistance".

    private ArrayList <String> display = new ArrayList <String>(); // This will hold the names of the variables that the computer will display to the user on screen.

    private ArrayList <Double> values = new ArrayList <Double>(); // This will hold the values of all the variables, such as the value of bulb 1's resistance.

    private boolean is_series; // Holds "true" if circuit is series, holds "false" if circuit is parallel.

    private int num_bulbs; // Holds how many bulbs there will be.


    // Constructor (both lists will be set, as well as the is_series variable and num_bulbs var):

    public Circuits()
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


        // Now to call the main method of this class ("runner") to display some of the variables to the user and get
        // the user to try to find the unknown variables:

        runner();
    }


    public void runner() // main method
    {
        Scanner scan = new Scanner (System.in);

        display.clear(); // Good to always clear this, in case the previous method run did not give good Strings to display list (user didn't have enough info).



        // The next step is to display x+1 number of variables, for x number of bulbs:

        // If there are 2 bulbs, I will display 3 variables.

        // I will also have to make sure I don't display all 3 variables of one bulb (such as V1, R1, and I1).

        // I'll make a "bad" list, that holds the variables that aren't allowed to be displayed to the user
        // (if the two other variables of a bulb have already been displayed), or other cases.

        // Ex:  if Vo and Io have been displayed, Rt's name from names list would be added to he "bad" list.

        String taboo_letter = "";

        // This taboo var holds a first letter that a variable displayed to user isn't allowed to have.


        ArrayList <String> bad = new ArrayList <String>();


        // I should also make three counters for how many times a current variable, voltage variable, and resistance variable were displayed.

        // If it is a series circuit, I will only want to display a current var once (since Io = I1 = I2 etc).
        // I will only want to display voltage vars x number of times, where x = num of bulbs.  Not all voltage vars
        // should be displayed, that would not be useful to user, since they can figure out last one themselves.

        // If it is a parallel circuit, I will only want to display a voltage var once (since Vo = V1 = V2 etc).
        // I will only want to display current vars x number of times, where x = num of bulbs.  Not all current vars
        // should be displayed, that would not be useful to user, since they can figure out last one themselves.

        // For either type of circuit, only (num_bulbs) resistance variables should be displayed.  In both types of
        // circuits, the user can figure out the remaining resistance var with these many.

        int voltage_counter = 0;

        int current_counter = 0;

        int resistance_counter = 0;

        // I will increment the appropriate var at the end of each iteration of the for loop below.

        // If one of them surpasses the allowed quota for each type, taboo_2 will be set to the first character
        // of that var's type in names list.

        // Example:  if voltage_counter reaches num_bulbs for a series circuit, taboo_2 = "v".  If randomly chosen
        // index of names list then has a first character: "v", loop runs again with no change to i.


        for (int i = 1; i <= num_bulbs + 1; i++) // num_bulbs + 1 since I want to display those many variables to user.
        {
            int index = random_int(values.size()); // index is set to a random index in values list.


            // Now to run a for loop that checks if names(index) is in the "bad" list (list of Strings that must not
            // be displayed to user):

            boolean is_fine = true; // Holds if the chosen index is not in the "bad" list (if it "is fine"):

            for (String current: bad) // running a for loop through the "bad" list:
            {
                if (names.get(index).equals(current))  // So the chosen index is "bad" (cannot be displayed):
                {
                    is_fine = false;

                    break;
                }
            }


            if (is_fine == false || names.get(index).substring(0, 1).equals(taboo_letter))
            {
                // Loop must run again with no changes to i.  Since i will always be incremented:

                i--;

                // Loop now runs again, incrementing i and then running a new iteration.
            }

            else // So the chosen index is fine:
            {
                // Display the element at the index of the names and values list to the user.


                // Before doing this, I need to figure out the correct units to put after the value (V, Ohms, or A):

                // At the same time, I'll increment one of: voltage_counter, current_counter, or resistance_counter:


                String units = ""; // String var holds the units that will be displayed on screen after the current value.

                if (names.get(index).substring(0, 1).equals("v"))
                {
                    units = "V";

                    voltage_counter++;
                }

                else if (names.get(index).substring(0, 1).equals("c"))
                {
                    units = "A"; // for Amperes - unit of current.

                    current_counter++;
                }

                else // meaning the first character is "r" for resistance:
                {
                    units = "Ohms";

                    resistance_counter++;
                }


                System.out.println ("The " + names.get(index) + " is " + values.get(index) + " " + units);


                // Now to add Strings to the bad list and give taboo_letter a Sring to hold (if applicable):


                if (is_series == true)
                {
                    // Circuit is series:

                    if (voltage_counter == num_bulbs) // Max number of voltage vars that should be displayed to user.
                    {
                        taboo_letter = "v";
                    }

                    // Now to check if both V and R of any bulb (or total) have been displayed.  If this is the case, no
                    // I vars should be displayed of ANY bulb.  This is because the I var of that bulb can be figured
                    // out, and in a series circuit: Io = I1 = I2 = I3:

                    // I'll start with checking if V and R of total are out of the names list (meaning they've been displayed already):

                    if (in_list(names, "voltage of the entire circuit") == false && in_list(names, "resistance of the entire circuit") == false)
                    {
                        // So, both total voltage and total resistance are not in list, and have been displayed:

                        taboo_letter = "c"; // No current vars will be displayed.
                    }

                    // Now to check if V and R of any bulb are out of the names list (meaning they've been displayed already):

                    // This can be accomplished with a for loop:

                    for (int b = 1; b <= num_bulbs; b++)
                    {
                        if (in_list(names, "voltage of bulb " + b) == false && in_list(names, "resistance of bulb " + b) == false)
                        {
                            taboo_letter = "c";

                            break; // Found a pair of V and R already displayed, so no need to continue searching.
                        }
                    }


                    // Now to see what happens when one current var has been displayed.  In a series circuit,
                    // knowing one current var let's you automatically know all the other current vars.

                    if (current_counter == 1) // Only one current variable should be displayed to the user:
                    {
                        taboo_letter = "c";

                        // Now since one "current" variable was displayed, this means that user can figure out
                        // the other "current" vars on their own.  So, I will not want to display both the V and R
                        // of any one bulb.

                        // I'll start with the "total" vars (Vo, Io, and Rt):

                        if (list_contains_one(names, "voltage of the entire circuit", "resistance of the entire circuit") == true)
                        {
                            // So, if the list only contains either total voltage or total resistance:

                            // I want to make sure the one still in the list is not displayed to the user:

                            if (in_list(names, "voltage of the entire circuit") == true)
                            {
                                bad.add("voltage of the entire circuit");
                            }

                            else // meaning the total resistance var must be the only one in the list for the total section:
                            {
                                bad.add("resistance of the entire circuit");
                            }
                        }

                        // Now to test each of the bulbs' vars with a for loop:

                        for (int a = 1; a <= num_bulbs; a++)
                        {
                            if (list_contains_one(names, "voltage of bulb " + a, "resistance of bulb " + a) == true)
                            {
                                // So, the list only contains either current bulb's voltage or resistance.

                                // I want to make sure the one still in the list is not displayed to the user:

                                if (in_list(names, "voltage of bulb " + a) == true)
                                {
                                    bad.add("voltage of bulb " + a);
                                }

                                else // meaning the resistance var must be the only one in the list for current bulb:
                                {
                                    bad.add("resistance of bulb " + a);
                                }
                            }
                        }

                    }
                }

                else // is_series is false:
                {
                    // Circuit is parallel:

                    if (current_counter == num_bulbs) // Max number of current vars that should be displayed to user.
                    {
                        taboo_letter = "c";
                    }


                    // Now to check if both I and R of any bulb (or total) have been displayed.  If this is the case, no
                    // V vars should be displayed of ANY bulb.  This is because the V var of that bulb can be figured
                    // out, and in a series circuit: Vo = V1 = V2 = V3:

                    // I'll start with checking if I and R of total are out of the names list (meaning they've been displayed already):

                    if (in_list(names, "current of the entire circuit") == false && in_list(names, "resistance of the entire circuit") == false)
                    {
                        // So, both total current and total resistance are not in list, and have been displayed:

                        taboo_letter = "v"; // No voltage vars will be displayed.
                    }


                    // Now to check if I and R of any bulb are out of the names list (meaning they've been displayed already):

                    // This can be accomplished with a for loop:

                    for (int b = 1; b <= num_bulbs; b++)
                    {
                        if (in_list(names, "current of bulb " + b) == false && in_list(names, "resistance of bulb " + b) == false)
                        {
                            taboo_letter = "v";

                            break; // Found a pair of I and R already displayed, so no need to continue searching.
                        }
                    }


                    // Now to write what happens when one voltage var has been displayed (since in a Parallel
                    // circuit, Vo = V1 = V2 = V3).

                    if (voltage_counter == 1) // Should only display one voltage var in a parallel circuit.
                    {
                        taboo_letter = "v";


                        // Now since one "voltage" variable was displayed, this means that user can figure out
                        // the other "voltage" vars on their own.  So, I will not want to display both the I and R
                        // of any one bulb.

                        // I'll start with the "total" vars (Vo, Io, and Rt):

                        if (list_contains_one(names, "current of the entire circuit", "resistance of the entire circuit") == true)
                        {
                            // So, if the list only contains either total current or total resistance:

                            // I want to make sure the one still in the list is not displayed to the user:

                            if (in_list(names, "current of the entire circuit") == true)
                            {
                                bad.add("current of the entire circuit");
                            }

                            else // meaning the total resistance var must be the only one in the list for the total section:
                            {
                                bad.add("resistance of the entire circuit");
                            }
                        }

                        // Now to test each of the bulbs' vars with a for loop:

                        for (int a = 1; a <= num_bulbs; a++)
                        {
                            if (list_contains_one(names, "current of bulb " + a, "resistance of bulb " + a) == true)
                            {
                                // So, the list only contains either current bulb's current or resistance.

                                // I want to make sure the one still in the list is not displayed to the user:

                                if (in_list(names, "current of bulb " + a) == true)
                                {
                                    bad.add("current of bulb " + a);
                                }

                                else // meaning the resistance var must be the only one in the list for current bulb:
                                {
                                    bad.add("resistance of bulb " + a);
                                }
                            }
                        }
                    }
                }

                // In either type of circuit, if resistance_counter = num_bulbs, no more resistance variables should be
                // displayed:

                if (resistance_counter == num_bulbs)
                {
                    taboo_letter = "r";
                }


                // Now to remove the element at both indexes in the lists:

                names.remove(index);

                values.remove(index);


                // The final step before the loop runs again is to add something to the bad list, should the
                // requirements permit it.  I will call my method in this class to perform this task:

                bad.add(set_bad());
            }

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

            // If the user enters a wrong value, got_correct is set to false and the loop is exited with break command.

            int index = random_int(names.size());

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

        System.out.print("\n");
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

                    for (String temp: display)
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
                        display.add(current); // Since the current String can be solved.

                        // For - each loop now iterates again and moves onto the next String in "names".
                    }

                    else
                    {
                        // Now to check if there are enough vars of the same type as "current" in other bulbs:

                        if (first_char.equals("r"))
                        {
                            // If the current String is a resistance variable, all of the other resistance variables
                            // must be in the display list for it to be figured out (regardless of circuit type):

                            int num = num_Strings_list(display, "r");

                            // num holds number of other resistance variables in "display" list.

                            if (num >= num_bulbs)
                            {
                                display.add(current);
                            }
                        }

                        else if (first_char.equals("c"))
                        {
                            int num = num_Strings_list(display, "c");

                            if (is_series == true)
                            {
                                // Circuit is series:

                                if (num >= 1)
                                {
                                    display.add(current);
                                }

                            }

                            else if (is_series == false)
                            {
                                // Circuit is parallel:

                                if (num >= num_bulbs)
                                {
                                    display.add(current);
                                }

                            }
                        }

                        else // meaning first char = "v":
                        {
                            int num = num_Strings_list(display, "v");

                            if (is_series == true)
                            {
                                // Circuit is series:

                                if (num >= num_bulbs)
                                {
                                    display.add(current);
                                }

                            }

                            else if (is_series == false)
                            {
                                // Circuit is parallel:

                                if (num >= 1)
                                {
                                    display.add(current);
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
            if (in_list(display, current) == false)
            {
                return false;
            }
        }

        // If program made it through the for loop without returning "false", that means all the variables were
        // in "display" list.  So, "true" is returned:

        return true;
    }






    public void tester()
    {
        // This method just tests printing out the names and values lists in parallel with each other:

        for (int i = 0; i < values.size(); i++)
        {
            System.out.println (names.get(i) + "\n" + values.get(i) + "\n");
        }
    }


    public void display_circuit_type()
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


    public String set_bad()
    {
        // This method returns a "bad" variable that should not ever be displayed to the user.  When returned to the
        // main "runner" method of this class, it is added to the "bad" list.


        // First I want to see if only one of: Vo, Io, or Rt is in the names list.  If this is the case,
        // the remaining variable in the names lists must be set to "bad" (since the other two have already
        // been removed, since they must have been displayed to the user).

        // Calling method in parent class to check this:

        if (list_contains_one(names, "voltage of the entire circuit", "current of the entire circuit", "resistance of the entire circuit") == true)
        {
            // If the method returned true, that meant names list only contains one of the total variables.  The
            // other two are not in the list, since they have been displayed to the user.

            // Now I'll add the variable still in the names list to the "bad" list.  To figure out which
            // of the 3 variables is still in the list, I'll use my other method I made in parent:

            if (in_list(names, "voltage of the entire circuit") == true)
            {
                return "voltage of the entire circuit";
            }

            else if (in_list(names, "current of the entire circuit") == true)
            {
                return "current of the entire circuit";
            }

            else // meaning "resistance of the entire circuit" must be still in the list:
            {
                return "resistance of the entire circuit";
            }

        }

        // Due to above return statements, the program wouldn't have reached this point if the above if statement was true.


        // If program reached this point, least two of the total variables are still left in the lists.  Now to move on to the rest of the vars:

        // I'll use a for loop to run through each bulb.  In the for loop, I'll assess the current bulb's three variables:

        for (int x = 1; x <= num_bulbs; x++)
        {
            // I will create three temporary Strings for this for loop:

            // one will be for the current bulb's voltage in names list.

            // two will be for the current bulb's current in names list.

            // three will be for the current bulb's resistance in names list.

            String one = "voltage of bulb " + x;

            String two = "current of bulb " + x;

            String three = "resistance of bulb " + x;


            // Now to see if only one of these is still in the names list (meaning the other two have
            // already been displayed to the user):

            if (list_contains_one(names, one, two, three) == true)
            {
                // So, list only contains one variable of the current bulb.  I must find out which one it is:

                if (in_list(names, one) == true)
                {
                    return one;
                }

                else if (in_list(names, two) == true)
                {
                    return two;
                }

                else // meaning String three must be the only var of the current bulb still in the list:
                {
                    return three;
                }
            }
        }

        // If program reaches this point (due to above return statements), there must be no bad variables in the lists.

        // So:

        return ""; // This will just be added to the "bad" list, and when the program checks over the "bad" list during
        // the next iteration, it will just look at this and move on.
    }

}

