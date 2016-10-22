package logic;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



import exception.FileException;
import security.SecureTokenManager;
import vo.NewsVo;

/**
 * @author 01014554
 * 新しい記事を登録するか確認画面を表示する
 * ロジックを構築するクラス
 */
public class AddNewsComfirm extends BaseLogic{



	@Override
	public boolean doProcess(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		res.setContentType("text/html;charset=UTF-8");
		boolean result = true;
		HttpSession session = req.getSession();
		//ファイルのアップロードに関する処理//
		String imagePath = req.getServletContext().getRealPath("tmp");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		List<FileItem> list = sfu.parseRequest(req);
		Iterator<FileItem> iterator = list.iterator();
		String fileName = null;
		NewsVo newNews = new NewsVo();

		while (iterator.hasNext()) {
			FileItem item = iterator.next();

			if (!item.isFormField()) {
				fileName = item.getName();
				if ((fileName != null) && (!fileName.equals(""))) {
					String userId = (String)session.getAttribute("userId");
					fileName = (new File(fileName)).getName();
					System.out.println(System.currentTimeMillis());
					System.out.println(fileName);
					//拡張子チェック//
					String[] extension = fileName.split("\\.", 0);
					if (extension[extension.length-1].equals("jpg")||extension[extension.length-1].equals("jpeg")||extension[extension.length-1].equals("gif")||
							extension[extension.length-1].equals("png")) {
						//ファイル名変換//
						fileName = userId+System.currentTimeMillis()+"."+extension[extension.length-1];
						item.write(new File(imagePath +"/"+ fileName));
					} else {
						System.out.println("画像ファイルではありません");
						result = false;
						throw new FileException("画像ファイルではありません");
					}
				} else {
					System.out.println("ファイルがアップロードされていません");
					result = false;
					throw new FileException("ファイルがアップロードされていません");
				}
			} else {
				String name = item.getFieldName();
				if (name.equals("title")) {
					newNews.setTitle(item.getString("UTF-8"));
				}
				if (name.equals("store")) {
					newNews.setStoreId(item.getString("UTF-8"));
				}
				if (name.equals("text")) {
					newNews.setTexts(item.getString("UTF-8"));
				}
			}
		}
		//ないのがあったらOUT//
		if (newNews.getTitle()==null || newNews.getStoreId()==null || newNews.getTexts()==null ||newNews.getTitle().equals("")||newNews.getStoreId().equals("")||newNews.getTexts().equals("")) {
			System.out.println("記事に必要な情報が足りていません");
			result = false;
			throw new FileException("記事に必要な情報が足りていません");
		}
		if (newNews.getTitle().length() >=90 || newNews.getTexts().length() >=1490 ) {
			result = false;
			throw new FileException("タイトルか本文のサイズが長すぎます");
		}
		newNews.setNewsImg("/tyoushoku/tmp/"+ fileName);
		session.setAttribute("newNews",newNews);
		session.setAttribute("fileName",fileName);
		//最後の画面で更新押されたときに何度も追加させないための処理（トークン）//
		String token=null;
		try {
			token = SecureTokenManager.getCsrfToken();
		} catch (NoSuchAlgorithmException e) {
			System.err.println("トークン生成のエラー");
			throw new ServletException(e);
		}
		if(token!=null){
			session.setAttribute("token", token);
		}
		return result;
	}
}
