package com.lucatto;

import java.util.*;

public class GraphHomeworkExercises {

//    public int[][] matrixReader() {
//        Scanner scanner = new Scanner(System.in);
//        int[][] first = new int[3][3];
//        System.out.println("Enter the elements of the matrix: ");
//        for (int i = 0; i < 3; i++)
//            for (int j = 0; j < 3; j++)
//                first[i][j] = scanner.nextInt();
//        scanner.close();
//        return null;
//    }

    public void matrixPrinter(int[][] matrix) {
        int columns = matrix[0].length;
        for (int[] ints : matrix) {
            for (int j = 0; j < columns; j++)
                System.out.print(ints[j] + " ");
            System.out.println();
        }
    }

    public void _1aPrintGraphEdges(int[][] adjacencyMatrix) {
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrix.length; i++)
            for (int j = 0; j < adjacencyMatrix[0].length; j++)
                if (adjacencyMatrix[i][j] == 1)
                    edgeList.add(new Edge(new Vertex((char) (i + 65) + ""), new Vertex((char) (j + 65) + "")));
        System.out.println("Graph edges:");
        int i = 0;
        for (Edge edges : edgeList)
            System.out.println("Edge #" + ++i + ": origin: " + edges.getOrigin().getName() + "; and destination: " + edges.getDestination().getName());
    }

    public boolean _1bIsDirectedGraph(int[][] adjacencyMatrix) {
        for (int i = 0; i < adjacencyMatrix.length; i++)
            for (int j = 0; j < adjacencyMatrix[0].length; j++) {
                if (i == j && adjacencyMatrix[i][j] == 1) return true;
                if (i != j && adjacencyMatrix[i][j] == 1 && adjacencyMatrix[j][i] == 0) return true;
            }
        return false;
    }

    public void _1cVerticesDegreeInDigraph(int[][] adjacencyMatrix) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrix.length; i++)
            for (int j = 0; j < adjacencyMatrix[0].length; j++)
                if (adjacencyMatrix[i][j] == 1) {
                    characters.add((char) (i + 65));
                    characters.add((char) (j + 65));
                }
        List<Character> differentCharacters = new ArrayList<>();
        for (Character character : characters) {
            if (differentCharacters.contains(character)) continue;
            differentCharacters.add(character);
        }
        for (Character character : differentCharacters) {
            int counter = 0;
            for (Character character1 : characters)
                if (character == character1) counter++;
            System.out.println(character + " has " + counter + " degree(s).");
        }
    }

    public int _1cVerticesDegreeInNonDirectedGraph(int[][] adjacencyMatrix) {
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrix.length; i++)
            for (int j = 0; j < adjacencyMatrix[0].length; j++)
                if (adjacencyMatrix[i][j] == 1)
                    edgeList.add(new Edge(new Vertex((char) (i + 65) + ""), new Vertex((char) (j + 65) + "")));
        for (int i = 0; i < edgeList.size(); i++)
            for (int j = i + 1; j < edgeList.size(); j++)
                if (edgeList.get(i).getOrigin().getName().equals(edgeList.get(j).getDestination().getName()) &&
                        edgeList.get(i).getDestination().getName().equals(edgeList.get(j).getOrigin().getName()))
                    edgeList.remove(edgeList.get(j));
        return edgeList.size();
    }

    public boolean _1dIsConnectedGraph(int[][] adjacencyMatrix) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrix.length; i++)
            for (int j = 0; j < adjacencyMatrix[0].length; j++)
                if (adjacencyMatrix[i][j] == 1) {
                    characters.add((char) (i + 65));
                    characters.add((char) (j + 65));
                }
        List<Character> differentCharacters = new ArrayList<>();
        for (Character character : characters) {
            if (differentCharacters.contains(character)) continue;
            differentCharacters.add(character);
        }
        if (!characters.contains('A')) return false;
        List<Character> missingCharacters = new ArrayList<>();
        int missingCharactersLength = Math.max(adjacencyMatrix.length, adjacencyMatrix[0].length);
        for (int i = 0; i < missingCharactersLength; i++)
            missingCharacters.add((char) (i + 65));
//        System.out.println(Arrays.toString(missingCharacters.toArray()));
//        System.out.println(Arrays.toString(characters.toArray()));
        for (Character character : missingCharacters)
            if (!characters.contains(character)) return false;
        return true;
    }

    public boolean _1eIsCycleGraph(int[][] adjacencyMatrix) {
        if (_1bIsDirectedGraph(adjacencyMatrix)) return false;
        if (!hasAtLeastThreeRowsWithTwoEdges(adjacencyMatrix)) return false;
        int numberOfOnesInRow = 0;
        for (int repeatingProcess = 0; repeatingProcess <= adjacencyMatrix.length / 2; repeatingProcess++) {
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = 0; j < adjacencyMatrix.length; j++)
                    if (adjacencyMatrix[i][j] == 1) numberOfOnesInRow++;
                if (numberOfOnesInRow == 1)
                    for (int j = 0; j < adjacencyMatrix.length; j++)
                        if (adjacencyMatrix[i][j] == 1) {
                            adjacencyMatrix[i][j] = 0;
                            adjacencyMatrix[j][i] = 0;
                        }
                numberOfOnesInRow = 0;
            }
        }
        for (int[] row : adjacencyMatrix) {
            for (int j = 0; j < adjacencyMatrix.length; j++)
                if (row[j] == 1) numberOfOnesInRow++;
            if (numberOfOnesInRow >= 2) return true;
            numberOfOnesInRow = 0;
        }
        return false;
    }

    public boolean _1eIsCycleDigraph(int[][] adjacencyMatrix) {
        for (int i = 0; i < adjacencyMatrix.length; i++)
            for (int j = 0; j < adjacencyMatrix.length; j++)
                if (adjacencyMatrix[i][j] == 1 && adjacencyMatrix[j][i] == 1) return true;
        return _1eIsCycleGraph(matrixMirror(adjacencyMatrix));
    }

    private int[][] matrixMirror(int[][] adjacencyMatrix) {
        for (int i = 0; i < adjacencyMatrix.length; i++)
            for (int j = 0; j < adjacencyMatrix.length; j++)
                if (adjacencyMatrix[i][j] == 1) adjacencyMatrix[j][i] = 1;
        return adjacencyMatrix;
    }

    private boolean hasAtLeastThreeRowsWithTwoEdges(int[][] adjacencyMatrix) {
        int rowsWithTwoEdges = 0;
        for (int[] row : adjacencyMatrix) {
            int edges = 0;
            for (int j = 0; j < adjacencyMatrix[0].length; j++)
                if (row[j] == 1) edges++;
            if (edges >= 2) rowsWithTwoEdges++;
        }
        return rowsWithTwoEdges >= 3;
    }

    public void _1fAdjacencyList(int[][] adjacencyMatrix) {
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrix.length; i++)
            for (int j = 0; j < adjacencyMatrix[0].length; j++)
                if (adjacencyMatrix[i][j] == 1)
                    edgeList.add(new Edge(new Vertex((char) (i + 65) + ""), new Vertex((char) (j + 65) + "")));
        System.out.print("Adjacency:");
        List<String> originCharacterList = new ArrayList<>();
        for (Edge edges : edgeList) {
            if (originCharacterList.contains(edges.getOrigin().getName())) continue;
            originCharacterList.add(edges.getOrigin().getName());
        }
        String oldEdge = null;
        for (Edge edges : edgeList) {
            if (!edges.getOrigin().getName().equals(oldEdge)) {
                System.out.println();
                System.out.print(edges.getOrigin().getName() + " -> ");
            }
            System.out.print(edges.getDestination().getName() + ", ");
            oldEdge = edges.getOrigin().getName();
        }
        System.out.println();
    }

//    public void _2EmptyGraphAndItsVertices(int vertices) {
//        int[][] newMatrix = new int[vertices][vertices];
//        for (int i = 0; i < newMatrix.length; i++)
//            for (int j = 0; j < newMatrix[0].length; j++)
//                newMatrix[i][j] = 0;
//        List<Character> originVertices = new ArrayList<>();
//        for (int i = 0; i < vertices; i++)
//            originVertices.add((char) (i + 65));
//        for (int[] row : newMatrix) {
//            for (int j = 0; j < newMatrix[0].length; j++)
//                System.out.print(row[j] + " ");
//            System.out.println();
//        }
//        for (Character chars : originVertices) {
//            System.out.println(chars + " -> ");
//        }
//    }

    public void _2KnGraphAndItsVertices(int vertices) {
        int[][] newMatrix = new int[vertices][vertices];
        for (int i = 0; i < newMatrix.length; i++)
            for (int j = 0; j < newMatrix.length; j++) {
                if (i == j) newMatrix[i][j] = 0;
                else newMatrix[i][j] = 1;
            }
        for (int[] row : newMatrix) {
            for (int j = 0; j < newMatrix[0].length; j++)
                System.out.print(row[j] + " ");
            System.out.println();
        }
        for (int i = 0; i < newMatrix.length; i++) {
            System.out.print((char) (i + 65) + " -> ");
            for (int j = 0; j < newMatrix[0].length; j++) {
                if (i == j) continue;
                System.out.print((char) (j + 65) + ", ");
            }
            System.out.println();
        }
    }

    public void _3WeightedNetworkEdgePrinter(double[][] adjacencyMatrix) {
        List<WeightedNetwork> weithedNetworkList = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrix.length; i++)
            for (int j = 0; j < adjacencyMatrix[0].length; j++)
                if (adjacencyMatrix[i][j] != 0)
                    weithedNetworkList.add(new WeightedNetwork(new Vertex((char) (i + 65) + ""), new Vertex((char) (j + 65) + ""), adjacencyMatrix[i][j]));
        System.out.println("Graph edges:");
        int i = 0;
        for (WeightedNetwork weightedNetwork : weithedNetworkList)
            System.out.println("Edge #" + ++i + ": from origin: " + weightedNetwork.getOrigin().getName() + "; to destination: " + weightedNetwork.getDestination().getName() + "; with weith of: " + weightedNetwork.getWeight());
    }

    public void _4FullMatrixFromItsBottomLeftHalf(int[][] halfOfAdjacencyMatrix) {
        System.out.println("Original one:");
        for (int[] row : halfOfAdjacencyMatrix) {
            for (int j = 0; j < halfOfAdjacencyMatrix[0].length; j++)
                System.out.print(row[j] + " ");
            System.out.println();
        }
        for (int i = 0; i < halfOfAdjacencyMatrix.length; i++)
            for (int j = 0; j < halfOfAdjacencyMatrix.length; j++)
                halfOfAdjacencyMatrix[i][j] = halfOfAdjacencyMatrix[j][i];
        System.out.println("Complete one:");
        for (int[] row : halfOfAdjacencyMatrix) {
            for (int j = 0; j < halfOfAdjacencyMatrix[0].length; j++)
                System.out.print(row[j] + " ");
            System.out.println();
        }
    }
}
