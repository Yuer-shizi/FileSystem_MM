package team.redrock.downloadtool.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.redrock.downloadtool.service.FileService;
import team.redrock.downloadtool.service.FolderService;
import team.redrock.downloadtool.utils.Response;
import team.redrock.downloadtool.utils.Utility;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileController {
    @Autowired
    FileService fileService;
    @Autowired
    FolderService folderService;

    @GetMapping("/list")
    @ResponseBody
    public Response fileList(){
        return  fileService.fileList();
    }


    @RequestMapping("/get-list-by-user")
    @ResponseBody
    public JSONObject getListByUser(@RequestParam("current") Integer current, @RequestParam("rowCount") Integer rowCount, HttpServletRequest request) {
        return  fileService.getListByUser(current, rowCount, request);
    }

    @RequestMapping("/get-video-list-by-user")
    @ResponseBody
    public JSONObject getVideoListByUser(@RequestParam("current") Integer current, @RequestParam("rowCount") Integer rowCount, HttpServletRequest request) {
        return  fileService.getVideoListByUser(current, rowCount, request);
    }

    @RequestMapping("/get-music-list-by-user")
    @ResponseBody
    public JSONObject getMusicListByUser(@RequestParam("current") Integer current, @RequestParam("rowCount") Integer rowCount, HttpServletRequest request) {
        return  fileService.getMusicListByUser(current, rowCount, request);
    }

    @RequestMapping("/get-text-list-by-user")
    @ResponseBody
    public JSONObject getTextListByUser(@RequestParam("current") Integer current, @RequestParam("rowCount") Integer rowCount, HttpServletRequest request) {
        return  fileService.getTextByUser(current, rowCount, request);
    }

    @RequestMapping("/get-torrent-list-by-user")
    @ResponseBody
    public JSONObject getTorrentListByUser(@RequestParam("current") Integer current, @RequestParam("rowCount") Integer rowCount, HttpServletRequest request) {
        return  fileService.getTorrentByUser(current, rowCount, request);
    }

    @RequestMapping("/get-other-list-by-user")
    @ResponseBody
    public JSONObject getOtherListByUser(@RequestParam("current") Integer current, @RequestParam("rowCount") Integer rowCount, HttpServletRequest request) {
        return  fileService.getOtherByUser(current, rowCount, request);
    }

    @GetMapping("/get-file-by-fileid")
    @ResponseBody
    public Response getFileByFileid(@RequestParam("fileid") Integer fileid){
        return  fileService.getFileByFileid(fileid);
    }

    @GetMapping("/get-file-type-number")
    @ResponseBody
    public JSONObject getFileTypeNumber(HttpServletRequest request){
        return  fileService.getFileTypeNumber(request);
    }

    @GetMapping("/get-last-seven-days-file-number")
    @ResponseBody
    public JSONObject getLastSevenDaysFileNumber(HttpServletRequest request){
        return  fileService.getLastSevenDaysFileNumber(request);
    }


    @GetMapping("/rootlist")
    @ResponseBody
    public Response rootlist(){
        return folderService.RootList();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Response deletefile(@RequestParam("deletename") String filename){

        System.out.println("更改前："+filename);
          String  fname = Utility.getfn(filename);
        System.out.println("更改后:"+fname);
        return fileService.fileDelete(fname);
    }

    @PostMapping("/delete-file-by-fileid")
    @ResponseBody
    public Response deleteFileById(@RequestParam("fileid") Integer fileId) {
        return fileService.deleteFileByFileid(fileId);
    }

    @GetMapping("/audio")
    @ResponseBody
    public Response AudioList(){
        return  fileService.AudioList();
    }

    @GetMapping("/vedio")
    @ResponseBody
    public Response VedioList(){
        return  fileService.VedioList();
    }

    @GetMapping("/text")
    @ResponseBody
    public Response TextList(){
        return  fileService.TextList();
    }

    @GetMapping("/torrent")
    @ResponseBody
    public Response TorrentList(){
        return  fileService.TorrentList();
    }



}
