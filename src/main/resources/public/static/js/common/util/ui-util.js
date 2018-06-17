'use strict';
var UiUtilModule = UiUtilModule || (function($) {
  'use strict';
  
  var mask = {
    open: function() {
      $('.form').addClass('loading');
    },
    close: function() {
      $('.form').removeClass('loading');
    }
  }
  
  var modalMsg = {
    open: function(opts) {
      
      var default_opts =  {
         title: '알림',
         msg: '',
         actions: {
           
         }
      }
          
      var opts = $.extend({}, default_opts, opts); 
      
      if (opts.title) {
        $('#modal-header').text(opts.title);
      }
      if (opts.msg) {
        $('#modal-msg').text(opts.msg);
      }
      
      if (opts.actions && typeof opts.actions.yes === 'function') {
        if (opts.actions.no) {
          $('#modal-action-no').show();
          $('#modal-action-no').on('click', function() {
            opts.actions.no(); 
          });        
        } else {
          $('#modal-action-no').hide();
        }
        if (opts.actions.yes) {
          $('#modal-action-yes').show();
          $('#modal-action-yes').on('click', function() {
            opts.actions.yes(); 
          });        
        } else {
          $('#modal-action-yes').hide();
        }
      } else {
        $('#modal-action-yes').show();
      }
      
      $('.mini.modal').modal('show');
    },
    
    alert: function(opts) {
      
    },
    
    confirm: function() {
      
    }
  }
  
  return {
    mask: {
      open: mask.open,
      close: mask.close
    },
    modalMsg: {
      open: modalMsg.open
    }
  };
})(jQuery)