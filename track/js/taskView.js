var TaskView = {};

(function() {
    TaskView = Backbone.View.extend({
        
    	tagName: 'li',
    	
    	events: {
    		"click button#toggle": "toggle",
    		"click button#reset": "reset",
    		"click button#remove": "clear",
    		"click button#addDelta": "add",
    		"keypress input#addDelta": "add",
    		"click a.titleLink": "toggleUI"
    	},
    	
    	initialize: function() {
            _.bindAll(this, 'addDelta');
    		this.model.bind('change', this.render, this);
    	},
    	
    	render: function() {
            var templateData = {
                title: this.model.get("title"),
                delta: this.model.getDelta(),
        	    created: this.niceDate(this.model.get("created")),
    		    start: this.niceDate(this.model.get("start")),
    		    stop: this.niceDate(this.model.get("stop"))
            }
    		var html = ich.task(templateData);
    		$(this.el).html(html).addClass("task").addClass("shadow");
    		
    		$(".taskBody", this.el).hide();
    		
    		if(this.model.get("running")) {
    			$(this.el).addClass("active");
    			$("button#toggle", this.el).button({ icons: {primary: "ui-icon-stop"}, text: false });
    		} else {
    			$(this.el).removeClass("active");
    			$("button#toggle", this.el).button({ icons: {primary: "ui-icon-play"}, text: false });
    		}
    		
    		$("button#sum", this.el).button({
    			icons: {primary: "ui-icon-clock"}, text: false
    		});
    		
    		$("button#remove", this.el).button({
    			icons: {primary: "ui-icon-trash"}, text: false
    		});
    		
    		$("button#addDelta", this.el).button({
    			icons: {primary: "ui-icon-plus"}, text: false
    		});
    		
    		$("button#reset", this.el).button({
    			icons: {primary: "ui-icon-seek-first"}, text: false
    		});	
    		
    		return this;
    	},
    	
    	toggle: function() {
    		this.model.toggle();
    	},
    	
    	reset: function() {
    		this.model.reset();
    	},
    	
    	add: function(e) {
    		return Util.keyboard.executeOnEnter(e, this.addDelta); 
    	},
        
        addDelta: function() {
            this.model.addDelta($("input#addDelta", this.el).val());
        },
    	
    	clear: function() {
    		if(confirm(unescape("Task \"" + this.model.get("title") + "\" l%F6schen?"))) {
    			this.model.destroy();
    			this.remove();
    			this.collection.remove(this.model);
    		}
    	},
    	
    	toggleUI: function() {
    		$("div#.taskBody", this.el).toggle();
    	},
    	
    	niceDate: function(date) {
    		if(date instanceof Date) {
    			var d = (date.getDay() < 10 ? "0" : "") + date.getDay();
    			var M = (date.getMonth() < 10 ? "0" : "") + date.getMonth();
    			var h = (date.getHours() < 10 ? "0" : "") + date.getHours();
    			var m = (date.getMinutes() < 10 ? "0" : "") + date.getMinutes();
    			return d + "." + M + "." + date.getFullYear() + " " + h + ":" + m;
    		} else return date;
    	}
    });
}())