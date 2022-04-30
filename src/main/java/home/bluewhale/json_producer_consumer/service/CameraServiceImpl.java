package home.bluewhale.json_producer_consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import home.bluewhale.json_producer_consumer.model.Camera;
import home.bluewhale.json_producer_consumer.model.CameraAggregation;
import home.bluewhale.json_producer_consumer.model.SourceData;
import home.bluewhale.json_producer_consumer.model.TokenData;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class CameraServiceImpl implements CameraConsumerService {

    private final ObjectMapper objectMapper;

    public CameraServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional aggregateCamerasResponse(Camera camera) {

        SourceData sourceData = null;
        TokenData tokenData = null;

        AsyncResult<SourceData> sourceDataAsyncResult = getSourceData(
                camera.getSourceDataUrl());
        AsyncResult<TokenData> tokenDataAsyncResult = getTokenData(
                camera.getTokenDataUrl());

        try {
            sourceData = sourceDataAsyncResult.get();
            tokenData = tokenDataAsyncResult.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return Optional.of(new CameraAggregation(camera.getId(), sourceData, tokenData));
    }

    @Async("threadPoolTaskExecutor")
    public AsyncResult<SourceData> getSourceData(URL url) {
        try {
            return new AsyncResult<>(objectMapper.readValue(url, SourceData.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new AsyncRequestTimeoutException();
    }

    @Async("threadPoolTaskExecutor")
    public AsyncResult<TokenData> getTokenData(URL url) {

        try {
            return new AsyncResult<>(objectMapper.readValue(url, TokenData.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new AsyncRequestTimeoutException();
    }


    public List<Camera> getRawListOfCameras(URL url) {
        CollectionType listType =
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Camera.class);
        try {
            return objectMapper.readValue(url, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
