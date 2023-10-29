package pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "LinksToEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LinksToEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger linksId;

    @Column( length = 8192)
    private List<String> links;

    @ManyToOne(optional = false)
    @JoinTable(
        name="NasaEntity_LinksToEntity",
        joinColumns = @JoinColumn( name="linksId"),
        inverseJoinColumns = @JoinColumn( name="id")
    )
    private NasaEntity nasaEntityLinks;

    public LinksToEntity(List<String> links) {
        this.links = links;
    }
}

