package br.com.ciceroednilson.feira.repository;

import br.com.ciceroednilson.feira.repository.entity.StreetFairEntity;
import br.com.ciceroednilson.feira.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class StreetFairRepositoryTest {

    @InjectMocks
    private StreetFairRepository streetFairRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private static final String JSON_STREET_FAIR_ENTITY = "street_fair_entity.json";
    private static final String JSON_LIST_STREET_FAIR_ENTITY = "list_street_fair_entity.json";

    @Test
    public void updateSuccessfully() throws IOException {
        when(jdbcTemplate.update(any(String.class), any(Object.class))).thenReturn(1);
        final StreetFairEntity entity = Util.loadFile(JSON_STREET_FAIR_ENTITY, StreetFairEntity.class);
        streetFairRepository.update(entity, entity.getId());
        verify(jdbcTemplate, times(1)).update(any(String.class), any(Object.class));
    }

    @Test
    public void saveSuccessfully() throws IOException {
        when(jdbcTemplate.update(any(String.class), any(Object.class))).thenReturn(1);
        final StreetFairEntity entity = Util.loadFile(JSON_STREET_FAIR_ENTITY, StreetFairEntity.class);
        entity.setId(null);
        streetFairRepository.save(entity);
        verify(jdbcTemplate, times(1)).update(any(String.class), any(Object.class));
    }

    @Test
    public void deleteSuccessfully() throws IOException {
        when(jdbcTemplate.update(any(String.class), any(Object.class))).thenReturn(1);
        streetFairRepository.delete(1);
        verify(jdbcTemplate, times(1)).update(any(String.class), any(Object.class));
    }

    @Test
    public void selectByIdSuccessfully() throws IOException {
        final List<StreetFairEntity> list = Util.loadFile(JSON_LIST_STREET_FAIR_ENTITY, new TypeReference<>() {
        });
        when(jdbcTemplate.query(any(String.class), any(BeanPropertyRowMapper.class), any())).thenReturn(list);
        final Integer id = streetFairRepository.selectById(11);
        assertEquals(id, list.get(0).getId());
    }

    @Test
    public void selectByIdListNullSuccessfully() throws IOException {
        when(jdbcTemplate.query(any(String.class), any(BeanPropertyRowMapper.class), any())).thenReturn(null);
        final Integer id = streetFairRepository.selectById(11);
        assertNull(id);
    }

    @Test
    public void selectByIdListEmptySuccessfully() throws IOException {
        when(jdbcTemplate.query(any(String.class), any(BeanPropertyRowMapper.class), any())).thenReturn(new ArrayList<>());
        final Integer id = streetFairRepository.selectById(11);
        assertNull(id);
    }

    @Test
    public void selectSuccessfully() throws IOException {
        final String district = "SAO MIGUEL";
        final String regionFive = "Leste";
        final String fairName = "PARQUE SONIA";
        final String neighborhood = "VL PROGRESSO";
        when(jdbcTemplate.query(any(String.class), any(BeanPropertyRowMapper.class), any())).thenReturn(new ArrayList<>());
        final List<StreetFairEntity> list = streetFairRepository.select(district,regionFive, fairName, neighborhood);
        assertEquals(list.size(), 0);
    }
}
