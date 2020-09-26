module music.core {
    requires javafx.base;

    exports music.core to music.db, music.ui;
    opens music.core to javafx.base;
}