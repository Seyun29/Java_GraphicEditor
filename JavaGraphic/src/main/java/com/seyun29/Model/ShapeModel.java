package com.seyun29.Model;

import com.seyun29.Model.Shape.Shape;
import com.seyun29.View.Observer;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;

public class ShapeModel {
    @Getter //Current Objects on Board
    private final ArrayList<Shape> Shapes = new ArrayList<>();

    private ArrayList<Shape> selectedShapes = new ArrayList<>();

    private final ArrayList<Observer> observers = new ArrayList<>();

    public ShapeModel() {}

    public ArrayList<Shape> getSelectedShapes() {
        if (selectedShapes.isEmpty())
            return null;
        else
            return selectedShapes;
    }

    public void setSelectedShapes(ArrayList<Shape> shapes) {
        selectedShapes = shapes;
        notifyObservers();
    }

    public void setSingleSelectedShape(Shape shape) {
        if (shape != null) {
            selectedShapes.clear();
            selectedShapes.add(shape);
        } else {
            clearSelectedShapes();
        }
        notifyObservers();
    }

    public void addToSelectedShapes(Shape shape) {
        selectedShapes.add(shape);
        notifyObservers();
    }

    public void clearSelectedShapes() {
        selectedShapes.clear();
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        observers.forEach(Observer::update);
    }

    public void addShape(Shape shape) {
        Shapes.add(shape);
        setSingleSelectedShape(shape);
        notifyObservers();
    }

    public void removeShape(Shape shape) {
        Shapes.remove(shape);
        clearSelectedShapes();
        notifyObservers();
    }

    public void clearShapes() {
        Shapes.clear();
        clearSelectedShapes();
        notifyObservers();
    }

    public void bringToFront(Shape shape) {
        Shapes.remove(shape);
        Shapes.add(shape);
        notifyObservers();
    }

    public void sendToBack(Shape shape) {
        Shapes.remove(shape);
        Shapes.add(0, shape);
        notifyObservers();
    }

    public Shape containsShape(Point point) {
        //always pick the one with the biggest zindex
        for(int i=Shapes.size()-1; i>=0; i--) {
            if(Shapes.get(i).contains(point)) {
                return Shapes.get(i);
            }
        }
        return null;
    }

    public void updateShapeProperties(Shape shape) {

    }

}