package Build.Model;

import java.util.ArrayList;

public class Grid
{
    public static final int SCALE = 50;
    public static final int NUMBERX = 22;
    public static final int NUMBERY = 16;
    private boolean[][] grid;
    public Grid()
    {
        grid = new boolean[NUMBERX][NUMBERY];
    }

    public void clear()
    {
        for(int i = 0; i < NUMBERX; i++)
            for (int j = 0; j < NUMBERY; j++)
                grid[i][j] = false;
    }

    //return true if free
    public boolean check(int x, int y)
    {
        return !grid[x][y];
    }

    public boolean check(Gizmo gizmo)
    {
        int x = gizmo.getX();
        int y = gizmo.getY();
        for(int i = 0; i < gizmo.getSize();i++)
        {
            for(int j = 0; j < gizmo.getSize(); j++)
            {
                if(grid[x+i][y+j])
                    return false;
            }
        }
        return true;
    }

    public boolean cover(int x, int y)
    {
        if(grid[x][y])
            return false;
        grid[x][y] = true;
        return true;
    }

    public boolean cover(Gizmo gizmo)
    {
        if(!check(gizmo))
            return false;
        int x = gizmo.getX();
        int y = gizmo.getY();
        for(int i = 0; i < gizmo.getSize();i++)
        {
            for(int j = 0; j < gizmo.getSize(); j++)
            {
                if(grid[x+i][y+j])
                    return false;
                grid[x+i][y+j] = true;
            }
        }
        return true;
    }

    public boolean cover(ArrayList<Gizmo> gizmos)
    {
        for(Gizmo gizmo : gizmos)
            if(check(gizmo))
                cover(gizmo);
            else
                return false;
        return true;
    }
}
