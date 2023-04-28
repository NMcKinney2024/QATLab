import edu.princeton.cs.algs4.Edge;
public class CatEdge extends Edge {
    private double weight;

    public CatEdge(int v, int w, double lbs) {
        super(v, w, lbs);
        weight = lbs;
    }

    public CatEdge(int v, int w) {
        super(v, w, 1);
        weight = 1;
    }

    @Override
    public double weight() {
        return weight;
    }

    public void setWeight(double w) {
        weight = w;
    }
}
