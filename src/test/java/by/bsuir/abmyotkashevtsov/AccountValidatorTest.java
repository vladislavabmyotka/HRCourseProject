package by.bsuir.abmyotkashevtsov;

import by.bsuir.abmyotkashevtsov.constant.AccountAttachmentConstant;
import by.bsuir.abmyotkashevtsov.validator.AccountValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountValidatorTest {

    @Test
    public void correctLoginTest() {
        String login = "simple123";
        Assert.assertTrue(AccountValidator.checkLogin(login));
    }

    @Test
    public void incorrectLoginTest() {
        String login = "656565";
        Assert.assertFalse(AccountValidator.checkLogin(login));
    }

    @Test
    public void nullLoginTest() {
        String login = null;
        Assert.assertFalse(AccountValidator.checkLogin(login));
    }

    @Test
    public void correctPasswordTest() {
        String password = "password123";
        Assert.assertTrue(AccountValidator.checkPassword(password));
    }

    @Test
    public void incorrectPasswordTest() {
        String password = "123123123";
        Assert.assertFalse(AccountValidator.checkPassword(password));
    }

    @Test
    public void nullPasswordTest() {
        String password = null;
        Assert.assertFalse(AccountValidator.checkLogin(password));
    }

    @Test
    public void checkAttachmentTrueTest() {
        String candidateAttachment = AccountAttachmentConstant.CANDIDATE_ATTACHMENT;
        String employerAttachment = AccountAttachmentConstant.EMPLOYER_ATTACHMENT;
        Assert.assertTrue(AccountValidator.checkAttachment(candidateAttachment));
        Assert.assertTrue(AccountValidator.checkAttachment(employerAttachment));
    }

    @Test
    public void checkAttachmentFalseTest() {
        String candidateAttachment = "candidateFalse";
        String employerAttachment = "employerFalse";
        Assert.assertFalse(AccountValidator.checkAttachment(candidateAttachment));
        Assert.assertFalse(AccountValidator.checkAttachment(employerAttachment));
        Assert.assertFalse(AccountValidator.checkAttachment(null));
    }
}
