package be.manager.impl;

import be.dao.SediuDao;
import be.exceptions.BusinessException;
import be.manager.remote.SediuManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SediuManager implements SediuManagerRemote {

    @Autowired
    private SediuDao sediuDao;

    @Override
    public String getDimensions() throws BusinessException {
        return sediuDao.findSediuByID(1).getDimensions();
    }

    @Override
    public void updateDimensions(String dimensions) throws BusinessException {
        String actualDimensions = sediuDao.findSediuByID(1).getDimensions();
        String[] rowsCol = actualDimensions.split(":");
        Integer rows = Integer.valueOf(rowsCol[0]);
        Integer columns = Integer.valueOf(rowsCol[1]);
        String[] updated = dimensions.split(":");
        String rowOrColumn = updated[0];
        String plusOrMinus = updated[1];
        if (rowOrColumn.equals("row")){
            if (plusOrMinus.equals("minus")){
                rows--;
            }
            else if (plusOrMinus.equals("plus")){
                rows++;
            }
        }
        else if (rowOrColumn.equals("column")){
            if (plusOrMinus.equals("minus")){
                columns--;
            }
            else if (plusOrMinus.equals("plus")){
                columns++;
            }
        }
        else throw new BusinessException("Invalid request", "Invalid request");
        String dtoUpdated = String.valueOf(rows) + ":" + String.valueOf(columns);
        sediuDao.updateDimensions(dtoUpdated, 1);
    }
}
