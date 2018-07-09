/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.model;

/**
 * Custom exception to handle problems during login
 *
 * @author Jens Larsen
 */
public class LoginException extends Exception {

    /**
     * Calls super message handler
     *
     * @param message
     */
    public LoginException(String message) {
        super(message);
    }
}
