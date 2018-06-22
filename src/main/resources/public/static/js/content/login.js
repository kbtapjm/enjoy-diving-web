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
      $('#loginForm')
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
      var _form = $('#loginForm')
      , url = _form.attr('action');
      
      var data = {
        email: $('#login-email').val(),
        password: $('#login-password').val()
      }
      
      if (!$('#loginForm').form('is valid')) {
        return false;
      }
      
      $.ajax({
        method: 'POST',
        headers: { 
          Accept: 'application/json; charset=UTF-8'
        },
        contentType: 'application/json; charset=UTF-8',
        url: url,
        data: JSON.stringify(data)
      }).done(function(data) {
        if (data.resultCd !== '0000') {
          LoginModule.data.setErrorMsg('이메일 또는 비밀번호가 일치 하지 않습니다.');
          return false;
        }
        
        location.href = '/';
      }).fail(function(jqXHR, textStatus, errorThrown) {
        console.error(jqXHR);
      });
    },
    
    setErrorMsg: function(msg) {
      var html = '';
      html += '<ul class="list">';
      html += ' <li> ' + msg + '</li>';
      html += '</ul>';
      
      $('.ui.error.message').show();
      $('.ui.error.message').html(html);
    }
  };
  
  var event = {
    init: function() {
      $('#btnLogin').on('click', function(e) {
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
      setErrorMsg: data.setErrorMsg,
      submit: data.submit
    },
    event: {
      init: event.init
    }
  }
})(jQuery)