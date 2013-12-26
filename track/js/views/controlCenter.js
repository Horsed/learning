var ControlCenter = {};

(function() {
    ControlCenter = Backbone.View.extend({
        
        el: "div.controlCenter",
        
    	title: "input#title",
    	
    	events: {
    		"click button#add": "create",
    		"keypress input#title": "create",
    		"click button#sum": "sum"
    	},
    	
    	initialize: function(){
    		_.bindAll(this, 'create', 'sum');
    		Util.vent.bind("deltaChanged", this.sum);
            
            $('button#add', this.el).button({
        		icons: {primary: "ui-icon-document"}, text: false
    		});
    		
    		$('button#sum', this.el).button({
    			icons: {primary: "ui-icon-clock"}, text: false
    		});
    	},
        
        create: function(e) {
            return Util.keyboard.executeOnEnter(e, this.fireCreate); 
    	},
        
        fireCreate: function() {
            var title = $(this.title, this.el).val();
            
        }
        
    });
}())