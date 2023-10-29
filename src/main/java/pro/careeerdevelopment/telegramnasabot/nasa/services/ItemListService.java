package pro.careeerdevelopment.telegramnasabot.nasa.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pro.careeerdevelopment.telegramnasabot.nasa.model.nasagov.entities.LinksItem;
import pro.careeerdevelopment.telegramnasabot.nasa.services.parsing.HttpClientExecutor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemListService {
    @Transactional
    public static List<LinksItem> getContentLinks(String urlStr) throws IOException {

        String resultString = HttpClientExecutor.getHttpResponse(urlStr);
        resultString = StringUtils.substring(resultString, 1, resultString.length() - 1);
        List<String> resultList = Arrays.asList(resultString.split(", "));

        List<LinksItem> linksItemList = new ArrayList<>();
        resultList = resultList.stream()
            .peek(s -> StringUtils.substring(s, 1,s.length() - 1))
            .filter(s -> s.matches("(.*)~medium.jpg(.*)"))
            .peek(s -> s = s.replace(" ", "%20"))
            .peek(s -> linksItemList.add(new LinksItem(s)))
            .collect(Collectors.toList());
        return linksItemList;
    }

    public static List<String> getContentLinksStr(String urlStr) throws IOException {
        String resultString = HttpClientExecutor.getHttpResponse(urlStr);
        resultString = StringUtils.substring(resultString, 1, resultString.length() - 1);
        List<String> resultList = Arrays.asList(resultString.split(", "));

        List<String> linksToEntityStr = new ArrayList<>();
        resultList = resultList.stream()
            .peek(s -> StringUtils.substring(s, 1,s.length() - 1))
            .filter(s -> s.matches("(.*)~medium.jpg(.*)"))
            .peek(s -> s = s.replace(" ", "%20"))
//            .peek(s -> s)
            .collect(Collectors.toList());
        return resultList;
    }
}
