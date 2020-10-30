package com.lucatto;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private String name;
    private List<Edge> adjacent;
    private List<String> connectedTo; // adjacentListButWithString instead of doing adjacent.get(i).getDestination{.getName()}

    public Vertex(String name) {
        this.name = name;
        this.adjacent = new ArrayList<>();
        this.connectedTo = new ArrayList<>();
    }

    void addAdjacent(Edge edge) {
        adjacent.add(edge);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getAdjacent() {
        return adjacent;
    }

    public void setAdjacent(List<Edge> adjacent) {
        this.adjacent = adjacent;
    }

    public List<String> getConnectedTo() {
        return connectedTo;
    }

    public void setConnectedTo(List<String> connectedTo) {
        this.connectedTo = connectedTo;
    }

    public void addConnectedTo(String nextVertex) {
        connectedTo.add(nextVertex);
    }

}
