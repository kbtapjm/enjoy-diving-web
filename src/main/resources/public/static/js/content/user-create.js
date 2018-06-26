$(document).ready(function() {
  try {
    UserCreateModule.init();
  } catch (e) {
    log('Error : ' + e.toString());
  }
}); 

var UserCreateModule = UserCreateModule || (function($) {
  'use strict'
  
  var _form = $('#createform')
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
      $('#createform')
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
          },
          passwordConfirm: {
            identifier: 'password-confirm',
            rules: [
              {
                type   : 'empty',
                prompt : '비밀번호 확인을 입력하세요.'
              },
              {
                type   : 'match[password]',
                prompt : '비밀번호와 비밀번호 확인 값이 일치 하지 않습니다.'
              }
            ]
          },
          name: {
            identifier: 'name',
            rules: [
              {
                type   : 'empty',
                prompt : '이름을 입력하세요.'
              }
            ]
          },
          nickname: {
            identifier: 'nickname',
            rules: [
              {
                type   : 'empty',
                prompt : '닉네임을 입력하세요.'
              }
            ]
          },
          gender: {
            identifier: 'gender',
            rules: [
              {
                type   : 'empty',
                prompt : '성별을 선택하세요.'
              }
            ]
          },
          country: {
            identifier: 'country',
            rules: [
              {
                type   : 'empty',
                prompt : '국가를 선택하세요.'
              }
            ]
          },
          terms: {
            identifier: 'terms',
            rules: [
              {
                type   : 'checked',
                prompt : '이용 약관에 동의해야합니다.'
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
      
      if (!$('#createform').form('is valid')) {
        return false;
      }
      
      UiUtilModule.mask.open();
      $.ajax({
        method: 'POST',
        headers: { 
          Accept: 'application/json; charset=UTF-8'
        },
        contentType: 'application/json; charset=UTF-8',
        url: url,
        data: JSON.stringify(data)
      }).done(function(data) {
        UserCreateModule.data.login();
      }).fail(function(jqXHR, textStatus, errorThrown) {
        UiUtilModule.mask.close();
        console.error(jqXHR);
      });
    },
    
    login: function() {
      var data = {
        email: $('#email').val(),
        password: $('#password').val()
      }
      
      $.ajax({
        method: 'POST',
        headers: { 
          Accept: 'application/json; charset=UTF-8'
        },
        contentType: 'application/json; charset=UTF-8',
        url: '/login',
        data: JSON.stringify(data)
      }).done(function(data) {
        console.log(data);
        
        // main redirect
        location.href = '/';
      }).fail(function(jqXHR, textStatus, errorThrown) {
        UiUtilModule.mask.close();
        console.error(jqXHR);
      });
    }
  };
  
  var event = {
    init: function() {
      $('.ui.dropdown').dropdown();
      $('.ui.radio.checkbox').checkbox();
      
      $('#submit').on('click', function(e) {
        e.preventDefault();
        UserCreateModule.data.submit();
      });
      
      $('#cancel').on('click', function(e) {
        e.preventDefault();
        location.href = '/';
      });
    }
  };
  
  return {
    init: init,
    data: {
      init: data.init,
      submit: data.submit,
      validation: data.validation,
      login: data.login
    },
    event: {
      init: event.init
    }
  }
})(jQuery)