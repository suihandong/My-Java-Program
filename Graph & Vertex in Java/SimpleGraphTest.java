import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class SimpleGraphTest {
    protected GraphInterface<Integer> testGraph;

    @Before
    public void setUp() throws Exception {
        testGraph = new SimpleGraph();
        for (int i = 0; i < 10; i++) {
            testGraph.addVertex(i);
        }
        testGraph.addEdge(1, 6, 1.0);
        testGraph.addEdge(6, 1, 1.0);
        testGraph.addEdge(1, 9, 2.0);
        testGraph.addEdge(9, 1, 2.0);
        testGraph.addEdge(9, 7, 3.0);
        testGraph.addEdge(7, 9, 3.0);
        testGraph.addEdge(9, 8, 4.0);
        testGraph.addEdge(8, 9, 4.0);
        testGraph.addEdge(6, 5, 5.0);
        testGraph.addEdge(5, 6, 5.0);
        testGraph.addEdge(6, 4, 6.0);
        testGraph.addEdge(4, 6, 6.0);
        testGraph.addEdge(6, 2, 7.0);
        testGraph.addEdge(2, 6, 7.0);
        testGraph.addEdge(6, 3);
        testGraph.addEdge(3, 6);
    }

    @Test
    public void breadthFirstSearch() {
        Queue<VertexInterface<Integer>> breadthQueue = testGraph.breadthFirstSearch(1);
        System.out.println("breadthQueue: " + breadthQueue);

        List<List<Integer>> distanceAway = new ArrayList<>();
        distanceAway.add(new ArrayList<Integer>(Arrays.asList(1)));
        distanceAway.add(new ArrayList<Integer>(Arrays.asList(6, 9)));
        distanceAway.add(new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5, 7, 8)));

        int currentDistance = 0;
        for (VertexInterface<Integer> vertex : breadthQueue) {
            if (distanceAway.get(currentDistance).contains(vertex.getLabel())) {
                distanceAway.get(currentDistance).remove(vertex.getLabel());
            } else {
                assertTrue(distanceAway.get(++currentDistance).contains(vertex.getLabel()));
                distanceAway.get(currentDistance).remove(vertex.getLabel());
            }
        }

        for (List<Integer> shell : distanceAway) {
            assertTrue("Element(s) " + shell + " was/were not returned by the traversal", shell.isEmpty());
        }
    }

    @Test
    public void depthFirstSearchRecursive() {
        Queue<VertexInterface<Integer>> depthQueue = testGraph.depthFirstSearchRecursive(1);
        System.out.println("depthQueue: " + depthQueue);

        List<Integer> vertexList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<VertexInterface<Integer>> previousNodes = new ArrayList<>();

        for (VertexInterface<Integer> currentVertex : depthQueue) {
            vertexList.remove(currentVertex.getLabel());
            if (previousNodes.size() == 0 || previousNodes.get(previousNodes.size() - 1).neighbors(currentVertex)) {
                previousNodes.add(currentVertex);
            } else {
                VertexInterface<Integer> lastNeighboringNode = previousNodes.stream()
                        .filter(n -> n.neighbors(currentVertex)).reduce((first, second) -> second).get();
                assertNotNull(lastNeighboringNode);
                previousNodes = previousNodes.subList(0, previousNodes.lastIndexOf(lastNeighboringNode) + 1);
                previousNodes.add(currentVertex);
            }
        }

        assertTrue("Element(s) " + vertexList + " was/were not returned by the traversal", vertexList.isEmpty());
    }

    @Test
    public void depthFirstSearchIterative() {
        Queue<VertexInterface<Integer>> depthQueue = testGraph.depthFirstSearchIterative(1);
        System.out.println("depthQueue: " + depthQueue);

        List<Integer> vertexList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<VertexInterface<Integer>> previousNodes = new ArrayList<>();

        for (VertexInterface<Integer> currentVertex : depthQueue) {
            vertexList.remove(currentVertex.getLabel());
            if (previousNodes.size() == 0 || previousNodes.get(previousNodes.size() - 1).neighbors(currentVertex)) {
                previousNodes.add(currentVertex);
            } else {
                VertexInterface<Integer> lastNeighboringNode = previousNodes.stream()
                        .filter(n -> n.neighbors(currentVertex)).reduce((first, second) -> second).get();
                assertNotNull(lastNeighboringNode);
                previousNodes = previousNodes.subList(0, previousNodes.lastIndexOf(lastNeighboringNode) + 1);
                previousNodes.add(currentVertex);
            }
        }

        assertTrue("Element(s) " + vertexList + " was/were not returned by the traversal", vertexList.isEmpty());
    }
}