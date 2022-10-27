package br.com.ciceroednilson.feira.service.impl;

import br.com.ciceroednilson.feira.http.ApplicationException;
import br.com.ciceroednilson.feira.repository.entity.StreetFairEntity;
import br.com.ciceroednilson.feira.util.Util;
import br.com.ciceroednilson.feira.domain.StreetFairDomain;
import br.com.ciceroednilson.feira.repository.StreetFairRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class StreetFairServiceImplTest {

    @InjectMocks
    private StreetFairServiceImpl streetFairServiceImpl;

    @Mock
    private StreetFairRepository streetFairRepository;

    private static final String JSON_STREET_FAIR_DOMAIN = "street_fair_domain.json";
    private static final String JSON_LIST_STREET_FAIR_ENTITY = "list_street_fair_entity.json";
    private static final String ERROR_TO_SAVE = "Error to save new register!";
    private static final String ERROR_TO_DELETE = "Error to delete the register!";
    private static final String ERROR_NO_EXISTS = "Register %s not found!";
    private static final String ERROR_TO_UPDATE = "Error to update the register!";

    private static final String ERROR_TO_FIND = "Error to find the registers!";

    @Test
    public void registerSuccessfully() throws IOException {
        doNothing().when(streetFairRepository).save(any());
        final StreetFairDomain streetFairDomain = Util.loadFile(JSON_STREET_FAIR_DOMAIN, StreetFairDomain.class);
        streetFairServiceImpl.register(streetFairDomain);
        verify(streetFairRepository, times(1)).save(any());
    }

    @Test
    public void registerWithErrorOfIOException() throws IOException {
        doThrow(IOException.class).when(streetFairRepository).save(any());
        final StreetFairDomain streetFairDomain = Util.loadFile(JSON_STREET_FAIR_DOMAIN, StreetFairDomain.class);
        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            streetFairServiceImpl.register(streetFairDomain);
        });
        assertTrue(exception.getMessage().contains(ERROR_TO_SAVE));
    }

    @Test
    public void registerWithErrorOfDataAccessException() throws IOException {
        doThrow(new DataAccessException(ERROR_TO_SAVE) {
        }).when(streetFairRepository).save(any());
        final StreetFairDomain streetFairDomain = Util.loadFile(JSON_STREET_FAIR_DOMAIN, StreetFairDomain.class);
        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            streetFairServiceImpl.register(streetFairDomain);
        });
        assertTrue(exception.getMessage().contains(ERROR_TO_SAVE));
    }

    @Test
    public void deleteSuccessfully() throws IOException {
        final int id = 1;
        when(streetFairRepository.selectById(id)).thenReturn(id);
        doNothing().when(streetFairRepository).delete(id);
        streetFairServiceImpl.delete(id);
        verify(streetFairRepository, times(1)).delete(1);
    }

    @Test
    public void deleteWithErrorOfIOException() throws IOException {
        final int id = 1;
        when(streetFairRepository.selectById(id)).thenReturn(id);
        doThrow(IOException.class).when(streetFairRepository).delete(id);
        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            streetFairServiceImpl.delete(id);
        });
        assertTrue(exception.getMessage().contains(ERROR_TO_DELETE));
    }

    @Test
    public void deleteWithErrorOfDataAccessException() throws IOException {
        final int id = 1;
        when(streetFairRepository.selectById(id)).thenReturn(id);
        doThrow(new DataAccessException(ERROR_TO_DELETE) {
        }).when(streetFairRepository).delete(id);
        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            streetFairServiceImpl.delete(id);
        });
        assertTrue(exception.getMessage().contains(ERROR_TO_DELETE));
    }

    @Test
    public void deleteWithErrorIdNotFound() throws IOException {
        final int id = 1;
        when(streetFairRepository.selectById(id)).thenReturn(null);
        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            streetFairServiceImpl.delete(id);
        });
        assertTrue(exception.getMessage().contains(String.format(ERROR_NO_EXISTS, id)));
    }

    @Test
    public void updateSuccessfully() throws IOException {
        final int id = 1;
        doNothing().when(streetFairRepository).update(any(), any());
        when(streetFairRepository.selectById(id)).thenReturn(id);
        final StreetFairDomain streetFairDomain = Util.loadFile(JSON_STREET_FAIR_DOMAIN, StreetFairDomain.class);
        streetFairServiceImpl.update(streetFairDomain, id);
        verify(streetFairRepository, times(1)).update(any(), any());
    }

    @Test
    public void updateWithErrorOfIOException() throws IOException {
        final int id = 1;
        doThrow(IOException.class).when(streetFairRepository).update(any(), any());
        when(streetFairRepository.selectById(id)).thenReturn(id);
        final StreetFairDomain streetFairDomain = Util.loadFile(JSON_STREET_FAIR_DOMAIN, StreetFairDomain.class);
        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            streetFairServiceImpl.update(streetFairDomain, id);
        });
        assertTrue(exception.getMessage().contains(ERROR_TO_UPDATE));
    }

    @Test
    public void updateWithErrorOfDataAccessException() throws IOException {
        final int id = 1;
        doThrow(new DataAccessException(ERROR_TO_UPDATE) {
        }).when(streetFairRepository).update(any(), any());
        when(streetFairRepository.selectById(id)).thenReturn(id);
        final StreetFairDomain streetFairDomain = Util.loadFile(JSON_STREET_FAIR_DOMAIN, StreetFairDomain.class);
        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            streetFairServiceImpl.update(streetFairDomain, id);
        });
        assertTrue(exception.getMessage().contains(ERROR_TO_UPDATE));
    }

    @Test
    public void updateWithErrorIdNotFound() throws IOException {
        final int id = 1;
        when(streetFairRepository.selectById(id)).thenReturn(null);
        final StreetFairDomain streetFairDomain = Util.loadFile(JSON_STREET_FAIR_DOMAIN, StreetFairDomain.class);
        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            streetFairServiceImpl.update(streetFairDomain, id);
        });
        assertTrue(exception.getMessage().contains(String.format(ERROR_NO_EXISTS, id)));
    }

    @Test
    public void findAllParametersSuccessFully() throws IOException {
        final String district = "SAO MIGUEL";
        final String regionFive = "Leste";
        final String fairName = "PARQUE SONIA";
        final String neighborhood = "VL PROGRESSO";
        final List<StreetFairEntity> list = Util.loadFile(JSON_LIST_STREET_FAIR_ENTITY, new TypeReference<>() {
        });
        when(streetFairRepository.select(any(), any(), any(), any())).thenReturn(list);
        final List<StreetFairDomain> listDomain = streetFairServiceImpl.find(district, regionFive, fairName, neighborhood);
        assertEquals(listDomain.size(), list.size());
    }

    @Test
    public void findNeighborhoodNullSuccessFully() throws IOException {
        final String district = "SAO MIGUEL";
        final String regionFive = "Leste";
        final String fairName = "PARQUE SONIA";
        final String neighborhood = null;
        final List<StreetFairEntity> list = Util.loadFile(JSON_LIST_STREET_FAIR_ENTITY, new TypeReference<>() {
        });
        when(streetFairRepository.select(any(), any(), any(), any())).thenReturn(list);
        final List<StreetFairDomain> listDomain = streetFairServiceImpl.find(district, regionFive, fairName, neighborhood);
        assertEquals(listDomain.size(), list.size());
    }

    @Test
    public void findNeighborhoodAndFairNameNullSuccessFully() throws IOException {
        final String district = "SAO MIGUEL";
        final String regionFive = "Leste";
        final String fairName = null;
        final String neighborhood = null;
        final List<StreetFairEntity> list = Util.loadFile(JSON_LIST_STREET_FAIR_ENTITY, new TypeReference<>() {
        });
        when(streetFairRepository.select(any(), any(), any(), any())).thenReturn(list);
        final List<StreetFairDomain> listDomain = streetFairServiceImpl.find(district, regionFive, fairName, neighborhood);
        assertEquals(listDomain.size(), list.size());
    }

    @Test
    public void findNeighborhoodAndFairNameAndRegionFiveNullSuccessFully() throws IOException {
        final String district = "SAO MIGUEL";
        final String regionFive = null;
        final String fairName = null;
        final String neighborhood = null;
        final List<StreetFairEntity> list = Util.loadFile(JSON_LIST_STREET_FAIR_ENTITY, new TypeReference<>() {
        });
        when(streetFairRepository.select(any(), any(), any(), any())).thenReturn(list);
        final List<StreetFairDomain> listDomain = streetFairServiceImpl.find(district, regionFive, fairName, neighborhood);
        assertEquals(listDomain.size(), list.size());
    }

    @Test
    public void findDistrictNullSuccessFully() throws IOException {
        final String district = null;
        final String regionFive = "Leste";
        final String fairName = "PARQUE SONIA";
        final String neighborhood = "VL PROGRESSO";
        final List<StreetFairEntity> list = Util.loadFile(JSON_LIST_STREET_FAIR_ENTITY, new TypeReference<>() {
        });
        when(streetFairRepository.select(any(), any(), any(), any())).thenReturn(list);
        final List<StreetFairDomain> listDomain = streetFairServiceImpl.find(district, regionFive, fairName, neighborhood);
        assertEquals(listDomain.size(), list.size());
    }

    @Test
    public void findAllParametersNullAndNotValid() {
        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            streetFairServiceImpl.find(null, null, null, null);
        });
        assertTrue(exception.getMessage().contains(ERROR_TO_FIND));
    }

    @Test
    public void findEmptyResult() throws IOException {
        final String district = null;
        final String regionFive = "Leste";
        final String fairName = "PARQUE SONIA";
        final String neighborhood = "VL PROGRESSO";
        final List<StreetFairEntity> list = new ArrayList<>();
        when(streetFairRepository.select(any(), any(), any(), any())).thenReturn(list);
        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            streetFairServiceImpl.find(district, regionFive, fairName, neighborhood);
        });
        assertTrue(exception.getMessage().contains(ERROR_TO_FIND));
    }

    @Test
    public void findAllParametersWithErrorOfIOException() throws IOException {
        final String district = "SAO MIGUEL";
        final String regionFive = "Leste";
        final String fairName = "PARQUE SONIA";
        final String neighborhood = "VL PROGRESSO";
        final List<StreetFairEntity> list = Util.loadFile(JSON_LIST_STREET_FAIR_ENTITY, new TypeReference<>() {
        });
        when(streetFairRepository.select(any(), any(), any(), any())).thenThrow(IOException.class);
        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            streetFairServiceImpl.find(district, regionFive, fairName, neighborhood);
        });
        assertTrue(exception.getMessage().contains(ERROR_TO_FIND));
    }

    @Test
    public void findAllParametersWithErrorOfDataAccessException() throws IOException {
        final String district = "SAO MIGUEL";
        final String regionFive = "Leste";
        final String fairName = "PARQUE SONIA";
        final String neighborhood = "VL PROGRESSO";
        final List<StreetFairEntity> list = Util.loadFile(JSON_LIST_STREET_FAIR_ENTITY, new TypeReference<>() {
        });
        when(streetFairRepository.select(any(), any(), any(), any())).thenThrow(new DataAccessException(ERROR_TO_FIND){});
        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            streetFairServiceImpl.find(district, regionFive, fairName, neighborhood);
        });
        assertTrue(exception.getMessage().contains(ERROR_TO_FIND));
    }
}
