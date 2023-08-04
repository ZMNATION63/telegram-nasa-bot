package pro.careeerdevelopment.telegramnasabot.nasa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities.KeyWordsEntity;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface KeyWordsEntityDao extends JpaRepository<KeyWordsEntity, BigInteger> {
    @Override
    List<KeyWordsEntity> findAll();

    @Override
    <S extends KeyWordsEntity> S save(S entity);

    @Override
    Optional<KeyWordsEntity> findById(BigInteger id);


}
