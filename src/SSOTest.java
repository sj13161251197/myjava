import java.io.IOException;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

public class SSOTest extends Filter{
	public static void main(String[] args) {
		/**
		 * 1)sso-client拦截未登录请求doFilter()
		 * 2)sso-server拦截未登录请求
		 * 		拦截从sso-client跳转至sso认证中心的未登录请求，跳转至登录页面，这个过程与sso-client完全一样
		 * 3)sso-server验证用户登录信息login()
		 * 4)sso-server创建授权令牌
		 * 		授权令牌是一串随机字符，以什么样的方式生成都没有关系，只要不重复、不易伪造即可
		 * 		如：String token = UUID.randomUUID().toString();
		 * 5)sso-client取得令牌并校验login2()
		 * 		sso认证中心登录后，跳转回子系统并附上令牌，子系统（sso-client）取得令牌，然后去sso认证中心校验，
		 * 		在LoginFilter.java的doFilter()中添加几行
		 * 6)sso-server接收并处理校验令牌请求
		 * 
		 * 7)客户端注销过程outlogin()向sso认证中心发起注销请求
		 * 8)sso服务端注销logout()触发LogoutListener
		 * 9)注销全局回话LogoutListener()//通过httpClient向所有注册系统发送注销请求
		 */
	}
	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * java拦截请求的方式有servlet、filter、listener三种方式，我们采用filter。
	 * 在sso-client中新建LoginFilter.java类并实现Filter接口，在doFilter()方法中加入对未登录用户的拦截
	 * 单点登录验证是否已经登录   登录则进入系统负责调整至sso认证中心
	 */
	@Override
	public void doFilter(HttpExchange arg0, Chain arg1) throws IOException {
		// TODO Auto-generated method stub
		
		/* HttpServletRequest req = (HttpServletRequest) request;
		    HttpServletResponse res = (HttpServletResponse) response;
		    
		    
		    String token = req.getParameter("token");
				if (token != null) {
				    // 去sso认证中心校验token
				    //verify()方法使用httpClient实现，这里仅简略介绍，httpClient详细使用方法请参考官方文档
				    boolean verifyResult = this.verify("sso-server-verify-url", token);
				    if (!verifyResult) {
				        res.sendRedirect("sso-server-url");
				        return;
				    }else{
				    	session.setAttribute("isLogin", true);//sso-client校验令牌成功创建局部会话
				    }
				    chain.doFilter(request, response);
				}
		    
		    HttpSession session = req.getSession();
		     
		    if (session.getAttribute("isLogin")) {
		        chain.doFilter(request, response);
		        return;
		    }*/
		
		
		
		
		    //跳转至sso认证中心
		    //res.sendRedirect("sso-server-url-with-system-url");
	}
	
	/*@RequestMapping("/login")
	public String login(String username, String password, HttpServletRequest req) {
	    this.checkLoginInfo(username, password);
	    req.getSession().setAttribute("isLogin", true);
	    return "success";
	}*/
	public void login2(){
		/*String token = req.getParameter("token");
		if (token != null) {
		    // 去sso认证中心校验token
		    boolean verifyResult = this.verify("sso-server-verify-url", token);
		    if (!verifyResult) {
		        res.sendRedirect("sso-server-url");
		        return;
		    }
		    chain.doFilter(request, response);
		}*/
	}
	public boolean verify(){
		/*HttpPost httpPost = new HttpPost("sso-server-verify-url-with-token");
		HttpResponse httpResponse = httpClient.execute(httpPost);*/
		return false;
		
	}
	
	/**
	 * 注销
	 */
	void outlogin(){
		/*String logout = req.getParameter("logout");
		if (logout != null) {
		    this.ssoServer.logout(token);//向sso认证中心发起注销请求
		}*/
	}
	/*@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
	    HttpSession session = req.getSession();
	    if (session != null) {
	        session.invalidate();//触发LogoutListener
	    }
	    return "redirect:/";
	}*/
	/*public class LogoutListener implements HttpSessionListener {
	    @Override
	    public void sessionCreated(HttpSessionEvent event) {}
	    @Override
	    public void sessionDestroyed(HttpSessionEvent event) {
	        //通过httpClient向所有注册系统发送注销请求
	    }
	}*/
}
