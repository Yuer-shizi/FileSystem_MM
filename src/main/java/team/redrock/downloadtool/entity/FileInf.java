package team.redrock.downloadtool.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "fileinf")
public class FileInf {
    @Id
    @GeneratedValue
    @Column(name = "f_id")
    private Long fileid;

    @Column(name = "f_name")
    private String filename;// 文件名

    @Column(name = "f_path")
    private String filepath;// 存储的文件

    @Column(name = "f_suffix")
    private String suffix;// 文件后缀

    @Column(name = "f_time")
    private Date time;// 存储时间

    @Column(name = "username")
    private String username;// 存储的用户名

    @Column(name = "foldername")
    private String foldername;

    @Column(name = "remark")
    private String remark;// 备注

}
