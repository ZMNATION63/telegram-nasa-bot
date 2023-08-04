package pro.careeerdevelopment.telegramnasabot.nasa.model.nasagov.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class CollectionNasa {
    private Double version;
    private String href;
    private List<Item> items;
    private Metadata metadata;
}
