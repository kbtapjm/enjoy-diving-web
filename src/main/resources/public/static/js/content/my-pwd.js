$(document).ready(function() {
  try {
    myPwdModule.init();
  } catch (e) {
    log('Error : ' + e.toString());
  }
}); 

var myPwdModule = myPwdModule || (function($) {
  'use strict'
  
  var _form = $('#myPwdform');

  var init = function() {
    this.data.init();
    this.event.init();
  };
  
  var data = {
    init: function() {
      this.validation(); 
    },
    
    validation: function() {
      $('#myPwdform')
      .form({
        fields: {
          oldPassword: {
            identifier: 'oldPassword',
            rules: [
              {
                type   : 'empty',
                prompt : '이전 비밀번호를 입력하세요.'
              }
            ]
          },
          newPassword: {
            identifier: 'newPassword',
            rules: [
              {
                type   : 'empty',
                prompt : '새 비밀번호를 입력하세요.'
              },
              {
                type   : 'minLength[6]',
                prompt : '새 비밀번호는 {ruleValue}자 이상이어야합니다.'
              }
            ]
          },
          newPasswordConfirm: {
            identifier: 'newPassword-confirm',
            rules: [
              {
                type   : 'empty',
                prompt : '새 비밀번호 확인을 입력하세요.'
              },
              {
                type   : 'match[newPassword]',
                prompt : '새 비밀번호와 새 비밀번호 확인 값이 일치 하지 않습니다.'
              }
            ]
          }
        }
      });
    },
    
    update: function() {
      var opt ={
        title: '비밀번호 변경',
        msg: '비밀번호를 변경 하시겠습니까?',
        actions: {
          'yes': function() {
            var data = _form.serializeObject();
            
            $.each(data, function (key, value) {
              data[key] = $.trim(value);
            });
            
            UiUtilModule.mask.open();
            $.ajax({
              method: 'PUT',
              headers: { 
                Accept: 'application/json; charset=UTF-8'
              },
              contentType: 'application/json; charset=UTF-8',
              url: '/my/pwd',
              data: JSON.stringify(data)
            }).done(function(data) {
              UiUtilModule.mask.close();
              
              if (data.resultCd !== constants.result.SUCCESS) {
                alert(data.resultMsg);
                return false;
              }
              
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
          'no': function() {}
        }
      }
      
      if (!$('#myPwdform').form('is valid')) {
        return false;
      }
      
      UiUtilModule.modal.confirm(opt);
    }
  };
  
  var event = {
    init: function() {
      $('#update').on('click', function(e) {
        e.preventDefault();
        myPwdModule.data.update();
      });
    }
  };
  
  return {
    init: init,
    data: {
      init: data.init,
      update: data.update,
      validation: data.validation
    },
    event: {
      init: event.init
    }
  }
})(jQuery)