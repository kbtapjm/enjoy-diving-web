var LoginModule = LoginModule || (function($) {
  'use strict'
  
  var init = function() {
    this.data.init();
    this.event.init();
  };
  
  var data = {
    init: function() {
      this.validation(); 
    },
    
    validation: function() {
      $('#form')
      .form({
        fields: {
          email: {
            identifier: 'login-email',
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
            identifier: 'login-password',
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
    
    submit: function() {
      var _form = $('#form')
      , url = _form.attr('action');
      
      var data = {
        email: $('#login-email').val(),
        password: $('#login-password').val()
      }
      
      if (!$('#form').form('is valid')) {
        return false;
      }
      
      $('#form').submit();
    }
  };
  
  var event = {
    init: function() {
      $('#btnLogin').on('click', function(e) {
        e.preventDefault();
        LoginModule.data.submit();
      });
      
      $('#btnFacebook').on('click', function(e) {
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