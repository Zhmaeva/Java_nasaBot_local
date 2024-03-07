package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Nasa {
    String date;
    String explanation;
    String hdurl;
    String media_type;
    String service_version;
    String title;
    String url;

    public Nasa(@JsonProperty("date") String date,
                @JsonProperty("explanation") String explanation,
                @JsonProperty("hdurl") String hdurl,
                @JsonProperty("media_type") String media_type,
                @JsonProperty("service_version") String service_version,
                @JsonProperty("title") String title,
                @JsonProperty("url") String url) {
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

}
