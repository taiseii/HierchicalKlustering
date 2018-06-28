
/**
 * Assignment 1
 * 
 *uncomment the part of the implementation bit so you can pick either one 
 * 
 * DBSCAN will find core points in a dataset and cluster the data based on these
 * core points. A core point is a point with at least minPts nodes within a 
 * distance epsilon.
 * 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class assignment1 {

    int k = 4;
    int n, d, minPts;
    int epsilon;
    ClusterPoint[] points;

    /**
     * Set to true before submitting. Don't forget to save.
     */
    static boolean submitting = false;

    public static void main(String[] args) throws IOException {
        int i = 2;
        String path_in = String.format("C:/Users/jhoik/Documents/JBIO40/input/%02d.in", i);
        String path_out = String.format("C:/Users/jhoik/Documents/JBIO40/output/%02d.out", i);
        long t1 = System.currentTimeMillis();
        (new assignment1()).run(submitting, path_in, path_out);
        long t2 = System.currentTimeMillis();
        System.out.println("time taken: " + (t2 - t1) / 1000);

    }

    public void run(boolean submitting, String path_in, String path_out) throws IOException {

        try {
            readInput(submitting, path_in);
        } catch (FileNotFoundException ex) {
            // Logger.getLogger(DBSCAN.class.getName()).log(Level.SEVERE, null, ex);
        }


        //Beginning of the implementation of the HKLUSTERING
        ArrayList<Node> computedRoots = new ArrayList<>();

        // implementation of the Hierarchical clustering -----
        
        HKlustering hk = new HKlustering(points, n, k);
        computedRoots.addAll(hk.computeRoot());
        //-----------------------------------------------------

        // implementation of the Faster one--------------------
        // HKlusteringKai hkai = new HKlusteringKai(points, k, n);
        // System.out.println( hkai.getRoot() );
        // computedRoots.addAll(hkai.getRoot());
        //-----------------------------------------------------
        




        System.out.println("computedRoot size: " + computedRoots.size());

        // labeling of the ClusterPoint --------
        ArrayList<ClusterPoint> prodakt = new ArrayList<>();
        for (int i = 0; i < computedRoots.size(); i++) {
            ArrayList<ClusterPoint> labeled = new ArrayList<>();

            System.out.println("prodakt size: " + prodakt.size());
            System.out.println("labeled size: " + labeled.size());

            ExPoints extract = new ExPoints(computedRoots.get(i));
            labeled.addAll(extract.getU());
            for (int j = 0; j < labeled.size(); j++) {
                labeled.get(j).setCluster(i);
            }
            prodakt.addAll(labeled);
        }
        System.out.println("final prodakt size: " + prodakt.size());
        // replace object inside points as the prodakt so points are labeled
        for (int i = 0; i < prodakt.size(); i++) {
            points[i] = prodakt.get(i);
        }
        int cluster_counter = k;
        // end of implementation of the both Hierarchical clustering -----

        if (submitting) {
            printOutput(cluster_counter);
        } else {
            writeOutput(cluster_counter, path_out);
        }
        System.err.println(cluster_counter);
    }

    /**
     * 
     * @param stdIO if true, use System.in (USE FOR SUBMITTING)
     * @param path  if !stdIO read from this file (USE FOR TESTING)
     * @throws FileNotFoundException
     */
    private void readInput(boolean stdIO, String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc;
        if (stdIO) {
            sc = new Scanner(System.in);
        } else {
            sc = new Scanner(file).useLocale(Locale.US);
        }

        n = sc.nextInt();
        d = sc.nextInt();
        epsilon = sc.nextInt();
        minPts = sc.nextInt();

        points = new ClusterPoint[n];
        // Read n points
        for (int i = 0; i < n; i++) {
            double[] coords = new double[d];

            // Read d coords
            for (int j = 0; j < d; j++) {
                coords[j] = sc.nextInt();
            }

            // Add point to array
            ClusterPoint p = new ClusterPoint(d, coords);
            points[i] = p;
        }
    }

    private void printOutput(int c) {
        // Assignment number
        System.out.format("%d\n", 1);
        System.out.format("%d %d %d\n", n, d, c);
        for (int i = 0; i < n; i++) {
            ClusterPoint point = points[i];
            System.out.print(point);
        }
    }

    private void writeOutput(int c, String path_out) throws UnsupportedEncodingException, IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path_out), "utf-8"))) {
            writer.write(String.format("%d\n", 1));
            writer.write(String.format("%d %d %d\n", n, d, c));
            for (int i = 0; i < n; i++) {
                ClusterPoint point = points[i];
                writer.write(point.toString());
            }
        }
    }
}
