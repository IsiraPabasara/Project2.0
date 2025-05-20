package controller;

import Database.ParcelDAO;
import model.Parcel;

import java.util.List;

public class ParcelController {
    private ParcelDAO parcelDAO;

    public ParcelController() {
        this.parcelDAO = new ParcelDAO();
    }

    public boolean addParcel(String parcelId, String senderId, String receiverId,
                             String parcelContent, String parcelType, String status) {
        Parcel parcel = new Parcel(parcelId, senderId, receiverId, parcelContent, parcelType, status);
        return parcelDAO.addParcel(parcel);
    }

    public List<Parcel> getAllParcels() {
        return parcelDAO.getAllParcels();
    }

    // Modified to only update the fields you want to change
    public boolean updateParcel(String parcelId, String parcelContent,
                                String parcelType, String status) {
        // First get the existing parcel to preserve sender/receiver IDs
        Parcel existingParcel = getParcelById(parcelId);
        if (existingParcel == null) {
            return false;
        }

        // Create updated parcel with existing sender/receiver IDs
        Parcel updatedParcel = new Parcel(
                parcelId,
                existingParcel.getSenderId(),
                existingParcel.getReceiverId(),
                parcelContent,
                parcelType,
                status
        );
        updatedParcel.setCreatedDate(existingParcel.getCreatedDate());

        return parcelDAO.updateParcel(updatedParcel);
    }

    public boolean deleteParcel(String parcelId) {
        return parcelDAO.deleteParcel(parcelId);
    }

    // Helper method to get a parcel by ID
    private Parcel getParcelById(String parcelId) {
        List<Parcel> parcels = getAllParcels();
        for (Parcel parcel : parcels) {
            if (parcel.getParcelId().equals(parcelId)) {
                return parcel;
            }
        }
        return null;
    }
}