/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.model;

/**
 * Custom exception to handle time entry problems
 *
 * @author Jens Larsen
 */
public class TimeEntryException extends Exception {

    public TimeEntryException(String message) {
        super(message);
    }
}
