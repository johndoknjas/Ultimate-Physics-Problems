public class Mass extends Parent
{
    // This class stores the characteristics of each object before a collision, for the Momentum class.

    private String name; // Stores the name of the object (like "ball" or "Earth").

    private String movement; // Stores the name of the way the object moves (like "thrown" or "rolls").

    private double mass;  // Stores the mass of the object.

    private String mass_as_String; // Stores the mass, just as a String (also without any floating point errors with repeating decimals).

    private double velocity;  // Stores initial velocity of object.

    private String velocity_as_String; // Stores the initial velocity value, just in a String format.

    private double angle;  // Stores angle of object's movement (in degrees).

    private double vertical_momentum;  // Stores the vertical component of the object's momentum.

    private double horizontal_momentum;  // Stores the horizontal component of the object's momentum.

    private int quadrant;  // Stores which quadrant the angle is in.

    private String direction;  // Stores the direction of the angle ("North of East" or "North of West" etc), to display to the user.

    private double momentum;  // Stores the momentum of the object.

    public Mass(String nam, double mas, double vel, String movmnt, String mas_String, String vel_String)
    {
        // Set name, mass, velocity, angle, and quadrant:

        name = nam;

        mass = mas;

        mass_as_String = mas_String;

        velocity = vel;

        velocity_as_String = vel_String;

        movement = movmnt;

        angle = random_int(91);

        quadrant = random_int(1, 4);

        momentum = mass * velocity;

        // Now to find vertical_momentum and horizontal_momentum, using two methods from Parent:

        vertical_momentum = find_vertical_leg(momentum, angle);

        horizontal_momentum = find_horizontal_leg(momentum, angle);

        // Now to change vertical_momentum and horizontal_momentum, as well as the String var "direction", depending
        // on which quadrant was randomly chosen:

        if (quadrant == 1)
        {
            // Both vertical_momentum and horizontal_momentum stay the same.

            direction = "North of East";
        }

        else if (quadrant == 2)
        {
            // Just horizontal_momentum becomes negative:

            horizontal_momentum *= -1;

            direction = "North of West";
        }

        else if (quadrant == 3)
        {
            // Both vertical_momentum and horizontal_momentum become negative:

            horizontal_momentum *= -1;

            vertical_momentum *= -1;

            direction = "South of West";
        }

        else // meaning quadrant = 4:
        {
            // Just vertical_momentum becomes negative:

            vertical_momentum *= -1;

            direction = "South of East";
        }
    }

    // METHODS:

    public double get_velocity()
    {
        // Returns the velocity variable.

        return velocity;
    }

    public String get_velocity_as_String()
    {
        // Returns the velocity_as_String variable.

        return velocity_as_String;
    }

    public double get_angle()
    {
        // Returns the angle variable.

        return angle;
    }

    public String get_direction()
    {
        // Returns the direction String.

        return direction;
    }

    public double get_mass()
    {
        // Returns the mass variable.

        return mass;
    }

    public String get_mass_as_String()
    {
        // Returns the mass_as_String variable.

        return mass_as_String;
    }

    public double get_momentum()
    {
        // returns the momentum variable.

        return momentum;
    }

    public double get_vertical_momentum()
    {
        // returns the vertical_momentum variable.

        return vertical_momentum;
    }

    public double get_horizontal_momentum()
    {
        // returns the horizontal_momentum variable.

        return horizontal_momentum;
    }

    public String get_name()
    {
        // returns the object's name:

        return name;
    }

    public String get_movement()
    {
        // returns the object's type of movement:

        return movement;
    }
}
