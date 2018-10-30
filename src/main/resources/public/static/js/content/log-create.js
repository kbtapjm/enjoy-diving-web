$(document).ready(function() {
  try {
    LogCreateModule.init();
  } catch (e) {
    log('Error : ' + e.toString());
  }
}); 

var LogCreateModule = LogCreateModule || (function($) {
  'use strict'
  
  var _form = $('#form')
    , url = _form.attr('action')
  	, create = _form.data('create')
  	, id = _form.data('id');

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
            diveNo: {
              identifier: 'diveNo',
              rules: [
                {
                  type   : 'empty',
                  prompt : '로그 번호를 입력하세요.'
                }
              ]
            },
            diveDate: {
              identifier: 'diveDate',
              rules: [
                {
                  type   : 'empty',
                  prompt : '로그 날짜를 입력하세요.'
                }
              ]
            },
            divePlace: {
              identifier: 'divePlace',
              rules: [
                {
                  type   : 'empty',
                  prompt : '다이브 장소를 입력하세요.'
                }
              ]
            },
            divePoint: {
              identifier: 'divePoint',
              rules: [
                {
                  type   : 'empty',
                  prompt : '다이브 포인트를 입력하세요.'
                }
              ]
            },
            diveInHour: {
              identifier: 'diveInHour',
              rules: [
            	{
                  type   : 'empty',
                  prompt : '다이브 입수시간(시)을 입력하세요.'
                }
              ]
            },
            diveInMinute: {
              identifier: 'diveInMinute',
              rules: [
              	{
                  type   : 'empty',
                  prompt : '다이브 입수시간(분)을 입력하세요.'
                }
              ]
            },
            diveOutHour: {
              identifier: 'diveOutHour',
              rules: [
              	{
              	  type   : 'empty',
                  prompt : '다이브 출수시간(시)을 입력하세요.'
                }
              ]
            },
            diveOutMinute: {
              identifier: 'diveOutMinute',
              rules: [
              	{
              	  type   : 'empty',
                  prompt : '다이브 출수시간(분)을 입력하세요.'
                }
              ]
            },
            diveTankStart: {
              identifier: 'diveTankStart',
              rules: [
              	{
              	  type   : 'empty',
                  prompt : '탱크 압력 시작(Bar)을 입력하세요.'
                }
              ]
            },
            diveTankEnd: {
              identifier: 'diveTankEnd',
              rules: [
               	{
              	  type   : 'empty',
                  prompt : '탱크 압력 종료(Bar)를 입력하세요.'
                }
              ]
            },
            maxDepth: {
              identifier: 'maxDepth',
              rules: [
               	{
               	  type   : 'empty',
                  prompt : '최대 수심을 입력하세요.'
                }
              ]
            },
            avgDepth: {
              identifier: 'avgDepth',
              rules: [
               	{
               	  type   : 'empty',
                  prompt : '평균 수심을 입력하세요.'
                }
              ]
            },
            diveTime: {
              identifier: 'diveTime',
              rules: [
               	{
               	  type   : 'empty',
                  prompt : '다이브 시간을 입력하세요.'
                }
              ]
            },
            diveSafetyTime: {
              identifier: 'diveSafetyTime',
              rules: [
               	{
               	  type   : 'empty',
                  prompt : '안전정지 시간을 입력하세요.'
                }
              ]
            },
            divePlanTool: {
              identifier: 'divePlanTool',
              rules: [
               	{
               	  type   : 'empty',
                  prompt : '계획도구를 선택하세요.'
                }
              ]
            },
            divePlanExrPtn: {
              identifier: 'divePlanExrPtn',
              rules: [
               	{
               	  type   : 'empty',
                  prompt : '노출 보호를 선택하세요.'
                }
              ]
            },
            visibility: {
              identifier: 'visibility',
              rules: [
               	{
               	  type   : 'empty',
                  prompt : '시야를 입력하세요.'
                }
              ]
            },
            temperature: {
              identifier: 'temperature',
              rules: [
               	{
               	  type   : 'empty',
                  prompt : '수온을 입력하세요.'
                }
              ]
            }
          }
        });
      },
      
      submit: function() {
        var data = _form.serializeObject();
        delete data._csrf;
        
        $.each(data, function (key, value) {
          data[key] = $.trim(value);
        });
        
        if (!$('#form').form('is valid')) {
          return false;
        }
        
        if (!create) {
          data.id = id;
        }
        
        UiUtilModule.mask.open();
        $.ajax({
          method: (create ? 'POST' : 'PUT'),
          headers: { 
            Accept: 'application/json; charset=UTF-8'
          },
          contentType: 'application/json; charset=UTF-8',
          url: (create ? url : url + '/' + id),
          data: JSON.stringify(data)
        }).done(function(data) {
        	if (data.resultCd !== constants.result.SUCCESS) {
        	  alert(data.resultMsg);
        	  UiUtilModule.mask.close();
        	  return false;
        	}
        	
        	UiUtilModule.modal.alert({
            msg: $('#submit').data('done'),
            actions: {
              'yes': function() {
                $(location).attr('href', (create ? url : url + '/' + id));
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
	        title: '삭제',
	        msg: '로그를 삭제 하시겠습니까?',
	        actions: {
	          'yes': function() {
	            UiUtilModule.mask.open();
	            $.ajax({
	              method: 'DELETE',
	              headers: { 
	                Accept: 'application/json; charset=UTF-8'
	              },
	              contentType: 'application/json; charset=UTF-8',
	              url: url + '/' + id,
	              data: JSON.stringify(data)
	            }).done(function(data) {
	              if (data.resultCd !== constants.result.SUCCESS) {
	                alert(data.resultMsg);
	                return false;
	              }
	              
	              UiUtilModule.modal.alert({
	                msg: $('#remove').data('remove'),
	                actions: {
	                  'yes': function() {
	                    $(location).attr('href', url);
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
	      
	      UiUtilModule.modal.confirm(opt);
      }
  };
  
  var event = {
    init: function() {
      $('.ui.dropdown').dropdown();
      $('.ui.radio.checkbox').checkbox();
      
      $('#cancel').on('click', function(e) {
    	location.href = '/log';
      });
      
      $('#submit').on('click', function(e) {
    	  e.preventDefault();
    	  LogCreateModule.data.submit();
      });
      
      $('#remove').on('click', function(e) {
          e.preventDefault();
          LogCreateModule.data.remove();
        });
      
      $('#diveDateCalendar').calendar({
    	  type: 'date',
    	  formatter: {
    	    date: function (date, settings) {
    	      if (!date) return '';
    	      
    	      var day = date.getDate();
    	      var month = date.getMonth() + 1;
    	      var year = date.getFullYear();
    	      
    	      return  year + '-' + addZero(month) + '-' + addZero(day);
    	    }
    	  }
      });
    }
  };
  
  return {
    init: init,
    data: {
      init: data.init,
      submit: data.submit,
      remove: data.remove,
      validation: data.validation
    },
    event: {
      init: event.init
    }
  }
})(jQuery)