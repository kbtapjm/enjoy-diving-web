$(document).ready(function() {
  try {
    LogListModule.init();
  } catch (e) {
    log('Error : ' + e.toString());
  }
}); 

var LogListModule = LogListModule || (function($) {
  'use strict'
  
  var init = function() {
    this.data.init();
    this.event.init();
  };
  
  var data = {
    init: function() {
    
    },
    
    logDetail: function(id) {
    	location.href = '/log/' + id;
    }
  };
  
  var event = {
    init: function() {
      
    }
  };
  
  return {
    init: init,
    data: {
      init: data.init,
      logDetail: data.logDetail
    },
    event: {
      init: event.init
    }
  }
})(jQuery)