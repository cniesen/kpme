package org.apache.jsp.tag.web.kr;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fieldShowIcons_tag
    extends javax.servlet.jsp.tagext.SimpleTagSupport
    implements org.apache.jasper.runtime.JspSourceDependent {


  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(23);
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
    _jspx_dependants.add("/WEB-INF/tags/kr/checkErrors.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/fieldShowErrorIcon.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/fieldShowLookupIcon.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/lookup.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/fieldShowDirectInquiryIcon.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/directInquiry.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/fieldShowHelpIcon.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/help.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/fieldShowChangedIcon.tag");
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
  private java.lang.String addHighlighting;

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

  public java.lang.String getAddHighlighting() {
    return this.addHighlighting;
  }

  public void setAddHighlighting(java.lang.String addHighlighting) {
    this.addHighlighting = addHighlighting;
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
    if( getAddHighlighting() != null ) 
      _jspx_page_context.setAttribute("addHighlighting", getAddHighlighting());

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
      out.write("            \n");
      out.write('\n');
      if (_jspx_meth_kul_checkErrors_0(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_kul_fieldShowLookupIcon_0(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_kul_fieldShowDirectInquiryIcon_0(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_c_if_1(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_c_if_2(_jspx_page_context))
        return;
      out.write('\n');
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

  private boolean _jspx_meth_kul_checkErrors_0(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:checkErrors
    org.apache.jsp.tag.web.kr.checkErrors_tag _jspx_th_kul_checkErrors_0 = new org.apache.jsp.tag.web.kr.checkErrors_tag();
    _jspx_th_kul_checkErrors_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_checkErrors_0.setKeyMatch((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${field.propertyName}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_checkErrors_0.doTag();
    return false;
  }

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${hasErrors}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\n');
        out.write('	');
        out.write(' ');
        if (_jspx_meth_kul_fieldShowErrorIcon_0(_jspx_th_c_if_0, _jspx_page_context))
          return true;
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

  private boolean _jspx_meth_kul_fieldShowErrorIcon_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:fieldShowErrorIcon
    org.apache.jsp.tag.web.kr.fieldShowErrorIcon_tag _jspx_th_kul_fieldShowErrorIcon_0 = new org.apache.jsp.tag.web.kr.fieldShowErrorIcon_tag();
    _jspx_th_kul_fieldShowErrorIcon_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_fieldShowErrorIcon_0.setParent(_jspx_th_c_if_0);
    _jspx_th_kul_fieldShowErrorIcon_0.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_fieldShowLookupIcon_0(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:fieldShowLookupIcon
    org.apache.jsp.tag.web.kr.fieldShowLookupIcon_tag _jspx_th_kul_fieldShowLookupIcon_0 = new org.apache.jsp.tag.web.kr.fieldShowLookupIcon_tag();
    _jspx_th_kul_fieldShowLookupIcon_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_fieldShowLookupIcon_0.setIsReadOnly((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isReadOnly}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_fieldShowLookupIcon_0.setField((org.kuali.rice.kns.web.ui.Field) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${field}", org.kuali.rice.kns.web.ui.Field.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_fieldShowLookupIcon_0.setAnchor((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentTabIndex}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_fieldShowLookupIcon_0.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_fieldShowDirectInquiryIcon_0(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:fieldShowDirectInquiryIcon
    org.apache.jsp.tag.web.kr.fieldShowDirectInquiryIcon_tag _jspx_th_kul_fieldShowDirectInquiryIcon_0 = new org.apache.jsp.tag.web.kr.fieldShowDirectInquiryIcon_tag();
    _jspx_th_kul_fieldShowDirectInquiryIcon_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_fieldShowDirectInquiryIcon_0.setIsReadOnly((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isReadOnly}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_fieldShowDirectInquiryIcon_0.setField((org.kuali.rice.kns.web.ui.Field) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${field}", org.kuali.rice.kns.web.ui.Field.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_fieldShowDirectInquiryIcon_0.setAnchor((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentTabIndex}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_fieldShowDirectInquiryIcon_0.doTag();
    return false;
  }

  private boolean _jspx_meth_c_if_1(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent(null);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${field.fieldLevelHelpEnabled || (!field.fieldLevelHelpDisabled && KualiForm.fieldLevelHelpEnabled)}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\n');
        if (_jspx_meth_kul_fieldShowHelpIcon_0(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write('\n');
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

  private boolean _jspx_meth_kul_fieldShowHelpIcon_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:fieldShowHelpIcon
    org.apache.jsp.tag.web.kr.fieldShowHelpIcon_tag _jspx_th_kul_fieldShowHelpIcon_0 = new org.apache.jsp.tag.web.kr.fieldShowHelpIcon_tag();
    _jspx_th_kul_fieldShowHelpIcon_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_fieldShowHelpIcon_0.setParent(_jspx_th_c_if_1);
    _jspx_th_kul_fieldShowHelpIcon_0.setIsReadOnly((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isReadOnly}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_fieldShowHelpIcon_0.setField((org.kuali.rice.kns.web.ui.Field) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${field}", org.kuali.rice.kns.web.ui.Field.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_fieldShowHelpIcon_0.doTag();
    return false;
  }

  private boolean _jspx_meth_c_if_2(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
    _jspx_th_c_if_2.setParent(null);
    _jspx_th_c_if_2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${addHighlighting && field.highlightField && !isReadOnly}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\n');
        out.write(' ');
        out.write(' ');
        if (_jspx_meth_kul_fieldShowChangedIcon_0(_jspx_th_c_if_2, _jspx_page_context))
          return true;
        out.write('\n');
        int evalDoAfterBody = _jspx_th_c_if_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
    return false;
  }

  private boolean _jspx_meth_kul_fieldShowChangedIcon_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_2, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:fieldShowChangedIcon
    org.apache.jsp.tag.web.kr.fieldShowChangedIcon_tag _jspx_th_kul_fieldShowChangedIcon_0 = new org.apache.jsp.tag.web.kr.fieldShowChangedIcon_tag();
    _jspx_th_kul_fieldShowChangedIcon_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_fieldShowChangedIcon_0.setParent(_jspx_th_c_if_2);
    _jspx_th_kul_fieldShowChangedIcon_0.doTag();
    return false;
  }
}
