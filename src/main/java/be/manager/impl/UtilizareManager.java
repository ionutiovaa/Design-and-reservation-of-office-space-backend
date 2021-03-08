package be.manager.impl;

import be.dao.LocDao;
import be.dao.UserDao;
import be.dao.UtilizareDao;
import be.dto.*;
import be.dtoEntityMappers.UtilizareDTOEntityMapper;
import be.entity.Loc;
import be.entity.Mail;
import be.entity.User;
import be.entity.Utilizare;
import be.entity.types.UserType;
import be.exceptions.BusinessException;
import be.manager.remote.UtilizareManagerRemote;
import be.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@Component
public class UtilizareManager implements UtilizareManagerRemote {

    @Autowired
    private UtilizareDao utilizareDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LocDao locDao;

    private Logger logger = Logger.getLogger(UtilizareManager.class.getName());

    @Override
    public String insertUtilizare(UtilizareDTO utilizareDTO) throws BusinessException, ParseException {
        Loc loc = locDao.findLocByPozitie(utilizareDTO.getPosition());
        User user = userDao.findUserByUsername(utilizareDTO.getUsername());
        if (loc == null || user == null)
            return "";
        if (!checkFree(utilizareDTO))
            return "This schedule exists already";


        if (user.getUserType().equals(UserType.ADMINISTRATOR)){
            String name = user.getFirstName() + " " + user.getLastName();
            MailService.sendMail(new Mail(user.getEmail(), "Organizare birouri", "Hello, " + name + "\n" +
                    "The office was booked in the interval: " + utilizareDTO.getStartDate() + " - " + utilizareDTO.getFinalDate() + "!\n" +
                    "Details: \n" +
                    "Floor: " + loc.getEtaj().getNumar()));
        }
        else {
            User administrator = userDao.findUserByUserType(UserType.ADMINISTRATOR);
            String name = user.getFirstName() + " " + user.getLastName();
            MailService.sendMail(new Mail(user.getEmail(), "Organizare birouri", "Hello, " + name + "!\n" +
                    "The office was booked in the interval: " + utilizareDTO.getStartDate() + " - " + utilizareDTO.getFinalDate() + "!\n" +
                    "Details: \n" +
                    "Floor: " + loc.getEtaj().getNumar()));
            MailService.sendMail(new Mail(administrator.getEmail(), "Organizare birouri", "Hello, " + administrator.getFirstName() +
                    " " + administrator.getLastName() + "!\n" + name + " booked an office in the interval: " +
                    utilizareDTO.getStartDate() + " - " + utilizareDTO.getFinalDate() + "!\n" +
                    "Details: \n" +
                    "Floor: " + loc.getEtaj().getNumar()));
        }


        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(utilizareDTO.getStartDate());
        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(utilizareDTO.getFinalDate());
        Utilizare utilizare = new Utilizare(user, startDate, endDate, loc);
        utilizareDao.save(utilizare);
        loc.getUtilizari().add(utilizare);
        locDao.save(loc);
        UtilizareDTO dtoPersisted = UtilizareDTOEntityMapper.getDTOFromUtilizare(utilizare);
        return "ok";
    }

    @Override
    public List<SchedulesDTO> findAllSchedules(ForGetSchedulesDTO forGetSchedulesDTO) throws BusinessException {
        Loc loc = locDao.findLocByPozitie(forGetSchedulesDTO.getPosition());
        List<String> allStartDates = utilizareDao.findAllSchedulesStartDate(forGetSchedulesDTO.getDate(), loc.getID());
        List<String> allEndDates = utilizareDao.findAllSchedulesEndDate(forGetSchedulesDTO.getDate(), loc.getID());
        List<Integer> allUsersIds = utilizareDao.getAllUserIds(forGetSchedulesDTO.getDate(), loc.getID());
        List<SchedulesDTO> schedulesDTOList = new ArrayList<>();
        for (int i = 0; i < allStartDates.size(); i++) {
            schedulesDTOList.add(new SchedulesDTO(allStartDates.get(i), allEndDates.get(i), allUsersIds.get(i)));
        }
        return schedulesDTOList;
    }

    @Override
    public Boolean checkFree(UtilizareDTO utilizareDTO) throws BusinessException, ParseException {
        Loc loc = locDao.findLocByPozitie(utilizareDTO.getPosition());
        Date startDatee = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(utilizareDTO.getStartDate());
        Date endDatee = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(utilizareDTO.getFinalDate());
        List<Utilizare> id = utilizareDao.findAllByLocAndStartDateIsLessThanAndFinalDateIsGreaterThan(loc, endDatee, startDatee);
        if (id.size() == 0)
            return true;
        return false;
    }

    @Override
    public Boolean deleteUtilizare(UtilizareDTO utilizareDTO) throws BusinessException, ParseException {
        User user = userDao.findUserByUsername(utilizareDTO.getUsername());
        Loc loc = locDao.findLocByPozitie(utilizareDTO.getPosition());
        Date startDatee = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(utilizareDTO.getStartDate());
        //Date endDatee = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(utilizareDTO.getFinalDate());
        try{
            Utilizare utilizare = utilizareDao.getUtilizareByStartDateAndLocAndUser_ID(startDatee, loc, user.getID());
            utilizareDao.deleteFromLocuriUtilizariByUtilizare_id(utilizare.getID());
            utilizareDao.deleteUtilizareByID(utilizare.getID());
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
