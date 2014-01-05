package minimumtrianglepaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MinimumTrianglePaths{
    public static void computePaths(Vertex source){
        source.minDistance = 0.0;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
      	vertexQueue.add(source);

	while (!vertexQueue.isEmpty()) {
	    Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            if(u.adjacencies != null)
            for (Edge e : u.adjacencies){
                Vertex v = e.target;                
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU ;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target){
        List<Vertex> path = new ArrayList<>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }
    
    private static int[] convertStringToIntArr(String l) throws Exception{
        String[] numbers = l.split(" ");
        int[] result = new int[numbers.length];
        for(int i=0; i < numbers.length; i++){
            integerCheck(numbers[i]);  
            result[i] = Integer.parseInt(numbers[i]);
        }
        return result;
    }
    
    private static void triangleSizeCheck(int expectedSize, int currentSize) throws Exception{
        if(expectedSize != currentSize){
            throw new Exception("Invalid Triangle Size!\n Check line " + expectedSize +" in text file."); 
        }
    }

    private static void integerCheck(String x) throws Exception{
        try {
            if(Integer.parseInt( x ) < 0){
                throw new Exception(""+x +" is not a positive integer!"); 
            }
        }
        catch( NumberFormatException e ) {
            throw new Exception(""+x +" is not a positive integer!"); 
        }
    }
    
    public static void main(String[] args) throws Exception{
        try{
            File file = new File(args[0]);
            List<Vertex> triangle = new ArrayList<>();
            int lineCount = 0;
            int nodeCount = 0;
            
            //Read from text file and write into ArrayList
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    lineCount++;
                    int[] line = convertStringToIntArr(scanner.nextLine());
                    triangleSizeCheck(lineCount,line.length);
                    for(int i=0; i<line.length; i++){                                   
                        triangle.add(new Vertex(line[i]+"",lineCount));
                        nodeCount++;
                    }                       
                }
            }
            
            //Create child nodes for each node
            for(int i=0; i<triangle.size(); i++){
                int row = triangle.get(i).getRow();
                if(row < lineCount){
                    triangle.get(i).adjacencies =   new Edge[]{
                                                        new Edge(triangle.get(i+row),triangle.get(i+row).getValue()),
                                                        new Edge(triangle.get(i+row+1),triangle.get(i+row+1).getValue())
                                                    };
                }
            }
            
            //Compute all paths
            computePaths(triangle.get(0));
            List<Vertex> minPath = new ArrayList<>();
            int minSum = Integer.MAX_VALUE;
            for(int i=0; i<nodeCount; i++){              
                if(triangle.get(i).getRow() == lineCount){
                    int tmpSum = 0;
                    List<Vertex> tmpPath = getShortestPathTo(triangle.get(i));
                    System.out.println("Possible Path: " + tmpPath);
                    for(int j=0; j<tmpPath.size(); j++){
                        tmpSum += tmpPath.get(j).getValue();
                    }
                    if(minSum > tmpSum){
                        minSum = tmpSum;
                        minPath = tmpPath;
                    }
                }
            }
            
            System.out.println("Min Path: " + minPath + " = "+ minSum);
            
        } catch (FileNotFoundException e) {
            
        }
    }
    
}