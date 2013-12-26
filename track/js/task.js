var Task = {};

Task = Backbone.Model.extend({
	defaults: {
		running: false,
		start: "-",
		stop: "-",
		calcTime: null,
		delta: 0
	},
	
	initialize: function() {
		this.set({
			id: new Date().getTime(),
			created: new Date()
		});
	},
	
	url: "/task",
	
	getDelta: function() {
		return Util.number.roundFloat(this.get("delta"), 3);
	},
	
	toggle: function() {
		if(this.get("running")) {
			var stopDate = new Date();
			var startTime = this.get("calcTime") != null ? this.get("calcTime") : this.get("start");
			this.set({
				running: false,
				stop: stopDate,
				calcTime: null,
				delta: this.get("delta") + (stopDate.getTime() - startTime.getTime()) / 3600000
			});
		} else {
			this.set({
				running: true,
				start: new Date(),
				stop: " ",
				calcTime: null
			});
		}
	},
			
	addDelta: function(value) {
		value = !parseFloat(value) ? 0 : parseFloat(value);
		this.set({delta: this.get("delta") + value});
	},
	
	calcDelta: function() {
		var currDelta = this.get("delta");
		var tmpDelta = 0;
		if(parseFloat(currDelta) || currDelta === 0) {
			if(this.get("running")) {
				if(this.get("calcTime") != null)
					tmpDelta = ((new Date()).getTime() - this.get("calcTime").getTime()) / 3600000;
				else
					tmpDelta = ((new Date()).getTime() - this.get("start").getTime()) / 3600000;
				this.set({
					calcTime: new Date()
				});
			}
		}
		this.set({
			delta: currDelta + tmpDelta
		});
	},
	
	reset: function() {
		this.set({delta: 0});
	}
	
});