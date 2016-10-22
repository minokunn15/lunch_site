package vo;
/**
 * user情報に関するVo
 */
public class UserVo implements java.io.Serializable{
	private static final long serialVersionUID = -5137407898783534871L;
	private String userId;
	private String passwd;
	private String mailAdress;
	private String nickName;
	private String area;
	private int userFlag;
	private String userImg;


	public UserVo(){
		super();
	}

	/**
	 * @param userId
	 * @param passwd
	 * @param mailAdress
	 * @param nickName
	 * @param area
	 * @param userFlag
	 * @param userImg
	 */
	public UserVo(String userId, String passwd, String mailAdress, String nickName, String area, int userFlag,
			String userImg) {
		super();
		setUserId(userId);
		setPasswd(passwd);
		setMailAdress(mailAdress);
		setNickName(nickName);
		setArea(area);
		setUserFlag(userFlag);
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		if(userId!=null&&!userId.equals("")){
			this.userId = userId;
		}else{
			this.userId = "000000";
		}
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		if (passwd!=null&&!passwd.equals("")) {
			this.passwd = passwd;
		} else {
			System.err.println("パスワードが空です。");
		}
	}
	public String getMailAdress() {
		return mailAdress;
	}
	public void setMailAdress(String mailAdress) {
		if (mailAdress!=null&&!mailAdress.equals("")) {
			this.mailAdress = mailAdress;
		} else {
			System.err.println("メールアドレスが空です。");
		}
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		if (nickName!=null&&!nickName.equals("")) {
			this.nickName = nickName;
		} else {
			System.err.println("ニックネームが空です。");
		}
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getUserFlag() {
		return userFlag;
	}
	public void setUserFlag(int userFlag) {
		if (userFlag==0 || userFlag==1) {
			this.userFlag = userFlag;
		} else {
			System.err.println("userFlagに予期せぬ値が入っています。");
		}
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}


}
