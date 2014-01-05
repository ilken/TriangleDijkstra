package minimumtrianglepaths;

public class Vertex implements Comparable<Vertex>{
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    public int row;
    public int value;
    
    public Vertex(String argName) { 
        name = argName; 
    }
    
    public Vertex(String argName, int argRow){
        name = argName; 
        row = argRow;
        value = Integer.parseInt(argName);
    }

    public int getRow() {
        return row;
    }

    public int getValue() {
        return value;
    }
    
    @Override
    public String toString() { 
        return name; 
    }
    
    @Override
    public int compareTo(Vertex other){
        return Double.compare(minDistance, other.minDistance);
    }
}
