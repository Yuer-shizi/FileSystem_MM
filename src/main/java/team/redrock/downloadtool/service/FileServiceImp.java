package team.redrock.downloadtool.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.redrock.downloadtool.entity.FileInf;
import team.redrock.downloadtool.entity.Picture;
import team.redrock.downloadtool.jpa.FileInfJPA;
import team.redrock.downloadtool.jpa.PictureJPA;
import team.redrock.downloadtool.utils.Response;
import team.redrock.downloadtool.utils.Utility;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileServiceImp implements FileService {
    @Autowired
    FileInfJPA fileInfJPA;

    @Autowired
    PictureJPA pictureJPA;

   @Override
    public Response fileUpload(String filepath,HttpServletRequest request) {
        HttpSession session = request.getSession();
        String filename = Utility.getFileName(filepath);
        String suffix = Utility.getsuffix(filename);

        FileInf fileInf = new FileInf();
        fileInf.setFilename(filename);
        fileInf.setFilepath(filepath);
        fileInf.setSuffix(suffix);
        fileInf.setTime(new Date());
        fileInf.setUsername((String) session.getAttribute("user_session"));
        fileInfJPA.save(fileInf);


        return new Response("0", JSON.toJSONString(fileInf));
    }

    public Response fileUpload(String filename,String filepath,String suffix, String remark, String username){
        FileInf fileInf = new FileInf();
        fileInf.setFilename(filename);
        fileInf.setFilepath(filepath);
        fileInf.setSuffix(suffix);
        fileInf.setTime(new Date());
        fileInf.setUsername(username);
        fileInf.setFoldername("ROOT");
        fileInf.setRemark(remark);
        fileInfJPA.save(fileInf);
//        Cookie[] cookies = request.getCookies();
//        String token = "";
//        for (Cookie cookie : cookies) {
//            switch(cookie.getName()){
//                case "token":
//                    token = cookie.getValue();
//                    break;
//                default:
//                    break;
//            }
//        }
//        System.out.println("tiken-"+token);
        return new Response("0", JSON.toJSONString(fileInf));
    }

    @Override
    public Response fileDelete(String filename) {

        fileInfJPA.DeleteFile(filename);
        return new Response("0", JSON.toJSONString("文件删除成功"));
    }

    @Transactional
    @Override
    public Response deleteFileByFileid(Integer fileId){
        fileInfJPA.deleteFileByFileid(fileId);
        return new Response("0", JSON.toJSONString("文件删除成功"));
    }

    @Override
    public Response fileSelect(String filename, String username) {
        FileInf fileInf = new FileInf();
        fileInf = fileInfJPA.findByFilenameAndUsername(filename, username);
        if(fileInf==null) {
            return new Response("-1","文件不存在");
        }
        return new Response("0",JSON.toJSONString(fileInf));
    }

    @Override
    public Response fileList() {
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.FindAllById();
        return new Response("0", JSON.toJSONString(fileInfs));

    }

    @Override
    public JSONObject getListByUser(Integer current, Integer rowCount, HttpServletRequest request) {
        String username = request.getSession().getAttribute("user").toString();
        JSONObject jsonObject = new JSONObject();
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.GetAllByUsernameAndStartRowAndSize(username, current - 1, rowCount);
        jsonObject.put("current", current);
        jsonObject.put("rowCount", rowCount);
        jsonObject.put("rows", fileInfs);
        jsonObject.put("total", fileInfs.size());
        return jsonObject;
    }
    @Override
    public JSONObject getVideoListByUser(Integer current, Integer rowCount, HttpServletRequest request){
        String username = request.getSession().getAttribute("user").toString();
        JSONObject jsonObject = new JSONObject();
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.GetVideoListByUsernameAndAndStartRowAndSize(username, current - 1, rowCount);
        jsonObject.put("current", current);
        jsonObject.put("rowCount", rowCount);
        jsonObject.put("rows", fileInfs);
        jsonObject.put("total", fileInfs.size());
        return jsonObject;
    }

    @Override
    public JSONObject getMusicListByUser(Integer current, Integer rowCount, HttpServletRequest request){
        String username = request.getSession().getAttribute("user").toString();
        JSONObject jsonObject = new JSONObject();
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.GetMusicListByUsernameAndAndStartRowAndSize(username, current - 1, rowCount);
        jsonObject.put("current", current);
        jsonObject.put("rowCount", rowCount);
        jsonObject.put("rows", fileInfs);
        jsonObject.put("total", fileInfs.size());
        return jsonObject;
    }

    @Override
    public JSONObject getTextByUser(Integer current, Integer rowCount, HttpServletRequest request){
        String username = request.getSession().getAttribute("user").toString();
        JSONObject jsonObject = new JSONObject();
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.GetTextListByUsernameAndAndStartRowAndSize(username, current - 1, rowCount);
        jsonObject.put("current", current);
        jsonObject.put("rowCount", rowCount);
        jsonObject.put("rows", fileInfs);
        jsonObject.put("total", fileInfs.size());
        return jsonObject;
    }

    @Override
    public JSONObject getTorrentByUser(Integer current, Integer rowCount, HttpServletRequest request){
        String username = request.getSession().getAttribute("user").toString();
        JSONObject jsonObject = new JSONObject();
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.GetTorrentListByUsernameAndAndStartRowAndSize(username, current - 1, rowCount);
        jsonObject.put("current", current);
        jsonObject.put("rowCount", rowCount);
        jsonObject.put("rows", fileInfs);
        jsonObject.put("total", fileInfs.size());
        return jsonObject;
    }

    @Override
    public JSONObject getOtherByUser(Integer current, Integer rowCount, HttpServletRequest request){
        String username = request.getSession().getAttribute("user").toString();
        JSONObject jsonObject = new JSONObject();
        List<FileInf> fileInfs;
        fileInfs = fileInfJPA.GetOtherListByUsernameAndAndStartRowAndSize(username, current - 1, rowCount);
        jsonObject.put("current", current);
        jsonObject.put("rowCount", rowCount);
        jsonObject.put("rows", fileInfs);
        jsonObject.put("total", fileInfs.size());
        return jsonObject;
    }

    @Override
    public Response getFileByFileid(Integer fileid){
        FileInf fileInf = fileInfJPA.findByFileid(fileid);
        return new Response("0", JSON.toJSONString(fileInf));
    }

    @Override
    public JSONObject getFileTypeNumber(HttpServletRequest request){
        String username = request.getSession().getAttribute("user").toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("video", fileInfJPA.GetVideoNumberByUsername(username));
        jsonObject.put("music", fileInfJPA.GetMusicNumberByUsername(username));
        jsonObject.put("text", fileInfJPA.GetTextNumberByUsername(username));
        jsonObject.put("torrent", fileInfJPA.GetTorrentNumberByUsername(username));
        jsonObject.put("other", fileInfJPA.GetOtherNumberByUsername(username));
        return jsonObject;
    }

    @Override
    public JSONObject getLastSevenDaysFileNumber(HttpServletRequest request){
        String username = request.getSession().getAttribute("user").toString();
        JSONObject jsonObject = new JSONObject();
        List<String> daysList = GetPastDaysList(8);
        int i = 0;
        for (String s : daysList) {
            Integer number = fileInfJPA.GetFileNumberByUsernameAndDate(s, username);
            jsonObject.put("last"+ i, number);
            i++;
        }
        return jsonObject;
    }

    public boolean isFileExist(String filename, String username){
        return fileInfJPA.findByFilenameAndUsername(filename, username) != null ? true : false;
    }

    @Override
    public Response AudioList() {
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.SelectAudio();
        return new Response("0",JSON.toJSONString(fileInfs));
    }

    @Override
    public Response VedioList() {
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.SelectVideo();
        return new Response("0",JSON.toJSONString(fileInfs));
    }

    @Override
    public Response TextList() {
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.SelectText();
        return new Response("0",JSON.toJSONString(fileInfs));
    }

    @Override
    public Response TorrentList() {
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.SelectTorrent();
        return new Response("0",JSON.toJSONString(fileInfs));
    }

    public void savePic(String filename,String suffix){
       String path = "D:\\temp\\";
        Picture picture = new Picture();
        picture.setPicname(filename);
        if(Utility.isVedio(suffix).equals("vedio")){
            picture.setPicpath(path+filename+".jpg");
        }
        if(Utility.isVedio(suffix).equals("audio")){
            picture.setPicpath(path+"audio.jpg");
        }
        if(Utility.isVedio(suffix).equals("text")){
            picture.setPicpath(path+"text.jpg");
        }
        if(Utility.isVedio(suffix).equals("torrent")){
            picture.setPicpath(path+"torrent.jpg");
        }
        if(Utility.isVedio(suffix).equals("other")){
            picture.setPicpath(path+"other.jpg");
        }
    }

    public static List<String> GetPastDaysList(int intervals) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = 0; i <intervals; i++) {
            pastDaysList.add(getPastDate(i));
        }
        return pastDaysList;
    }

    /**
     * 获取过去第几天的日期
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

}

