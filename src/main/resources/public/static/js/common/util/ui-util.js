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
  
  var modal = {
    alert: function(opts) {
      var default_opts =  {
        title: '알림',
        msg: '',
        actions: {
          'yes': function() {}
        }
      }
           
      var opts = $.extend({}, default_opts, opts); 
       
      if (opts.title) {
        $('#modal-header').text(opts.title);
      }
      if (opts.msg) {
        $('#modal-msg').text(opts.msg);
      }
      
      if (opts.actions) {
        if (opts.actions.yes) {
          $('#modal-action-yes').show();
          $('#modal-action-yes').click(function(){
            opts.actions.yes();
          })
        }
        
        if (opts.actions.no) {
          $('#modal-action-no').show();
          $('#modal-action-no').click(function(){
            opts.actions.no();
          })        
        } else {
          $('#modal-action-no').hide();
        }
      }
      
      $('.mini.modal').modal('show');
    },
    
    confirm: function(opts) {
      var default_opts =  {
        title: '알림',
        msg: '',
        actions: {
          'yes': function() {},
          'no': function() {}
        }
      }
           
      var opts = $.extend({}, default_opts, opts); 
       
      if (opts.title) {
        $('#modal-header').text(opts.title);
      }
      
      if (opts.msg) {
        $('#modal-msg').text(opts.msg);
      }
      
      if (opts.actions) {
        if (opts.actions.yes) {
          $('#modal-action-yes').show();
          $('#modal-action-yes').click(function(){
            opts.actions.yes();
          })
        }
        
        if (opts.actions.no) {
          $('#modal-action-no').show();
          $('#modal-action-no').click(function(){
            opts.actions.no();
          })
        } else {
          $('#modal-action-no').hide();
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
    modal: {
      alert: modal.alert,
      confirm: modal.confirm
    }
  };
})(jQuery)