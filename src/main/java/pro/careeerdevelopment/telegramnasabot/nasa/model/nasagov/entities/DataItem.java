package pro.careeerdevelopment.telegramnasabot.nasa.model.nasagov.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class DataItem {
    private String description;
    private String description_508;
    private List<String> album;
    private String title;
    private String photographer;
    private String location;
    private String nasa_id;
    private String date_created;
    private List<String> keywords;
    private String media_type;
    private String center;
    private String secondary_creator;
}
