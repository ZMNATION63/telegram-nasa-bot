package pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "NumberOfPlays")
@ToString
@Data
@EqualsAndHashCode
public class NumberOfPlaysEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger numberOfPlaysId;
	private BigInteger numberOfPlays;
}
