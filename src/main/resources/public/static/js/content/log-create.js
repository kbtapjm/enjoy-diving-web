$(document).ready(function() {
  try {
    LogCreateModule.init();
  } catch (e) {
    log('Error : ' + e.toString());
  }
}); 

var LogCreateModule = LogCreateModule || (function($) {
  'use strict'
  
  var _form = $('#form');

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
              identifier: 'diveNo',
              rules: [
                {
                  type   : 'empty',
                  prompt : '로그 번호를 입력하세요.'
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
        
        if (!$('#form').form('is valid')) {
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
          LogCreateModule.data.submit();
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
      validation: data.validation
    },
    event: {
      init: event.init
    }
  }
})(jQuery)