import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class Task implements Runnable {

    private final Graph graph;
    private final int startingNode;
    private final List<Integer> path;
    private final Lock lock;
    private final List<Integer> result;

    Task(Graph graph, int node, List<Integer> result, Lock lock) {
        this.graph = graph;
        this.startingNode = node;
        this.path = new ArrayList<>();
        this.lock = lock;
        this.result = result;
    }

    @Override
    public void run() {
        visit(startingNode);
    }

    private void visit(int node) {
        path.add(node);

        if (path.size() == graph.size()) {
            if (graph.neighboursOf(node).contains(startingNode)){
                setResult();
            }
            return;
        }

        for (int neighbour : graph.neighboursOf(node)) {
            if (!path.contains(neighbour)){
                visit(neighbour);
            }
        }
    }

    private void setResult() {
        lock.lock();
        result.clear();
        result.addAll(this.path);
        lock.unlock();
    }

}