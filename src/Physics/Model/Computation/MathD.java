package Physics.Model.Computation;

public class MathD {

    //Clamp the val BTW min and max
    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
}
