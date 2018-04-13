package by.bsuir.abmyotkashevtsov;

import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ManagerTest {

    @Test
    public void messageManagerRUTest() {
        String language = "ru";
        String key = "surname";
        String actualMessage = "Фамилия";
        String message = MessageManager.getMessage(language, key);
        Assert.assertEquals(actualMessage, message);
    }

    @Test
    public void messageManagerENTest() {
        String language = "en";
        String key = "surname";
        String actualMessage = "Surname";
        String message = MessageManager.getMessage(language, key);
        Assert.assertEquals(actualMessage, message);
    }

    @Test
    public void messageManagerDefaultNullTest() {
        String language = null;
        String key = "surname";
        String actualMessage = "Фамилия";
        String message = MessageManager.getMessage(language, key);
        Assert.assertEquals(actualMessage, message);
    }

    @Test
    public void messageManagerMoreValueRUTest() {
        String language = "http://localhost:8080/hr/index.jsp?language=ru";
        String key = "surname";
        String actualMessage = "Фамилия";
        String message = MessageManager.getMessage(language, key);
        Assert.assertEquals(actualMessage, message);
    }

    @Test
    public void messageManagerMoreValueENTest() {
        String language = "http://localhost:8080/hr/index.jsp?language=en";
        String key = "surname";
        String actualMessage = "Surname";
        String message = MessageManager.getMessage(language, key);
        Assert.assertEquals(actualMessage, message);
    }
}
