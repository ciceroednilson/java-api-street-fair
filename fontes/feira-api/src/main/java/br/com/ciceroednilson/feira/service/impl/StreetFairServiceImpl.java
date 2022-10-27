package br.com.ciceroednilson.feira.service.impl;

import br.com.ciceroednilson.feira.domain.StreetFairDomain;
import br.com.ciceroednilson.feira.http.ApplicationException;
import br.com.ciceroednilson.feira.mapper.StreetFairMapper;
import br.com.ciceroednilson.feira.repository.StreetFairRepository;
import br.com.ciceroednilson.feira.repository.entity.StreetFairEntity;
import br.com.ciceroednilson.feira.service.StreetFairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class StreetFairServiceImpl implements StreetFairService {

    @Autowired
    private StreetFairRepository streetFairRepository;

    private static final String ERROR_TO_SAVE = "Error to save new register!";
    private static final String ERROR_TO_DELETE = "Error to delete the register!";
    private static final String ERROR_NO_EXISTS = "Register %s not found!";
    private static final String ERROR_TO_UPDATE = "Error to update the register!";
    private static final String ERROR_TO_FIND = "Error to find the registers!";
    private static final String LOG_API_FAIR = "API-FAIR - {}";

    @Override
    public void register(final StreetFairDomain streetFairDomain) {
        try {
            log.info(LOG_API_FAIR, "Started new register.");
            final StreetFairEntity streetFairEntity = StreetFairMapper.toStreetFairEntity(streetFairDomain);
            streetFairRepository.save(streetFairEntity);
            log.info(LOG_API_FAIR, "New register save successfully.");
        } catch (final IOException ex) {
            throw new ApplicationException(ERROR_TO_SAVE, HttpStatus.INTERNAL_SERVER_ERROR, ex);
        } catch (final DataAccessException ex) {
            throw new ApplicationException(ERROR_TO_SAVE, HttpStatus.SERVICE_UNAVAILABLE, ex);
        }
    }

    @Override
    public void delete(final Integer id) {
        try {
            log.info(LOG_API_FAIR, String.format("Started delete of id %s.", id));
            if (!exists(id)) {
                throw new ApplicationException(String.format(ERROR_NO_EXISTS, id), HttpStatus.NOT_FOUND);
            }
            streetFairRepository.delete(id);
            log.info(LOG_API_FAIR, String.format("Finished delete of id %s.", id));
        } catch (final IOException ex) {
            throw new ApplicationException(ERROR_TO_DELETE, HttpStatus.INTERNAL_SERVER_ERROR, ex);
        } catch (final DataAccessException ex) {
            throw new ApplicationException(ERROR_TO_DELETE, HttpStatus.SERVICE_UNAVAILABLE, ex);
        }
    }

    @Override
    public void update(final StreetFairDomain streetFairDomain, final Integer id) {
        try {
            log.info(LOG_API_FAIR, String.format("Started update of id %s.", id));
            if (!exists(id)) {
                throw new ApplicationException(String.format(ERROR_NO_EXISTS, id), HttpStatus.NOT_FOUND);
            }
            final StreetFairEntity streetFairEntity = StreetFairMapper.toStreetFairEntity(streetFairDomain);
            streetFairRepository.update(streetFairEntity, id);
            log.info(LOG_API_FAIR, String.format("Finished update of id %s.", id));
        } catch (final IOException ex) {
            throw new ApplicationException(ERROR_TO_UPDATE, HttpStatus.INTERNAL_SERVER_ERROR, ex);
        } catch (final DataAccessException ex) {
            throw new ApplicationException(ERROR_TO_UPDATE, HttpStatus.SERVICE_UNAVAILABLE, ex);
        }
    }

    @Override
    public List<StreetFairDomain> find(final String district, final String regionFive, final String fairName, final String neighborhood) {
        try {
            log.info(LOG_API_FAIR, String.format("Started find by [district]:[%s][[regionFive][%s][fairName][%s][neighborhood][%s]",
                    district, regionFive, fairName, neighborhood));
            if (isNotValid(district, regionFive, fairName, neighborhood)) {
                throw new ApplicationException(ERROR_TO_FIND, HttpStatus.BAD_REQUEST);
            }
            final List<StreetFairEntity> listEntity = streetFairRepository.select(district, regionFive, fairName, neighborhood);
            if (listEntity.isEmpty()) {
                throw new ApplicationException(ERROR_TO_FIND, HttpStatus.NOT_FOUND);
            }
            log.info(LOG_API_FAIR, String.format("Finished find by [district]:[%s][[regionFive][%s][fairName][%s][neighborhood][%s]",
                    district, regionFive, fairName, neighborhood));
            return StreetFairMapper.toListOfStreetFairDomain(listEntity);
        } catch (final IOException ex) {
            throw new ApplicationException(ERROR_TO_FIND, HttpStatus.INTERNAL_SERVER_ERROR, ex);
        } catch (final DataAccessException ex) {
            throw new ApplicationException(ERROR_TO_FIND, HttpStatus.SERVICE_UNAVAILABLE, ex);
        }
    }

    private boolean exists(final Integer id) throws IOException {
        return streetFairRepository.selectById(id) != null;
    }

    private boolean isNotValid(final String district, final String regionFive, final String fairName, final String neighborhood) {
        return district == null && regionFive == null && fairName == null && neighborhood == null;
    }
}
