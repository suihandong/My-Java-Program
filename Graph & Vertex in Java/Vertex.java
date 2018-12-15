/**
 This is a class derived and adapted from the textbook: Vertex.java
 A class of vertices for a graph.

 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 4.0
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;


public class Vertex<T> implements VertexInterface {
    private T label;
    private ArrayList<Edge> edgeList;
    private boolean visited;

    public Vertex(T value) {
        label = value;
        edgeList = new ArrayList<Edge>();
        visited = false;
    }

    @Override
    public T getLabel() {
        return label;
    }

    @Override
    public void visit() {
        visited = true;
    }

    @Override
    public void unvisit() {
        visited = false;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }

    @Override
    public boolean connect(VertexInterface endVertex, double edgeWeight) {
        boolean result = false;

        if (!this.equals(endVertex))
        {  // Vertices are distinct
            Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
            boolean duplicateEdge = false;

            while (!duplicateEdge && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    duplicateEdge = true;
            } // end while

            if (!duplicateEdge)
            {
                edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            } // end if
        } // end if

        return result;
    }

    @Override
    public boolean connect(VertexInterface endVertex) {
        return false;
    }

    @Override
    public boolean neighbors(VertexInterface otherVertex) {
        return edgeList.stream().map(Edge::getEndVertex).collect(Collectors.toList()).contains(otherVertex);
    }

    @Override
    public Iterator<VertexInterface<T>> getNeighborIterator() {
        return new NeighborIterator();
    }

    private class NeighborIterator implements Iterator<VertexInterface<T>> {
        private Iterator<Edge> edges;

        private NeighborIterator() {
            edges = edgeList.iterator();
        } // end default constructor

        public boolean hasNext() {
            return edges.hasNext();
        } // end hasNext

        public VertexInterface<T> next() {
            VertexInterface<T> nextNeighbor = null;

            if (edges.hasNext()) {
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            } else
                throw new NoSuchElementException();

            return nextNeighbor;
        } // end next
    }

    protected class Edge
    {
        private VertexInterface<T> vertex; // Vertex at end of edge
        private double weight;

        protected Edge(VertexInterface<T> endVertex, double edgeWeight)
        {
            vertex = endVertex;
            weight = edgeWeight;
        } // end constructor

        protected VertexInterface<T> getEndVertex()
        {
            return vertex;
        } // end getEndVertex

        protected double getWeight()
        {
            return weight;
        } // end getWeight
    } // end Edge

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return isVisited() == vertex.isVisited() &&
                Objects.equals(getLabel(), vertex.getLabel()) &&
                Objects.equals(edgeList, vertex.edgeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabel(), edgeList, isVisited());
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label=" + label +
                '}';
    }
}
