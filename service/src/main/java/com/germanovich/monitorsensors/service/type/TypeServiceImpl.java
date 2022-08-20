package com.germanovich.monitorsensors.service.type;

import com.germanovich.monitorsensors.dao.type.TypeDao;
import com.germanovich.monitorsensors.model.type.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

    private final TypeDao typeDao;

    @Override
    public List<Type> getTypes() {
        return typeDao.getAll();
    }
}
