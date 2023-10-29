package pro.careeerdevelopment.telegramnasabot.nasa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pro.careeerdevelopment.telegramnasabot.nasa.dao.KeyWordsEntityDao;
import pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities.KeyWordsEntity;

import java.math.BigInteger;
import java.util.List;

@Component
public class KeyWordsEntityService {
    private final KeyWordsEntityDao keyWordsEntityDao;

    @Autowired
    public KeyWordsEntityService(KeyWordsEntityDao keyWordsEntityDao) {
        this.keyWordsEntityDao = keyWordsEntityDao;
    }

//    @Transactional
//    public List<KeyWordsEntity> findAllByNasaEntityId(BigInteger id) {
//        return keyWordsEntityDao.findAllByNasaEntityId(id);
//    }


    @Transactional
    public List<KeyWordsEntity> findAll() {
        return keyWordsEntityDao.findAll();
    }

    @Transactional
    public <S extends KeyWordsEntity> S save(S entity) {
        return keyWordsEntityDao.save(entity);
    }

    @Transactional
    public KeyWordsEntity findById(BigInteger id) {
        return keyWordsEntityDao.findById(id).isPresent() ? keyWordsEntityDao.findById(id).get() : null;
    }
}
