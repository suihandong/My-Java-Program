/**
 This is a class derived and adapted from the textbook: VertexInterface.java
 An interface for a vertex in a graph.

 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 4.0
 */
import java.util.Iterator;


public interface VertexInterface<T>
{
    /** Gets this vertex's label.
     @return  The object that labels the vertex. */
    public T getLabel();

    /** Marks this vertex as visited. */
    public void visit();

    /** Removes this vertex's visited mark. */
    public void unvisit();

    /** Sees whether the vertex is marked as visited.
     @return  True if the vertex is visited. */
    public boolean isVisited();

    /** Connects this vertex and a given vertex with a weighted edge.
     The two vertices cannot be the same, and must not already
     have this edge between them. In a directed graph, the edge
     points toward the given vertex.
     @param endVertex   A vertex in the graph that ends the edge.
     @param edgeWeight  A real-valued edge weight, if any.
     @return  True if the edge is added, or false if not. */
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight);

    /** Connects this vertex and a given vertex with an unweighted
     edge. The two vertices cannot be the same, and must not
     already have this edge between them. In a directed graph,
     the edge points toward the given vertex.
     @param endVertex   A vertex in the graph that ends the edge.
     @return  True if the edge is added, or false if not. */
    public boolean connect(VertexInterface<T> endVertex);

    /** Creates an iterator of this vertex's neighbors by following
     all edges that begin at this vertex.
     @return  An iterator of the neighboring vertices of this vertex. */
    public Iterator<VertexInterface<T>> getNeighborIterator();

    public boolean neighbors(VertexInterface<T> otherVertex);
}
