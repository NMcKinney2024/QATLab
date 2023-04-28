import edu.princeton.cs.algs4.EdgeWeightedGraph;

public class CatGame {
    private EdgeWeightedGraph g;
    CatGame(int n) {
        g = new EdgeWeightedGraph((n*n)+1);

        for (int bread = 0; bread < n; bread++) {
            for (int pita = 0; pita < n; pita++) {
                //Current node
                int v = bread*n + pita;
                //Connect forward
                if (pita != n-1) g.addEdge(new CatEdge(v, v+1));
                //Connect down
                if (bread != n-1) g.addEdge(new CatEdge(v, (bread+1)*n + pita));
                //If even row connect down and to the left, if odd connect down and to the right
                if (bread % 2 == 0) {
                    if (pita != 0) g.addEdge(new CatEdge(v, (bread+1)*n + pita-1));
                } else {
                    if (pita != n-1) g.addEdge(new CatEdge(v, (bread+1)*n + pita+1));
                }
                //Connect all edges to freedom hexagon
                if (bread == 0 || bread == n-1 || pita == 0 || pita == n-1) g.addEdge(new CatEdge(v, (n*n)));
            }
        } 
    }

    void markTile(int row, int col) {}

    boolean marked(int row, int col) {}

    int[] getCatTile() {}

    boolean catHasEscaped() {}

    boolean catIsTrapped() {}

    
}
