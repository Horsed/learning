var Tasks = {};
Tasks = Backbone.Collection.extend({
	
	model: Task,
	
	url: "/tasks",
	
	getSum: function() {
		var overallDelta = 0.0;
		for(var i = 0; i < this.models.length; i++) {
			this.models[i].calcDelta();
			overallDelta = overallDelta + this.models[i].get("delta");
		}			
		return Util.number.roundFloat(overallDelta, 3);
	}
});