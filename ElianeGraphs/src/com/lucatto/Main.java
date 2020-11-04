package com.lucatto;

public class Main {

    public static void main(String[] args) {
//        GraphClassByEliane graphClassByEliane = new GraphClassByEliane();
//        Vertex a = graphClassByEliane.addVertex("A");
//        Vertex b = graphClassByEliane.addVertex("B");
//        Vertex c = graphClassByEliane.addVertex("C");
//        Edge ab = graphClassByEliane.addEdge(a, b);
//        Edge ac = graphClassByEliane.addEdge(a, c);
//        Edge bc = graphClassByEliane.addEdge(b, c);
//        Edge cb = graphClassByEliane.addEdge(c, b);
//        System.out.println(graphClassByEliane);

        GraphHomeworkExercises graphHomeworkExercises = new GraphHomeworkExercises();
        int[][] testMatrix = new int[][]{{0, 0, 0, 1},{1, 1, 0, 0}, {0, 0, 0, 0}, {1, 1, 1, 1}};
        int[][] testMatrix2 = new int[][]{{0, 1, 1, 0},{1, 0, 0, 1}, {1, 0, 0, 1}, {0, 1, 1, 0}};
        int[][] testMatrix3 = new int[][]{{0, 0, 0, 1},{0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}};
        int[][] testMatrix4 = new int[][]{{0, 1, 1, 1},{1, 0, 0, 1}, {1, 0, 0, 1}, {1, 1, 1, 1}};
        int[][] testMatrix5 = new int[][] {{0, 0, 1, 0}, {1, 0, 0, 0}, {0, 0, 0, 1}, {0, 1, 0, 0}};
        int[][] testMatrix6 = new int[][] {{0, 0, 1, 0}, {1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
        int[][] testMatrix7 = new int[][]{{0, 0, 0, 0, 0},{1, 0, 0, 1, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 0, 0}};
        int[][] testMatrix8 = new int[][]{{0, 0, 0, 1, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {1, 1, 0, 0, 0}, {0, 1, 1, 0, 0}};
        int[][] testMatrix9 = new int[][]{{0, 1, 0, 0, 0, 0},{1, 0, 1, 0, 0, 0}, {0, 1, 0, 1, 0, 1}, {0, 0, 1, 0, 1, 0}, {0, 0, 0, 1, 0, 0}, {0, 0, 1, 0, 0, 0}};
        int[][] testMatrix10 = new int[][]{{0, 1, 0, 0, 0, 0},{1, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 1}, {0, 0, 1, 0, 1, 0}, {0, 0, 0, 1, 0, 1}, {0, 0, 1, 0, 1, 0}};
        int[][] testMatrix11 = new int[][]{{0, 1, 0, 0, 0, 0},{1, 0, 1, 0, 0, 0}, {0, 1, 0, 1, 0, 0}, {0, 0, 1, 0, 1, 0}, {0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 1, 0}};
        int[][] testMatrix12 = new int[][]{{0, 1, 1, 0, 0, 0},{1, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 0, 0}, {0, 0, 1, 0, 1, 0}, {0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 1, 0}};
        int[][] testMatrix13 = new int[][]{{0, 0, 0, 0, 0, 0},{1, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0}, {0, 1, 1, 0, 0, 0}, {1, 0, 0, 0, 1, 0}};
        int[][] testMatrix14 = new int[][]{{0, 1, 0, 0, 0, 1},{1, 0, 1, 0, 0, 0}, {0, 1, 0, 1, 0, 1}, {0, 0, 1, 0, 1, 0}, {0, 0, 0, 1, 0, 0}, {1, 0, 1, 0, 0, 0}};
        double[][] testMatrix15 = new double[][]{{5, 1, 6, 0, 10, 1},{1, 0, 1, 5.5, 0, 0}, {0, 1, 33, 1, 0, 1}, {0, 0, 1, 9, 1, 0}, {0, 0, 43, 1, 0, 0}, {1, 0, 1, 0, 99.9, 0}};

//        graphHomeworkExercises.matrixPrinter(testMatrix);
//        System.out.println("=========================================");
//        graphHomeworkExercises.matrixPrinter(testMatrix2);
//        System.out.println("=========================================");
//        graphHomeworkExercises.matrixPrinter(testMatrix3);
//        System.out.println("=========================================");
//        graphHomeworkExercises.matrixPrinter(testMatrix4);
//        System.out.println("=========================================");
//        graphHomeworkExercises.matrixPrinter(testMatrix5);
//        System.out.println("=========================================");
//        graphHomeworkExercises._1aPrintGraphEdges(testMatrix);
//        graphHomeworkExercises._1cVerticesDegreeInDigraph(testMatrix);
//        System.out.println(graphHomeworkExercises._1cVerticesDegreeInNonDirectedGraph(testMatrix2));
//        System.out.println(graphHomeworkExercises._1cVerticesDegreeInNonDirectedGraph(testMatrix3));
//        System.out.println(graphHomeworkExercises._1bIsDirectedGraph(testMatrix));
//        System.out.println(graphHomeworkExercises._1bIsDirectedGraph(testMatrix2));
//        System.out.println(graphHomeworkExercises._1bIsDirectedGraph(testMatrix3));
//        System.out.println(graphHomeworkExercises._1bIsDirectedGraph(testMatrix4));
//        System.out.println(graphHomeworkExercises._1bIsDirectedGraph(testMatrix5));
//        System.out.println(graphHomeworkExercises._1dIsConnectedGraph(testMatrix));
//        System.out.println(graphHomeworkExercises._1dIsConnectedGraph(testMatrix2));
//        graphHomeworkExercises._1aPrintGraphEdges(testMatrix3);
//        graphHomeworkExercises._1fAdjacencyList(testMatrix5);
//        graphHomeworkExercises._2KnGraphAndItsVertices(4);
//        graphHomeworkExercises._3WeightedNetworkEdgePrinter(testMatrix6);
//        graphHomeworkExercises._4FullMatrixFromItsBottomLeftHalf(testMatrix7);
//        System.out.println(graphHomeworkExercises._1eIsCycleGraph(testMatrix2)); // testMatrix2 is a cycle graph
//        System.out.println(graphHomeworkExercises._1eIsCycleGraph(testMatrix8));
//        System.out.println(graphHomeworkExercises._1eIsCycleGraph(testMatrix9));
//        System.out.println(graphHomeworkExercises._1eIsCycleGraph(testMatrix10));
//        System.out.println(graphHomeworkExercises._1eIsCycleGraph(testMatrix11));
//        System.out.println(graphHomeworkExercises._1eIsCycleGraph(testMatrix12));
//        System.out.println(graphHomeworkExercises._1eIsCycleGraph(testMatrix13)); // false because testMatrix13 is a directed graph
//        System.out.println(graphHomeworkExercises._1eIsCycleDigraph(testMatrix13)); // true  (this method's been removed)
//        System.out.println(graphHomeworkExercises._1eIsCycleGraph(testMatrix6));
    }

}
