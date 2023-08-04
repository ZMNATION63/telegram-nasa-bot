package pro.careeerdevelopment.telegramnasabot.nasa.model.nasagov.links;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;


//    TODO
//     updated when
//     updated who
//     updated days ago
//     updated date
//     add mechanism for add new links
//     add mechanism for update information from the link
//     add

@Entity
@Table(name = "LinkEntity")
@Getter
@Setter
@ToString
public class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "url")
    private String url;

}
