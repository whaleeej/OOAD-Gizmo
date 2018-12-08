package Build.Model;

public class Setting
{
    private int gravity;
    private int u;
    private int c;

    public Setting(int gravity, int u, int c)
    {
        this.gravity = gravity;
        this.u = u;
        this.c = c;
    }

    public double getRealGravity()
    {
        return ((double)gravity*20);
    }

    public double getRealU()
    {
        return ((double)u / 100);
    }

    public double getRealC()
    {
        return ((double)c / 100);
    }

    public int getGravity()
    {
        return gravity;
    }

    public void setGravity(int gravity)
    {
        this.gravity = gravity;
    }

    public int getU()
    {
        return u;
    }

    public void setU(int u)
    {
        this.u = u;
    }

    public int getC()
    {
        return c;
    }

    public void setC(int c)
    {
        this.c = c;
    }
}
