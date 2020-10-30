package com.lucatto;

public class Edge {

    private Vertex origin;
    private Vertex destination;

    public Edge(Vertex origin, Vertex destination) {
        this.origin = origin;
        this.destination = destination;
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

}
