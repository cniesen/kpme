package org.apache.jsp.tag.web.kr;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fieldShowLookupIcon_tag
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
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

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
  private java.lang.String isReadOnly;
  private org.kuali.rice.kns.web.ui.Field field;
  private java.lang.String anchor;

  public java.lang.String getIsReadOnly() {
    return this.isReadOnly;
  }

  public void setIsReadOnly(java.lang.String isReadOnly) {
    this.isReadOnly = isReadOnly;
  }

  public org.kuali.rice.kns.web.ui.Field getField() {
    return this.field;
  }

  public void setField(org.kuali.rice.kns.web.ui.Field field) {
    this.field = field;
  }

  public java.lang.String getAnchor() {
    return this.anchor;
  }

  public void setAnchor(java.lang.String anchor) {
    this.anchor = anchor;
  }

  public Object getDependants() {
    return _jspx_dependants;
  }

  private void _jspInit(ServletConfig config) {
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
  }

  public void doTag() throws JspException, java.io.IOException {
    PageContext _jspx_page_context = (PageContext)jspContext;
    HttpServletRequest request = (HttpServletRequest) _jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse) _jspx_page_context.getResponse();
    HttpSession session = _jspx_page_context.getSession();
    ServletContext application = _jspx_page_context.getServletContext();
    ServletConfig config = _jspx_page_context.getServletConfig();
    JspWriter out = jspContext.getOut();
    _jspInit(config);
    if( getIsReadOnly() != null ) 
      _jspx_page_context.setAttribute("isReadOnly", getIsReadOnly());
    if( getField() != null ) 
      _jspx_page_context.setAttribute("field", getField());
    if( getAnchor() != null ) 
      _jspx_page_context.setAttribute("anchor", getAnchor());

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
      out.write("              \n");
      if (_jspx_meth_c_if_0(_jspx_page_context))
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
      _jspDestroy();
    }
  }

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isReadOnly ne true && field.fieldType ne field.KUALIUSER && field.fieldType ne field.HIDDEN}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\n");
        out.write("    ");
        if (_jspx_meth_c_if_1(_jspx_th_c_if_0, _jspx_page_context))
          return true;
        out.write('\n');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_if_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!(empty field.quickFinderClassNameImpl)}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("        \n");
        out.write("        ");
        if (_jspx_meth_kul_lookup_0(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                \n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
    return false;
  }

  private boolean _jspx_meth_kul_lookup_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:lookup
    org.apache.jsp.tag.web.kr.lookup_tag _jspx_th_kul_lookup_0 = new org.apache.jsp.tag.web.kr.lookup_tag();
    _jspx_th_kul_lookup_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_lookup_0.setParent(_jspx_th_c_if_1);
    _jspx_th_kul_lookup_0.setBoClassName((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${field.quickFinderClassNameImpl}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_lookup_0.setFieldConversions((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${field.fieldConversions}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_lookup_0.setLookupParameters((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${field.lookupParameters}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_lookup_0.setFieldLabel((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${field.fieldLabel}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_lookup_0.setReferencesToRefresh((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${field.referencesToRefresh}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_lookup_0.setAnchor((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${anchor}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_lookup_0.setBaseLookupUrl((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${field.baseLookupUrl}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_lookup_0.doTag();
    return false;
  }
}
