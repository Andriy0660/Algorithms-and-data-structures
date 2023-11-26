package graph;

import java.util.*;

public class DijkstraGraph {
    public List<GraphNode> nodes;

    public static class GraphNode {
        int value;
        List<GraphEdge> edges;

        public GraphNode(int value) {
            this.value = value;
            this.edges = new ArrayList<>();
        }

        public void addEdge(GraphNode neighbor, int weight) {
            edges.add(new GraphEdge(neighbor, weight));
        }

        @Override
        public String toString() {
            return "GraphNode{" +
                    "value=" + value +
                    '}';
        }
    }

    public static class GraphEdge {
        GraphNode neighbor;
        int weight;

        public GraphEdge(GraphNode neighbor, int weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }
    public static class NodeInfo {
        Integer distance;
        List<DijkstraGraph.GraphNode> path;

        public NodeInfo(Integer distance, List<DijkstraGraph.GraphNode> path) {
            this.distance = distance;
            this.path = path;
        }

        @Override
        public String toString() {
            return "NodeInfo{" +
                    "distance=" + distance +
                    ", path=" + path +
                    '}';
        }
    }


    public Map<GraphNode, NodeInfo> dijkstra(GraphNode startNode) {
        Map<GraphNode, NodeInfo> shortestDistances = new HashMap<>();
        PriorityQueue<GraphNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> shortestDistances.get(o).distance));

        shortestDistances.put(startNode, new NodeInfo(0, new ArrayList<>()));
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            GraphNode currentNode = queue.poll();
            int currentDistance = shortestDistances.get(currentNode).distance;

            for (GraphEdge edge : currentNode.edges) {
                int newDistance = currentDistance + edge.weight;

                if (!shortestDistances.containsKey(edge.neighbor) || newDistance < shortestDistances.get(edge.neighbor).distance) {
                    List<GraphNode> pathToNeighbor = new ArrayList<>(shortestDistances.get(currentNode).path);
                    pathToNeighbor.add(currentNode);
                    shortestDistances.put(edge.neighbor, new NodeInfo(newDistance,pathToNeighbor));
                    queue.offer(edge.neighbor);
                }
            }
        }
        return shortestDistances;
    }
}
