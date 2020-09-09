package com.github.tanyueran.service.imp;

import com.github.tanyueran.service.IdBuilderService;
import com.github.tanyueran.utils.IdBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdBuilderServiceImp implements IdBuilderService {

    private static IdBuilder idBuilder = new IdBuilder(1, 1, 1);

    @Override
    public List<String> buildIds(Integer number) throws Exception {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(idBuilder.nextId() + "");
        }
        return list;
    }
}
