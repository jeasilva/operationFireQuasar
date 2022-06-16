package com.operation.quasar.util;

import com.operation.quasar.entities.Position;
import com.operation.quasar.entities.Satellite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SatelliteUtils {

    public static double[] getDistances(List<Satellite> satellites) {

        double[] distances = new double[satellites.size()];
        for (int i = 0; i < satellites.size(); i++) {
            distances[i] = satellites.get(i).getDistance();
        }
        return distances;
    }

    public static double[][] getPositions(List<Satellite> satellites) {
        double[][] positions = new double[satellites.size()][];
        String[] points;
        for (int i = 0; i < satellites.size(); i++) {
            if (satellites.get(i).getPosition() != null) {
                points = satellites.get(i).getPosition().toString().split(",");
                positions[i] = Arrays.stream(points).map(Double::valueOf).mapToDouble(Double::doubleValue).toArray();
            }
        }
        return positions;
    }

    public static void setPositions(List<Satellite> satellites, double[][] pointsList) {
        Position position;
        for (int i = 0; i < pointsList.length; i++) {
            position = new Position(pointsList[i]);
            satellites.get(i).setPosition(position);
        }
    }

    public static List<List<String>> getMessages(List<Satellite> satellites) {
        List<List<String>> messages = new ArrayList<>();
        for (Satellite satellite : satellites) {
            messages.add(satellite.getMessage());
        }
        return messages;
    }
}
