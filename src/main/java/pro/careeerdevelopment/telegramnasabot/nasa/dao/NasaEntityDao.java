package pro.careeerdevelopment.telegramnasabot.nasa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities.NasaEntity;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface NasaEntityDao extends JpaRepository<NasaEntity, BigInteger> {
    @Override
    List<NasaEntity> findAll();

    @Override
    <S extends NasaEntity> S save(S entity);

    @Override
    Optional<NasaEntity> findById(BigInteger bigInteger);
}
