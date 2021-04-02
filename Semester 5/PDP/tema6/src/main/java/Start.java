import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Start {
    private static final Integer GRAPHS = 100;

    /**
     *         Start the finding algorithm on a certain amount of tasks.
     */
    private static void startFind(List<Graph> graphs, Integer threads) throws InterruptedException {
        for (int testNo = 0; testNo < graphs.size(); testNo++) {
            long startTime = System.nanoTime();

            findHamiltonianCycle(graphs.get(testNo), threads);

            long endTime = System.nanoTime();
            long elapsed = (endTime - startTime) / 1000000;

            if (testNo == 1 || testNo == GRAPHS/2 || testNo == GRAPHS - 1) {
                System.out.println("Graph " + testNo + ": " + elapsed + " ms");
            }
        }
    }

    /**
     *         Create a threadpool and start the search
     */
    private static void findHamiltonianCycle(Graph graph, Integer threadCount) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        Lock lock = new ReentrantLock();
        List<Integer> result = new ArrayList<>(graph.size());

        for (int i = 0; i < graph.size(); i++){
            pool.execute(new Task(graph, i, result, lock));
        }

        pool.shutdown();

        pool.awaitTermination(10, TimeUnit.SECONDS);
    }

    /**
     *         Generating a random Hamiltonian cycle in a graph.
     */
    private static Graph generateRandomHamiltonian(Integer size) {
        Graph graph = new Graph(size);

        List<Integer> nodes = graph.getNodes();

        java.util.Collections.shuffle(nodes);

        for (int i = 1; i < nodes.size(); i++){
            graph.addEdge(nodes.get(i - 1),  nodes.get(i));
        }

        graph.addEdge(nodes.get(nodes.size() -1), nodes.get(0));

        Random random = new Random();

        for (int i = 0; i < size / 2; i++){
            int nodeA = random.nextInt(size - 1);
            int nodeB = random.nextInt(size - 1);

            graph.addEdge(nodeA, nodeB);
        }

        return graph;
    }

    public static void main(String[] args) throws InterruptedException {

        List<Graph> graphs = new ArrayList<>();

        // Generating GRAPHS to be tested
        for (int i = 1; i <= GRAPHS; i++) {
            graphs.add(generateRandomHamiltonian(i * 10));
        }

        // Comparing sequential and parallel runs

        System.out.println("Sequential");
        startFind(graphs, 1);

        System.out.println("Parallel");
        startFind(graphs, 4);

    }

}