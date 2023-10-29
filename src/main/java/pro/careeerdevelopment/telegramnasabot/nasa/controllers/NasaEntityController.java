package pro.careeerdevelopment.telegramnasabot.nasa.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities.KeyWordsEntity;
import pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities.LinksToEntity;
import pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities.NasaEntity;
import pro.careeerdevelopment.telegramnasabot.nasa.model.nasagov.entities.Item;
import pro.careeerdevelopment.telegramnasabot.nasa.model.nasagov.entities.LinksItem;
import pro.careeerdevelopment.telegramnasabot.nasa.model.nasagov.entities.Root;
import pro.careeerdevelopment.telegramnasabot.nasa.services.ItemListService;
import pro.careeerdevelopment.telegramnasabot.nasa.services.NasaEntityService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NasaEntityController {

    private final NasaEntityService nasaEntityService;

    @Autowired
    public NasaEntityController(NasaEntityService nasaEntityDao) {
        this.nasaEntityService = nasaEntityDao;
    }

    public NasaEntity getEntityById(BigInteger id) {
        return nasaEntityService.getNasaEntityById(id);
    }

    public void uploadNasaEntities(String resultStr) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Root collectionNasa = objectMapper.readValue(resultStr.getBytes(), Root.class);
        for (Item item : collectionNasa.getCollection().getItems()) {
            NasaEntity nasaEntityTemp = new NasaEntity();
            if (StringUtils.isNotBlank(item.getHref())) {
                nasaEntityTemp.setHref(item.getHref().replace(" ", "%20"));
            }

            nasaEntityTemp.setDescription(checkIfNull(item.getData().get(0).getDescription()));
            nasaEntityTemp.setCenter(checkIfNull(item.getData().get(0).getCenter()));
            nasaEntityTemp.setTitle(checkIfNull(item.getData().get(0).getTitle()));
            nasaEntityTemp.setNasaId(checkIfNull(item.getData().get(0).getNasa_id()));
            nasaEntityTemp.setDateCreated(checkIfNull(item.getData().get(0).getDate_created()));
            nasaEntityTemp.setMediaType(checkIfNull(item.getData().get(0).getMedia_type()));

            if (item.getData().get(0).getKeywords() != null) {
                Set<KeyWordsEntity> keyWordsEntityList1 = new HashSet<>();
                for (String s : item.getData().get(0).getKeywords()) {
                    KeyWordsEntity keyWordsEntity = new KeyWordsEntity();
                    keyWordsEntity.setKeyWords(s);
                    keyWordsEntityList1.add(keyWordsEntity);
                    keyWordsEntity.setNasaEntityKey(nasaEntityTemp);
                }
                nasaEntityTemp.setNasaEntityKey(keyWordsEntityList1);
            }

            nasaEntityTemp.setNumberOfPlays(BigInteger.valueOf(0L));
            String urlFinal = item.getHref().replace(" ", "%20");
            List<LinksItem> linkItemHrefList = ItemListService.getContentLinks(urlFinal);
            List<String> someLinks = linkItemHrefList.stream()
                .map(LinksItem::getHref)
                .map(s -> s.replace(" ", "%20"))
                .collect(Collectors.toList());

            List<LinksToEntity> linksToEntityListString =
                linkItemHrefList.stream()
                    .map(l -> new LinksToEntity(someLinks))
                    .collect(Collectors.toList());

            nasaEntityTemp.setNasaEntityLinks(linksToEntityListString);
            nasaEntityService.save(nasaEntityTemp);
        }
    }

    private String checkIfNull(String param) {
        return StringUtils.isNotBlank(param) ? param : "BLANK";
    }
}
