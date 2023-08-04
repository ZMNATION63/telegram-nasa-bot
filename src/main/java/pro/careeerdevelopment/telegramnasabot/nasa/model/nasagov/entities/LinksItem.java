package pro.careeerdevelopment.telegramnasabot.nasa.model.nasagov.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties
public class LinksItem {
    private String href;
    private String rel;
    private String render;

    public LinksItem(String href) {
        this.href = href;
    }
}
