package ru.basisintellect.support_smis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.client.SoapFaultClientException;
import ru.basisintellect.support_smis.controllers.SmisController;
import ru.basisintellect.support_smis.model.SmisStateMsg;
import ru.basisintellect.support_smis.model.entities.SmisEntity;
import ru.basisintellect.support_smis.repositories.SmisRepository;
import ru.basisintellect.support_smis.soap_client.TestConnectClient;
import ru.basisintellect.support_smis.soap_client.wsdl.node.TestRequest;
import ru.basisintellect.support_smis.soap_client.wsdl.node.TestResponse;

import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

@Service
public class SmisService {

    class MyThread implements Runnable {

        public void setCancelled(boolean cancelled) {
            this.cancelled = cancelled;
        }

        private volatile boolean cancelled = false;

        TestConnectClient connectClient = new TestConnectClient();
        SmisService smisService;

        public MyThread(SmisService smisService) {
            this.smisService = smisService;
        }


        Long i = (long) 0;
        @Override
        public void run() {
            while (!cancelled) {
                for (SmisEntity smisEntity : smises) {
                    try {
                        connectClient.setDefaultUri(smisEntity.getUrl());
                        TestRequest request = new TestRequest();
                        TestResponse response = connectClient.testConnect(request);
                    } /* catch (SoapFaultClientException e) {
                        //ловим наше исключение - комплекс работает
                            smisEntity.setEnabled(true);
                            smisService.onChangeState(smisEntity.getId(),true);

                    }*/ catch (Exception e) {
                        //ловим любое другое исключение - не работает
                            if (e instanceof SoapFaultClientException) {
                                smisEntity.setEnabled(true);
                                smisService.onChangeState(smisEntity.getId(), true);

                            }else{
                                smisEntity.setEnabled(false);
                                smisService.onChangeState(smisEntity.getId(), false);
                            }
                    }
                }
                try {

                    /*addSmis("name", "name", "name", "name","name", i);
                    i++;*/

                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    MyThread myRunnable;
    List<SmisEntity> smises;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    SmisRepository smisesRepo;

    @Autowired
    SmisController smisController;

    @Autowired
    public void init() {
        if (myRunnable != null)
            myRunnable.setCancelled(true);

            smises = (List<SmisEntity>) smisesRepo.findAll();
            myRunnable = new MyThread(this);
            Thread myThread = new Thread(myRunnable);

            try {
                myThread.start();
            } catch (ConcurrentModificationException e){
                myThread.start();
            }
    }

    public SmisEntity findSmisById(Long id) {
        SmisEntity result = null;
        for (SmisEntity smisEntity : smises) {
            if (id == smisEntity.getId()) {
                result = smisEntity;
                break;
            }
        }
        return result;
    }

    public void onChangeState(Long id, Boolean state) {
        SmisStateMsg msg = new SmisStateMsg();
        msg.setId(id);
        msg.setActive(state);
        simpMessagingTemplate.convertAndSend("/topic/changestate", msg);
//        System.out.println("**********************************************************************************");
    }

    public SmisEntity addSmis(String name, String agreement, String validity, String contacts, String url, Long parent_id) {
        SmisEntity smisEntity = new SmisEntity();
        smisEntity.setName(name);
        smisEntity.setDateRegistration(new Date());
        smisEntity.setAgreement(agreement);
        smisEntity.setValidity(validity);
        smisEntity.setContacts(contacts);
        smisEntity.setUrl(url);
        if (parent_id != null) {
            smisEntity.setParentSmis(smisesRepo.findById(parent_id).get());
        }
        smisesRepo.save(smisEntity);
        smises.add(smisEntity);
        return smisEntity;
    }

    public List<SmisEntity> getAllSmises() {
        return smises;
    }

    public SmisEntity editSmis(Long id, String name, String agreement, String validity, String contacts, String url, SmisEntity parent) {
        //добавить проверку на валидность id
        SmisEntity smisEntity = findSmisById(id);
        smisEntity.setName(name);
        smisEntity.setAgreement(agreement);
        smisEntity.setValidity(validity);
        smisEntity.setContacts(contacts);
        smisEntity.setUrl(url);
        smisEntity.setParentSmis(parent);
        smisesRepo.save(smisEntity);
        return smisEntity;
    }

    public void deleteSmis(Long id) {
        //добавить проверку на валидность id
        SmisEntity smisEntity = findSmisById(id);
        smisesRepo.deleteById(id);
        smises.remove(smisEntity);
    }
    }
