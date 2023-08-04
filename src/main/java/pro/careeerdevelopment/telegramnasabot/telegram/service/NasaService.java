package pro.careeerdevelopment.telegramnasabot.telegram.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.careeerdevelopment.telegramnasabot.nasa.controllers.NasaEntityController;
import pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities.NasaEntity;
import pro.careeerdevelopment.telegramnasabot.nasa.model.nasagov.entities.CollectionNasa;
import pro.careeerdevelopment.telegramnasabot.nasa.model.nasagov.entities.Root;
import pro.careeerdevelopment.telegramnasabot.nasa.services.parsing.HttpClientExecutor;

import java.io.IOException;
import java.math.BigInteger;

@Component
public class NasaService {

    private NasaEntityController nasaEntityController;

    @Autowired
    public NasaService(NasaEntityController nasaEntityController) {
        this.nasaEntityController = nasaEntityController;
    }

     public NasaEntity getNasaEntity(long id) {
        return nasaEntityController.getEntityById(BigInteger.valueOf(id));
    }

    public String getAllDate(String message, CollectionNasa model) throws IOException {
        String resultStr = HttpClientExecutor.getHttpResponse("https://images-api.nasa.gov/search?q=apollo&description=moon%20landing&media_type=video&year_start=1965&year_end=2023&page_size=10000");
        ObjectMapper objectMapper = new ObjectMapper();
        Root collectionNasa = objectMapper.readValue(resultStr.getBytes(), Root.class);
        return "";
    }

    public String updateDb() throws IOException {
//        String resultStr = HttpClientExecutor.getHttpResponse("https://images-api.nasa.gov/search?q=apollo&description=moon%20landing&media_type=video&year_start=1965&year_end=2023&page_size=10000");
        String resultStr = HttpClientExecutor.getHttpResponse("https://images-api.nasa.gov/search?q=apollo&description=moon%20landing&media_type=image&year_start=1965&year_end=2023&page_size=10000");
//        System.out.println(resultStr);
        ObjectMapper objectMapper = new ObjectMapper();
        Root collectionNasa = objectMapper.readValue(resultStr.getBytes(), Root.class);

        nasaEntityController.uploadNasaEntities(resultStr);

        return "";
    }
}
