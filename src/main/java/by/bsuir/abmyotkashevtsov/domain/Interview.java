package by.bsuir.abmyotkashevtsov.domain;

public class Interview extends AbstractDomain {
    private int interviewId;
    private int candidateId;
    private int vacancyId;
    private String preResult;
    private String finalResult;
    private String candidateInfo;
    private String vacancyInfo;
    private String employerInfo;

    public Interview() {
    }

    public Interview(int candidateId, int vacancyId) {
        this.candidateId = candidateId;
        this.vacancyId = vacancyId;
    }

    public Interview(int interviewId, String preResult, String finalResult) {
        this.interviewId = interviewId;
        this.preResult = preResult;
        this.finalResult = finalResult;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getPreResult() {
        return preResult;
    }

    public void setPreResult(String preResult) {
        this.preResult = preResult;
    }

    public String getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    public String getCandidateInfo() {
        return candidateInfo;
    }

    public void setCandidateInfo(String candidateInfo) {
        this.candidateInfo = candidateInfo;
    }

    public String getVacancyInfo() {
        return vacancyInfo;
    }

    public void setVacancyInfo(String vacancyInfo) {
        this.vacancyInfo = vacancyInfo;
    }

    public String getEmployerInfo() {
        return employerInfo;
    }

    public void setEmployerInfo(String employerInfo) {
        this.employerInfo = employerInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Interview interview = (Interview) o;

        return interviewId == interview.interviewId && candidateId == interview.candidateId
                && vacancyId == interview.vacancyId
                && (preResult != null ? preResult.equals(interview.preResult) : interview.preResult == null)
                && (finalResult != null ? finalResult.equals(interview.finalResult) : interview.finalResult == null)
                && (candidateInfo != null ? candidateInfo.equals(interview.candidateInfo) :
                    interview.candidateInfo == null)
                && (vacancyInfo != null ? vacancyInfo.equals(interview.vacancyInfo) : interview.vacancyInfo == null)
                && (employerInfo != null ? employerInfo.equals(interview.employerInfo) :
                    interview.employerInfo == null);
    }

    @Override
    public int hashCode() {
        int result = interviewId;
        result = 31 * result + candidateId;
        result = 31 * result + vacancyId;
        result = 31 * result + (preResult != null ? preResult.hashCode() : 0);
        result = 31 * result + (finalResult != null ? finalResult.hashCode() : 0);
        result = 31 * result + (candidateInfo != null ? candidateInfo.hashCode() : 0);
        result = 31 * result + (vacancyInfo != null ? vacancyInfo.hashCode() : 0);
        result = 31 * result + (employerInfo != null ? employerInfo.hashCode() : 0);
        return result;
    }
}
