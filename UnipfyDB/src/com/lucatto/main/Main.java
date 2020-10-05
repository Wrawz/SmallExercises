package com.lucatto.main;

import com.lucatto.model.Artist;
import com.lucatto.model.DataSource;
import com.lucatto.model.Song;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        DataSource.getInstance().open();
        DataSource.getInstance().insertIntoSongs("Black Night", "3:26", "Deep Purple", "rock");
        DataSource.getInstance().insertIntoArtists("Eric Clapton");
        List<Song> songs = DataSource.getInstance().querySongs();
        List<Artist> artists = DataSource.getInstance().queryArtists();
        DataSource.getInstance().searchForSong("welcome to the jungle");
        DataSource.getInstance().searchForSong("What It Takes");
        DataSource.getInstance().close();
        int i = 0;
        for(Song song : songs) System.out.println(++i + ". " + song.getTitle());
        int j = 0;
        for(Artist artist : artists) System.out.println(++j + ". " + artist.getArtistName());
    }
}
