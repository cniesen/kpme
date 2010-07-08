package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class dummy_005flogin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(14);
    _jspx_dependants.add("/kr/WEB-INF/jsp/tldHeader.jsp");
    _jspx_dependants.add("/kr/WEB-INF/tlds/c.tld");
    _jspx_dependants.add("/kr/WEB-INF/tlds/fmt.tld");
    _jspx_dependants.add("/kr/WEB-INF/tlds/fn.tld");
    _jspx_dependants.add("/kr/WEB-INF/tlds/struts-bean.tld");
    _jspx_dependants.add("/kr/WEB-INF/tlds/struts-html.tld");
    _jspx_dependants.add("/kr/WEB-INF/tlds/struts-logic.tld");
    _jspx_dependants.add("/kr/WEB-INF/tlds/struts-nested.tld");
    _jspx_dependants.add("/kr/WEB-INF/tlds/displaytag.tld");
    _jspx_dependants.add("/kr/WEB-INF/tlds/struts-bean-el.tld");
    _jspx_dependants.add("/kr/WEB-INF/tlds/struts-html-el.tld");
    _jspx_dependants.add("/kr/WEB-INF/tlds/struts-logic-el.tld");
    _jspx_dependants.add("/kr/WEB-INF/tlds/displaytag-el.tld");
    _jspx_dependants.add("/kr/WEB-INF/tlds/kuali-func.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\r\n");
      out.write("\n");
      out.write("\r\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("    <title>Login</title>\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.application.url}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('/');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.portal.css.files}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("\n");
      out.write("    <style type=\"text/css\">\n");
      out.write("        div.body {\n");
      out.write("            background-image: url(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.application.url}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/rice-portal/images/os-guy.gif\");\n");
      out.write("            background-repeat: no-repeat;\n");
      out.write("            padding-top: 5em;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        table#login {\n");
      out.write("            margin: auto; \n");
      out.write("            background-color: #dfdda9; \n");
      out.write("            border: .5em solid #fffdd8;\n");
      out.write("            /* simple rounded corners for mozilla & webkit */\n");
      out.write("            -moz-border-radius: 10px;\n");
      out.write("            -webkit-border-radius: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        table#login th {\n");
      out.write("            height: 30 px;\n");
      out.write("            padding-top: .8em;\n");
      out.write("            padding-bottom: .8em;\n");
      out.write("            color: #a02919; \n");
      out.write("            font-size: 2em;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        #login td {\n");
      out.write("            padding: .2em;\n");
      out.write("            height: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        #login .rightTd {\n");
      out.write("            padding-right: 1.2em;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        #login .leftTd {\n");
      out.write("            padding-left: 1.2em;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        table#login td#buttonRow {\n");
      out.write("            padding-top: 1em;\n");
      out.write("            padding-bottom: .6em;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("    </style>\n");
      out.write("  </head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<form action=\"\" method=\"post\">\n");
      out.write("\n");
      out.write("<div class=\"body\">\n");
      out.write("        <table id=\"login\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n");
      out.write("          <tbody>\n");
      out.write("            <tr>\n");
      out.write("              <th colspan=\"2\">Login</th>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("\t            <td class=\"leftTd\" align=\"right\" width=\"Infinity%\">\n");
      out.write("\t                <label>Username:&nbsp;</label>\n");
      out.write("\t            </td>\n");
      out.write("\t            <td class=\"rightTd\" align=\"left\">\n");
      out.write("\t                <input type=\"text\" name=\"__login_user\" value=\"admin\" size=\"20\"/>\n");
      out.write("\t            </td>\n");
      out.write("            </tr>\n");
      out.write("            ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("            ");
      if (_jspx_meth_c_if_1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("            <tr>\n");
      out.write("              <td id=\"buttonRow\" height=\"30\" colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"Login\"/>\n");
      out.write("              <!-- input type=\"image\" title=\"Click to login.\" value=\"login\" name=\"imageField\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/rice-portal/images/tinybutton-login.gif\"/ -->\n");
      out.write("              </td>\n");
      out.write("            </tr>            \n");
      out.write("          </tbody>\n");
      out.write("        </table>\n");
      out.write("</div>\n");
      out.write("</form>\n");
      out.write("</body>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.showPasswordField}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("            <tr>\n");
        out.write("            <td class=\"leftTd\" width=\"Infinity%\" align=\"right\">\n");
        out.write("                <label>Password:&nbsp;</label>\n");
        out.write("            </td>\n");
        out.write("              <td class=\"rightTd\" align=\"left\"><input type=\"password\" name=\"__login_pw\" value=\"admin\" size=\"20\"/></td>\n");
        out.write("            </tr>\n");
        out.write("            ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_if_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent(null);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.invalidPassword}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("            <tr>\n");
        out.write("              <td align=\"center\" colspan=\"2\"><strong>Invalid username or password</strong></td>\n");
        out.write("            </tr>\n");
        out.write("            ");
        int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
    return false;
  }
}
