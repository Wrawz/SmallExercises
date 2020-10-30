package com.lucatto;

import java.util.ArrayList;
import java.util.List;

public class GraphClassByEliane {

    private final List<Vertex> vertices;
    private final List<Edge> edges;

    public GraphClassByEliane() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public Vertex addVertex(String vertexName) {
        Vertex vertex = new Vertex(vertexName);
        vertices.add(vertex);
        return vertex;
    }

    public Edge addEdge(Vertex origin, Vertex destination) {
        Edge edge = new Edge(origin, destination);
        origin.addAdjacent(edge);
        edges.add(edge);
        return edge;
    }

    public String toString() {
        String string = "";
        for (Vertex vertex : vertices) {
            string += vertex.getName() + " -> ";
            for (Edge edge : vertex.getAdjacent()) {
                Vertex vertex1 = edge.getDestination();
                string += vertex1.getName() + ", ";
            }
            string += "\n";
        }
        return string;
    }


}
