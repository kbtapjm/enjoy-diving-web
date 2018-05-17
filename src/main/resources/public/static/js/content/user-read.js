$(document).ready(function() {
  try {
    UserReadModule.init();
  } catch (e) {
    log('Error : ' + e.toString());
  }
}); 

var UserReadModule = UserReadModule || (function($) {
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
          }
        }
      });
    },
    
    submit: function() {
      var data = _form.serializeObject();
      
      $.each(data, function (key, value) {
        data[key] = $.trim(value);
      });
      
      if (!$('.ui.form').form('is valid')) {
        return false;
      }
      
      UiUtilModule.mask.open();
      $.ajax({
        method: 'PUT',
        headers: { 
          Accept: 'application/json; charset=UTF-8'
        },
        contentType: 'application/json; charset=UTF-8',
        url: url,
        data: JSON.stringify(data)
      }).done(function(data) {
        $('.ui form').addClass('success');
        UiUtilModule.mask.close();
        location.reload();
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
        UserReadModule.data.submit();
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