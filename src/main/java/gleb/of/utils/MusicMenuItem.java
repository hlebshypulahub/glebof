package gleb.of.utils;

public enum MusicMenuItem {
    RAIN("Дождь за окном ☔", "music_rain"),
    FLUTE("Бамбуковая флейта \uD83C\uDF8B", "music_flute"),
    FIRE("Костёр у реки \uD83D\uDD25", "music_fire"),
    MUSICOCEAN("Океан музыки \uD83C\uDF0A", "music_musicocean"),
    JAZZ("Ночной джаз \uD83C\uDFB7", "music_jazz"),
    TROPIC("Тропический лес \uD83E\uDD8B", "music_tropic"),
    PIANO("Вечерний рояль \uD83C\uDFB9", "music_piano"),
    MOON("Полёт на Луну \uD83C\uDF16", "music_moon");

    public static final String MUSIC_BUTTON_CALLBACK_STRING = "music_";

    public final String buttonText;
    public final String callbackDataString;

    MusicMenuItem(String buttonText, String callbackDataString) {
        this.buttonText = buttonText;
        this.callbackDataString = callbackDataString;
    }
}
