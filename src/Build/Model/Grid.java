package Build.Model;

import java.util.ArrayList;

public class Grid
{
    public static final int SCALE = 50;
    public static final int NUMBERX = 22;
    public static final int NUMBERY = 16;
    private Gizmo[][] grid;
    public Grid()
    {
        grid = new Gizmo[NUMBERX][NUMBERY];
    }

    //new load
    public void clear()
    {
        for(int i = 0; i < NUMBERX; i++)
            for (int j = 0; j < NUMBERY; j++)
                grid[i][j] = null;
    }

    //move resize delete rotate
    public Gizmo getGizmo(int x, int y)
    {
        return grid[x][y];
    }

    //return true if is free
    private boolean check(int x, int y)
    {
        return grid[x][y] == null;
    }

    //check gizmo
    private boolean check(int x, int y, Gizmo coveringGizmo)
    {
        if((x<0)||(y<0)||(x>=NUMBERX)||(y>=NUMBERY))
            return false;
        return ((grid[x][y] == null)||(grid[x][y].equals(coveringGizmo)));
    }

    //add move resize  check gizmos
    public boolean check(Gizmo gizmo)
    {
        int x = gizmo.getX();
        int y = gizmo.getY();
        for(int i = 0; i < gizmo.getSize();i++)
        {
            for(int j = 0; j < gizmo.getSize(); j++)
            {
                if(!check(x+i,y+j,gizmo))
                    return false;
            }
        }
        return true;
    }

    //cover gizmo
    private boolean cover(int x, int y,Gizmo coveringGizmo)
    {
        if(check(x,y))
        {
            grid[x][y] = coveringGizmo;
            return true;
        }
        return false;
    }

    //add  replace
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
                cover(x+i,y+j,gizmo);
            }
        }
        return true;
    }

    //load
    public boolean cover(ArrayList<Gizmo> gizmos)
    {
        for(Gizmo gizmo : gizmos)
            if(check(gizmo))
                cover(gizmo);
            else
                return false;
        return true;
    }

    //replace
    private void clear(Gizmo gizmo)
    {
        for(int i = 0; i < NUMBERX; i++)
            for (int j = 0; j < NUMBERY; j++)
                if(grid[i][j] != null && grid[i][j].equals(gizmo))
                    remove(i,j);
    }

    //move resize
    public void replace(Gizmo gizmo)
    {
        clear(gizmo);
        cover(gizmo);
    }

    //delete replace
    private void remove(int x, int y)
    {
        grid[x][y] = null;
    }

    //delete
    public void remove(Gizmo gizmo)
    {
        int x = gizmo.getX();
        int y = gizmo.getY();
        for(int i = 0; i < gizmo.getSize();i++)
        {
            for(int j = 0; j < gizmo.getSize(); j++)
            {
                remove(x+i,y+j);
            }
        }
    }
}
