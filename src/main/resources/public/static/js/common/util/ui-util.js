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
  
  return {
    mask: {
      open: mask.open,
      close: mask.close
    }
  };
})(jQuery)