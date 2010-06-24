package org.apache.jsp.tag.web.kr;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class documentControls_tag
    extends javax.servlet.jsp.tagext.SimpleTagSupport
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

  private JspContext jspContext;
  private java.io.Writer _jspx_sout;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_set_var_value_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_image_styleClass_src_property_alt_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_image_title_styleClass_src_property_onclick_alt_nobody;

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
  private java.lang.String transactionalDocument;
  private java.lang.String saveButtonOverride;
  private java.lang.String suppressRoutingControls;
  private java.lang.String extraButtonSource;
  private java.lang.String extraButtonProperty;
  private java.lang.String extraButtonAlt;
  private java.util.List extraButtons;
  private java.lang.String viewOnly;

  public java.lang.String getTransactionalDocument() {
    return this.transactionalDocument;
  }

  public void setTransactionalDocument(java.lang.String transactionalDocument) {
    this.transactionalDocument = transactionalDocument;
  }

  public java.lang.String getSaveButtonOverride() {
    return this.saveButtonOverride;
  }

  public void setSaveButtonOverride(java.lang.String saveButtonOverride) {
    this.saveButtonOverride = saveButtonOverride;
  }

  public java.lang.String getSuppressRoutingControls() {
    return this.suppressRoutingControls;
  }

  public void setSuppressRoutingControls(java.lang.String suppressRoutingControls) {
    this.suppressRoutingControls = suppressRoutingControls;
  }

  public java.lang.String getExtraButtonSource() {
    return this.extraButtonSource;
  }

  public void setExtraButtonSource(java.lang.String extraButtonSource) {
    this.extraButtonSource = extraButtonSource;
  }

  public java.lang.String getExtraButtonProperty() {
    return this.extraButtonProperty;
  }

  public void setExtraButtonProperty(java.lang.String extraButtonProperty) {
    this.extraButtonProperty = extraButtonProperty;
  }

  public java.lang.String getExtraButtonAlt() {
    return this.extraButtonAlt;
  }

  public void setExtraButtonAlt(java.lang.String extraButtonAlt) {
    this.extraButtonAlt = extraButtonAlt;
  }

  public java.util.List getExtraButtons() {
    return this.extraButtons;
  }

  public void setExtraButtons(java.util.List extraButtons) {
    this.extraButtons = extraButtons;
  }

  public java.lang.String getViewOnly() {
    return this.viewOnly;
  }

  public void setViewOnly(java.lang.String viewOnly) {
    this.viewOnly = viewOnly;
  }

  public Object getDependants() {
    return _jspx_dependants;
  }

  private void _jspInit(ServletConfig config) {
    _jspx_tagPool_c_set_var_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_image_styleClass_src_property_alt_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_image_title_styleClass_src_property_onclick_alt_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_set_var_value_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_html_image_styleClass_src_property_alt_nobody.release();
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.release();
    _jspx_tagPool_html_image_title_styleClass_src_property_onclick_alt_nobody.release();
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
    if( getTransactionalDocument() != null ) 
      _jspx_page_context.setAttribute("transactionalDocument", getTransactionalDocument());
    if( getSaveButtonOverride() != null ) 
      _jspx_page_context.setAttribute("saveButtonOverride", getSaveButtonOverride());
    if( getSuppressRoutingControls() != null ) 
      _jspx_page_context.setAttribute("suppressRoutingControls", getSuppressRoutingControls());
    if( getExtraButtonSource() != null ) 
      _jspx_page_context.setAttribute("extraButtonSource", getExtraButtonSource());
    if( getExtraButtonProperty() != null ) 
      _jspx_page_context.setAttribute("extraButtonProperty", getExtraButtonProperty());
    if( getExtraButtonAlt() != null ) 
      _jspx_page_context.setAttribute("extraButtonAlt", getExtraButtonAlt());
    if( getExtraButtons() != null ) 
      _jspx_page_context.setAttribute("extraButtons", getExtraButtons());
    if( getViewOnly() != null ) 
      _jspx_page_context.setAttribute("viewOnly", getViewOnly());

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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_set_0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_set_1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("        ");
      if (_jspx_meth_c_set_2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("        ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t");
      if (_jspx_meth_c_if_1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("        \n");
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

  private boolean _jspx_meth_c_set_0(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_0.setPageContext(_jspx_page_context);
    _jspx_th_c_set_0.setParent(null);
    _jspx_th_c_set_0.setVar("documentTypeName");
    _jspx_th_c_set_0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.docTypeName}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_0 = _jspx_th_c_set_0.doStartTag();
    if (_jspx_th_c_set_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
    return false;
  }

  private boolean _jspx_meth_c_set_1(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_1.setPageContext(_jspx_page_context);
    _jspx_th_c_set_1.setParent(null);
    _jspx_th_c_set_1.setVar("documentEntry");
    _jspx_th_c_set_1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary[documentTypeName]}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_1 = _jspx_th_c_set_1.doStartTag();
    if (_jspx_th_c_set_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_1);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_1);
    return false;
  }

  private boolean _jspx_meth_c_set_2(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_2.setPageContext(_jspx_page_context);
    _jspx_th_c_set_2.setParent(null);
    _jspx_th_c_set_2.setVar("saveButtonValue");
    _jspx_th_c_set_2.setValue(new String("save"));
    int _jspx_eval_c_set_2 = _jspx_th_c_set_2.doStartTag();
    if (_jspx_th_c_set_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_2);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_2);
    return false;
  }

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty saveButtonOverride}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_set_3(_jspx_th_c_if_0, _jspx_page_context))
          return true;
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

  private boolean _jspx_meth_c_set_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_3 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_3.setPageContext(_jspx_page_context);
    _jspx_th_c_set_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_c_set_3.setVar("saveButtonValue");
    _jspx_th_c_set_3.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${saveButtonOverride}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_3 = _jspx_th_c_set_3.doStartTag();
    if (_jspx_th_c_set_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_3);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_3);
    return false;
  }

  private boolean _jspx_meth_c_if_1(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent(null);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not KualiForm.suppressAllButtons}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t        <div id=\"globalbuttons\" class=\"globalbuttons\">\r\n");
        out.write("\t        \t");
        if (_jspx_meth_c_if_2(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t        \t");
        if (_jspx_meth_c_if_3(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        if (_jspx_meth_c_if_4(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        if (_jspx_meth_c_if_5(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        if (_jspx_meth_c_if_6(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        if (_jspx_meth_c_if_7(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        if (_jspx_meth_c_if_8(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        if (_jspx_meth_c_if_9(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        if (_jspx_meth_c_if_10(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        if (_jspx_meth_c_if_11(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        if (_jspx_meth_c_if_12(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        if (_jspx_meth_c_if_13(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        if (_jspx_meth_c_if_14(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("            \r\n");
        out.write("\t            ");
        if (_jspx_meth_c_if_15(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                ");
        if (_jspx_meth_c_if_16(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t        </div>\r\n");
        out.write("        ");
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

  private boolean _jspx_meth_c_if_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
    _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty extraButtonSource}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t        \t\t");
        if (_jspx_meth_html_image_0(_jspx_th_c_if_2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t        \t");
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

  private boolean _jspx_meth_html_image_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_2, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_0 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_0.setPageContext(_jspx_page_context);
    _jspx_th_html_image_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
    _jspx_th_html_image_0.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${extraButtonSource}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_0.setStyleClass("globalbuttons");
    _jspx_th_html_image_0.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${extraButtonProperty}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_0.setAlt((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${extraButtonAlt}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_html_image_0 = _jspx_th_html_image_0.doStartTag();
    if (_jspx_th_html_image_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_0);
    return false;
  }

  private boolean _jspx_meth_c_if_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_3.setPageContext(_jspx_page_context);
    _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty extraButtons}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
    if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t        \t");
        if (_jspx_meth_c_forEach_0(_jspx_th_c_if_3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t        \t");
        int evalDoAfterBody = _jspx_th_c_if_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
    return false;
  }

  private boolean _jspx_meth_c_forEach_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_3, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${extraButtons}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_c_forEach_0.setVar("extraButton");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t        \t\t");
          if (_jspx_meth_html_image_1(_jspx_th_c_forEach_0, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
            return true;
          out.write("\r\n");
          out.write("\t\t        \t");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        throw new SkipPageException();
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }

  private boolean _jspx_meth_html_image_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_forEach_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_1 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_1.setPageContext(_jspx_page_context);
    _jspx_th_html_image_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_forEach_0);
    _jspx_th_html_image_1.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${extraButton.extraButtonSource}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_1.setStyleClass("globalbuttons");
    _jspx_th_html_image_1.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${extraButton.extraButtonProperty}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_1.setTitle((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${extraButton.extraButtonAltText}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_1.setAlt((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${extraButton.extraButtonAltText}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_html_image_1 = _jspx_th_html_image_1.doStartTag();
    if (_jspx_th_html_image_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_1);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_1);
    return false;
  }

  private boolean _jspx_meth_c_if_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_4 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_4.setPageContext(_jspx_page_context);
    _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_PERFORM_ROUTE_REPORT] and not suppressRoutingControls}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
    if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t    ");
        if (_jspx_meth_html_image_2(_jspx_th_c_if_4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        int evalDoAfterBody = _jspx_th_c_if_4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
    return false;
  }

  private boolean _jspx_meth_html_image_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_4, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_2 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_2.setPageContext(_jspx_page_context);
    _jspx_th_html_image_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
    _jspx_th_html_image_2.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}buttonsmall_routereport.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_2.setStyleClass("globalbuttons");
    _jspx_th_html_image_2.setProperty("methodToCall.performRouteReport");
    _jspx_th_html_image_2.setTitle("Perform Route Report");
    _jspx_th_html_image_2.setAlt("Perform Route Report");
    int _jspx_eval_html_image_2 = _jspx_th_html_image_2.doStartTag();
    if (_jspx_th_html_image_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_2);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_2);
    return false;
  }

  private boolean _jspx_meth_c_if_5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_5 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_5.setPageContext(_jspx_page_context);
    _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_5.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_SEND_ADHOC_REQUESTS] and not suppressRoutingControls}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
    if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t                ");
        if (_jspx_meth_html_image_3(_jspx_th_c_if_5, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        int evalDoAfterBody = _jspx_th_c_if_5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
    return false;
  }

  private boolean _jspx_meth_html_image_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_3 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_3.setPageContext(_jspx_page_context);
    _jspx_th_html_image_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    _jspx_th_html_image_3.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}buttonsmall_sendadhocreq.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_3.setStyleClass("globalbuttons");
    _jspx_th_html_image_3.setProperty("methodToCall.sendAdHocRequests");
    _jspx_th_html_image_3.setTitle("Send AdHoc Requests");
    _jspx_th_html_image_3.setAlt("Send AdHoc Requests");
    int _jspx_eval_html_image_3 = _jspx_th_html_image_3.doStartTag();
    if (_jspx_th_html_image_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_3);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_3);
    return false;
  }

  private boolean _jspx_meth_c_if_6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_6 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_6.setPageContext(_jspx_page_context);
    _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_6.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_ROUTE] and not suppressRoutingControls}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
    if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t                ");
        if (_jspx_meth_html_image_4(_jspx_th_c_if_6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        int evalDoAfterBody = _jspx_th_c_if_6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
    return false;
  }

  private boolean _jspx_meth_html_image_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_6, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_4 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_4.setPageContext(_jspx_page_context);
    _jspx_th_html_image_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
    _jspx_th_html_image_4.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}buttonsmall_submit.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_4.setStyleClass("globalbuttons");
    _jspx_th_html_image_4.setProperty("methodToCall.route");
    _jspx_th_html_image_4.setTitle("submit");
    _jspx_th_html_image_4.setAlt("submit");
    int _jspx_eval_html_image_4 = _jspx_th_html_image_4.doStartTag();
    if (_jspx_th_html_image_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_4);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_4);
    return false;
  }

  private boolean _jspx_meth_c_if_7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_7 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_7.setPageContext(_jspx_page_context);
    _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_7.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_SAVE] and not viewOnly}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
    if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t                ");
        if (_jspx_meth_html_image_5(_jspx_th_c_if_7, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        int evalDoAfterBody = _jspx_th_c_if_7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
    return false;
  }

  private boolean _jspx_meth_html_image_5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_7, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_5 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_5.setPageContext(_jspx_page_context);
    _jspx_th_html_image_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
    _jspx_th_html_image_5.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_5.setStyleClass("globalbuttons");
    _jspx_th_html_image_5.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("methodToCall.${saveButtonValue}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_5.setTitle("save");
    _jspx_th_html_image_5.setAlt("save");
    int _jspx_eval_html_image_5 = _jspx_th_html_image_5.doStartTag();
    if (_jspx_th_html_image_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_5);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_5);
    return false;
  }

  private boolean _jspx_meth_c_if_8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_8 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_8.setPageContext(_jspx_page_context);
    _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_8.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_RELOAD]}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
    if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t                ");
        if (_jspx_meth_html_image_6(_jspx_th_c_if_8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        int evalDoAfterBody = _jspx_th_c_if_8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
    return false;
  }

  private boolean _jspx_meth_html_image_6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_8, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_6 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_onclick_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_6.setPageContext(_jspx_page_context);
    _jspx_th_html_image_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
    _jspx_th_html_image_6.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}buttonsmall_reload.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_6.setStyleClass("globalbuttons");
    _jspx_th_html_image_6.setProperty("methodToCall.reload");
    _jspx_th_html_image_6.setTitle("reload");
    _jspx_th_html_image_6.setAlt("reload");
    _jspx_th_html_image_6.setOnclick("excludeSubmitRestriction=true");
    int _jspx_eval_html_image_6 = _jspx_th_html_image_6.doStartTag();
    if (_jspx_th_html_image_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_onclick_alt_nobody.reuse(_jspx_th_html_image_6);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_onclick_alt_nobody.reuse(_jspx_th_html_image_6);
    return false;
  }

  private boolean _jspx_meth_c_if_9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_9 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_9.setPageContext(_jspx_page_context);
    _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_9.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_BLANKET_APPROVE] and not suppressRoutingControls}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
    if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t                ");
        if (_jspx_meth_html_image_7(_jspx_th_c_if_9, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        int evalDoAfterBody = _jspx_th_c_if_9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
    return false;
  }

  private boolean _jspx_meth_html_image_7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_9, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_7 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_7.setPageContext(_jspx_page_context);
    _jspx_th_html_image_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
    _jspx_th_html_image_7.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}buttonsmall_blanketapp.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_7.setStyleClass("globalbuttons");
    _jspx_th_html_image_7.setProperty("methodToCall.blanketApprove");
    _jspx_th_html_image_7.setTitle("blanket approve");
    _jspx_th_html_image_7.setAlt("blanket approve");
    int _jspx_eval_html_image_7 = _jspx_th_html_image_7.doStartTag();
    if (_jspx_th_html_image_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_7);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_7);
    return false;
  }

  private boolean _jspx_meth_c_if_10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_10 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_10.setPageContext(_jspx_page_context);
    _jspx_th_c_if_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_10.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_APPROVE] and not suppressRoutingControls}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
    if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t                ");
        if (_jspx_meth_html_image_8(_jspx_th_c_if_10, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        int evalDoAfterBody = _jspx_th_c_if_10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
    return false;
  }

  private boolean _jspx_meth_html_image_8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_8 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_8.setPageContext(_jspx_page_context);
    _jspx_th_html_image_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    _jspx_th_html_image_8.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}buttonsmall_approve.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_8.setStyleClass("globalbuttons");
    _jspx_th_html_image_8.setProperty("methodToCall.approve");
    _jspx_th_html_image_8.setTitle("approve");
    _jspx_th_html_image_8.setAlt("approve");
    int _jspx_eval_html_image_8 = _jspx_th_html_image_8.doStartTag();
    if (_jspx_th_html_image_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_8);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_8);
    return false;
  }

  private boolean _jspx_meth_c_if_11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_11 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_11.setPageContext(_jspx_page_context);
    _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_11.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_DISAPPROVE] and not suppressRoutingControls}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
    if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t                ");
        if (_jspx_meth_html_image_9(_jspx_th_c_if_11, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        int evalDoAfterBody = _jspx_th_c_if_11.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
    return false;
  }

  private boolean _jspx_meth_html_image_9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_11, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_9 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_9.setPageContext(_jspx_page_context);
    _jspx_th_html_image_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
    _jspx_th_html_image_9.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}buttonsmall_disapprove.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_9.setStyleClass("globalbuttons");
    _jspx_th_html_image_9.setProperty("methodToCall.disapprove");
    _jspx_th_html_image_9.setTitle("disapprove");
    _jspx_th_html_image_9.setAlt("disapprove");
    int _jspx_eval_html_image_9 = _jspx_th_html_image_9.doStartTag();
    if (_jspx_th_html_image_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_9);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_9);
    return false;
  }

  private boolean _jspx_meth_c_if_12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_12 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_12.setPageContext(_jspx_page_context);
    _jspx_th_c_if_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_12.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_FYI] and not suppressRoutingControls}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_12 = _jspx_th_c_if_12.doStartTag();
    if (_jspx_eval_c_if_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t                ");
        if (_jspx_meth_html_image_10(_jspx_th_c_if_12, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        int evalDoAfterBody = _jspx_th_c_if_12.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
    return false;
  }

  private boolean _jspx_meth_html_image_10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_12, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_10 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_10.setPageContext(_jspx_page_context);
    _jspx_th_html_image_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
    _jspx_th_html_image_10.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}buttonsmall_fyi.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_10.setStyleClass("globalbuttons");
    _jspx_th_html_image_10.setProperty("methodToCall.fyi");
    _jspx_th_html_image_10.setTitle("fyi");
    _jspx_th_html_image_10.setAlt("fyi");
    int _jspx_eval_html_image_10 = _jspx_th_html_image_10.doStartTag();
    if (_jspx_th_html_image_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_10);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_10);
    return false;
  }

  private boolean _jspx_meth_c_if_13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_13 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_13.setPageContext(_jspx_page_context);
    _jspx_th_c_if_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_13.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_ACKNOWLEDGE] and not suppressRoutingControls}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_13 = _jspx_th_c_if_13.doStartTag();
    if (_jspx_eval_c_if_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t                ");
        if (_jspx_meth_html_image_11(_jspx_th_c_if_13, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        int evalDoAfterBody = _jspx_th_c_if_13.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
    return false;
  }

  private boolean _jspx_meth_html_image_11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_13, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_11 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_11.setPageContext(_jspx_page_context);
    _jspx_th_html_image_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_13);
    _jspx_th_html_image_11.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}buttonsmall_acknowledge.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_11.setStyleClass("globalbuttons");
    _jspx_th_html_image_11.setProperty("methodToCall.acknowledge");
    _jspx_th_html_image_11.setTitle("acknowledge");
    _jspx_th_html_image_11.setAlt("acknowledge");
    int _jspx_eval_html_image_11 = _jspx_th_html_image_11.doStartTag();
    if (_jspx_th_html_image_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_11);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_11);
    return false;
  }

  private boolean _jspx_meth_c_if_14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_14 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_14.setPageContext(_jspx_page_context);
    _jspx_th_c_if_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_14.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_CLOSE]}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_14 = _jspx_th_c_if_14.doStartTag();
    if (_jspx_eval_c_if_14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t                ");
        if (_jspx_meth_html_image_12(_jspx_th_c_if_14, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        int evalDoAfterBody = _jspx_th_c_if_14.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
    return false;
  }

  private boolean _jspx_meth_html_image_12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_12 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_12.setPageContext(_jspx_page_context);
    _jspx_th_html_image_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    _jspx_th_html_image_12.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_12.setStyleClass("globalbuttons");
    _jspx_th_html_image_12.setProperty("methodToCall.close");
    _jspx_th_html_image_12.setTitle("close");
    _jspx_th_html_image_12.setAlt("close");
    int _jspx_eval_html_image_12 = _jspx_th_html_image_12.doStartTag();
    if (_jspx_th_html_image_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_12);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_12);
    return false;
  }

  private boolean _jspx_meth_c_if_15(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_15 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_15.setPageContext(_jspx_page_context);
    _jspx_th_c_if_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_15.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_CANCEL]}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_15 = _jspx_th_c_if_15.doStartTag();
    if (_jspx_eval_c_if_15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t                ");
        if (_jspx_meth_html_image_13(_jspx_th_c_if_15, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t            ");
        int evalDoAfterBody = _jspx_th_c_if_15.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
    return false;
  }

  private boolean _jspx_meth_html_image_13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_15, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_13 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_13.setPageContext(_jspx_page_context);
    _jspx_th_html_image_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_15);
    _jspx_th_html_image_13.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_13.setStyleClass("globalbuttons");
    _jspx_th_html_image_13.setProperty("methodToCall.cancel");
    _jspx_th_html_image_13.setTitle("cancel");
    _jspx_th_html_image_13.setAlt("cancel");
    int _jspx_eval_html_image_13 = _jspx_th_html_image_13.doStartTag();
    if (_jspx_th_html_image_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_13);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_13);
    return false;
  }

  private boolean _jspx_meth_c_if_16(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_16 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_16.setPageContext(_jspx_page_context);
    _jspx_th_c_if_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_if_16.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_COPY]}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_16 = _jspx_th_c_if_16.doStartTag();
    if (_jspx_eval_c_if_16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                   ");
        if (_jspx_meth_html_image_14(_jspx_th_c_if_16, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_c_if_16.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
    return false;
  }

  private boolean _jspx_meth_html_image_14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_16, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_14 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_14.setPageContext(_jspx_page_context);
    _jspx_th_html_image_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
    _jspx_th_html_image_14.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}buttonsmall_copy.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_14.setStyleClass("globalbuttons");
    _jspx_th_html_image_14.setProperty("methodToCall.copy");
    _jspx_th_html_image_14.setTitle("Copy current document");
    _jspx_th_html_image_14.setAlt("Copy current document");
    int _jspx_eval_html_image_14 = _jspx_th_html_image_14.doStartTag();
    if (_jspx_th_html_image_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_14);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_14);
    return false;
  }
}
