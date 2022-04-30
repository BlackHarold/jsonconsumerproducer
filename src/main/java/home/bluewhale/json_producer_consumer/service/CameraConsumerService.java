package home.bluewhale.json_producer_consumer.service;

import home.bluewhale.json_producer_consumer.model.Camera;
import home.bluewhale.json_producer_consumer.model.SourceData;
import home.bluewhale.json_producer_consumer.model.TokenData;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public interface CameraConsumerService {
    Optional aggregateCamerasResponse(Camera camera) throws IOException;

    AsyncResult<SourceData> getSourceData(URL url);

    AsyncResult<TokenData> getTokenData(URL url);

    List<Camera> getRawListOfCameras(URL url);
}
