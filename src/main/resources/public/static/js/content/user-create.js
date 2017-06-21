$(document).ready(function() {
  try {
    UserCreateModule.init();
  } catch (e) {
    log('Error : ' + e.toString());
  }
}); 

var UserCreateModule = UserCreateModule || (function($) {
  'use strict'
  
  var _form = $('#form')
  , url = _form.attr('action');

  var init = function() {
    this.data.init();
    this.event.init();
  };
  
  var data = {
    init: function() {
      //this.validate();
    },
    
    validate: function() {
      $('.ui.form')
      .form({
        fields: {
          name: {
            identifier: 'name',
            rules: [
              {
                type   : 'empty',
                prompt : '이름을 입력하세요.'
              }
            ]
          }
        }
      });
    },
    
    submit: function() {
      var data = _form.serializeObject();
      
      $.each(data, function (key, value) {
        data[key] = $.trim(value);
      });
      
      // TODO 벨리데이션 체크 로직(우선적으로 서버에서 체크) 
      
      $.ajax({
        method: 'POST',
        headers: { 
          Accept: 'application/json; charset=UTF-8'
        },
        contentType: 'application/json; charset=UTF-8',
        url: url,
        data: JSON.stringify(data)
      }).done(function(data) {
        
      }).fail(function(jqXHR, textStatus, errorThrown) {
        console.error(jqXHR);
      });
    },
    
    validation: function(data) {
      return true;
    }
  };
  
  var event = {
    init: function() {
      $('.ui.dropdown').dropdown();
      
      $('#submit').on('click', function(e) {
        e.preventDefault();
        UserCreateModule.data.submit();
      });
    }
  };
  
  return {
    init: init,
    data: {
      init: data.init,
      submit: data.submit,
      validate: data.validate
    },
    event: {
      init: event.init
    }
  }
})(jQuery)