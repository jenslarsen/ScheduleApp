/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.model;

import java.time.LocalDateTime;

/**
 * Models user table in the database
 *
 * @author Jens Larsen
 */
public class User {

    private int userId;
    private String userName;
    private String password;
    private boolean active;
    private String createBy;
    private LocalDateTime createDate;
    private String lastUpdate;
    private String lateUpdatedBy;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLateUpdatedBy() {
        return lateUpdatedBy;
    }

    public void setLateUpdatedBy(String lateUpdatedBy) {
        this.lateUpdatedBy = lateUpdatedBy;
    }
}
