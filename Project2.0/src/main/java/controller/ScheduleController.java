package controller;

import model.ScheduleModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleController {
    private ScheduleModel model;

    public ScheduleController() {
        model = new ScheduleModel();
    }

    //Insert,Add new schedule
    public void saveSchedule(String scheduleId, Date date, String packageId, String personnelId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(date);

        try {
            model.insertSchedule(scheduleId, formattedDate, personnelId, packageId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Update schedule
    public void updateSchedule(String scheduleId, String newDate) {
        try {
            model.updateSchedule(scheduleId, newDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
