package be.manager.impl;

import be.dao.LocDao;
import be.dao.LocuriUtilizariDao;
import be.dao.UserDao;
import be.dao.UtilizareDao;
import be.dto.FreeTimeDTO;
import be.dto.LocDTO;
import be.dto.UtilizareDTO;
import be.dtoEntityMappers.LocDTOEntityMapper;
import be.entity.Loc;
import be.entity.User;
import be.entity.Utilizare;
import be.exceptions.BusinessException;
import be.manager.remote.UtilizareManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class UtilizareManager implements UtilizareManagerRemote {

    @Autowired
    private UtilizareDao utilizareDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LocDao locDao;

    @Autowired
    private LocuriUtilizariDao locuriUtilizariDao;

    private Logger logger = Logger.getLogger(UtilizareManager.class.getName());

    private static java.sql.Date convertUtilToSql(Date uDate){
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    private static EntityManagerFactory createEM(){
        Properties properties = new Properties();
        properties.setProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/licentabe");
        properties.setProperty("spring.datasource.username", "iova");
        properties.setProperty("spring.datasource.password", "asasiasa123");
        properties.setProperty("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
        EntityManagerFactory entityManager = Persistence.createEntityManagerFactory("jdbc:mysql://localhost:3306/licentabe", properties);
        return entityManager;
    }

    /*@PersistenceContext
    EntityManager entityManager;*/

    private int checkFree(Integer id, String startDate, String finalDate){
        EntityManagerFactory entityManagerFactory = createEM();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("select u.id from utilizari u inner join locuri_utilizari lu on u.id = lu.utilizari_id " +
                "inner join locuri l on lu.loc_id = l.id where lu.loc_id = ? and (? < u.final_date and ? > u.start_date)");
        query.setParameter(1, id);
        query.setParameter(2, startDate);
        query.setParameter(3, finalDate);
        int idB = (int) query.getSingleResult();
        return idB;
    }

    private int ver(String data){
        EntityManagerFactory entityManagerFactory = createEM();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("select u.id from utilizari u where ? > u.start_date");
        query.setParameter(1, data);
        int re = query.getFirstResult();
        return re;
    }

    @Override
    public LocDTO insertUtilizare(UtilizareDTO utilizareDTO) throws BusinessException {
        //List<FreeTimeDTO> freeTimeList = locDao.findAllFreeTimes(utilizareDTO.getIdLoc());
        //int free = locDao.checkFree(utilizareDTO.getIdLoc(), utilizareDTO.getStartDate(), utilizareDTO.getFinalDate());
        //if (free != 0)
        //    return null;
        int id = utilizareDTO.getIdLoc();
        //List<Integer> list = locuriUtilizariDao.getIds(id);


        //Date startDate = utilizareDTO.getStartDate();
        //Date finalDate = utilizareDTO.getFinalDate();
        //java.sql.Date startDateSQL = convertUtilToSql(startDate);

        //java.sql.Date finalDateSQL = convertUtilToSql(finalDate);
        //DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        //String d = dateFormat.format(startDate);
        //String df = dateFormat.format(startDate);
        //int r = ver(d);

        //int free = locuriUtilizariDao.checkFree(utilizareDTO.getIdLoc(), d, df);
        /*int free = checkFree(utilizareDTO.getIdLoc(), d, df);
        if (free != 0)
            return null;*/
        User user = userDao.findUserByUsername(utilizareDTO.getUsername());
        Loc loc = locDao.findAllByID(utilizareDTO.getIdLoc());
        if (user == null)
            throw new BusinessException("Not found", "The username is wrong");
        Utilizare utilizare = new Utilizare();
        utilizare.setUser(user);
        utilizare.setStartDate(utilizareDTO.getStartDate());
        utilizare.setFinalDate(utilizareDTO.getFinalDate());
        utilizareDao.save(utilizare);
        Set<Utilizare> utilizari = loc.getUtilizari();
        utilizari.add(utilizare);
        locDao.save(loc);
        LocDTO dtoPersisted = LocDTOEntityMapper.getDTOFromLoc(loc);
        return dtoPersisted;
    }

    @Override
    public List<UtilizareDTO> findAllUtilizari() {
        return null;
    }

    @Override
    public void deleteUtilizareById(Integer id) throws BusinessException {

    }
}
