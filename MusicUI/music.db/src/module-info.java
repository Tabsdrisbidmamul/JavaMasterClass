module music.db {
    requires java.sql;
    requires sqlite.jdbc;
    requires transitive music.core;

    exports music.db to music.ui;
}