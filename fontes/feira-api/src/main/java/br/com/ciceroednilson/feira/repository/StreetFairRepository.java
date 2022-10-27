package br.com.ciceroednilson.feira.repository;

import br.com.ciceroednilson.feira.repository.entity.StreetFairEntity;
import br.com.ciceroednilson.feira.repository.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Repository
public class StreetFairRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(final StreetFairEntity entity) throws IOException {
        this.jdbcTemplate.update(Query.builder().getInsert(),
                entity.getLongitude(),
                entity.getLatitude(), entity.getSetCens(), entity.getAreAp(),
                entity.getCodDist(), entity.getDistrict(), entity.getCodSubPrefecture(),
                entity.getSubPrefecture(), entity.getRegionFive(), entity.getRegionEight(),
                entity.getFairName(), entity.getRegister(), entity.getStreetName(),
                entity.getNumber(), entity.getNeighborhood(), entity.getReferencePoint());
    }

    public void delete(final Integer id) throws IOException {
        this.jdbcTemplate.update(Query.builder().getDelete(), id);
    }

    public void update(final StreetFairEntity entity, final Integer id) throws IOException {
        this.jdbcTemplate.update(Query.builder().getUpdate(),
                entity.getLongitude(),
                entity.getLatitude(), entity.getSetCens(), entity.getAreAp(),
                entity.getCodDist(), entity.getDistrict(), entity.getCodSubPrefecture(),
                entity.getSubPrefecture(), entity.getRegionFive(), entity.getRegionEight(),
                entity.getFairName(), entity.getRegister(), entity.getStreetName(),
                entity.getNumber(), entity.getNeighborhood(), entity.getReferencePoint(),
                id);
    }

    public List<StreetFairEntity> select(final String district, final String regionFive, final String fairName, final String neighborhood) throws IOException {
        return jdbcTemplate.query(Query.builder().getSelect(),
                new BeanPropertyRowMapper(StreetFairEntity.class),
                district, regionFive, fairName, neighborhood);
    }

    public Integer selectById(final Integer id) throws IOException {
        final List<StreetFairEntity> list = jdbcTemplate.query(Query.builder().getSelectById(), new BeanPropertyRowMapper(StreetFairEntity.class), id);
        if (Objects.isNull(list) || list.isEmpty()) {
            return null;
        }
        return list.get(0).getId();
    }
}
