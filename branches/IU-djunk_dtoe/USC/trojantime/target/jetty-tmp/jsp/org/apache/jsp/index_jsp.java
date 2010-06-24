package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/jsp/tlds.jsp");
  }

  public Object getDependants() {
    return _jspx_dependants;
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t<title>TK Index</title>\n");
      out.write("\t<link href=\"kr/css/kuali.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("\t<link type=\"text/css\" href=\"../jsp/css/hr_styles.css\" rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div id=\"headerarea\" class=\"headerarea\">\n");
      out.write("<span class=\"left\">\n");
      out.write("<img border=\"0\" alt=\"Logo\" src=\"jsp/images/time_logo3.gif\"/>\n");
      out.write("</span>\n");
      out.write("\n");
      out.write("<span class=\"right\" style=\"margin-right: 10px;\">\n");
      out.write("\n");
      out.write("</span>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<br/><br/>\n");
      out.write("\n");
      out.write(" <div align=\"center\" id=\"main\">\n");
      out.write(" <table width=\"95%\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-style:ridge; border-color:#7d1100; border-width:thin\">\n");
      out.write("   <tr>\n");
      out.write("    <td style=\"background-color:#F0F0F0\">\n");
      out.write("    \n");
      out.write("      <table width=\"100%\">\n");
      out.write("        <tr>\n");
      out.write("        <td width=\"33%\" valign=\"top\">\n");
      out.write("\t\t   <div class=\"portlet\">\n");
      out.write("\t          <div class=\"header\">\n");
      out.write("\t    \t\t<h2>Maintenance</h2>\n");
      out.write("\t          </div>\n");
      out.write("\t\t\t  <div class=\"portlet-content\">\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\t\t<a href=\"kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.hr.time.clock.location.ClockLocationRule&returnLocation=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.application.url}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/portal.do&hideReturnLink=true&docFormKey=88888888\">Clock Location Rule</a>   \n");
      out.write("\t\t\t\n");
      out.write("\t\t\t   </div>    \n");
      out.write("           </div>\n");
      out.write("           <div class=\"portlet\">\n");
      out.write("              <div class=\"header\">\n");
      out.write("                  <h2>Inquiry</h2>\n");
      out.write("              </div>\n");
      out.write("              <div class=\"portlet-content\">\n");
      out.write("\t\t\t\t\n");
      out.write(" \t\t\t\t\n");
      out.write("              </div>\n");
      out.write("           </div>\n");
      out.write("        </td>\n");
      out.write("        <td width=\"33%\" valign=\"top\">\n");
      out.write("           <div class=\"portlet\">\n");
      out.write("\t\t\t  <div class=\"header\">\n");
      out.write("\t\t\t\t  <h2>Support</h2>\n");
      out.write("\t\t\t  </div>\n");
      out.write("\t\t\t  <div class=\"portlet-content\">\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t  </div>\n");
      out.write("\t\t   </div>        \n");
      out.write("\t\t   <div class=\"portlet\">\n");
      out.write("\t          <div class=\"header\">\n");
      out.write("\t              <h2>OneStart Portal</h2>\n");
      out.write("\t          </div>\n");
      out.write("\t\t \t  <div class=\"portlet-content\">\n");
      out.write("\t\t\t  </div>\n");
      out.write("           </div>\n");
      out.write("           <div class=\"portlet\">\n");
      out.write("\t          <div class=\"header\">\n");
      out.write("\t              <h2>Kiosk</h2> \n");
      out.write("\t          </div>\n");
      out.write("\t\t\t  <div class=\"portlet-content\">\n");
      out.write("\t\t\t  </div>\n");
      out.write("           </div>\n");
      out.write("\t       <div class=\"portlet\">\n");
      out.write("\t          <div class=\"header\">\n");
      out.write("\t              <h2>Administrative</h2> \n");
      out.write("\t          </div>\n");
      out.write("\t\t\t  <div class=\"portlet-content\">\n");
      out.write("\t\t\t  \t\n");
      out.write("\n");
      out.write("\t\t\t\t<form name=\"backdoorForm\">\n");
      out.write("\t\t\t\t   BackdoorId <input type=\"text\" name=\"backdoorId\">\n");
      out.write("\n");
      out.write("\t\t\t\t</form>\n");
      out.write("\t\t\t   </div>\n");
      out.write("\t       </div>\n");
      out.write("        </td>\n");
      out.write("        </tr>\n");
      out.write("      </table> \n");
      out.write("    <br/><br/>\n");
      out.write("    </td>\n");
      out.write("   </tr>\n");
      out.write(" </table>\n");
      out.write("<br/><br/>\n");
      out.write("</body>\n");
      out.write("</html>");
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
}
