/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-11-07 08:47:19 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class tabbar_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".my-tab{\r\n");
      out.write("    position:fixed;\r\n");
      out.write("    bottom:1px;\r\n");
      out.write("    Z-index:999;\r\n");
      out.write("    height:60px;\r\n");
      out.write("    width:100%;\r\n");
      out.write("    background-color: #f7f7fa;\r\n");
      out.write("}\r\n");
      out.write(".my-tabbar{\r\n");
      out.write("    display:-webkit-flex;\r\n");
      out.write("    display: flex;\r\n");
      out.write("    flex-direction:row;\r\n");
      out.write("    justify-content:space-around ;\r\n");
      out.write("    align-items:center ;\r\n");
      out.write("    align-content:center ;\r\n");
      out.write("}\r\n");
      out.write(".my-tab-item{\r\n");
      out.write("    display:-webkit-flex;\r\n");
      out.write("    display: flex;\r\n");
      out.write("    flex-direction:column;\r\n");
      out.write("    justify-content:center ;\r\n");
      out.write("    align-items:center ;\r\n");
      out.write("    align-content:center;\r\n");
      out.write("    margin-top:10px;\r\n");
      out.write("}\r\n");
      out.write(".my_tabbar_icon {\r\n");
      out.write("  margin: 0 auto;\r\n");
      out.write("  width: 24px;\r\n");
      out.write("  height: 24px;\r\n");
      out.write("}\r\n");
      out.write(".blank_div{\r\n");
      out.write("    width:100%;\r\n");
      out.write("    height:100px;\r\n");
      out.write("}\r\n");
      out.write(".badge{\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("    min-width: 1px;\r\n");
      out.write("    height: 5rpx;\r\n");
      out.write("    line-height: 5px;\r\n");
      out.write("    font-size: 10px;\r\n");
      out.write("    text-align: center;\r\n");
      out.write("    font-family: -apple-system-font, Helvetica Neue, Helvetica, sans-serif;\r\n");
      out.write("    text-rendering: optimizeLegibility;\r\n");
      out.write("    -webkit-font-smoothing: antialiased;\r\n");
      out.write("    color: #FFF;\r\n");
      out.write("    background: #f5342f;\r\n");
      out.write("    border-radius: 50%;\r\n");
      out.write("    overflow: hidden;\r\n");
      out.write("    line-height: 15px;\r\n");
      out.write("    font-size: 10px;\r\n");
      out.write("    text-indent: 5px;\r\n");
      out.write("    padding: 0 5px 5 1px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<div class=\"blank_div\"></div>\r\n");
      out.write("<div class=\"my-tab\">\r\n");
      out.write("    <div class=\"my-tabbar\">\r\n");
      out.write("        <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("index/index.action\" class=\"my-tab-item\">\r\n");
      out.write("               <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("icon/work.png\" alt=\"\" class=\"my_tabbar_icon\">\r\n");
      out.write("                <p class=\"weui-tabbar__label\">工作台</p>\r\n");
      out.write("        </a>\r\n");
      out.write("        <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("index/message.action\" class=\"my-tab-item\">\r\n");
      out.write("                <div>\r\n");
      out.write("                   <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("icon/message.png\" alt=\"\"class=\"my_tabbar_icon\">\r\n");
      out.write("                   <p class=\"badge\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${messageCount==0?'':messageCount}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</p>\r\n");
      out.write("               </div>\r\n");
      out.write("                <p class=\"weui-tabbar__label\">消息</p>\r\n");
      out.write("        </a>\r\n");
      out.write("        <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("setup/index.action\" class=\"my-tab-item\">\r\n");
      out.write("               <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("icon/setting.png\" alt=\"\" class=\"my_tabbar_icon\">\r\n");
      out.write("                <p class=\"weui-tabbar__label\">设置</p>\r\n");
      out.write("        </a>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
