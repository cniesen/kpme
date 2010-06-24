package org.apache.jsp.tag.web.kr;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class containerRowDisplay_tag
    extends javax.servlet.jsp.tagext.SimpleTagSupport
    implements org.apache.jasper.runtime.JspSourceDependent {


  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(15);
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
    _jspx_dependants.add("/WEB-INF/tags/kr/rowDisplay.tag");
  }

  private JspContext jspContext;
  private java.io.Writer _jspx_sout;
  public void setJspContext(JspContext ctx) {
    super.setJspContext(ctx);
    java.util.ArrayList _jspx_nested = null;
    java.util.ArrayList _jspx_at_begin = null;
    java.util.ArrayList _jspx_at_end = null;
    this.jspContext = new org.apache.jasper.runtime.JspContextWrapper(ctx, _jspx_nested, _jspx_at_begin, _jspx_at_end, null);
  }

  public JspContext getJspContext() {
    return this.jspContext;
  }
  private java.util.List rows;
  private java.lang.String numberOfColumns;
  private java.lang.String skipTheOldNewBar;
  private java.lang.String depth;
  private java.lang.String rowsHidden;
  private java.lang.String rowsReadOnly;

  public java.util.List getRows() {
    return this.rows;
  }

  public void setRows(java.util.List rows) {
    this.rows = rows;
  }

  public java.lang.String getNumberOfColumns() {
    return this.numberOfColumns;
  }

  public void setNumberOfColumns(java.lang.String numberOfColumns) {
    this.numberOfColumns = numberOfColumns;
  }

  public java.lang.String getSkipTheOldNewBar() {
    return this.skipTheOldNewBar;
  }

  public void setSkipTheOldNewBar(java.lang.String skipTheOldNewBar) {
    this.skipTheOldNewBar = skipTheOldNewBar;
  }

  public java.lang.String getDepth() {
    return this.depth;
  }

  public void setDepth(java.lang.String depth) {
    this.depth = depth;
  }

  public java.lang.String getRowsHidden() {
    return this.rowsHidden;
  }

  public void setRowsHidden(java.lang.String rowsHidden) {
    this.rowsHidden = rowsHidden;
  }

  public java.lang.String getRowsReadOnly() {
    return this.rowsReadOnly;
  }

  public void setRowsReadOnly(java.lang.String rowsReadOnly) {
    this.rowsReadOnly = rowsReadOnly;
  }

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void doTag() throws JspException, java.io.IOException {
    PageContext _jspx_page_context = (PageContext)jspContext;
    HttpServletRequest request = (HttpServletRequest) _jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse) _jspx_page_context.getResponse();
    HttpSession session = _jspx_page_context.getSession();
    ServletContext application = _jspx_page_context.getServletContext();
    ServletConfig config = _jspx_page_context.getServletConfig();
    JspWriter out = jspContext.getOut();
    if( getRows() != null ) 
      _jspx_page_context.setAttribute("rows", getRows());
    if( getNumberOfColumns() != null ) 
      _jspx_page_context.setAttribute("numberOfColumns", getNumberOfColumns());
    if( getSkipTheOldNewBar() != null ) 
      _jspx_page_context.setAttribute("skipTheOldNewBar", getSkipTheOldNewBar());
    if( getDepth() != null ) 
      _jspx_page_context.setAttribute("depth", getDepth());
    if( getRowsHidden() != null ) 
      _jspx_page_context.setAttribute("rowsHidden", getRowsHidden());
    if( getRowsReadOnly() != null ) 
      _jspx_page_context.setAttribute("rowsReadOnly", getRowsReadOnly());

    try {
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("             \n");
      out.write("    ");
      if (_jspx_meth_kul_rowDisplay_0(_jspx_page_context))
        return;
      out.write("          \n");
    } catch( Throwable t ) {
      if( t instanceof SkipPageException )
          throw (SkipPageException) t;
      if( t instanceof java.io.IOException )
          throw (java.io.IOException) t;
      if( t instanceof IllegalStateException )
          throw (IllegalStateException) t;
      if( t instanceof JspException )
          throw (JspException) t;
      throw new JspException(t);
    } finally {
      ((org.apache.jasper.runtime.JspContextWrapper) jspContext).syncEndTagFile();
    }
  }

  private boolean _jspx_meth_kul_rowDisplay_0(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:rowDisplay
    org.apache.jsp.tag.web.kr.rowDisplay_tag _jspx_th_kul_rowDisplay_0 = new org.apache.jsp.tag.web.kr.rowDisplay_tag();
    _jspx_th_kul_rowDisplay_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_rowDisplay_0.setRows((java.util.List) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${rows}", java.util.List.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_rowDisplay_0.setNumberOfColumns((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${numberOfColumns}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_rowDisplay_0.setSkipTheOldNewBar((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${skipTheOldNewBar}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_rowDisplay_0.setDepth((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${depth}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_rowDisplay_0.setRowsHidden((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${rowsHidden}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_rowDisplay_0.setRowsReadOnly((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${rowsReadOnly}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_rowDisplay_0.doTag();
    return false;
  }
}
