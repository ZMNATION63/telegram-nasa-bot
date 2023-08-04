package pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "KeyWordsEntity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class KeyWordsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger kyeWords_id;

    private String keywords;

    @ManyToOne(optional = false)
    @JoinTable(
        name="NasaEntity_KeyWordsEntity",
        joinColumns = @jakarta.persistence.JoinColumn( name="kyeWords_id"),
        inverseJoinColumns = @JoinColumn( name="id")
    )
    private NasaEntity nasaEntityKey;
}


