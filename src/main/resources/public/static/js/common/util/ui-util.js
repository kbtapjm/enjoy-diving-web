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
      if (opts.title) {
        $('#modal-header').text(opts.title);
      }
      if (opts.msg) {
        $('#modal-msg').text(opts.msg);
      }
      
      if (opts.actions && typeof opts.actions.yes === 'function') {
        /*if (opts.actions.no) {
          $('#modal-action-no').on('click', function() {
            opts.actions.no(); 
          });        
        }*/
        if (opts.actions.yes) {
          $('#modal-action-yes').on('click', function() {
            opts.actions.yes(); 
          });        
        }
      }
      
      $('.mini.modal').modal('show');
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