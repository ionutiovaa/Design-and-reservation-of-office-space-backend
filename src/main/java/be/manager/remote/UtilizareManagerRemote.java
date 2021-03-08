package be.manager.remote;

import be.dto.*;
import be.entity.Loc;
import be.exceptions.BusinessException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@Component
public interface UtilizareManagerRemote {

    String insertUtilizare(UtilizareDTO utilizareDTO) throws BusinessException, ParseException;

    List<SchedulesDTO> findAllSchedules(ForGetSchedulesDTO forGetSchedulesDTO) throws BusinessException;

    Boolean checkFree(UtilizareDTO utilizareDTO) throws BusinessException, ParseException;

    Boolean deleteUtilizare(UtilizareDTO utilizareDTO) throws BusinessException, ParseException;

}
