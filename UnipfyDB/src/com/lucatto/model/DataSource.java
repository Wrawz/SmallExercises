package com.lucatto.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.lucatto.main.Color.*;

public class DataSource {
    public static final String CONNECTION_STRING = "jdbc:mysql://127.0.0.1:3306/";
    public static final String DB_NAME = "unipfy";
    public static final String TIME_ZONE_CORRECTION = "?useTimezone=true&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "**********";

    public static final String TABLE_SONG = "musica_01";
    public static final String TABLE_SONG_COLUMN_SONG_ID = "id_01";
    public static final String TABLE_SONG_COLUMN_SONG_NAME = "nome_01";
    public static final String TABLE_SONG_COLUMN_SONG_TIME_LENGTH = "duracao_01";
    public static final String TABLE_SONG_COLUMN_ARTIST_ID = "id_02";
    public static final String TABLE_SONG_COLUMN_SONG_GENRE = "genero_01";

    public static final String TABLE_ARTIST = "artista_02";
    public static final String TABLE_ARTIST_COLUMN_ARTIST_ID = "id_02";
    public static final String TABLE_ARTIST_COLUMN_ARTIST_NAME = "nome_02";

    public static final String QUERY_SONGS = "SELECT * FROM " + TABLE_SONG;
    public static final String QUERY_ARTISTS = "SELECT * FROM " + TABLE_ARTIST;
    public static final String QUERY_SONG_ID_BY_NAME = "SELECT " + TABLE_SONG_COLUMN_SONG_ID + " FROM " + DB_NAME + "." + TABLE_SONG + " WHERE " + TABLE_SONG_COLUMN_SONG_NAME + " = ?";
    public static final String QUERY_ARTIST_ID_BY_NAME = "SELECT " + TABLE_ARTIST_COLUMN_ARTIST_ID + " FROM " + DB_NAME + "." + TABLE_ARTIST + " WHERE " + TABLE_ARTIST_COLUMN_ARTIST_NAME + " = ?";
    public static final String INSERT_INTO_SONGS = "INSERT INTO `" + DB_NAME + "`.`" + TABLE_SONG + "` (`" + TABLE_SONG_COLUMN_SONG_NAME + "`, `" + TABLE_SONG_COLUMN_SONG_TIME_LENGTH + "`, `" + TABLE_SONG_COLUMN_ARTIST_ID + "`, `" + TABLE_SONG_COLUMN_SONG_GENRE + "`) VALUES (?, ?, ?, ?);";
    public static final String INSERT_INTO_ARTISTS = "INSERT INTO `" + DB_NAME + "`.`" + TABLE_ARTIST + "` (`" + TABLE_ARTIST_COLUMN_ARTIST_NAME + "`) VALUES (?);";

    private static Connection connection;
    private static PreparedStatement querySongsPrep;
    private static PreparedStatement queryArtistsPrep;
    private static PreparedStatement querySongIdByName;
    private static PreparedStatement queryArtistIdByName;
    private static PreparedStatement insertIntoSongsPrep;
    private static PreparedStatement insertIntoArtistsPrep;

    private DataSource() {
    }

    public static DataSource getInstance() {
        return new DataSource();
    }

    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME + TIME_ZONE_CORRECTION, USER, PASSWORD);
            querySongsPrep = connection.prepareStatement(QUERY_SONGS);
            queryArtistsPrep = connection.prepareStatement(QUERY_ARTISTS);
            insertIntoSongsPrep = connection.prepareStatement(INSERT_INTO_SONGS);
            insertIntoArtistsPrep = connection.prepareStatement(INSERT_INTO_ARTISTS, Statement.RETURN_GENERATED_KEYS);
            querySongIdByName = connection.prepareStatement(QUERY_SONG_ID_BY_NAME);
            queryArtistIdByName = connection.prepareStatement(QUERY_ARTIST_ID_BY_NAME);
            System.out.println(ANSI_GREEN + "Connection is open." + ANSI_RESET);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to the database: " + e.getMessage());
        }
        return false;
    }

    public void close() {
        try {
            if (querySongIdByName != null) querySongIdByName.close();
            if (queryArtistIdByName != null) queryArtistIdByName.close();
            if (insertIntoSongsPrep != null) insertIntoSongsPrep.close();
            if (insertIntoArtistsPrep != null) insertIntoArtistsPrep.close();
            if (queryArtistsPrep != null) queryArtistsPrep.close();
            if (querySongsPrep != null) querySongsPrep.close();
            if (connection != null) connection.close();
            System.out.println(ANSI_PURPLE + "Connection is closed." + ANSI_RESET);
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public List<Song> querySongs() {
        try {
            ResultSet resultSet = querySongsPrep.executeQuery();
            List<Song> songs = new ArrayList<>();
            while (resultSet.next()) {
                Song song = new Song();
                song.setId(resultSet.getInt(1));
                song.setTitle(resultSet.getString(2));
                song.setTimeLength(resultSet.getString(3));
                song.setGenre(resultSet.getString(4));
                songs.add(song);
            }
            resultSet.close();
            return songs;
        } catch (SQLException e) {
            System.out.println("Couldn't query any songs: " + e.getMessage());
        }
        return null;
    }

    public List<Artist> queryArtists() {
        try {
            ResultSet resultSet = queryArtistsPrep.executeQuery();
            List<Artist> artists = new ArrayList<>();
            while (resultSet.next()) {
                Artist artist = new Artist();
                artist.setId(resultSet.getInt(1));
                artist.setArtistName(resultSet.getString(2));
                artists.add(artist);
            }
            resultSet.close();
            return artists;
        } catch (SQLException e) {
            System.out.println("Couldn't query any artists: " + e.getMessage());
        }
        return null;
    }

    public int insertIntoArtists(String artistName) throws SQLException {
        queryArtistIdByName.setString(1, artistName);
        ResultSet resultSet = queryArtistIdByName.executeQuery();
        if (resultSet.next()) {
            System.out.println("Artist \"" + artistName + "\" is already in the DB.");
            return resultSet.getInt(1);
        }
        insertIntoArtistsPrep.setString(1, artistName);
        int affectedRows = insertIntoArtistsPrep.executeUpdate();
        if (affectedRows != 1)
            throw new SQLException("Artist \"" + artistName + "\" has not been inserted successfully.");
        ResultSet resultSet2 = insertIntoArtistsPrep.getGeneratedKeys();
        if (resultSet2.next())
            return resultSet2.getInt(1);
        else
            throw new SQLException("Couldn't find new artist id.");
    }

    public void insertIntoSongs(String songName, String songTimeLength, String artistName, String genre) throws SQLException {
        querySongIdByName.setString(1, songName);
        ResultSet resultSet = querySongIdByName.executeQuery();
        if (resultSet.next()) {
            System.out.println("Song \"" + songName + "\" is already in the DB.");
            return;
        }
        try {
            connection.setAutoCommit(false);
            int artistId = insertIntoArtists(artistName);
            insertIntoSongsPrep.setString(1, songName);
            insertIntoSongsPrep.setString(2, songTimeLength);
            insertIntoSongsPrep.setInt(3, artistId);
            insertIntoSongsPrep.setString(4, genre);
            int affectedRows = insertIntoSongsPrep.executeUpdate();
            if (affectedRows == 1)
                connection.commit();
            else
                throw new SQLException("Connection commit is still off.");
        } catch (SQLException e) {
            System.out.println("Song \"" + songName + "\" has not been inserted successfully: " + e.getMessage());
            try {
                System.out.println("Executing rollback...");
                connection.rollback();
            } catch (SQLException e2) {
                System.out.println("Couldn't execute rollback: " + e2.getMessage());
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                System.out.println("Connection autoCommit has been reset to true.");
            } catch (SQLException e) {
                System.out.println("Couldn't reset autoCommit to true: " + e.getMessage());
            }
        }
    }

    public void searchForSong(String songTitle) {
        try {
            ResultSet resultSet = querySongsPrep.executeQuery();
            List<Song> songs = new ArrayList<>();
            while (resultSet.next()) {
                Song song = new Song();
//                song.setId(resultSet.getInt(1));
                song.setTitle(resultSet.getString(2));
//                song.setTimeLength(resultSet.getString(3));
//                song.setGenre(resultSet.getString(4));
                songs.add(song);
            }
            resultSet.close();
            Collections.sort(songs);
            Song song = new Song();
            song.setTitle(songTitle);
            int foundSong = Collections.binarySearch(songs, song, null);
            System.out.println("foundSong integer: " + foundSong);
            if (foundSong >= 0)
                System.out.println("Song \"" + songTitle + "\" is in the DB.");
             else
                System.out.println("Song \"" + songTitle + "\" not found in the DB.");
        } catch (SQLException e) {
            System.out.println("Couldn't search for song: " + e.getMessage());
        }
    }

    // brute force search below:
//    public void searchForSong(String songTitle) {
//        try {
//            ResultSet resultSet = querySongsPrep.executeQuery();
//            List<Song> songs = new ArrayList<>();
//            while (resultSet.next()) {
//                Song song = new Song();
////                song.setId(resultSet.getInt(1));
//                song.setTitle(resultSet.getString(2));
////                song.setTimeLength(resultSet.getString(3));
////                song.setGenre(resultSet.getString(4));
//                songs.add(song);
//            }
//            resultSet.close();
//            for (Song song : songs) {
//                if (song.getTitle().equals(songTitle)) {
//                    System.out.println("The song \""+songTitle+"\" has been found.");
//                    return;
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Couldn't search for song: " + e.getMessage());
//        }
//    }
}
