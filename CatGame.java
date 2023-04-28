import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.DijkstraUndirectedSP;
import edu.princeton.cs.algs4.Edge;

public class CatGame {
    int bs;
    int[] catPos = {0, 0};
    boolean[] markedTiles;
    EdgeWeightedGraph g;

    CatGame(int n) {
        g = new EdgeWeightedGraph((n*n)+1);

        //Create all edges.
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                //Current node
                int v = row*n + col;
                //Connect forward
                if (col != n-1) g.addEdge(new CatEdge(v, v+1));
                //Connect down
                if (row != n-1) g.addEdge(new CatEdge(v, (row+1)*n + col));
                //If even row connect down and to the left, if odd connect down and to the right
                if (row % 2 == 0) {
                    if (col != 0) g.addEdge(new CatEdge(v, (row+1)*n + col-1));
                } else {
                    if (col != n-1) g.addEdge(new CatEdge(v, (row+1)*n + col+1));
                }
                //Connect all edges to freedom hexagon
                if (row == 0 || row == n-1 || col == 0 || col == n-1) g.addEdge(new CatEdge(v, (n*n)));
            }
        }

        //Start cat in the middle.
        catPos[0] = n/2;
        catPos[1] = n/2;

        bs = n;
        markedTiles = new boolean[(n*n)+1];
    }

    void markTile(int row, int col) {
        int v = row*bs + col;
        markedTiles[v] = true;
        int cat = catPos[0]*bs + catPos[1];
        for(Edge e : g.adj(v)) {
            CatEdge ce = (CatEdge) e;
            ce.setWeight(Double.POSITIVE_INFINITY);
        }
        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(g, cat);
        CatEdge next = (CatEdge) sp.pathTo(bs*bs).iterator().next();
        int nextSquare = next.other(cat);
        catPos[0] = nextSquare/bs;
        catPos[1] = nextSquare%bs;
    }

    boolean marked(int row, int col) {
        return markedTiles[row*bs + col];
    }

    int[] getCatTile() {
        return catPos;
    }

    boolean catHasEscaped() {
        if (catPos[0]*bs+catPos[1] == bs*bs) return true;
        else return false;
    }

    boolean catIsTrapped() {
        int s = catPos[0]*bs + catPos[1];
        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(g, s);
        if (sp.distTo(bs*bs) == Double.POSITIVE_INFINITY) return true;
        else return false;
    }
}