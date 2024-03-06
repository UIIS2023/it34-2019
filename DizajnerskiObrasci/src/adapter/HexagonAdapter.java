package adapter;

import java.awt.Graphics;
import java.io.Serializable;

import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.SurfaceShape;
import hexagon.Hexagon;
import java.awt.Color;

public class HexagonAdapter extends SurfaceShape{
	private Hexagon hexagon;
	private Point center;
	
	public HexagonAdapter() {

	}
	
	public HexagonAdapter(Point center, int radius) {
		this.center = center;
		this.hexagon = center != null ? new Hexagon(center.getX(), center.getY(), radius) : new Hexagon(-10, -10, radius);
	}
	
	public HexagonAdapter(Point center, int radius, boolean isSelected) {
		this(center,radius);
		this.hexagon.setSelected(isSelected);
	}
	
	public HexagonAdapter(Point center, int radius, boolean isSelected, Color color) {
		this(center,radius,isSelected);
		this.hexagon.setBorderColor(color);
	}
	
	public HexagonAdapter(Point center, int radius, boolean isSelected, Color color, Color innerColor) {
		this(center,radius,isSelected,color);
		this.hexagon.setAreaColor(innerColor);
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof HexagonAdapter) return this.hexagon.getR() - ((HexagonAdapter) o).hexagon.getR();
		return 0;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter pomocni = (HexagonAdapter) obj;
			return this.center.equals(pomocni.center) && this.getRadius() == pomocni.getRadius();
		} else return false;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.hexagon.setX(this.hexagon.getX() + byX);
		this.hexagon.setY(this.hexagon.getY() + byY);
	}

	@Override
	public void fill(Graphics g) {
		this.hexagon.paint(g);
	}

	@Override
	public boolean contains(int x, int y) {
		return this.hexagon.doesContain(x,y);
	}

	@Override
	public void draw(Graphics g) {
		this.fill(g);
	}
	
	@Override
	public Color getColor() {
		return this.hexagon.getBorderColor();
	}
	
	@Override
	public void setColor(Color color) {
		this.hexagon.setBorderColor(color);
	}
	
	@Override
	public Color getInnerColor() {
		return this.hexagon.getAreaColor();
	}
	
	@Override
	public void setInnerColor(Color innerColor) {
		this.hexagon.setAreaColor(innerColor);
	}
	
	@Override
	public boolean isSelected() {
		return this.hexagon.isSelected();
	}
	
	@Override
	public void setSelected(boolean isSelected) {
		this.hexagon.setSelected(isSelected);
	}

	public Point getCenter() {
		return this.center;
	}

	public void setCenter(Point center) throws Exception {
		if (center.getX() < 0 || center.getY() < 0) throw new Exception();
		this.center = center;
		this.hexagon.setX(center.getX());
		this.hexagon.setY(center.getY());
	}
	
	public int getRadius() {
		return this.hexagon.getR();
	}

	public void setRadius(int radius) throws Exception {
		if (radius <= 0) throw new Exception();
		this.hexagon.setR(radius);
	}
	
	@Override
	public String toString() {
		return "Hexagon: center: " + this.getCenter().getX() + " " + this.getCenter().getY() + " radius: " + this.getRadius()
				+ " area_color: " + this.getInnerColor().getRGB() + " line_color: "
				+ this.getColor().getRGB();
	}
	
	@Override
	public HexagonAdapter clone() {
		HexagonAdapter hex = new HexagonAdapter(this.center.clone(), this.hexagon.getR());
		hex.hexagon.setX(this.hexagon.getX());
		hex.hexagon.setY(this.hexagon.getY());
		hex.setColor(this.getColor());
		hex.setInnerColor(this.getInnerColor());
		hex.setSelected(this.isSelected());
		return hex;
	}
}
