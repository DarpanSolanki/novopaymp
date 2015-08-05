package in.novopay.darpan.mymusicplayer.models;

/**
 * Created by darpansolanki on 8/4/15.
 */
public class Music {

    private String songName;
    private String artistName;
    private String albumName;

    public Music(String albumName, String artistName, String songName) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.songName = songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
