package by.bsuir.abmyotkashevtsov.tag;


import by.bsuir.abmyotkashevtsov.constant.AccountAttachmentConstant;
import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * The tag class for displaying the basic information of the tag 'body' for the Administrator's home pages,
 * Candidates and HR's.
 */
@SuppressWarnings("serial")
public class BodyHomePage extends TagSupport {
    private Object language;
    private String attachment;
    private String login;
    private String error;

    public void setLanguage(Object language) {
        this.language = language;
    }

    public Object getLanguage() {
        return this.language;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            switch (attachment) {
                case AccountAttachmentConstant.ADMIN_ATTACHMENT:
                    adminBodyHomePAge();
                    break;
                case AccountAttachmentConstant.CANDIDATE_ATTACHMENT:
                    candidateBodyHomePage();
                    break;
                case AccountAttachmentConstant.EMPLOYER_ATTACHMENT:
                    employerBodyHomePage();
                    break;

            }
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    private void adminBodyHomePAge() throws IOException {
        JspWriter out = pageContext.getOut();

        String hello = MessageManager.getMessage(language.toString(), MessageConstant.TAG_HELLO);
        String homeMainTitle = MessageManager.getMessage(language.toString(), MessageConstant.TAG_HOME_MAIN_TITLE);
        String adminCandidate = MessageManager.getMessage(language.toString(), MessageConstant.TAG_ADMIN_CANDIDATE);
        String adminHomeCandidateMain = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_ADMIN_HOME_CANDIDATE_MAIN);
        String adminEmployer = MessageManager.getMessage(language.toString(), MessageConstant.TAG_ADMIN_EMPLOYER);
        String adminHomeEmployerMain = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_ADMIN_HOME_EMPLOYER_MAIN);
        String adminHomeGeneral = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_ADMIN_HOME_GENERAL);
        String adminHomeGeneralMain = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_ADMIN_HOME_GENERAL_MAIN);

        out.write("<main role=\"main\">\n" +
                "\n" +
                "        <!-- Main jumbotron for a primary marketing message or call to action -->\n" +
                "        <div class=\"jumbotron\">\n" +
                "            <div class=\"container\">\n" +
                "                <h1 class=\"display-3\">" + hello + " " + login + "!\n" +
                "                </h1>\n" +
                "                <p>" + homeMainTitle + "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"container\">\n" +
                "            <!-- Example row of columns -->\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-md-4\">\n" +
                "                    <h2>" + adminCandidate + "</h2>\n" +
                "                    <p>" + adminHomeCandidateMain + "</p>\n" +
                "                </div>\n" +
                "                <div class=\"col-md-4\">\n" +
                "                    <h2>" + adminEmployer + "</h2>\n" +
                "                    <p>" + adminHomeEmployerMain + "</p>\n" +
                "                </div>\n" +
                "                <div class=\"col-md-4\">\n" +
                "                    <h2>" + adminHomeGeneral + "</h2>\n" +
                "                    <p>" + adminHomeGeneralMain + "</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <hr>\n" +
                "\n" +
                "        </div>\n" +
                "\n" +
                "    </main>");
    }

    private void candidateBodyHomePage() throws IOException {
        JspWriter out = pageContext.getOut();

        String hello = MessageManager.getMessage(language.toString(), MessageConstant.TAG_HELLO);
        String homeMainTitle = MessageManager.getMessage(language.toString(), MessageConstant.TAG_HOME_MAIN_TITLE);
        String candidateHome8Second = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_CANDIDATE_HOME_8_SECOND);
        String candidateHome8SecondMain = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_CANDIDATE_HOME_8_SECOND_MAIN);
        String candidateHomeWantRich = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_CANDIDATE_HOME_WANT_RICH);
        String candidateHomeWantRichMain = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_CANDIDATE_HOME_WANT_RICH_MAIN);
        String candidateHomeMotivation = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_CANDIDATE_HOME_MOTIVATION);
        String candidateHomeMotivationMain = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_CANDIDATE_HOME_MOTIVATION_MAIN);

        out.write("<main role=\"main\">\n" +
                "\n" +
                "        <!-- Main jumbotron for a primary marketing message or call to action -->\n" +
                "        <div class=\"jumbotron\">\n" +
                "            <div class=\"container\">\n" +
                "                <h1 class=\"display-3\">" + hello + " " + login + "!\n" +
                "                </h1>\n" +
                "                <p>" + homeMainTitle + "</p><br/>\n" +
                "                <h6 class=\"form-signin-heading error\">" + error + "</h6>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"container\">\n" +
                "            <!-- Example row of columns -->\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-md-4\">\n" +
                "                    <h2>" + candidateHome8Second + "</h2>\n" +
                "                    <p>" + candidateHome8SecondMain + "</p>\n" +
                "                </div>\n" +
                "                <div class=\"col-md-4\">\n" +
                "                    <h2>" + candidateHomeWantRich + "</h2>\n" +
                "                    <p>" + candidateHomeWantRichMain + "</p>\n" +
                "                </div>\n" +
                "                <div class=\"col-md-4\">\n" +
                "                    <h2>" + candidateHomeMotivation + "</h2>\n" +
                "                    <p>" + candidateHomeMotivationMain + "</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <hr>\n" +
                "\n" +
                "        </div>\n" +
                "\n" +
                "    </main>");
    }

    private void employerBodyHomePage() throws IOException {
        JspWriter out = pageContext.getOut();

        String hello = MessageManager.getMessage(language.toString(), MessageConstant.TAG_HELLO);
        String homeMainTitle = MessageManager.getMessage(language.toString(), MessageConstant.TAG_HOME_MAIN_TITLE);
        String employerHomeTitle1 = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_EMPLOYER_HOME_TITLE_1);
        String employerHomeTitle1Main = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_EMPLOYER_HOME_TITLE_1_MAIN);
        String employerHomeTitle2 = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_EMPLOYER_HOME_TITLE_2);
        String employerHomeTitle2Main = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_EMPLOYER_HOME_TITLE_2_MAIN);
        String employerHomeTitle3 = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_EMPLOYER_HOME_TITLE_3);
        String employerHomeTitle3Main = MessageManager.getMessage(language.toString(),
                MessageConstant.TAG_EMPLOYER_HOME_TITLE_3_MAIN);

        out.write("<main role=\"main\">\n" +
                "\n" +
                "        <!-- Main jumbotron for a primary marketing message or call to action -->\n" +
                "        <div class=\"jumbotron\">\n" +
                "            <div class=\"container\">\n" +
                "                <h1 class=\"display-3\">" + hello + " " + login + "!\n" +
                "                </h1>\n" +
                "                <p>" + homeMainTitle + "</p>\n" +
                "                <h6 class=\"form-signin-heading error\">" + error + "</h6>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"container\">\n" +
                "            <!-- Example row of columns -->\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-md-4\">\n" +
                "                    <h2>" + employerHomeTitle1 + "</h2>\n" +
                "                    <p>" + employerHomeTitle1Main + "</p>\n" +
                "                </div>\n" +
                "                <div class=\"col-md-4\">\n" +
                "                    <h2>" + employerHomeTitle2 + "</h2>\n" +
                "                    <p>" + employerHomeTitle2Main + "</p>\n" +
                "                </div>\n" +
                "                <div class=\"col-md-4\">\n" +
                "                    <h2>" + employerHomeTitle3 + "</h2>\n" +
                "                    <p>" + employerHomeTitle3Main + "</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <hr>\n" +
                "\n" +
                "        </div>\n" +
                "\n" +
                "    </main>");
    }
}
