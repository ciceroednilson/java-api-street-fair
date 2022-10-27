package br.com.ciceroednilson.feira.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class Util {

    public static <T> T loadFile(final String fileName,  Class<T> valueType) throws IOException {
        final String path = "src/test/resources/".concat(fileName);
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Paths.get(path).toFile(), valueType);
    }

    public static <T> T loadFile(final String fileName,  TypeReference<T> valueTypeRef) throws IOException {
        final String path = "src/test/resources/".concat(fileName);
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Paths.get(path).toFile(), valueTypeRef);
    }

    public static String loadFile(final String fileName) throws IOException {
        final String path = "src/test/resources/".concat(fileName);;
        final File file = new File(path);
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}
