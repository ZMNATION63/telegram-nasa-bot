package pro.careeerdevelopment.telegramnasabot.telegram.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities.NasaEntity;
import pro.careeerdevelopment.telegramnasabot.nasa.services.NasaEntityService;

import java.util.List;
import java.util.Random;

@Component
public class NasaMessageCreator {

    private NasaEntityService nasaEntityService;

    @Autowired
    public NasaMessageCreator(NasaEntityService nasaEntityService) {
        this.nasaEntityService = nasaEntityService;
    }

    public String createMessage() {
        List<NasaEntity> nasaEntityList = nasaEntityService.findAll();
        Random random = new Random();
        int index = random.nextInt(nasaEntityList.size());
        NasaEntity nasaEntity = nasaEntityList.get(index);
        String s = "||Field||Value||\n" + "|*Declarative decomposition*|Enable|\n" + "|*Existence condition*|TRUE|\n"
            + "|*Modify condition*|OrderAim in (Modify)|";
        return nasaEntity.getTitle()
            + "\nNasa ID " +  nasaEntity.getNasaId()
            + "\nDate creation " + nasaEntity.getDateCreated()
            + "\n"
            + "\nDescryption " + nasaEntity.getDescription()
            + "\n"
            + "\n " + nasaEntity.getNasaEntityLinks().get(0).getLinks().get(0).replace("\"", "");
    }
}
