/*
package be.manager.impl;

import be.dao.QRCodeDao;
import be.dto.ChangeQRCodeDTO;
import be.dto.QRCodeDTO;
import be.dtoEntityMappers.QRCodeDTOEntityMapper;
import be.entity.QRCode;
import be.exceptions.BusinessException;
import be.manager.remote.QRCodeManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class QRCodeManager implements QRCodeManagerRemote {

    @Autowired
    private QRCodeDao qrCodeDao;

    public QRCodeDao getQrCodeDao() {
        return qrCodeDao;
    }

    private Logger logger = Logger.getLogger(QRCodeManager.class.getName());

    @Override
    public QRCodeDTO insertQRCode(QRCodeDTO qrCodeDTO) throws BusinessException {
        QRCode qrCode = qrCodeDao.findQRCodeByNume(qrCodeDTO.getNume());
        if (qrCode != null)
            return null;
        QRCodeDTO dtoPersisted = new QRCodeDTO();
        qrCode = new QRCode();
        qrCode.setNume(qrCodeDTO.getNume());
        QRCode persistedQRCode = qrCodeDao.save(qrCode);
        dtoPersisted = QRCodeDTOEntityMapper.getDTOFromQRCode(persistedQRCode);
        return dtoPersisted;
    }

    @Override
    public List<QRCodeDTO> findAllQRCodes() {
        List<QRCode> qrCodes = qrCodeDao.findAll();
        return QRCodeDTOEntityMapper.getAllQRCodes(qrCodes);
    }

    @Override
    public QRCodeDTO deleteQRCodeByNume(String nume) throws Exception {
        QRCode qrCode = qrCodeDao.findQRCodeByNume(nume);
        try{
            if (qrCode != null)
                qrCodeDao.delete(qrCode);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return QRCodeDTOEntityMapper.getDTOFromQRCode(qrCode);
    }

    @Override
    public QRCodeDTO findQRCodeById(Integer id) throws BusinessException {
        QRCode qrCode = qrCodeDao.findAllByID(id);
        QRCodeDTO qrCodeDTO = QRCodeDTOEntityMapper.getDTOFromQRCode(qrCode);
        return qrCodeDTO;
    }

    @Override
    public QRCodeDTO findQRCodeByNume(String nume) throws BusinessException {
        QRCode qrCode = qrCodeDao.findQRCodeByNume(nume);
        QRCodeDTO qrCodeDTO = QRCodeDTOEntityMapper.getDTOFromQRCode(qrCode);
        return qrCodeDTO;
    }

    @Override
    public QRCodeDTO updateQRCode(ChangeQRCodeDTO changeQRCodeDTO) throws BusinessException {
        QRCode qrCode = this.qrCodeDao.findQRCodeByNume(changeQRCodeDTO.getOldNume());
        if (qrCode == null)
            return null;
        if (qrCode.getNume().equals(changeQRCodeDTO.getOldNume())){
            qrCode.setNume(changeQRCodeDTO.getNewNume());
            int updated = this.qrCodeDao.updateNumeClient(qrCode.getID(), qrCode.getNume());
            return QRCodeDTOEntityMapper.getDTOAfterUpdateNume(qrCode);
        }
        else
            throw new BusinessException("Name error", "The old name is wrong");
    }
}
*/
