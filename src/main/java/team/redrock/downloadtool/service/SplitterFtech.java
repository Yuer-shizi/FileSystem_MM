package team.redrock.downloadtool.service;

import team.redrock.downloadtool.utils.Utility;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class SplitterFtech extends Thread {
	private long         nStartPos; //File Snippet Start Position

	private long         nEndPos; //File Snippet End Position

	private int          nThreadID; //Thread's ID

	public  boolean      bDownOver = false; //Downing is over

	private OutputStream out;

	private FileAccess   fileAccessI; //File Access interface

	public SplitterFtech(String sName, long nStart, long nEnd, int id, OutputStream out)
			throws IOException {
		this.nStartPos = nStart;
		this.nEndPos = nEnd;
		this.out = new BufferedOutputStream(out);
		nThreadID = id;
		fileAccessI = new FileAccess(sName, nStartPos);
	}

	public void run() {
		//Stop identical
		while (nStartPos < nEndPos) {
			try {
				byte[] b = new byte[4096];
				int nRead = 4096;
				while ((nEndPos - nStartPos + 1) >= 4096 && nStartPos < nEndPos)
					out.write(b, 0, nRead);                                    // 这里有问题
				nStartPos += fileAccessI.write(b, 0, nRead);

				if (nEndPos - nStartPos + 1 < 4096) {
					nRead = (int) (nEndPos - nStartPos + 1);
					if (nRead == 0) {
						System.out.println("nRead==0");
						break;
					}
				}
				Utility.log("Thread " + nThreadID + " is over!");
				bDownOver = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
