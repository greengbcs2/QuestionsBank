package android.atilim.tr.questionsbank;

/**
 * Created by efraat on 08.12.2015.
 */
public class Questions {
    private String questionText;
    private String[] answers=new String[5];
    private int totalOfQuestions;
    private int idQuestion;
    private String rightAnswer;

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Questions(String questionText, int idQuestion, int totalOfQuestions, String[] answers,String rightAnswer) {
        this.questionText = questionText;
        this.idQuestion = idQuestion;
        this.totalOfQuestions = totalOfQuestions;
        this.answers = answers;

        this.rightAnswer=rightAnswer;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getTotalOfQuestions() {
        return totalOfQuestions;
    }

    public void setTotalOfQuestions(int totalOfQuestions) {
        this.totalOfQuestions = totalOfQuestions;
        setQuestionText("jkj");
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
