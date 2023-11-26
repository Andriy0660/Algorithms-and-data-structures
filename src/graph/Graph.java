package graph;

import java.util.*;

public class Graph {
    private final List<GraphNode> nodes;
    public static class GraphNode {
        int value;
        List<GraphNode> neighbors;

        public GraphNode(int value) {
            this.value = value;
            this.neighbors = new ArrayList<>();
        }

        public int getValue() {
            return value;
        }

        public List<GraphNode> getNeighbors() {
            return neighbors;
        }

        public void addNeighbor(GraphNode neighbor) {
            neighbors.add(neighbor);
        }

        @Override
        public String toString() {
            return "Node: " + value;
        }
    }

    public Graph() {
        nodes = new ArrayList<>();
    }

    public List<GraphNode> getNodes() {
        return nodes;
    }

    public void addNode(GraphNode node) {
        nodes.add(node);
    }

    public void addUndirectedEdge(GraphNode node1, GraphNode node2) {
        node1.addNeighbor(node2);
        node2.addNeighbor(node1);
    }
    public void addDirectedEdge(GraphNode node1, GraphNode node2) {
        node1.addNeighbor(node2);
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (GraphNode node : nodes) {
            s.append(node.value).append(": ");
            for (int j = 0; j < node.neighbors.size(); j++) {
                s.append((node.neighbors.get(j).value)).append("\t");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public void bfs() {
        Set<GraphNode> visited = new HashSet<>();
        for (GraphNode node : nodes) {
            if (!visited.contains(node)) {
                bfs(node, visited);
            }
        }
    }
    private void bfs(GraphNode node, Set<GraphNode> visited) {
        Queue<GraphNode> queue = new LinkedList<>();

        queue.offer(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            GraphNode currentNode = queue.poll();
            System.out.print(currentNode.value + " ");

            for (GraphNode neighbor : currentNode.neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
    public void dfs() {
        Set<GraphNode> visited = new HashSet<>();
        for (GraphNode node : nodes) {
            if (!visited.contains(node)) {
                dfs(node, visited);
            }
        }
    }
    private void dfs(GraphNode node, Set<GraphNode> visited) {
        Stack<GraphNode> stack = new Stack<>();
        stack.push(node);
        visited.add(node);
        while(!stack.isEmpty()) {
            GraphNode currentNode = stack.pop();
            System.out.print(currentNode.value + " ");

            for (GraphNode neighbor : currentNode.neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
    public void topologicalSort() {
        Stack<GraphNode> stack = new Stack<>();
        Set<GraphNode> visited = new HashSet<>();
        for (GraphNode node : nodes) {
            if (!visited.contains(node)) {
                topologicalVisit(node, stack, visited);
            }
        }
        while(!stack.isEmpty()) {
            System.out.print(stack.pop().value + " ");
        }
    }

    private void topologicalVisit(GraphNode node, Stack<GraphNode> stack, Set<GraphNode> visited) {
        for (GraphNode neighbor : node.neighbors) {
            if (!visited.contains(neighbor)) {
                topologicalVisit(neighbor, stack, visited);
            }
        }
        visited.add(node);
        stack.push(node);
    }
    public List<GraphNode> findShortestPath(GraphNode start, GraphNode end) {
        Queue<GraphNode> queue = new LinkedList<>();
        Map<GraphNode, GraphNode> parentMap = new HashMap<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();
            if (current.equals(end)) {
                List<GraphNode> path = new ArrayList<>();
                while (current != start) {
                    path.add(current);
                    current = parentMap.get(current);
                }
                path.add(start);
                Collections.reverse(path);
                return path;
            }
            for (GraphNode neighbor : current.neighbors) {
                if (!parentMap.containsKey(neighbor)) {
                    parentMap.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }
}
