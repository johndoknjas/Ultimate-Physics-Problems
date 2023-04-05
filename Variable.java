public class Variable extends Parent
{
    // This class holds the properties of each variable used in problems (acceleration, velocity, etc).

    // It has three variables:  the value of the variable in question, the name of it (such as "acceleration"), and its units (m/s^2 for example).

    private double value;
    private String name;
    private String unit;

    public Variable()
    {
        // If nothing is sent in, the variables will not be set to anything yet.
    }

    public Variable(double val, String nam, String uni)
    {
        // value and name are sent in for a specific variable, like velocity, and these are set to my variables.

        value = val;

        name = nam;

        unit = uni;
    }

    public double get_value()
    {
        return value;
    }

    public String get_name()
    {
        return name;
    }

    public String get_unit()
    {
        return unit;
    }

    public void set_value(double val)
    {
        value = val;
    }

    public void set_name(String nam)
    {
        name = nam;
    }

    public void set_unit(String uni)
    {
        unit = uni;
    }

    public void change_negative()
    {
        value *= -1;
    }
}
