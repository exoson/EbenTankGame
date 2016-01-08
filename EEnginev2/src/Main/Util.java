package Main;

import java.util.ArrayList;

public class Util 
{
    public static boolean los(Gameobject go1, Gameobject go2)
    {
        return los(go1.getX(),go1.getY(),go2.getX(),go2.getY());
    }
    public static boolean los(Gameobject go1, float x,float y)
    {
        return los(go1.getX(),go1.getY(),x,y);
    }
    public static boolean los(float x1,float y1,float x2,float y2)
    {
        //check that start point is closer to 0,y than end point
        int startx = (int)((x1 < x2 ? x1 : x2) / Game.SQUARESIZE),starty = (int)((x1 < x2 ? y1 : y2) / Game.SQUARESIZE),endx = (int)((x1 > x2 ? x1 : x2) / Game.SQUARESIZE),endy = (int)((x1 > x2 ? y1 : y2) / Game.SQUARESIZE);
        
        float dx = (int)((endx-startx)),dy = (int)((endy - starty));
        if((int)((x2 - x1)/ Game.SQUARESIZE) == 0 && (int)((y2 - y1) / Game.SQUARESIZE) == 0) return true;
        
        int x,y;
        if(dx == 0){
            for(int i = 0; Math.abs(i) < Math.abs(dy);i += Math.abs(dy) / dy){
                x = startx;
                y = starty + i;
                Square sqr = Game.getLevel().getMap().getsquare(x, y);
                if(sqr != null) if(!sqr.getTransparent()) return false;
            }
            
        }else if(dy == 0){
            for(int i = 0; Math.abs(i) < Math.abs(dx);i += Math.abs(dx) / dx){
                x = startx + i;
                y = starty;
                Square sqr = Game.getLevel().getMap().getsquare(x, y);
                if(sqr != null) if(!sqr.getTransparent()) return false;
            }
        }else{
            float k = dy / dx;
            if(Math.abs(k) < 1)
            {
                for(float i = 0; i < dx; i += Math.abs(k))
                {
                    x = startx + (int) i;
                    y = starty + (int)(i * k);
                    Square sqr = Game.getLevel().getMap().getsquare(x, y);
                    if(sqr != null) if(!sqr.getTransparent()) return false;
                }
            }else{
                for(float i = 0; Math.abs(i) < Math.abs(dy); i +=  1 / k)
                {
                    x = startx + (int)(i / k);
                    y = starty + (int)(i);
                    Square sqr = Game.getLevel().getMap().getsquare(x, y);
                    if(sqr != null) if(!sqr.getTransparent()) return false;
                }
            }
        }
        return true;
    }
    public static float dist(Vector2f a, Vector2f b)
    {
        return a.minus(b).length();
    }
    public static float dist(float x1, float y1, float x2, float y2)
    {
        return new Vector2f(x1-x2,y1-y2).length();
        
    }
    public static ArrayList<Vector2f> findPath(Gameobject g1, Gameobject g2)
    {
        Square[][] sqrs = Game.getLevel().getMap().getSquares();
        
        Vector2f goal = new Vector2f((int)(g2.getX()/Game.SQUARESIZE),(int)(g2.getY()/Game.SQUARESIZE));
        while(!sqrs[(int)goal.getX()][(int)goal.getY()].getTransparent()) goal = goal.add(new Vector2f(1,0));
        
        ArrayList<Vector2f> ClosedSet = new ArrayList<>();    	  // The set of nodes already evaluated.
        ArrayList<Vector2f> OpenSet = new ArrayList<>();    // The set of tentative nodes to be evaluated, initially containing the start node
        
        OpenSet.add(new Vector2f((int)(g1.getX()/Game.SQUARESIZE),(int)(g1.getY()/Game.SQUARESIZE)));
        
        Vector2f[][] Came_From = new Vector2f[sqrs.length][sqrs[0].length];    // The map of navigated nodes.
        
        float[][] g_score = new float[sqrs.length][sqrs[0].length];
        for(int i = 0; i < g_score.length; i++) {
            for(int j = 0; j < g_score[i].length; j++) {
                g_score[i][j] = Float.POSITIVE_INFINITY;
            }
        }
        g_score[(int)(g1.getX()/Game.SQUARESIZE)][(int)(g1.getY()/Game.SQUARESIZE)] = 0;    // Cost from start along best known path.
        
        // Estimated total cost from start to goal through y.
        float[][] f_score = new float[sqrs.length][sqrs[0].length];
        for(int i = 0; i < f_score.length; i++) {
            for(int j = 0; j < f_score[i].length; j++) {
                f_score[i][j] = Float.POSITIVE_INFINITY;
            }
        }
        f_score[(int)(g1.getX()/Game.SQUARESIZE)][(int)(g1.getY()/Game.SQUARESIZE)] = Util.dist(new Vector2f((int)(g1.getX()/Game.SQUARESIZE),(int)(g1.getY()/Game.SQUARESIZE)), goal);    // Cost from start along best known path.
        
        int i = 0;
        while(!OpenSet.isEmpty()) {
            i++;
            Vector2f current = new Vector2f();
            float minVal = Float.POSITIVE_INFINITY;
            for(Vector2f v : OpenSet) {
                if(f_score[(int)v.getX()][(int)v.getY()] < minVal) {
                    current = v;
                    minVal = f_score[(int)v.getX()][(int)v.getY()];
                }
            }
            if (current.equals(goal)) {
                return reconstruct_path(Came_From, goal);
            }
            if(i > 1000) break;
            //System.out.println(current.toString());
            OpenSet.remove(current);
            ClosedSet.add(current);
            Vector2f[] neighbors = new Vector2f[]{
                current.add(new Vector2f(1,0)),
                current.add(new Vector2f(0,1)),
                current.add(new Vector2f(-1,0)),
                current.add(new Vector2f(0,-1))
            };
            for(Vector2f neighbor : neighbors) {
                if(neighbor.getX() < 0 || 
                        neighbor.getY() < 0 || 
                        (int)(neighbor.getX()) >= g_score.length || 
                        (int)(neighbor.getY()) >= g_score[0].length){
                    continue;
                }
                if(neighbor.equals(current)) {
                    continue;
                }
                if(ClosedSet.contains(neighbor)) {
                    continue;
                }
                /*if(!sqrs[(int)neighbor.getX()][(int)neighbor.getY()].getTransparent()) {
                    continue;
                }*/
                float tentative_g_score = g_score[(int)current.getX()][(int)current.getY()] + distanceBetween(current, neighbor); // length of this path.
                if (!OpenSet.contains(neighbor)) {	// Discover a new node
                    OpenSet.add(neighbor);
                } else if(tentative_g_score >= g_score[(int)neighbor.getX()][(int)neighbor.getY()]) {
                    continue;       // This is not a better path.
                }

                // This path is the best until now. Record it!
                Came_From[(int)neighbor.getX()][(int)neighbor.getY()] = current;
                g_score[(int)neighbor.getX()][(int)neighbor.getY()] = tentative_g_score;
                f_score[(int)neighbor.getX()][(int)neighbor.getY()] = g_score[(int)neighbor.getX()][(int)neighbor.getY()] + goal.minus(neighbor).length();
            }
        }
        return null;
    }
    private static float distanceBetween(Vector2f start, Vector2f end) {
        if(!Game.getLevel().getMap().getsquare((int)end.getX(), (int)end.getY()).getTransparent())
            return Float.POSITIVE_INFINITY;
        /*if(!Game.getLevel().getMap().getsquare((int)start.getX(), (int)start.getY()).getTransparent())
            return Float.POSITIVE_INFINITY;*/
        return Util.dist(start, end);
    }
    private static ArrayList<Vector2f> reconstruct_path(Vector2f[][] Came_From, Vector2f current) {
        ArrayList<Vector2f> total_path = new ArrayList<>();
        current = Came_From[(int)current.getX()][(int)current.getY()];
        while(current != null) {
            total_path.add(current);
            current = Came_From[(int)current.getX()][(int)current.getY()];
        }
        return total_path;
    }
}
