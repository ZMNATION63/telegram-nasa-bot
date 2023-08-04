package pro.careeerdevelopment.telegramnasabot.nasa.model.internal.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "NasaEntity")
@Getter
@Setter
@ToString
public class NasaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "href", length = 1024)
    private String href;

    @Column(name = "description", length = 4096)
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "nasaId")
    private String nasaId;

    @Column(name = "dateCreated")
    private String dateCreated;

    @Column(name = "mediaType")
    private String mediaType;

    @Column(name = "center")
    private String center;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name="NasaEntity_KeyWordsEntity",
        joinColumns = @JoinColumn( name="id"),
        inverseJoinColumns = @JoinColumn( name="kyeWords_id")
    )
    private Set<KeyWordsEntity> nasaEntityKey;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name="NasaEntity_LinksToEntity",
        joinColumns = @JoinColumn( name="id"),
        inverseJoinColumns = @JoinColumn( name="linksId")
    )
    private List<LinksToEntity> nasaEntityLinks;

    @Column(name = "numberOfPlays")
    private BigInteger numberOfPlays;
}
