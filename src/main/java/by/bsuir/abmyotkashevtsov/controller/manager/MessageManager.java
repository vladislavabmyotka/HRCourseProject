package by.bsuir.abmyotkashevtsov.controller.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Manager of the message.
 */
public class MessageManager {
    public enum LanguageType {
        RU(ResourceBundle.getBundle("lang.app", new Locale("ru"))),
        EN(ResourceBundle.getBundle("lang.app", new Locale("en")));

        private ResourceBundle bundle;

        LanguageType(ResourceBundle bundle) {
            this.bundle = bundle;
        }

        public String getMessage(String key) {
            return bundle.getString(key);
        }
    }

    /**
     * Gets the necessary message from the file-properties by the key, based on the transferred string value of the
     * user selected language.
     * @param language - user selected language.
     * @param key - key to get a message.
     * @return right message.
     */
    public static String getMessage(String language, String key) {
        LanguageType type;
        if (language != null) {
            int length = language.length();
            if (length > 2) {
                language = language.substring(length - 2, length);
            }
            try {
                type = LanguageType.valueOf(language.toUpperCase());
            } catch (IllegalArgumentException e) {
                type = LanguageType.RU;
            }
        } else {
            type = LanguageType.RU;
        }
        return type.getMessage(key);
    }
}
