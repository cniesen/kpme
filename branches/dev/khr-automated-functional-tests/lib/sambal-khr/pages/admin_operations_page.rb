class AdminOperationsPage < BasePage

  element(:document_no) { |b| b.div(id: 'adminActions').td(class: 'content').div(class: 'portlet',index: 2).div(id: 'content').text_field(name: 'deleteDocumentId' ) }
  action(:submit)  { |b| b.div(id: 'adminActions').td(class: 'content').div(class: 'portlet',index: 2).div(id: 'content').input(value: 'Submit').click }
  value(:delete_message) { |b| b.div(id: 'adminActions').td(class: 'content').div(class: 'portlet',index: 2).div(id: 'content').text }


end