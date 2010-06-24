package org.apache.jsp.tag.web.kr;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class adHocRecipients_tag
    extends javax.servlet.jsp.tagext.SimpleTagSupport
    implements org.apache.jasper.runtime.JspSourceDependent {


  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(29);
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
    _jspx_dependants.add("/WEB-INF/tags/kr/tab.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/checkErrors.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/htmlControlAttribute.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/fieldShowErrorIcon.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/inquiry.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/errors.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/auditErrors.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/displayIfErrors.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/htmlAttributeHeaderCell.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/htmlAttributeLabel.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/dd/evalNameToMap.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/user.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/lookup.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/fieldShowChangedIcon.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/workflowWorkgroupLookup.tag");
  }

  private JspContext jspContext;
  private java.io.Writer _jspx_sout;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_hidden_property_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_set_var_value_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_select_title_property;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_options_property_labelProperty_collection_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_logic_iterate_property_name_indexId_id;

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

  public Object getDependants() {
    return _jspx_dependants;
  }

  private void _jspInit(ServletConfig config) {
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_hidden_property_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_c_set_var_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_select_title_property = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_options_property_labelProperty_collection_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_logic_iterate_property_name_indexId_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_html_hidden_property_nobody.release();
    _jspx_tagPool_c_set_var_value_nobody.release();
    _jspx_tagPool_html_select_title_property.release();
    _jspx_tagPool_html_options_property_labelProperty_collection_nobody.release();
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.release();
    _jspx_tagPool_logic_iterate_property_name_indexId_id.release();
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
      out.write("    ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.documentActions[Constants.KUALI_ACTION_CAN_ADD_ADHOC_REQUESTS] and not KualiForm.suppressAllButtons}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("        ");
          //  kul:tab
          org.apache.jsp.tag.web.kr.tab_tag _jspx_th_kul_tab_0 = new org.apache.jsp.tag.web.kr.tab_tag();
          _jspx_th_kul_tab_0.setJspContext(_jspx_page_context);
          _jspx_th_kul_tab_0.setParent(_jspx_th_c_if_0);
          _jspx_th_kul_tab_0.setTabTitle("Ad Hoc Recipients");
          _jspx_th_kul_tab_0.setDefaultOpen("false");
          _jspx_th_kul_tab_0.setTabErrorKey((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${Constants.AD_HOC_ROUTE_ERRORS}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
          _jspx_th_kul_tab_0.setJspBody(new adHocRecipients_tagHelper( 0, _jspx_page_context, _jspx_th_kul_tab_0, null));
          _jspx_th_kul_tab_0.doTag();
          out.write("\r\n");
          out.write("    ");
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

  private boolean _jspx_meth_kul_displayIfErrors_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:displayIfErrors
    org.apache.jsp.tag.web.kr.displayIfErrors_tag _jspx_th_kul_displayIfErrors_0 = new org.apache.jsp.tag.web.kr.displayIfErrors_tag();
    _jspx_th_kul_displayIfErrors_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_displayIfErrors_0.setParent(_jspx_parent);
    _jspx_th_kul_displayIfErrors_0.setKeyMatch((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${Constants.AD_HOC_ROUTE_PERSON_ERRORS}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_displayIfErrors_0.setJspBody(new adHocRecipients_tagHelper( 1, _jspx_page_context, _jspx_th_kul_displayIfErrors_0, null));
    _jspx_th_kul_displayIfErrors_0.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_errors_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:errors
    org.apache.jsp.tag.web.kr.errors_tag _jspx_th_kul_errors_0 = new org.apache.jsp.tag.web.kr.errors_tag();
    _jspx_th_kul_errors_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_errors_0.setParent(_jspx_parent);
    _jspx_th_kul_errors_0.setKeyMatch((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${Constants.AD_HOC_ROUTE_PERSON_ERRORS}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_errors_0.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_0 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_0.setParent(_jspx_parent);
    _jspx_th_kul_htmlAttributeHeaderCell_0.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary.AdHocRoutePerson.attributes.actionRequested}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_0.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_0.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_1 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_1.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_1.setParent(_jspx_parent);
    _jspx_th_kul_htmlAttributeHeaderCell_1.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary.AdHocRoutePerson.attributes.id}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_1.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_1.setColspan("2");
    _jspx_th_kul_htmlAttributeHeaderCell_1.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_2(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_2 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_2.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_2.setParent(_jspx_parent);
    _jspx_th_kul_htmlAttributeHeaderCell_2.setLiteralLabel("Actions");
    _jspx_th_kul_htmlAttributeHeaderCell_2.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_2.doTag();
    return false;
  }

  private boolean _jspx_meth_html_hidden_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.kuali.rice.kns.web.taglib.html.KNSHiddenTag _jspx_th_html_hidden_0 = (org.kuali.rice.kns.web.taglib.html.KNSHiddenTag) _jspx_tagPool_html_hidden_property_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSHiddenTag.class);
    _jspx_th_html_hidden_0.setPageContext(_jspx_page_context);
    _jspx_th_html_hidden_0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_html_hidden_0.setProperty("newAdHocRoutePerson.type");
    int _jspx_eval_html_hidden_0 = _jspx_th_html_hidden_0.doStartTag();
    if (_jspx_th_html_hidden_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_0);
    return false;
  }

  private boolean _jspx_meth_c_set_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_0.setPageContext(_jspx_page_context);
    _jspx_th_c_set_0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_c_set_0.setVar("accessibleTitle");
    _jspx_th_c_set_0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary.AdHocRoutePerson.attributes.actionRequested.label}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_0 = _jspx_th_c_set_0.doStartTag();
    if (_jspx_th_c_set_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
    return false;
  }

  private boolean _jspx_meth_c_set_1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_1.setPageContext(_jspx_page_context);
    _jspx_th_c_set_1.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_c_set_1.setVar("accessibleTitle2");
    _jspx_th_c_set_1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary.AdHocRouteWorkgroup.attributes.actionRequested.label}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_1 = _jspx_th_c_set_1.doStartTag();
    if (_jspx_th_c_set_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_1);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_1);
    return false;
  }

  private boolean _jspx_meth_c_if_1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(DataDictionary.AdHocRoutePerson.attributes.actionRequested.required == true) && readOnly != true}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                          ");
        if (_jspx_meth_c_set_2(_jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                        ");
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

  private boolean _jspx_meth_c_set_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_2.setPageContext(_jspx_page_context);
    _jspx_th_c_set_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_set_2.setVar("accessibleTitle");
    _jspx_th_c_set_2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${Constants.REQUIRED_FIELD_SYMBOL} ${accessibleTitle}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_2 = _jspx_th_c_set_2.doStartTag();
    if (_jspx_th_c_set_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_2);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_2);
    return false;
  }

  private boolean _jspx_meth_c_if_2(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
    _jspx_th_c_if_2.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_c_if_2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(DataDictionary.AdHocRouteWorkgroup.attributes.actionRequested.required == true) && readOnly != true}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                          ");
        if (_jspx_meth_c_set_3(_jspx_th_c_if_2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                        ");
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

  private boolean _jspx_meth_c_set_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_2, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_3 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_3.setPageContext(_jspx_page_context);
    _jspx_th_c_set_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
    _jspx_th_c_set_3.setVar("accessibleTitle2");
    _jspx_th_c_set_3.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${Constants.REQUIRED_FIELD_SYMBOL} ${accessibleTitle2}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_3 = _jspx_th_c_set_3.doStartTag();
    if (_jspx_th_c_set_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_3);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_3);
    return false;
  }

  private boolean _jspx_meth_html_select_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:select
    org.kuali.rice.kns.web.taglib.html.KNSSelectTag _jspx_th_html_select_0 = (org.kuali.rice.kns.web.taglib.html.KNSSelectTag) _jspx_tagPool_html_select_title_property.get(org.kuali.rice.kns.web.taglib.html.KNSSelectTag.class);
    _jspx_th_html_select_0.setPageContext(_jspx_page_context);
    _jspx_th_html_select_0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_html_select_0.setTitle((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${accessibleTitle}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_select_0.setProperty("newAdHocRoutePerson.actionRequested");
    int _jspx_eval_html_select_0 = _jspx_th_html_select_0.doStartTag();
    if (_jspx_eval_html_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_select_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_select_0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                          ");
        if (_jspx_meth_c_set_4(_jspx_th_html_select_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                          ");
        if (_jspx_meth_html_options_0(_jspx_th_html_select_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_html_select_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_html_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_select_title_property.reuse(_jspx_th_html_select_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_select_title_property.reuse(_jspx_th_html_select_0);
    return false;
  }

  private boolean _jspx_meth_c_set_4(javax.servlet.jsp.tagext.JspTag _jspx_th_html_select_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_4 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_4.setPageContext(_jspx_page_context);
    _jspx_th_c_set_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_select_0);
    _jspx_th_c_set_4.setVar("actionRequestCodes");
    _jspx_th_c_set_4.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.adHocActionRequestCodes}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_4 = _jspx_th_c_set_4.doStartTag();
    if (_jspx_th_c_set_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_4);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_4);
    return false;
  }

  private boolean _jspx_meth_html_options_0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_select_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:options
    org.apache.struts.taglib.html.OptionsTag _jspx_th_html_options_0 = (org.apache.struts.taglib.html.OptionsTag) _jspx_tagPool_html_options_property_labelProperty_collection_nobody.get(org.apache.struts.taglib.html.OptionsTag.class);
    _jspx_th_html_options_0.setPageContext(_jspx_page_context);
    _jspx_th_html_options_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_select_0);
    _jspx_th_html_options_0.setCollection("actionRequestCodes");
    _jspx_th_html_options_0.setProperty("key");
    _jspx_th_html_options_0.setLabelProperty("value");
    int _jspx_eval_html_options_0 = _jspx_th_html_options_0.doStartTag();
    if (_jspx_th_html_options_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_options_property_labelProperty_collection_nobody.reuse(_jspx_th_html_options_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_options_property_labelProperty_collection_nobody.reuse(_jspx_th_html_options_0);
    return false;
  }

  private boolean _jspx_meth_kul_checkErrors_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:checkErrors
    org.apache.jsp.tag.web.kr.checkErrors_tag _jspx_th_kul_checkErrors_0 = new org.apache.jsp.tag.web.kr.checkErrors_tag();
    _jspx_th_kul_checkErrors_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_checkErrors_0.setParent(_jspx_parent);
    _jspx_th_kul_checkErrors_0.setKeyMatch("newAdHocRoutePerson.id");
    _jspx_th_kul_checkErrors_0.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_user_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:user
    org.apache.jsp.tag.web.kr.user_tag _jspx_th_kul_user_0 = new org.apache.jsp.tag.web.kr.user_tag();
    _jspx_th_kul_user_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_user_0.setParent(_jspx_parent);
    _jspx_th_kul_user_0.setUserIdFieldName("newAdHocRoutePerson.id");
    _jspx_th_kul_user_0.setUserId((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.newAdHocRoutePerson.id}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_0.setUniversalIdFieldName("");
    _jspx_th_kul_user_0.setUniversalId("");
    _jspx_th_kul_user_0.setUserNameFieldName("newAdHocRoutePerson.name");
    _jspx_th_kul_user_0.setUserName((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.newAdHocRoutePerson.name}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_0.setReadOnly((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${displayReadOnly}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_0.setRenderOtherFields("true");
    _jspx_th_kul_user_0.setFieldConversions("principalName:newAdHocRoutePerson.id,name:newAdHocRoutePerson.name");
    _jspx_th_kul_user_0.setLookupParameters("newAdHocRoutePerson.id:principalName");
    _jspx_th_kul_user_0.setHasErrors((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${hasErrors}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_0.doTag();
    return false;
  }

  private boolean _jspx_meth_html_image_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_0 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_0.setPageContext(_jspx_page_context);
    _jspx_th_html_image_0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_html_image_0.setProperty("methodToCall.insertAdHocRoutePerson");
    _jspx_th_html_image_0.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_0.setTitle("Insert Additional Ad Hoc Person");
    _jspx_th_html_image_0.setAlt("Insert Additional Ad Hoc Person");
    _jspx_th_html_image_0.setStyleClass("tinybutton");
    int _jspx_eval_html_image_0 = _jspx_th_html_image_0.doStartTag();
    if (_jspx_th_html_image_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_0);
    return false;
  }

  private boolean _jspx_meth_html_hidden_1(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_iterate_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.kuali.rice.kns.web.taglib.html.KNSHiddenTag _jspx_th_html_hidden_1 = (org.kuali.rice.kns.web.taglib.html.KNSHiddenTag) _jspx_tagPool_html_hidden_property_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSHiddenTag.class);
    _jspx_th_html_hidden_1.setPageContext(_jspx_page_context);
    _jspx_th_html_hidden_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_iterate_0);
    _jspx_th_html_hidden_1.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("adHocRoutePerson[${ctr}].type", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_html_hidden_1 = _jspx_th_html_hidden_1.doStartTag();
    if (_jspx_th_html_hidden_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_1);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_1);
    return false;
  }

  private boolean _jspx_meth_html_hidden_2(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_iterate_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.kuali.rice.kns.web.taglib.html.KNSHiddenTag _jspx_th_html_hidden_2 = (org.kuali.rice.kns.web.taglib.html.KNSHiddenTag) _jspx_tagPool_html_hidden_property_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSHiddenTag.class);
    _jspx_th_html_hidden_2.setPageContext(_jspx_page_context);
    _jspx_th_html_hidden_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_iterate_0);
    _jspx_th_html_hidden_2.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("adHocRoutePerson[${ctr}].versionNumber", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_html_hidden_2 = _jspx_th_html_hidden_2.doStartTag();
    if (_jspx_th_html_hidden_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_2);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_2);
    return false;
  }

  private boolean _jspx_meth_html_select_1(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_iterate_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:select
    org.kuali.rice.kns.web.taglib.html.KNSSelectTag _jspx_th_html_select_1 = (org.kuali.rice.kns.web.taglib.html.KNSSelectTag) _jspx_tagPool_html_select_title_property.get(org.kuali.rice.kns.web.taglib.html.KNSSelectTag.class);
    _jspx_th_html_select_1.setPageContext(_jspx_page_context);
    _jspx_th_html_select_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_iterate_0);
    _jspx_th_html_select_1.setTitle((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${accessibleTitle}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_select_1.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("adHocRoutePerson[${ctr}].actionRequested", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_html_select_1 = _jspx_th_html_select_1.doStartTag();
    if (_jspx_eval_html_select_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_select_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_select_1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                            ");
        if (_jspx_meth_c_set_5(_jspx_th_html_select_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                        ");
        if (_jspx_meth_html_options_1(_jspx_th_html_select_1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_html_select_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_html_select_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_select_title_property.reuse(_jspx_th_html_select_1);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_select_title_property.reuse(_jspx_th_html_select_1);
    return false;
  }

  private boolean _jspx_meth_c_set_5(javax.servlet.jsp.tagext.JspTag _jspx_th_html_select_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_5 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_5.setPageContext(_jspx_page_context);
    _jspx_th_c_set_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_select_1);
    _jspx_th_c_set_5.setVar("actionRequestCodes");
    _jspx_th_c_set_5.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.adHocActionRequestCodes}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_5 = _jspx_th_c_set_5.doStartTag();
    if (_jspx_th_c_set_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_5);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_5);
    return false;
  }

  private boolean _jspx_meth_html_options_1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_select_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:options
    org.apache.struts.taglib.html.OptionsTag _jspx_th_html_options_1 = (org.apache.struts.taglib.html.OptionsTag) _jspx_tagPool_html_options_property_labelProperty_collection_nobody.get(org.apache.struts.taglib.html.OptionsTag.class);
    _jspx_th_html_options_1.setPageContext(_jspx_page_context);
    _jspx_th_html_options_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_select_1);
    _jspx_th_html_options_1.setCollection("actionRequestCodes");
    _jspx_th_html_options_1.setProperty("key");
    _jspx_th_html_options_1.setLabelProperty("value");
    int _jspx_eval_html_options_1 = _jspx_th_html_options_1.doStartTag();
    if (_jspx_th_html_options_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_options_property_labelProperty_collection_nobody.reuse(_jspx_th_html_options_1);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_options_property_labelProperty_collection_nobody.reuse(_jspx_th_html_options_1);
    return false;
  }

  private boolean _jspx_meth_kul_checkErrors_1(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_iterate_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:checkErrors
    org.apache.jsp.tag.web.kr.checkErrors_tag _jspx_th_kul_checkErrors_1 = new org.apache.jsp.tag.web.kr.checkErrors_tag();
    _jspx_th_kul_checkErrors_1.setJspContext(_jspx_page_context);
    _jspx_th_kul_checkErrors_1.setParent(_jspx_th_logic_iterate_0);
    _jspx_th_kul_checkErrors_1.setKeyMatch((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("adHocRoutePerson[${ctr}].id", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_checkErrors_1.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_user_1(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_iterate_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:user
    org.apache.jsp.tag.web.kr.user_tag _jspx_th_kul_user_1 = new org.apache.jsp.tag.web.kr.user_tag();
    _jspx_th_kul_user_1.setJspContext(_jspx_page_context);
    _jspx_th_kul_user_1.setParent(_jspx_th_logic_iterate_0);
    _jspx_th_kul_user_1.setUserIdFieldName((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("adHocRoutePerson[${ctr}].id", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_1.setUserId((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.document.adHocRoutePersons[ctr].id}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_1.setUniversalIdFieldName("");
    _jspx_th_kul_user_1.setUniversalId("");
    _jspx_th_kul_user_1.setUserNameFieldName((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("adHocRoutePerson[${ctr}].name", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_1.setUserName((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.document.adHocRoutePersons[ctr].name}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_1.setReadOnly((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${displayReadOnly}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_1.setRenderOtherFields("true");
    _jspx_th_kul_user_1.setFieldConversions((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("principalName:adHocRoutePerson[${ctr}].id,name:adHocRoutePerson[${ctr}].name", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_1.setLookupParameters((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("adHocRoutePerson[${ctr}].id:principalName", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_1.setHasErrors((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${hasErrors}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_1.doTag();
    return false;
  }

  private boolean _jspx_meth_html_image_1(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_iterate_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_1 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_1.setPageContext(_jspx_page_context);
    _jspx_th_html_image_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_iterate_0);
    _jspx_th_html_image_1.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("methodToCall.deleteAdHocRoutePerson.line${ctr}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_1.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_1.setTitle("Delete Additional Ad Hoc Person");
    _jspx_th_html_image_1.setAlt("Delete Additional Ad Hoc Person");
    _jspx_th_html_image_1.setStyleClass("tinybutton");
    int _jspx_eval_html_image_1 = _jspx_th_html_image_1.doStartTag();
    if (_jspx_th_html_image_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_1);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_1);
    return false;
  }

  private boolean _jspx_meth_kul_displayIfErrors_1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:displayIfErrors
    org.apache.jsp.tag.web.kr.displayIfErrors_tag _jspx_th_kul_displayIfErrors_1 = new org.apache.jsp.tag.web.kr.displayIfErrors_tag();
    _jspx_th_kul_displayIfErrors_1.setJspContext(_jspx_page_context);
    _jspx_th_kul_displayIfErrors_1.setParent(_jspx_parent);
    _jspx_th_kul_displayIfErrors_1.setKeyMatch((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${Constants.AD_HOC_ROUTE_WORKGROUP_ERRORS}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_displayIfErrors_1.setJspBody(new adHocRecipients_tagHelper( 2, _jspx_page_context, _jspx_th_kul_displayIfErrors_1, null));
    _jspx_th_kul_displayIfErrors_1.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_errors_1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:errors
    org.apache.jsp.tag.web.kr.errors_tag _jspx_th_kul_errors_1 = new org.apache.jsp.tag.web.kr.errors_tag();
    _jspx_th_kul_errors_1.setJspContext(_jspx_page_context);
    _jspx_th_kul_errors_1.setParent(_jspx_parent);
    _jspx_th_kul_errors_1.setKeyMatch((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${Constants.AD_HOC_ROUTE_WORKGROUP_ERRORS}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_errors_1.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_3(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_3 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_3.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_3.setParent(_jspx_parent);
    _jspx_th_kul_htmlAttributeHeaderCell_3.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary.AdHocRouteWorkgroup.attributes.actionRequested}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_3.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_3.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_4(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_4 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_4.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_4.setParent(_jspx_parent);
    _jspx_th_kul_htmlAttributeHeaderCell_4.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary.PersonDocumentGroup.attributes.namespaceCode}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_4.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_4.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_5(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_5 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_5.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_5.setParent(_jspx_parent);
    _jspx_th_kul_htmlAttributeHeaderCell_5.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary.PersonDocumentGroup.attributes.groupName}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_5.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_5.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_6(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_6 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_6.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_6.setParent(_jspx_parent);
    _jspx_th_kul_htmlAttributeHeaderCell_6.setLiteralLabel("Actions");
    _jspx_th_kul_htmlAttributeHeaderCell_6.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_6.doTag();
    return false;
  }

  private boolean _jspx_meth_html_hidden_3(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.kuali.rice.kns.web.taglib.html.KNSHiddenTag _jspx_th_html_hidden_3 = (org.kuali.rice.kns.web.taglib.html.KNSHiddenTag) _jspx_tagPool_html_hidden_property_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSHiddenTag.class);
    _jspx_th_html_hidden_3.setPageContext(_jspx_page_context);
    _jspx_th_html_hidden_3.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_html_hidden_3.setProperty("newAdHocRouteWorkgroup.type");
    int _jspx_eval_html_hidden_3 = _jspx_th_html_hidden_3.doStartTag();
    if (_jspx_th_html_hidden_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_3);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_3);
    return false;
  }

  private boolean _jspx_meth_html_select_2(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:select
    org.kuali.rice.kns.web.taglib.html.KNSSelectTag _jspx_th_html_select_2 = (org.kuali.rice.kns.web.taglib.html.KNSSelectTag) _jspx_tagPool_html_select_title_property.get(org.kuali.rice.kns.web.taglib.html.KNSSelectTag.class);
    _jspx_th_html_select_2.setPageContext(_jspx_page_context);
    _jspx_th_html_select_2.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_html_select_2.setTitle((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${accessibleTitle2}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_select_2.setProperty("newAdHocRouteWorkgroup.actionRequested");
    int _jspx_eval_html_select_2 = _jspx_th_html_select_2.doStartTag();
    if (_jspx_eval_html_select_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_select_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_select_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_select_2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                          ");
        if (_jspx_meth_c_set_6(_jspx_th_html_select_2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                        ");
        if (_jspx_meth_html_options_2(_jspx_th_html_select_2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_html_select_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_select_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_html_select_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_select_title_property.reuse(_jspx_th_html_select_2);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_select_title_property.reuse(_jspx_th_html_select_2);
    return false;
  }

  private boolean _jspx_meth_c_set_6(javax.servlet.jsp.tagext.JspTag _jspx_th_html_select_2, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_6 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_6.setPageContext(_jspx_page_context);
    _jspx_th_c_set_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_select_2);
    _jspx_th_c_set_6.setVar("actionRequestCodes");
    _jspx_th_c_set_6.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.adHocActionRequestCodes}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_6 = _jspx_th_c_set_6.doStartTag();
    if (_jspx_th_c_set_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_6);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_6);
    return false;
  }

  private boolean _jspx_meth_html_options_2(javax.servlet.jsp.tagext.JspTag _jspx_th_html_select_2, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:options
    org.apache.struts.taglib.html.OptionsTag _jspx_th_html_options_2 = (org.apache.struts.taglib.html.OptionsTag) _jspx_tagPool_html_options_property_labelProperty_collection_nobody.get(org.apache.struts.taglib.html.OptionsTag.class);
    _jspx_th_html_options_2.setPageContext(_jspx_page_context);
    _jspx_th_html_options_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_select_2);
    _jspx_th_html_options_2.setCollection("actionRequestCodes");
    _jspx_th_html_options_2.setProperty("key");
    _jspx_th_html_options_2.setLabelProperty("value");
    int _jspx_eval_html_options_2 = _jspx_th_html_options_2.doStartTag();
    if (_jspx_th_html_options_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_options_property_labelProperty_collection_nobody.reuse(_jspx_th_html_options_2);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_options_property_labelProperty_collection_nobody.reuse(_jspx_th_html_options_2);
    return false;
  }

  private boolean _jspx_meth_kul_htmlControlAttribute_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlControlAttribute
    org.apache.jsp.tag.web.kr.htmlControlAttribute_tag _jspx_th_kul_htmlControlAttribute_0 = new org.apache.jsp.tag.web.kr.htmlControlAttribute_tag();
    _jspx_th_kul_htmlControlAttribute_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlControlAttribute_0.setParent(_jspx_parent);
    _jspx_th_kul_htmlControlAttribute_0.setProperty("newAdHocRouteWorkgroup.recipientNamespaceCode");
    _jspx_th_kul_htmlControlAttribute_0.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary.PersonDocumentGroup.attributes.namespaceCode}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_0.setReadOnly((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${displayReadOnly}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_0.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlControlAttribute_1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlControlAttribute
    org.apache.jsp.tag.web.kr.htmlControlAttribute_tag _jspx_th_kul_htmlControlAttribute_1 = new org.apache.jsp.tag.web.kr.htmlControlAttribute_tag();
    _jspx_th_kul_htmlControlAttribute_1.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlControlAttribute_1.setParent(_jspx_parent);
    _jspx_th_kul_htmlControlAttribute_1.setProperty("newAdHocRouteWorkgroup.recipientName");
    _jspx_th_kul_htmlControlAttribute_1.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary.PersonDocumentGroup.attributes.groupName}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_1.setReadOnly((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${displayReadOnly}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_1.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_workflowWorkgroupLookup_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:workflowWorkgroupLookup
    org.apache.jsp.tag.web.kr.workflowWorkgroupLookup_tag _jspx_th_kul_workflowWorkgroupLookup_0 = new org.apache.jsp.tag.web.kr.workflowWorkgroupLookup_tag();
    _jspx_th_kul_workflowWorkgroupLookup_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_workflowWorkgroupLookup_0.setParent(_jspx_parent);
    _jspx_th_kul_workflowWorkgroupLookup_0.setFieldConversions("namespaceCode:newAdHocRouteWorkgroup.recipientNamespaceCode,groupName:newAdHocRouteWorkgroup.recipientName");
    _jspx_th_kul_workflowWorkgroupLookup_0.setLookupParameters("newAdHocRouteWorkgroup.recipientNamespaceCode:namespaceCode,newAdHocRouteWorkgroup.recipientName:groupName");
    _jspx_th_kul_workflowWorkgroupLookup_0.doTag();
    return false;
  }

  private boolean _jspx_meth_html_image_2(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_2 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_2.setPageContext(_jspx_page_context);
    _jspx_th_html_image_2.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_html_image_2.setProperty("methodToCall.insertAdHocRouteWorkgroup");
    _jspx_th_html_image_2.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_2.setTitle("Insert Additional Ad Hoc Workgroup");
    _jspx_th_html_image_2.setAlt("Insert Additional Ad Hoc Workgroup");
    _jspx_th_html_image_2.setStyleClass("tinybutton");
    int _jspx_eval_html_image_2 = _jspx_th_html_image_2.doStartTag();
    if (_jspx_th_html_image_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_2);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_2);
    return false;
  }

  private boolean _jspx_meth_html_hidden_4(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_iterate_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.kuali.rice.kns.web.taglib.html.KNSHiddenTag _jspx_th_html_hidden_4 = (org.kuali.rice.kns.web.taglib.html.KNSHiddenTag) _jspx_tagPool_html_hidden_property_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSHiddenTag.class);
    _jspx_th_html_hidden_4.setPageContext(_jspx_page_context);
    _jspx_th_html_hidden_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_iterate_1);
    _jspx_th_html_hidden_4.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("adHocRouteWorkgroup[${ctr}].type", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_html_hidden_4 = _jspx_th_html_hidden_4.doStartTag();
    if (_jspx_th_html_hidden_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_4);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_4);
    return false;
  }

  private boolean _jspx_meth_html_hidden_5(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_iterate_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.kuali.rice.kns.web.taglib.html.KNSHiddenTag _jspx_th_html_hidden_5 = (org.kuali.rice.kns.web.taglib.html.KNSHiddenTag) _jspx_tagPool_html_hidden_property_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSHiddenTag.class);
    _jspx_th_html_hidden_5.setPageContext(_jspx_page_context);
    _jspx_th_html_hidden_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_iterate_1);
    _jspx_th_html_hidden_5.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("adHocRouteWorkgroup[${ctr}].versionNumber", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_html_hidden_5 = _jspx_th_html_hidden_5.doStartTag();
    if (_jspx_th_html_hidden_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_5);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_5);
    return false;
  }

  private boolean _jspx_meth_html_select_3(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_iterate_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:select
    org.kuali.rice.kns.web.taglib.html.KNSSelectTag _jspx_th_html_select_3 = (org.kuali.rice.kns.web.taglib.html.KNSSelectTag) _jspx_tagPool_html_select_title_property.get(org.kuali.rice.kns.web.taglib.html.KNSSelectTag.class);
    _jspx_th_html_select_3.setPageContext(_jspx_page_context);
    _jspx_th_html_select_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_iterate_1);
    _jspx_th_html_select_3.setTitle((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${accessibleTitle2}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_select_3.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("adHocRouteWorkgroup[${ctr}].actionRequested", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_html_select_3 = _jspx_th_html_select_3.doStartTag();
    if (_jspx_eval_html_select_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_select_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_select_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_select_3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                              ");
        if (_jspx_meth_c_set_7(_jspx_th_html_select_3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                            ");
        if (_jspx_meth_html_options_3(_jspx_th_html_select_3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_html_select_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_select_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_html_select_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_select_title_property.reuse(_jspx_th_html_select_3);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_select_title_property.reuse(_jspx_th_html_select_3);
    return false;
  }

  private boolean _jspx_meth_c_set_7(javax.servlet.jsp.tagext.JspTag _jspx_th_html_select_3, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_7 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_7.setPageContext(_jspx_page_context);
    _jspx_th_c_set_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_select_3);
    _jspx_th_c_set_7.setVar("actionRequestCodes");
    _jspx_th_c_set_7.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.adHocActionRequestCodes}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_7 = _jspx_th_c_set_7.doStartTag();
    if (_jspx_th_c_set_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_7);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_7);
    return false;
  }

  private boolean _jspx_meth_html_options_3(javax.servlet.jsp.tagext.JspTag _jspx_th_html_select_3, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:options
    org.apache.struts.taglib.html.OptionsTag _jspx_th_html_options_3 = (org.apache.struts.taglib.html.OptionsTag) _jspx_tagPool_html_options_property_labelProperty_collection_nobody.get(org.apache.struts.taglib.html.OptionsTag.class);
    _jspx_th_html_options_3.setPageContext(_jspx_page_context);
    _jspx_th_html_options_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_select_3);
    _jspx_th_html_options_3.setCollection("actionRequestCodes");
    _jspx_th_html_options_3.setProperty("key");
    _jspx_th_html_options_3.setLabelProperty("value");
    int _jspx_eval_html_options_3 = _jspx_th_html_options_3.doStartTag();
    if (_jspx_th_html_options_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_options_property_labelProperty_collection_nobody.reuse(_jspx_th_html_options_3);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_options_property_labelProperty_collection_nobody.reuse(_jspx_th_html_options_3);
    return false;
  }

  private boolean _jspx_meth_kul_htmlControlAttribute_2(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_iterate_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlControlAttribute
    org.apache.jsp.tag.web.kr.htmlControlAttribute_tag _jspx_th_kul_htmlControlAttribute_2 = new org.apache.jsp.tag.web.kr.htmlControlAttribute_tag();
    _jspx_th_kul_htmlControlAttribute_2.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlControlAttribute_2.setParent(_jspx_th_logic_iterate_1);
    _jspx_th_kul_htmlControlAttribute_2.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("adHocRouteWorkgroup[${ctr}].recipientNamespaceCode", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_2.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary.PersonDocumentGroup.attributes.namespaceCode}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_2.setReadOnly("displayReadOnly");
    _jspx_th_kul_htmlControlAttribute_2.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlControlAttribute_3(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_iterate_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlControlAttribute
    org.apache.jsp.tag.web.kr.htmlControlAttribute_tag _jspx_th_kul_htmlControlAttribute_3 = new org.apache.jsp.tag.web.kr.htmlControlAttribute_tag();
    _jspx_th_kul_htmlControlAttribute_3.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlControlAttribute_3.setParent(_jspx_th_logic_iterate_1);
    _jspx_th_kul_htmlControlAttribute_3.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("adHocRouteWorkgroup[${ctr}].recipientName", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_3.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary.PersonDocumentGroup.attributes.groupName}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_3.setReadOnly("displayReadOnly");
    _jspx_th_kul_htmlControlAttribute_3.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_workflowWorkgroupLookup_1(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_iterate_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:workflowWorkgroupLookup
    org.apache.jsp.tag.web.kr.workflowWorkgroupLookup_tag _jspx_th_kul_workflowWorkgroupLookup_1 = new org.apache.jsp.tag.web.kr.workflowWorkgroupLookup_tag();
    _jspx_th_kul_workflowWorkgroupLookup_1.setJspContext(_jspx_page_context);
    _jspx_th_kul_workflowWorkgroupLookup_1.setParent(_jspx_th_logic_iterate_1);
    _jspx_th_kul_workflowWorkgroupLookup_1.setFieldConversions((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("namespaceCode:adHocRouteWorkgroup[${ctr}].recipientNamespaceCode,groupName:adHocRouteWorkgroup[${ctr}].recipientName", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_workflowWorkgroupLookup_1.setLookupParameters((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("adHocRouteWorkgroup[${ctr}].recipientNamespaceCode:namespaceCode,adHocRouteWorkgroup[${ctr}].recipientName:groupName", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_workflowWorkgroupLookup_1.doTag();
    return false;
  }

  private boolean _jspx_meth_html_image_3(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_iterate_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_3 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_3.setPageContext(_jspx_page_context);
    _jspx_th_html_image_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_iterate_1);
    _jspx_th_html_image_3.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("methodToCall.deleteAdHocRouteWorkgroup.line${ctr}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_3.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_3.setTitle("Delete Additional Ad Hoc Workgroup");
    _jspx_th_html_image_3.setAlt("Delete Additional Ad Hoc Workgroup");
    _jspx_th_html_image_3.setStyleClass("tinybutton");
    int _jspx_eval_html_image_3 = _jspx_th_html_image_3.doStartTag();
    if (_jspx_th_html_image_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_3);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_3);
    return false;
  }

  private class adHocRecipients_tagHelper
      extends org.apache.jasper.runtime.JspFragmentHelper
  {
    private javax.servlet.jsp.tagext.JspTag _jspx_parent;
    private int[] _jspx_push_body_count;

    public adHocRecipients_tagHelper( int discriminator, JspContext jspContext, javax.servlet.jsp.tagext.JspTag _jspx_parent, int[] _jspx_push_body_count ) {
      super( discriminator, jspContext, _jspx_parent );
      this._jspx_parent = _jspx_parent;
      this._jspx_push_body_count = _jspx_push_body_count;
    }
    public void invoke0( JspWriter out ) 
      throws Throwable
    {
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <div class=\"tab-container\" align=center>\r\n");
      out.write("    <h3>Ad Hoc Recipients</h3>\r\n");
      out.write("            <table cellpadding=\"0\" cellspacing=\"0\" class=\"datatable\" summary=\"view/edit ad hoc recipients\">\r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("              ");
      if (_jspx_meth_kul_displayIfErrors_0(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("              <tr>\r\n");
      out.write("                <td colspan=4 class=\"tab-subhead\">Person Requests:</td>\r\n");
      out.write("              </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                  ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_0(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                  ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_1(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_2(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("              </tr>\r\n");
      out.write("\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td class=\"infoline\" ><div align=center>\r\n");
      out.write("                        ");
      if (_jspx_meth_html_hidden_0(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                        ");
      out.write("\r\n");
      out.write("                        ");
      if (_jspx_meth_c_set_0(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                                                ");
      if (_jspx_meth_c_set_1(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                        ");
      if (_jspx_meth_c_if_1(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                        ");
      if (_jspx_meth_c_if_2(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                        ");
      if (_jspx_meth_html_select_0(_jspx_parent, _jspx_page_context))
        return;
      out.write("</div>\r\n");
      out.write("                    </td>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_kul_checkErrors_0(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("                    <td class=\"infoline\" colspan=\"2\" ><div align=center>\r\n");
      out.write("                        ");
      if (_jspx_meth_kul_user_0(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td class=\"infoline\" ><div align=center>\r\n");
      out.write("                        ");
      if (_jspx_meth_html_image_0(_jspx_parent, _jspx_page_context))
        return;
      out.write("</div>\r\n");
      out.write("                    </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("              ");
      //  logic:iterate
      org.apache.struts.taglib.logic.IterateTag _jspx_th_logic_iterate_0 = (org.apache.struts.taglib.logic.IterateTag) _jspx_tagPool_logic_iterate_property_name_indexId_id.get(org.apache.struts.taglib.logic.IterateTag.class);
      _jspx_th_logic_iterate_0.setPageContext(_jspx_page_context);
      _jspx_th_logic_iterate_0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
      _jspx_th_logic_iterate_0.setName("KualiForm");
      _jspx_th_logic_iterate_0.setId("person");
      _jspx_th_logic_iterate_0.setProperty("adHocRoutePersons");
      _jspx_th_logic_iterate_0.setIndexId("ctr");
      int _jspx_eval_logic_iterate_0 = _jspx_th_logic_iterate_0.doStartTag();
      if (_jspx_eval_logic_iterate_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        java.lang.Object person = null;
        java.lang.Integer ctr = null;
        if (_jspx_eval_logic_iterate_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_logic_iterate_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_logic_iterate_0.doInitBody();
        }
        person = (java.lang.Object) _jspx_page_context.findAttribute("person");
        ctr = (java.lang.Integer) _jspx_page_context.findAttribute("ctr");
        do {
          out.write("\r\n");
          out.write("                  <tr>\r\n");
          out.write("                      <td class=\"datacell center\" ><div align=center>\r\n");
          out.write("                          ");
          if (_jspx_meth_html_hidden_1(_jspx_th_logic_iterate_0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("                          ");
          if (_jspx_meth_html_hidden_2(_jspx_th_logic_iterate_0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("                          ");
          if (_jspx_meth_html_select_1(_jspx_th_logic_iterate_0, _jspx_page_context))
            return;
          out.write("</div>\r\n");
          out.write("                      </td>\r\n");
          out.write("                      <td class=\"datacell center\" colspan=\"2\"><div align=center>\r\n");
          out.write("\t\t\t\t\t\t");
          if (_jspx_meth_kul_checkErrors_1(_jspx_th_logic_iterate_0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("                        ");
          if (_jspx_meth_kul_user_1(_jspx_th_logic_iterate_0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("                      </td>\r\n");
          out.write("                          <td class=\"datacell center\" ><div align=center>\r\n");
          out.write("                            ");
          if (_jspx_meth_html_image_1(_jspx_th_logic_iterate_0, _jspx_page_context))
            return;
          out.write("</div>\r\n");
          out.write("                      </td>\r\n");
          out.write("                  </tr>\r\n");
          out.write("              ");
          int evalDoAfterBody = _jspx_th_logic_iterate_0.doAfterBody();
          person = (java.lang.Object) _jspx_page_context.findAttribute("person");
          ctr = (java.lang.Integer) _jspx_page_context.findAttribute("ctr");
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_logic_iterate_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_logic_iterate_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_logic_iterate_property_name_indexId_id.reuse(_jspx_th_logic_iterate_0);
        throw new SkipPageException();
      }
      _jspx_tagPool_logic_iterate_property_name_indexId_id.reuse(_jspx_th_logic_iterate_0);
      out.write("\r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("        ");
      if (_jspx_meth_kul_displayIfErrors_1(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("          <tr>\r\n");
      out.write("                <td colspan=4 class=\"tab-subhead\">Ad Hoc Group Requests:</td>\r\n");
      out.write("              </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                  ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_3(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                  ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_4(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                  ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_5(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                  ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_6(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("              </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td class=\"infoline\" ><div align=center>\r\n");
      out.write("                        ");
      if (_jspx_meth_html_hidden_3(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                        ");
      if (_jspx_meth_html_select_2(_jspx_parent, _jspx_page_context))
        return;
      out.write("</div>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td class=\"infoline\" ><div align=center>\r\n");
      out.write("                        ");
      if (_jspx_meth_kul_htmlControlAttribute_0(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td class=\"infoline\" ><div align=center>\r\n");
      out.write("                        ");
      if (_jspx_meth_kul_htmlControlAttribute_1(_jspx_parent, _jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                        ");
      if (_jspx_meth_kul_workflowWorkgroupLookup_0(_jspx_parent, _jspx_page_context))
        return;
      out.write("</div>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td class=\"infoline\" ><div align=center>\r\n");
      out.write("                        ");
      if (_jspx_meth_html_image_2(_jspx_parent, _jspx_page_context))
        return;
      out.write("</div>\r\n");
      out.write("                    </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                ");
      //  logic:iterate
      org.apache.struts.taglib.logic.IterateTag _jspx_th_logic_iterate_1 = (org.apache.struts.taglib.logic.IterateTag) _jspx_tagPool_logic_iterate_property_name_indexId_id.get(org.apache.struts.taglib.logic.IterateTag.class);
      _jspx_th_logic_iterate_1.setPageContext(_jspx_page_context);
      _jspx_th_logic_iterate_1.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
      _jspx_th_logic_iterate_1.setName("KualiForm");
      _jspx_th_logic_iterate_1.setId("workgroup");
      _jspx_th_logic_iterate_1.setProperty("adHocRouteWorkgroups");
      _jspx_th_logic_iterate_1.setIndexId("ctr");
      int _jspx_eval_logic_iterate_1 = _jspx_th_logic_iterate_1.doStartTag();
      if (_jspx_eval_logic_iterate_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        java.lang.Object workgroup = null;
        java.lang.Integer ctr = null;
        if (_jspx_eval_logic_iterate_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_logic_iterate_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_logic_iterate_1.doInitBody();
        }
        workgroup = (java.lang.Object) _jspx_page_context.findAttribute("workgroup");
        ctr = (java.lang.Integer) _jspx_page_context.findAttribute("ctr");
        do {
          out.write("\r\n");
          out.write("                    <tr>\r\n");
          out.write("                        <td class=\"datacell center\" ><div align=center>\r\n");
          out.write("                            ");
          if (_jspx_meth_html_hidden_4(_jspx_th_logic_iterate_1, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("                            ");
          if (_jspx_meth_html_hidden_5(_jspx_th_logic_iterate_1, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("                            ");
          if (_jspx_meth_html_select_3(_jspx_th_logic_iterate_1, _jspx_page_context))
            return;
          out.write("</div>\r\n");
          out.write("                        </td>\r\n");
          out.write("                        <td class=\"datacell center\"><div align=center>\r\n");
          out.write("                            ");
          if (_jspx_meth_kul_htmlControlAttribute_2(_jspx_th_logic_iterate_1, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("                        </td>\r\n");
          out.write("                        <td class=\"datacell center\"><div align=center>\r\n");
          out.write("                            ");
          if (_jspx_meth_kul_htmlControlAttribute_3(_jspx_th_logic_iterate_1, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("                            ");
          if (_jspx_meth_kul_workflowWorkgroupLookup_1(_jspx_th_logic_iterate_1, _jspx_page_context))
            return;
          out.write("</div>\r\n");
          out.write("                       </td>\r\n");
          out.write("                        <td class=\"datacell center\" ><div align=center>\r\n");
          out.write("                            ");
          if (_jspx_meth_html_image_3(_jspx_th_logic_iterate_1, _jspx_page_context))
            return;
          out.write("</div>\r\n");
          out.write("                        </td>\r\n");
          out.write("                    </tr>\r\n");
          out.write("                ");
          int evalDoAfterBody = _jspx_th_logic_iterate_1.doAfterBody();
          workgroup = (java.lang.Object) _jspx_page_context.findAttribute("workgroup");
          ctr = (java.lang.Integer) _jspx_page_context.findAttribute("ctr");
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_logic_iterate_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_logic_iterate_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_logic_iterate_property_name_indexId_id.reuse(_jspx_th_logic_iterate_1);
        throw new SkipPageException();
      }
      _jspx_tagPool_logic_iterate_property_name_indexId_id.reuse(_jspx_th_logic_iterate_1);
      out.write("\r\n");
      out.write("          </table>\r\n");
      out.write("          </div>\r\n");
      out.write("      ");
      return;
    }
    public boolean invoke1( JspWriter out ) 
      throws Throwable
    {
      out.write("\r\n");
      out.write("          <tr>\r\n");
      out.write("              <th colspan=4>\r\n");
      out.write("                ");
      if (_jspx_meth_kul_errors_0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("              </th>\r\n");
      out.write("            </tr>\r\n");
      out.write("        ");
      return false;
    }
    public boolean invoke2( JspWriter out ) 
      throws Throwable
    {
      out.write("\r\n");
      out.write("          <tr>\r\n");
      out.write("              <th colspan=4>\r\n");
      out.write("                ");
      if (_jspx_meth_kul_errors_1(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("              </th>\r\n");
      out.write("            </tr>\r\n");
      out.write("        ");
      return false;
    }
    public void invoke( java.io.Writer writer )
      throws JspException
    {
      JspWriter out = null;
      if( writer != null ) {
        out = this.jspContext.pushBody(writer);
      } else {
        out = this.jspContext.getOut();
      }
      try {
        switch( this.discriminator ) {
          case 0:
            invoke0( out );
            break;
          case 1:
            invoke1( out );
            break;
          case 2:
            invoke2( out );
            break;
        }
      }
      catch( Throwable e ) {
        if (e instanceof SkipPageException)
            throw (SkipPageException) e;
        throw new JspException( e );
      }
      finally {
        if( writer != null ) {
          this.jspContext.popBody();
        }
      }
    }
  }
}
