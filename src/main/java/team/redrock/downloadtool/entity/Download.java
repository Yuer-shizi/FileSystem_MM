package team.redrock.downloadtool.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "download")
public class Download {
    @Id
    @GeneratedValue
    @Column(name = "d_id")
    private Long downloadid;

    private String username;

    @CreatedDate
    @Column(name = "d_time")
    private Date time;// 下载时间

    public Download(String username) {
        this.username = username;
    }
}
