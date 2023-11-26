import graph.DijkstraGraph;
import graph.Graph;
import tree.RedBlackTree;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Graph.GraphNode node = new Graph.GraphNode(2);
//        GraphNode node1 = new GraphNode(1);
//        GraphNode node2 = new GraphNode(5);
//        GraphNode node3 = new GraphNode(6);
//        GraphNode node4 = new GraphNode(8);
//        GraphNode node5 = new GraphNode(27);
//        GraphNode node6 = new GraphNode(16);
//        GraphNode node7 = new GraphNode(54);
//        GraphNode node8 = new GraphNode(63);
//        GraphNode node9 = new GraphNode(82);
//        graph.addNode(node);
//        graph.addNode(node1);
//        graph.addNode(node2);
//        graph.addNode(node3);
//        graph.addNode(node4);
//        graph.addNode(node5);
//        graph.addNode(node6);
//        graph.addNode(node7);
//        graph.addNode(node8);
//        graph.addNode(node9);
//        graph.addDirectedEdge(node,node1);
//        graph.addDirectedEdge(node1,node9);
//        graph.addDirectedEdge(node2,node3);
//        graph.addDirectedEdge(node3,node4);
//        graph.addDirectedEdge(node4,node9);

//        graph.addDirectedEdge(node,node6);
//        graph.addDirectedEdge(node6,node7);
//        graph.addDirectedEdge(node7,node9);

        //System.out.println(graph.findShortestPath(node, node9));


//        DijkstraGraph.GraphNode graphNode1 = new DijkstraGraph.GraphNode(1);
//        DijkstraGraph.GraphNode graphNode2 = new DijkstraGraph.GraphNode(2);
//        DijkstraGraph.GraphNode graphNode3 = new DijkstraGraph.GraphNode(3);
//        DijkstraGraph.GraphNode graphNode4 = new DijkstraGraph.GraphNode(4);
//        DijkstraGraph.GraphNode graphNode5 = new DijkstraGraph.GraphNode(5);
//        DijkstraGraph.GraphNode graphNode6 = new DijkstraGraph.GraphNode(6);
//        DijkstraGraph.GraphNode graphNode7 = new DijkstraGraph.GraphNode(7);
//        DijkstraGraph.GraphNode graphNode8 = new DijkstraGraph.GraphNode(8);
//        DijkstraGraph.GraphNode graphNode9 = new DijkstraGraph.GraphNode(9);
//        graphNode1.addEdge(graphNode2,5);
//        graphNode2.addEdge(graphNode3,5);
//        graphNode3.addEdge(graphNode4,5);
//        graphNode4.addEdge(graphNode5,5);
//        graphNode5.addEdge(graphNode9,5);
//        graphNode1.addEdge(graphNode6,5);
//        graphNode6.addEdge(graphNode9,5);
//        List<DijkstraGraph.GraphNode> list = new ArrayList<>();
//        list.add(graphNode1);
//        list.add(graphNode2);
//        list.add(graphNode3);
//        list.add(graphNode4);
//        list.add(graphNode5);
//        list.add(graphNode6);
//        list.add(graphNode7);
//        list.add(graphNode8);
//        list.add(graphNode9);
//        DijkstraGraph dijkstraGraph = new DijkstraGraph();
//        dijkstraGraph.nodes = list;
//        System.out.println(dijkstraGraph.dijkstra(graphNode1).get(graphNode9));
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(50);
//        tree.insert(30);
        System.out.println(tree.levelOrder());
        tree.delete(20);

    }
}