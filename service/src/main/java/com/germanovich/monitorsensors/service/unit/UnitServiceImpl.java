package com.germanovich.monitorsensors.service.unit;

import com.germanovich.monitorsensors.dao.unit.UnitDao;
import com.germanovich.monitorsensors.model.unit.Unit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitDao unitDao;

    @Override
    public List<Unit> getUnits() {
        return unitDao.getAll();
    }
}
