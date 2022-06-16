package com.operation.quasar.services;

import com.operation.quasar.configuration.SatelliteProperties;
import com.operation.quasar.entities.Position;
import com.operation.quasar.entities.Satellite;
import com.operation.quasar.entities.TopSecretRequest;
import com.operation.quasar.entities.TopSecretResponse;
import com.operation.quasar.exceptions.LocationException;
import com.operation.quasar.exceptions.MessageException;
import com.operation.quasar.util.ErrorCodes;
import com.operation.quasar.util.SatelliteUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommunicationService {

    private final MessageService messageService;

    private final LocationService locationService;

    //private final SatelliteProperties properties;

    @Value("${satellites.number}")
    private Integer satelliteNumbers;

    @Value("${satellites.positions}")
    private String[] positions;

    public TopSecretResponse generateMessage(TopSecretRequest request) throws MessageException, LocationException {
        if (request.getSatellite().size() <2){
            throw new MessageException(ErrorCodes.MESSAGE_SIZE_INCORRECT);
        }
        String message = messageService.getMessage(SatelliteUtils.getMessages(request.getSatellite()));
        upLoadPositions(request.getSatellite());
        if (SatelliteUtils.getPositions(request.getSatellite()).length<2 ||
                SatelliteUtils.getDistances(request.getSatellite()).length <2){
            throw new LocationException(ErrorCodes.MESSAGE_LOCATION_ERROR);
        }

        double[] points = locationService.getLocation(SatelliteUtils.getPositions(request.getSatellite()),
                SatelliteUtils.getDistances(request.getSatellite()));
        Position position = new Position(points);
        return TopSecretResponse.builder()
                .position(position)
                .message(message)
                .build();
    }

    private void upLoadPositions(List<Satellite> satellites) {
        if (SatelliteUtils.getPositions(satellites)[0] == null) {
            //int numberSatellite = properties.getNumber();
            //String[] positions = properties.getPositions();
            double[][] pointsLists = new double[satelliteNumbers][];
            String[] satellitePost;
            for (int i = 0; i < satellites.size(); i++) {
                satellitePost = positions[i].split(":");
                pointsLists[i] = Arrays.stream(satellitePost).map(Double::valueOf)
                        .mapToDouble(Double::doubleValue).toArray();
            }
            SatelliteUtils.setPositions(satellites, pointsLists);
        }
    }
}
