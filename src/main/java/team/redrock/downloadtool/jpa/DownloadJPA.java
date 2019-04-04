package team.redrock.downloadtool.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import team.redrock.downloadtool.entity.Download;

import java.io.Serializable;

public interface DownloadJPA extends JpaRepository<Download, Long>, JpaSpecificationExecutor<Download>, Serializable {

}
