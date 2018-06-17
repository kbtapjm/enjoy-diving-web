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
        UiUtilModule.mask.close();
        UiUtilModule.modal.alert({
          msg: '정상처리되었습니다.',
          actions: {
            'yes': function() {
              location.reload();
            }
          }
        });
      }).fail(function(jqXHR, textStatus, errorThrown) {
        UiUtilModule.mask.close();
        console.error(jqXHR);
      });
    },
    
    remove: function() {
      var opt ={
        title: '회원 탈퇴',
        msg: '회원 탈퇴 하시겠습니까?',
        actions: {
          'yes': function() {
            UiUtilModule.mask.open();
            $.ajax({
              method: 'DELETE',
              headers: { 
                Accept: 'application/json; charset=UTF-8'
              },
              contentType: 'application/json; charset=UTF-8',
              url: url + '/' + $('#id').val(),
              data: JSON.stringify(data)
            }).done(function(data) {
              UiUtilModule.mask.close();
              location.href = '/signOut';
            }).fail(function(jqXHR, textStatus, errorThrown) {
              UiUtilModule.mask.close();
              console.error(jqXHR);
            });
          },
          'no': function() {}
        }
      }
      
      UiUtilModule.modal.confirm(opt);
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
      
      $('#remove').on('click', function(e) {
        e.preventDefault();
        UserReadModule.data.remove();
      });
    }
  };
  
  return {
    init: init,
    data: {
      init: data.init,
      submit: data.submit,
      remove: data.remove,
      validation: data.validation,
      login: data.login
    },
    event: {
      init: event.init
    }
  }
})(jQuery)