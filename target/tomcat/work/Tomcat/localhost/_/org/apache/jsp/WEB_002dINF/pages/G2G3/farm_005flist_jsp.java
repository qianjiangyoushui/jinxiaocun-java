/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-11-07 08:47:39 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages.G2G3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class farm_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"zh_CN\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<meta name=\"viewport\"\r\n");
      out.write("\tcontent=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\">\r\n");
      out.write("<meta name=\"apple-mobile-web-app-capable\" content=\"yes\" />\r\n");
      out.write("<meta name=\"apple-mobile-web-app-status-bar-style\"\r\n");
      out.write("\tcontent=\"black-translucen\" />\r\n");
      out.write("<title></title>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("css/weui.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("css/jquery-weui.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("css/demos.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("css/weui.css\">\r\n");
      out.write("<body>\r\n");
      out.write("\t<div style=\"height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative\">\r\n");
      out.write("\t\t大田基地\r\n");
      out.write("\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("g2g3/index.action?type=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" style=\"position: absolute; left: 10px; top: 8px\">\r\n");
      out.write("\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("icon/back.png\" style=\"width: 20px\" />\r\n");
      out.write("\t\t</a>\r\n");
      out.write("\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("farm/add.action?type=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" style=\"position: absolute; right: 27px; top: 0px;\">\r\n");
      out.write("\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("icon/add.png\" style=\"width: 20px\" />\r\n");
      out.write("\t\t</a>\r\n");
      out.write("\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("farm/add.action?type=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" style=\"position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333\">新增农场</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"weui-cells weui-cells_form infinite\" style=\"margin-top: 0;\" >\r\n");
      out.write("\t\t<div id=\"list\"></div>\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("\t\t<div class=\"weui-loadmore\" style=\"display: none;\" id=\"dis\">\r\n");
      out.write("\t\t\t<i class=\"weui-loading\"></i> <span class=\"weui-loadmore__tips\">正在加载...</span>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"weui-loadmore\" style=\"display: none;\" id=\"disp\">\r\n");
      out.write("\t\t\t<span class=\"weui-loadmore__tips\">没有更多数据...</span>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<div style=\"height: 155px; width: 100%\"></div>\r\n");
      out.write("\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../common/tabbar.jsp", out, true);
      out.write("\r\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/jquery-1.8.1.min.js\"\r\n");
      out.write("\t\ttype=\"text/javascript\"></script>\r\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/jquery-weui.min.js\"\r\n");
      out.write("\t\ttype=\"text/javascript\"></script>\r\n");
      out.write("\r\n");
      out.write("\t<!-- \t分页加载\t -->\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\tvar type=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(";\r\n");
      out.write("\t$(\"#datetime-start\").calendar();\r\n");
      out.write("\t $(\"#datetime-end\").calendar();\r\n");
      out.write("\t \t\r\n");
      out.write("\t\tvar pageNo=1;\t\r\n");
      out.write("\t\tvar pageCount=100;//默认\r\n");
      out.write("\t\tvar loading = false;  //状态标记\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(document.body).infinite().on(\"infinite\", function() {\r\n");
      out.write("\t\t  if(loading) return;\r\n");
      out.write("\t\t  loading = true;\r\n");
      out.write("\t\t  if(pageNo<=pageCount){\r\n");
      out.write("\t\t\t  getdataPage();\r\n");
      out.write("\t\t  }else{\r\n");
      out.write("\t\t\t  $(\"#disp\").removeAttr(\"style\");\r\n");
      out.write("\t\t  }\r\n");
      out.write("\t\t})\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction getdataPage(){\r\n");
      out.write("\t\t\t$(\"#dis\").removeAttr(\"style\");\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t  url: \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("farm/page.action\",\r\n");
      out.write("\t\t\t  type:\"POST\",\r\n");
      out.write("\t\t\t  async: true,\r\n");
      out.write("\t\t\t  dataType: 'json',\r\n");
      out.write("\t\t\t  data: {\"pageNo\":pageNo},\r\n");
      out.write("\t\t\t  error:function(data){\r\n");
      out.write("\t\t\t\t  $.toptip(\"数据加载失败,请刷新\",'error');\r\n");
      out.write("\t\t\t\t  $(\"#dis\").css(\"display\", \"none\");\r\n");
      out.write("\t\t\t  },\r\n");
      out.write("\t\t\t  success: function(data){\r\n");
      out.write("\t\t\t\t  var content=\"\";\r\n");
      out.write("\t\t\t\t  var pagelist=data.page;\r\n");
      out.write("\t\t\t\t  \r\n");
      out.write("\t\t\t\t  pageNo=pagelist.nextPage;//设置pageNo;\r\n");
      out.write("\t\t\t\t  pageCount=pagelist.pageCount;//设置总页数\r\n");
      out.write("\t\t\t\t  \r\n");
      out.write("\t\t\t\t  var rows=pagelist.rows;\r\n");
      out.write("\t\t\t\t  for(var i=0;i<rows.length;i++){\r\n");
      out.write("\t\t\t\t\t  if(type==4){\r\n");
      out.write("\t\t\t\t\t\t  content+='<a style=\"text-decoration:none; color:#333;border-bottom:1px solid #bdbbbc; display:block;padding:20px 0;background-color: #FFFFFF\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("farm/detail.action?farmid='+rows[i].guid+'&type=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("&operate=7\">';\r\n");
      out.write("\t\t\t\t\t  }else{\r\n");
      out.write("\t\t\t\t\t\t  content+='<a style=\"text-decoration:none; color:#333;border-bottom:1px solid #bdbbbc; display:block;padding:20px 0;background-color: #FFFFFF\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("farm/detail.action?farmid='+rows[i].guid+'&type=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("&operate=10\">';\r\n");
      out.write("\t\t\t\t\t  }\r\n");
      out.write("\t\t\t\t\t  content+='<table border=\"0\" style=\"width:100% ;background-color: #FFFFFF\"><col style=\"width:10%\"/><col style=\"width:70%\"/><col style=\"width:15%\"/>';\r\n");
      out.write("\t\t\t\t\t  content+='<tr><td><div style=\"margin-left: 8px;\">';\r\n");
      out.write("\t\t\t\t\t  if(rows[i].url!=\"\"){\r\n");
      out.write("\t\t\t\t\t\t  content+='<img src=\"'+rows[i].url+'\" style=\"width:77px;height:77px;\"/>';\r\n");
      out.write("\t\t\t\t\t  }else{\r\n");
      out.write("\t\t\t\t\t\t  content+='<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("icon/test.png\"/>';\r\n");
      out.write("\t\t\t\t\t  }\r\n");
      out.write("\t\t\t\t\t  content+='</div></td>';\r\n");
      out.write("\t\t\t\t\t  content+='<td align=\"left\" valign=\"top\"><div style=\"margin-left: 8px;\"><strong style=\" font-size:14px\">'+rows[i].farmname+'</strong></div><div style=\" font-size:12px; color:#999; margin-top:15px;margin-left: 8px; line-height:20px\"><span >';\r\n");
      out.write("\t\t\t\t\t  content+='实际种植面积：'+rows[i].plantarea+'亩<br/></span><span>';\r\n");
      out.write("\t\t\t\t\t  content+='租期到期年份：'+rows[i].leaseend+'年';\r\n");
      out.write("\t\t\t\t\t  content+='</span></div></td>';\r\n");
      out.write("\t\t\t\t\t  content+='<td align=\"right\"><div style=\"margin-right: 10px;\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("icon/right.png\" style=\" width:16px\"/></div></td></tr></table></a>';\r\n");
      out.write("\t\t\t\t  }\r\n");
      out.write("\t\t\t\t  $(\"#list\").append(content);\r\n");
      out.write("\t\t\t\t  \r\n");
      out.write("\t\t\t\t  $(\"#dis\").css(\"display\",\"none\");\r\n");
      out.write("\t\t\t\t  loading=false;\r\n");
      out.write("\t\t\t  }\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction info(){\r\n");
      out.write("\t\t\t$(\"#list\").empty();\r\n");
      out.write("\t\t\tpageNo=1;\r\n");
      out.write("\t\t\tpageCount=100;//默认\r\n");
      out.write("\t\t\tgetdataPage();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tinfo();\r\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
