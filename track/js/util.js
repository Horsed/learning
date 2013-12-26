var Util = {
    number: {},
    vent: {},
    keyboard: {}
    };

(function($) {

    Util.number.roundFloat = function(x, n) {
		n = parseInt(n) ? n : 0;
		return parseFloat(x) ? Math.round(x*Math.pow(10,n))/Math.pow(10,n) : x;
	};
    
    Util.keyboard = {
        executeOnEnter: function(event, callback) {
            if(event.keyCode != null && event.keyCode != undefined) {
        		if(event.keyCode == 13) {
    				return callback();
    			}
    		} else {
    			return callback();
    		}
        }
    }
	
	Util.vent = {};
	_.extend(Util.vent, Backbone.Events);
	
})($);