package logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NewsDao;
import exception.FileException;
import vo.NewsVo;

/**
 * @author 01014554
 * 新しい記事を追加完了する
 * ロジックのクラス
 */
public class AddNewsComplete extends BaseLogic{

	private int InsertNewNewsResult(String title,String newsImg,String texts,String storeId) throws NamingException, SQLException, FileException {
		int result = 0;
		NewsDao dao = new NewsDao();
		result = dao.insertNewNews(title, newsImg, texts, storeId);
		return result;
	}

	@Override
	public boolean doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException, NamingException, FileException {
		boolean result = true;
		HttpSession session = req.getSession();
		//トークン取得//
		String tokenPara = req.getParameter("token");
		String sessionToken = (String)session.getAttribute("token");
		String fileName = (String)session.getAttribute("fileName");
		if (tokenPara.equals(sessionToken)) {
			String imagePath = req.getServletContext().getRealPath("images");
			System.out.println(imagePath);
			NewsVo newNews = (NewsVo)session.getAttribute("newNews");
			String title = newNews.getTitle();
			String newsImg = newNews.getNewsImg().replace("tmp","images");
			String texts = newNews.getTexts();
			String storeId = newNews.getStoreId();
			try {
				 FileInputStream fis = new FileInputStream(req.getServletContext().getRealPath("tmp")+"/"+fileName);
				 FileOutputStream fos = new FileOutputStream(imagePath+"/"+fileName);
				 byte buf[] = new byte[256];
				 int len;
		         while ((len = fis.read(buf)) != -1) {
		              fos.write(buf, 0, len);
		         }
		         //終了処理
		         fos.flush();
		         fos.close();
		         fis.close();
		         System.out.println("コピーが完了しました。");
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
				throw new FileException("ファイルのアップロードに失敗しました");
			}
			int insertResult = this.InsertNewNewsResult(title, newsImg, texts, storeId);
			if (insertResult==0) {
				result = false;
				throw new FileException("記事の追加に失敗しました");
			}
			session.removeAttribute("token");
		} else {
			result = false;
			throw new FileException("既にこの記事はアップロードされています");
		}
		return result;
	}
}
