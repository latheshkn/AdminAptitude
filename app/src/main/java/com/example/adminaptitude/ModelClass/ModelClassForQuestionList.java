package com.example.adminaptitude.ModelClass;

public class ModelClassForQuestionList {
    String option_a;
    String option_b;
    String option_c;
    String option_d;
    String question;

    public ModelClassForQuestionList(String option_a, String option_b, String option_c, String option_d, String question) {
        this.option_a = option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.option_d = option_d;
        this.question = question;
    }
    public ModelClassForQuestionList() {

    }
    public String getOption_a() {
        return option_a;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public String getOption_d() {
        return option_d;
    }

    public void setOption_d(String option_d) {
        this.option_d = option_d;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
