package team.redrock.downloadtool.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import team.redrock.downloadtool.utils.Response;

import javax.servlet.http.HttpServletRequest;


public interface FileService {
    Response fileUpload(String filepath, HttpServletRequest request);
    Response fileUpload(String filename,String filepath,String suffix, String remark, String username);
    Response fileDelete(String filename);
    Response deleteFileByFileid(Integer fileId);
    Response fileSelect(String filename, String username);
    Response fileList();
    boolean isFileExist(String filename, String username);
    Response AudioList();
    Response VedioList();
    Response TextList();
    Response TorrentList();
    void savePic(String filename,String suffix);
    JSONObject getListByUser(Integer current, Integer rowCount, HttpServletRequest request);
    JSONObject getVideoListByUser(Integer current, Integer rowCount, HttpServletRequest request);
    JSONObject getMusicListByUser(Integer current, Integer rowCount, HttpServletRequest request);
    JSONObject getTextByUser(Integer current, Integer rowCount, HttpServletRequest request);
    JSONObject getTorrentByUser(Integer current, Integer rowCount, HttpServletRequest request);
    JSONObject getOtherByUser(Integer current, Integer rowCount, HttpServletRequest request);
    Response getFileByFileid(Integer fileid);
    JSONObject getFileTypeNumber(HttpServletRequest request);
    JSONObject getLastSevenDaysFileNumber(HttpServletRequest request);

}
