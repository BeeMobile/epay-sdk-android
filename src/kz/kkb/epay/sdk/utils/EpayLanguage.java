package kz.kkb.epay.sdk.utils;

/**
 * epay-sdk-android
 * Created by http://beemobile.kz on 3/29/15 4:37 PM.
 */
public enum EpayLanguage {
    RUSSIAN ("rus"),
    KAZAKH ("kaz"),
    ENGLISH ("eng");

    private String lang;

    private EpayLanguage(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return lang;
    }
}
