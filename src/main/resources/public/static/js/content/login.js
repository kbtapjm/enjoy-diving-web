$(document).ready(function() {
  try {
    LoginModule.init();
  } catch (e) {
    log('Error : ' + e.toString());
  }
}); 

var LoginModule = LoginModule || (function($) {
  'use strict'
  
  var _form = $('#form')
  , url = _form.attr('action');

  var init = function() {
    this.data.init();
    this.event.init();
  };
  
  var data = {
    init: function() {
      this.validation(); 
    },
    
    validation: function() {
      $('.ui.form')
      .form({
        fields: {
          email: {
            identifier: 'email',
            rules: [
              {
                type   : 'empty',
                prompt : '이메일을 입력하세요.'
              },
              {
                type   : 'email',
                prompt : '유효한 이메일을 입력하세요.'
              }
            ]
          },
          password: {
            identifier: 'password',
            rules: [
              {
                type   : 'empty',
                prompt : '비밀번호를 입력하세요.'
              },
              {
                type   : 'minLength[6]',
                prompt : '비밀번호는 {ruleValue}자 이상이어야합니다.'
              }
            ]
          }
        }
      });
    },
    
    // 로그인 처리
    submit: function() {
      var data = _form.serializeObject();
      
      $.each(data, function (key, value) {
        data[key] = $.trim(value);
      });
      
      if (!$('.ui.form').form('is valid')) {
        return false;
      }
      
      // TODO: loading 추가
      $.ajax({
        method: 'POST',
        headers: { 
          Accept: 'application/json; charset=UTF-8'
        },
        contentType: 'application/json; charset=UTF-8',
        url: url,
        data: JSON.stringify(data)
      }).done(function(data) {
        location.href = '/';
      }).fail(function(jqXHR, textStatus, errorThrown) {
        console.error(jqXHR);
      });
    }
  };
  
  var event = {
    init: function() {
      $('#submit').on('click', function(e) {
        e.preventDefault();
        LoginModule.data.submit();
      });
    }
  };
  
  return {
    init: init,
    data: {
      init: data.init,
      validation: data.validation,
      submit: data.submit
    },
    event: {
      init: event.init
    }
  }
})(jQuery)