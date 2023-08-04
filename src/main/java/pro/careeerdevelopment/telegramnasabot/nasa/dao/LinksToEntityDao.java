package pro.careeerdevelopment.telegramnasabot.nasa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities.LinksToEntity;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface LinksToEntityDao extends JpaRepository<LinksToEntity, BigInteger> {
    @Override
    List<LinksToEntity> findAll();

    @Override
    <S extends LinksToEntity> S save(S entity);

    @Override
    Optional<LinksToEntity> findById(BigInteger bigInteger);
}
