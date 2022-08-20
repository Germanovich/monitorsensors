package com.germanovich.monitorsensors.dao.range;

import com.germanovich.monitorsensors.dao.AbstractDao;
import com.germanovich.monitorsensors.model.range.Range;
import com.germanovich.monitorsensors.model.range.Range_;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaUpdate;

@Component
public class RangeDaoImpl extends AbstractDao<Range, Long> implements RangeDao {

    @Override
    protected void updateSet(final CriteriaUpdate<Range> criteriaUpdate, final Range entity) {
        criteriaUpdate.set(Range_.from, entity.getFrom());
        criteriaUpdate.set(Range_.to, entity.getTo());
    }

    @Override
    protected Class<Range> getType() {
        return Range.class;
    }
}
