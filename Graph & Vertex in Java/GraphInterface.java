import java.util.Queue;

/**
 This is a class derived and adapted from the textbook: GraphInterface.java
 An interface of methods that create, manipulate, and process a graph.

 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 4.0
 */

public interface GraphInterface<T> {
    /** Adds a given vertex to this graph.
     @param vertexLabel  An object that labels the new vertex and is
     distinct from the labels of current vertices.
     @return  True if the vertex is added, or false if not. */
    public boolean addVertex(T vertexLabel);

    /** Adds a weighted edge between two given distinct vertices that
     are currently in this graph. The desired edge must not already
     be in the graph. In a directed graph, the edge points toward
     the second vertex given.
     @param begin  An object that labels the origin vertex of the edge.
     @param end    An object, distinct from begin, that labels the end
     vertex of the edge.
     @param edgeWeight  The real value of the edge's weight.
     @return  True if the edge is added, or false if not. */
    public boolean addEdge(T begin, T end, double edgeWeight);

    /** Adds an unweighted edge between two given distinct vertices
     that are currently in this graph. The desired edge must not
     already be in the graph. In a directed graph, the edge points
     toward the second vertex given.
     @param begin  An object that labels the origin vertex of the edge.
     @param end    An object, distinct from begin, that labels the end
     vertex of the edge.
     @return  True if the edge is added, or false if not. */
    public boolean addEdge(T begin, T end);

    /** Sees whether an edge exists between two given vertices.
     @param begin  An object that labels the origin vertex of the edge.
     @param end    An object that labels the end vertex of the edge.
     @return  True if an edge exists. */
    public boolean hasEdge(T begin, T end);

    /** Sees whether this graph is empty.
     @return  True if the graph is empty. */
    public boolean isEmpty();

    /** Gets the number of vertices in this graph.
     @return  The number of vertices in the graph. */
    public int getNumberOfVertices();

    /** Gets the number of edges in this graph.
     @return  The number of edges in the graph. */
    public int getNumberOfEdges();

    /** Removes all vertices and edges from this graph. */
    public void clear();

    /** Gets the vertex based on the input label in this graph.
     @return  The vertex specified by the label in this graph. */
    public VertexInterface<T> getVertex(T label);

    /**
     * Performs a breadth first search on the graph.
     * @param origin the desired starting vertex.
     * @return a queue of the nodes visited in order.
     */
    public Queue<VertexInterface<T>> breadthFirstSearch(T origin);

    /**
     * Performs a recursive depth first search on the graph.
     * @param origin the desired starting vertex.
     * @return a queue of the nodes visited in order.
     */
    public Queue<VertexInterface<T>> depthFirstSearchRecursive(T origin);

    /**
     * Performs an iterative depth first search on the graph.
     * @param origin the desired starting vertex.
     * @return a queue of the nodes visited in order.
     */
    public Queue<VertexInterface<T>> depthFirstSearchIterative(T origin);
}
