package pro.careeerdevelopment.telegramnasabot.nasa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pro.careeerdevelopment.telegramnasabot.nasa.dao.NasaEntityDao;
import pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities.NasaEntity;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Component
public class NasaEntityService{

    private NasaEntityDao nasaEntityDao;

    @Autowired
    public NasaEntityService(NasaEntityDao nasaEntityDao) {
        this.nasaEntityDao = nasaEntityDao;
    }

    @Transactional
    public NasaEntity getNasaEntityById(BigInteger id) {
        return nasaEntityDao.findById(id).isPresent() ? nasaEntityDao.findById(id).get() : null;
    }

    @Transactional
    public void save(NasaEntity nasaEntityTemp) {
        nasaEntityDao.save(nasaEntityTemp);
    }

    @Transactional
    public List<NasaEntity> findAll() {
        return nasaEntityDao.findAll();
    };

    @Transactional
    Optional<NasaEntity> findById(BigInteger bigInteger) {
        return nasaEntityDao.findById(bigInteger);
    }
}
