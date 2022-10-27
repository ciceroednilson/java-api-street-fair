package br.com.ciceroednilson.feira.repository.query;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.io.IOException;

@Getter
@AllArgsConstructor
public class Query {

    private String selectById;
    private String select;
    private String insert;
    private String update;
    private String delete;

    public static Query builder() throws IOException {
        final String path = "src/main/resources/query/sql.yaml";
        final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules();
        return objectMapper.readValue(new File(path), Query.class);
    }
}
