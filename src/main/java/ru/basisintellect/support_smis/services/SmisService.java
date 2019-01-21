package ru.basisintellect.support_smis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.basisintellect.support_smis.entities.Smis;
import ru.basisintellect.support_smis.repositories.SmisRepository;
import ru.basisintellect.support_smis.soap_client.TestConnect;

import java.util.Date;
import java.util.List;

@Service
public class SmisService {

    @Autowired
    SmisRepository smisesRepo;

    List<Smis> smises;

    @Autowired
    public void init(){
        smises = (List<Smis>) smisesRepo.findAll();
    }

    public Smis findSmisById(Long id){
        Smis result = null;
        for (Smis smis : smises) {
            if (id == smis.getId())
                result = smis;
            break;
        }
        return result;
    }

    public Smis addSmis(String region, String agreement, String validity, String contacts, String url){
        Smis smis = new Smis();
        smis.setRegion(region);
        smis.setDateRegistration(new Date());
        smis.setAgreement(agreement);
        smis.setValidity(validity);
        smis.setContacts(contacts);
        smis.setUrl(url);
        smisesRepo.save(smis);
        smises.add(smis);
        return smis;
    }

    public List<Smis> getAllSmises(){
        return smises;
    }

    public Smis editSmis (Long id, String region, String agreement, String validity, String contacts, String url){
        //добавить проверку на валидность id
        Smis smis = findSmisById(id);
        smis.setRegion(region);
        smis.setAgreement(agreement);
        smis.setValidity(validity);
        smis.setContacts(contacts);
        smis.setUrl(url);
        smisesRepo.save(smis);
        return smis;
}

    public void deleteSmis(Long id){
        //добавить проверку на валидность id
        Smis smis = findSmisById(id);
        smisesRepo.deleteById(id);
        smises.remove(smis);
    }

    public void changeStatusSmik(Long id){
        //добавить проверку на валидность id
        Smis smis = findSmisById(id);
        smis.setEnabled(TestConnect.checkStatus(smis.getUrl()));
        smisesRepo.save(smis);
    }

    /*private class TestConnect implements Runnable{
        @Override
        public void run() {
            while (true){

            }
        }
    }*/

}
