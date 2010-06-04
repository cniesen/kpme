package org.apache.jsp.tag.web.kr;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class documentOverview_tag
    extends javax.servlet.jsp.tagext.SimpleTagSupport
    implements org.apache.jasper.runtime.JspSourceDependent {


static private org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_0;

static {
  _jspx_fnmap_0= org.apache.jasper.runtime.ProtectedFunctionMapper.getInstance();
  _jspx_fnmap_0.mapFunction("fn:replace", org.apache.taglibs.standard.functions.Functions.class, "replace", new Class[] {java.lang.String.class, java.lang.String.class, java.lang.String.class});
  _jspx_fnmap_0.mapFunction("fn:escapeXml", org.apache.taglibs.standard.functions.Functions.class, "escapeXml", new Class[] {java.lang.String.class});
}

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
    _jspx_dependants.add("/WEB-INF/tags/kr/dd/evalNameToMap.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/tabTop.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/checkErrors.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/inquiry.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/errors.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/htmlAttributeHeaderCell.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/htmlAttributeLabel.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/htmlControlAttribute.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/fieldShowErrorIcon.tag");
  }

  private JspContext jspContext;
  private java.io.Writer _jspx_sout;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_set_var_value_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_hidden_property_nobody;

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
  private java.util.Map editingMode;

  public java.util.Map getEditingMode() {
    return this.editingMode;
  }

  public void setEditingMode(java.util.Map editingMode) {
    this.editingMode = editingMode;
  }

  public Object getDependants() {
    return _jspx_dependants;
  }

  private void _jspInit(ServletConfig config) {
    _jspx_tagPool_c_set_var_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_hidden_property_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_set_var_value_nobody.release();
    _jspx_tagPool_html_hidden_property_nobody.release();
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
    if( getEditingMode() != null ) 
      _jspx_page_context.setAttribute("editingMode", getEditingMode());

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
      if (_jspx_meth_c_set_0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_set_1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_set_2(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_set_3(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_set_4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      //  dd:evalNameToMap
      org.apache.jsp.tag.web.kr.dd.evalNameToMap_tag _jspx_th_dd_evalNameToMap_0 = new org.apache.jsp.tag.web.kr.dd.evalNameToMap_tag();
      java.util.HashMap _jspx_th_dd_evalNameToMap_0_aliasMap = new java.util.HashMap();
      _jspx_th_dd_evalNameToMap_0_aliasMap.put("valueHolder", "documentAttributes");
      _jspx_th_dd_evalNameToMap_0.setJspContext(_jspx_page_context, _jspx_th_dd_evalNameToMap_0_aliasMap);
      _jspx_th_dd_evalNameToMap_0.setMapName((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("DataDictionary.${KualiForm.docTypeName}.attributes", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      _jspx_th_dd_evalNameToMap_0.setReturnVar("documentAttributes");
      _jspx_th_dd_evalNameToMap_0.doTag();
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_kul_tabTop_0(_jspx_page_context))
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

  private boolean _jspx_meth_c_set_0(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_0.setPageContext(_jspx_page_context);
    _jspx_th_c_set_0.setParent(null);
    _jspx_th_c_set_0.setVar("isMaintenance");
    _jspx_th_c_set_0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.class.name eq 'org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm' || maintenanceViewMode eq Constants.PARAM_MAINTENANCE_VIEW_MODE_MAINTENANCE}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
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
    _jspx_th_c_set_1.setVar("readOnly");
    _jspx_th_c_set_1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ ! KualiForm.documentActions[Constants.KUALI_ACTION_CAN_EDIT__DOCUMENT_OVERVIEW]}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
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
    _jspx_th_c_set_2.setVar("docHeaderAttributes");
    _jspx_th_c_set_2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary.DocumentHeader.attributes}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_2 = _jspx_th_c_set_2.doStartTag();
    if (_jspx_th_c_set_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_2);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_2);
    return false;
  }

  private boolean _jspx_meth_c_set_3(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_3 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_3.setPageContext(_jspx_page_context);
    _jspx_th_c_set_3.setParent(null);
    _jspx_th_c_set_3.setVar("documentTypeName");
    _jspx_th_c_set_3.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.docTypeName}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_3 = _jspx_th_c_set_3.doStartTag();
    if (_jspx_th_c_set_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_3);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_3);
    return false;
  }

  private boolean _jspx_meth_c_set_4(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_4 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_4.setPageContext(_jspx_page_context);
    _jspx_th_c_set_4.setParent(null);
    _jspx_th_c_set_4.setVar("documentEntry");
    _jspx_th_c_set_4.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary[documentTypeName]}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_4 = _jspx_th_c_set_4.doStartTag();
    if (_jspx_th_c_set_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_4);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_4);
    return false;
  }

  private boolean _jspx_meth_kul_tabTop_0(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:tabTop
    org.apache.jsp.tag.web.kr.tabTop_tag _jspx_th_kul_tabTop_0 = new org.apache.jsp.tag.web.kr.tabTop_tag();
    _jspx_th_kul_tabTop_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_tabTop_0.setTabTitle("Document Overview");
    _jspx_th_kul_tabTop_0.setDefaultOpen("true");
    _jspx_th_kul_tabTop_0.setTabErrorKey((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${Constants.DOCUMENT_ERRORS}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_tabTop_0.setJspBody(new documentOverview_tagHelper( 0, _jspx_page_context, _jspx_th_kul_tabTop_0, null));
    _jspx_th_kul_tabTop_0.doTag();
    return false;
  }

  private boolean _jspx_meth_html_hidden_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.kuali.rice.kns.web.taglib.html.KNSHiddenTag _jspx_th_html_hidden_0 = (org.kuali.rice.kns.web.taglib.html.KNSHiddenTag) _jspx_tagPool_html_hidden_property_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSHiddenTag.class);
    _jspx_th_html_hidden_0.setPageContext(_jspx_page_context);
    _jspx_th_html_hidden_0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_html_hidden_0.setProperty("document.documentHeader.documentNumber");
    int _jspx_eval_html_hidden_0 = _jspx_th_html_hidden_0.doStartTag();
    if (_jspx_th_html_hidden_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_hidden_property_nobody.reuse(_jspx_th_html_hidden_0);
    return false;
  }

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_0 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_0.setParent(_jspx_parent);
    _jspx_th_kul_htmlAttributeHeaderCell_0.setLabelFor("document.documentHeader.documentDescription");
    _jspx_th_kul_htmlAttributeHeaderCell_0.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${docHeaderAttributes.documentDescription}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_0.setHorizontal("true");
    _jspx_th_kul_htmlAttributeHeaderCell_0.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlControlAttribute_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlControlAttribute
    org.apache.jsp.tag.web.kr.htmlControlAttribute_tag _jspx_th_kul_htmlControlAttribute_0 = new org.apache.jsp.tag.web.kr.htmlControlAttribute_tag();
    _jspx_th_kul_htmlControlAttribute_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlControlAttribute_0.setParent(_jspx_parent);
    _jspx_th_kul_htmlControlAttribute_0.setProperty("document.documentHeader.documentDescription");
    _jspx_th_kul_htmlControlAttribute_0.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${docHeaderAttributes.documentDescription}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_0.setReadOnly((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${readOnly}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_0.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_1 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_1.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_1.setParent(_jspx_parent);
    _jspx_th_kul_htmlAttributeHeaderCell_1.setLabelFor("document.documentHeader.explanation");
    _jspx_th_kul_htmlAttributeHeaderCell_1.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${docHeaderAttributes.explanation}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_1.setHorizontal("true");
    _jspx_th_kul_htmlAttributeHeaderCell_1.setRowspan("2");
    _jspx_th_kul_htmlAttributeHeaderCell_1.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlControlAttribute_1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlControlAttribute
    org.apache.jsp.tag.web.kr.htmlControlAttribute_tag _jspx_th_kul_htmlControlAttribute_1 = new org.apache.jsp.tag.web.kr.htmlControlAttribute_tag();
    _jspx_th_kul_htmlControlAttribute_1.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlControlAttribute_1.setParent(_jspx_parent);
    _jspx_th_kul_htmlControlAttribute_1.setProperty("document.documentHeader.explanation");
    _jspx_th_kul_htmlControlAttribute_1.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${docHeaderAttributes.explanation}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_1.setReadOnly((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${readOnly}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_1.setReadOnlyAlternateDisplay((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fn:replace(fn:escapeXml(KualiForm.document.documentHeader.explanation), Constants.NEWLINE, '<br/>')}", java.lang.String.class, (PageContext)this.getJspContext(), _jspx_fnmap_0, false));
    _jspx_th_kul_htmlControlAttribute_1.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_2(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_2 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_2.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_2.setParent(_jspx_parent);
    _jspx_th_kul_htmlAttributeHeaderCell_2.setLabelFor("document.documentHeader.organizationDocumentNumber");
    _jspx_th_kul_htmlAttributeHeaderCell_2.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${docHeaderAttributes.organizationDocumentNumber}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_2.setHorizontal("true");
    _jspx_th_kul_htmlAttributeHeaderCell_2.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlControlAttribute_2(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlControlAttribute
    org.apache.jsp.tag.web.kr.htmlControlAttribute_tag _jspx_th_kul_htmlControlAttribute_2 = new org.apache.jsp.tag.web.kr.htmlControlAttribute_tag();
    _jspx_th_kul_htmlControlAttribute_2.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlControlAttribute_2.setParent(_jspx_parent);
    _jspx_th_kul_htmlControlAttribute_2.setProperty("document.documentHeader.organizationDocumentNumber");
    _jspx_th_kul_htmlControlAttribute_2.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${docHeaderAttributes.organizationDocumentNumber}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_2.setReadOnly((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${readOnly}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_2.doTag();
    return false;
  }

  private class documentOverview_tagHelper
      extends org.apache.jasper.runtime.JspFragmentHelper
  {
    private javax.servlet.jsp.tagext.JspTag _jspx_parent;
    private int[] _jspx_push_body_count;

    public documentOverview_tagHelper( int discriminator, JspContext jspContext, javax.servlet.jsp.tagext.JspTag _jspx_parent, int[] _jspx_push_body_count ) {
      super( discriminator, jspContext, _jspx_parent );
      this._jspx_parent = _jspx_parent;
      this._jspx_push_body_count = _jspx_push_body_count;
    }
    public boolean invoke0( JspWriter out ) 
      throws Throwable
    {
      out.write("\r\n");
      out.write("\t<div class=\"tab-container\" align=center>\r\n");
      out.write("\t\t  <!-- DOC OVERVIEW TABLE -->\r\n");
      out.write("\t\t  ");
      if (_jspx_meth_html_hidden_0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t  <h3>Document Overview</h3>\r\n");
      out.write("\t\t  <table cellpadding=\"0\" cellspacing=\"0\" class=\"datatable\" title=\"view/edit document overview information\" summary=\"view/edit document overview information\">\r\n");
      out.write("\t\t    <tr>\r\n");
      out.write("\t\t      ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t      <td align=\"left\" valign=\"middle\">\r\n");
      out.write("\t\t      \t");
      if (_jspx_meth_kul_htmlControlAttribute_0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t      </td>\r\n");
      out.write("\t\t      ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_1(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t      <td align=\"left\" valign=\"middle\" rowspan=\"2\">\r\n");
      out.write("                  ");
      if (_jspx_meth_kul_htmlControlAttribute_1(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("              </td>\r\n");
      out.write("\t\t    </tr>\r\n");
      out.write("\t\t    <tr>\r\n");
      out.write("\t\t\t  ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_2(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\t\t\t  \r\n");
      out.write("              <td align=\"left\" valign=\"middle\">\r\n");
      out.write("              \t");
      if (_jspx_meth_kul_htmlControlAttribute_2(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("              </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("          </table>\r\n");
      out.write("          ");
      ((org.apache.jasper.runtime.JspContextWrapper) this.jspContext).syncBeforeInvoke();
      _jspx_sout = null;
      if (getJspBody() != null)
        getJspBody().invoke(_jspx_sout);
      out.write("            \r\n");
      out.write("        </div>\r\n");
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
