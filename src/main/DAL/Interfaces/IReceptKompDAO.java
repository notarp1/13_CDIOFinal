package DAL.Interfaces;

import DAL.DALException;
import DTO.ReceptKompDTO;

import java.util.List;

public interface IReceptKompDAO {
    ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException;
    List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException;
    List<ReceptKompDTO> getReceptKompList() throws DALException;
    void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException;
    void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException;
    void deleteReceptKomp(int receptId, int raavaareId) throws DALException;

}
