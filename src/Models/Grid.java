package Models;

import java.util.ArrayList;

public class Grid {
    private ArrayList<Point> pointList;

    public Grid() {
        this.pointList = new ArrayList<>();
    }

    public Grid(ArrayList<Point> pointList) {
        this.pointList = pointList;
    }

    public void addPoint(Point point) {
        pointList.add(point);
    }

    public ArrayList<Point> getPointList() {
        return pointList;
    }
}
