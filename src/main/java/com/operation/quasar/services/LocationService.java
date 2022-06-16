package com.operation.quasar.services;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    public double[] getLocation(double[][] positions, double[] distances) {
        TrilaterationFunction function = new TrilaterationFunction(positions, distances);
        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(function, new LevenbergMarquardtOptimizer());

        return solver.solve().getPoint().toArray();
    }
}
