package pro.careeerdevelopment.telegramnasabot.telegram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.careeerdevelopment.telegramnasabot.nasa.controllers.NasaEntityController;
import pro.careeerdevelopment.telegramnasabot.nasa.services.parsing.HttpClientExecutor;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

@Component
public class NasaService {

    private final NasaEntityController nasaEntityController;

    @Autowired
    public NasaService(NasaEntityController nasaEntityController) {
        this.nasaEntityController = nasaEntityController;
    }

    public void updateDb() throws IOException {
        Collection<String> hrefCollection = new HashSet<>();
        hrefCollection.add("https://images-api.nasa.gov/search?q=mars&description=mars%20landing&media_type=image&year_start=1965&year_end=2023&page_size=10000");
        hrefCollection.add("https://images-api.nasa.gov/search?q=apollo&description=moon%20landing&media_type=image&year_start=1965&year_end=2023&page_size=10000");
        for (String href :hrefCollection) {
            String resultStr = HttpClientExecutor.getHttpResponse(href);
            nasaEntityController.uploadNasaEntities(resultStr);
        }
    }
}
