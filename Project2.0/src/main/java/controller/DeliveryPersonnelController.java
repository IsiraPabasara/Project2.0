package controller;

import model.DeliveryPersonnel;
import model.DeliveryPersonnelDAO;
import view.DeliveryPersonnelView;

import java.util.List;

//DAO to fetch or save data
public class DeliveryPersonnelController {

    private DeliveryPersonnelDAO dao;//Used to interact with the database
    private DeliveryPersonnelView view;//Used to update and control the UI

    public DeliveryPersonnelController(DeliveryPersonnelDAO dao, DeliveryPersonnelView view) {

        this.dao = dao;
        this.view = view;
        view.setController(this);//View know who is the controller

    }

    public void addPersonnel(DeliveryPersonnel p) {

        dao.addPersonnel(p);
        refreshPersonnelList();

    }

    public void updatePersonnel(DeliveryPersonnel p) {

        dao.updatePersonnel(p);
        refreshPersonnelList();

    }

    public void deletePersonnel(String delivery_personnel_id) {
        dao.deletePersonnel(delivery_personnel_id);
        refreshPersonnelList();
    }

    public void refreshPersonnelList() {

        List<DeliveryPersonnel> personnelList = dao.getAllPersonnel();
        view.displayPersonnel(personnelList);

    }


}
