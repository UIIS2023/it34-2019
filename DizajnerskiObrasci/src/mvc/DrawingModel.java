package mvc;

import java.io.Serializable;
import java.util.ArrayList;
import geometry.Shape;

import geometry.Point;

public class DrawingModel implements Serializable{
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public void add(Shape p) {
		shapes.add(p);
	}
	
	public void remove(Shape p) {
		shapes.remove(p);
	}
	
	public Shape get(int index) {
		return shapes.get(index);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public void clear() {
		shapes.clear();
	}
}
