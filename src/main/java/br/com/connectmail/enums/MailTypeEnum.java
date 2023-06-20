package br.com.connectmail.enums;

public enum MailTypeEnum {

    CONFIRM_ACCOUNT("confirm-account.html"),
    CONFIRM_CONDOMINIUM_EMAIL("confirm-condominium-email.html");

    private final String template;

    MailTypeEnum(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }
}
