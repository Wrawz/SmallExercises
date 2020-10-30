package com.lucatto;

public class WeightedNetwork {

    private Vertex origin;
    private Vertex destination;
    private double weight;

    public WeightedNetwork(Vertex origin, Vertex destination, double weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getOrigin() {
        return origin;
    }

    public void setOrigin(Vertex origin) {
        this.origin = origin;
    }

    public Vertex getDestination() {
        return destination;
    }

    public void setDestination(Vertex destination) {
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
