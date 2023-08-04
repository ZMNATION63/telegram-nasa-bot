package pro.careeerdevelopment.telegramnasabot.nasa.model.nasagov.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@EqualsAndHashCode
@ToString
public class Item {
    private String href;
    private List<DataItem> data;
    private List<LinksItem> links;
}
