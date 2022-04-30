package home.bluewhale.json_producer_consumer.controller;

import home.bluewhale.json_producer_consumer.model.Camera;
import home.bluewhale.json_producer_consumer.model.CameraAggregation;
import home.bluewhale.json_producer_consumer.service.CameraConsumerService;
import io.katharsis.spring.boot.v3.KatharsisConfigV3;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Import(KatharsisConfigV3.class)
public class RequestController {

    final CameraConsumerService service;

    @Value("${cameras.address.url}")
    private String httpAddress;

    public RequestController(CameraConsumerService service) {
        this.service = service;
    }

    @ApiOperation("Retrieving cameras API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 500, message = "Service unavailable at the moment")
    })
    @GetMapping(value = "/retrieve", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CameraAggregation> retrieveRequest() throws IOException {

        List<CameraAggregation> listOfAggregatedCameras = new ArrayList<>();

        List<Camera> listOfCameras = service.getRawListOfCameras(new URL(httpAddress));

        if (listOfCameras != null) {
            for (Camera c : listOfCameras) {
                Optional<CameraAggregation> optional = service.aggregateCamerasResponse(c);
                listOfAggregatedCameras.add(optional.get());
            }
        }

        return listOfAggregatedCameras;
    }
}
