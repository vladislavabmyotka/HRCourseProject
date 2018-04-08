package by.bsuir.abmyotkashevtsov.validator;

import by.bsuir.abmyotkashevtsov.constant.ReCaptchaConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Utility class for validation ReCaptcha.
 */
public class VerifyValidator {
    private final static Logger LOGGER = LogManager.getLogger(VerifyValidator.class);

    private static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public static boolean verify(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
            return false;
        }

        try {
            URL verifyUrl = new URL(SITE_VERIFY_URL);

            // Open a Connection to URL above.
            HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();

            // Add the Header informations to the Request to prepare send to the server.
            conn.setRequestMethod("POST");

            // Data will be sent to the server.
            String postParams = "secret=" + ReCaptchaConstant.SECRET_KEY + "&response=" + gRecaptchaResponse;

            // Send Request
            conn.setDoOutput(true);

            // Get the output stream of Connection.
            // Write data in this stream, which means to send data to Server.
            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());

            outStream.flush();
            outStream.close();

            // Get the Input Stream of Connection to read data sent from the Server.
            InputStream is = conn.getInputStream();

            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            return jsonObject.getBoolean("success");
        } catch (MalformedURLException e) {
            LOGGER.log(Level.ERROR, "Invalid verify URL! Detail: " + e.getMessage());
            return false;
        } catch (ProtocolException e) {
            LOGGER.log(Level.ERROR, "Can't add the Header information to the Request to prepare send " +
                    "to the server! Detail: " + e.getMessage());
            return false;
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "There was an attempt to register with invalid reCAPTCHA! Detail: " +
                    e.getMessage());
            return false;
        }
    }
}
