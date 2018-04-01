package by.bsuir.abmyotkashevtsov.controller.creator;

import by.bsuir.abmyotkashevtsov.domain.Account;

import java.util.ResourceBundle;

/**
 * The class whose only method is "createAdmin()" returns an administrator object of type "Account", formed on the
 * basis of information stored in the corresponding file-property.
 */
public class AdminCreator {

    public Account createAdmin() {
        ResourceBundle resource = ResourceBundle.getBundle("admin");
        String login = resource.getString("admin.username");
        String password = resource.getString("admin.password");
        String attachment = resource.getString("admin.attachment");
        return new Account(login, password, attachment);
    }
}
