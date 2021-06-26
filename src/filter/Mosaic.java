package filter;

import imagemodel.IImage;
import imagemodel.IPixel;
import imagemodel.Pixel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Mosaic implements IModifier{

  private final int seeds;
  private final List<IPixel> centroids;
  private final Map<IPixel, List<IPixel>> centroidToPixels;
  private final Map<IPixel, List<Double>> centroidToColor;

  public Mosaic(int seeds) {
    this.seeds = seeds;
    this.centroids = new ArrayList<>();
    this.centroidToPixels = new HashMap<>();
    this.centroidToColor = new HashMap<>();
  }

  @Override
  public String toString() {
    return "mosaic";
  }

  @Override
  public List<IPixel> modify(IImage image) {
    /*
    LOOP: generate random seeds based on this.seeds
    LOOP: go through every pixel => find closest seed
          associate pixel with seed = cluster
    LOOP: find average color for each cluster
    LOOP: go through every pixel => set color to average color for cluster
     */

    List<IPixel> pixels = image.getPixels();
    List<IPixel> newPixels = new ArrayList<>();
    int searchDist =
        Math.min(image.getProps().get(0), image.getProps().get(1)) / (int)Math.sqrt(this.seeds);

    System.out.println("Search dist:" + searchDist);
    System.out.println("Sowing...");
    this.sowSeeds(image.getPixels());
    System.out.println("Clustering...");
    this.cluster(pixels, searchDist);
    System.out.println("Averaging...");
    this.getAverage();

    System.out.println("Generating...");
    for (Map.Entry<IPixel, List<IPixel>> centroid: centroidToPixels.entrySet()) {
      for (IPixel pixel: centroid.getValue()) {
        newPixels.add(new Pixel(pixel.getCoords(), this.centroidToColor.get(centroid.getKey())));
      }
    }

    System.out.println("Sorting...");
    newPixels.sort((o1, o2) -> {
      if (o1.getCoords().get(1).equals(o2.getCoords().get(1))) {
        return o1.getCoords().get(0) - o2.getCoords().get(0);
      }
      return o1.getCoords().get(1) - o2.getCoords().get(1);
    });

    return newPixels;
  }

  private void getAverage() {
    for (Map.Entry<IPixel, List<IPixel>> centroid: centroidToPixels.entrySet()) {
      List<Double> finalRGB = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0));
      for (IPixel pixel: centroid.getValue()) {
        finalRGB.set(0, finalRGB.get(0) + pixel.getColor().get(0));
        finalRGB.set(1, finalRGB.get(1) + pixel.getColor().get(1));
        finalRGB.set(2, finalRGB.get(2) + pixel.getColor().get(2));
      }
      for (int i = 0; i < finalRGB.size(); i++) {
        finalRGB.set(i, finalRGB.get(i) / centroid.getValue().size());
      }
      this.centroidToColor.put(centroid.getKey(), finalRGB);
    }
  }

  private void cluster(List<IPixel> pixels, int searchDist) {
    for (IPixel pixel: pixels) {
      /*
      set arbitrary distance
      try and get centroid within that distance
      if 1 --> CLOSEST!
      if > 1 --> Pythagorean distance
      if 0 --> expand distance
       */

      int incr = searchDist * 2;
      int minX = pixel.getCoords().get(0) - searchDist;
      int maxX = pixel.getCoords().get(0) + searchDist;
      int minY = pixel.getCoords().get(1) - searchDist;
      int maxY = pixel.getCoords().get(1) + searchDist;

      IPixel closest = this.getClosestCentroid(this.clamp(minX, maxX, minY, maxY), pixel);

      while (closest == null) {
        closest = this.getClosestCentroid(this.clamp(minX - incr, maxX + incr,
            minY - incr,
            maxY + incr),
            pixel);
      }

      centroidToPixels.get(closest).add(pixel);
    }
  }

  private IPixel getClosestCentroid(List<IPixel> centroids, IPixel pixel) {
    if (centroids.size() == 1) {
      return centroids.get(0);
    }
    if (centroids.size() == 0) {
      return null;
    }
    IPixel closestCentroid = null;
    double distance = -1.0;

    for (IPixel centroid : centroids) {
      if (distance == -1) {
        distance = this.findDistance(pixel.getCoords(), centroid.getCoords());
        closestCentroid = centroid;
      } else {
        double tentativeDistance = this.findDistance(pixel.getCoords(), centroid.getCoords());
        if (tentativeDistance < distance) {
          distance = tentativeDistance;
          closestCentroid = centroid;
        }
      }
    }

    return closestCentroid;
  }

  private List<IPixel> clamp(int minX, int maxX, int minY, int maxY) {
    return this.centroids.stream().filter(pixel -> pixel.getCoords().get(0) > minX
        && pixel.getCoords().get(0) < maxX
        && pixel.getCoords().get(1) > minY
        && pixel.getCoords().get(1) < maxY).collect(Collectors.toList());
  }

  private double findDistance(List<Integer> CS1, List<Integer> CS2) {
    return Math.hypot(Math.abs(CS2.get(0) - CS1.get(0)), Math.abs(CS2.get(1) - CS1.get(1)));
  }

  private void sowSeeds(List<IPixel> pixels) {
    Collections.shuffle(pixels);

    for (int i = 0; i < this.seeds; i++) {
      IPixel centroid = pixels.get(i);
      this.centroids.add(centroid);
      this.centroidToPixels.put(centroid, new ArrayList<>());
    }

  }
}
