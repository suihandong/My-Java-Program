/**
 This is a class derived and adapted from the textbook: DirectedGraph.java
 A class that implements the ADT directed graph.

 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 4.0
 */
import java.util.*;

public class SimpleGraph<T> implements GraphInterface<T> {
    private HashMap<T,  VertexInterface<T>> vertices;
    private int edgeCount;

    public SimpleGraph() {
        vertices = new HashMap<T, VertexInterface<T>>();
        edgeCount = 0;
    }

    @Override
    public boolean addVertex(T vertexLabel) {
        return null == vertices.put(vertexLabel, new Vertex<>(vertexLabel));
    }

    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        boolean result = false;

        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        if ( (beginVertex != null) && (endVertex != null) )
            result = beginVertex.connect(endVertex, edgeWeight);

        if (result)
            edgeCount++;

        return result;
    }

    @Override
    public boolean addEdge(T begin, T end) {

        return addEdge(begin, end, 0);
    }

    @Override
    public boolean hasEdge(T begin, T end) {
        boolean found = false;

        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        if ( (beginVertex != null) && (endVertex != null) )
        {
            Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
            while (!found && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    found = true;
            } // end while
        } // end if

        return found;
    }

    @Override
    public boolean isEmpty() {

        return vertices.isEmpty();
    }

    @Override
    public int getNumberOfVertices() {

        return vertices.size();
    }

    @Override
    public int getNumberOfEdges() {

        return edgeCount;
    }

    @Override
    public void clear() {
        vertices.clear();
        edgeCount = 0;
    }

    @Override
    public VertexInterface<T> getVertex(T label) {

        return vertices.get(label);
    }

    private class VertexIterator implements Iterator<VertexInterface<T>> {
        private Iterator<Map.Entry<T, VertexInterface<T>>> verticeIterator;

        private VertexIterator() {

            verticeIterator = vertices.entrySet().iterator();
        } // end default constructor

        public boolean hasNext() {

            return verticeIterator.hasNext();
        } // end hasNext

        public  VertexInterface<T> next() {
            return verticeIterator.hasNext() ? vertices.get(verticeIterator.next().getValue().getLabel()) : null;
        } // end next
    }

    public VertexIterator getVertexIterator() {

        return new VertexIterator();
    }

    @Override
    public Queue<VertexInterface<T>> breadthFirstSearch(T origin) {
        Queue<VertexInterface<T>> travelOrder = new LinkedList<>();
        Queue<VertexInterface<T>> vertexQueue = new LinkedList<>();

        VertexInterface<T> originVertex = vertices.get(origin);
        originVertex.visit();

        travelOrder.add(originVertex);
        vertexQueue.add(originVertex);

        while(!vertexQueue.isEmpty()){
            VertexInterface<T> frontVertex = vertexQueue.remove();
            //System.out.println("111");

            Iterator<VertexInterface<T>> i = frontVertex.getNeighborIterator();
            while(i.hasNext()){
                VertexInterface<T> nextVertex = i.next();

                if(!nextVertex.isVisited()){
                    nextVertex.visit();
                    travelOrder.add(nextVertex);
                    vertexQueue.add(nextVertex);
                }

            }
        }//end of while loop

        return travelOrder;
    }

    @Override
    public Queue<VertexInterface<T>> depthFirstSearchRecursive(T origin) {
        Queue<VertexInterface<T>> travelOrder = new LinkedList<>();

        depthFirstSearchHelper(origin, travelOrder);

        return travelOrder;
    }

    private void depthFirstSearchHelper(T origin, Queue<VertexInterface<T>> q) {
        VertexInterface<T> originVertex = vertices.get(origin);
        originVertex.visit();
        q.add(originVertex);

        Iterator<VertexInterface<T>> i = originVertex.getNeighborIterator();
        while(i.hasNext()){
            VertexInterface<T> nextNeighbor = i.next();
            if(!nextNeighbor.isVisited()) {
                depthFirstSearchHelper(nextNeighbor.getLabel(),q);
            }

        }
    }

    @Override
    public Queue<VertexInterface<T>> depthFirstSearchIterative(T origin) {
        Queue<VertexInterface<T>> travelOrder = new LinkedList<>();
        Stack<VertexInterface<T>> vertexStack = new Stack<>();

        VertexInterface<T> originVertex = vertices.get(origin);
        originVertex.visit();

        travelOrder.add(originVertex);
        vertexStack.push(originVertex);

        while(!vertexStack.isEmpty()){
            VertexInterface<T> topVertex = vertexStack.peek();

            Iterator<VertexInterface<T>> i = topVertex.getNeighborIterator();
            boolean flag = false;
            while(i.hasNext()){

                VertexInterface<T> nextNeighbor = i.next();

                if(!nextNeighbor.isVisited()){
                    travelOrder.add(nextNeighbor);
                    vertexStack.push(nextNeighbor);
                    nextNeighbor.visit();
                    flag = true;
                    break;
                }
            }
            if(!flag){
                vertexStack.pop();
            }

        }

        return travelOrder;
    }

}
