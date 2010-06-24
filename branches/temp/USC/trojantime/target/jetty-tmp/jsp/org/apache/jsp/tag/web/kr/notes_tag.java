package org.apache.jsp.tag.web.kr;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class notes_tag
    extends javax.servlet.jsp.tagext.SimpleTagSupport
    implements org.apache.jasper.runtime.JspSourceDependent {


static private org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_0;
static private org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_1;
static private org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_2;
static private org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_3;
static private org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_4;

static {
  _jspx_fnmap_0= org.apache.jasper.runtime.ProtectedFunctionMapper.getMapForFunction("fn:length", org.apache.taglibs.standard.functions.Functions.class, "length", new Class[] {java.lang.Object.class});
  _jspx_fnmap_1= org.apache.jasper.runtime.ProtectedFunctionMapper.getMapForFunction("kfunc:canAddNoteAttachment", org.kuali.rice.kns.util.WebUtils.class, "canAddNoteAttachment", new Class[] {org.kuali.rice.kns.document.Document.class});
  _jspx_fnmap_2= org.apache.jasper.runtime.ProtectedFunctionMapper.getMapForFunction("fn:replace", org.apache.taglibs.standard.functions.Functions.class, "replace", new Class[] {java.lang.String.class, java.lang.String.class, java.lang.String.class});
  _jspx_fnmap_3= org.apache.jasper.runtime.ProtectedFunctionMapper.getMapForFunction("kfunc:canViewNoteAttachment", org.kuali.rice.kns.util.WebUtils.class, "canViewNoteAttachment", new Class[] {org.kuali.rice.kns.document.Document.class, java.lang.String.class});
  _jspx_fnmap_4= org.apache.jasper.runtime.ProtectedFunctionMapper.getMapForFunction("kfunc:canDeleteNoteAttachment", org.kuali.rice.kns.util.WebUtils.class, "canDeleteNoteAttachment", new Class[] {org.kuali.rice.kns.document.Document.class, java.lang.String.class, java.lang.String.class});
}

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(28);
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
    _jspx_dependants.add("/WEB-INF/tags/kr/htmlAttributeHeaderCell.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/htmlAttributeLabel.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/dd/evalNameToMap.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/fileSize.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/user.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/lookup.tag");
    _jspx_dependants.add("/WEB-INF/tags/kr/fieldShowChangedIcon.tag");
  }

  private JspContext jspContext;
  private java.io.Writer _jspx_sout;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_set_var_value_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_hidden_value_property_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_file_value_styleId_size_property_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_select_property;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_optionsCollection_value_property_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_varStatus_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_bean_write_property_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_image_title_style_src_property_onclick_alt_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;

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
  private java.util.List notesBo;
  private java.lang.Enum noteType;
  private java.lang.String displayTopicFieldInNotes;
  private java.lang.String attachmentTypesValuesFinderClass;
  private java.lang.String transparentBackground;
  private java.lang.String defaultOpen;

  public java.util.List getNotesBo() {
    return this.notesBo;
  }

  public void setNotesBo(java.util.List notesBo) {
    this.notesBo = notesBo;
  }

  public java.lang.Enum getNoteType() {
    return this.noteType;
  }

  public void setNoteType(java.lang.Enum noteType) {
    this.noteType = noteType;
  }

  public java.lang.String getDisplayTopicFieldInNotes() {
    return this.displayTopicFieldInNotes;
  }

  public void setDisplayTopicFieldInNotes(java.lang.String displayTopicFieldInNotes) {
    this.displayTopicFieldInNotes = displayTopicFieldInNotes;
  }

  public java.lang.String getAttachmentTypesValuesFinderClass() {
    return this.attachmentTypesValuesFinderClass;
  }

  public void setAttachmentTypesValuesFinderClass(java.lang.String attachmentTypesValuesFinderClass) {
    this.attachmentTypesValuesFinderClass = attachmentTypesValuesFinderClass;
  }

  public java.lang.String getTransparentBackground() {
    return this.transparentBackground;
  }

  public void setTransparentBackground(java.lang.String transparentBackground) {
    this.transparentBackground = transparentBackground;
  }

  public java.lang.String getDefaultOpen() {
    return this.defaultOpen;
  }

  public void setDefaultOpen(java.lang.String defaultOpen) {
    this.defaultOpen = defaultOpen;
  }

  public Object getDependants() {
    return _jspx_dependants;
  }

  private void _jspInit(ServletConfig config) {
    _jspx_tagPool_c_set_var_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_hidden_value_property_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_file_value_styleId_size_property_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_select_property = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_optionsCollection_value_property_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_c_forEach_varStatus_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_bean_write_property_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_html_image_title_style_src_property_onclick_alt_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_c_out_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_set_var_value_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_html_hidden_value_property_nobody.release();
    _jspx_tagPool_html_file_value_styleId_size_property_nobody.release();
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.release();
    _jspx_tagPool_html_select_property.release();
    _jspx_tagPool_html_optionsCollection_value_property_label_nobody.release();
    _jspx_tagPool_c_forEach_varStatus_var_items.release();
    _jspx_tagPool_bean_write_property_name_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_html_image_title_style_src_property_onclick_alt_nobody.release();
    _jspx_tagPool_c_out_value_nobody.release();
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_c_otherwise.release();
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
    if( getNotesBo() != null ) 
      _jspx_page_context.setAttribute("notesBo", getNotesBo());
    if( getNoteType() != null ) 
      _jspx_page_context.setAttribute("noteType", getNoteType());
    if( getDisplayTopicFieldInNotes() != null ) 
      _jspx_page_context.setAttribute("displayTopicFieldInNotes", getDisplayTopicFieldInNotes());
    if( getAttachmentTypesValuesFinderClass() != null ) 
      _jspx_page_context.setAttribute("attachmentTypesValuesFinderClass", getAttachmentTypesValuesFinderClass());
    if( getTransparentBackground() != null ) 
      _jspx_page_context.setAttribute("transparentBackground", getTransparentBackground());
    if( getDefaultOpen() != null ) 
      _jspx_page_context.setAttribute("defaultOpen", getDefaultOpen());

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
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      if (_jspx_meth_c_set_0(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_c_set_3(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_c_set_4(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_c_set_5(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_c_set_6(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_c_set_7(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_c_if_1(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_c_set_9(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_c_if_2(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_c_if_3(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_c_if_4(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      //  kul:tab
      org.apache.jsp.tag.web.kr.tab_tag _jspx_th_kul_tab_0 = new org.apache.jsp.tag.web.kr.tab_tag();
      _jspx_th_kul_tab_0.setJspContext(_jspx_page_context);
      _jspx_th_kul_tab_0.setTabTitle((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${tabTitle}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      _jspx_th_kul_tab_0.setDefaultOpen((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty notesBo or (not empty defaultOpen and defaultOpen)}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      _jspx_th_kul_tab_0.setTabErrorKey((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${Constants.DOCUMENT_NOTES_ERRORS}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      _jspx_th_kul_tab_0.setTabItemCount((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fn:length(notesBo)}", java.lang.String.class, (PageContext)this.getJspContext(), _jspx_fnmap_0, false));
      _jspx_th_kul_tab_0.setTransparentBackground((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${transparentBackground}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      _jspx_th_kul_tab_0.setJspBody(new notes_tagHelper( 0, _jspx_page_context, _jspx_th_kul_tab_0, null));
      _jspx_th_kul_tab_0.doTag();
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
    _jspx_th_c_set_0.setVar("noteColSpan");
    _jspx_th_c_set_0.setValue(new String("6"));
    int _jspx_eval_c_set_0 = _jspx_th_c_set_0.doStartTag();
    if (_jspx_th_c_set_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
    return false;
  }

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty noteType}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\n');
        out.write(' ');
        out.write(' ');
        out.write('\n');
        out.write(' ');
        out.write(' ');
        if (_jspx_meth_c_set_1(_jspx_th_c_if_0, _jspx_page_context))
          return true;
        out.write('\n');
        out.write(' ');
        out.write(' ');
        if (_jspx_meth_c_set_2(_jspx_th_c_if_0, _jspx_page_context))
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

  private boolean _jspx_meth_c_set_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_1.setPageContext(_jspx_page_context);
    _jspx_th_c_set_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_c_set_1.setVar("noteType");
    _jspx_th_c_set_1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${Constants.NoteTypeEnum.DOCUMENT_HEADER_NOTE_TYPE}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_1 = _jspx_th_c_set_1.doStartTag();
    if (_jspx_th_c_set_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_1);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_1);
    return false;
  }

  private boolean _jspx_meth_c_set_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_2.setPageContext(_jspx_page_context);
    _jspx_th_c_set_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_c_set_2.setVar("notesBo");
    _jspx_th_c_set_2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.document.documentHeader.boNotes}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
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

  private boolean _jspx_meth_c_set_5(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_5 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_5.setPageContext(_jspx_page_context);
    _jspx_th_c_set_5.setParent(null);
    _jspx_th_c_set_5.setVar("allowsNoteAttachments");
    _jspx_th_c_set_5.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${documentEntry.allowsNoteAttachments}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_5 = _jspx_th_c_set_5.doStartTag();
    if (_jspx_th_c_set_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_5);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_5);
    return false;
  }

  private boolean _jspx_meth_c_set_6(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_6 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_6.setPageContext(_jspx_page_context);
    _jspx_th_c_set_6.setParent(null);
    _jspx_th_c_set_6.setVar("allowsNoteFYI");
    _jspx_th_c_set_6.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${documentEntry.allowsNoteFYI}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_6 = _jspx_th_c_set_6.doStartTag();
    if (_jspx_th_c_set_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_6);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_6);
    return false;
  }

  private boolean _jspx_meth_c_set_7(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_7 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_7.setPageContext(_jspx_page_context);
    _jspx_th_c_set_7.setParent(null);
    _jspx_th_c_set_7.setVar("tabTitle");
    _jspx_th_c_set_7.setValue(new String("Notes and Attachments"));
    int _jspx_eval_c_set_7 = _jspx_th_c_set_7.doStartTag();
    if (_jspx_th_c_set_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_7);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_7);
    return false;
  }

  private boolean _jspx_meth_c_if_1(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent(null);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${allowsNoteAttachments eq false}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\n');
        out.write(' ');
        out.write(' ');
        if (_jspx_meth_c_set_8(_jspx_th_c_if_1, _jspx_page_context))
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

  private boolean _jspx_meth_c_set_8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_8 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_8.setPageContext(_jspx_page_context);
    _jspx_th_c_set_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_set_8.setVar("tabTitle");
    _jspx_th_c_set_8.setValue(new String("Notes"));
    int _jspx_eval_c_set_8 = _jspx_th_c_set_8.doStartTag();
    if (_jspx_th_c_set_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_8);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_8);
    return false;
  }

  private boolean _jspx_meth_c_set_9(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_9 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_9.setPageContext(_jspx_page_context);
    _jspx_th_c_set_9.setParent(null);
    _jspx_th_c_set_9.setVar("propPrefix");
    _jspx_th_c_set_9.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteType.fullPath}.", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_9 = _jspx_th_c_set_9.doStartTag();
    if (_jspx_th_c_set_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_9);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_9);
    return false;
  }

  private boolean _jspx_meth_c_if_2(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
    _jspx_th_c_if_2.setParent(null);
    _jspx_th_c_if_2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty attachmentTypesValuesFinderClass}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\n');
        out.write(' ');
        out.write(' ');
        if (_jspx_meth_c_set_10(_jspx_th_c_if_2, _jspx_page_context))
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

  private boolean _jspx_meth_c_set_10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_2, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_10 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_10.setPageContext(_jspx_page_context);
    _jspx_th_c_set_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
    _jspx_th_c_set_10.setVar("noteColSpan");
    _jspx_th_c_set_10.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteColSpan + 1}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_10 = _jspx_th_c_set_10.doStartTag();
    if (_jspx_th_c_set_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_10);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_10);
    return false;
  }

  private boolean _jspx_meth_c_if_3(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_3.setPageContext(_jspx_page_context);
    _jspx_th_c_if_3.setParent(null);
    _jspx_th_c_if_3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty displayTopicFieldInNotes}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
    if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\n');
        out.write(' ');
        out.write(' ');
        if (_jspx_meth_c_set_11(_jspx_th_c_if_3, _jspx_page_context))
          return true;
        out.write('\n');
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

  private boolean _jspx_meth_c_set_11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_3, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_11 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_11.setPageContext(_jspx_page_context);
    _jspx_th_c_set_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
    _jspx_th_c_set_11.setVar("displayTopicFieldInNotes");
    _jspx_th_c_set_11.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${documentEntry.displayTopicFieldInNotes}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_11 = _jspx_th_c_set_11.doStartTag();
    if (_jspx_th_c_set_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_11);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_11);
    return false;
  }

  private boolean _jspx_meth_c_if_4(PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_4 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_4.setPageContext(_jspx_page_context);
    _jspx_th_c_if_4.setParent(null);
    _jspx_th_c_if_4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${displayTopicFieldInNotes eq true}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
    if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\n');
        out.write(' ');
        out.write(' ');
        if (_jspx_meth_c_set_12(_jspx_th_c_if_4, _jspx_page_context))
          return true;
        out.write('\n');
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

  private boolean _jspx_meth_c_set_12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_4, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_12 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_12.setPageContext(_jspx_page_context);
    _jspx_th_c_set_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
    _jspx_th_c_set_12.setVar("noteColSpan");
    _jspx_th_c_set_12.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteColSpan + 1}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_12 = _jspx_th_c_set_12.doStartTag();
    if (_jspx_th_c_set_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_12);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_12);
    return false;
  }

  private boolean _jspx_meth_c_set_13(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_13 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_13.setPageContext(_jspx_page_context);
    _jspx_th_c_set_13.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_c_set_13.setVar("notesAttributes");
    _jspx_th_c_set_13.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DataDictionary.Note.attributes}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_13 = _jspx_th_c_set_13.doStartTag();
    if (_jspx_th_c_set_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_13);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_13);
    return false;
  }

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_0 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_0.setParent(_jspx_parent);
    _jspx_th_kul_htmlAttributeHeaderCell_0.setLiteralLabel("&nbsp;");
    _jspx_th_kul_htmlAttributeHeaderCell_0.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_0.setAlign("left");
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
    _jspx_th_kul_htmlAttributeHeaderCell_1.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${notesAttributes.notePostedTimestamp}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_1.setHideRequiredAsterisk("true");
    _jspx_th_kul_htmlAttributeHeaderCell_1.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_1.setAlign("left");
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
    _jspx_th_kul_htmlAttributeHeaderCell_2.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${notesAttributes.authorUniversalIdentifier}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_2.setHideRequiredAsterisk("true");
    _jspx_th_kul_htmlAttributeHeaderCell_2.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_2.setAlign("left");
    _jspx_th_kul_htmlAttributeHeaderCell_2.doTag();
    return false;
  }

  private boolean _jspx_meth_c_if_5(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_5 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_5.setPageContext(_jspx_page_context);
    _jspx_th_c_if_5.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_c_if_5.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${displayTopicFieldInNotes eq true}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
    if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("            ");
        if (_jspx_meth_kul_htmlAttributeHeaderCell_3(_jspx_th_c_if_5, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("          ");
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

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_3 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_3.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_3.setParent(_jspx_th_c_if_5);
    _jspx_th_kul_htmlAttributeHeaderCell_3.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${notesAttributes.noteTopicText}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_3.setForceRequired("true");
    _jspx_th_kul_htmlAttributeHeaderCell_3.setLabelFor("newNote.noteTopicText");
    _jspx_th_kul_htmlAttributeHeaderCell_3.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_3.setAlign("left");
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
    _jspx_th_kul_htmlAttributeHeaderCell_4.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${notesAttributes.noteText}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_4.setLabelFor("newNote.noteText");
    _jspx_th_kul_htmlAttributeHeaderCell_4.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_4.setAlign("left");
    _jspx_th_kul_htmlAttributeHeaderCell_4.doTag();
    return false;
  }

  private boolean _jspx_meth_c_if_6(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_6 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_6.setPageContext(_jspx_page_context);
    _jspx_th_c_if_6.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_c_if_6.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${allowsNoteAttachments eq true}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
    if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                      ");
        if (_jspx_meth_kul_htmlAttributeHeaderCell_5(_jspx_th_c_if_6, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                    ");
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

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_6, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_5 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_5.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_5.setParent(_jspx_th_c_if_6);
    _jspx_th_kul_htmlAttributeHeaderCell_5.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${notesAttributes.attachment}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_5.setLabelFor("attachmentFile");
    _jspx_th_kul_htmlAttributeHeaderCell_5.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_5.setAlign("left");
    _jspx_th_kul_htmlAttributeHeaderCell_5.doTag();
    return false;
  }

  private boolean _jspx_meth_c_if_7(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_7 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_7.setPageContext(_jspx_page_context);
    _jspx_th_c_if_7.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_c_if_7.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(not empty attachmentTypesValuesFinderClass) and (allowsNoteAttachments eq true)}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
    if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                      ");
        if (_jspx_meth_kul_htmlAttributeHeaderCell_6(_jspx_th_c_if_7, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                    ");
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

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_7, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_6 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_6.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_6.setParent(_jspx_th_c_if_7);
    _jspx_th_kul_htmlAttributeHeaderCell_6.setLiteralLabel("Attachment Type");
    _jspx_th_kul_htmlAttributeHeaderCell_6.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_6.setAlign("left");
    _jspx_th_kul_htmlAttributeHeaderCell_6.doTag();
    return false;
  }

  private boolean _jspx_meth_c_if_8(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_8 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_8.setPageContext(_jspx_page_context);
    _jspx_th_c_if_8.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_c_if_8.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${allowsNoteFYI}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
    if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                      ");
        if (_jspx_meth_kul_htmlAttributeHeaderCell_7(_jspx_th_c_if_8, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                    ");
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

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_8, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_7 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_7.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_7.setParent(_jspx_th_c_if_8);
    _jspx_th_kul_htmlAttributeHeaderCell_7.setLiteralLabel("Notification Recipient");
    _jspx_th_kul_htmlAttributeHeaderCell_7.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_7.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_8(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_8 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_8.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_8.setParent(_jspx_parent);
    _jspx_th_kul_htmlAttributeHeaderCell_8.setLiteralLabel("Actions");
    _jspx_th_kul_htmlAttributeHeaderCell_8.setScope("col");
    _jspx_th_kul_htmlAttributeHeaderCell_8.doTag();
    return false;
  }

  private boolean _jspx_meth_html_hidden_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.kuali.rice.kns.web.taglib.html.KNSHiddenTag _jspx_th_html_hidden_0 = (org.kuali.rice.kns.web.taglib.html.KNSHiddenTag) _jspx_tagPool_html_hidden_value_property_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSHiddenTag.class);
    _jspx_th_html_hidden_0.setPageContext(_jspx_page_context);
    _jspx_th_html_hidden_0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_html_hidden_0.setProperty("newNote.noteTypeCode");
    _jspx_th_html_hidden_0.setValue((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteType.code}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_html_hidden_0 = _jspx_th_html_hidden_0.doStartTag();
    if (_jspx_th_html_hidden_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_hidden_value_property_nobody.reuse(_jspx_th_html_hidden_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_hidden_value_property_nobody.reuse(_jspx_th_html_hidden_0);
    return false;
  }

  private boolean _jspx_meth_c_if_9(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_9 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_9.setPageContext(_jspx_page_context);
    _jspx_th_c_if_9.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_c_if_9.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ ((not empty attachmentTypesValuesFinderClass) and (allowsNoteAttachments eq true)) || kfunc:canAddNoteAttachment(KualiForm.document)}", java.lang.Boolean.class, (PageContext)this.getJspContext(), _jspx_fnmap_1, false)).booleanValue());
    int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
    if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                  <tr>\n");
        out.write("                      ");
        if (_jspx_meth_kul_htmlAttributeHeaderCell_9(_jspx_th_c_if_9, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                      <td class=\"infoline\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n");
        out.write("                      <td class=\"infoline\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n");
        out.write("                      ");
        if (_jspx_meth_c_if_10(_jspx_th_c_if_9, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                      <td class=\"infoline\">");
        if (_jspx_meth_kul_htmlControlAttribute_1(_jspx_th_c_if_9, _jspx_page_context))
          return true;
        out.write("</td>\n");
        out.write("                      ");
        if (_jspx_meth_c_if_11(_jspx_th_c_if_9, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                     ");
        if (_jspx_meth_c_if_12(_jspx_th_c_if_9, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                     ");
        if (_jspx_meth_c_if_13(_jspx_th_c_if_9, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                     <td class=\"infoline\"><div align=\"center\">");
        if (_jspx_meth_html_image_1(_jspx_th_c_if_9, _jspx_page_context))
          return true;
        out.write("</div></td>\t \n");
        out.write("\t\t\t       </tr>\n");
        out.write("\t\t\t   ");
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

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_9, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_9 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_9.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_9.setParent(_jspx_th_c_if_9);
    _jspx_th_kul_htmlAttributeHeaderCell_9.setLiteralLabel("add:");
    _jspx_th_kul_htmlAttributeHeaderCell_9.setScope("row");
    _jspx_th_kul_htmlAttributeHeaderCell_9.doTag();
    return false;
  }

  private boolean _jspx_meth_c_if_10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_9, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_10 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_10.setPageContext(_jspx_page_context);
    _jspx_th_c_if_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
    _jspx_th_c_if_10.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${displayTopicFieldInNotes eq true}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
    if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                       <td class=\"infoline\">");
        if (_jspx_meth_kul_htmlControlAttribute_0(_jspx_th_c_if_10, _jspx_page_context))
          return true;
        out.write("</td>\n");
        out.write("                      ");
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

  private boolean _jspx_meth_kul_htmlControlAttribute_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlControlAttribute
    org.apache.jsp.tag.web.kr.htmlControlAttribute_tag _jspx_th_kul_htmlControlAttribute_0 = new org.apache.jsp.tag.web.kr.htmlControlAttribute_tag();
    _jspx_th_kul_htmlControlAttribute_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlControlAttribute_0.setParent(_jspx_th_c_if_10);
    _jspx_th_kul_htmlControlAttribute_0.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${notesAttributes.noteTopicText}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_0.setProperty("newNote.noteTopicText");
    _jspx_th_kul_htmlControlAttribute_0.setForceRequired("true");
    _jspx_th_kul_htmlControlAttribute_0.doTag();
    return false;
  }

  private boolean _jspx_meth_kul_htmlControlAttribute_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_9, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlControlAttribute
    org.apache.jsp.tag.web.kr.htmlControlAttribute_tag _jspx_th_kul_htmlControlAttribute_1 = new org.apache.jsp.tag.web.kr.htmlControlAttribute_tag();
    _jspx_th_kul_htmlControlAttribute_1.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlControlAttribute_1.setParent(_jspx_th_c_if_9);
    _jspx_th_kul_htmlControlAttribute_1.setAttributeEntry((java.util.Map) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${notesAttributes.noteText}", java.util.Map.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_1.setProperty("newNote.noteText");
    _jspx_th_kul_htmlControlAttribute_1.setForceRequired((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${notesAttributes.noteText.required}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlControlAttribute_1.doTag();
    return false;
  }

  private boolean _jspx_meth_c_if_11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_9, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_11 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_11.setPageContext(_jspx_page_context);
    _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
    _jspx_th_c_if_11.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${allowsNoteAttachments eq true}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
    if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                        <td class=\"infoline\">\n");
        out.write("                          <div align=\"center\"><br />\n");
        out.write("                          ");
        if (_jspx_meth_html_file_0(_jspx_th_c_if_11, _jspx_page_context))
          return true;
        out.write("<br /><br />\n");
        out.write("                          ");
        if (_jspx_meth_html_image_0(_jspx_th_c_if_11, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                          </div>\n");
        out.write("                        </td>\n");
        out.write("                     ");
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

  private boolean _jspx_meth_html_file_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_11, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:file
    org.kuali.rice.kns.web.taglib.html.KNSFileTag _jspx_th_html_file_0 = (org.kuali.rice.kns.web.taglib.html.KNSFileTag) _jspx_tagPool_html_file_value_styleId_size_property_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSFileTag.class);
    _jspx_th_html_file_0.setPageContext(_jspx_page_context);
    _jspx_th_html_file_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
    _jspx_th_html_file_0.setProperty("attachmentFile");
    _jspx_th_html_file_0.setSize("30");
    _jspx_th_html_file_0.setStyleId("attachmentFile");
    _jspx_th_html_file_0.setValue("");
    int _jspx_eval_html_file_0 = _jspx_th_html_file_0.doStartTag();
    if (_jspx_th_html_file_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_file_value_styleId_size_property_nobody.reuse(_jspx_th_html_file_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_file_value_styleId_size_property_nobody.reuse(_jspx_th_html_file_0);
    return false;
  }

  private boolean _jspx_meth_html_image_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_11, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_0 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_0.setPageContext(_jspx_page_context);
    _jspx_th_html_image_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
    _jspx_th_html_image_0.setProperty("methodToCall.cancelBOAttachment");
    _jspx_th_html_image_0.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}tinygrey-cancel.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_0.setTitle("Cancel Attachment");
    _jspx_th_html_image_0.setAlt("Cancel Attachment");
    _jspx_th_html_image_0.setStyleClass("tinybutton");
    int _jspx_eval_html_image_0 = _jspx_th_html_image_0.doStartTag();
    if (_jspx_th_html_image_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_0);
    return false;
  }

  private boolean _jspx_meth_c_if_12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_9, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_12 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_12.setPageContext(_jspx_page_context);
    _jspx_th_c_if_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
    _jspx_th_c_if_12.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(not empty attachmentTypesValuesFinderClass) and (allowsNoteAttachments eq true)}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_12 = _jspx_th_c_if_12.doStartTag();
    if (_jspx_eval_c_if_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                        ");
        if (_jspx_meth_c_set_14(_jspx_th_c_if_12, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                        <td class=\"infoline\">\n");
        out.write("                            ");
        if (_jspx_meth_html_select_0(_jspx_th_c_if_12, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                        </td>\n");
        out.write("                     ");
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

  private boolean _jspx_meth_c_set_14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_12, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_14 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_14.setPageContext(_jspx_page_context);
    _jspx_th_c_set_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
    _jspx_th_c_set_14.setVar("finderClass");
    _jspx_th_c_set_14.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fn:replace(attachmentTypesValuesFinderClass,'.','|')}", java.lang.Object.class, (PageContext)this.getJspContext(), _jspx_fnmap_2, false));
    int _jspx_eval_c_set_14 = _jspx_th_c_set_14.doStartTag();
    if (_jspx_th_c_set_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_14);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_14);
    return false;
  }

  private boolean _jspx_meth_html_select_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_12, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:select
    org.kuali.rice.kns.web.taglib.html.KNSSelectTag _jspx_th_html_select_0 = (org.kuali.rice.kns.web.taglib.html.KNSSelectTag) _jspx_tagPool_html_select_property.get(org.kuali.rice.kns.web.taglib.html.KNSSelectTag.class);
    _jspx_th_html_select_0.setPageContext(_jspx_page_context);
    _jspx_th_html_select_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
    _jspx_th_html_select_0.setProperty("newNote.attachment.attachmentTypeCode");
    int _jspx_eval_html_select_0 = _jspx_th_html_select_0.doStartTag();
    if (_jspx_eval_html_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_select_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_select_0.doInitBody();
      }
      do {
        out.write("\n");
        out.write("                                ");
        if (_jspx_meth_html_optionsCollection_0(_jspx_th_html_select_0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                            ");
        int evalDoAfterBody = _jspx_th_html_select_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_html_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_select_property.reuse(_jspx_th_html_select_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_select_property.reuse(_jspx_th_html_select_0);
    return false;
  }

  private boolean _jspx_meth_html_optionsCollection_0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_select_0, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:optionsCollection
    org.apache.struts.taglib.html.OptionsCollectionTag _jspx_th_html_optionsCollection_0 = (org.apache.struts.taglib.html.OptionsCollectionTag) _jspx_tagPool_html_optionsCollection_value_property_label_nobody.get(org.apache.struts.taglib.html.OptionsCollectionTag.class);
    _jspx_th_html_optionsCollection_0.setPageContext(_jspx_page_context);
    _jspx_th_html_optionsCollection_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_select_0);
    _jspx_th_html_optionsCollection_0.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_optionsCollection_0.setLabel("label");
    _jspx_th_html_optionsCollection_0.setValue("key");
    int _jspx_eval_html_optionsCollection_0 = _jspx_th_html_optionsCollection_0.doStartTag();
    if (_jspx_th_html_optionsCollection_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_optionsCollection_value_property_label_nobody.reuse(_jspx_th_html_optionsCollection_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_optionsCollection_value_property_label_nobody.reuse(_jspx_th_html_optionsCollection_0);
    return false;
  }

  private boolean _jspx_meth_c_if_13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_9, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_13 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_13.setPageContext(_jspx_page_context);
    _jspx_th_c_if_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
    _jspx_th_c_if_13.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${allowsNoteFYI}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_13 = _jspx_th_c_if_13.doStartTag();
    if (_jspx_eval_c_if_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                      <td>&nbsp;</td>\n");
        out.write("                     ");
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

  private boolean _jspx_meth_html_image_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_9, PageContext _jspx_page_context)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_1 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_1.setPageContext(_jspx_page_context);
    _jspx_th_html_image_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
    _jspx_th_html_image_1.setProperty("methodToCall.insertBONote");
    _jspx_th_html_image_1.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_1.setAlt("Add a Note");
    _jspx_th_html_image_1.setTitle("Add a Note");
    _jspx_th_html_image_1.setStyleClass("tinybutton");
    int _jspx_eval_html_image_1 = _jspx_th_html_image_1.doStartTag();
    if (_jspx_th_html_image_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_1);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_1);
    return false;
  }

  private boolean _jspx_meth_c_set_15(javax.servlet.jsp.tagext.JspTag _jspx_th_c_forEach_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_15 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_15.setPageContext(_jspx_page_context);
    _jspx_th_c_set_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_forEach_0);
    _jspx_th_c_set_15.setVar("authorUniversalIdentifier");
    _jspx_th_c_set_15.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${note.authorUniversalIdentifier}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_15 = _jspx_th_c_set_15.doStartTag();
    if (_jspx_th_c_set_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_15);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_15);
    return false;
  }

  private boolean _jspx_meth_kul_htmlAttributeHeaderCell_10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:htmlAttributeHeaderCell
    org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag _jspx_th_kul_htmlAttributeHeaderCell_10 = new org.apache.jsp.tag.web.kr.htmlAttributeHeaderCell_tag();
    _jspx_th_kul_htmlAttributeHeaderCell_10.setJspContext(_jspx_page_context);
    _jspx_th_kul_htmlAttributeHeaderCell_10.setParent(_jspx_th_c_if_14);
    _jspx_th_kul_htmlAttributeHeaderCell_10.setLiteralLabel((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status.index + 1}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_htmlAttributeHeaderCell_10.setScope("row");
    _jspx_th_kul_htmlAttributeHeaderCell_10.doTag();
    return false;
  }

  private boolean _jspx_meth_bean_write_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_write_0 = (org.apache.struts.taglib.bean.WriteTag) _jspx_tagPool_bean_write_property_name_nobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_write_0.setPageContext(_jspx_page_context);
    _jspx_th_bean_write_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    _jspx_th_bean_write_0.setName("KualiForm");
    _jspx_th_bean_write_0.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${propPrefix}boNote[${status.index}].notePostedTimestamp", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_bean_write_0 = _jspx_th_bean_write_0.doStartTag();
    if (_jspx_th_bean_write_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_bean_write_property_name_nobody.reuse(_jspx_th_bean_write_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_bean_write_property_name_nobody.reuse(_jspx_th_bean_write_0);
    return false;
  }

  private boolean _jspx_meth_bean_write_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_write_1 = (org.apache.struts.taglib.bean.WriteTag) _jspx_tagPool_bean_write_property_name_nobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_write_1.setPageContext(_jspx_page_context);
    _jspx_th_bean_write_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    _jspx_th_bean_write_1.setName("KualiForm");
    _jspx_th_bean_write_1.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${propPrefix}boNote[${status.index}].authorUniversal.name", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_bean_write_1 = _jspx_th_bean_write_1.doStartTag();
    if (_jspx_th_bean_write_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_bean_write_property_name_nobody.reuse(_jspx_th_bean_write_1);
      throw new SkipPageException();
    }
    _jspx_tagPool_bean_write_property_name_nobody.reuse(_jspx_th_bean_write_1);
    return false;
  }

  private boolean _jspx_meth_c_if_15(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_15 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_15.setPageContext(_jspx_page_context);
    _jspx_th_c_if_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    _jspx_th_c_if_15.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${displayTopicFieldInNotes eq true}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_15 = _jspx_th_c_if_15.doStartTag();
    if (_jspx_eval_c_if_15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                          <td class=\"datacell center\">\n");
        out.write("                          ");
        if (_jspx_meth_bean_write_2(_jspx_th_c_if_15, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("</td>\n");
        out.write("                        ");
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

  private boolean _jspx_meth_bean_write_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_15, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_write_2 = (org.apache.struts.taglib.bean.WriteTag) _jspx_tagPool_bean_write_property_name_nobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_write_2.setPageContext(_jspx_page_context);
    _jspx_th_bean_write_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_15);
    _jspx_th_bean_write_2.setName("KualiForm");
    _jspx_th_bean_write_2.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${propPrefix}boNote[${status.index}].noteTopicText", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_bean_write_2 = _jspx_th_bean_write_2.doStartTag();
    if (_jspx_th_bean_write_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_bean_write_property_name_nobody.reuse(_jspx_th_bean_write_2);
      throw new SkipPageException();
    }
    _jspx_tagPool_bean_write_property_name_nobody.reuse(_jspx_th_bean_write_2);
    return false;
  }

  private boolean _jspx_meth_bean_write_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_write_3 = (org.apache.struts.taglib.bean.WriteTag) _jspx_tagPool_bean_write_property_name_nobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_write_3.setPageContext(_jspx_page_context);
    _jspx_th_bean_write_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    _jspx_th_bean_write_3.setName("KualiForm");
    _jspx_th_bean_write_3.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${propPrefix}boNote[${status.index}].noteText", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_bean_write_3 = _jspx_th_bean_write_3.doStartTag();
    if (_jspx_th_bean_write_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_bean_write_property_name_nobody.reuse(_jspx_th_bean_write_3);
      throw new SkipPageException();
    }
    _jspx_tagPool_bean_write_property_name_nobody.reuse(_jspx_th_bean_write_3);
    return false;
  }

  private boolean _jspx_meth_c_if_17(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_16, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_17 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_17.setPageContext(_jspx_page_context);
    _jspx_th_c_if_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
    _jspx_th_c_if_17.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(!empty note.attachment)}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_17 = _jspx_th_c_if_17.doStartTag();
    if (_jspx_eval_c_if_17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_c_set_16(_jspx_th_c_if_17, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t\t  ");
        int evalDoAfterBody = _jspx_th_c_if_17.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_17);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_17);
    return false;
  }

  private boolean _jspx_meth_c_set_16(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_17, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_16 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_16.setPageContext(_jspx_page_context);
    _jspx_th_c_set_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_17);
    _jspx_th_c_set_16.setVar("attachmentTypeCode");
    _jspx_th_c_set_16.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${note.attachment.attachmentTypeCode}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_16 = _jspx_th_c_set_16.doStartTag();
    if (_jspx_th_c_set_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_16);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_16);
    return false;
  }

  private boolean _jspx_meth_c_if_18(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_16, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_18 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_18.setPageContext(_jspx_page_context);
    _jspx_th_c_if_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
    _jspx_th_c_if_18.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${kfunc:canViewNoteAttachment(KualiForm.document, attachmentTypeCode)}", java.lang.Boolean.class, (PageContext)this.getJspContext(), _jspx_fnmap_3, false)).booleanValue());
    int _jspx_eval_c_if_18 = _jspx_th_c_if_18.doStartTag();
    if (_jspx_eval_c_if_18 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                                        ");
        if (_jspx_meth_html_image_2(_jspx_th_c_if_18, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n");
        out.write("                                      ");
        int evalDoAfterBody = _jspx_th_c_if_18.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_18);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_18);
    return false;
  }

  private boolean _jspx_meth_html_image_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_18, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_2 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_style_src_property_onclick_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_2.setPageContext(_jspx_page_context);
    _jspx_th_html_image_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_18);
    _jspx_th_html_image_2.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("methodToCall.downloadBOAttachment.attachment[${status.index}]", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_2.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}clip.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_2.setTitle("download attachment");
    _jspx_th_html_image_2.setAlt("download attachment");
    _jspx_th_html_image_2.setStyle("padding:5px");
    _jspx_th_html_image_2.setOnclick("excludeSubmitRestriction=true");
    int _jspx_eval_html_image_2 = _jspx_th_html_image_2.doStartTag();
    if (_jspx_th_html_image_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_style_src_property_onclick_alt_nobody.reuse(_jspx_th_html_image_2);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_style_src_property_onclick_alt_nobody.reuse(_jspx_th_html_image_2);
    return false;
  }

  private boolean _jspx_meth_bean_write_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_16, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_write_4 = (org.apache.struts.taglib.bean.WriteTag) _jspx_tagPool_bean_write_property_name_nobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_write_4.setPageContext(_jspx_page_context);
    _jspx_th_bean_write_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
    _jspx_th_bean_write_4.setName("KualiForm");
    _jspx_th_bean_write_4.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${propPrefix}boNote[${status.index}].attachment.attachmentFileName", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_bean_write_4 = _jspx_th_bean_write_4.doStartTag();
    if (_jspx_th_bean_write_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_bean_write_property_name_nobody.reuse(_jspx_th_bean_write_4);
      throw new SkipPageException();
    }
    _jspx_tagPool_bean_write_property_name_nobody.reuse(_jspx_th_bean_write_4);
    return false;
  }

  private boolean _jspx_meth_c_out_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context, int[] _jspx_push_body_count)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_out_0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _jspx_tagPool_c_out_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_out_0.setPageContext(_jspx_page_context);
    _jspx_th_c_out_0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_c_out_0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileSize} ${fileSizeUnits}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_out_value_nobody.reuse(_jspx_th_c_out_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_out_value_nobody.reuse(_jspx_th_c_out_0);
    return false;
  }

  private boolean _jspx_meth_bean_write_5(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context, int[] _jspx_push_body_count)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_write_5 = (org.apache.struts.taglib.bean.WriteTag) _jspx_tagPool_bean_write_property_name_nobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_write_5.setPageContext(_jspx_page_context);
    _jspx_th_bean_write_5.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    _jspx_th_bean_write_5.setName("KualiForm");
    _jspx_th_bean_write_5.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${propPrefix}boNote[${status.index}].attachment.attachmentMimeTypeCode", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_bean_write_5 = _jspx_th_bean_write_5.doStartTag();
    if (_jspx_th_bean_write_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_bean_write_property_name_nobody.reuse(_jspx_th_bean_write_5);
      throw new SkipPageException();
    }
    _jspx_tagPool_bean_write_property_name_nobody.reuse(_jspx_th_bean_write_5);
    return false;
  }

  private boolean _jspx_meth_c_if_19(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_19 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_19.setPageContext(_jspx_page_context);
    _jspx_th_c_if_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    _jspx_th_c_if_19.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(not empty attachmentTypesValuesFinderClass) and (allowsNoteAttachments eq true)}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_19 = _jspx_th_c_if_19.doStartTag();
    if (_jspx_eval_c_if_19 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                                     <td class=\"datacell center\">\n");
        out.write("                                     &nbsp;\n");
        out.write("\t\t\t\t\t\t\t\t\t ");
        if (_jspx_meth_c_set_17(_jspx_th_c_if_19, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t\t ");
        if (_jspx_meth_c_set_18(_jspx_th_c_if_19, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n");
        out.write("                                       ");
        if (_jspx_meth_c_forEach_1(_jspx_th_c_if_19, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n");
        out.write("                                     </td>\n");
        out.write("                                  ");
        int evalDoAfterBody = _jspx_th_c_if_19.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_19);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_19);
    return false;
  }

  private boolean _jspx_meth_c_set_17(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_19, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_17 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_17.setPageContext(_jspx_page_context);
    _jspx_th_c_set_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_19);
    _jspx_th_c_set_17.setVar("mapKey");
    _jspx_th_c_set_17.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_17 = _jspx_th_c_set_17.doStartTag();
    if (_jspx_th_c_set_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_17);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_17);
    return false;
  }

  private boolean _jspx_meth_c_set_18(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_19, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_18 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_18.setPageContext(_jspx_page_context);
    _jspx_th_c_set_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_19);
    _jspx_th_c_set_18.setVar("attachmentTypeFinderMap");
    _jspx_th_c_set_18.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${KualiForm.actionFormUtilMap[mapKey]}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    int _jspx_eval_c_set_18 = _jspx_th_c_set_18.doStartTag();
    if (_jspx_th_c_set_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_18);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_18);
    return false;
  }

  private boolean _jspx_meth_c_forEach_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_19, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_1.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_19);
    _jspx_th_c_forEach_1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${attachmentTypeFinderMap}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_c_forEach_1.setVar("type");
    int[] _jspx_push_body_count_c_forEach_1 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_1 = _jspx_th_c_forEach_1.doStartTag();
      if (_jspx_eval_c_forEach_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                         ");
          if (_jspx_meth_c_if_20(_jspx_th_c_forEach_1, _jspx_page_context, _jspx_push_body_count_c_forEach_1))
            return true;
          out.write("\n");
          out.write("                                       ");
          int evalDoAfterBody = _jspx_th_c_forEach_1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        throw new SkipPageException();
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_1.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_1);
    }
    return false;
  }

  private boolean _jspx_meth_c_if_20(javax.servlet.jsp.tagext.JspTag _jspx_th_c_forEach_1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_20 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_20.setPageContext(_jspx_page_context);
    _jspx_th_c_if_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_forEach_1);
    _jspx_th_c_if_20.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type.key eq note.attachment.attachmentTypeCode}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_20 = _jspx_th_c_if_20.doStartTag();
    if (_jspx_eval_c_if_20 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type.label}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
        int evalDoAfterBody = _jspx_th_c_if_20.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_20);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_20);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_otherwise_0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
    int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
    if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                                    <td class=\"datacell center\">&nbsp;</td>\n");
        out.write("                                    ");
        if (_jspx_meth_c_if_21(_jspx_th_c_otherwise_0, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n");
        out.write("                                ");
        int evalDoAfterBody = _jspx_th_c_otherwise_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
    return false;
  }

  private boolean _jspx_meth_c_if_21(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_21 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_21.setPageContext(_jspx_page_context);
    _jspx_th_c_if_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
    _jspx_th_c_if_21.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(not empty attachmentTypesValuesFinderClass) and (allowsNoteAttachments eq true)}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_21 = _jspx_th_c_if_21.doStartTag();
    if (_jspx_eval_c_if_21 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                                        <td class=\"datacell center\">&nbsp;</td>\n");
        out.write("                                    ");
        int evalDoAfterBody = _jspx_th_c_if_21.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_21);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_21);
    return false;
  }

  private boolean _jspx_meth_c_if_22(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_22 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_22.setPageContext(_jspx_page_context);
    _jspx_th_c_if_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    _jspx_th_c_if_22.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${allowsNoteFYI}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_22 = _jspx_th_c_if_22.doStartTag();
    if (_jspx_eval_c_if_22 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                              <td class=\"infoline\">\n");
        out.write("                                ");
        if (_jspx_meth_c_if_23(_jspx_th_c_if_22, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n");
        out.write("                            ");
        if (_jspx_meth_c_if_24(_jspx_th_c_if_22, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n");
        out.write("                             </td>\n");
        out.write("                           ");
        int evalDoAfterBody = _jspx_th_c_if_22.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_22);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_22);
    return false;
  }

  private boolean _jspx_meth_c_if_23(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_22, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_23 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_23.setPageContext(_jspx_page_context);
    _jspx_th_c_if_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_22);
    _jspx_th_c_if_23.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_SEND_NOTE_FYI]}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_23 = _jspx_th_c_if_23.doStartTag();
    if (_jspx_eval_c_if_23 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                             ");
        if (_jspx_meth_kul_user_0(_jspx_th_c_if_23, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n");
        out.write("                            ");
        int evalDoAfterBody = _jspx_th_c_if_23.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_23);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_23);
    return false;
  }

  private boolean _jspx_meth_kul_user_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_23, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  kul:user
    org.apache.jsp.tag.web.kr.user_tag _jspx_th_kul_user_0 = new org.apache.jsp.tag.web.kr.user_tag();
    _jspx_th_kul_user_0.setJspContext(_jspx_page_context);
    _jspx_th_kul_user_0.setParent(_jspx_th_c_if_23);
    _jspx_th_kul_user_0.setUserIdFieldName((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${propPrefix}boNote[${status.index}].adHocRouteRecipient.id", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_0.setUserId((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${note.adHocRouteRecipient.id}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_0.setUniversalIdFieldName("");
    _jspx_th_kul_user_0.setUniversalId("");
    _jspx_th_kul_user_0.setUserNameFieldName((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${propPrefix}boNote[${status.index}].adHocRouteRecipient.name", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_0.setUserName((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${note.adHocRouteRecipient.name}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_0.setReadOnly("false");
    _jspx_th_kul_user_0.setRenderOtherFields("true");
    _jspx_th_kul_user_0.setFieldConversions((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("principalName:${propPrefix}boNote[${status.index}].adHocRouteRecipient.id,name:${propPrefix}boNote[${status.index}].adHocRouteRecipient.name", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_0.setLookupParameters((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${propPrefix}boNote[${status.index}].adHocRouteRecipient.id:principalName", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_kul_user_0.doTag();
    return false;
  }

  private boolean _jspx_meth_c_if_24(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_22, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_24 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_24.setPageContext(_jspx_page_context);
    _jspx_th_c_if_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_22);
    _jspx_th_c_if_24.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_SEND_NOTE_FYI]}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_24 = _jspx_th_c_if_24.doStartTag();
    if (_jspx_eval_c_if_24 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                              &nbsp;\n");
        out.write("                            ");
        int evalDoAfterBody = _jspx_th_c_if_24.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_24);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_24);
    return false;
  }

  private boolean _jspx_meth_c_if_25(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_25 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_25.setPageContext(_jspx_page_context);
    _jspx_th_c_if_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    _jspx_th_c_if_25.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${kfunc:canDeleteNoteAttachment(KualiForm.document, attachmentTypeCode, authorUniversalIdentifier)}", java.lang.Boolean.class, (PageContext)this.getJspContext(), _jspx_fnmap_4, false)).booleanValue());
    int _jspx_eval_c_if_25 = _jspx_th_c_if_25.doStartTag();
    if (_jspx_eval_c_if_25 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                            ");
        if (_jspx_meth_html_image_3(_jspx_th_c_if_25, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n");
        out.write("                          ");
        int evalDoAfterBody = _jspx_th_c_if_25.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_25);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_25);
    return false;
  }

  private boolean _jspx_meth_html_image_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_25, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_3 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_3.setPageContext(_jspx_page_context);
    _jspx_th_html_image_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_25);
    _jspx_th_html_image_3.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("methodToCall.deleteBONote.line${status.index}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_3.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_3.setTitle("Delete a Note");
    _jspx_th_html_image_3.setAlt("Delete a Note");
    _jspx_th_html_image_3.setStyleClass("tinybutton");
    int _jspx_eval_html_image_3 = _jspx_th_html_image_3.doStartTag();
    if (_jspx_th_html_image_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_3);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_3);
    return false;
  }

  private boolean _jspx_meth_c_if_26(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_14, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_26 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_26.setPageContext(_jspx_page_context);
    _jspx_th_c_if_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
    _jspx_th_c_if_26.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${allowsNoteFYI && !empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_SEND_NOTE_FYI]}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_c_if_26 = _jspx_th_c_if_26.doStartTag();
    if (_jspx_eval_c_if_26 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                              ");
        if (_jspx_meth_html_image_4(_jspx_th_c_if_26, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
          return true;
        out.write("\n");
        out.write("                          ");
        int evalDoAfterBody = _jspx_th_c_if_26.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_26);
      throw new SkipPageException();
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_26);
    return false;
  }

  private boolean _jspx_meth_html_image_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_26, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    JspWriter out = _jspx_page_context.getOut();
    //  html:image
    org.kuali.rice.kns.web.taglib.html.KNSImageTag _jspx_th_html_image_4 = (org.kuali.rice.kns.web.taglib.html.KNSImageTag) _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.get(org.kuali.rice.kns.web.taglib.html.KNSImageTag.class);
    _jspx_th_html_image_4.setPageContext(_jspx_page_context);
    _jspx_th_html_image_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_26);
    _jspx_th_html_image_4.setProperty((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("methodToCall.sendNoteWorkflowNotification.line${status.index}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_4.setSrc((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ConfigProperties.kr.externalizable.images.url}tinybutton-send.gif", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
    _jspx_th_html_image_4.setTitle("Send FYI");
    _jspx_th_html_image_4.setAlt("Send FYI");
    _jspx_th_html_image_4.setStyleClass("tinybutton");
    int _jspx_eval_html_image_4 = _jspx_th_html_image_4.doStartTag();
    if (_jspx_th_html_image_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_4);
      throw new SkipPageException();
    }
    _jspx_tagPool_html_image_title_styleClass_src_property_alt_nobody.reuse(_jspx_th_html_image_4);
    return false;
  }

  private class notes_tagHelper
      extends org.apache.jasper.runtime.JspFragmentHelper
  {
    private javax.servlet.jsp.tagext.JspTag _jspx_parent;
    private int[] _jspx_push_body_count;

    public notes_tagHelper( int discriminator, JspContext jspContext, javax.servlet.jsp.tagext.JspTag _jspx_parent, int[] _jspx_push_body_count ) {
      super( discriminator, jspContext, _jspx_parent );
      this._jspx_parent = _jspx_parent;
      this._jspx_push_body_count = _jspx_push_body_count;
    }
    public void invoke0( JspWriter out ) 
      throws Throwable
    {
      out.write("\n");
      out.write("    ");
      if (_jspx_meth_c_set_13(_jspx_parent, _jspx_page_context))
        return;
      out.write("\n");
      out.write("    <div class=\"tab-container\" align=center id=\"G4\">\n");
      out.write("    <p align=left>");
      ((org.apache.jasper.runtime.JspContextWrapper) this.jspContext).syncBeforeInvoke();
      _jspx_sout = null;
      if (getJspBody() != null)
        getJspBody().invoke(_jspx_sout);
      out.write("\n");
      out.write("  <h3>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${tabTitle}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("</h3>\n");
      out.write("        <table cellpadding=\"0\" cellspacing=\"0\" class=\"datatable\" summary=\"view/add notes\">\n");
      out.write("            <tbody>\n");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_0(_jspx_parent, _jspx_page_context))
        return;
      out.write("\n");
      out.write("                    ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_1(_jspx_parent, _jspx_page_context))
        return;
      out.write("\n");
      out.write("                    ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_2(_jspx_parent, _jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("                    ");
      if (_jspx_meth_c_if_5(_jspx_parent, _jspx_page_context))
        return;
      out.write("\n");
      out.write("                     ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_4(_jspx_parent, _jspx_page_context))
        return;
      out.write("\n");
      out.write("                    ");
      if (_jspx_meth_c_if_6(_jspx_parent, _jspx_page_context))
        return;
      out.write("\n");
      out.write("                    ");
      if (_jspx_meth_c_if_7(_jspx_parent, _jspx_page_context))
        return;
      out.write("\n");
      out.write("                    ");
      if (_jspx_meth_c_if_8(_jspx_parent, _jspx_page_context))
        return;
      out.write("\n");
      out.write("                    ");
      if (_jspx_meth_kul_htmlAttributeHeaderCell_8(_jspx_parent, _jspx_page_context))
        return;
      out.write("\n");
      out.write("                </tr>\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_html_hidden_0(_jspx_parent, _jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_c_if_9(_jspx_parent, _jspx_page_context))
        return;
      out.write("   \n");
      out.write("\n");
      out.write("  ");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_varStatus_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
      _jspx_th_c_forEach_0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
      _jspx_th_c_forEach_0.setVar("note");
      _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${notesBo}", java.lang.Object.class, (PageContext)this.getJspContext(), null, false));
      _jspx_th_c_forEach_0.setVarStatus("status");
      int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
      try {
        int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
        if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write('\n');
            out.write('\n');
            out.write('	');
            if (_jspx_meth_c_set_15(_jspx_th_c_forEach_0, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
              return;
            out.write('\n');
            out.write('	');
            //  c:if
            org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_14 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
            _jspx_th_c_if_14.setPageContext(_jspx_page_context);
            _jspx_th_c_if_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_forEach_0);
            _jspx_th_c_if_14.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${kfunc:canViewNoteAttachment(KualiForm.document, null)}", java.lang.Boolean.class, (PageContext)this.getJspContext(), _jspx_fnmap_3, false)).booleanValue());
            int _jspx_eval_c_if_14 = _jspx_th_c_if_14.doStartTag();
            if (_jspx_eval_c_if_14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\n");
                out.write("      <tr>\n");
                out.write("            ");
                if (_jspx_meth_kul_htmlAttributeHeaderCell_10(_jspx_th_c_if_14, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
                  return;
                out.write("\n");
                out.write("            <td class=\"datacell center\">\n");
                out.write("\t\t\t");
                if (_jspx_meth_bean_write_0(_jspx_th_c_if_14, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
                  return;
                out.write("\n");
                out.write("            &nbsp;</td>\n");
                out.write("\n");
                out.write("                        <td class=\"datacell center\">\n");
                out.write("                        ");
                if (_jspx_meth_bean_write_1(_jspx_th_c_if_14, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
                  return;
                out.write('\n');
                out.write("\n");
                out.write("                        ");
                if (_jspx_meth_c_if_15(_jspx_th_c_if_14, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
                  return;
                out.write("\n");
                out.write("\n");
                out.write("                        <td class=\"datacell center\">");
                if (_jspx_meth_bean_write_3(_jspx_th_c_if_14, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
                  return;
                out.write("</td>\n");
                out.write("\n");
                out.write("            ");
                out.write("\n");
                out.write("                        ");
                out.write('\n');
                out.write('\n');
                out.write("\n");
                out.write("\n");
                out.write("                            ");
                //  c:choose
                org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_choose_0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _jspx_tagPool_c_choose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
                _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
                _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
                int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
                if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  do {
                    out.write("\n");
                    out.write("                                ");
                    //  c:when
                    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_when_0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _jspx_tagPool_c_when_test.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
                    _jspx_th_c_when_0.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                    _jspx_th_c_when_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(!empty note.attachment) and (note.attachment.complete)}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
                    int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
                    if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      do {
                        out.write("\n");
                        out.write("                                  <td class=\"datacell center\">\n");
                        out.write("                                    \n");
                        out.write("                                    ");
                        //  c:if
                        org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_16 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
                        _jspx_th_c_if_16.setPageContext(_jspx_page_context);
                        _jspx_th_c_if_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                        _jspx_th_c_if_16.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${allowsNoteAttachments eq true}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null, false)).booleanValue());
                        int _jspx_eval_c_if_16 = _jspx_th_c_if_16.doStartTag();
                        if (_jspx_eval_c_if_16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          do {
                            out.write("\n");
                            out.write("                                      ");
                            if (_jspx_meth_c_if_17(_jspx_th_c_if_16, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
                              return;
                            out.write("\n");
                            out.write("                                      ");
                            if (_jspx_meth_c_if_18(_jspx_th_c_if_16, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
                              return;
                            out.write("\n");
                            out.write("                                      ");
                            if (_jspx_meth_bean_write_4(_jspx_th_c_if_16, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
                              return;
                            out.write("\n");
                            out.write("                                      &nbsp;\n");
                            out.write("                                      &nbsp;\n");
                            out.write("                                      <span style=\"white-space: nowrap\">\n");
                            out.write("                                        ");
                            //  kul:fileSize
                            org.apache.jsp.tag.web.kr.fileSize_tag _jspx_th_kul_fileSize_0 = new org.apache.jsp.tag.web.kr.fileSize_tag();
                            _jspx_th_kul_fileSize_0.setJspContext(_jspx_page_context);
                            _jspx_th_kul_fileSize_0.setParent(_jspx_th_c_if_16);
                            _jspx_th_kul_fileSize_0.setByteSize((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${note.attachment.attachmentFileSize}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
                            _jspx_th_kul_fileSize_0.setJspBody(new notes_tagHelper( 1, _jspx_page_context, _jspx_th_kul_fileSize_0, _jspx_push_body_count_c_forEach_0));
                            _jspx_th_kul_fileSize_0.doTag();
                            out.write("\n");
                            out.write("                                      </span>\n");
                            out.write("                                    ");
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
                        out.write("\n");
                        out.write("                                  </td>\n");
                        out.write("                                  ");
                        if (_jspx_meth_c_if_19(_jspx_th_c_when_0, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
                          return;
                        out.write("\n");
                        out.write("                                ");
                        int evalDoAfterBody = _jspx_th_c_when_0.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                    }
                    if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                      throw new SkipPageException();
                    }
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                    out.write("\n");
                    out.write("                                ");
                    if (_jspx_meth_c_otherwise_0(_jspx_th_c_choose_0, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
                      return;
                    out.write("\n");
                    out.write("                            ");
                    int evalDoAfterBody = _jspx_th_c_choose_0.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                }
                if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
                  throw new SkipPageException();
                }
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
                out.write("\n");
                out.write("\n");
                out.write("                            ");
                if (_jspx_meth_c_if_22(_jspx_th_c_if_14, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
                  return;
                out.write("\n");
                out.write("                           \n");
                out.write("                        <td class=\"datacell center\"><div align=\"center\">\n");
                out.write("                          ");
                if (_jspx_meth_c_if_25(_jspx_th_c_if_14, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
                  return;
                out.write(" &nbsp;\n");
                out.write("                          ");
                if (_jspx_meth_c_if_26(_jspx_th_c_if_14, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
                  return;
                out.write("  \n");
                out.write("                        </div></td>\n");
                out.write("                    </tr>\n");
                out.write("\t");
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
            out.write('\n');
            out.write(' ');
            out.write(' ');
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
        _jspx_tagPool_c_forEach_varStatus_var_items.reuse(_jspx_th_c_forEach_0);
      }
      out.write("\n");
      out.write("              </tbody>\n");
      out.write("        </table>\n");
      out.write("    </div>\n");
      return;
    }
    public void invoke1( JspWriter out ) 
      throws Throwable
    {
      out.write("\n");
      out.write("                                            (");
      if (_jspx_meth_c_out_0(_jspx_parent, _jspx_page_context, _jspx_push_body_count))
        return;
      out.write(',');
      out.write(' ');
      out.write(' ');
      if (_jspx_meth_bean_write_5(_jspx_parent, _jspx_page_context, _jspx_push_body_count))
        return;
      out.write(")\n");
      out.write("                                        ");
      return;
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
