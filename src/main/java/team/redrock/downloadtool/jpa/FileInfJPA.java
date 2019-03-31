package team.redrock.downloadtool.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import team.redrock.downloadtool.entity.FileInf;




import java.io.Serializable;
import java.util.List;

public interface FileInfJPA extends JpaRepository<FileInf, Long>,
        JpaSpecificationExecutor<FileInf>,
        Serializable {

    @Query(value = "select * from fileinf order by f_id desc ",nativeQuery = true)
    public List<FileInf> FindAllById();

    @Query(value = "SELECT * FROM fileinf WHERE f_id = ?1 LIMIT 1",nativeQuery = true)
    public FileInf findByFileid(Integer fileid);

    @Query(value = "select * from fileinf order by conert(f_name using GBK) ",nativeQuery = true)
    public List<FileInf> FindAllByName();

    @Query(value = "SELECT * FROM fileinf WHERE username = ?1 ORDER BY f_time DESC LIMIT ?2, ?3", nativeQuery = true)
    public List<FileInf> GetAllByUsernameAndStartRowAndSize(String username, Integer startRow, Integer pageSize);

    @Query(value = "SELECT * FROM fileinf WHERE username = ?1 AND lower(f_suffix) in('.mp4','.avi','.mov','.wmv','.flv','.rmvb') ORDER BY f_time DESC LIMIT ?2, ?3", nativeQuery = true)
    public List<FileInf> GetVideoListByUsernameAndAndStartRowAndSize(String username, Integer startRow, Integer pageSize);

    @Query(value = "SELECT * FROM fileinf WHERE username = ?1 AND lower(f_suffix) in('.mp3','.wav','.flac','.aac') ORDER BY f_time DESC LIMIT ?2, ?3", nativeQuery = true)
    public List<FileInf> GetMusicListByUsernameAndAndStartRowAndSize(String username, Integer startRow, Integer pageSize);

    @Query(value = "SELECT * FROM fileinf WHERE username = ?1 AND lower(f_suffix) in('.txt','.doc','.md','.pdf','equb') ORDER BY f_time DESC LIMIT ?2, ?3", nativeQuery = true)
    public List<FileInf> GetTextListByUsernameAndAndStartRowAndSize(String username, Integer startRow, Integer pageSize);

    @Query(value = "SELECT * FROM fileinf WHERE username = ?1 AND lower(f_suffix) in('.torrent') ORDER BY f_time DESC LIMIT ?2, ?3", nativeQuery = true)
    public List<FileInf> GetTorrentListByUsernameAndAndStartRowAndSize(String username, Integer startRow, Integer pageSize);

    @Query(value = "SELECT * FROM fileinf WHERE username = ?1 AND lower(f_suffix) NOT IN('.mp4','.avi','.mov','.wmv','.flv','.rmvb','.mp3','.wav','.flac','.aac','.txt','.doc','.md','.pdf','equb','.torrent') ORDER BY f_time DESC LIMIT ?2, ?3", nativeQuery = true)
    public List<FileInf> GetOtherListByUsernameAndAndStartRowAndSize(String username, Integer startRow, Integer pageSize);

    @Query(value = "SELECT COUNT(*) FROM fileinf WHERE username = ?1 AND lower(f_suffix) in('.mp4','.avi','.mov','.wmv','.flv','.rmvb')", nativeQuery = true)
    public Integer GetVideoNumberByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM fileinf WHERE username = ?1 AND lower(f_suffix) in('.mp3','.wav','.flac','.aac')", nativeQuery = true)
    public Integer GetMusicNumberByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM fileinf WHERE username = ?1 AND lower(f_suffix) in('.txt','.doc','.md','.pdf','equb')", nativeQuery = true)
    public Integer GetTextNumberByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM fileinf WHERE username = ?1 AND lower(f_suffix) in('.torrent')", nativeQuery = true)
    public Integer GetTorrentNumberByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM fileinf WHERE username = ?1 AND lower(f_suffix) NOT IN('.mp4','.avi','.mov','.wmv','.flv','.rmvb','.mp3','.wav','.flac','.aac','.txt','.doc','.md','.pdf','equb','.torrent')", nativeQuery = true)
    public Integer GetOtherNumberByUsername(String username);


    @Query(value = "SELECT COUNT(*) FROM fileinf WHERE DATE(f_time) = ?1 AND username = ?2", nativeQuery = true)
    public Integer GetFileNumberByUsernameAndDate(String time, String username);

    @Query(value = "select * from fileinf  where f_name = ?1 AND username = ?2",nativeQuery = true)
    public FileInf findByFilenameAndUsername(String fileName, String username);

    @Query(value = "select * from fileinf  where f_path = ?1",nativeQuery = true)
    public FileInf SelectByFPath(String fpath);

    @Modifying
    @Query(value = "delete  from fileinf where f_name = ?1",nativeQuery = true)
    public void DeleteFile(String fname);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM fileinf where f_id = ?1")
    public void deleteFileByFileid(Integer fileId);

    @Query(value = "select * from fileinf where foldername = ?1 order by f_id desc",nativeQuery = true)
    public List<FileInf> getfilechildren(String foldername);

    @Query(value = "select * from fileinf where lower(f_suffix) in('.mp4','.avi','.mov','.wmv','.flv','.rmvb') order by f_id desc ",nativeQuery = true)
    public List<FileInf> SelectVideo();

    @Query(value = "select * from fileinf where lower(f_suffix) in('.mp3','.wav','.flac','.aac') order by f_id desc ",nativeQuery = true)
    public List<FileInf> SelectAudio();

    @Query(value = "select * from fileinf where lower(f_suffix) in('.txt','.doc','.md','.pdf','equb') order by f_id desc ",nativeQuery = true)
    public List<FileInf> SelectText();

    @Query(value = "select * from fileinf where lower(f_suffix) in('.torrent') order by f_id desc ",nativeQuery = true)
    public List<FileInf> SelectTorrent();


}
