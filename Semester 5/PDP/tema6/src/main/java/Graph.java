import java.util.ArrayList;
import java.util.List;

class Graph {
    private final List<List<Integer>> wrapper;
    private final List<Integer> nodes;

    Graph(int sizing) {
        this.wrapper = new ArrayList<>(sizing);
        this.nodes = new ArrayList<>();

        for (int i = 0; i < sizing; i++) {
            wrapper.add(new ArrayList<>());
            nodes.add(i);
        }
    }

    void addEdge(int sourceNode, int targetNode) {
        wrapper.get(sourceNode).add(targetNode);
    }

    List<Integer> neighboursOf(int node) {
        return wrapper.get(node);
    }

    List<Integer> getNodes(){
        return nodes;
    }

    int size() {
        return wrapper.size();
    }

}