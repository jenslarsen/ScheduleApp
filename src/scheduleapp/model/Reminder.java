/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.model;

import java.time.LocalDateTime;

/**
 *
 * @author Jens Larsen
 */
public class Reminder {

    private int reminderId;
    private LocalDateTime reminderDate;
    private int snoozeincrement;
    private int snoozeincrementTypeId;
    private int appointmentId;
    private String createdby;
    private LocalDateTime createdDate;
    private int remindercol;

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public LocalDateTime getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(LocalDateTime reminderDate) {
        this.reminderDate = reminderDate;
    }

    public int getSnoozeincrement() {
        return snoozeincrement;
    }

    public void setSnoozeincrement(int snoozeincrement) {
        this.snoozeincrement = snoozeincrement;
    }

    public int getSnoozeincrementTypeId() {
        return snoozeincrementTypeId;
    }

    public void setSnoozeincrementTypeId(int snoozeincrementTypeId) {
        this.snoozeincrementTypeId = snoozeincrementTypeId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public int getRemindercol() {
        return remindercol;
    }

    public void setRemindercol(int remindercol) {
        this.remindercol = remindercol;
    }

}
