package graph;

import exception.BadVertexException;
import exception.DuplicateTagException;
import models.IEdge;
import models.IVertex;
import models.tools.Color;

import java.util.*;

public class Graph {
    private Map<IVertex, List<IVertex>> adjacencyList;
    private Map<IVertex, Map<IVertex, IEdge>> edges;
    private Map<String, IVertex> tags;
    private Map<IVertex, Integer> inDegree;
    private int nbEdges;

    public Graph(){
        adjacencyList = new HashMap<IVertex, List<IVertex>>();
        edges = new HashMap<IVertex, Map<IVertex, IEdge>>();
        inDegree = new HashMap<IVertex, Integer>();
        nbEdges = 0;
    }

    public int nbVertices() {
        return adjacencyList.size();
    }

    public int nbEdges() {
        return nbEdges;
    }

    public IVertex getVertex(String tag) {
        if ( ! tags.containsKey(tag) )
            return null;
        return tags.get(tag);
    }

    public IVertex addVertex(String tag, Color color){
        if ( tags.containsKey(tag) )
            throw new DuplicateTagException(tag);
        Vertex v = new Vertex(this,tag, color);
        tags.put(tag, v);
        adjacencyList.put(v,new LinkedList<IVertex>());
        inDegree.put(v,0);
        return v;
    }

    private void checkVertex(IVertex v) {
        if ( ((Vertex) v).fromGraph != this )
            throw new BadVertexException(v.getTag());
    }

    private boolean add(IVertex u, IVertex v) {
        if ( adjacencyList.get(u).contains(v) )
            return false;
        adjacencyList.get(u).add(v);
        return true;
    }

    protected boolean remove(IVertex u, IVertex v) {
        if ( adjacencyList.get(u).contains(v) )
            return false;
        adjacencyList.get(u).remove(v);
        return false;
    }

    private void storeEdge(IVertex u , IVertex v, Color color) {
        if ( edges.get(u) == null )
            edges.put(u, new HashMap<IVertex, IEdge>());
        edges.get(u).put(v,new Edge(u,v,color));
    }

    public void addEdge(IVertex u, IVertex v, Color color) {
        checkVertex(u);
        checkVertex(v);
        if ( add(u,v) ) {
            storeEdge(u,v,color);
            nbEdges++;
            inDegree.put(v,inDegree.get(v)+1);
        }
    }

    public void removeEdge(IVertex u, IVertex v) {
        checkVertex(u);
        checkVertex(v);
        if ( remove(u,v) ) {
            nbEdges--;
            inDegree.put(v,inDegree.get(v)-1);
        }
    }

    public int degree(IVertex u) {
        checkVertex(u);
        return outDegree(u) + inDegree(u);
    }

    public int outDegree(IVertex u) {
        checkVertex(u);
        return adjacencyList.get(u).size();
    }

    public int inDegree(IVertex u) {
        checkVertex(u);
        return inDegree.get(u);
    }

    protected IEdge findEdge(IVertex u, IVertex v) {
        return edges.get(u).get(v);
    }

    public String toString() {
        String s = "";
        s += "number of verticies: " + nbVertices();
        s += "\nnumber of edges: " + nbEdges;
        s += "\nvertices:";
        for ( IVertex u : vertices() )
            s += " " + u;
        s += "\nedges :";
        for ( IEdge e : edges() )
            s += " " + e;
        return "Graph : \n" + s;
    }

    public Iterable<IVertex> vertices() {
        return adjacencyList.keySet();
    }

    public Iterable<IEdge> edges() {
        return new EdgeIterator();
    }


    private class EdgeIterator implements Iterable<IEdge>, Iterator<IEdge> {

        Iterator<Map.Entry<IVertex,Map<IVertex, IEdge>>> firstMapIterator;
        Iterator<Map.Entry<IVertex, IEdge>> secondMapIterator;

        EdgeIterator() {
            firstMapIterator = edges.entrySet().iterator();
            if ( firstMapIterator.hasNext() ) {
                secondMapIterator = firstMapIterator.next().getValue().entrySet().iterator();
            }

        }

        public Iterator<IEdge> iterator() {
            return this;
        }

        public boolean hasNext() {
            return secondMapIterator.hasNext() || firstMapIterator.hasNext();
        }

        public IEdge next() {
            if ( ! secondMapIterator.hasNext() )
                secondMapIterator = firstMapIterator.next().getValue().entrySet().iterator();
            return secondMapIterator.next().getValue();
        }
    }
}
