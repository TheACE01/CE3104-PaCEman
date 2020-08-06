package dataStructures.DijkstraAlgorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;



public class DijkstraExec {

    private List<Vertex> nodes;
    private List<Edge> edges;
    private DijkstraAlgorithm d;


    @Test
    public ArrayList<String> getPath(int s, int f, DijkstraAlgorithm dijkstra) {
        List<String> newPath = new ArrayList<String>();

        dijkstra.execute(nodes.get(s));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(f));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Vertex vertex : path) {
            newPath.add(vertex.getName());
            //System.out.println(vertex);
        }
        return (ArrayList<String>) newPath;

    }

    public void initGraph(){
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();

        //number of nodes
        for (int i = 0; i <= 64; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }

        addLane("Edge_0", 0, 1, 141);
        addLane("Edge_0", 1, 0, 141);

        addLane("Edge_1", 0, 5, 235);
        addLane("Edge_1", 5, 0, 235);

        addLane("Edge_2", 1, 2, 100);
        addLane("Edge_2", 2, 1, 100);

        addLane("Edge_3", 2, 3, 47);
        addLane("Edge_3", 3, 2, 47);

        addLane("Edge_4", 5, 4, 94);
        addLane("Edge_4", 4, 5, 94);

        addLane("Edge_5", 4, 3, 150);
        addLane("Edge_5", 3, 4, 150);

        addLane("Edge_6", 1, 23, 188);
        addLane("Edge_6", 23, 1, 188);

        addLane("Edge_7", 2, 20, 47);
        addLane("Edge_7", 20, 2, 47);

        addLane("Edge_8", 5, 6, 150);
        addLane("Edge_8", 6, 5, 150);

        addLane("Edge_9", 6, 7, 94);
        addLane("Edge_9", 7, 6, 94);

        addLane("Edge_10", 7, 8, 100);
        addLane("Edge_10", 8, 7, 100);

        addLane("Edge_11", 8, 4, 50);
        addLane("Edge_11", 4, 8, 50);

        addLane("Edge_12", 6, 9, 100);
        addLane("Edge_12", 9, 6, 100);

        addLane("Edge_13", 23, 22, 100);
        addLane("Edge_13", 22, 23, 100);

        addLane("Edge_14", 20, 22, 141);
        addLane("Edge_14", 22, 20, 141);

        addLane("Edge_15", 20, 18, 100);
        addLane("Edge_15", 18, 20, 100);

        addLane("Edge_16", 8, 15, 188);
        addLane("Edge_16", 15, 8, 188);

        addLane("Edge_17", 18, 15, 100);
        addLane("Edge_17", 15, 18, 100);

        addLane("Edge_18", 22, 21, 50);
        addLane("Edge_18", 21, 22, 50);

        addLane("Edge_19", 21, 19, 50);
        addLane("Edge_19", 19, 21, 50);

        addLane("Edge_20", 18, 19, 141);
        addLane("Edge_20", 19, 18, 141);

        addLane("Edge_21", 15, 16, 94);
        addLane("Edge_21", 16, 15, 94);

        addLane("Edge_22", 16, 17, 47);
        addLane("Edge_22", 17, 16, 47);

        addLane("Edge_23", 19, 17, 100);
        addLane("Edge_23", 17, 19, 100);

        addLane("Edge_24", 9, 10, 188);
        addLane("Edge_24", 10, 9, 188);

        addLane("Edge_25", 10, 11, 100);
        addLane("Edge_25", 11, 10, 100);

        addLane("Edge_26", 11, 12, 94);
        addLane("Edge_26", 12, 11, 94);

        addLane("Edge_27", 16, 12, 100);
        addLane("Edge_27", 12, 16, 100);

        addLane("Edge_28", 12, 14, 94);
        addLane("Edge_28", 14, 12, 94);

        addLane("Edge_29", 10, 13, 188);
        addLane("Edge_29", 13, 10, 188);

        addLane("Edge_30", 14, 13, 100);
        addLane("Edge_30", 13, 14, 100);

        addLane("Edge_31", 23, 24, 141);
        addLane("Edge_31", 24, 23, 141);

        addLane("Edge_32", 24, 25, 50);
        addLane("Edge_32", 25, 24, 50);

        addLane("Edge_33", 25, 26, 188);
        addLane("Edge_33", 26, 25, 188);

        addLane("Edge_34", 26, 27, 50);
        addLane("Edge_34", 27, 26, 50);

        addLane("Edge_35", 26, 41, 50);
        addLane("Edge_35", 41, 26, 50);

        addLane("Edge_36", 41, 40, 50);
        addLane("Edge_36", 40, 41, 50);

        addLane("Edge_37", 21, 28, 141);
        addLane("Edge_37", 28, 21, 141);

        addLane("Edge_38", 28, 40, 141);
        addLane("Edge_38", 40, 28, 141);

        addLane("Edge_39", 28, 29, 100);
        addLane("Edge_39", 29, 28, 100);

        addLane("Edge_40", 29, 30, 47);
        addLane("Edge_40", 30, 29, 47);

        addLane("Edge_41", 29, 31, 47);
        addLane("Edge_41", 31, 29, 47);

        addLane("Edge_42", 14, 33, 47);
        addLane("Edge_42", 33, 14, 47);

        addLane("Edge_43", 33, 32, 50);
        addLane("Edge_43", 32, 33, 50);

        addLane("Edge_44", 32, 34, 188);
        addLane("Edge_44", 34, 32, 188);

        addLane("Edge_45", 34, 39, 47);
        addLane("Edge_45", 39, 34, 47);

        addLane("Edge_46", 34, 40, 200);
        addLane("Edge_46", 40, 34, 200);

        addLane("Edge_47", 13, 36, 141);
        addLane("Edge_47", 36, 13, 141);

        addLane("Edge_48", 36, 35, 50);
        addLane("Edge_48", 35, 36, 50);

        addLane("Edge_49", 35, 37, 141);
        addLane("Edge_49", 37, 35, 141);

        addLane("Edge_50", 37, 39, 100);
        addLane("Edge_50", 39, 37, 100);

        addLane("Edge_51", 37, 38, 50);
        addLane("Edge_51", 38, 37, 50);

        addLane("Edge_52", 38, 57, 94);
        addLane("Edge_52", 57, 38, 94);

        addLane("Edge_53", 57, 56, 150);
        addLane("Edge_53", 56, 57, 150);

        addLane("Edge_54", 56, 54, 100);
        addLane("Edge_54", 54, 56, 100);

        addLane("Edge_55", 54, 55, 47);
        addLane("Edge_55", 55, 54, 47);

        addLane("Edge_56", 55, 42, 150);
        addLane("Edge_56", 42, 55, 150);

        addLane("Edge_57", 41, 26, 50);
        addLane("Edge_57", 26, 41, 50);

        addLane("Edge_58", 42, 44, 141);
        addLane("Edge_58", 44, 42, 141);

        addLane("Edge_59", 44, 43, 100);
        addLane("Edge_59", 43, 44, 100);

        addLane("Edge_60", 27, 43, 235);
        addLane("Edge_60", 43, 27, 235);

        addLane("Edge_61", 43, 45, 94);
        addLane("Edge_61", 45, 43, 94);

        addLane("Edge_62", 45, 47, 141);
        addLane("Edge_62", 47, 45, 141);

        addLane("Edge_63", 45, 46, 100);
        addLane("Edge_63", 46, 45, 100);

        addLane("Edge_64", 44, 46, 141);
        addLane("Edge_64", 46, 44, 141);

        addLane("Edge_65", 46, 49, 47);
        addLane("Edge_65", 49, 46, 47);

        addLane("Edge_66", 49, 48, 94);
        addLane("Edge_66", 48, 49, 94);

        addLane("Edge_67", 48, 47, 100);
        addLane("Edge_67", 47, 48, 100);

        addLane("Edge_68", 48, 63, 200);
        addLane("Edge_68", 63, 48, 200);

        addLane("Edge_69", 63, 62, 141);
        addLane("Edge_69", 62, 63, 141);

        addLane("Edge_70", 62, 51, 100);
        addLane("Edge_70", 51, 62, 100);

        addLane("Edge_71", 51, 50, 47);
        addLane("Edge_71", 50, 51, 47);

        addLane("Edge_72", 50, 49, 100);
        addLane("Edge_72", 49, 50, 100);

        addLane("Edge_73", 51, 52, 141);
        addLane("Edge_73", 52, 51, 141);

        addLane("Edge_74", 52, 53, 50);
        addLane("Edge_74", 53, 52, 50);

        addLane("Edge_75", 53, 54, 47);
        addLane("Edge_75", 54, 53, 47);

        addLane("Edge_76", 62, 61, 47);
        addLane("Edge_76", 61, 62, 47);

        addLane("Edge_77", 61, 64, 50);
        addLane("Edge_77", 64, 61, 50);

        addLane("Edge_78", 64, 56, 141);
        addLane("Edge_78", 56, 64, 141);

        addLane("Edge_79", 64, 58, 50);
        addLane("Edge_79", 58, 64, 50);

        addLane("Edge_80", 58, 60, 188);
        addLane("Edge_80", 60, 58, 188);

        addLane("Edge_81", 60, 63, 100);
        addLane("Edge_81", 63, 60, 100);

        addLane("Edge_82", 60, 59, 100);
        addLane("Edge_82", 59, 60, 100);

        addLane("Edge_83", 59, 57, 329);
        addLane("Edge_83", 57, 59, 329);

        addLane("Edge_84", 41, 42, 94);
        addLane("Edge_84", 42, 41, 94);

        //create a unique graph structure in all the petitions
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        d = dijkstra;
    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo,
                         int duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
    }

    public DijkstraAlgorithm getD() {
        return d;
    }
}