package org.apache.jsp.tag.web.kr;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class workflowWorkgroupLookup_tag
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
    _jspx_dependants.add("/WEB-INF/tags/kr/lookup.tag");
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
  private java.lang.String fieldConversions;
  private java.lang.String lookupParameters;
  private java.lang.String tabKey;

  public java.lang.String getFieldConversions() {
    return this.fieldConversions;
  }

  public void setFieldConversions(java.lang.String fieldConversions) {
    this.fieldConversions = fieldConversions;
  }

  public java.lang.String getLookupParameters() {
    return this.lookupParameters;
  }

  public void setLookupParameters(java.lang.String lookupParameters) {
    this.lookupParameters = lookupParameters;
  }

  public java.lang.String getTabKey() {
    return this.tabKey;
  }

  public void setTabKey(java.lang.String tabKey) {
    this.tabKey = tabKey;
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
    if( getFieldConversions() != null ) 
      _jspx_page_context.setAttribute("fieldConversions", getFieldConversions());
    if( getLookupParameters() != null ) 
      _jspx_page_context.setAttribute("lookupParameters", getLookupParameters());
    if( getTabKey() != null ) 
      _jspx_page_context.setAttribute("tabKey", getTabKey());

    try {
      out.write('\r');
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_kul_lookup_0(_jspx_page_context))
        return;
      out.write('\n');
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

  private boolean _jspx_meth_kul_lookup_0(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:lookup
    org.apache.jsp.tag.web.kr.lookup_tag _jspx_th_kul_lookup_0 = new org.apache.jsp.tag.web.kr.lookup_tag();
    _jspx_th_kul_lookup_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_lookup_0.setBoClassName("org.kuali.rice.kim.bo.impl.GroupImpl");
    _jspx_th_kul_lookup_0.setFieldConversions((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fieldConversions}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_lookup_0.setLookupParameters((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lookupParameters}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_lookup_0.setAnchor((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${tabKey}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_lookup_0.doTag();
    return false;
  }
}
